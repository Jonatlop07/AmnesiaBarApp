package amnesiabar.vista;

import amnesiabar.modelo.Distribucion;
import amnesiabar.modelo.IngredienteCerveza;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import amnesiabar.utilidad.CampoLimitadoTexto;
import java.time.LocalDate;
import javafx.scene.text.TextFlow;
/**
 *
 * @author Jonathan López
 */
public class VistaFabrica extends Vista{
    private MenuItem itemCerrarSesion;
    
    private TextFlow taNotificaciones;
    
    private Tab tbregistrarMateriaPrimaCerveza; 
    private Label lbnombrematprimacer;
    private CampoLimitadoTexto tfnombreMatPrimaCer; 
    private ChoiceBox cbnumfabmatprimacerv; 
    private Button btregmatprimcerv; 
    
    private Tab tbregistrarproduccion; 
    private Label lbfechapro; 
    private DatePicker dpfechapro; 
    private Label lbcantidadpro; 
    private CampoLimitadoTexto tfcantidadpro; 
    private Label lbnumfabricapro; 
    private ChoiceBox cbnumfabricapro; 
    private Label lbnomcervezapro; 
    private ChoiceBox cbnomcervezapro; 
    private Button btregistrarproduccion; 
    private Label lbnotifregproduccion; 
                   
    private Tab tbregistrarafiliado; 
    private Label lbidafi; 
    private CampoLimitadoTexto tfidafi; 
    private Label lbtelefonoafi; 
    private CampoLimitadoTexto tftelefonoafi; 
    private Button btregistrarafiliado; 
    private Label lbnotifregafiliado; 
    
    private Tab tbregistraradquisicionmateriaprima;
    private Label lbnomdistribuidoraadq; 
    private ChoiceBox cbnomdistribuidoraadq; 
    private Label lbnommateriaadq; 
    private ChoiceBox cbnommateriaadq; 
    private Label lbprecioadq; 
    private CampoLimitadoTexto tfprecioadq; 
    private Label lbcantidadadq; 
    private CampoLimitadoTexto tfcantidadadq; 
    private Label lbfechaadq; 
    private DatePicker dpfechaadq;
    private Button btregistraradquisicion; 
    private Label lbnotifregadquisicion; 
            
    private Tab tbregistrarcomposicioncerveza; 
    private Label lbnomcervezacomp;
    private ChoiceBox cbnomcervezacomp; 
    private Label lbnommatcomp; 
    private CampoLimitadoTexto tfnommatcomp; 
    private Label lbcantidadcomp; 
    private CampoLimitadoTexto tfcantidadcomp; 
    private Label lbnumfabcomp; 
    private ChoiceBox cbnumfabcomp; 
    private Button btregistrarcomposicion; 
    private Label lbnotifregcomposicion;
            
    private Tab tbregistrardistribucion; 
    private Label lbfechadist; 
    private DatePicker dpfechadist; 
    private Label lbcantidaddist; 
    private CampoLimitadoTexto tfcantidaddist; 
    private Label lbnomcervezadist; 
    private ChoiceBox cbnomcervezadist; 
    private Label lbidafiliadodist; 
    private CampoLimitadoTexto tfidafiliadodist; 
    private Label lbtelefonoafiliadodist; 
    private CampoLimitadoTexto tftelefonoafiliadodist;
    private Label lbnumsededist; 
    private ChoiceBox cbnumsededist; 
    private Label lbnumfabdist; 
    private ChoiceBox cbnumfabdist; 
    private Button btregdist; 
    private Label lbnotifregdist; 
    
    private Tab tbinventariomateriaprimacerveza; 
    private Label lbnumfabinv; 
    
    private Tab tbdistribucionessede; 
    private Label lbnumsededistsede; 
    
    private Tab distribucionescerveza; 
    private Label lbnomcervezadistcerv;
    private ChoiceBox cbnomcervezadistcerv;
    
    private ChoiceBox cbInvNumSede;
    private Button btnInventarioMateriaprimaCerveza ;
    private ChoiceBox cbInvNumSedeDistribuciones;
    private Button btnDistribucionSede;
    private Button btnNombreCerveza;
    
    private TableView tvInventarioMateriaprimaCerveza;
    private TableView tvDistribucionSede;
    private TableView tvDistribucionCerveza;
    
    
    public VistaFabrica(){
        super();
        inicializar();
    }
    
