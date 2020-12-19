package amnesiabar.vista;

import amnesiabar.modelo.AdquisicionLicor;
import amnesiabar.modelo.Compra;
import amnesiabar.modelo.IngredienteComida;
import amnesiabar.modelo.LicorSede;
import amnesiabar.modelo.Producto;
import amnesiabar.utilidad.CampoLimitadoTexto;
import java.sql.Date;
import java.time.LocalDate;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Jonathan López
 */
public class VistaSede extends Vista{
    private MenuItem itemCerrarSesion;
    
    private Tab tbRegistroDistribuidora;
    private CampoLimitadoTexto tfRegNombreDist;
    private CampoLimitadoTexto tfRegTelDist;
    private Button btnRegistrarDistribuidora;
    private Label lbNotifRegDist;
    
    private Tab tbRegistroProductos;
    private CampoLimitadoTexto tfRegNombProd;
    private CampoLimitadoTexto tfRegPrecioProd;
    private ChoiceBox cbRegSedeProd;
    private CampoLimitadoTexto tfRegPresLic;
    private CampoLimitadoTexto tfRegTipoCerv;
    private ChoiceBox cbRegIdFabCerv;
    private Label lbNotifRegPro;
    private Button btnRegistroComida;
    private Button btnRegistroLicor;
    private Button btnRegistroCervezaArt;
    
    private Tab tbRegistroMateriaPrima;
    private CampoLimitadoTexto tfRegNombMatPrima;
    private ChoiceBox cbRegNumSedeMatPrima;
    private Label lbNotifRegMatPrima;
    private Button btnRegMatPrimaComida;
    
    private Tab tbRegistroAdquisiciones;
    private ChoiceBox cbNombDistribuidora;
    private CampoLimitadoTexto tfPrecioCompra;
    private CampoLimitadoTexto tfCantidadCompra;
    private DatePicker dpFechaCompra;
    private ChoiceBox cbNombMatPrima;
    private ChoiceBox cbNombLicor;
    private Button btnAdqMateriaPrima;
    private Button btnAdqLicor;
    private Label lbNotifAdquisicion;
    
    private Tab tbRegistroComposicionComida;
    private ChoiceBox cbNombComida;
    private ChoiceBox cbNomMateriaPrima;
    private CampoLimitadoTexto tfCantMat;
    private ChoiceBox cbNumSedMatPrima;
    private Button btnRegComposicion;
    private Label lbNotifComposicion;
    
    private Tab tbRegistroHorasTrabajo;
    private ChoiceBox cbCedulaEmpleado;
    private CampoLimitadoTexto tfHorasEmpleado;
    private Button btnHorasEmpleado;
    private Label lbNotifHorasEmpleado;
    
    private Tab tbRegistroVenta;
    private ChoiceBox cbCedCliente;
    private ChoiceBox cbNumSedeVenta;
    private DatePicker dpFechaVenta;
    private CampoLimitadoTexto tfNumProducto;
    private ChoiceBox cbNumVenta;
    private ChoiceBox cbProductoVenta;
    private CampoLimitadoTexto tfCantidadVenta;
    private Button btnRegistrarVenta;
    private Button btnRegistrarParteVenta;
    private Button btnBorrarParteVenta;
    private Label lbNotifVenta;

    private CampoLimitadoTexto tfDescuento;
    private Button btnDescuento;
    
    private Tab tbInventarios;
    private ChoiceBox cbInvNumSede;
    private Button btnInventarioLicor;
    private TableView tvInventarioLicor;
    private Button btnInventarioMateria;
    private TableView tvInventarioMateria;
    
    private Tab tbConsultaCompras;
    private ChoiceBox cbComprasProducto;
    private Button btnComprasProducto;
    private TableView tvComprasProducto;
    private Button btnComprasTotales;
    private TableView tvComprasTotales;
    
    private Tab tbConsultaGastos;
    private DatePicker dpFechaInicio;
    private DatePicker dpFechaFinal;
    private Button btnGastosLicores;
    private TableView tvGastosLicores;
    private Button btnComprasIngredientes;
    private TableView tvComprasIngredientes;
    private Button btnGastosIngredientes;
    private TableView tvGastosIngredientes;
    
    
    
    public VistaSede(){
        super();
        inicializar();
    }
    
    public VistaSede(double ancho, double alto){
        super(ancho, alto);
        inicializar();
    }
    
