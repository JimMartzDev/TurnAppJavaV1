/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Recursos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private String ip;
    private String bd;
    private String usuario;
    private String clave;
    private String url;
    public Connection con;

    public Conexion(String ip, String bd, String usuario, String clave) {

        this.ip = ip;
        this.bd = bd;
        this.usuario = usuario;
        this.clave = clave;
        url = "jdbc:mysql://" + ip + ":3306/" + bd;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.getLogger(Conexion.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            System.out.println("Erro al registrar el Driver JDBC");
        }

        try {

            con = DriverManager.getConnection(url, usuario, clave);
        } catch (SQLException ex) {
            System.getLogger(Conexion.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            System.out.println("Error de conexión a la base de datos");
        }
    }

    public Connection getConexion() {
        return con;
    }

    public Connection cerrarConexion() {
        try {
            con.close();
        } catch (SQLException ex) {
            System.getLogger(Conexion.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        con = null;
        return con;
    }
}
