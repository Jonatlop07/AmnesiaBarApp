package amnesiabar.controlador;

import amnesiabar.modelo.AdquisicionIngrediente;
import amnesiabar.modelo.AdquisicionLicor;
import amnesiabar.modelo.Compra;
import amnesiabar.modelo.IngredienteComida;
import amnesiabar.modelo.LicorSede;
import amnesiabar.modelo.ModeloBD;
import amnesiabar.modelo.Producto;
import amnesiabar.vista.VistaLogin;
import amnesiabar.vista.VistaSede;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Jonathan López
 */
public class ControladorSede {
    private ModeloBD modelo;
    private VistaSede vista;
    
    private ObservableList<LicorSede> listaLicoresSede;
    private ObservableList<IngredienteComida> listaIngredientesComida;
    private ObservableList<Producto> listaComprasProducto;
    private ObservableList<Compra> listaCompras;
    private ObservableList<AdquisicionLicor> listaLicoresComprados;
    private ObservableList<AdquisicionIngrediente> listaIngredientesComprados;
    
    public ControladorSede(){
        modelo = ModeloBD.getInstancia();
        vista = new VistaSede(800,700);
        
        vista.getItemCerrarSesion().setOnAction(
                e -> { 
                    Singleton singleton = Singleton.getInstancia();
                    Stage stage = singleton.getStage();
                    ControladorLogin controladorLogin = new ControladorLogin();
                    VistaLogin vistaLogin = controladorLogin.getVista();
                    stage.setScene(vistaLogin.getEscena());
                }
        );
        
        vista.getTbRegistroDistribuidora().setOnSelectionChanged(e -> {
            if(vista.getTbRegistroDistribuidora().isSelected()){
                vista.getTfRegNombreDist().clear();
                vista.getTfRegTelDist().clear();
                vista.getLbNotifRegDist().setText("");
            }
        });
        
        vista.getBtnRegistrarDistribuidora().setOnAction(e -> { registrarDistribuidora(); });
        
        vista.getTbRegistroProductos().setOnSelectionChanged(e -> {
            if(vista.getTbRegistroProductos().isSelected()){
                vista.getCbRegSedeProd().getItems().clear();
                vista.getCbRegIdFabCerv().getItems().clear();
                vista.getCbRegSedeProd().getItems().addAll(modelo.obtenerNumerosSedes());
                vista.getCbRegIdFabCerv().getItems().addAll(modelo.obtenerNumerosFabricas());
                vista.getLbNotifRegPro().setText("");
            }
        });
        
        vista.getBtnRegistroComida().setOnAction(e -> { registrarComida(); });
        vista.getBtnRegistroLicor().setOnAction(e -> { registrarLicorMarca(); });
        vista.getBtnRegistroCervezaArt().setOnAction(e -> { registrarCerveza(); });
        
        vista.getTbRegistroMateriaPrima().setOnSelectionChanged(e -> {
            if(vista.getTbRegistroMateriaPrima().isSelected()){
                vista.getTfRegNombMatPrima().clear();
                vista.getCbRegNumSedeMatPrima().getItems().clear();
                vista.getCbRegNumSedeMatPrima().getItems().addAll(modelo.obtenerNumerosSedes());
                vista.getLbNotifRegMatPrima().setText("");
            }
        });
        
        vista.getBtnRegMatPrimaComida().setOnAction(e -> { registrarMateriaPrimaComida(); });
        
        vista.getTbRegistroAdquisiciones().setOnSelectionChanged(e -> {
            if(vista.getTbRegistroAdquisiciones().isSelected()){
                vista.getCbNombDistribuidora().getItems().clear();
                vista.getCbNombDistribuidora().getItems().addAll(modelo.obtenerNombresDistribuidoras());
                vista.getTfPrecioCompra().clear();
                vista.getTfCantidadCompra().clear();
                vista.getDpFechaCompra().setValue(LocalDate.now());
                vista.getCbNombMatPrima().getItems().clear();
                vista.getCbNombMatPrima().getItems().addAll(modelo.obtenerNombresMatPrimComidas());
                vista.getCbNombLicor().getItems().clear();
                vista.getCbNombLicor().getItems().addAll(modelo.obtenerNombresLicores());
            }
        });
        
        vista.getBtnAdqMateriaPrima().setOnAction(e -> {registrarAdquisicionMateriaPrima(); });
        vista.getBtnAdqLicor().setOnAction(e -> {registrarAdquisicionLicor(); });
        
        vista.getTbRegistroComposicionComida().setOnSelectionChanged(e -> {
            if(vista.getTbRegistroComposicionComida().isSelected()){
                vista.getCbNombComida().getItems().clear();
                vista.getCbNombComida().getItems().addAll(modelo.obtenerNombresComidas());
                vista.getCbNomMateriaPrima().getItems().clear();
                vista.getCbNomMateriaPrima().getItems().addAll(modelo.obtenerNombresMatPrimComidas());
                vista.getTfCantMat().clear();
                vista.getCbNumSedMatPrima().getItems().clear();
                vista.getCbNumSedMatPrima().getItems().addAll(modelo.obtenerNumerosSedes());
                vista.getLbNotifComposicion().setText("");
            }
        });
        
        vista.getBtnRegComposicion().setOnAction(e -> { registrarComposicion(); });
        
        vista.getTbRegistroHorasTrabajo().setOnSelectionChanged(e -> {
            if(vista.getTbRegistroHorasTrabajo().isSelected()){
                vista.getCbCedulaEmpleado().getItems().clear();
                vista.getCbCedulaEmpleado().getItems().addAll(modelo.obtenerCedulasEmpleadosSedes());
                vista.getTfHorasEmpleado().clear();
                vista.getLbNotifHorasEmpleado().setText("");
            }
        });
        
        vista.getBtnHorasEmpleado().setOnAction(e -> { registrarHorasTrabajo(); });
        
        vista.getTbRegistroVenta().setOnSelectionChanged(e -> {
            if(vista.getTbRegistroVenta().isSelected()){
                vista.getCbCedCliente().getItems().clear();
                vista.getCbCedCliente().getItems().addAll(modelo.obtenerCedulasClientes());
                vista.getCbNumSedeVenta().getItems().clear();
                vista.getCbNumSedeVenta().getItems().addAll(modelo.obtenerNumerosSedes());
                vista.getDpFechaVenta().setValue(LocalDate.now());
                vista.getTfNumProducto().clear();
                vista.getCbNumVenta().getItems().clear();
                vista.getCbNumVenta().getItems().addAll(modelo.obtenerNumVentas());
                vista.getCbProductoVenta().getItems().clear();
                vista.getCbProductoVenta().getItems().addAll(modelo.obtenerNombresProductos());
                vista.getTfCantidadVenta().clear();
                vista.getLbNotifVenta().setText("");
            }
        });
        
        vista.getBtnRegistrarVenta().setOnAction(e -> { 
            registrarVenta();
            vista.getCbNumVenta().getItems().clear();
            vista.getCbNumVenta().getItems().addAll(modelo.obtenerNumVentas());
        });
        vista.getBtnRegistrarParteVenta().setOnAction(e -> { registrarParteVenta(); });
        vista.getBtnBorrarParteVenta().setOnAction(e -> { borrarParteVenta(); });
        vista.getBtnDescuento().setOnAction(e -> { aplicarDescuento(); });
        
        vista.getCbInvNumSede().getItems().addAll(modelo.obtenerNumerosSedes());
        
        vista.getTbInventarios().setOnSelectionChanged(e -> {
            if(vista.getTbInventarios().isSelected()){
                vista.getCbInvNumSede().getItems().clear();
                vista.getCbInvNumSede().getItems().addAll(modelo.obtenerNumerosSedes());
            }
        });
        
        vista.getBtnInventarioLicor().setOnAction(e -> { consultarLicorSede(); });
        vista.getBtnInventarioMateria().setOnAction(e -> { consultarIngredientesComida(); });
        
        vista.getTbConsultaCompras().setOnSelectionChanged(e -> {
            if(vista.getTbConsultaCompras().isSelected()){
                vista.getCbComprasProducto().getItems().clear();
                vista.getCbComprasProducto().getItems().addAll(modelo.obtenerNombresProductos());
            }
        });
        
        vista.getBtnComprasProducto().setOnAction(e -> { consultarComprasProducto(); });
        vista.getBtnComprasTotales().setOnAction(e -> { consultarComprasTotales(); });
        
        vista.getTbConsultaGastos().setOnSelectionChanged(e -> {
            if(vista.getTbConsultaGastos().isSelected()){
                vista.getDpFechaInicio().setValue(LocalDate.now());
                vista.getDpFechaFinal().setValue(LocalDate.now());
            }
        });
        
        vista.getBtnGastosLicores().setOnAction(e -> { consultarGastosLicores(); });
        vista.getBtnComprasIngredientes().setOnAction(e -> { consultarComprasIngredientes(); });
        vista.getBtnGastosIngredientes().setOnAction(e -> { consultarGastosIngredientes(); });
    }
    
