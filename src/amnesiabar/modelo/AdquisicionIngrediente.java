package amnesiabar.modelo;

import java.sql.Date;

/**
 *
 * @author Jonathan López
 */
public class AdquisicionIngrediente {
    private String nombre;
    private String proveedor;
    private double precio;
    private double cantidad;
    private Date fecha;
    
    public AdquisicionIngrediente(String nombre, String proveedor, double precio, double cantidad, Date fecha){
        this.nombre = nombre;
        this.proveedor = proveedor;
        this.precio = precio;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String distribuidora) {
        this.proveedor = distribuidora;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
