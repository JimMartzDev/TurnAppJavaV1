package Modelo.registro;

/**
 * @author jimma
 */
public class ProfesionalVO {
    
    // Atributos propios de la tabla 'profesional'
    private int idProfesional;
    private int idUsuario;
    private String especialidadPrincipal; // Mapea a especialidad_principal en la BD
    
    // Atributos heredados del JOIN con la tabla 'usuario' (Para mostrar en la interfaz)
    private String nombre;
    private String apellido;

    // 1. Constructor vacío (Esencial para inicializarlo en los métodos de búsqueda o DAO)
    public ProfesionalVO() {
    }

    // 2. Constructor completo
    public ProfesionalVO(int idProfesional, int idUsuario, String especialidadPrincipal, String nombre, String apellido) {
        this.idProfesional = idProfesional;
        this.idUsuario = idUsuario;
        this.especialidadPrincipal = especialidadPrincipal;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    // 3. GETTERS Y SETTERS (El estándar que tu JSP leerá con Expression Language)

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

    public void setEspecialidadPrincipal(String LaurelEspecialidad) {
        this.especialidadPrincipal= especialidadPrincipal;
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