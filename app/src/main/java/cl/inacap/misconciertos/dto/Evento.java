package cl.inacap.misconciertos.dto;

public class Evento {
    private String nombreArtista;
    private String fechaEvento;
    private String generoMusical;
    private int valorEntrada;
    private int calificacion;

    @Override
    public String toString() {
        return "Evento[" + "nombreArtista='" + nombreArtista + "\'" +
            "; fechaEvento='" + fechaEvento + "\'" +
            "; generoMusical='" + generoMusical + "\'" +
            "; valorEntrada=" + valorEntrada + "\'" +
            "; calificacion=" + calificacion +
            "]";
    }

    public String getNombreArtista() {
        return nombreArtista;
    }

    public void setNombreArtista(String nombreArtista) {
        this.nombreArtista = nombreArtista;
    }

    public String getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(String fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public String getGeneroMusical() {
        return generoMusical;
    }

    public void setGeneroMusical(String generoMusical) {
        this.generoMusical = generoMusical;
    }

    public int getValorEntrada() {
        return valorEntrada;
    }

    public void setValorEntrada(int valorEntrada) {
        this.valorEntrada = valorEntrada;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }
}
