package amnesiabar.modelo;

/**
 *
 * @author Jonathan López
 */
public class Producto {
    private String nombre;
    private double cantidad;
    private double montoTotal;
    
    public Producto(String nombre, double cantidad, double montoTotal) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.montoTotal = montoTotal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }
}
