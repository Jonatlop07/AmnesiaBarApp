package amnesiabar.modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

/** Principal clase que administra la base de datos, se encarga de realizar la conexión con esta.
 * Solo puede existir una instancia de esta clase.
 * @author Jonathan López
 */
public class ModeloBD {
    private static ModeloBD instanciaUnica;
    private Connection conexion;
    private final String SERVIDOR = "jdbc:mysql://localhost/proyecto?useUnicode=true&useJDBCCompliantTimezoneShift=true&"
                                    + "useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
    
    private ModeloBD(){ }
    
    public static ModeloBD getInstancia(){
        if (instanciaUnica == null) {
            instanciaUnica = new ModeloBD();
        }
        return instanciaUnica;
    }
    
    /** Verifica que el usuario existe en el esquema de la base dN_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = 'proyecto'";
        ResultSet rs = st.executeQuery(sql);

        if (rs.next()) {e datos y de hacerlo realiza la conexión con este.
     * Además modifica la conexión, si existe, en el momento de ser llamado.
     * @param usuario Cadena que guarda el nombre del usuario con el cual se desea ingresar
     * @param clave Cadena que guarda la clave del usuario con la cual se desea ingresar
     * @return Devuelve verdadero si los datos ingresados corresponden a un usuario de la base de datos
     */
    public int realizarConexion(String usuario, String clave) throws ClassNotFoundException, SQLException{
        conexion = null;
        Statement st = null;
        Class.forName("com.mysql.jdbc.Driver");
        conexion = DriverManager.getConnection(SERVIDOR, usuario, clave);
        st = conexion.createStatement();
        String sql = "SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = 'proyecto'";
        ResultSet rs = st.executeQuery(sql);

        if (rs.next()) {
            switch (usuario){
                case "HectorRojas":
                    return 1;     
                case "EmpleadoSedeExperto":
                    return 2;
                case "EmpleadoFabricaExperto":
                    return 3;
            }
        }
        return -1;
    }

    public Connection getConexion(){
        return conexion;
    }
    
    public ArrayList<LicorSede> obtenerInventarioLicores(int numSed){
        ArrayList<LicorSede> lista = new ArrayList();
        try{
            String call = "CALL inventariolicorSede(?)";
            CallableStatement stmt = conexion.prepareCall(call);
            stmt.setInt(1, numSed);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            
            while(rs.next()){
                lista.add(new LicorSede(rs.getString(1), rs.getDouble(2), rs.getDouble(3), rs.getDouble(4)));
            }
        } catch(SQLException | NullPointerException e){
            System.err.println(e.getMessage());
        }
        return lista;
    }
    
    public ArrayList<IngredienteComida> obtenerInventarioIngredientesComida(int numSed){
        ArrayList<IngredienteComida> lista = new ArrayList();
        try{
            String call = "CALL inventariomateriaprimaSede(?)";
            CallableStatement stmt = conexion.prepareCall(call);
            stmt.setInt(1, numSed);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            
            while(rs.next()){
                lista.add(new IngredienteComida(rs.getString(1), rs.getDouble(2)));
            }
        } catch(SQLException | NullPointerException e){
            System.err.println(e.getMessage());
        }
        return lista;
    }
    
    public ArrayList<Integer> obtenerNumerosSedes(){
        ArrayList<Integer> numerosSedes = new ArrayList<>();
        try {
            String st = "SELECT sed_numeroSede FROM sede";
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(st);
            
            while(rs.next()){
                numerosSedes.add(rs.getInt(1));
            }
        } catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return numerosSedes;
    }
    
    public ArrayList<Integer> obtenerNumerosFabricas(){
        ArrayList<Integer> numerosFabricas = new ArrayList<>();
        try {
            String st = "SELECT fab_id FROM fabrica";
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(st);
            
            while(rs.next()){
                numerosFabricas.add(rs.getInt(1));
            }
        } catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return numerosFabricas;
    }
    
    public ArrayList<Integer> obtenerNumVentas(){
        ArrayList<Integer> numeros = new ArrayList<>();
        try{
            String st = "SELECT ven_id FROM venta ORDER BY ven_id DESC";
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(st);
            
            while(rs.next()){
                numeros.add(rs.getInt(1));
            }
        } catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return numeros;
    }
    
    public ArrayList<String> obtenerNombresProductos(){
        ArrayList<String> nombres = new ArrayList<>();
        try{
            String st = "SELECT pro_nombre FROM producto";
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(st);
            
            while(rs.next()){
                nombres.add(rs.getString(1));
            }
        } catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return nombres;
    }
    