    @Override
    protected void inicializar() {
        VBox layoutPrincipal = new VBox();
        escena = new Scene(layoutPrincipal, getAncho(), getAlto());
        escena.getStylesheets().add("amnesiabar/estilos/estilosSede.css");
        
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
        
        tbRegistroDistribuidora = new Tab ("Proveedores");
        
        Label lbDist = new Label("Datos proveedor:");
        
        Label lbRegNombreDist = new Label("Nombre:");
        tfRegNombreDist = new CampoLimitadoTexto(45);
        HBox hbRegNombreDist = new HBox(lbRegNombreDist, tfRegNombreDist);
        hbRegNombreDist.getStyleClass().add("sub-hb");
        
        Label lbRegTelDist = new Label("Teléfono:");
        tfRegTelDist = new CampoLimitadoTexto(15);
        HBox hbRegTelExt = new HBox(lbRegTelDist, tfRegTelDist);
        hbRegTelExt.getStyleClass().add("sub-hb");
        
        btnRegistrarDistribuidora = new Button("Registrar");
        
        lbNotifRegDist = new Label();
        
        VBox vbRegistrarDistribuidora = new VBox();
        vbRegistrarDistribuidora.getChildren().addAll(lbDist, hbRegNombreDist, hbRegTelExt, lbNotifRegDist, btnRegistrarDistribuidora);
        vbRegistrarDistribuidora.getStyleClass().add("vb");
        
        tbRegistroDistribuidora.setContent(vbRegistrarDistribuidora);
        
        tbRegistroProductos = new Tab("Productos");
        
        Label lbRegNombProd = new Label("(*)(/)Nombre: ");
        tfRegNombProd = new CampoLimitadoTexto(30);
        HBox hbRegNombProd = new HBox(lbRegNombProd, tfRegNombProd);
        hbRegNombProd.getStyleClass().add("sub-hb");
        
        
        Label lbRegPrecioProd = new Label("(*)(/)Precio: ");
        tfRegPrecioProd = new CampoLimitadoTexto(10);
        HBox hbRegPrecioProd = new HBox(lbRegPrecioProd, tfRegPrecioProd);
        hbRegPrecioProd.getStyleClass().add("sub-hb");
        
        Label lbRegSedeProd = new Label("(*)(/)Número de sede: ");
        cbRegSedeProd = new ChoiceBox();
        HBox hbRegSedeProd = new HBox(lbRegSedeProd, cbRegSedeProd);
        hbRegSedeProd.getStyleClass().add("sub-hb");
        
        
        Label lbRegPresLic = new Label("(/)Presentación del licor(ml):");
        tfRegPresLic = new CampoLimitadoTexto(5);
        HBox hbRegPresLic = new HBox(lbRegPresLic, tfRegPresLic);
        hbRegPresLic.getStyleClass().add("sub-hb");
        
        Label lbInfoCerveza = new Label("Datos de la cerveza: ");
        
        Label lbRegTipoCerv = new Label("Tipo:");
        tfRegTipoCerv = new CampoLimitadoTexto(30);
        HBox hbRegTipoCerv = new HBox(lbRegTipoCerv, tfRegTipoCerv);
        hbRegTipoCerv.getStyleClass().add("sub-hb");
        
        Label lbRegIdFabCerv = new Label("Número de fábrica:");
        cbRegIdFabCerv = new ChoiceBox();
        HBox hbRegIdFabCerv = new HBox(lbRegIdFabCerv, cbRegIdFabCerv);
        hbRegIdFabCerv.getStyleClass().add("sub-hb");
        
        lbNotifRegPro = new Label();
        
        btnRegistroComida = new Button("Registrar comida");
        btnRegistroLicor = new Button("Registrar licor de marca");
        btnRegistroCervezaArt = new Button("Registrar cerveza artesanal");
        HBox hbBtnRegProducto = new HBox(btnRegistroComida, btnRegistroLicor, btnRegistroCervezaArt);
        hbBtnRegProducto.getStyleClass().add("sub-hb");
        
        VBox vbRegProducto = new VBox(hbRegNombProd, hbRegPrecioProd, hbRegSedeProd, hbRegPresLic, lbInfoCerveza, 
                                      hbRegTipoCerv, hbRegIdFabCerv, lbNotifRegPro, hbBtnRegProducto);
        vbRegProducto.getStyleClass().add("vb");
        tbRegistroProductos.setContent(vbRegProducto);
        
        tbRegistroMateriaPrima = new Tab("Ingredientes");
        
        Label lbRegNombMatPrima = new Label("Nombre del ingrediente:");
        tfRegNombMatPrima = new CampoLimitadoTexto(30);
        HBox hbRegNombMatPrima = new HBox(lbRegNombMatPrima, tfRegNombMatPrima);
        hbRegNombMatPrima.getStyleClass().add("sub-hb");
        
        Label lbRegNumSedeMatPrima = new Label("Número de sede:");
        cbRegNumSedeMatPrima = new ChoiceBox();
        HBox hbRegNumSedeMatPrima = new HBox(lbRegNumSedeMatPrima, cbRegNumSedeMatPrima);
        hbRegNumSedeMatPrima.getStyleClass().add("sub-hb");
        
        lbNotifRegMatPrima = new Label();
        
        btnRegMatPrimaComida = new Button("Registrar ingrediente");
        HBox hbBtnRegMatPrima = new HBox(btnRegMatPrimaComida);
        
        VBox vbRegistroMateriaPrima = new VBox(hbRegNombMatPrima, hbRegNumSedeMatPrima,
                                               lbNotifRegMatPrima, hbBtnRegMatPrima);
        vbRegistroMateriaPrima.getStyleClass().add("vb");
        
        tbRegistroMateriaPrima.setContent(vbRegistroMateriaPrima);
        
        tbRegistroAdquisiciones = new Tab("Adquisiciones");
        
        Label lbNombDistribuidora = new Label("Nombre de la distribuidora:");
        cbNombDistribuidora = new ChoiceBox();
        HBox hbNombDistribuidora = new HBox(lbNombDistribuidora, cbNombDistribuidora);
        hbNombDistribuidora.getStyleClass().add("sub-hb");
        
        Label lbPrecio = new Label("Precio:");
        tfPrecioCompra = new CampoLimitadoTexto(15);
        HBox hbPrecioCompra = new HBox(lbPrecio, tfPrecioCompra);
        hbPrecioCompra.getStyleClass().add("sub-hb");
        
        Label lbCantidadCompra = new Label("Cantidad:");
        tfCantidadCompra = new CampoLimitadoTexto(10);
        HBox hbCantidadCompra = new HBox(lbCantidadCompra, tfCantidadCompra);
        hbCantidadCompra.getStyleClass().add("sub-hb");
        
        Label lbFechaCompra = new Label("Fecha de la compra:");
        dpFechaCompra = new DatePicker(LocalDate.now());
        dpFechaCompra.setEditable(false);
        HBox hbFechaCompra = new HBox(lbFechaCompra, dpFechaCompra);
        hbFechaCompra.getStyleClass().add("sub-hb");
        
        Label lbNombMatPrima = new Label("Ingrediente:");
        cbNombMatPrima = new ChoiceBox();
        HBox hbNombMatPrima = new HBox(lbNombMatPrima, cbNombMatPrima);
        hbNombMatPrima.getStyleClass().add("sub-hb");
        
        Label lbNombreLicor = new Label("Nombre del licor:");
        cbNombLicor = new ChoiceBox();
        HBox hbNombreLicor = new HBox(lbNombreLicor, cbNombLicor);
        hbNombreLicor.getStyleClass().add("sub-hb");
        
        btnAdqMateriaPrima = new Button("Adquisicion de ingrediente");
        btnAdqLicor = new Button("Adquisicion de licor");
        
        lbNotifAdquisicion = new Label("");
        
        VBox vbRegistroAdquisicion = new VBox(hbNombDistribuidora, hbNombMatPrima, hbPrecioCompra,
                        hbCantidadCompra, hbFechaCompra, hbNombreLicor, lbNotifAdquisicion, btnAdqMateriaPrima, btnAdqLicor);
        vbRegistroAdquisicion.getStyleClass().add("vb");
        tbRegistroAdquisiciones.setContent(vbRegistroAdquisicion);
        
        tbRegistroComposicionComida = new Tab("Ingredientes por comida");
        
        Label lbNombreComida = new Label("Comida:");
        cbNombComida = new ChoiceBox();
        HBox hbNombComida = new HBox(lbNombreComida, cbNombComida);
        hbNombComida.getStyleClass().add("sub-hb");
        
        Label lbNombreMateriaPrima = new Label("Ingrediente:");
        cbNomMateriaPrima = new ChoiceBox();
        HBox hbNombMateriaPrima = new HBox(lbNombreMateriaPrima, cbNomMateriaPrima);
        hbNombMateriaPrima.getStyleClass().add("sub-hb");
        
        Label lbCantMat = new Label("Cantidad de ingrediente requerida:");
        tfCantMat = new CampoLimitadoTexto(10);
        HBox hbCantMat = new HBox(lbCantMat, tfCantMat);
        hbCantMat.getStyleClass().add("sub-hb");
        
        Label lbIdSede = new Label("Numero de sede:");
        cbNumSedMatPrima = new ChoiceBox();
        HBox hbIdSede = new HBox(lbIdSede, cbNumSedMatPrima);
        hbIdSede.getStyleClass().add("sub-hb");
        
        btnRegComposicion = new Button("Registrar");
        lbNotifComposicion = new Label("");
        
        VBox vbRegistroComposicion = new VBox(hbNombComida, hbNombMateriaPrima, hbCantMat, hbIdSede, lbNotifComposicion,
                btnRegComposicion );
        vbRegistroComposicion.getStyleClass().add("vb");
        
        tbRegistroComposicionComida.setContent(vbRegistroComposicion);
        
        tbRegistroHorasTrabajo = new Tab("Horas de trabajo empleados");
        
        Label lbCedulaEmpleado = new Label("Numero de cedula:");
        cbCedulaEmpleado = new ChoiceBox();
        HBox hbCedulaEmpleado = new HBox(lbCedulaEmpleado, cbCedulaEmpleado);
        hbCedulaEmpleado.getStyleClass().add("sub-hb");
        
        Label lbHorasEmpleado = new Label("Horas trabajadas:");
        tfHorasEmpleado = new CampoLimitadoTexto(4);
        HBox hbHorasEmpleado = new HBox(lbHorasEmpleado, tfHorasEmpleado);
        hbHorasEmpleado.getStyleClass().add("sub-hb");
        
        btnHorasEmpleado = new Button("Registro");
        lbNotifHorasEmpleado = new Label("");
        
        VBox vbHorasEmpleado = new VBox(hbCedulaEmpleado, hbHorasEmpleado, lbNotifHorasEmpleado, btnHorasEmpleado);
        vbHorasEmpleado.getStyleClass().add("vb");
        
        tbRegistroHorasTrabajo.setContent(vbHorasEmpleado);
        
        tbRegistroVenta = new Tab("Compras");
        
        Label lbCedCliente = new Label("(*)Cédula del cliente:");
        cbCedCliente = new ChoiceBox();
        HBox hbCedCliente = new HBox(lbCedCliente, cbCedCliente);
        hbCedCliente.getStyleClass().add("sub-hb");
        
        Label lbNumSedeVenta = new Label("(*)Número de sede:");
        cbNumSedeVenta = new ChoiceBox();
        HBox hbNumSedeVenta = new HBox(lbNumSedeVenta, cbNumSedeVenta);
        hbNumSedeVenta.getStyleClass().add("sub-hb");
        
        Label lbFechaVenta = new Label("(*)Fecha:");
        dpFechaVenta = new DatePicker(LocalDate.now());
        dpFechaVenta.setEditable(false);
        HBox hbFechaVenta = new HBox(lbFechaVenta, dpFechaVenta);
        hbFechaVenta.getStyleClass().add("sub-hb");
        
        Label lbNumProducto = new Label("(/)Número de ítem:");
        tfNumProducto = new CampoLimitadoTexto(3);
        HBox hbNumProducto = new HBox(lbNumProducto, tfNumProducto);
        hbNumProducto.getStyleClass().add("sub-hb");
        
        Label lbNumVenta = new Label("(/)Id de la compra:");
        cbNumVenta = new ChoiceBox();
        HBox hbNumVenta = new HBox(lbNumVenta, cbNumVenta);
        hbNumVenta.getStyleClass().add("sub-hb");
        
        Label lbProductoVenta = new Label("(/)Seleccione el producto:");
        cbProductoVenta = new ChoiceBox();
        HBox hbProductoVenta = new HBox(lbProductoVenta, cbProductoVenta);
        hbProductoVenta.getStyleClass().add("sub-hb");
        
        Label lbCantidadVenta = new Label("(/)Cantidad:");
        tfCantidadVenta = new CampoLimitadoTexto(4);
        HBox hbCantidadVenta = new HBox(lbCantidadVenta, tfCantidadVenta);
        hbCantidadVenta.getStyleClass().add("sub-hb");
        
        btnRegistrarVenta = new Button("Registrar compra");
        btnRegistrarParteVenta = new Button("Registrar ítem");
        btnBorrarParteVenta = new Button("Borrar ítem");
        HBox hbBotonesVenta = new HBox(btnRegistrarVenta, btnRegistrarParteVenta, btnBorrarParteVenta);
        hbBotonesVenta.getStyleClass().add("sub-hb");
        
        lbNotifVenta = new Label("");
        
        Label lbDescuento = new Label("Porcentaje de descuento:");
        tfDescuento = new CampoLimitadoTexto(5);
        HBox hbDescuento = new HBox(lbDescuento, tfDescuento);
        hbDescuento.getStyleClass().add("sub-hb");
        
        btnDescuento = new Button("Aplicar descuento");
        
        VBox vbVenta = new VBox(hbCedCliente, hbNumSedeVenta, hbFechaVenta, hbNumProducto, hbNumVenta,
                                hbProductoVenta, hbCantidadVenta, hbBotonesVenta, lbNotifVenta,
                                hbDescuento, btnDescuento);
        vbVenta.getStyleClass().add("vb");
        
        tbRegistroVenta.setContent(vbVenta);
        
        tpRegistros.getTabs().addAll(tbRegistroDistribuidora, tbRegistroProductos, tbRegistroMateriaPrima,
                tbRegistroComposicionComida, tbRegistroAdquisiciones, tbRegistroHorasTrabajo, tbRegistroVenta);
        
        Tab tbConsultas = new Tab("Consultas");
        
        TabPane tpConsultas = new TabPane();
        tpConsultas.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        
        tbInventarios = new Tab("Inventarios");
        
        Label lbInvNumSede = new Label("Número de sede:");
        cbInvNumSede = new ChoiceBox();
        btnInventarioLicor = new Button("Obtener inventario de licores");
        HBox hbInvNumSede = new HBox(lbInvNumSede, cbInvNumSede, btnInventarioLicor);
        hbInvNumSede.getStyleClass().addAll("sub-hb", "sub-hb-centro");
        
        tvInventarioLicor = new TableView();
        tvInventarioLicor.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn<LicorSede, String> tcNombreLicor = new TableColumn<>("Nombre");
        TableColumn<LicorSede, Double> tcPrecioLicor = new TableColumn<>("Precio");
        TableColumn<LicorSede, Double> tcPresentacionLicor = new TableColumn<>("Presentación(ml)");
        TableColumn<LicorSede, Double> tcCantidadLicor = new TableColumn<>("Cantidad disponible");
        tvInventarioLicor.getColumns().addAll(tcNombreLicor, tcPrecioLicor, tcPresentacionLicor, tcCantidadLicor);
        tcNombreLicor.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcPrecioLicor.setCellValueFactory(new PropertyValueFactory<>("precio"));
        tcPresentacionLicor.setCellValueFactory(new PropertyValueFactory<>("presentacion"));
        tcCantidadLicor.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        
        btnInventarioMateria = new Button("Obtener inventario de ingredientes de la comida");
        
        tvInventarioMateria = new TableView();
        tvInventarioMateria.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn<IngredienteComida, String> tcNombreIngrediente = new TableColumn<>("Nombre");
        TableColumn<IngredienteComida, Double> tcCantidadIngrediente = new TableColumn<>("Cantidad");
        tvInventarioMateria.getColumns().addAll(tcNombreIngrediente, tcCantidadIngrediente);
        tcNombreIngrediente.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcCantidadIngrediente.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        
        VBox vbInventarioLicor = new VBox(hbInvNumSede,  tvInventarioLicor, btnInventarioMateria, tvInventarioMateria);
        vbInventarioLicor.getStyleClass().add("vb");
        
        tbInventarios.setContent(vbInventarioLicor);
        
        tbConsultaCompras = new Tab("Compras");
        
        Label lbComprasProducto = new Label("Nombre del producto:");
        cbComprasProducto = new ChoiceBox();
        btnComprasProducto = new Button("Consultar el total de las compras del producto");
        HBox hbComprasProducto = new HBox(lbComprasProducto, cbComprasProducto, btnComprasProducto);
        hbComprasProducto.getStyleClass().addAll("sub-hb", "sub-hb-centro");
        
        tvComprasProducto = new TableView();
        tvComprasProducto.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn<Producto, String> tcNombreProducto = new TableColumn<>("Nombre");
        TableColumn<Producto, Double> tcCantidadProducto = new TableColumn<>("Cantidad vendida");
        TableColumn<Producto, Double> tcMontoProducto = new TableColumn<>("Total recaudado");
        tvComprasProducto.getColumns().addAll(tcNombreProducto, tcCantidadProducto, tcMontoProducto);
        tcNombreProducto.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcCantidadProducto.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        tcMontoProducto.setCellValueFactory(new PropertyValueFactory<>("montoTotal"));
        
        btnComprasTotales = new Button("Consultar las compras");
        
        tvComprasTotales = new TableView();
        tvComprasTotales.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn<Compra, Date> tcFechaCompra = new TableColumn<>("Fecha de compra");
        TableColumn<Compra, Integer> tcIdCompra = new TableColumn<>("ID");
        TableColumn<Compra, Integer> tcSedeCompra = new TableColumn<>("Sede");
        TableColumn<Compra, Integer> tcCedulaClienteCompra = new TableColumn<>("C.C. Cliente");
        TableColumn<Compra, Integer> tcItemCompra = new TableColumn<>("#");
        TableColumn<Compra, String> tcProductoCompra = new TableColumn<>("Producto");
        TableColumn<Compra, Double> tcCantidadCompra = new TableColumn<>("Cantidad");
        TableColumn<Compra, Double> tcPrecioProductoCompra = new TableColumn<>("Precio producto");
        TableColumn<Compra, Double> tcPrecioCompra = new TableColumn<>("Total compra");
        tvComprasTotales.getColumns().addAll(tcFechaCompra, tcIdCompra, tcSedeCompra, tcCedulaClienteCompra,
                tcItemCompra, tcProductoCompra, tcCantidadCompra, tcPrecioProductoCompra, tcPrecioCompra);
        tcFechaCompra.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tcIdCompra.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcSedeCompra.setCellValueFactory(new PropertyValueFactory<>("sede"));
        tcCedulaClienteCompra.setCellValueFactory(new PropertyValueFactory<>("cedulaCliente"));
        tcItemCompra.setCellValueFactory(new PropertyValueFactory<>("item"));
        tcProductoCompra.setCellValueFactory(new PropertyValueFactory<>("producto"));
        tcCantidadCompra.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        tcPrecioProductoCompra.setCellValueFactory(new PropertyValueFactory<>("precioProducto"));
        tcPrecioCompra.setCellValueFactory(new PropertyValueFactory<>("precio"));
        
        VBox vbConsultaCompras = new VBox(hbComprasProducto, tvComprasProducto, btnComprasTotales, tvComprasTotales);
        vbConsultaCompras.getStyleClass().add("vb");
        
        tbConsultaCompras.setContent(vbConsultaCompras);
        
        tbConsultaGastos = new Tab("Gastos");     
        
        Label lbFechaInicio = new Label("Inicio: ");
        dpFechaInicio = new DatePicker(LocalDate.now());
        dpFechaInicio.setEditable(false);
        Label lbFechaLimite = new Label("Fin: ");
        dpFechaFinal = new DatePicker(LocalDate.now());
        dpFechaFinal.setEditable(false);
        HBox hbFecha = new HBox(lbFechaInicio, dpFechaInicio, lbFechaLimite, dpFechaFinal);
        hbFecha.getStyleClass().addAll("sub-hb", "sub-hb-centro");
        
        btnGastosLicores = new Button("Consultar adquisiciones de licores");
        
        tvGastosLicores = new TableView();
        tvGastosLicores.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn<AdquisicionLicor, String> tcNombreLicorAdq = new TableColumn<>("Nombre del licor");
        TableColumn<AdquisicionLicor, String> tcNombreProveedorAdq = new TableColumn<>("Proveedor");
        TableColumn<AdquisicionLicor, Double> tcPrecioLicorAdq = new TableColumn<>("Precio de compra");
        TableColumn<AdquisicionLicor, Integer> tcCantidadLicorAdq = new TableColumn<>("Cantidad");
        TableColumn<AdquisicionLicor, Date> tcFechaLicorAdq = new TableColumn<>("Fecha de compra");
        tvGastosLicores.getColumns().addAll(tcNombreLicorAdq, tcNombreProveedorAdq, tcPrecioLicorAdq,
                    tcCantidadLicorAdq, tcFechaLicorAdq);
        tcNombreLicorAdq.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcNombreProveedorAdq.setCellValueFactory(new PropertyValueFactory<>("proveedor"));
        tcPrecioLicorAdq.setCellValueFactory(new PropertyValueFactory<>("precio"));
        tcCantidadLicorAdq.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        tcFechaLicorAdq.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        
        btnComprasIngredientes = new Button("Consultar adquisiciones de ingredientes");
        
        tvComprasIngredientes = new TableView();
        tvComprasIngredientes.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn<AdquisicionLicor, String> tcIngredienteNombre = new TableColumn<>("Nombre del ingrediente");
        TableColumn<AdquisicionLicor, String> tcIngredienteProveedor = new TableColumn<>("Proveedor");
        TableColumn<AdquisicionLicor, Double> tcIngredientePrecio = new TableColumn<>("Precio de compra");
        TableColumn<AdquisicionLicor, Double> tcIngredienteCantidad = new TableColumn<>("Cantidad");
        TableColumn<AdquisicionLicor, Date> tcIngredienteFecha = new TableColumn<>("Fecha de compra");
        tvComprasIngredientes.getColumns().addAll(tcIngredienteNombre, tcIngredienteProveedor, tcIngredientePrecio,
                    tcIngredienteCantidad, tcIngredienteFecha);
        tcIngredienteNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcIngredienteProveedor.setCellValueFactory(new PropertyValueFactory<>("proveedor"));
        tcIngredientePrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        tcIngredienteCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        tcIngredienteFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        
        btnGastosIngredientes = new Button("Consultar gasto de ingredientes");
        
        tvGastosIngredientes = new TableView();
        tvGastosIngredientes.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn<AdquisicionLicor, String> tcGastIngNombre = new TableColumn<>("Nombre del ingrediente");
        TableColumn<AdquisicionLicor, Double> tcGastIngCantidad = new TableColumn<>("Cantidad gastada");
        TableColumn<AdquisicionLicor, Date> tcGastIngFecha = new TableColumn<>("Fecha");
        tvGastosIngredientes.getColumns().addAll(tcGastIngNombre, tcGastIngCantidad, tcGastIngFecha);
        tcGastIngNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcGastIngCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        tcGastIngFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        
        VBox vbConsultaGastos = new VBox(hbFecha, btnGastosLicores, tvGastosLicores, btnComprasIngredientes,
            tvComprasIngredientes, btnGastosIngredientes, tvGastosIngredientes);
        vbConsultaGastos.getStyleClass().add("vb");
        
        tbConsultaGastos.setContent(vbConsultaGastos);
        
        tpConsultas.getTabs().addAll(tbInventarios, tbConsultaCompras, tbConsultaGastos);
        
        VBox vbRegistros = new VBox(tpRegistros);
        tbRegistros.setContent(vbRegistros);
        VBox vbConsultas = new VBox(tpConsultas);
        tbConsultas.setContent(vbConsultas);
        tpPanel.getTabs().addAll(tbRegistros, tbConsultas);
        VBox vbPanel = new VBox(barraPrincipal, tpPanel);
        
        layoutPrincipal.getChildren().add(vbPanel);
        
        
        
    }
    
