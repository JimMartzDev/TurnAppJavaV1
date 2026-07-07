package modelo.registro;

import modelo.registro.ProfesionalVO;
import modelo.registro.RegistroVO;
import Recursos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProfesionalDAO {

    private static Connection con;

    public ProfesionalDAO() {

        Conexion conexion = new Conexion("localhost", "turnapp_db", "root", "Chocolate123*");
        con = conexion.getConexion();
    }

    public List<ProfesionalVO> listarProfesionales() {
        List<ProfesionalVO> lista = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;

        String sql = "SELECT p.id_profesional, u.nombre, u.apellido, p.especialidad_principal "
                + "FROM profesional p "
                + "INNER JOIN usuario u ON p.id_usuario = u.id_usuario";

        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                ProfesionalVO prof = new ProfesionalVO();
                prof.setIdProfesional(rs.getInt("id_profesional"));
                prof.setNombre(rs.getString("nombre"));
                prof.setApellido(rs.getString("apellido"));
                prof.setEspecialidadPrincipal(rs.getString("especialidad_principal"));

                lista.add(prof);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProfesionalDAO.class.getName()).log(Level.SEVERE, "Error al listar profesionales: " + ex.getMessage(), ex);
        } finally {
            cerrarRecursos(pst, rs);
        }
        return lista;
    }

    public String registrarProfesional(RegistroVO regVO, String especialidad) {
        String mensaje = "";
        PreparedStatement pstUsuario = null;
        PreparedStatement pstProfesional = null;
        ResultSet rs = null;

        String sqlUsuario = "INSERT INTO usuario (nombre, apellido, tipo_documento, num_identificacion, fecha_nacimiento, email, password, rol_id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, 2)";

        String sqlProfesional = "INSERT INTO profesional (id_usuario, especialidad_principal) VALUES (?, ?)";

        try {

            con.setAutoCommit(false);

            pstUsuario = con.prepareStatement(sqlUsuario, PreparedStatement.RETURN_GENERATED_KEYS);
            pstUsuario.setString(1, regVO.getNombre());
            pstUsuario.setString(2, regVO.getApellido());
            pstUsuario.setString(3, regVO.getTipoDocumento());
            pstUsuario.setString(4, regVO.getNumIdentificacion());
            pstUsuario.setString(5, regVO.getFechaNac());
            pstUsuario.setString(6, regVO.getCorreo());
            pstUsuario.setString(7, regVO.getPassword());

            pstUsuario.executeUpdate();

            rs = pstUsuario.getGeneratedKeys();
            int idUsuarioGenerado = 0;
            if (rs.next()) {
                idUsuarioGenerado = rs.getInt(1);
            }

            if (idUsuarioGenerado > 0) {
                pstProfesional = con.prepareStatement(sqlProfesional);
                pstProfesional.setInt(1, idUsuarioGenerado);
                pstProfesional.setString(2, especialidad);
                pstProfesional.executeUpdate();

                con.commit();
                mensaje = "El profesional ha sido registrado con éxito.";
            } else {
                con.rollback();
                mensaje = "No se pudo generar el identificador del usuario.";
            }

        } catch (SQLException ex) {
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException rollbackEx) {
                Logger.getLogger(ProfesionalDAO.class.getName()).log(Level.SEVERE, null, rollbackEx);
            }
            Logger.getLogger(ProfesionalDAO.class.getName()).log(Level.SEVERE, "Error en transaccion de registro: " + ex.getMessage(), ex);
            mensaje = "Error interno: El número de documento o correo ya podrían estar registrados.";
        } finally {
            try {
                con.setAutoCommit(true); 
            } catch (SQLException e) {
                Logger.getLogger(ProfesionalDAO.class.getName()).log(Level.SEVERE, null, e);
            }
            cerrarRecursos(pstUsuario, rs);
            cerrarRecursos(pstProfesional, null);
        }
        return mensaje;
    }

    private void cerrarRecursos(PreparedStatement pst, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
        } catch (SQLException e) {
            Logger.getLogger(ProfesionalDAO.class.getName()).log(Level.SEVERE, "Error al cerrar recursos", e);
        }
    }
}
