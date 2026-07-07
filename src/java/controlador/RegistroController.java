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

public class RegistroController extends HttpServlet {

    RegistroDAO registroDAO;
    RegistroVO registroVO;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String mensaje = "";
        String accion = request.getParameter("accion");

        String vistaDestino = "usuario/registro.jsp";

        if (accion != null && accion.equals("registrar")) {

            String nombres = request.getParameter("nombres");
            String apellidos = request.getParameter("apellidos");
            String tipoDocumento = request.getParameter("tipoDocumento");
            String documento = request.getParameter("documento");
            String fechaNacimiento = request.getParameter("fechaNacimiento");
            String correo = request.getParameter("correo");
            String password = request.getParameter("password");
            String passwordConfirm = request.getParameter("passwordConfirm");

            if (password == null || passwordConfirm == null || !password.equals(passwordConfirm)) {
                mensaje = "La contraseña no coincide.";
            } else {

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

                    registroDAO = new RegistroDAO();

                    RegistroVO documentoExistente = registroDAO.EncontrarUsuario(documento);

                    if (documentoExistente.getNumIdentificacion() != null && !documentoExistente.getNumIdentificacion().equals("")) {
                        mensaje = "El número de documento ya se encuentra registrado.";
                    } else {

                        RegistroVO correoExistente = registroDAO.EncontrarUsuarioPorCorreo(correo);

                        if (correoExistente.getCorreo() != null && !correoExistente.getCorreo().equals("")) {
                            mensaje = "El correo electrónico ya se encuentra registrado.";
                        } else {

                            registroVO = new RegistroVO(nombres, apellidos, tipoDocumento, documento, fechaNacimiento, correo, password);
                            mensaje = registroDAO.registrarUsuario(registroVO);

                            if (mensaje.toLowerCase().contains("éxito") || mensaje.toLowerCase().contains("exito")) {

                            }
                        }
                    }
                }
            }

            request.setAttribute("respuesta", mensaje);
        }

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