    public MenuItem getItemCerrarSesion() { return itemCerrarSesion; }
    
    public Tab getTbRegistroDistribuidora() { return tbRegistroDistribuidora; }
    public CampoLimitadoTexto getTfRegNombreDist() { return tfRegNombreDist; }
    public CampoLimitadoTexto getTfRegTelDist() { return tfRegTelDist; }
    public Button getBtnRegistrarDistribuidora() { return btnRegistrarDistribuidora; }
    public Label getLbNotifRegDist() { return lbNotifRegDist; }
    
    public Tab getTbRegistroProductos() { return tbRegistroProductos; }
    public CampoLimitadoTexto getTfRegNombProd() { return tfRegNombProd; }
    public CampoLimitadoTexto getTfRegPrecioProd() { return tfRegPrecioProd; }
    public ChoiceBox getCbRegSedeProd() { return cbRegSedeProd; }
    public CampoLimitadoTexto getTfRegPresLic() { return tfRegPresLic; }
    public CampoLimitadoTexto getTfRegTipoCerv() { return tfRegTipoCerv; }
    public ChoiceBox getCbRegIdFabCerv() { return cbRegIdFabCerv; }
    public Label getLbNotifRegPro() { return lbNotifRegPro; }
    public Button getBtnRegistroComida() { return btnRegistroComida; }
    public Button getBtnRegistroLicor() { return btnRegistroLicor; }
    public Button getBtnRegistroCervezaArt() { return btnRegistroCervezaArt; }
    