    private void registrarDistribuidora(){
        try{
            String nombre = (String) vista.getTfRegNombreDist().getText();
            String telefono = (String) vista.getTfRegTelDist().getText();
            if (telefono.isEmpty()) telefono = null;

            if (nombre.isEmpty()){
                vista.getLbNotifRegDist().setTextFill(Color.RED);
                vista.getLbNotifRegDist().setText("El campo \"nombre\" de la distribuidora no debe estar vacío.");
            } else {
                modelo.registrarDistribuidora(nombre, telefono);
                vista.getLbNotifRegDist().setTextFill(Color.GREEN);
                vista.getLbNotifRegDist().setText("Registro exitoso");
                vista.getTfRegNombreDist().setText("");
                vista.getTfRegTelDist().setText("");
            }
        } catch(SQLException e){
            vista.getLbNotifRegDist().setTextFill(Color.MAGENTA);
            vista.getLbNotifRegDist().setText("No se pudo realizar el registro. El registro indicado ya existe.");
            vista.getTfRegNombreDist().setText("");
            vista.getTfRegTelDist().setText("");
        }
    }
    
    private void registrarComida(){
        try{
            double precio;
            int idSede;

            String nombre = (String) vista.getTfRegNombProd().getText();
            idSede = (Integer) vista.getCbRegSedeProd().getSelectionModel().getSelectedItem();

            if (!vista.getTfRegPrecioProd().getText().isEmpty())
                precio = Double.parseDouble(vista.getTfRegPrecioProd().getText());
            else precio = 0;

            if (nombre.isEmpty() || precio <= 0){
                vista.getLbNotifRegPro().setTextFill(Color.RED);
                vista.getLbNotifRegPro().setText("Debe diligenciar los campos con un (*)." 
                        + " El precio debe ser mayor a 0.");
            } else {
                modelo.registrarComida(nombre, precio, idSede);
                vista.getLbNotifRegPro().setTextFill(Color.GREEN);
                vista.getLbNotifRegPro().setText("Registro exitoso");
                vista.getTfRegNombProd().setText("");
                vista.getTfRegPrecioProd().setText("");
            }
        } catch(NumberFormatException | NullPointerException e){
            vista.getLbNotifRegPro().setTextFill(Color.RED);
            vista.getLbNotifRegPro().setText("Debe diligenciar los campos con un (*).");
        } catch (SQLException f){
            vista.getLbNotifRegPro().setTextFill(Color.MAGENTA);
            vista.getLbNotifRegPro().setText("No se pudo realizar el registro. Inténtelo de nuevo.");
        }
    }
    
