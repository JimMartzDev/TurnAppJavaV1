/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.registro;
//importamos librerías

import Modelo.usuario.UsuarioVO;
import Recursos.Conexion;
import java.util.LinkedList;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jimma
 */
public class RegistroDAO {

    private static Connection con;

    public RegistroDAO() {
        Conexion conexion = new Conexion("localhost", "turnapp_db", "root", "Chocolate123*");
        con = conexion.getConexion();
        String mensaje = "";
    }

    public String registrarUsuario(RegistroVO registroVO) {
        // Inicializamos el mensaje de manera interna
        String mensaje = "";
        PreparedStatement pst = null;

        try {
            // CORRECCIÓN: Añadido el paréntesis de cierre ) al final de los VALUES
            String sql = "INSERT INTO usuario (nombre, apellido, tipo_documento, num_identificacion, fecha_nacimiento, email, password) VALUES (?, ?, ?, ?, ?, ?, ?)";

            pst = con.prepareStatement(sql);

            // Pasamos los datos del VO al PreparedStatement
            pst.setString(1, registroVO.getNombre());
            pst.setString(2, registroVO.getApellido());
            pst.setString(3, registroVO.getTipoDocumento());
            pst.setString(4, registroVO.getNumIdentificacion());
            pst.setString(5, registroVO.getFechaNac());
            pst.setString(6, registroVO.getCorreo());
            pst.setString(7, registroVO.getPassword());

            // Ejecutamos la inserción en la base de datos
            pst.executeUpdate();

            mensaje = "Usuario registrado satisfactoriamente.";
            System.out.println("Usuario registrado exitosamente en la base de datos.");

        } catch (SQLException ex) {
            // Registramos el error en la consola del servidor para poder debugar
            Logger.getLogger(RegistroDAO.class.getName()).log(Level.SEVERE, "Error al registrar usuario: " + ex.getMessage(), ex);
            mensaje = "No se pudo registrar el usuario debido a un error interno.";
        } finally {
            // Buena práctica: Cerramos el PreparedStatement para liberar memoria de la base de datos
            try {
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(RegistroDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return mensaje;
    }

    public RegistroVO EncontrarUsuario(String num_identificacion) {
        RegistroVO registroVO = new RegistroVO();
        String sql = "SELECT * FROM usuario WHERE num_identificacion=?";
        PreparedStatement pst;

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, num_identificacion);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                registroVO.setNumIdentificacion(rs.getString("num_identificacion"));
                registroVO.setNombre(rs.getString("nombre"));
                registroVO.setApellido(rs.getString("apellido"));
                registroVO.setTipoDocumento(rs.getString("tipo_documento"));
                registroVO.setFechaNac(rs.getString("fecha_nacimiento"));
                registroVO.setCorreo(rs.getString("email"));
                registroVO.setPassword(rs.getString("password"));

            }
        } catch (SQLException ex) {
            System.getLogger(RegistroDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

        return registroVO;
    }

    public RegistroVO EncontrarUsuarioPorCorreo(String correo) {
        RegistroVO registroVO = new RegistroVO();
        String sql = "SELECT * FROM usuario WHERE email = ?";
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, correo);
            rs = pst.executeQuery();

            if (rs.next()) {
                // Si lo encuentra, llenamos el VO para indicar que el correo ya existe
                registroVO.setCorreo(rs.getString("email"));
                registroVO.setNumIdentificacion(rs.getString("num_identificacion"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistroDAO.class.getName()).log(Level.SEVERE, "Error al buscar correo: " + ex.getMessage(), ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(RegistroDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return registroVO;
    }
    
    public boolean actualizarPassword(String correo, String nuevaPassword) {
    String sql = "UPDATE usuario SET password = ? WHERE email = ?";
    PreparedStatement pst = null;
    boolean actualizado = false;

    try {
        pst = con.prepareStatement(sql);
        pst.setString(1, nuevaPassword);
        pst.setString(2, correo);
        
        // executeUpdate devuelve la cantidad de filas afectadas en MySQL
        int filasAfectadas = pst.executeUpdate();
        if (filasAfectadas > 0) {
            actualizado = true;
            System.out.println("Contraseña actualizada con éxito en la BD para: " + correo);
        }
    } catch (SQLException ex) {
        Logger.getLogger(RegistroDAO.class.getName()).log(Level.SEVERE, "Error al actualizar contraseña: " + ex.getMessage(), ex);
    } finally {
        try {
            if (pst != null) pst.close();
        } catch (SQLException e) {
            Logger.getLogger(RegistroDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    return actualizado;
}
}
