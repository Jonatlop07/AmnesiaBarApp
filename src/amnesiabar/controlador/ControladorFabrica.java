package amnesiabar.controlador;
import amnesiabar.modelo.Distribucion;
import amnesiabar.modelo.IngredienteCerveza;
import java.sql.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import amnesiabar.modelo.ModeloBD;
import amnesiabar.vista.VistaFabrica;
import amnesiabar.vista.VistaLogin;
import java.sql.SQLException;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ControladorFabrica {
    private ModeloBD modelo;
    private VistaFabrica vista;
    
    private ObservableList<IngredienteCerveza> listaInventarioMatPrima ;
    private ObservableList<Distribucion> listaDistribucionSede;
    private ObservableList<Distribucion> listaDistribucionCerveza;
    
    private final String msjExito = "Registro completado con éxito.";
    private final String msjError = "No se pudo realizar el registro. Inténtelo de nuevo.";
    private final String msjTodo = "Todos los campos deben estar diligenciados.";
    
    public ControladorFabrica(){
        modelo = ModeloBD.getInstancia();
        vista = new VistaFabrica();
        
        vista.getItemCerrarSesion().setOnAction(
                e -> { 
                    Singleton singleton = Singleton.getInstancia();
                    Stage stage = singleton.getStage();
                    ControladorLogin controladorLogin = new ControladorLogin();
                    VistaLogin vistaLogin = controladorLogin.getVista();
                    stage.setScene(vistaLogin.getEscena());
                }
        );
        
        
        vista.getTbregistrarMateriaPrimaCerveza().setOnSelectionChanged(e ->{
            if (vista.getTbregistrarMateriaPrimaCerveza().isSelected()){
                vista.getCbnumfabmatprimacerv().getItems().clear(); 
                vista.getCbnumfabmatprimacerv().getItems().addAll(modelo.obtenerNumerosFabricas());
            }
        });
        
        vista.getCbnumfabricapro().getItems().addAll(modelo.obtenerNumerosFabricas());
        vista.getCbnomcervezapro().getItems().addAll(modelo.obtenerNombresCerveza());
        
        vista.getTbregistrarproduccion().setOnSelectionChanged(e -> {
            if (vista.getTbregistrarproduccion().isSelected()){
                vista.getCbnumfabricapro().getItems().clear();
                vista.getCbnumfabricapro().getItems().addAll(modelo.obtenerNumerosFabricas()); 
                vista.getCbnomcervezapro().getItems().clear();
                vista.getCbnomcervezapro().getItems().addAll(modelo.obtenerNombresCerveza()); 
            } 
        });
        vista.getTbregistrardistribucion().setOnSelectionChanged(e -> {
            if (vista.getTbregistrardistribucion().isSelected()){
                vista.getCbnumfabdist().getItems().clear();
                vista.getCbnumfabdist().getItems().addAll(modelo.obtenerNumerosFabricas());
                vista.getCbnomcervezadist().getItems().clear();
                vista.getCbnomcervezadist().getItems().addAll(modelo.obtenerNombresCerveza()); 
                vista.getCbnumsededist().getItems().clear();
                vista.getCbnumsededist().getItems().addAll(modelo.obtenerNumerosSedes());
            }
        });
        vista.getTbregistrarcomposicioncerveza().setOnSelectionChanged(e -> {
            if (vista.getTbregistrarcomposicioncerveza().isSelected()){
                vista.getCbnumfabcomp().getItems().clear();
                vista.getCbnumfabcomp().getItems().addAll(modelo.obtenerNumerosFabricas()); 
                vista.getCbnomcervezacomp().getItems().clear(); 
                vista.getCbnomcervezacomp().getItems().addAll(modelo.obtenerNombresCerveza()); 
                
            }
        });
        vista.getTbregistraradquisicionmateriaprima().setOnSelectionChanged(e -> {
            if (vista.getTbregistraradquisicionmateriaprima().isSelected()){
                vista.getCbnommateriaadq().getItems().clear();
                vista.getCbnommateriaadq().getItems().addAll(modelo.obtenerNombresMatPrimaCerveza()); 
                vista.getCbnomdistribuidoraadq().getItems().clear();
                vista.getCbnomdistribuidoraadq().getItems().addAll(modelo.obtenerNombresDistribuidoras()); 
            }
        });
        vista.getBtregistrarproduccion().setOnAction(e -> {registrarProduccion();});
        vista.getBtregdist().setOnAction(e -> {registrarDistribucion();});
        vista.getBtregistrarafiliado().setOnAction(e -> {registrarAfiliado();});
        vista.getBtregistraradquisicion().setOnAction(e -> {registrarAdquisicionMateriaPrima();});
        vista.getBtregistrarcomposicion().setOnAction(e-> {registrarComposicion();});
        
        vista.getcbInvNumSede().getItems().addAll(modelo.obtenerNumerosFabricas()); 
        
        vista.getcbInvNumSede().getItems().addAll(modelo.obtenerNumerosFabricas());
        
        vista.getTbinventariomateriaprimacerveza().setOnSelectionChanged(e -> {
            if (vista.getTbinventariomateriaprimacerveza().isSelected()){
                vista.getcbInvNumSede().getItems().clear();
                vista.getcbInvNumSede().getItems().addAll(modelo.obtenerNumerosFabricas());
            }
        });
        
        vista.getTbdistribucionessede().setOnSelectionChanged(e -> {
            if (vista.getTbdistribucionessede().isSelected()){
                vista.getcbInvNumSedeDistribuciones().getItems().clear();
                vista.getcbInvNumSedeDistribuciones().getItems().addAll(modelo.obtenerNumerosSedes()); 
            }
        });
        
        vista.getDistribucionescerveza().setOnSelectionChanged(e -> {
            if (vista.getDistribucionescerveza().isSelected()){
                vista.getCbnomcervezadistcerv().getItems().clear();
                vista.getCbnomcervezadistcerv().getItems().addAll(modelo.obtenerNombresCerveza());
            }
        });
        
        vista.getbtnInventarioMateriaprimaCerveza().setOnAction(e -> { consultarMateriaPrimaCerveza(); });
        vista.getbtnDistribucionSede().setOnAction(e -> { consultarDistribucionesSede(); });
        vista.getbtnNombreCerveza().setOnAction(e -> { consultarDistribucionCerveza(); });
        vista.getBtregmatprimcerv().setOnAction(e ->{registrarMateriaPrimaCerveza();});
    }
    
    private void registrarProduccion(){
        controlarTextFlow();
        try {
            Date fecha = Date.valueOf(vista.getDpfechapro().getValue());
            double cantidad = Double.parseDouble(vista.getTfcantidadpro().getText());
            int numfab = (Integer) vista.getCbnumfabricapro().getSelectionModel().getSelectedItem();
            String nomcerv = (String) vista.getCbnomcervezapro().getSelectionModel().getSelectedItem();
            
            if (cantidad <= 0 || nomcerv.isEmpty()){
                vista.getTaNotificaciones().getChildren()
                        .add(mT(msjTodo + " La cantidad debe ser mayor a 0.", 2));
            } else {
                modelo.registrarProduccion(nomcerv, cantidad, numfab, fecha);
                vista.getTaNotificaciones().getChildren()
                        .add(mT(msjExito, 1));
                vista.getTfcantidadpro().setText("");
            }
        } catch(NullPointerException | NumberFormatException e){
            vista.getTaNotificaciones().getChildren().add(mT(msjTodo + " La cantidad debe ser mayor a 0.", 2));
        } catch(SQLException s){
            vista.getTaNotificaciones().getChildren().add(mT(msjError, 3));
        }
        vista.getTaNotificaciones().getChildren().add(new Text("\n"));
    }
    
    private void registrarMateriaPrimaCerveza(){
        controlarTextFlow();
        try{
            String nombre = vista.getTfnombreMatPrimaCer().getText(); 
            int numfab = (Integer) vista.getCbnumfabmatprimacerv().getSelectionModel().getSelectedItem();
            if (nombre.isEmpty()){
                vista.getTaNotificaciones().getChildren().add(mT(msjTodo, 2));
            } else {
                modelo.registrarMateriaPrimaCerveza(nombre, numfab);
                vista.getTaNotificaciones().getChildren().add(mT(msjExito, 1));
                vista.getTfnombreMatPrimaCer().setText("");
            }
        } catch(NullPointerException | NumberFormatException e){
            vista.getTaNotificaciones().getChildren().add(mT(msjTodo, 2));
        } catch(SQLException s){
            vista.getTaNotificaciones().getChildren().add(mT(msjError, 3));
        }
        vista.getTaNotificaciones().getChildren().add(new Text("\n"));
    }
    
    private void registrarDistribucion (){
        controlarTextFlow();
        try{
            Date fecha = Date.valueOf(vista.getDpfechadist().getValue()); 
            double cantidad = Double.parseDouble(vista.getTfcantidaddist().getText());
            String nomcerv = vista.getCbnomcervezadist().getSelectionModel().getSelectedItem().toString();
            int numfab = (Integer) vista.getCbnumfabdist().getSelectionModel().getSelectedItem(); 
            int numsede = (Integer) vista.getCbnumsededist().getSelectionModel().getSelectedItem();
            
            if (cantidad <= 0 || nomcerv.isEmpty()){
                vista.getTaNotificaciones().getChildren().add(mT(msjTodo + ". La cantidad debe ser mayor que 0.", 2));
            } else if (vista.getTfidafiliadodist().getText().isEmpty() || 
                vista.getTftelefonoafiliadodist().getText().isEmpty()){
                modelo.registrarDistribucion(nomcerv, cantidad, numsede, numfab, fecha);
                vista.getTaNotificaciones().getChildren().add(mT(msjExito, 1));
                vista.getTfcantidaddist().setText("");
            }
            else {
                int idafi = Integer.parseInt( vista.getTfidafiliadodist().getText()); 
                if (idafi >= 100000){
                    String telefono =  vista.getTftelefonoafiliadodist().getText();
                    modelo.registrarDistribucion(nomcerv, cantidad, numsede, numfab, fecha, idafi, telefono);
                    vista.getTaNotificaciones().getChildren().add(mT(msjExito, 1));
                    vista.getTfidafiliadodist().setText("");
                } else {
                    vista.getTaNotificaciones().getChildren().add(mT(" La cédula debe tener más de 6 dígitos.", 2));
                }
            }
        } catch(NullPointerException | NumberFormatException e){
            vista.getTaNotificaciones().getChildren()
                    .add(mT(msjTodo + " Para realizar la distribución a un afiliado ingresar su cédula. De lo contrario, deje el campo vacío."+
                            "El teléfono es opcional. La cantidad debe ser mayor que 0.", 2));
        } catch(SQLException s){
            vista.getTaNotificaciones().getChildren().add(mT(msjError, 3));
        }
        vista.getTaNotificaciones().getChildren().add(new Text("\n"));
    }  
    
    private void registrarAfiliado (){
        controlarTextFlow();
        try{
            int idafi = Integer.parseInt( vista.getTfidafi().getText());
            String telefono = vista.getTftelefonoafi().getText();
            if (idafi >= 100000){
                modelo.registrarAfiliado(idafi, telefono);
                vista.getTaNotificaciones().getChildren().add(mT(msjExito, 1));
                vista.getTftelefonoafi().setText("");
            } else {
                vista.getTaNotificaciones().getChildren().add(mT(" La cédula debe tener más de 6 dígitos.", 2));
            }
            
        } catch(NullPointerException | NumberFormatException e){
            vista.getTaNotificaciones().getChildren().add(mT(msjTodo + " El campo Id es opcional. La cantidad debe ser mayor que 0.", 2));
        } catch(SQLException s){
            vista.getTaNotificaciones().getChildren().add(mT(msjError, 3));
        }
        vista.getTaNotificaciones().getChildren().add(new Text("\n"));
    }
    
    private void registrarMateriaPrima(){
        controlarTextFlow();
        try{
            String nombreCliente = (String) vista.getTfnombreMatPrimaCer().getText();
            int idFab = (int) vista.getCbnumfabmatprimacerv().getSelectionModel().getSelectedItem();
            if (nombreCliente.isEmpty()){
                vista.getTaNotificaciones().getChildren().add(mT(msjTodo + " El campo Id es opcional. La cantidad debe ser mayor que 0.", 2));
            } else {
                modelo.registrarMateriaPrimaCerveza(nombreCliente, idFab);
                vista.getTaNotificaciones().getChildren().add(mT(msjExito, 1));
                vista.getTfnombreMatPrimaCer().setText("");
            }
        } catch(NullPointerException | NumberFormatException e){
            vista.getTaNotificaciones().getChildren().add(mT(msjTodo + " El campo Id es opcional. La cantidad debe ser mayor que 0.", 2));
        } catch(SQLException s){
            vista.getTaNotificaciones().getChildren().add(mT(msjError, 3));
        }
        vista.getTaNotificaciones().getChildren().add(new Text("\n"));
    }
    
    private void registrarAdquisicionMateriaPrima (){
        controlarTextFlow();
        try{
            Date fecha = Date.valueOf(vista.getDpfechaadq().getValue()); 
            double cantidad = Double.parseDouble(vista.getTfcantidadadq().getText());
            double precio = Double.parseDouble(vista.getTfprecioadq().getText()); 
            String distri = vista.getCbnomdistribuidoraadq().getSelectionModel().getSelectedItem().toString(); 
            String nommat = vista.getCbnommateriaadq().getSelectionModel().getSelectedItem().toString(); 
            
            if (cantidad <= 0 || precio < 0 || distri.isEmpty() || nommat.isEmpty()){
                vista.getTaNotificaciones().getChildren().add(mT(msjTodo + " La cantidad y el precio deben ser mayores que 0.", 2));
            } else {
                modelo.registrarIngredienteCerveza(distri, nommat, precio, cantidad, fecha);
                vista.getTaNotificaciones().getChildren().add(mT(msjExito, 1));
                vista.getTfcantidadadq().setText("");
                vista.getTfprecioadq().setText("");
            }
        } catch(NullPointerException | NumberFormatException e){
            vista.getTaNotificaciones().getChildren().add(mT(msjTodo + " La cantidad y el precio deben ser mayores que 0.", 2));
        } catch(SQLException s){
            vista.getTaNotificaciones().getChildren().add(mT(msjError, 3));
        }
        vista.getTaNotificaciones().getChildren().add(new Text("\n"));
    }
    
    private void registrarComposicion (){
        controlarTextFlow();
        try{
            String nomcerv = vista.getCbnomcervezacomp().getSelectionModel().getSelectedItem().toString();
            int numfab = (Integer) vista.getCbnumfabcomp().getSelectionModel().getSelectedItem(); 
            double cantidad = Double.parseDouble(vista.getTfcantidadcomp().getText());
            String nommat = vista.getTfnommatcomp().getText();
            if (cantidad <= 0 || nomcerv.isEmpty() || nommat.isEmpty()){
                vista.getTaNotificaciones().getChildren().add(mT(msjTodo + " La cantidad debe ser mayor que 0.", 2));
            } else {
                modelo.registrarComposicionCerveza(nomcerv, nommat, cantidad, numfab);
                vista.getTaNotificaciones().getChildren().add(mT(msjExito, 1));
                vista.getTfcantidadcomp().setText("");
                vista.getTfnommatcomp().setText("");
            }
        } catch(NullPointerException | NumberFormatException e){
            vista.getTaNotificaciones().getChildren().add(mT(msjTodo + " La cantidad debe ser mayor que 0.", 2));
        } catch(SQLException s){
            vista.getTaNotificaciones().getChildren().add(mT(msjError, 3));
        }
        vista.getTaNotificaciones().getChildren().add(new Text("\n"));
    }
    
    private void consultarMateriaPrimaCerveza(){
        controlarTextFlow();
        try{
            int NumSedFabrica = (int) vista.getcbInvNumSede().getSelectionModel().getSelectedItem();
            listaInventarioMatPrima = FXCollections.observableArrayList(modelo.obtenerIngredientesCerveza(NumSedFabrica));
            vista.getTvInventarioMateriaprimaCerveza().setItems(listaInventarioMatPrima);
            vista.getTaNotificaciones().getChildren().add(mT("Consulta exitosa.", 1));
        } catch(NullPointerException | NumberFormatException e){
            vista.getTaNotificaciones().getChildren().add(mT("Debe escoger un número de fábrica. También es posible que no hayan datos registrados.", 2));
        } catch(SQLException s){
            vista.getTaNotificaciones().getChildren().add(mT(msjError, 3));
        }
        vista.getTaNotificaciones().getChildren().add(new Text("\n"));
    }
    
    private void consultarDistribucionesSede(){
        controlarTextFlow();
        try{
            int NumSedFabrica = (int) vista.getcbInvNumSedeDistribuciones().getSelectionModel().getSelectedItem();
            listaDistribucionSede = FXCollections.observableArrayList (modelo.obtenerDistribucionesPorSede(NumSedFabrica));
            vista.gettvDistribucionSede().setItems(listaDistribucionSede);
            vista.getTaNotificaciones().getChildren().add(mT("Consulta exitosa.", 1));
        } catch(NullPointerException | NumberFormatException e){
            vista.getTaNotificaciones().getChildren().add(mT("Debe escoger un número de sede. También es posible que no hayan datos registrados.", 2));
        } catch(SQLException s){
            vista.getTaNotificaciones().getChildren().add(mT(msjError, 3));
        }
        vista.getTaNotificaciones().getChildren().add(new Text("\n"));
    }
    
    private void consultarDistribucionCerveza(){
        controlarTextFlow();
        try{
            String producto = (String) vista.getCbnomcervezadistcerv().getSelectionModel().getSelectedItem();
            if (producto.isEmpty()){
                vista.getTaNotificaciones().getChildren().add(mT("Debe escoger una cerveza artesanal.", 2));
            } else {
                listaDistribucionCerveza = FXCollections.observableArrayList(modelo.obtenerDistribucionesPorCerveza(producto));
                vista.gettvDistribucionCerveza().setItems(listaDistribucionCerveza);
                vista.getTaNotificaciones().getChildren().add(mT("Consulta exitosa.", 1));
            }
            
        }  catch(NullPointerException | NumberFormatException e){
            vista.getTaNotificaciones().getChildren().add(mT("Debe escoger una cerveza artesanal. También es posible que no hayan datos registrados.", 2));
        } catch(SQLException s){
            vista.getTaNotificaciones().getChildren().add(mT(msjError, 3));
        }
        vista.getTaNotificaciones().getChildren().add(new Text("\n"));
    }
    
    private void controlarTextFlow(){
        if (vista.getTaNotificaciones().getChildren().size() > 8)
            vista.getTaNotificaciones().getChildren().clear();
    }
    
    private Text mT(String mensaje, int color){
        Text t = new Text();
        t.setText(mensaje);
        switch (color) {
            case 1:
                t.setFill(Color.GREEN);
                break;
            case 2:
                t.setFill(Color.RED);
                break;
            default:
                t.setFill(Color.MAGENTA);
                break;
        }
        return t;
    }
    
    public VistaFabrica getVista(){return vista;}
}