    private void registrarLicorMarca(){
        try{
            double precio, presentacion;
            int idSede;

            String nombre = (String) vista.getTfRegNombProd().getText();
            idSede = (Integer) vista.getCbRegSedeProd().getSelectionModel().getSelectedItem();

            if (!vista.getTfRegPrecioProd().getText().isEmpty())
                precio = Double.parseDouble(vista.getTfRegPrecioProd().getText());
            else precio = 0;

            if (!vista.getTfRegPresLic().getText().isEmpty())
                presentacion = Double.parseDouble(vista.getTfRegPresLic().getText());
            else presentacion = 0;

            if (nombre.isEmpty() || precio <= 0 || presentacion <= 0){
                vista.getLbNotifRegPro().setTextFill(Color.RED);
                vista.getLbNotifRegPro().setText("Los campos con un (/) deben ser diligenciados." +
                        " El precio y la presentación deben ser positivos.");
            } else {
                modelo.registrarLicorMarca(nombre, precio, idSede, presentacion);
                vista.getLbNotifRegPro().setTextFill(Color.GREEN);
                vista.getLbNotifRegPro().setText("Registro exitoso");
                vista.getTfRegNombProd().setText("");
                vista.getTfRegPrecioProd().setText("");
                vista.getTfRegPresLic().setText("");
            }
        } catch(NumberFormatException | NullPointerException e){
            vista.getLbNotifRegPro().setTextFill(Color.RED);
            vista.getLbNotifRegPro().setText("Los campos con un (/) deben ser diligenciados." +
                        " El precio y la presentación deben ser positivos.");
        } catch(SQLException f){
            vista.getLbNotifRegPro().setTextFill(Color.MAGENTA);
            vista.getLbNotifRegPro().setText("No se pudo realizar el registro. Inténtelo de nuevo.");
        }
    }
    
