package amnesiabar.modelo;

/**
 *
 * @author Jonathan López
 */
public class Distribucion {
    private String nombreCerveza;
    private double cantidad;
    private int numSede;
    
    public Distribucion(String nombreCerveza, double cantidad, int numSede) {
        this.nombreCerveza = nombreCerveza;
        this.cantidad = cantidad;
        this.numSede = numSede;
    }
    
    public String getNombreCerveza() {
        return nombreCerveza;
    }

    public void setNombreCerveza(String nombreCerveza) {
        this.nombreCerveza = nombreCerveza;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public int getNumSede() {
        return numSede;
    }

    public void setNumSede(int numSede) {
        this.numSede = numSede;
    }
}