    public Tab getTbRegistroMateriaPrima (){ return tbRegistroMateriaPrima; }
    public CampoLimitadoTexto getTfRegNombMatPrima() { return tfRegNombMatPrima; }
    public ChoiceBox getCbRegNumSedeMatPrima() { return cbRegNumSedeMatPrima; }
    public Label getLbNotifRegMatPrima() { return lbNotifRegMatPrima; }
    public Button getBtnRegMatPrimaComida() { return btnRegMatPrimaComida; }
    
    public Tab getTbRegistroAdquisiciones() { return tbRegistroAdquisiciones; }
    public ChoiceBox getCbNombDistribuidora() { return cbNombDistribuidora; }
    public CampoLimitadoTexto getTfPrecioCompra() { return tfPrecioCompra; }
    public CampoLimitadoTexto getTfCantidadCompra() { return tfCantidadCompra; }
    public DatePicker getDpFechaCompra() { return dpFechaCompra; }
    public ChoiceBox getCbNombMatPrima() { return cbNombMatPrima; }
    public ChoiceBox getCbNombLicor() { return cbNombLicor; }
    public Button getBtnAdqMateriaPrima() { return btnAdqMateriaPrima; }
    public Button getBtnAdqLicor() { return btnAdqLicor; }
    public Label getLbNotifAdquisicion() { return lbNotifAdquisicion; }
    