    private void registrarCerveza(){
        try{
            double precio, presentacion;
            int idSede, idFab;

            String nombre = (String) vista.getTfRegNombProd().getText();
            String tipo = (String) vista.getTfRegTipoCerv().getText();
            idSede = (Integer) vista.getCbRegSedeProd().getSelectionModel().getSelectedItem();
            idFab = (Integer) vista.getCbRegIdFabCerv().getSelectionModel().getSelectedItem();

            if (!vista.getTfRegPrecioProd().getText().isEmpty())
                precio = Double.parseDouble(vista.getTfRegPrecioProd().getText());
            else precio = 0;

            if (!vista.getTfRegPresLic().getText().isEmpty())
                presentacion = Double.parseDouble(vista.getTfRegPresLic().getText());
            else presentacion = 0;

            if (nombre.isEmpty() || precio <= 0 || presentacion <= 0 || tipo.isEmpty()){
                vista.getLbNotifRegPro().setTextFill(Color.RED);
                vista.getLbNotifRegPro().setText("Todos los campos deben ser diligenciados." +
                        " El precio y la presentación deben ser mayores a 0.");
            } else {
                modelo.registrarCerveza(nombre, precio, idSede, presentacion, tipo, idFab);
                vista.getLbNotifRegPro().setTextFill(Color.GREEN);
                vista.getLbNotifRegPro().setText("Registro exitoso");
                vista.getTfRegNombProd().setText("");
                vista.getTfRegPrecioProd().setText("");
                vista.getTfRegPresLic().setText("");
                vista.getTfRegTipoCerv().setText("");
            }
        }catch(NumberFormatException | NullPointerException e){
            vista.getLbNotifRegPro().setTextFill(Color.RED);
            vista.getLbNotifRegPro().setText("Todos los campos deben ser diligenciados." +
                        " El precio y la presentación deben ser mayores a 0.");
        }catch (SQLException s){
            vista.getLbNotifRegPro().setTextFill(Color.MAGENTA);
            vista.getLbNotifRegPro().setText("No se pudo realizar el registro. Inténtelo de nuevo.");
        }
    }
    
    private void registrarMateriaPrimaComida(){
        try{
            int idSede;
            idSede = (Integer) vista.getCbRegNumSedeMatPrima().getSelectionModel().getSelectedItem();
            String nombre = (String) vista.getTfRegNombMatPrima().getText();

            if (nombre.isEmpty()){
                vista.getLbNotifRegMatPrima().setTextFill(Color.RED);
                vista.getLbNotifRegMatPrima().setText("Todos los campos deben ser diligenciados.");
            } else {
                modelo.registrarMateriaPrimaComida(nombre, idSede);
                vista.getLbNotifRegMatPrima().setTextFill(Color.GREEN);
                vista.getLbNotifRegMatPrima().setText("Registro exitoso");
                vista.getTfRegNombMatPrima().setText("");
            }
        }catch(NumberFormatException | NullPointerException e){ 
            vista.getLbNotifRegMatPrima().setTextFill(Color.RED);
            vista.getLbNotifRegMatPrima().setText("Todos los campos deben ser diligenciados.");
        }catch(SQLException e){
            vista.getLbNotifRegMatPrima().setTextFill(Color.MAGENTA);
            vista.getLbNotifRegMatPrima().setText("No se pudo realizar el registro. Inténtelo de nuevo.");
        }
    }
    
