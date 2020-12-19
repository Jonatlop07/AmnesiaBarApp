package amnesiabar.vista;

import amnesiabar.utilidad.CampoLimitadoTexto;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/** Vista que se inicia cuando se ejecuta la aplicación y que permite validar el ingreso de un usuario
 *
 * @author Jonathan López
 */
public class VistaLogin extends Vista{
    
    private CampoLimitadoTexto tfUsuario;
    private PasswordField pfClave;
    private Label lbNotificacion;
    private Button btnIngreso;
    
    public VistaLogin(){
        super();
        inicializar();
    }
    
    public VistaLogin(double ancho, double alto){
        super(ancho, alto);
        inicializar();
    }
    
    @Override
    protected void inicializar(){
        HBox layoutPrincipal = new HBox();
        
        VBox vbSeccionLogin = new VBox();
        //vbSeccionLogin.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        vbSeccionLogin.setAlignment(Pos.CENTER);
        vbSeccionLogin.setSpacing(20);
        vbSeccionLogin.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, 
                CornerRadii.EMPTY, new BorderWidths(4))));
        vbSeccionLogin.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #FCFAFA, #B1F9F4)");
        
        Label lbIniciarSesion = new Label("Iniciar sesion");
        lbIniciarSesion.setFont(Font.font("Papyrus", FontWeight.BOLD, 25));
        lbIniciarSesion.setTextFill(Color.BLUE);
        
        
        HBox hbIngresoUsuario = new HBox();
        hbIngresoUsuario.setAlignment(Pos.CENTER);
        hbIngresoUsuario.setSpacing(5);
        Label lbUsuario = new Label("Usuario:");
        lbUsuario.setStyle("-fx-font-family: 'Papyrus'; fx-font-size: 16;");
        tfUsuario = new CampoLimitadoTexto(22);
        hbIngresoUsuario.getChildren().setAll(lbUsuario, tfUsuario);
        
        HBox hbIngresoClave = new HBox();
        hbIngresoClave.setAlignment(Pos.CENTER);
        hbIngresoClave.setSpacing(5);
        Label lbClave = new Label("Contraseña:");
        lbClave.setStyle("-fx-font-family: 'Papyrus'; fx-font-size: 16;");
        pfClave = new PasswordField();
        hbIngresoClave.getChildren().setAll(lbClave, pfClave);
        
        lbNotificacion = new Label();
        lbNotificacion.setFont(Font.font("Arial", 12));
        
        btnIngreso = new Button("Ingresar");
        btnIngreso.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        btnIngreso.setStyle("-fx-background-color: #AFFA97; -fx-border-color: #000000; -fx-border-width: 2px;");
        vbSeccionLogin.getChildren().setAll(lbIniciarSesion, hbIngresoUsuario, hbIngresoClave, lbNotificacion, btnIngreso);
        
        VBox vbSeccionTitulo = new VBox();
        vbSeccionTitulo.setAlignment(Pos.CENTER);
        //vbSeccionTitulo.setBackground(new Background(new BackgroundFill(Color.DARKGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        vbSeccionTitulo.setSpacing(20);
        vbSeccionTitulo.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, 
                CornerRadii.EMPTY, new BorderWidths(4))));
        vbSeccionTitulo.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #AFFA97, #47A828)");
        
        Label lbTitulo = new Label("AMNESIA BAR");
        String estilolbTitulo = "-fx-stroke: firebrick; -fx-stroke-width: 4px;";
        lbTitulo.setFont(Font.font("Courier New", FontWeight.BOLD, 40));
        lbTitulo.setTextFill(Color.WHITE);
        lbTitulo.setStyle(estilolbTitulo);
        
        final ImageView iv = new ImageView();
        
        try{
            Image imagen = new Image(new FileInputStream("src/amnesiabar/utilidad/duende.jpg"));
            iv.setImage(imagen);
        } catch(FileNotFoundException e){
            System.out.println("Error al cargar la imagen");
        }
        
        vbSeccionTitulo.getChildren().addAll(lbTitulo, iv);
        
        layoutPrincipal.getChildren().addAll(vbSeccionLogin, vbSeccionTitulo);
        HBox.setHgrow(vbSeccionLogin, Priority.ALWAYS);
        HBox.setHgrow(vbSeccionTitulo, Priority.ALWAYS);
             
        escena = new Scene(layoutPrincipal, getAncho(), getAlto());
    }
    
    public Button getBtnIngreso(){ return btnIngreso; }
    public CampoLimitadoTexto getTfUsuario(){ return tfUsuario; }
    public PasswordField getPfClave(){ return pfClave; }
    public Label getLbNotificacion() { return lbNotificacion; }
}

