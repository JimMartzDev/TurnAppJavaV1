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


@WebServlet(name = "RecuperarController", urlPatterns = {"/RecuperarController"}) 
public class RecuperarController extends HttpServlet {

    private RegistroDAO registroDAO;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mensaje = "";
        String accion = request.getParameter("accion");
        RequestDispatcher rd = null;

        registroDAO = new RegistroDAO();

        if (accion != null) {

          
            if (accion.equals("verificarCorreo")) {
                String correo = request.getParameter("txtCorreo");

              
                RegistroVO usuario = registroDAO.EncontrarUsuarioPorCorreo(correo);

                if (usuario.getCorreo() != null && !usuario.getCorreo().equals("")) {
                    
                    request.setAttribute("correoUsuario", correo);
                    request.setAttribute("paso", "2");
                } else {
                    mensaje = "El correo electrónico no se encuentra registrado.";
                    request.setAttribute("mensaje", mensaje);
                }

                rd = request.getRequestDispatcher("recuperar.jsp");
                rd.forward(request, response);
            } 
            else if (accion.equals("cambiarPassword")) {
                String correo = request.getParameter("txtCorreoConfirmado");
                String nuevaPas = request.getParameter("txtNuevaPas");
                String confirmarPas = request.getParameter("txtConfirmarPas");

               
                if (nuevaPas == null || confirmarPas == null || !nuevaPas.equals(confirmarPas)) {
                    request.setAttribute("mensaje", "La contraseña no coincide.");
                    request.setAttribute("correoUsuario", correo);
                    request.setAttribute("paso", "2"); // 
                } else {
                
                    boolean exito = registroDAO.actualizarPassword(correo, nuevaPas);

                    if (exito) {
                        
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