    private void registrarAdquisicionMateriaPrima(){
        try{
            String nombreDist = (String) vista.getCbNombDistribuidora().getSelectionModel().getSelectedItem();
            String nombreMateria = (String) vista.getCbNombMatPrima().getSelectionModel().getSelectedItem();
            double precio, cantidad;

            if (!vista.getTfPrecioCompra().getText().isEmpty())
                precio = Double.parseDouble(vista.getTfPrecioCompra().getText());
            else precio = 0;

            if (!vista.getTfCantidadCompra().getText().isEmpty())
                cantidad = Double.parseDouble(vista.getTfCantidadCompra().getText());
            else cantidad = 0;

            Date fecha = Date.valueOf(vista.getDpFechaCompra().getValue());

            if (nombreDist.isEmpty() || nombreMateria.isEmpty() || precio <= 0 || cantidad <= 0){
                vista.getLbNotifAdquisicion().setTextFill(Color.RED);
                vista.getLbNotifAdquisicion().setText("Todos los campos deben estar diligenciados. "
                        + " El precio y la cantidad no deben ser menores que 0.");
            } else {
                modelo.registrarAdquisicionMateriaPrima(nombreDist, nombreMateria, precio, cantidad, fecha);
                vista.getLbNotifAdquisicion().setTextFill(Color.GREEN);
                vista.getLbNotifAdquisicion().setText("Registro exitoso");
                vista.getTfPrecioCompra().setText("");
                vista.getTfCantidadCompra().setText("");
                vista.getDpFechaCompra().setValue(LocalDate.now());
            }
        } catch(NumberFormatException | NullPointerException e){
            vista.getLbNotifAdquisicion().setTextFill(Color.RED);
            vista.getLbNotifAdquisicion().setText("Todos los campos deben estar diligenciados. "
                        + " El precio y la cantidad no deben ser menores que 0.");
        } catch(SQLException e){
            vista.getLbNotifAdquisicion().setTextFill(Color.MAGENTA);
            vista.getLbNotifAdquisicion().setText("No se pudo realizar el registro. Inténtelo de nuevo.");
        }
    }
    
    private void registrarAdquisicionLicor(){
        try{
            String nombreDist = (String) vista.getCbNombDistribuidora().getSelectionModel().getSelectedItem();
            String nombreLicor = (String) vista.getCbNombLicor().getSelectionModel().getSelectedItem();
            double precio, cantidad;

            if (!vista.getTfPrecioCompra().getText().isEmpty())
                precio = Double.parseDouble(vista.getTfPrecioCompra().getText());
            else precio = 0;

            if (!vista.getTfCantidadCompra().getText().isEmpty())
                cantidad = Double.parseDouble(vista.getTfCantidadCompra().getText());
            else cantidad = 0;

            Date fecha = Date.valueOf(vista.getDpFechaCompra().getValue());

            if (nombreDist.isEmpty() || nombreLicor.isEmpty() || precio <= 0 || cantidad <= 0){
                vista.getLbNotifAdquisicion().setTextFill(Color.RED);
                vista.getLbNotifAdquisicion().setText("Todos los campos deben estar diligenciados. "
                        + " El precio y la cantidad no deben ser menores que 0.");
            } else {
                modelo.registrarAdquisicionLicor(nombreDist, nombreLicor, precio, cantidad, fecha);
                vista.getLbNotifAdquisicion().setTextFill(Color.GREEN);
                vista.getLbNotifAdquisicion().setText("Registro exitoso");
                vista.getTfPrecioCompra().setText("");
                vista.getTfCantidadCompra().setText("");
                vista.getDpFechaCompra().setValue(LocalDate.now());
            }
        } catch(NumberFormatException | NullPointerException e){
            vista.getLbNotifAdquisicion().setTextFill(Color.RED);
            vista.getLbNotifAdquisicion().setText("Todos los campos deben estar diligenciados. "
                        + " El precio y la cantidad no deben ser menores que 0.");
        } catch(SQLException e){
            vista.getLbNotifAdquisicion().setTextFill(Color.MAGENTA);
            vista.getLbNotifAdquisicion().setText("No se pudo realizar el registro. Inténtelo de nuevo.");
        }
    }
    
