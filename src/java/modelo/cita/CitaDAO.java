package modelo.cita;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Recursos.Conexion;

public class CitaDAO {

    // Cambiamos la clave local a una constante unificada para evitar confusiones de dedo
    private static final String DB_CLAVE = "Chocolate123*";

    public List<String> listarHorasOcupadas(int idProfesional, String fecha) {
        List<String> horasOcupadas = new ArrayList<>();

        // Mantener el filtro de tu base de datos actual para los turnos del negocio
        String sql = "SELECT TIME(fecha_hora) AS hora FROM cita "
                + "WHERE id_profesional = ? AND DATE(fecha_hora) = ? AND estado IN ('pendiente', 'confirmado')";

        Conexion objetoConexion = new Conexion("localhost", "turnapp_db", "root", DB_CLAVE);
        Connection conActiva = null;
        PreparedStatement pst = null;
        ResultSet resultado = null;

        try {
            conActiva = objetoConexion.getConexion();
            pst = conActiva.prepareStatement(sql);

            pst.setInt(1, idProfesional);
            pst.setString(2, fecha);
            resultado = pst.executeQuery();

            while (resultado.next()) {
                horasOcupadas.add(resultado.getString("hora"));
            }
        } catch (SQLException e) {
            System.err.println("Error en CitaDAO.listarHorasOcupadas: " + e.getMessage());
        } finally {
            // Cierre explícito de los recursos internos del método antes de cerrar la conexión general
            try {
                if (resultado != null) {
                    resultado.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar puentes en listarHorasOcupadas: " + ex.getMessage());
            }
            objetoConexion.cerrarConexion();
        }
        return horasOcupadas;
    }

    public boolean registrarCita(CitaVO cita) {
        boolean insercionExitosa = false;
        String sql = "INSERT INTO cita (id_cliente, fecha_hora, estado, notas_cliente, id_profesional, id_servicio) VALUES (?, ?, ?, ?, ?, ?)";

        Conexion objetoConexion = new Conexion("localhost", "turnapp_db", "root", DB_CLAVE);
        Connection conActiva = null;
        PreparedStatement pst = null;

        try {
            conActiva = objetoConexion.getConexion();
            pst = conActiva.prepareStatement(sql);

            pst.setInt(1, cita.getIdCliente());
            pst.setString(2, cita.getFechaHoraCombinada());
            pst.setString(3, cita.getEstado());
            pst.setString(4, cita.getNotasCliente());
            pst.setInt(5, cita.getIdProfesional());
            pst.setInt(6, cita.getIdServicio());

            int filasAfectadas = pst.executeUpdate();
            if (filasAfectadas > 0) {
                insercionExitosa = true;
            }
        } catch (SQLException e) {
            System.err.println("Error en CitaDAO.registrarCita: " + e.getMessage());
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar puentes en registrarCita: " + ex.getMessage());
            }
            objetoConexion.cerrarConexion();
        }
        return insercionExitosa;
    }
}
