/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.cita;
import Recursos.Conexion;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jimma
 */
public class CitaDAO {

    private static Connection con;

    // Constructor idéntico a tus DAOs para mantener la misma consistencia de conexión
    public CitaDAO() {
        Conexion conexion = new Conexion("localhost", "turnapp_db", "root", "Chocolate123*");
        con = conexion.getConexion();
    }

    /**
     * Busca la clave primaria (id_profesional_servicio) de la tabla intermedia
     * cruzando el ID del Profesional y el ID del Servicio seleccionados en la interfaz.
     */
    public int obtenerIdProfesionalServicio(int idProfesional, int idServicio) {
        int idResultado = 0;
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        String sql = "SELECT id_profesional_servicio FROM profesional_servicio WHERE id_profesional = ? AND id_servicio = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idProfesional);
            pst.setInt(2, idServicio);
            
            rs = pst.executeQuery();
            
            if (rs.next()) {
                idResultado = rs.getInt("id_profesional_servicio");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CitaDAO.class.getName()).log(Level.SEVERE, "Error al obtener id_profesional_servicio: " + ex.getMessage(), ex);
        } finally {
            // Cierre seguro de flujos locales
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
            } catch (SQLException e) {
                Logger.getLogger(CitaDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return idResultado;
    }

    /**
     * Inserta un nuevo registro de turno en la tabla 'cita'.
     * Retorna un String con el mensaje de éxito o error siguiendo tu patrón.
     */
    public String registrarCita(CitaVO citaVO) {
        String mensaje = "";
        PreparedStatement pstCita = null;

        // Sentencia SQL alineada con las columnas de tu diagrama de base de datos
        String sqlCita = "INSERT INTO cita (id_cliente, fecha_hora, estado, id_profesional_servicio, notas_cliente) VALUES (?, ?, ?, ?, ?)";

        try {
            pstCita = con.prepareStatement(sqlCita);

            // Asignación de parámetros capturados desde el VO
            pstCita.setInt(1, citaVO.getIdCliente());
            
            // COHERENCIA DATETIME: Convertimos el String combinado (yyyy-MM-dd HH:mm:ss) a Timestamp de SQL
            pstCita.setTimestamp(2, java.sql.Timestamp.valueOf(citaVO.getFechaHora()));
            
            pstCita.setString(3, citaVO.getEstado()); // 'Pendiente' por defecto enviado desde el controlador
            pstCita.setInt(4, citaVO.getIdProfesionalServicio());
            pstCita.setString(5, citaVO.getNotasCliente());

            int filasAfectadas = pstCita.executeUpdate();

            if (filasAfectadas > 0) {
                mensaje = "Cita agendada satisfactoriamente.";
                System.out.println("Cita registrada exitosamente en la tabla 'cita'.");
            } else {
                mensaje = "No se pudo completar el agendamiento del turno.";
            }

        } catch (SQLException ex) {
            Logger.getLogger(CitaDAO.class.getName()).log(Level.SEVERE, "Error al registrar cita: " + ex.getMessage(), ex);
            mensaje = "No se pudo registrar la cita debido a un error interno.";
            
        } catch (IllegalArgumentException ex) {
            // Captura errores si el formato de fecha/hora recibido no es un DATETIME válido
            Logger.getLogger(CitaDAO.class.getName()).log(Level.SEVERE, "Error en formato de fecha y hora: " + ex.getMessage(), ex);
            mensaje = "Error interno: El formato de la fecha u hora no es válido.";
            
        } finally {
            // Cierre seguro de flujos como lo haces tú
            try {
                if (pstCita != null) {
                    pstCita.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(CitaDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }

        return mensaje;
    }
}