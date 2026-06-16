package Modelo.cita;


public class CitaVO {
    private int idCita;
    private int idCliente;
    private String fechaHora; // Se mapeará como String o Timestamp en el DAO
    private String estado;    // 'Pendiente', 'Realizada', 'Cancelada'
    private int idProfesionalServicio;
    private String notasCliente;

    // Constructor vacío obligado
    public CitaVO() {
    }

    // Constructor con parámetros
    public CitaVO(int idCliente, String fechaHora, String estado, int idProfesionalServicio, String notasCliente) {
        this.idCliente = idCliente;
        this.fechaHora = fechaHora;
        this.estado = estado;
        this.idProfesionalServicio = idProfesionalServicio;
        this.notasCliente = notasCliente;
    }

    // Getters y Setters
    public int getIdCita() { return idCita; }
    public void setIdCita(int idCita) { this.idCita = idCita; }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public String getFechaHora() { return fechaHora; }
    public void setFechaHora(String fechaHora) { this.fechaHora = fechaHora; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public int getIdProfesionalServicio() { return idProfesionalServicio; }
    public void setIdProfesionalServicio(int idProfesionalServicio) { this.idProfesionalServicio = idProfesionalServicio; }

    public String getNotasCliente() { return notasCliente; }
    public void setNotasCliente(String notasCliente) { this.notasCliente = notasCliente; }
}