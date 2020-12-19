package amnesiabar;

import amnesiabar.controlador.ControladorLogin;
import amnesiabar.controlador.Singleton;
import amnesiabar.vista.VistaLogin;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** Clase principal donde se ejecuta la aplicación
 * @author Jonathan López
 */
public class AmnesiaBarDriver extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Singleton singleton = Singleton.getInstancia();
        singleton.setStage(primaryStage);
        
        ControladorLogin controladorLogin = new ControladorLogin();
        VistaLogin vistaLogin = controladorLogin.getVista();
        
        Scene escena = vistaLogin.getEscena();
        
        primaryStage.setTitle("AmnesiaBar");
        primaryStage.setScene(escena);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /** Método donde se ejecuta la aplicación
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
