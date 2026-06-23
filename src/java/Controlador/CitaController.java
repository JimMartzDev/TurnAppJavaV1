package controlador;

import modelo.registro.ProfesionalDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// REPARADO: Eliminado el import de ServicioDAO que no existe
import modelo.cita.CitaDAO;
import modelo.cita.CitaVO;

@WebServlet(name = "CitaController", urlPatterns = {"/CitaController"})
public class CitaController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String accion = request.getParameter("accion");
        if (accion == null) {
            accion = "cargarPantalla";
        }

        switch (accion) {
            case "cargarPantalla":
                cargarDatosAgendamiento(request, response);
                break;
            case "registrar":
                ejecutarRegistro(request, response);
                break;
            case "consultarDisponibilidad":
                procesarDisponibilidadAjax(request, response);
                break;
            default:
                response.sendRedirect("agendar.jsp");
                break;
        }
    }

    private void cargarDatosAgendamiento(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Únicamente traemos la lista de profesionales
        String sql = "SELECT p.*, s.nombre_servicio FROM profesional p JOIN ..."; // Referencia mental de tu DB
        ProfesionalDAO profesionalDao = new ProfesionalDAO();
        request.setAttribute("listaProfesionales", profesionalDao.listarProfesionales());

        request.getRequestDispatcher("agendar.jsp").forward(request, response);
    }

    private void procesarDisponibilidadAjax(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Definición estricta de cabeceras HTTP para transferencia de datos estructurados
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Bloque defensivo para evitar excepciones de parseo si los parámetros llegan nulos
        String strIdProfesional = request.getParameter("idProfesional");
        String fechaCita = request.getParameter("fechaCita");

        List<String> horasOcupadas = new java.util.ArrayList<>();

        if (strIdProfesional != null && fechaCita != null && !fechaCita.isEmpty()) {
            try {
                int idProfesional = Integer.parseInt(strIdProfesional);
                CitaDAO citaDao = new CitaDAO();
                horasOcupadas = citaDao.listarHorasOcupadas(idProfesional, fechaCita);
            } catch (NumberFormatException e) {
                System.err.println("Error de conversión de datos numéricos en el controlador: " + e.getMessage());
            }
        }

        if (horasOcupadas == null) {
            horasOcupadas = new java.util.ArrayList<>();
        }

        // Serialización manual eficiente a JSON utilizando StringBuilder
        try (PrintWriter out = response.getWriter()) {
            StringBuilder json = new StringBuilder("[");
            for (int i = 0; i < horasOcupadas.size(); i++) {
                json.append("\"").append(horasOcupadas.get(i)).append("\"");
                if (i < horasOcupadas.size() - 1) {
                    json.append(",");
                }
            }
            json.append("]");

            out.print(json.toString());
            out.flush(); // Fuerza la salida del flujo antes del cierre automático del bloque try-with-resources
        }
    }

    private void ejecutarRegistro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sesion = request.getSession();
        Integer idCliente = (Integer) sesion.getAttribute("idUsuarioLogueado");

        if (idCliente == null) {
            idCliente = 1; // Respaldo para pruebas locales
        }

        int idProfesional = Integer.parseInt(request.getParameter("idProfesional"));
        int idServicio = Integer.parseInt(request.getParameter("idServicio")); // Recibe el parámetro oculto
        String fechaCita = request.getParameter("fechaCita");
        String horaCita = request.getParameter("horaCita");
        String notasCliente = request.getParameter("notasCliente");

        CitaVO cita = new CitaVO(idCliente, idProfesional, idServicio, fechaCita, horaCita, notasCliente);
        CitaDAO citaDao = new CitaDAO();

        boolean operacionExitosa = citaDao.registrarCita(cita);

        if (operacionExitosa) {
            request.setAttribute("mensajeExito", "¡Excelente! Tu turno ha sido agendado exitosamente.");
        } else {
            request.setAttribute("mensajeError", "No se pudo completar el agendamiento. Por favor, verifica e intenta de nuevo.");
        }

        // Volvemos a cargar únicamente los profesionales para el formulario de vuelta
        ProfesionalDAO profesionalDao = new ProfesionalDAO();
        request.setAttribute("listaProfesionales", profesionalDao.listarProfesionales());

        request.getRequestDispatcher("agendar.jsp").forward(request, response);
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
}