    public ArrayList<String> obtenerNombresCerveza (){
        ArrayList<String> nombres = new ArrayList<>(); 
        try {
           String st = "SELECT licor_producto_pro_nombre FROM cervezaartesanal"; 
           Statement stmt = conexion.createStatement(); 
           ResultSet rs= stmt.executeQuery(st);
           while (rs.next()){
               nombres.add(rs.getString(1)); 
           }
        } catch (SQLException e){
               System.err.println(e.getMessage());
        }
        return nombres; 
    }
    
    public ArrayList<String> obtenerNombresMatPrimComidas(){
        ArrayList<String> nombres = new ArrayList<>();
        try{
            String st = "SELECT mpco_nombre FROM materiaPrimaComida";
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(st);
            
            while(rs.next()){
                nombres.add(rs.getString(1));
            }
        } catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return nombres;
    }
    public ArrayList<String> obtenerNombresMatPrimaCerveza (){
        ArrayList <String> nombres = new ArrayList<>(); 
        try {
            String st = "SELECT mpce_nombre FROM materiaPrimaCerveza"; 
            Statement stmt = conexion.createStatement(); 
            ResultSet rs = stmt.executeQuery(st); 
            while (rs.next()){
                nombres.add(rs.getString(1)); 
            }
        } catch (SQLException e){
            System.err.println(e); 
        }
        return nombres; 
    }
    public ArrayList<String> obtenerNombresLicores(){
        ArrayList<String> nombres = new ArrayList<>();
        try{
            String st = "SELECT licor_producto_pro_nombre FROM licorMarca";
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(st);
            
            while(rs.next()){
                nombres.add(rs.getString(1));
            }
        } catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return nombres;
    }
    
    public ArrayList<String> obtenerNombresDistribuidoras(){
        ArrayList<String> nombres = new ArrayList<>();
        try{
            String st = "SELECT dis_nombre FROM distribuidora";
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(st);
            
            while(rs.next()){
                nombres.add(rs.getString(1));
            }
        } catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return nombres;
    }
    
    public ArrayList<String> obtenerNombresComidas(){
        ArrayList<String> nombres = new ArrayList<>();
        try{
            String st = "SELECT producto_pro_nombre FROM comida";
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(st);
            
            while(rs.next()){
                nombres.add(rs.getString(1));
            }
        } catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return nombres;
    }
    
    public ArrayList<Integer> obtenerCedulasEmpleadosSedes(){
        ArrayList<Integer> cedulas = new ArrayList<>();
        try{
            String st = "SELECT empleado_persona_per_cedula FROM empleadoSede";
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(st);
            
            while(rs.next()){
                cedulas.add(rs.getInt(1));
            }
        } catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return cedulas;
    }
    
    public ArrayList<Integer> obtenerCedulasClientes(){
        ArrayList<Integer> cedulas = new ArrayList<>();
        try{
            String st = "SELECT persona_per_cedula FROM cliente";
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(st);
            
            while(rs.next()){
                cedulas.add(rs.getInt(1));
            }
        } catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return cedulas;
    }
    
    public ArrayList<Producto> obtenerComprasProducto(String producto){
        ArrayList<Producto> lista = new ArrayList();
        try{
            String call = "CALL totalventasProducto(?)";
            CallableStatement stmt = conexion.prepareCall(call);
            stmt.setString(1, producto);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            
            while(rs.next()){
                lista.add(new Producto(rs.getString(1), rs.getDouble(2), rs.getDouble(3)));
            }
        } catch(SQLException | NullPointerException e){
            System.err.println(e.getMessage());
        }
        return lista;
    }
        
    public ArrayList<Compra> obtenerCompras(){
        ArrayList<Compra> lista = new ArrayList();
        try{
            String st = "SELECT * FROM vw_consultaVentas";
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(st);
            
            while(rs.next()){
                lista.add(new Compra(rs.getDate(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5),
                                     rs.getString(6), rs.getDouble(7), rs.getDouble(8), rs.getDouble(9)));
            }
        } catch(SQLException | NullPointerException e){
            System.err.println(e.getMessage());
        }
        return lista;
    }
    
