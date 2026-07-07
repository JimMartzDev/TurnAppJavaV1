package modelo.cita;

public class CitaVO {

    private int idCita;
    private int idCliente;
    private String fechaHora;
    private String estado;
    private String notasCliente;
    private int idProfesional;
    private int idServicio;

    private String fechaCita;
    private String horaCita;

    public CitaVO() {
    }

    public CitaVO(int idCliente, int idProfesional, int idServicio, String fechaCita, String horaCita, String notasCliente) {
        this.idCliente = idCliente;
        this.idProfesional = idProfesional;
        this.idServicio = idServicio;
        this.fechaCita = fechaCita;
        this.horaCita = horaCita;
        this.notasCliente = notasCliente;
        this.estado = "pendiente";
    }

    public CitaVO(int idCita, int idCliente, String fechaHora, String estado, String notasCliente, int idProfesional, int idServicio) {
        this.idCita = idCita;
        this.idCliente = idCliente;
        this.fechaHora = fechaHora;
        this.estado = estado;
        this.notasCliente = notasCliente;
        this.idProfesional = idProfesional;
        this.idServicio = idServicio;
    }

    public String getFechaHoraCombinada() {
        if (this.fechaCita != null && this.horaCita != null) {
            return this.fechaCita + " " + this.horaCita;
        }
        return this.fechaHora;
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNotasCliente() {
        return notasCliente;
    }

    public void setNotasCliente(String notasCliente) {
        this.notasCliente = notasCliente;
    }

    public int getIdProfesional() {
        return idProfesional;
    }

    public void setIdProfesional(int idProfesional) {
        this.idProfesional = idProfesional;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public String getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(String fechaCita) {
        this.fechaCita = fechaCita;
    }

    public String getHoraCita() {
        return horaCita;
    }

    public void setHoraCita(String horaCita) {
        this.horaCita = horaCita;
    }
}
