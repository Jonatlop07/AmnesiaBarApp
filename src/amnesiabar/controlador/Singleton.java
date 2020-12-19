package amnesiabar.controlador;

import javafx.stage.Stage;

/** Clase Singleton para manejar una única instancia de una determinada vista
 * @author Jonathan López
 */
public class Singleton {
    private static Singleton unicaInstancia;
    private Stage stage;
    
    private Singleton(){}
    
    public static Singleton getInstancia(){
        if (unicaInstancia == null){
            unicaInstancia = new Singleton();
        }
        return unicaInstancia;
    }
    
    public Stage getStage(){ return stage; }
    public void setStage(Stage stage){ this.stage = stage; }
}