    private void registrarComposicion(){
        try{
            String nombreComida = (String) vista.getCbNombComida().getSelectionModel().getSelectedItem();
            String nombreMatPrima = (String) vista.getCbNombMatPrima().getSelectionModel().getSelectedItem();
            String numeroSede = (String) vista.getCbRegNumSedeMatPrima().getSelectionModel().getSelectedItem();

            double cantidad;
            int idSede;

            if (!numeroSede.isEmpty())
                idSede = Integer.parseInt(numeroSede);
            else idSede = 0;

            if (!vista.getTfCantMat().getText().isEmpty())
                cantidad = Double.parseDouble(vista.getTfCantMat().getText());
            else cantidad = 0;

            if (nombreComida.isEmpty() || nombreMatPrima.isEmpty() || cantidad <= 0 || idSede == 0){
                vista.getLbNotifComposicion().setTextFill(Color.RED);
                vista.getLbNotifComposicion().setText("Todos los campos deben estar diligenciados. "
                        + " La cantidad no debe ser menor que 0.");
            } else {
                modelo.registrarComposicionComida(nombreComida, nombreMatPrima, cantidad, idSede);
                vista.getLbNotifComposicion().setTextFill(Color.GREEN);
                vista.getLbNotifComposicion().setText("Registro exitoso");
            }
        } catch(NumberFormatException | NullPointerException e){
            vista.getLbNotifComposicion().setTextFill(Color.RED);
            vista.getLbNotifComposicion().setText("Todos los campos deben estar diligenciados. La cantidad debe ser mayor a 0.");
        } catch(SQLException e){
            vista.getLbNotifComposicion().setTextFill(Color.MAGENTA);
            vista.getLbNotifComposicion().setText("No se pudo realizar el registro. Inténtelo de nuevo.");
        }
    }
    
    private void registrarHorasTrabajo(){
        try{
            int cedula = (int) vista.getCbCedulaEmpleado().getSelectionModel().getSelectedItem();
            int horas = Integer.parseInt(vista.getTfHorasEmpleado().getText());

            if(horas <= 0){
                vista.getLbNotifHorasEmpleado().setTextFill(Color.RED);
                vista.getLbNotifHorasEmpleado().setText("Todos los campos deben estar diligenciados. "
                        + " El número de horas debe ser mayor o igual que 0.");
            } else {
                modelo.registrarHorasTrabajoEmpleadoSede(cedula, horas);
                vista.getLbNotifHorasEmpleado().setTextFill(Color.GREEN);
                vista.getLbNotifHorasEmpleado().setText("Registro exitoso");
                vista.getTfHorasEmpleado().setText("");
            }
        } catch(NumberFormatException | NullPointerException e){
            vista.getLbNotifHorasEmpleado().setTextFill(Color.RED);
            vista.getLbNotifHorasEmpleado().setText("Todos los campos deben ser diligenciados. El número de horas debe ser mayor o igual que 0");
        } catch(SQLException e){
            vista.getLbNotifHorasEmpleado().setTextFill(Color.MAGENTA);
            vista.getLbNotifHorasEmpleado().setText("No se pudo realizar el registro. Inténtelo de nuevo.");
        }
    }
    
