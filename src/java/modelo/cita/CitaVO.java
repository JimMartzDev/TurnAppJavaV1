package modelo.cita;

public class CitaVO {
    // Atributos reales basados en la estructura exacta de tu tabla 'cita'
    private int idCita;
    private int idCliente;
    private String fechaHora; // Formato esperado por MySQL DATETIME: YYYY-MM-DD HH:MM:SS
    private String estado;    // Mapea el ENUM('pendiente', ...)
    private String notasCliente;
    private int idProfesional;
    private int idServicio;

    // Atributos auxiliares para facilitar la recepción desde el formulario JSP
    private String fechaCita; // Captura el input de tipo date (YYYY-MM-DD)
    private String horaCita;  // Captura el selector de hora (HH:MM:SS)

    // Constructor vacío exigido por los estándares de JavaBeans
    public CitaVO() {
    }

    // Constructor para insertar un nuevo registro desde el formulario de agendamiento
    public CitaVO(int idCliente, int idProfesional, int idServicio, String fechaCita, String horaCita, String notasCliente) {
        this.idCliente = idCliente;
        this.idProfesional = idProfesional;
        this.idServicio = idServicio;
        this.fechaCita = fechaCita;
        this.horaCita = horaCita;
        this.notasCliente = notasCliente;
        this.estado = "pendiente"; // Estado inicial por defecto de tu ENUM
    }

    // Constructor completo para cuando recuperes datos desde la BD (ej. Listados o Historiales)
    public CitaVO(int idCita, int idCliente, String fechaHora, String estado, String notasCliente, int idProfesional, int idServicio) {
        this.idCita = idCita;
        this.idCliente = idCliente;
        this.fechaHora = fechaHora;
        this.estado = estado;
        this.notasCliente = notasCliente;
        this.idProfesional = idProfesional;
        this.idServicio = idServicio;
    }

    // Método lógico de utilidad para unificar los inputs individuales del formulario
    public String getFechaHoraCombinada() {
        if (this.fechaCita != null && this.horaCita != null) {
            return this.fechaCita + " " + this.horaCita;
        }
        return this.fechaHora;
    }

    // --- Métodos de Encapsulamiento (Getters y Setters) ---
    public int getIdCita() { return idCita; }
    public void setIdCita(int idCita) { this.idCita = idCita; }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public String getFechaHora() { return fechaHora; }
    public void setFechaHora(String fechaHora) { this.fechaHora = fechaHora; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getNotasCliente() { return notasCliente; }
    public void setNotasCliente(String notasCliente) { this.notasCliente = notasCliente; }

    public int getIdProfesional() { return idProfesional; }
    public void setIdProfesional(int idProfesional) { this.idProfesional = idProfesional; }

    public int getIdServicio() { return idServicio; }
    public void setIdServicio(int idServicio) { this.idServicio = idServicio; }

    public String getFechaCita() { return fechaCita; }
    public void setFechaCita(String fechaCita) { this.fechaCita = fechaCita; }

    public String getHoraCita() { return horaCita; }
    public void setHoraCita(String horaCita) { this.horaCita = horaCita; }
}