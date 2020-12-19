package amnesiabar.modelo;

/**
 *
 * @author Jonathan López
 */
public class Afiliado {
    private int cedula;
    private String telefono;
    
    public Afiliado(int cedula, String telefono) {
        this.cedula = cedula;
        this.telefono = telefono;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