    private void registrarVenta(){
        try{
            int cedula = (int) vista.getCbCedCliente().getSelectionModel().getSelectedItem();
            int numSed = (int) vista.getCbNumSedeVenta().getSelectionModel().getSelectedItem();

            Date fecha = Date.valueOf(vista.getDpFechaVenta().getValue());

            if (cedula <= 0 || numSed <= 0){
                vista.getLbNotifVenta().setTextFill(Color.RED);
                vista.getLbNotifVenta().setText("Todos los campos con (*) deben ser diligenciados.");
            } else {
                modelo.registrarVenta(cedula, numSed, fecha);
                vista.getLbNotifVenta().setTextFill(Color.GREEN);
                vista.getLbNotifVenta().setText("Compra registrada");
            }
        } catch(NumberFormatException | NullPointerException e){
            vista.getLbNotifVenta().setTextFill(Color.RED);
            vista.getLbNotifVenta().setText("Todos los campos con un (*) deben ser diligenciados.");
        } catch(SQLException e){
            vista.getLbNotifVenta().setTextFill(Color.MAGENTA);
            vista.getLbNotifVenta().setText("No se pudo realizar el registro. Inténtelo de nuevo.");
        }
            
    }
    
    private void registrarParteVenta(){
        try{
            int idParte = Integer.parseInt(vista.getTfNumProducto().getText());
            int idVenta = (int) vista.getCbNumVenta().getSelectionModel().getSelectedItem();
            String nombrePro = (String) vista.getCbProductoVenta().getSelectionModel().getSelectedItem();
            double cantidad = Double.parseDouble(vista.getTfCantidadVenta().getText());

            if (nombrePro.isEmpty() || cantidad <= 0){
                vista.getLbNotifVenta().setTextFill(Color.RED);
                vista.getLbNotifVenta().setText("Todos los campos con un (/) deben ser diligenciados. "+
                        "El número de ítem y la cantidad deben ser mayores que 0");
            } else {
                modelo.registrarParteVenta(idParte, idVenta, nombrePro, cantidad);
                vista.getLbNotifVenta().setTextFill(Color.GREEN);
                vista.getLbNotifVenta().setText("Producto registrado");
                vista.getTfNumProducto().setText("");
                vista.getTfCantidadVenta().setText("");
            }
        } catch(NumberFormatException | NullPointerException e){
            vista.getLbNotifVenta().setTextFill(Color.RED);
            vista.getLbNotifVenta().setText("Todos los campos con un (/) deben ser diligenciados. "+
                        "El número de ítem y la cantidad deben ser mayores que 0");
        } catch(SQLException e){
            vista.getLbNotifVenta().setTextFill(Color.MAGENTA);
            vista.getLbNotifVenta().setText("No se pudo realizar el registro. Inténtelo de nuevo.");
        }
    }
    
    private void borrarParteVenta(){
        try{
            String nombrePro = (String) vista.getCbProductoVenta().getSelectionModel().getSelectedItem();
            int idVenta = (int) vista.getCbNumVenta().getSelectionModel().getSelectedItem();

            if (nombrePro.isEmpty()){
                vista.getLbNotifVenta().setTextFill(Color.RED);
                vista.getLbNotifVenta().setText("Escoja el nombre del producto y el número de la venta.");
            } else if (modelo.borrarParteVenta(nombrePro, idVenta)) {
                vista.getLbNotifVenta().setTextFill(Color.GREEN);
                vista.getLbNotifVenta().setText("Producto borrado de la venta" + idVenta);
            } else {
                vista.getLbNotifVenta().setTextFill(Color.YELLOW);
                vista.getLbNotifVenta().setText("La venta indicada no contiene el producto especificado.");
            }
        } catch(NumberFormatException | NullPointerException e){
            vista.getLbNotifVenta().setTextFill(Color.RED);
            vista.getLbNotifVenta().setText("Escoja el nombre del producto y el número de la venta.");
        } catch(SQLException e){
            vista.getLbNotifVenta().setTextFill(Color.MAGENTA);
            vista.getLbNotifVenta().setText("No se pudo realizar el registro. Inténtelo de nuevo.");
        }
    }
    