    public VistaFabrica(double ancho, double alto){
        super(ancho, alto);
        inicializar();
    }
    
    @Override
    protected void inicializar() {
        VBox layoutPrincipal = new VBox();
        
        escena = new Scene(layoutPrincipal, getAncho(), getAlto());
        escena.getStylesheets().add("amnesiabar/estilos/estilosFabrica.css");
        
        layoutPrincipal.getStyleClass().add("vb-main");
        
        MenuBar barraPrincipal = new MenuBar();
        Menu menuInicio = new Menu("Inicio");
        itemCerrarSesion = new MenuItem("Cerrar sesión");
        
        menuInicio.getItems().add(itemCerrarSesion);
        barraPrincipal.getMenus().add(menuInicio);
        
        TabPane tpPanel = new TabPane();
        tpPanel.getStyleClass().add("tab-principal");
        tpPanel.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        Tab tbRegistros = new Tab("Registros"); 
        TabPane tpRegistros = new TabPane(); 
        tpRegistros.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        
        tbregistrarproduccion = new Tab ("Producción"); 
        
        lbfechapro = new Label ("Fecha: "); 
        dpfechapro = new DatePicker (LocalDate.now());
        dpfechapro.setEditable(false);
        HBox hbfechapro = new HBox(); 
        hbfechapro.getChildren().addAll(lbfechapro,dpfechapro);
        hbfechapro.getStyleClass().add("sub-hb");
        
        lbcantidadpro= new Label ("Cantidad(mililitros):");
        tfcantidadpro = new CampoLimitadoTexto(45);
        HBox hbcantidadpro = new HBox(); 
        hbcantidadpro.getChildren().addAll(lbcantidadpro,tfcantidadpro); 
        hbcantidadpro.getStyleClass().add("sub-hb");
        
        lbnumfabricapro= new Label ("Número de Fábrica: ");
        cbnumfabricapro = new ChoiceBox(); 
        HBox hbnumfabpro = new HBox(); 
        hbnumfabpro.getChildren().addAll(lbnumfabricapro,cbnumfabricapro); 
        hbnumfabpro.getStyleClass().add("sub-hb");
        
        lbnomcervezapro = new Label ("Nombre de la Cerveza: "); 
        cbnomcervezapro = new ChoiceBox (); 
        HBox hbnomcerpro = new HBox(); 
        hbnomcerpro.getChildren().addAll(lbnomcervezapro,cbnomcervezapro);
        hbnomcerpro.getStyleClass().add("sub-hb");
        
        btregistrarproduccion = new Button ("Registrar"); 
        lbnotifregproduccion= new Label(); 
        
        VBox vbregistrarpro = new VBox (); 
        vbregistrarpro.getChildren().addAll(hbfechapro,hbcantidadpro,hbnumfabpro,hbnomcerpro,btregistrarproduccion,lbnotifregproduccion); 
        vbregistrarpro.getStyleClass().add("vb");
        tbregistrarproduccion.setContent(vbregistrarpro);
        
        tbregistrarafiliado = new Tab ("Afiliado");
        lbidafi = new Label ("Cédula: "); 
        tfidafi = new CampoLimitadoTexto (45); 
        HBox hbidafi = new HBox (); 
        hbidafi.getChildren().addAll(lbidafi,tfidafi);
        hbidafi.getStyleClass().add("sub-hb");
        
        lbtelefonoafi = new Label ("Telefóno: "); 
        tftelefonoafi = new CampoLimitadoTexto(45); 
        HBox hbtelefonoafi = new HBox(); 
        hbtelefonoafi.getChildren().addAll(lbtelefonoafi,tftelefonoafi);
        hbtelefonoafi.getStyleClass().add("sub-hb");
        
        btregistrarafiliado = new Button ("Registrar"); 
        lbnotifregafiliado = new Label(); 
        
        VBox vbregistrarafi = new VBox(); 
        vbregistrarafi.getChildren().addAll(hbidafi,hbtelefonoafi,btregistrarafiliado,lbnotifregafiliado);
        vbregistrarafi.getStyleClass().add("vb");
        tbregistrarafiliado.setContent(vbregistrarafi);
        
        tbregistraradquisicionmateriaprima = new Tab ("Adquisicion"); 
        
        lbnomdistribuidoraadq = new Label ("Proveedor: "); 
        cbnomdistribuidoraadq = new ChoiceBox(); 
        HBox hbnomdistriadq = new HBox (); 
        hbnomdistriadq.getChildren().addAll(lbnomdistribuidoraadq,cbnomdistribuidoraadq);
        hbnomdistriadq.getStyleClass().add("sub-hb");
        
        lbnommateriaadq = new Label ("Nombre del ingrediente: ");
        cbnommateriaadq = new ChoiceBox(); 
        HBox hbnommateriadq= new HBox(); 
        hbnommateriadq.getChildren().addAll(lbnommateriaadq,cbnommateriaadq);
        hbnommateriadq.getStyleClass().add("sub-hb");
        
        lbprecioadq = new Label ("Precio: "); 
        tfprecioadq= new CampoLimitadoTexto(45); 
        HBox hbprecioadq= new HBox(); 
        hbprecioadq.getChildren().addAll(lbprecioadq,tfprecioadq); 
        hbprecioadq.getStyleClass().add("sub-hb");
        
        lbcantidadadq= new Label ("Cantidad: "); 
        tfcantidadadq = new CampoLimitadoTexto (45); 
        HBox hbcantidadadq = new HBox();
        hbcantidadadq.getChildren().addAll(lbcantidadadq,tfcantidadadq);
        hbcantidadadq.getStyleClass().add("sub-hb");
        
        lbfechaadq= new Label ("Fecha de adquisición: ");
        dpfechaadq= new DatePicker(LocalDate.now()); 
        HBox hbfechaadq = new HBox(); 
        hbfechaadq.getChildren().addAll(lbfechaadq,dpfechaadq);
        hbfechaadq.getStyleClass().add("sub-hb");
        
        btregistraradquisicion= new Button("Registrar"); 
        lbnotifregadquisicion= new Label (); 
        
        VBox vbregistraradquisicion= new VBox(); 
        vbregistraradquisicion.getChildren().addAll(hbnomdistriadq,hbnommateriadq,hbprecioadq,hbcantidadadq,hbfechaadq,btregistraradquisicion,lbnotifregadquisicion); 
        vbregistraradquisicion.getStyleClass().add("vb");
        tbregistraradquisicionmateriaprima.setContent(vbregistraradquisicion);
        
        tbregistrarMateriaPrimaCerveza = new Tab ("Ingredientes"); 
        lbnombrematprimacer = new Label ("Nombre del ingrediente: "); 
        tfnombreMatPrimaCer = new CampoLimitadoTexto(45);
        HBox hbnombrematprimacer = new HBox (); 
        hbnombrematprimacer.getChildren().addAll(lbnombrematprimacer,tfnombreMatPrimaCer);
        hbnombrematprimacer.getStyleClass().add("sub-hb");
        Label lbNumFabMatPrim = new Label("Número de fábrica: ");
        cbnumfabmatprimacerv = new ChoiceBox(); 
        HBox hbnumfabmatprimacerv = new HBox (); 
        hbnumfabmatprimacerv.getChildren().addAll(lbNumFabMatPrim, cbnumfabmatprimacerv); 
        hbnumfabmatprimacerv.getStyleClass().add("sub-hb"); 
        btregmatprimcerv = new Button ("Registrar"); 
        VBox vbregmatprimacerv = new VBox (); 
        vbregmatprimacerv.getChildren().addAll(hbnombrematprimacer,hbnumfabmatprimacerv,btregmatprimcerv); 
        vbregmatprimacerv.getStyleClass().add("vb"); 
        tbregistrarMateriaPrimaCerveza.setContent(vbregmatprimacerv);
        
        tbregistrarcomposicioncerveza = new Tab ("Composición Cerveza"); 
        lbnomcervezacomp = new Label("Nombre de la cerveza: "); 
        cbnomcervezacomp= new ChoiceBox(); 
        HBox hbnomcervezacomp = new HBox(); 
        hbnomcervezacomp.getChildren().addAll(lbnomcervezacomp,cbnomcervezacomp);
        hbnomcervezacomp.getStyleClass().add("sub-hb");
        
        lbnommatcomp= new Label ("Nombre del ingrediente: "); 
        tfnommatcomp = new CampoLimitadoTexto(45); 
        HBox hbnommatcomp = new HBox(); 
        hbnommatcomp.getChildren().addAll(lbnommatcomp,tfnommatcomp);
        hbnommatcomp.getStyleClass().add("sub-hb");
        
        lbcantidadcomp = new Label ("Cantidad: "); 
        tfcantidadcomp = new CampoLimitadoTexto (45); 
        HBox hbcantidadcomp = new HBox (); 
        hbcantidadcomp.getChildren().addAll(lbcantidadcomp,tfcantidadcomp);
        hbcantidadcomp.getStyleClass().add("sub-hb");
        
        lbnumfabcomp = new Label ("Número Fábrica: "); 
        cbnumfabcomp = new ChoiceBox(); 
        HBox hbnumfabcomp = new HBox (); 
        hbnumfabcomp.getChildren().addAll(lbnumfabcomp,cbnumfabcomp);
        hbnumfabcomp.getStyleClass().add("sub-hb");
        
        btregistrarcomposicion = new Button ("Registrar"); 
        lbnotifregcomposicion = new Label(); 
        
        VBox vbregcomposicion = new VBox (); 
        vbregcomposicion.getChildren().addAll(hbnomcervezacomp,hbnommatcomp,hbcantidadcomp,hbnumfabcomp,btregistrarcomposicion,lbnotifregcomposicion);
        vbregcomposicion.getStyleClass().add("vb");
        tbregistrarcomposicioncerveza.setContent(vbregcomposicion);
        
        tbregistrardistribucion = new Tab ("Distribución"); 
        lbfechadist = new Label ("Fecha"); 
        dpfechadist = new DatePicker(LocalDate.now()); 
        HBox hbfechadist = new HBox (); 
        hbfechadist.getChildren().addAll(lbfechadist,dpfechadist);
        hbfechadist.getStyleClass().add("sub-hb");
        
        lbnomcervezadist= new Label ("Cerveza: "); 
        cbnomcervezadist = new ChoiceBox (); 
        HBox hbnomcervezadist = new HBox (); 
        hbnomcervezadist.getChildren().addAll(lbnomcervezadist,cbnomcervezadist);
        hbnomcervezadist.getStyleClass().add("sub-hb");
        
        lbcantidaddist = new Label ("Cantidad(mililitros):"); 
        tfcantidaddist = new CampoLimitadoTexto(45); 
        HBox hbcantidaddist = new HBox (); 
        hbcantidaddist.getChildren().addAll(lbcantidaddist,tfcantidaddist);
        hbcantidaddist.getStyleClass().add("sub-hb");
        
        lbidafiliadodist = new Label ("Cédula del afiliado: "); 
        tfidafiliadodist = new CampoLimitadoTexto (45); 
        HBox hbafiliadodist = new HBox (); 
        hbafiliadodist.getChildren().addAll(lbidafiliadodist,tfidafiliadodist);
        hbafiliadodist.getStyleClass().add("sub-hb");
        
        lbtelefonoafiliadodist = new Label ("Telefono: "); 
        tftelefonoafiliadodist = new CampoLimitadoTexto (45); 
        HBox hbtelefonoafiliadodist = new HBox(); 
        hbtelefonoafiliadodist.getChildren().addAll(lbtelefonoafiliadodist,tftelefonoafiliadodist);
        hbtelefonoafiliadodist.getStyleClass().add("sub-hb");
        
        lbnumsededist = new Label ("Sede: "); 
        cbnumsededist = new ChoiceBox(); 
        HBox hbnumsededist = new HBox (); 
        hbnumsededist.getChildren().addAll(lbnumsededist,cbnumsededist);
        hbnumsededist.getStyleClass().add("sub-hb");
        
        lbnumfabdist = new Label ("Fábrica: ");
        cbnumfabdist = new ChoiceBox (); 
        HBox hbnumfabdist = new HBox (); 
        hbnumfabdist.getChildren().addAll(lbnumfabdist,cbnumfabdist);
        hbnumfabdist.getStyleClass().add("sub-hb");
        
        btregdist = new Button ("Registrar"); 
        lbnotifregdist= new Label (); 
        
        VBox vbregistrardistri= new VBox (); 
        vbregistrardistri.getChildren().addAll(hbfechadist,hbnomcervezadist,hbcantidaddist,hbafiliadodist,
                hbtelefonoafiliadodist,hbnumsededist,hbnumfabdist,btregdist,lbnotifregdist);
        vbregistrardistri.getStyleClass().add("vb");
        tbregistrardistribucion.setContent(vbregistrardistri);
        
        tpRegistros.getTabs().addAll(tbregistrarproduccion,tbregistrardistribucion,tbregistrarafiliado, tbregistrarMateriaPrimaCerveza,
                tbregistraradquisicionmateriaprima,tbregistrarcomposicioncerveza); 
        
        
        Tab tbConsultas = new Tab("Consultas");
        TabPane tpConsultas = new TabPane();
        tpConsultas.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tbinventariomateriaprimacerveza = new Tab("Inventarios");
        //inventariomateriaprimaCerveza
        Label lbInvNumSede = new Label("Número de fábrica:");
        cbInvNumSede = new ChoiceBox();
        btnInventarioMateriaprimaCerveza = new Button("Obtener inventario de materias primas cervezas");
        HBox hbInvNumSede = new HBox(lbInvNumSede, cbInvNumSede, btnInventarioMateriaprimaCerveza);
        hbInvNumSede.getStyleClass().add("sub-hb");
        
        tvInventarioMateriaprimaCerveza = new TableView();
        tvInventarioMateriaprimaCerveza.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn<IngredienteCerveza, String> tcNombreIngCerveza = new TableColumn<>("Nombre");
        TableColumn<IngredienteCerveza, Double> tcCantidadIngCerveza = new TableColumn<>("Cantidad");
        tvInventarioMateriaprimaCerveza.getColumns().addAll(tcNombreIngCerveza, tcCantidadIngCerveza);
        tcNombreIngCerveza.setCellValueFactory(new PropertyValueFactory<>("nombreIngrediente"));
        tcCantidadIngCerveza.setCellValueFactory(new PropertyValueFactory<>("cantidadIngrediente"));
    
        VBox vbInventarioCerveza = new VBox(hbInvNumSede,  tvInventarioMateriaprimaCerveza);
        vbInventarioCerveza.getStyleClass().add("vb");
    
        tbinventariomateriaprimacerveza.setContent(vbInventarioCerveza);
        tbdistribucionessede = new Tab("Distribuciones por Sede");
        //
        Label lbInvNumSedeDistribuciones = new Label("Número de sede:");
        cbInvNumSedeDistribuciones = new ChoiceBox();
        btnDistribucionSede = new Button("Obtener Distribuciones de la sede");
        HBox hbInvNumSedeDis = new HBox(lbInvNumSedeDistribuciones, cbInvNumSedeDistribuciones, btnDistribucionSede);
        hbInvNumSedeDis.getStyleClass().add("sub-hb");
        
        tvDistribucionSede = new TableView();
        tvDistribucionSede.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn<Distribucion, String> tcNombreLicorDistribucion = new TableColumn<>("Nombre");
        TableColumn<Distribucion, Double> tcCantidadDistribuida = new TableColumn<>("Cantidad Distribuida");
        TableColumn<Distribucion, Integer> tcIdSede = new TableColumn<>("Sede");
        tvDistribucionSede.getColumns().addAll(tcNombreLicorDistribucion, tcCantidadDistribuida, tcIdSede);
        tcNombreLicorDistribucion.setCellValueFactory(new PropertyValueFactory<>("nombreCerveza"));
        tcCantidadDistribuida.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        tcIdSede.setCellValueFactory(new PropertyValueFactory<>("numSede"));
        
        VBox vbDistribucionSede = new VBox(hbInvNumSedeDis,  tvDistribucionSede);
        vbDistribucionSede.getStyleClass().add("vb");
        tbdistribucionessede.setContent(vbDistribucionSede);
        //
        distribucionescerveza = new Tab("Distribuciones por cerveza");
        //
        Label lbDisNombreCerveza = new Label("Nombre Cerveza:");
        cbnomcervezadistcerv = new ChoiceBox();
        btnNombreCerveza= new Button("Buscar");
        HBox hbDisNombreCerveza = new HBox(lbDisNombreCerveza, cbnomcervezadistcerv, btnNombreCerveza);
        hbDisNombreCerveza.getStyleClass().add("sub-hb");
        
        tvDistribucionCerveza = new TableView();
        tvDistribucionCerveza.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn<Distribucion, String> tcNombreCervezaDistribucion = new TableColumn<>("Nombre");
        TableColumn<Distribucion, Double> tcCantidadDistribuidaCerveza = new TableColumn<>("Cantidad Distribuida");
        TableColumn<Distribucion, String> tcIdSedeCerveza = new TableColumn<>("Sede");
        tvDistribucionCerveza.getColumns().addAll(tcNombreCervezaDistribucion, tcCantidadDistribuidaCerveza, tcIdSedeCerveza);
        tcNombreCervezaDistribucion.setCellValueFactory(new PropertyValueFactory<>("nombreCerveza"));
        tcCantidadDistribuidaCerveza .setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        tcIdSedeCerveza.setCellValueFactory(new PropertyValueFactory<>("numSede"));
        
        VBox vbDistribucionCerveza = new VBox(hbDisNombreCerveza,  tvDistribucionCerveza);
        vbDistribucionCerveza.getStyleClass().add("vb");
        //HBox hbDistribucionCerveza = new HBox(vbDistribucionCerveza);
        //hbDistribucionCerveza.getStyleClass().add("sub-hb");
        distribucionescerveza.setContent(vbDistribucionCerveza);



        //
        
        tpConsultas.getTabs().addAll(tbinventariomateriaprimacerveza, tbdistribucionessede, distribucionescerveza);
        VBox vbRegistros = new VBox(tpRegistros);
        tbRegistros.setContent(vbRegistros);
        VBox vbConsultas = new VBox(tpConsultas);
        tbConsultas.setContent(vbConsultas);
        tpPanel.getTabs().addAll(tbRegistros, tbConsultas);
        VBox vbPanel = new VBox(barraPrincipal, tpPanel);
    
        layoutPrincipal.getChildren().add(vbPanel);
        
        taNotificaciones = new TextFlow();
        taNotificaciones.getStyleClass().add("tf");
        taNotificaciones.setMaxHeight(100);
        layoutPrincipal.getChildren().add(taNotificaciones);
    }
    public MenuItem getItemCerrarSesion() {return itemCerrarSesion;}
    
