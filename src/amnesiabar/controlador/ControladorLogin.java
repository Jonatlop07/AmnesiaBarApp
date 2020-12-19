package amnesiabar.controlador;

import amnesiabar.modelo.ModeloBD;
import amnesiabar.vista.VistaAdministrador;
import amnesiabar.vista.VistaFabrica;
import amnesiabar.vista.VistaLogin;
import amnesiabar.vista.VistaSede;
import java.sql.SQLException;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/** Controlador de la VistaLogin
 * @author Jonathan López
 */
public class ControladorLogin {
    private ModeloBD modelo;
    private VistaLogin vista;
    
    public ControladorLogin(){
        modelo = ModeloBD.getInstancia();
        vista = new VistaLogin();
        vista.getBtnIngreso().setOnAction(e -> {
            String usuario = (String) vista.getTfUsuario().getText();
            String clave = (String) vista.getPfClave().getText();
            if (usuario.isEmpty() || usuario.length() < 11) {
                vista.getTfUsuario().setText("");
                vista.getLbNotificacion().setTextFill(Color.RED);
                vista.getLbNotificacion().setText("El campo Usuario debe contener al menos 11 caracteres");
            } else if (clave.isEmpty() && clave.length() < 10){
                vista.getLbNotificacion().setTextFill(Color.RED);
                vista.getLbNotificacion().setText("El campo Clave debe contener al menos 10 caracteres");
            } else {
                int resultado;
                
                try{
                    resultado = modelo.realizarConexion(usuario, clave);

                    if (resultado > 0){
                        Singleton singleton = Singleton.getInstancia();
                        Stage stage = singleton.getStage();

                        switch (resultado) {
                            case 1:
                                ControladorAdministrador controladorAdm = new ControladorAdministrador();
                                VistaAdministrador vistaAdmin = controladorAdm.getVista();
                                stage.setScene(vistaAdmin.getEscena());
                                break;
                            case 2:
                                ControladorSede controladorSed = new ControladorSede();
                                VistaSede vistaSed = controladorSed.getVista();
                                stage.setScene(vistaSed.getEscena());
                                break;
                            case 3:
                                ControladorFabrica controladorFab = new ControladorFabrica();
                                VistaFabrica vistaFab = controladorFab.getVista();
                                stage.setScene(vistaFab.getEscena());
                                break;
                        }

                        stage.setOnCloseRequest((WindowEvent event) -> {
                            try{
                                modelo.getConexion().close();
                            } catch (SQLException ex){

                            }
                            Platform.exit();
                            System.exit(0);
                        });
                        stage.show();
                    } else {
                        vista.getTfUsuario().setText("");
                        vista.getLbNotificacion().setText("No se pudo conectar. Asegúrese de ingresar el usuario y la clave correctamente");
                    }
                    vista.getPfClave().setText("");
                    vista.getLbNotificacion().setTextFill(Color.RED);
                } catch(ClassNotFoundException c){
                    vista.getLbNotificacion().setTextFill(Color.DARKBLUE);
                    vista.getLbNotificacion().setText("Error cargando el Driver MySQL JDBC...FAIL");
                } catch(SQLException s){
                    s.printStackTrace();
                    vista.getLbNotificacion().setTextFill(Color.MAGENTA);
                    vista.getLbNotificacion().setText("Imposible realizar la conexión con el servidor");
                }
            }
        });
    }
    
    public VistaLogin getVista(){ return vista; }
}
