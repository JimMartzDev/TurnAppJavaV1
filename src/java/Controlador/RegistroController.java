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
        
        // Verificamos que la acción sea "registrar"
        if (accion != null && accion.equals("registrar")) {
            
            // Sincronizados con los atributos 'name' del registro.jsp
            String nombres = request.getParameter("nombres");
            String apellidos = request.getParameter("apellidos");
            String tipoDocumento = request.getParameter("tipoDocumento");
            String documento = request.getParameter("documento");
            String fechaNacimiento = request.getParameter("fechaNacimiento");
            String correo = request.getParameter("correo");
            String password = request.getParameter("password");
            
            // NUEVO: Empaquetamos los datos en el VO usando el constructor lleno
            registroVO = new RegistroVO(nombres, apellidos, tipoDocumento, documento, fechaNacimiento, correo, password);
            
            // NUEVO: Instanciamos el DAO y enviamos el objeto a la base de datos
            registroDAO = new RegistroDAO();
            mensaje = registroDAO.registrarUsuario(registroVO);
            
            // NUEVO: Enviamos la respuesta de vuelta a la vista
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