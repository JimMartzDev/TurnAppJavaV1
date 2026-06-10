/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.registro;

/**
 *
 * @author jimma
 */
public class RegistroVO {
    
    //Atributos
    private String nombre;
    private String apellido;
    private String tipoDocumento;
    private String numIdentificacion;
    private String fechaNac;
    private String correo;
    private String password;
    
    
    // Constructor

    public RegistroVO(String nombre, String apellido, String tipoDocumento, String numIdentificacion, String fechaNac, String correo, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDocumento = tipoDocumento;
        this.numIdentificacion = numIdentificacion;
        this.fechaNac = fechaNac;
        this.correo = correo;
        this.password = password;
    }
    
    
    public RegistroVO() {
        this.nombre = "";
        this.apellido = "";
        this.tipoDocumento = "";
        this.numIdentificacion = "";
        this.fechaNac = "";
        this.correo = "";
        this.password = "";
    }
     
    
    // getters and setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumIdentificacion() {
        return numIdentificacion;
    }

    public void setNumIdentificacion(String numIdentificacion) {
        this.numIdentificacion = numIdentificacion;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
            
}
