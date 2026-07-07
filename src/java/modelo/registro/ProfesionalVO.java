package modelo.registro;

public class ProfesionalVO {

    private int idProfesional;
    private int idUsuario;
    private String especialidadPrincipal;

    private String nombre;
    private String apellido;

    public ProfesionalVO() {
    }

    public ProfesionalVO(int idProfesional, int idUsuario, String especialidadPrincipal, String nombre, String apellido) {
        this.idProfesional = idProfesional;
        this.idUsuario = idUsuario;
        this.especialidadPrincipal = especialidadPrincipal;
        this.nombre = nombre;
        this.apellido = apellido;
    }

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

    public String getEspecialidadPrincipal() {
        return especialidadPrincipal;
    }

    public void setEspecialidadPrincipal(String especialidadPrincipal) {
        this.especialidadPrincipal = especialidadPrincipal;
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
}
