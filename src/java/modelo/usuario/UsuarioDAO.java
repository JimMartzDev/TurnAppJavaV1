/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.usuario;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import Recursos.Conexion;

public class UsuarioDAO {

    private static Connection con;

    public UsuarioDAO() {
        Conexion conexion = new Conexion("localhost", "turnapp_db", "root", "Chocolate123*");
        con = conexion.getConexion();
    }

    public boolean validarUsuario(String password, String usuario) {
        boolean encontro = false;
        String sql = "SELECT * FROM usuario WHERE password = ?  and num_identificacion = ?";
        PreparedStatement ps;

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, password);
            ps.setString(2, usuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                encontro = true;
            }

        } catch (SQLException ex) {
            System.getLogger(UsuarioDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            System.out.println(ex.getMessage());

        }

        return encontro;

    }
}
