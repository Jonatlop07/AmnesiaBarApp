package amnesiabar.modelo;

/**
 *
 * @author Jonathan López
 */
public class LicorSede {
    private String nombre;
    private double precio;
    private double presentacion;
    private double cantidad;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(double presentacion) {
        this.presentacion = presentacion;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public LicorSede(String nombre, double precio, double presentacion, double cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.presentacion = presentacion;
        this.cantidad = cantidad;
    }
}
