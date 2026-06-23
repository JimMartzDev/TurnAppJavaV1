/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.usuario;

/**
 *
 * @author jimma
 */
public class UsuarioVO {
    private String usuario;
    private String password;

    
    // Definir constructor
    public UsuarioVO(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }
    
    public UsuarioVO() {
        this.usuario = "";
        this.password = "";
    }
    
    //Definir constructores getters and setters

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
