package Controlador;

import Modelo.registro.ProfesionalDAO;
import Modelo.registro.ProfesionalVO;
import Modelo.registro.RegistroVO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jimma
 */
@WebServlet(name = "ProfesionalController", urlPatterns = {"/ProfesionalController"})
public class ProfesionalController extends HttpServlet {

    /**
     * Procesa las peticiones tanto para los métodos HTTP GET como POST.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        // Capturamos la acción enviada por el formulario oculto o el botón
        String accion = request.getParameter("accion");
        
        // Instanciamos el DAO encargado de la persistencia
        ProfesionalDAO profesionalDAO = new ProfesionalDAO();

        if (accion != null && accion.equals("registrar")) {
            try {
                // 1. CAPTURAR LOS DATOS DEL FORMULARIO (Se usan los 'name' del JSP)
                String nombres = request.getParameter("nombres");
                String apellidos = request.getParameter("apellidos");
                String tipoDocumento = request.getParameter("tipoDocumento");
                String documento = request.getParameter("documento");
                String fechaNacimiento = request.getParameter("fechaNacimiento");
                String correo = request.getParameter("correo");
                String password = request.getParameter("password");
                String especialidadPrincipal = request.getParameter("especialidadPrincipal");

                // 2. LLENAR EL OBJETO REGISTRO VO (Datos personales para la tabla usuario)
                RegistroVO registroVO = new RegistroVO();
                registroVO.setNombre(nombres);
                registroVO.setApellido(apellidos);
                registroVO.setTipoDocumento(tipoDocumento);
                registroVO.setNumIdentificacion(documento);
                registroVO.setFechaNac(fechaNacimiento);
                registroVO.setCorreo(correo);
                registroVO.setPassword(password);

                // 3. LLENAR EL OBJETO PROFESIONAL VO (Datos del oficio para su tabla)
                // Usamos el constructor de la Opción B: el ID de usuario se asignará en el DAO,
                // y pasamos el ID de establecimiento 1 por defecto (temporal), junto a la especialidad.
                int idEstablecimientoDefecto = 1; 
                ProfesionalVO profesionalVO = new ProfesionalVO(0, idEstablecimientoDefecto, especialidadPrincipal);

                // 4. EJECUTAR LA TRANSACCIÓN MEDIANTE EL DAO
                String resultadoBusqueda = profesionalDAO.registrarProfesional(registroVO, profesionalVO);

                // 5. RETORNAR LA RESPUESTA A LA VISTA
                // Guardamos el mensaje devuelto ("Profesional registrado satisfactoriamente" o el error)
                request.setAttribute("respuesta", resultadoBusqueda);
                
                // Redireccionamos de vuelta al formulario para que el usuario vea el aviso en pantalla
                request.getRequestDispatcher("usuario/registroProfesional.jsp").forward(request, response);

            } catch (Exception e) {
                System.out.println("Error en ProfesionalController: " + e.getMessage());
                request.setAttribute("respuesta", "Ocurrió un error inesperado al procesar el registro.");
                request.getRequestDispatcher("usuario/registroProfesional.jsp").forward(request, response);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Métodos HttpServlet. Click en el signo + para mostrar código.">
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
    // </editor-fold>
}