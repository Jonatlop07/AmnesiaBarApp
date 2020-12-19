package amnesiabar.modelo;

import java.sql.Date;

/**
 *
 * @author Jonathan López
 */
public class Compra {
    private Date fecha;
    private int id;
    private int sede;
    private int cedulaCliente;
    private int item;
    private String producto;
    private double cantidad;
    private double precioProducto;
    private double precio;
    
    public Compra(Date fecha, int id, int sede, int cedulaCliente, int item, 
            String producto, double cantidad, double precioProducto, double precio) {
        this.fecha = fecha;
        this.id = id;
        this.sede = sede;
        this.cedulaCliente = cedulaCliente;
        this.item = item;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioProducto = precioProducto;
        this.precio = precio;
    }
    
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSede() {
        return sede;
    }

    public void setSede(int sede) {
        this.sede = sede;
    }

    public int getCedulaCliente() {
        return cedulaCliente;
    }

    public void setCedulaCliente(int cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(double precioProducto) {
        this.precioProducto = precioProducto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