    public ArrayList<AdquisicionLicor> obtenerLicoresComprados(Date fechaInicio, Date fechaFin){
        ArrayList<AdquisicionLicor> lista = new ArrayList();
        try{
            String st = "SELECT * FROM facturaLicor WHERE fli_fecha BETWEEN '" + fechaInicio + "' AND '" + fechaFin + "'";
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(st);
            
            while(rs.next()){
                lista.add(new AdquisicionLicor(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getInt(4), rs.getDate(5)));
            }
        } catch(SQLException | NullPointerException e){
            System.err.println(e.getMessage());
        }
        return lista;
    }
    
    public ArrayList<AdquisicionIngrediente> obtenerIngredientesComprados(Date fechaInicio, Date fechaFin){
        ArrayList<AdquisicionIngrediente> lista = new ArrayList();
        try{
            String st = "SELECT * FROM facturaMateriaPrimaComida WHERE fco_fecha BETWEEN '" 
                    + fechaInicio + "' AND '" + fechaFin + "'";
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(st);
            
            while(rs.next()){
                lista.add(new AdquisicionIngrediente(rs.getString(2), rs.getString(1), rs.getDouble(3), rs.getDouble(4), rs.getDate(5)));
            }
        } catch(SQLException | NullPointerException e){
            System.err.println(e.getMessage());
        }
        return lista;
    }
    
    public ArrayList<IngredienteComida> obtenerIngredientesGastados(Date fechaInicio, Date fechaFin){
        ArrayList<IngredienteComida> lista = new ArrayList();
        try{
            String st = "SELECT * FROM vw_registroMateriaPrimaFecha WHERE ven_fecha BETWEEN '" 
                    + fechaInicio + "' AND '" + fechaFin + "'";
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(st);
            
            while(rs.next()){
                lista.add(new IngredienteComida(rs.getString(2), rs.getDouble(3), rs.getDate(1)));
            }
        } catch(SQLException | NullPointerException e){
            System.err.println(e.getMessage());
        }
        return lista;
    }
    
