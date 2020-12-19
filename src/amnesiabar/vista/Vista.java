package amnesiabar.vista;

import javafx.scene.Scene;

/** Clase abstracta que define las características y el comportamiento principales de toda vista
 * @author Jonathan López
 */
public abstract class Vista {
    protected static final double ANCHO_PREDETERMINADO = 800;
    protected static final double ALTO_PREDETERMINADO = 700;
    
    protected final double ancho;
    protected final double alto;
    
    protected Scene escena;
    
    /** Constructor que establece el tamaño de la vista predeterminado
     */
    public Vista(){
        ancho = ANCHO_PREDETERMINADO;
        alto = ALTO_PREDETERMINADO;
    }
    
    /** Constructor que establece el tamaño de la vista definido por el usuario
     * @param ancho Ancho de la vista
     * @param alto Alto de la vista
     */
    public Vista(double ancho, double alto){
        this.ancho = ancho;
        this.alto = alto;
    }
    
    /** Inicializa los componentes de la vista
     */
    protected abstract void inicializar();
    public Scene getEscena(){ return escena; }
    public double getAncho(){ return ancho; }
    public double getAlto(){ return alto; }
}
