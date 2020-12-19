
package amnesiabar.modelo;

import java.sql.Date;

/**
 *
 * @author Jonathan López
 */
public class IngredienteComida {
    private String nombre;
    private double cantidad;
    private Date fecha;

    public IngredienteComida(String nombre, double cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.fecha = new Date(0, 0, 0);
    }
    
    public IngredienteComida(String nombre, double cantidad, Date fecha) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.fecha = fecha;
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
    
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