    public void registrarSede(String ciudad, String calle, String carrera, String barrio){
        try{
            String call = "CALL registrarSede (?, ?, ?, ?)";
            CallableStatement stmt = conexion.prepareCall(call);
            stmt.setString(1, ciudad);
            stmt.setString(2, barrio);
            stmt.setObject(3, calle, Types.VARCHAR);
            stmt.setObject(4, carrera, Types.VARCHAR);
            stmt.execute();
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }
    
    public void registrarFabrica(String ciudad, String calle, String carrera){
        try{
            String call = "CALL registrarFabrica (?, ?, ?)";
            CallableStatement stmt = conexion.prepareCall(call);
            stmt.setString(1, ciudad);
            stmt.setObject(2, calle, Types.VARCHAR);
            stmt.setObject(3, carrera, Types.VARCHAR);
            stmt.execute();
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }
    
    public void registrarEmpleado(int cedula, String nombre, String apellido, int edad, String telefono, String direccion){
        try{
            String call = "CALL registrarEmpleado (?, ?, ?, ?, ?, ?, ?)";
            CallableStatement stmt = conexion.prepareCall(call);
            stmt.setInt(1, cedula);
            stmt.setString(2, nombre);
            stmt.setString(3, apellido);
            stmt.setInt(4, edad);
            stmt.setObject(5, telefono, Types.VARCHAR);
            stmt.setObject(6, direccion, Types.VARCHAR);
            stmt.execute();
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }
    public void registrarMateriaPrimaCerveza (String nombre, int idfab) throws SQLException{
        String call = "CALL registrarMateriaPrimaCerveza (?,?)"; 
        CallableStatement stmt = conexion.prepareCall(call); 
        stmt.setString(1,nombre);
        stmt.setInt(2, idfab);
        stmt.execute(); 
    }
    
    public void registrarEmpleadoSede(int cedula, int idSede){
        try{
            String call = "CALL registrarEmpleadoSede (?, ?)";
            CallableStatement stmt = conexion.prepareCall(call);
            stmt.setInt(1, cedula);
            stmt.setInt(2, idSede);
            stmt.execute();
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }
    
    public void registrarEmpleadoFabrica(int cedula, int idFabrica){
        try{
            String call = "CALL registrarEmpleadoFabrica (?, ?)";
            CallableStatement stmt = conexion.prepareCall(call);
            stmt.setInt(1, cedula);
            stmt.setInt(2, idFabrica);
            stmt.execute();
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }
    
    public void registrarComida(String nombre, double precio, int idSede) throws SQLException{
        String call = "CALL registrarComida (?, ?, ?)";
        CallableStatement stmt = conexion.prepareCall(call);
        stmt.setString(1, nombre);
        stmt.setDouble(2, precio);
        stmt.setInt(3, idSede);
        stmt.execute();
    }
    
    public void registrarLicorMarca(String nombre, double precio, int idSede, double presentacion) 
            throws SQLException{
        String call = "CALL registrarLicorMarca (?, ?, ?, ?)";
        CallableStatement stmt = conexion.prepareCall(call);
        stmt.setString(1, nombre);
        stmt.setDouble(2, precio);
        stmt.setInt(3, idSede);
        stmt.setDouble(4, presentacion);
        stmt.execute();
    }
    
    public void registrarCerveza(String nombre, double precio, int idSede, double presentacion, String tipo, int idFab)
            throws SQLException{
        String call = "CALL registrarCervezaArtesanal (?, ?, ?, ?, ?, ?)";
        CallableStatement stmt = conexion.prepareCall(call);
        stmt.setString(1, nombre);
        stmt.setDouble(2, precio);
        stmt.setInt(3, idSede);
        stmt.setDouble(4, presentacion);
        stmt.setString(5, tipo);
        stmt.setInt(6, idFab);
        stmt.execute();
    }
    
    public void registrarMateriaPrimaComida(String nombre, int idSede) throws SQLException{
        String call = "CALL registrarMateriaPrimaComida (?, ?)";
        CallableStatement stmt = conexion.prepareCall(call);
        stmt.setString(1, nombre);
        stmt.setInt(2, idSede);
        stmt.execute();
    }
    
    
    public void registrarDistribuidora(String nombre, String telefono) throws SQLException{
        String call = "CALL registrarDistribuidora (?, ?)";
        CallableStatement stmt = conexion.prepareCall(call);
        stmt.setString(1, nombre);
        stmt.setObject(2, telefono, Types.VARCHAR);
        stmt.execute();
    }
    
    public void registrarAfiliado(int cedula, String telefono) throws SQLException{
        String call = "CALL registrarAfiliado (?, ?)";
        CallableStatement stmt = conexion.prepareCall(call);
        stmt.setInt(1, cedula);
        stmt.setObject(2, telefono, Types.VARCHAR);
        stmt.execute();
    }
    
    public void registrarCliente(int cedula, String nombre, String apellido){
        try{
            String call = "CALL registrarCliente (?, ?, ?)";
            CallableStatement stmt = conexion.prepareCall(call);
            stmt.setInt(1, cedula);
            stmt.setString(2, nombre);
            stmt.setString(2, apellido);
            stmt.execute();
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }
    
    public void registrarAdquisicionMateriaPrima(String nombreDist, String nombreMateria, 
            double precio, double cantidad, Date fecha) throws SQLException{
        try{
            String call = "CALL registrarAdquisicionMatPrimaComida (?, ?, ?, ?, ?)";
            CallableStatement stmt = conexion.prepareCall(call);
            stmt.setString(1, nombreDist);
            stmt.setString(2, nombreMateria);
            stmt.setDouble(3, precio);
            stmt.setDouble(4, cantidad);
            stmt.setDate(5, fecha);
            stmt.execute();
        } catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }
    
    public void registrarAdquisicionLicor(String nombreDist, String nombreLicor, 
            double precio, double cantidad, Date fecha) throws SQLException{
        String call = "CALL registrarAdquisicionLicor (?, ?, ?, ?, ?)";
        CallableStatement stmt = conexion.prepareCall(call);
        stmt.setString(1, nombreLicor);
        stmt.setString(2, nombreDist);
        stmt.setDouble(3, precio);
        stmt.setDouble(4, cantidad);
        stmt.setDate(5, fecha);
        stmt.execute();
    }
    
    public void registrarComposicionComida(String nombreComida, String nombreMatPrima, double cantidad, int idSede)
            throws SQLException{
        String call = "CALL registrarComposicionComida (?, ?, ?, ?, ?)";
        CallableStatement stmt = conexion.prepareCall(call);
        stmt.setString(1, nombreComida);
        stmt.setString(2, nombreMatPrima);
        stmt.setDouble(3, cantidad);
        stmt.setDouble(4, idSede);
        stmt.execute();
    }
    
    public void registrarHorasTrabajoEmpleadoSede(int cedula, int horas) throws SQLException{
        String call = "CALL registrarhorastrabajoEmpleadoSede (?, ?)";
        CallableStatement stmt = conexion.prepareCall(call);
        stmt.setInt(1, cedula);
        stmt.setInt(2, horas);
        stmt.execute();
    }
    
    public void registrarVenta(int cedula, int numSed, Date fecha) throws SQLException{
        String call = "CALL registrarVenta (?, ?, ?)";
        CallableStatement stmt = conexion.prepareCall(call);
        stmt.setInt(1, cedula);
        stmt.setInt(2, numSed);
        stmt.setDate(3, fecha);
        stmt.execute();
    }
    
    public void registrarParteVenta(int idParte, int idVenta, String nombrePro, double cantidad)
            throws SQLException{
        String call = "CALL registrarParteVenta (?, ?, ?, ?)";
        CallableStatement stmt = conexion.prepareCall(call);
        stmt.setInt(1, idParte);
        stmt.setInt(2, idVenta);
        stmt.setString(3, nombrePro);
        stmt.setDouble(4, cantidad);
        stmt.execute();
    }
    
    public boolean borrarParteVenta(String nombrePro, int idVenta) throws SQLException{
        boolean existe = false;
        String call_1 = "SELECT producto_pro_nombre FROM parteVenta WHERE venta_ven_id = " + idVenta;
        Statement s = conexion.createStatement();
        ResultSet rs =  s.executeQuery(call_1);

        if (rs.next()) existe = true;

        String call_2 = "CALL borrarParteVenta (?, ?)";
        CallableStatement stmt2 = conexion.prepareCall(call_2);
        stmt2.setString(1, nombrePro);
        stmt2.setInt(2, idVenta);
        stmt2.execute();
 
        return existe;
    }
    
    public boolean aplicarDescuento(int numVenta, double descuento) throws SQLException{
            String call = "SELECT aplicarDescuentoCliente(" + numVenta + ", " + descuento + ")";
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery(call);
            
            if (rs.next()) return rs.getBoolean(1);
            else return false;
    }
    
    public ArrayList<IngredienteCerveza> obtenerIngredientesCerveza(int idFab) throws SQLException, NullPointerException{
        ArrayList<IngredienteCerveza> lista = new ArrayList();
        String call = "CALL inventariomateriaprimaCerveza(?)";
        CallableStatement stmt = conexion.prepareCall(call);
        stmt.setInt(1, idFab);
        stmt.execute();
        ResultSet rs = stmt.getResultSet();

        while(rs.next()) lista.add(new IngredienteCerveza(rs.getString(1), rs.getDouble(2)));
        
        return lista;
    }

    public ArrayList<AdquisicionIngrediente> consultarAdquisicionCervezas(Date fechaInicio, Date fechaFin){
        ArrayList<AdquisicionIngrediente> lista = new ArrayList();
        try{
            String st = "SELECT * FROM facturaMateriaPrimaCerveza WHERE fce_fecha BETWEEN '" 
                    + fechaInicio + "' AND '" + fechaFin + "'";
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(st);

            while(rs.next()){
                lista.add(new AdquisicionIngrediente(rs.getString(2), rs.getString(1), rs.getDouble(3), rs.getDouble(4), rs.getDate(5)));
            }
        } catch(SQLException | NullPointerException e){
            System.err.println(e.getMessage());
        }
        return lista;
    }

    public ArrayList<Produccion> obtenerTotalProducciones(Date fechaInicio, Date fechaFin){
        ArrayList<Produccion> lista = new ArrayList();
        try{
            String st = "SELECT * FROM produccion WHERE pro_fecha BETWEEN '" + fechaInicio + "' AND '" + fechaFin + "'";
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(st);

            while(rs.next()){
                lista.add(new Produccion(rs.getInt(1), rs.getDate(2), rs.getInt(4), rs.getString(5), rs.getDouble(3)));
            }
        } catch(SQLException | NullPointerException e){
            System.err.println(e.getMessage());
        }
        return lista;
    }

    public ArrayList<Produccion> obtenerProduccionesPorCerveza(String nombreCerveza, Date fechaInicio, Date fechaFin){
        ArrayList<Produccion> lista = new ArrayList();
        try{
            String st = "SELECT * FROM produccion WHERE cervezaArtesanal_licor_producto_pro_nombre = '" + nombreCerveza
                + "' AND pro_fecha BETWEEN '" + fechaInicio + "' AND '" + fechaFin + "'";
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(st);

            while(rs.next()){
                lista.add(new Produccion(rs.getInt(1), rs.getDate(2), rs.getInt(4), rs.getString(5), rs.getDouble(3)));
            }
        } catch(SQLException | NullPointerException e){
            System.err.println(e.getMessage());
        }
        return lista;
    }


    public ArrayList<Distribucion> obtenerDistribucionesPorSede(int idSede) throws SQLException, NullPointerException{
        ArrayList<Distribucion> lista = new ArrayList();
        String call = "CALL obtenerDistribucionesPorSede(?)";
        CallableStatement stmt = conexion.prepareCall(call);
        stmt.setInt(1, idSede);
        stmt.execute();
        ResultSet rs = stmt.getResultSet();

        while(rs.next()) lista.add(new Distribucion(rs.getString(1), rs.getDouble(2), rs.getInt(3)));
        
        return lista;
    }

    public ArrayList<Distribucion> obtenerDistribucionesPorCerveza(String nombreCerveza)
            throws SQLException, NullPointerException{
        ArrayList<Distribucion> lista = new ArrayList();
        String call = "CALL obtenerDistribucionesPorCerveza(?)";
        CallableStatement stmt = conexion.prepareCall(call);
        stmt.setString(1, nombreCerveza);
        stmt.execute();
        ResultSet rs = stmt.getResultSet();

        while(rs.next()) lista.add(new Distribucion(rs.getString(1), rs.getDouble(2), rs.getInt(3)));
            
        return lista;
    }

    public ArrayList<Afiliado> consultarAfiliados(){
        ArrayList<Afiliado> lista = new ArrayList();
        try{
            String st = "SELECT * FROM afiliado";
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(st);

            while(rs.next()){
                lista.add(new Afiliado(rs.getInt(1), rs.getString(2)));
            }
        } catch(SQLException | NullPointerException e){
            System.err.println(e.getMessage());
        }
        return lista;
    }


    public void registrarProduccion(String nombreCerveza, double cantidad, int idFab, Date fecha)
            throws SQLException{
        String call = "CALL registrarProduccion (?, ?, ?, ?)";
        CallableStatement stmt = conexion.prepareCall(call);
        stmt.setDate(1, fecha);
        stmt.setDouble(2, cantidad);
        stmt.setInt(3, idFab);
        stmt.setString(4, nombreCerveza);
        stmt.execute();
    }

    public void registrarDistribucion(String nombreCerveza, double cantidad, int idSede, int idFab, Date fecha)
            throws SQLException{
        String call = "CALL registrarDistribucion (?, ?, ?, ?, ?, ?, ?)";
        CallableStatement stmt = conexion.prepareCall(call);
        stmt.setDate(1, fecha);
        stmt.setDouble(2, cantidad);
        stmt.setString(3, nombreCerveza);
        stmt.setNull(4, Types.INTEGER);
        stmt.setNull(5, Types.VARCHAR);
        stmt.setInt(6, idSede);
        stmt.setInt(7, idFab);
        stmt.execute();
    }

    public void registrarDistribucion(String nombreCerveza, double cantidad, int idSede, int idFab, Date fecha, int idAfiliado, String telefono)
            throws SQLException{
        String call = "CALL registrarProduccion (?, ?, ?, ?, ?, ?, ?)";
        CallableStatement stmt = conexion.prepareCall(call);
        stmt.setDate(1, fecha);
        stmt.setDouble(2, cantidad);
        stmt.setString(3, nombreCerveza);
        stmt.setInt(4, idAfiliado);
        stmt.setString(5, telefono);
        stmt.setInt(6, idSede);
        stmt.setInt(7, idFab);
        stmt.execute();
    }

    public void registrarComposicionCerveza(String nombreCerveza, String ingrediente, double cantidad, int idFab)
                throws SQLException{
        String call = "CALL registrarComposicionCerveza (?, ?, ?, ?)";
        CallableStatement stmt = conexion.prepareCall(call);
        stmt.setString(1, nombreCerveza);
        stmt.setString(2, ingrediente);
        stmt.setDouble(3, cantidad);
        stmt.setInt(4, idFab);
        stmt.execute();
    }

    public void registrarIngredienteCerveza (String proveedor, String ingrediente, double precio, double cantidad, Date fecha)
                throws SQLException{
        String call = "CALL registrarAdquisicionMatPrimaCerveza (?, ?, ?, ?, ?)";
        CallableStatement stmt = conexion.prepareCall(call);
        stmt.setString(1, proveedor);
        stmt.setString(2, ingrediente);
        stmt.setDouble(3, precio);
        stmt.setDouble(4, cantidad);
        stmt.setDate(5, fecha);
        stmt.execute();
    }
}
