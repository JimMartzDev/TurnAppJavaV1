package Modelo.registro;

/**
 * @author jimma
 */
public class ProfesionalVO {
    private int idProfesional;
    private int idUsuario;
    private int idEstablecimiento;
    private String especialidadPrincipal;

    // Constructor vacío
    public ProfesionalVO() {}

    // Constructor para inserción (Opción B)
    public ProfesionalVO(int idUsuario, int idEstablecimiento, String especialidadPrincipal) {
        this.idUsuario = idUsuario;
        this.idEstablecimiento = idEstablecimiento;
        this.especialidadPrincipal = especialidadPrincipal;
    }

    // Getters y Setters
    public int getIdProfesional() {
        return idProfesional;
    }

    public void setIdProfesional(int idProfesional) {
        this.idProfesional = idProfesional;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdEstablecimiento() {
        return idEstablecimiento;
    }

    public void setIdEstablecimiento(int idEstablecimiento) {
        this.idEstablecimiento = idEstablecimiento;
    }

    public String getEspecialidadPrincipal() {
        return especialidadPrincipal;
    }

    public void setEspecialidadPrincipal(String especialidadPrincipal) {
        this.especialidadPrincipal = especialidadPrincipal;
    }
} // <-- Esta es la llave de cierre de la clase que le hacía falta a NetBeanscimiento() { return idEstable