package Controlador;

import Modelo.registro.RegistroDAO;
import Modelo.registro.RegistroVO;
import java.io.IOException;
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

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mensaje = "";
        RequestDispatcher rd = null;
        
        // Capturamos la acción del formulario
        String accion = request.getParameter("accion");
        
        // OPTIMIZACIÓN: Verificamos que la acción sea "registrar"
        if (accion != null && accion.equals("registrar")) {
            
            // CORRECCIÓN: Sincronizamos los nombres con los atributos 'name' del registro.jsp
            String nombres = request.getParameter("nombres");
            String apellidos = request.getParameter("apellidos");
            String tipoDocumento = request.getParameter("tipoDocumento");
            String documento = request.getParameter("documento");
            String fechaNacimiento = request.getParameter("fechaNacimiento");
            String correo = request.getParameter("correo");
            String password = request.getParameter("password");
            
            // El siguiente paso será empaquetar estos datos en el VO y llamar al DAO...
        }
    }