    public Tab getTbRegistroComposicionComida() { return tbRegistroComposicionComida; }
    public ChoiceBox getCbNombComida() { return cbNombComida; }
    public ChoiceBox getCbNomMateriaPrima() { return cbNomMateriaPrima; }
    public CampoLimitadoTexto getTfCantMat() { return tfCantMat; }
    public ChoiceBox getCbNumSedMatPrima() { return cbNumSedMatPrima; }
    public Button getBtnRegComposicion() { return btnRegComposicion; }
    public Label getLbNotifComposicion() { return lbNotifComposicion; }
    
    public Tab getTbRegistroHorasTrabajo() { return tbRegistroHorasTrabajo; }
    public ChoiceBox getCbCedulaEmpleado() { return cbCedulaEmpleado; }
    public CampoLimitadoTexto getTfHorasEmpleado() { return tfHorasEmpleado; }
    public Button getBtnHorasEmpleado() { return btnHorasEmpleado; }
    public Label getLbNotifHorasEmpleado() { return lbNotifHorasEmpleado; }
    
    public Tab getTbRegistroVenta() { return tbRegistroVenta; }
    public ChoiceBox getCbCedCliente() { return cbCedCliente; }
    public ChoiceBox getCbNumSedeVenta() { return cbNumSedeVenta; }
    public DatePicker getDpFechaVenta() { return dpFechaVenta; }
    public CampoLimitadoTexto getTfNumProducto() { return tfNumProducto; }
    public ChoiceBox getCbNumVenta() { return cbNumVenta; }
    public ChoiceBox getCbProductoVenta() { return cbProductoVenta; }
    public CampoLimitadoTexto getTfCantidadVenta() { return tfCantidadVenta; }
    public Button getBtnRegistrarVenta() { return btnRegistrarVenta; }
    public Button getBtnRegistrarParteVenta() { return btnRegistrarParteVenta; }
    public Button getBtnBorrarParteVenta() { return btnBorrarParteVenta; }
    public Label getLbNotifVenta() { return lbNotifVenta; }
    public CampoLimitadoTexto getTfDescuento() { return tfDescuento; }
    public Button getBtnDescuento() { return btnDescuento; }
    
