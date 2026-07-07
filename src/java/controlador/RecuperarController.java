package controlador;

import modelo.registro.RegistroDAO;
import modelo.registro.RegistroVO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

/**
 * @author jimma
 */
@WebServlet(name = "RecuperarController", urlPatterns = {"/RecuperarController"}) // <-- 2. AGREGAR ESTA ANOTACIÓN AQUÍ
public class RecuperarController extends HttpServlet {

    private RegistroDAO registroDAO;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mensaje = "";
        String accion = request.getParameter("accion");
        RequestDispatcher rd = null;

        registroDAO = new RegistroDAO();

        if (accion != null) {

            // FASE 1: Verificar si el correo existe en la base de datos
            if (accion.equals("verificarCorreo")) {
                String correo = request.getParameter("txtCorreo");

                // Usamos el método que ya tenías para buscar por correo
                RegistroVO usuario = registroDAO.EncontrarUsuarioPorCorreo(correo);

                if (usuario.getCorreo() != null && !usuario.getCorreo().equals("")) {
                    // Si el correo existe, guardamos el correo en el request y activamos el paso 2
                    request.setAttribute("correoUsuario", correo);
                    request.setAttribute("paso", "2");
                } else {
                    mensaje = "El correo electrónico no se encuentra registrado.";
                    request.setAttribute("mensaje", mensaje);
                }

                rd = request.getRequestDispatcher("recuperar.jsp");
                rd.forward(request, response);
            } // FASE 2: Validar contraseñas nuevas y actualizar en MySQL
            else if (accion.equals("cambiarPassword")) {
                String correo = request.getParameter("txtCorreoConfirmado");
                String nuevaPas = request.getParameter("txtNuevaPas");
                String confirmarPas = request.getParameter("txtConfirmarPas");

                // Validamos en memoria que coincidan las dos contraseñas
                if (nuevaPas == null || confirmarPas == null || !nuevaPas.equals(confirmarPas)) {
                    request.setAttribute("mensaje", "La contraseña no coincide.");
                    request.setAttribute("correoUsuario", correo);
                    request.setAttribute("paso", "2"); // Lo devolvemos al formulario de contraseñas
                } else {
                    // Intentamos actualizar la contraseña en la Base de Datos
                    boolean exito = registroDAO.actualizarPassword(correo, nuevaPas);

                    if (exito) {
                        // Si todo sale bien, lo mandamos al login con mensaje de éxito
                        request.setAttribute("mensaje", "Contraseña restablecida con éxito. Ya puedes ingresar.");
                        rd = request.getRequestDispatcher("login.jsp");
                        rd.forward(request, response);
                        return;
                    } else {
                        request.setAttribute("mensaje", "Hubo un error al actualizar. Intenta de nuevo.");
                        request.setAttribute("correoUsuario", correo);
                        request.setAttribute("paso", "2");
                    }
                }

                rd = request.getRequestDispatcher("recuperar.jsp");
                rd.forward(request, response);
            }
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
        return "Controlador para la recuperación de contraseñas";
    }
    // </editor-fold>
}
