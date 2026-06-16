/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;


import Modelo.cita.CitaDAO;
import Modelo.cita.CitaVO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jimma
 */
@WebServlet("/CitaController")
public class CitaController extends HttpServlet {

    /**
     * Procesa las peticiones HTTP POST que envía el formulario de agendar.jsp
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // 1. Validar o recuperar la sesión del cliente logueado
        HttpSession session = request.getSession();
        Integer idCliente = (Integer) session.getAttribute("id_cliente");
        
        // Comodín temporal para pruebas en desarrollo si no hay sesión activa:
        if (idCliente == null) {
            idCliente = 1; 
        }

        // 2. Recuperar los parámetros enviados por el formulario usando los names del JSP
        String idProfesionalStr = request.getParameter("idProfesional");
        String idServicioStr = request.getParameter("idServicio");
        String fechaCitaStr = request.getParameter("fechaCita");   // Captura "yyyy-MM-dd"
        String horaCitaStr = request.getParameter("horaCita");     // Captura "HH:mm:ss"
        String notasCliente = request.getParameter("notasCliente");

        String mensajeResultado = "";

        try {
            // Validar que los campos obligatorios no vengan vacíos
            if (idProfesionalStr != null && idServicioStr != null && fechaCitaStr != null && horaCitaStr != null) {
                
                int idProfesional = Integer.parseInt(idProfesionalStr);
                int idServicio = Integer.parseInt(idServicioStr);

                // COHERENCIA: Combinar los Strings de fecha y hora para cumplir con el tipo DATETIME
                String fechaHoraCombinada = fechaCitaStr + " " + horaCitaStr; // Formato: "yyyy-MM-dd HH:mm:ss"

                CitaDAO citaDAO = new CitaDAO();
                
                // 3. Cruzar datos en el DAO para obtener la FK de la tabla intermedia
                int idProfServ = citaDAO.obtenerIdProfesionalServicio(idProfesional, idServicio);
                

                if (idProfServ > 0) {
                    // 4. Instanciar el Objeto de Valor (CitaVO) con el estado inicial "Pendiente"
                    CitaVO nuevaCita = new CitaVO();
                    nuevaCita.setIdCliente(idCliente);
                    nuevaCita.setFechaHora(fechaHoraCombinada);
                    nuevaCita.setEstado("Pendiente"); // Valor del ENUM de tu BD
                    nuevaCita.setIdProfesionalServicio(idProfServ);
                    nuevaCita.setNotasCliente(notasCliente);

                    // 5. Enviar el VO al DAO para ejecutar la inserción
                    mensajeResultado = citaDAO.registrarCita(nuevaCita);
                    
                    // Si el mensaje del DAO es el de éxito, redirigimos positivamente
                    if (mensajeResultado.contains("satisfactoriamente")) {
                        response.sendRedirect("misCitas.jsp?mensaje=" + mensajeResultado);
                        return; // Detiene la ejecución para evitar doble redirección
                    }
                    
                } else {
                    mensajeResultado = "Error: El profesional seleccionado no ofrece ese servicio.";
                }
            } else {
                mensajeResultado = "Error: Faltan datos obligatorios para procesar el turno.";
            }

        } catch (NumberFormatException ex) {
            Logger.getLogger(CitaController.class.getName()).log(Level.SEVERE, "Error de conversión numérica: " + ex.getMessage(), ex);
            mensajeResultado = "Los identificadores de profesional o servicio son inválidos.";
        } catch (Exception ex) {
            Logger.getLogger(CitaController.class.getName()).log(Level.SEVERE, "Error inesperado en el controlador: " + ex.getMessage(), ex);
            mensajeResultado = "Ocurrió un error inesperado al procesar la solicitud.";
        }

        // Si el flujo falla o no se concreta la inserción, regresa al formulario mostrando el error
        response.sendRedirect("agendar.jsp?error=" + mensajeResultado);
    }

    /**
     * Opcional: Redirige al formulario si intentan acceder al Servlet escribiendo la URL directamente (GET)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("agendar.jsp");
    }
}