    private void aplicarDescuento(){
        try{
            int numVenta = (int) vista.getCbNumVenta().getSelectionModel().getSelectedItem();
            double descuento = Double.parseDouble(vista.getTfDescuento().getText());
            if (descuento <= 0 || descuento >= 100){
                vista.getLbNotifVenta().setTextFill(Color.RED);
                vista.getLbNotifVenta().setText("El porcentaje debe estar entre 0 y 100 (no incluido el 0).");
            } else {
                descuento /= 100;
                modelo.aplicarDescuento(numVenta, descuento);
                vista.getLbNotifVenta().setTextFill(Color.GREEN);
                vista.getLbNotifVenta().setText("Descuento aplicado.");
            }
        } catch(NullPointerException | NumberFormatException e){
            vista.getLbNotifVenta().setTextFill(Color.RED);
            vista.getLbNotifVenta().setText("Los campos id de la compra y porcentaje de descuento deben " + 
                    "estar diligenciados. El porcentaje debe ser un número.");
        } catch(SQLException s){
            vista.getLbNotifVenta().setTextFill(Color.DARKMAGENTA);
            vista.getLbNotifVenta().setText("Ocurrió un problema al registrar el descuento. Inténtelo de nuevo.");
        }
    }
    
    private void consultarLicorSede(){
        try {
            int numSed = (int) vista.getCbInvNumSede().getSelectionModel().getSelectedItem();
            listaLicoresSede = FXCollections.observableArrayList(modelo.obtenerInventarioLicores(numSed));
            vista.getTvInventarioLicor().setItems(listaLicoresSede);
        }catch(NullPointerException | NumberFormatException e){}
    }
    
    private void consultarIngredientesComida(){
        try{
            int numSed = (int) vista.getCbInvNumSede().getSelectionModel().getSelectedItem();
            listaIngredientesComida = FXCollections.observableArrayList(modelo.obtenerInventarioIngredientesComida(numSed));
            vista.getTvInventarioMateria().setItems(listaIngredientesComida);
        }catch(NullPointerException | NumberFormatException e){ }
    }
    
    private void consultarComprasProducto(){
        try{
            String producto = (String) vista.getCbComprasProducto().getSelectionModel().getSelectedItem();
            listaComprasProducto = FXCollections.observableArrayList(modelo.obtenerComprasProducto(producto));
            vista.getTvComprasProducto().setItems(listaComprasProducto);
        }catch(NullPointerException | NumberFormatException e){ }
    }
    
    private void consultarComprasTotales(){
        try{
            listaCompras = FXCollections.observableArrayList(modelo.obtenerCompras());
            vista.getTvComprasTotales().setItems(listaCompras);
        }catch(NullPointerException | NumberFormatException e){ }
    }
    
    private void consultarGastosLicores(){
        try{
            Date fechaInicial = Date.valueOf(vista.getDpFechaInicio().getValue());
            Date fechaFinal = Date.valueOf(vista.getDpFechaFinal().getValue());

            listaLicoresComprados = FXCollections.observableArrayList(modelo.obtenerLicoresComprados(fechaInicial, fechaFinal));
            vista.getTvGastosLicores().setItems(listaLicoresComprados);
        }catch(NullPointerException | NumberFormatException e){ }
    }
    
    private void consultarComprasIngredientes(){
        try{
            Date fechaInicial = Date.valueOf(vista.getDpFechaInicio().getValue());
            Date fechaFinal = Date.valueOf(vista.getDpFechaFinal().getValue());
            listaIngredientesComprados = FXCollections.observableArrayList(modelo.obtenerIngredientesComprados(fechaInicial, fechaFinal));
            vista.getTvComprasIngredientes().setItems(listaIngredientesComprados);
        } catch(NullPointerException | NumberFormatException e){ }
    }
    
    private void consultarGastosIngredientes(){
        try{
            Date fechaInicial = Date.valueOf(vista.getDpFechaInicio().getValue());
            Date fechaFinal = Date.valueOf(vista.getDpFechaFinal().getValue());
            listaIngredientesComida = FXCollections.observableArrayList(modelo.obtenerIngredientesGastados(fechaInicial, fechaFinal));
            vista.getTvGastosIngredientes().setItems(listaIngredientesComida);
        } catch(NullPointerException | NumberFormatException e){ }
    }
    
    public VistaSede getVista(){ return vista; }
}
