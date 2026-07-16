/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UsuarioVO {

    private int idUsuario;
    private String nombre;
    private String apellido;
    private String tipoDocumento;
    private String usuario; // Se usa String por seguridad con números muy largos o caracteres
    private LocalDate fechaNacimiento; // O puedes usar java.util.Date / String si lo prefieres
    private String email;
    private String password;
    private int rolId;
    private LocalDateTime fechaRegistro;

    public UsuarioVO(int idUsuario, String nombre, String apellido, String tipoDocumento,
            String numIdentificacion, LocalDate fechaNacimiento, String email,
            String password, int rolId, LocalDateTime fechaRegistro) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDocumento = tipoDocumento;
        this.usuario = numIdentificacion;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
        this.password = password;
        this.rolId = rolId;
        this.fechaRegistro = fechaRegistro;
    }

    public UsuarioVO(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    public UsuarioVO() {
        this.usuario = "";
        this.password = "";
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRolId() {
        return rolId;
    }

    public void setRolId(int rolId) {
        this.rolId = rolId;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

   

}
