/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.login;

//importar librerias.
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import Recursos.Conexion;


/**
 *
 * @author jimma
 */
public class LoginDAO {
    // Definir los atributos
    private static Connection con;
    
    // Definimos el constructor
    public LoginDAO(){
    Conexion conexion = new Conexion("localhost", "turnapp_db", "root", "123456");
    con = conexion.getConexion();
}
    
    //Método para Consultar si el usuario y el password son correctos

    public boolean validarUsuario(String usuario, String password){
    boolean encontro = false;
    String sql = "SELECT * FROM login WHERE password = ?  and num_identificacion = ?";
    PreparedStatement ps;
    
    
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
            encontro = true;
            }
            
        } catch (SQLException ex) {
            System.getLogger(LoginDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            System.out.println(ex.getMessage());

        }

        return encontro;
    
    }
}