    public TextFlow getTaNotificaciones() { return taNotificaciones; }
    
    public ChoiceBox getcbInvNumSede(){return cbInvNumSede;}
    public Button getbtnInventarioMateriaprimaCerveza(){return btnInventarioMateriaprimaCerveza ;}
    public ChoiceBox getcbInvNumSedeDistribuciones(){return cbInvNumSedeDistribuciones;}
    public Button getbtnDistribucionSede(){return btnDistribucionSede;}
    public Button getbtnNombreCerveza(){return btnNombreCerveza;}
    public TableView getTvInventarioMateriaprimaCerveza() { return tvInventarioMateriaprimaCerveza; }
    public TableView gettvDistribucionSede (){return tvDistribucionSede;} 
    public TableView gettvDistribucionCerveza (){return tvDistribucionCerveza;}
    
    
    public Tab getTbregistrarproduccion() {return tbregistrarproduccion;}
    public Label getLbfechapro() {return lbfechapro;}
    public DatePicker getDpfechapro() {return dpfechapro;}
    public Label getLbcantidadpro() {return lbcantidadpro;}
    public CampoLimitadoTexto getTfcantidadpro() {return tfcantidadpro;}
    public Label getLbnumfabricapro() {return lbnumfabricapro;}
    public ChoiceBox getCbnumfabricapro() {return cbnumfabricapro;}
    public Label getLbnomcervezapro() {return lbnomcervezapro;}
    public ChoiceBox getCbnomcervezapro() {return cbnomcervezapro;}
    public Button getBtregistrarproduccion() {return btregistrarproduccion;}
    public Label getLbnotifregproduccion() {return lbnotifregproduccion;}
    public Tab getTbregistrarafiliado() {return tbregistrarafiliado;}
    public Label getLbidafi() {return lbidafi;}
    public CampoLimitadoTexto getTfidafi() {return tfidafi;}
    public Label getLbtelefonoafi() {return lbtelefonoafi;}
    public CampoLimitadoTexto getTftelefonoafi() {return tftelefonoafi;}
    public Button getBtregistrarafiliado() {return btregistrarafiliado;}
    public Label getLbnotifregafiliado() {return lbnotifregafiliado;}
    public Tab getTbregistraradquisicionmateriaprima() {return tbregistraradquisicionmateriaprima;}
    public Label getLbnomdistribuidoraadq() {return lbnomdistribuidoraadq;}
    public ChoiceBox getCbnomdistribuidoraadq() {return cbnomdistribuidoraadq;}
    public Label getLbnommateriaadq() {return lbnommateriaadq;}
    public ChoiceBox getCbnommateriaadq() {return cbnommateriaadq;}
    public Label getLbprecioadq() {return lbprecioadq;}
    public CampoLimitadoTexto getTfprecioadq() {return tfprecioadq;}
    public Label getLbcantidadadq() {return lbcantidadadq;}
    public CampoLimitadoTexto getTfcantidadadq() {return tfcantidadadq;}
    public Label getLbfechaadq() {return lbfechaadq;}
    public DatePicker getDpfechaadq() {return dpfechaadq;}
    public Button getBtregistraradquisicion() {return btregistraradquisicion;}
    public Label getLbnotifregadquisicion() {return lbnotifregadquisicion;}
    public Tab getTbregistrarcomposicioncerveza() {return tbregistrarcomposicioncerveza;}
    public Label getLbnomcervezacomp() {return lbnomcervezacomp;}
    public ChoiceBox getCbnomcervezacomp() {return cbnomcervezacomp;}
    public Label getLbnommatcomp() {return lbnommatcomp;}
    public CampoLimitadoTexto getTfnommatcomp() {return tfnommatcomp;}
    public Label getLbcantidadcomp() {return lbcantidadcomp;}
    public CampoLimitadoTexto getTfcantidadcomp() {return tfcantidadcomp;}
    public Label getLbnumfabcomp() {return lbnumfabcomp;}
    public ChoiceBox getCbnumfabcomp() {return cbnumfabcomp;}
    public Button getBtregistrarcomposicion() {return btregistrarcomposicion;}
    public Label getLbnotifregcomposicion() {return lbnotifregcomposicion;}
    public Tab getTbregistrardistribucion() {return tbregistrardistribucion;}
    public Label getLbfechadist() {return lbfechadist;}
    public DatePicker getDpfechadist() {return dpfechadist;}
    public Label getLbcantidaddist() {return lbcantidaddist;}
    public CampoLimitadoTexto getTfcantidaddist() {return tfcantidaddist;}
    public Label getLbnomcervezadist() {return lbnomcervezadist;}
    public ChoiceBox getCbnomcervezadist() {return cbnomcervezadist;}
    public Label getLbidafiliadodist() {return lbidafiliadodist;}
    public CampoLimitadoTexto getTfidafiliadodist() {return tfidafiliadodist;}
    public Label getLbtelefonoafiliadodist() {return lbtelefonoafiliadodist;}
    public CampoLimitadoTexto getTftelefonoafiliadodist() {return tftelefonoafiliadodist;}
    public Label getLbnumsededist() {return lbnumsededist; }
    public ChoiceBox getCbnumsededist() {return cbnumsededist;}
    public Label getLbnumfabdist() {return lbnumfabdist;}
    public ChoiceBox getCbnumfabdist() {return cbnumfabdist;}
    public Button getBtregdist() {return btregdist;}
    public Label getLbnotifregdist() {return lbnotifregdist;}
    public Tab getTbinventariomateriaprimacerveza() {return tbinventariomateriaprimacerveza;}
    public Label getLbnumfabinv() {return lbnumfabinv;}
    public Tab getTbdistribucionessede() {return tbdistribucionessede;}
    public Label getLbnumsededistsede() {return lbnumsededistsede;}
    public Tab getDistribucionescerveza() {return distribucionescerveza;}
    public Label getLbnomcervezadistcerv() {return lbnomcervezadistcerv;}
    public ChoiceBox getCbnomcervezadistcerv() {return cbnomcervezadistcerv;}
    public Tab getTbregistrarMateriaPrimaCerveza(){return tbregistrarMateriaPrimaCerveza;}
    public CampoLimitadoTexto getTfnombreMatPrimaCer() {return tfnombreMatPrimaCer;}
    public ChoiceBox getCbnumfabmatprimacerv() { return cbnumfabmatprimacerv;}
    public Button getBtregmatprimcerv() { return btregmatprimcerv;}
}
