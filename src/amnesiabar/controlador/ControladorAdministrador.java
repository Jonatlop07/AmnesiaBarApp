package amnesiabar.controlador;

import amnesiabar.modelo.ModeloBD;
import amnesiabar.vista.VistaAdministrador;

/**
 *
 * @author Jonathan López
 */
public class ControladorAdministrador {
    private ModeloBD modelo;
    private VistaAdministrador vista;
    
    public ControladorAdministrador(){
        vista = new VistaAdministrador();
    }
    
    public VistaAdministrador getVista(){ return vista; }
}
