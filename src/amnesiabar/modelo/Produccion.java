package amnesiabar.modelo;

import java.sql.Date;

/**
 *
 * @author Jonathan López
 */
public class Produccion {
    private int id;
    private Date fecha;
    private int numFab;
    private String nombreProducto;
    private double cantidad;

    public Produccion(int id, Date fecha, int numFab, String nombreProducto, double cantidad) {
        this.id = id;
        this.fecha = fecha;
        this.numFab = numFab;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getNumFab() {
        return numFab;
    }

    public void setNumFab(int numFab) {
        this.numFab = numFab;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }
}
