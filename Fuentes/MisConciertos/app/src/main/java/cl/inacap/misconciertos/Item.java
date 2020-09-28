package cl.inacap.misconciertos;

public class Item { //Esta clase es la que representa a cada item en el ListView.
    private int imagen; //Tiene que ser int, ya que se hará referencia al id de la imagen.
    private String nombreArtista;
    private String fecha;
    private String valorEntrada;

    public Item(int imagen, String nombreArtista, String fecha, String valorEntrada) {
        this.imagen = imagen;
        this.nombreArtista = nombreArtista;
        this.fecha = fecha;
        this.valorEntrada = valorEntrada;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getNombreArtista() {
        return nombreArtista;
    }

    public void setNombreArtista(String nombreArtista) {
        this.nombreArtista = nombreArtista;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getValorEntrada() {
        return valorEntrada;
    }

    public void setValorEntrada(String valorEntrada) {
        this.valorEntrada = valorEntrada;
    }
}
