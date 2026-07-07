package controlador;

import modelo.registro.ProfesionalDAO;
import modelo.registro.RegistroVO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ProfesionalController", urlPatterns = {"/ProfesionalController"})
public class ProfesionalController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String accion = request.getParameter("accion");

        ProfesionalDAO profesionalDAO = new ProfesionalDAO();

        if (accion != null && accion.equals("registrar")) {
            try {

                String nombres = request.getParameter("nombres");
                String apellidos = request.getParameter("apellidos");
                String tipoDocumento = request.getParameter("tipoDocumento");
                String documento = request.getParameter("documento");
                String fechaNacimiento = request.getParameter("fechaNacimiento");
                String correo = request.getParameter("correo");
                String password = request.getParameter("password");
                String especialidadPrincipal = request.getParameter("especialidadPrincipal");

                RegistroVO registroVO = new RegistroVO();
                registroVO.setNombre(nombres);
                registroVO.setApellido(apellidos);
                registroVO.setTipoDocumento(tipoDocumento);
                registroVO.setNumIdentificacion(documento);
                registroVO.setFechaNac(fechaNacimiento);
                registroVO.setCorreo(correo);
                registroVO.setPassword(password);

                String resultadoBusqueda = profesionalDAO.registrarProfesional(registroVO, especialidadPrincipal);

                request.setAttribute("respuesta", resultadoBusqueda);
                request.getRequestDispatcher("usuario/registroProfesional.jsp").forward(request, response);

            } catch (Exception e) {
                System.out.println("Error en ProfesionalController: " + e.getMessage());
                request.setAttribute("respuesta", "Ocurrió un error inesperado al procesar el registro.");
                request.getRequestDispatcher("usuario/registroProfesional.jsp").forward(request, response);
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
        return "Controlador para el registro de profesionales en TurnApp";
    }
}