    public Tab getTbInventarios() { return tbInventarios; }
    public ChoiceBox getCbInvNumSede() { return cbInvNumSede; }
    public Button getBtnInventarioLicor() { return btnInventarioLicor; }
    public TableView getTvInventarioLicor() { return tvInventarioLicor; }
    public Button getBtnInventarioMateria() { return btnInventarioMateria; }
    public TableView getTvInventarioMateria() { return tvInventarioMateria; }
    
    public Tab getTbConsultaCompras() { return tbConsultaCompras; }
    public ChoiceBox getCbComprasProducto() { return cbComprasProducto; }
    public Button getBtnComprasProducto() { return btnComprasProducto; }
    public TableView getTvComprasProducto() { return tvComprasProducto; }
    public Button getBtnComprasTotales() { return btnComprasTotales; }
    public TableView getTvComprasTotales() { return tvComprasTotales; }
    
    public Tab getTbConsultaGastos() { return tbConsultaGastos; }
    public DatePicker getDpFechaInicio() { return dpFechaInicio; }
    public DatePicker getDpFechaFinal() { return dpFechaFinal; }
    public Button getBtnGastosLicores() { return btnGastosLicores; }
    public TableView getTvGastosLicores() { return tvGastosLicores; }
    public Button getBtnComprasIngredientes() { return btnComprasIngredientes; }
    public TableView getTvComprasIngredientes() { return tvComprasIngredientes; }
    public Button getBtnGastosIngredientes() { return btnGastosIngredientes; }
    public TableView getTvGastosIngredientes() { return tvGastosIngredientes; }
    
}
