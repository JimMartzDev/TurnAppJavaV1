package Controlador;

import Modelo.registro.RegistroDAO;
import Modelo.registro.RegistroVO;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jimma
 */
public class RegistroController extends HttpServlet {

    RegistroDAO registroDAO;
    RegistroVO registroVO;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mensaje = "";
        RequestDispatcher rd = null;
        
        String accion = request.getParameter("accion");
        
        if (accion != null && accion.equals("registrar")) {
            
            // 1. Capturamos los datos del formulario
            String nombres = request.getParameter("nombres");
            String apellidos = request.getParameter("apellidos");
            String tipoDocumento = request.getParameter("tipoDocumento");
            String documento = request.getParameter("documento");
            String fechaNacimiento = request.getParameter("fechaNacimiento"); // Formato yyyy-MM-dd
            String correo = request.getParameter("correo");
            String password = request.getParameter("password");
            String passwordConfirm = request.getParameter("passwordConfirm");
            
            // 2. FILTRO 1: Validar contraseñas
            if (password == null || passwordConfirm == null || !password.equals(passwordConfirm)) {
                mensaje = "La contraseña no coincide.";
            } else {
                
                // 3. NUEVO - FILTRO 2: Validar mayoría de edad
                boolean esMayorDeEdad = false;
                try {
                    if (fechaNacimiento != null && !fechaNacimiento.equals("")) {
                        // Convertimos el String que viene del formulario a un objeto LocalDate
                        LocalDate fechaNac = LocalDate.parse(fechaNacimiento);
                        LocalDate fechaActual = LocalDate.now();
                        
                        // Calculamos el periodo de tiempo entre la fecha de nacimiento y hoy
                        int edad = Period.between(fechaNac, fechaActual).getYears();
                        
                        if (edad >= 18) {
                            esMayorDeEdad = true;
                        }
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("Error al parsear la fecha: " + e.getMessage());
                }

                if (!esMayorDeEdad) {
                    mensaje = "Debes ser mayor de edad para registrarte en la aplicación.";
                } else {
                    
                    // Si es mayor de edad, procedemos con las validaciones de la Base de Datos
                    registroDAO = new RegistroDAO();
                    
                    // FILTRO 3: Validar si el documento ya existe
                    RegistroVO documentoExistente = registroDAO.EncontrarUsuario(documento);
                    
                    if (documentoExistente.getNumIdentificacion() != null && !documentoExistente.getNumIdentificacion().equals("")) {
                        mensaje = "El número de documento ya se encuentra registrado.";
                    } else {
                        
                        // FILTRO 4: Validar si el correo ya existe
                        RegistroVO correoExistente = registroDAO.EncontrarUsuarioPorCorreo(correo);
                        
                        if (correoExistente.getCorreo() != null && !correoExistente.getCorreo().equals("")) {
                            mensaje = "El correo electrónico ya se encuentra registrado.";
                        } else {
                            
                            // ÉXITO: Si pasa todos los filtros de seguridad y negocio, se registra
                            registroVO = new RegistroVO(nombres, apellidos, tipoDocumento, documento, fechaNacimiento, correo, password);
                            mensaje = registroDAO.registrarUsuario(registroVO);
                        }
                    }
                }
            }
            
            // 4. Enviamos la respuesta de vuelta a la vista
            request.setAttribute("respuesta", mensaje);
            rd = request.getRequestDispatcher("usuario/registro.jsp");
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
    // </editor-fold>
}