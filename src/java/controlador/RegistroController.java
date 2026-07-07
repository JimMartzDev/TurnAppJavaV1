package controlador;

import modelo.registro.RegistroDAO;
import modelo.registro.RegistroVO;
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
        String accion = request.getParameter("accion");
        
        // Definimos la vista por defecto a la que siempre responderá este controlador
        String vistaDestino = "usuario/registro.jsp";
        
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
                
                // 3. FILTRO 2: Validar mayoría de edad
                boolean esMayorDeEdad = false;
                try {
                    if (fechaNacimiento != null && !fechaNacimiento.equals("")) {
                        LocalDate fechaNac = LocalDate.parse(fechaNacimiento);
                        LocalDate fechaActual = LocalDate.now();
                        
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
                    
                    // Conexión a la Base de Datos
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
                            
                            // ÉXITO: Registro Exitoso
                            registroVO = new RegistroVO(nombres, apellidos, tipoDocumento, documento, fechaNacimiento, correo, password);
                            mensaje = registroDAO.registrarUsuario(registroVO);
                            
                            // [OPCIONAL] Si el mensaje del DAO contiene "éxito", podrías redirigir a un login
                            if (mensaje.toLowerCase().contains("éxito") || mensaje.toLowerCase().contains("exito")) {
                                // Vista alternativa en caso de éxito total (descomentar si tienes un login.jsp)
                                // vistaDestino = "login.jsp"; 
                            }
                        }
                    }
                }
            }
            
            // Enviamos el mensaje de feedback (Tu scriptlet JSP lo leerá aquí)
            request.setAttribute("respuesta", mensaje);
        }
        
        // 4. Despacho seguro: Siempre redirige a la vista correspondiente, previniendo pantallas en blanco
        RequestDispatcher rd = request.getRequestDispatcher(vistaDestino);
        rd.forward(request, response);
    }

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
        return "Controlador de Registro de Usuarios";
    }
}