package Modelo.registro;

import Recursos.Conexion;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author jimma
 */
public class ProfesionalDAO {

    private static Connection con;

    // Constructor idéntico al de tu RegistroDAO para mantener la misma conexión
    public ProfesionalDAO() {
        Conexion conexion = new Conexion("localhost", "turnapp_db", "root", "Chocolate123*");
        con = conexion.getConexion();
    }

    /**
     * Registra un Profesional aplicando una Transacción Segura en las tablas usuario y profesional.
     * Retorna un String con el mensaje de éxito o error, tal como lo haces en tu RegistroDAO.
     */
    public String registrarProfesional(RegistroVO registroVO, ProfesionalVO profesionalVO) {
        String mensaje = "";
        PreparedStatement pstUsuario = null;
        PreparedStatement pstProfesional = null;
        ResultSet rsKeys = null;

        try {
            // 1. Desactivamos el autocommit para controlar la transacción manual
            con.setAutoCommit(false);

            // 2. Consulta 1: Insertar datos básicos en la tabla 'usuario' (Asegúrate de incluir id_rol si tu BD lo pide, o déjalo según tus columnas)
            // Usamos RETURN_GENERATED_KEYS para atrapar el ID generado automáticamente
            String sqlUsuario = "INSERT INTO usuario (nombre, apellido, tipo_documento, num_identificacion, fecha_nacimiento, email, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
            pstUsuario = con.prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS);

            pstUsuario.setString(1, registroVO.getNombre());
            pstUsuario.setString(2, registroVO.getApellido());
            pstUsuario.setString(3, registroVO.getTipoDocumento());
            pstUsuario.setString(4, registroVO.getNumIdentificacion());
            pstUsuario.setString(5, registroVO.getFechaNac());
            pstUsuario.setString(6, registroVO.getCorreo());
            pstUsuario.setString(7, registroVO.getPassword());

            int filasUsuario = pstUsuario.executeUpdate();

            // Si el usuario se guardó bien, procedemos con el profesional
            if (filasUsuario > 0) {
                // Recuperamos el ID autoincremental que generó MySQL para el usuario
                rsKeys = pstUsuario.getGeneratedKeys();
                
                if (rsKeys.next()) {
                    int idUsuarioGenerado = rsKeys.getInt(1);

                    // 3. Consulta 2: Insertar datos del oficio en la tabla 'profesional'
                    String sqlProfesional = "INSERT INTO profesional (id_usuario, id_establecimiento, especialidad_principal) VALUES (?, ?, ?)";
                    pstProfesional = con.prepareStatement(sqlProfesional);

                    pstProfesional.setInt(1, idUsuarioGenerado); // Le inyectamos el ID que acabamos de recuperar arriba
                    pstProfesional.setInt(2, profesionalVO.getIdEstablecimiento()); // Opción B: ID fijo/temporal
                    pstProfesional.setString(3, profesionalVO.getEspecialidadPrincipal());

                    int filasProfesional = pstProfesional.executeUpdate();

                    // Si ambas inserciones fueron exitosas, guardamos los cambios de verdad
                    if (filasProfesional > 0) {
                        con.commit(); // <-- Guarda todo en la BD de forma definitiva
                        mensaje = "Profesional registrado satisfactoriamente.";
                        System.out.println("Profesional registrado exitosamente en ambas tablas.");
                    } else {
                        con.rollback(); // Deshacer si falla el profesional
                        mensaje = "No se pudo completar el registro del área profesional.";
                    }
                } else {
                    con.rollback(); // Deshacer si no se generó el ID del usuario
                    mensaje = "Error al recuperar la identificación del usuario.";
                }
            } else {
                con.rollback();
                mensaje = "No se pudo registrar los datos de usuario.";
            }

        } catch (SQLException ex) {
            // Si algo falla en todo el bloque try, aplicamos rollback inmediato para que la BD quede limpia
            try {
                if (con != null) {
                    con.rollback();
                    System.out.println("Se ejecutó Rollback en la base de datos debido a un error.");
                }
            } catch (SQLException e) {
                Logger.getLogger(ProfesionalDAO.class.getName()).log(Level.SEVERE, "Error al hacer rollback: " + e.getMessage(), e);
            }

            Logger.getLogger(ProfesionalDAO.class.getName()).log(Level.SEVERE, "Error al registrar profesional: " + ex.getMessage(), ex);
            mensaje = "No se pudo registrar el profesional debido a un error interno.";

        } finally {
            // Cerramos de forma segura todos los flujos como lo haces tú
            try {
                if (rsKeys != null) rsKeys.close();
                if (pstUsuario != null) pstUsuario.close();
                if (pstProfesional != null) pstProfesional.close();
                if (con != null) {
                    con.setAutoCommit(true); // Devolvemos la conexión a su comportamiento por defecto
                }
            } catch (SQLException e) {
                Logger.getLogger(ProfesionalDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }

        return mensaje;
    }
}