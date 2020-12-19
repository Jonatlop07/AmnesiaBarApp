package amnesiabar.modelo;

/**
 *
 * @author Jonathan López
 */
public class IngredienteCerveza {
    private String nombreIngrediente;
    private double cantidadIngrediente;
    
    public IngredienteCerveza(String nombre, double cantidad){
        this.nombreIngrediente = nombre;
        this.cantidadIngrediente = cantidad;
    }
    
    public String getNombreIngrediente() {
        return nombreIngrediente;
    }
    
    public void setNombreIngrediente(String nombreIngrediente) {
        this.nombreIngrediente = nombreIngrediente;
    }
    
    public double getCantidadIngrediente() {
        return cantidadIngrediente;
    }

    public void setCantidadIngrediente(double cantidadIngrediente) {
        this.cantidadIngrediente = cantidadIngrediente;
    }
}
