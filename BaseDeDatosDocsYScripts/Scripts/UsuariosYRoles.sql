use proyecto; 
-- Eliminar usuarios si existen
Drop user if exists 'HectorRojas'@'localhost';
Drop user if exists 'EmpleadoSede'@'localhost';
Drop user if exists 'EmpleadoFabrica'@'localhost';
Drop user if exists 'EmpleadoSedeExperto'@'localhost';
Drop user if exists 'EmpleadoFabricaExperto'@'localhost';
-- creacion de usuarios
Create user 'HectorRojas'@'localhost' identified by 'DirectorAmnesia';

Create user 'EmpleadoSede'@'localhost' identified by 'AmnesiaBar';
Create user 'EmpleadoSedeExperto'@'localhost' identified by 'GerenteSede';

Create user 'EmpleadoFabrica'@'localhost' identified by 'FabricaAmnesia';
Create user 'EmpleadoFabricaExperto'@'localhost' identified by 'GerenteFabrica';
-- otorgar permisos a tablas:
-- (Admin) Hector Rojas: se otorga el permiso a toda la base de datos
Grant all privileges on *.* to 'HectorRojas'@'localhost'; 
-- (EmpleadoSede) Simple y experto(gerente) :

-- GERENTE SEDE:
Grant select on proyecto.sede to 'EmpleadoSedeExperto'@'localhost';
Grant select on proyecto.fabrica to 'EmpleadoSedeExperto'@'localhost';
Grant select, insert, update on proyecto.distribuidora to 'EmpleadoSedeExperto'@'localhost';
Grant select, insert, update on proyecto.empleado to 'EmpleadoSedeExperto'@'localhost';
Grant select, insert, update on proyecto.empleadoSede to 'EmpleadoSedeExperto'@'localhost';
Grant insert, select, update on proyecto.persona to 'EmpleadoSedeExperto'@'localhost';
Grant select, insert on proyecto.cliente to 'EmpleadoSedeExperto'@'localhost';
Grant select, insert, update on proyecto.venta to 'EmpleadoSedeExperto'@'localhost';
Grant select, insert, update, delete on proyecto.parteVenta to 'EmpleadoSedeExperto'@'localhost';
Grant insert, select on proyecto.producto to 'EmpleadoSedeExperto'@'localhost';
Grant insert, select on proyecto.comida to 'EmpleadoSedeExperto'@'localhost';
Grant insert, select on proyecto.licor to 'EmpleadoSedeExperto'@'localhost';
Grant insert, select, update on proyecto.licorMarca to 'EmpleadoSedeExperto'@'localhost';
Grant insert, select, update on proyecto.cervezaArtesanal to 'EmpleadoSedeExperto'@'localhost';
Grant select, insert, update on proyecto.composicionComida to 'EmpleadoSedeExperto'@'localhost';
Grant select, insert, update on proyecto.materiaPrimaComida to 'EmpleadoSedeExperto'@'localhost';
Grant select, insert, update on proyecto.facturaMateriaPrimaComida to 'EmpleadoSedeExperto'@'localhost';
Grant select, insert, update on proyecto.facturaLicor to 'EmpleadoSedeExperto'@'localhost';
Grant select on proyecto.vw_inventariolicorsede to 'EmpleadoSedeExperto'@'localhost';
Grant select on proyecto.vw_consultaventas to 'EmpleadoSedeExperto'@'localhost';
Grant select on proyecto.vw_registroMateriaPrimaFecha to 'EmpleadoSedeExperto'@'localhost';

## Grant select on mysql.proc to 'EmpleadoSedeExperto'@'localhost';
Grant execute on procedure proyecto.registrarhorastrabajoEmpleadoSede to 'EmpleadoSedeExperto'@'localhost';
Grant execute on procedure proyecto.registrarCervezaArtesanal to 'EmpleadoSedeExperto'@'localhost';
Grant execute on procedure proyecto.registrarDistribuidora to 'EmpleadoSedeExperto'@'localhost';
Grant execute on procedure proyecto.registrarLicorMarca to 'EmpleadoSedeExperto'@'localhost';
Grant execute on procedure proyecto.registrarAdquisicionLicor to 'EmpleadoSedeExperto'@'localhost';
Grant execute on procedure proyecto.registrarComida to 'EmpleadoSedeExperto'@'localhost';
Grant execute on procedure proyecto.registrarMateriaPrimaComida to 'EmpleadoSedeExperto'@'localhost';
Grant execute on procedure proyecto.registrarAdquisicionMatPrimaComida to 'EmpleadoSedeExperto'@'localhost';
Grant execute on procedure proyecto.registrarComposicionComida to 'EmpleadoSedeExperto'@'localhost';
Grant execute on procedure proyecto.registrarVenta to 'EmpleadoSedeExperto'@'localhost';
Grant execute on procedure proyecto.registrarParteVenta to 'EmpleadoSedeExperto'@'localhost';
Grant execute on procedure proyecto.borrarParteVenta to 'EmpleadoSedeExperto'@'localhost';
Grant execute on procedure proyecto.buscarProducto to 'EmpleadoSedeExperto'@'localhost';
Grant execute on procedure proyecto.inventariolicorSede to 'EmpleadoSedeExperto'@'localhost';
Grant execute on procedure proyecto.inventariomateriaprimaSede to 'EmpleadoSedeExperto'@'localhost';
Grant execute on procedure proyecto.totalventasProducto to 'EmpleadoSedeExperto'@'localhost';
Grant execute on procedure proyecto.reiniciarHorasTrabajo to 'EmpleadoSedeExperto'@'localhost';
Grant execute on procedure proyecto.obtenerDistribucionesPorSede to 'EmpleadoSedeExperto'@'localhost';
Grant execute on procedure proyecto.obtenerDistribucionesPorCerveza to 'EmpleadoSedeExperto'@'localhost';

##Grant execute on function calcularMontoTotalVentasCliente to 'EmpleadoSedeExperto'@'localhost';
##Grant execute on function aplicarDescuentoCliente to 'EmpleadoSedeExperto'@'localhost';

-- EmpleadoSedeSimple
Grant insert, select, update on proyecto.persona to 'EmpleadoSede'@'localhost';
Grant select, insert on proyecto.cliente to 'EmpleadoSede'@'localhost';
Grant select, insert, update on proyecto.venta to 'EmpleadoSede'@'localhost';
Grant select, insert, update on proyecto.parteVenta to 'EmpleadoSede'@'localhost';
Grant select on proyecto.producto to 'EmpleadoSede'@'localhost';
Grant select on proyecto.comida to 'EmpleadoSede'@'localhost';
Grant select on proyecto.licor to 'EmpleadoSede'@'localhost';
Grant select, update on proyecto.licorMarca to 'EmpleadoSede'@'localhost';
Grant select, update on proyecto.cervezaArtesanal to 'EmpleadoSede'@'localhost';
Grant select on proyecto.composicionComida to 'EmpleadoSede'@'localhost';
Grant select, update on proyecto.materiaPrimaComida to 'EmpleadoSede'@'localhost';

Grant execute on procedure proyecto.registrarVenta to 'EmpleadoSede'@'localhost';
Grant execute on procedure proyecto.registrarParteVenta to 'EmpleadoSede'@'localhost';
Grant execute on procedure proyecto.buscarProducto to 'EmpleadoSede'@'localhost';
Grant execute on procedure proyecto.inventariolicorSede to 'EmpleadoSede'@'localhost';
Grant execute on procedure proyecto.inventariomateriaprimaSede to 'EmpleadoSede'@'localhost';
Grant execute on procedure proyecto.totalventasProducto to 'EmpleadoSede'@'localhost';
Grant execute on procedure proyecto.registrarCliente to 'EmpleadoSede'@'localhost';

#Grant execute on function calcularMontoTotalVentasCliente to 'EmpleadoSede'@'localhost';
#Grant execute on function aplicarDescuentoCliente to 'EmpleadoSede'@'localhost';

-- (EmpleadoFabrica) Simple y experto (gerente):
-- GERENTE FABRICA:
Grant select, insert, update on proyecto.afiliado to 'EmpleadoFabricaExperto'@'localhost'; 
Grant select on proyecto.fabrica to 'EmpleadoFabricaExperto'@'localhost';
Grant select, update, insert on proyecto.distribucion to 'EmpleadoFabricaExperto'@'localhost';
Grant select, update, insert on proyecto.distribucionSede to 'EmpleadoFabricaExperto'@'localhost';
Grant select on proyecto.producto to 'EmpleadoFabricaExperto'@'localhost';
Grant update, insert, select on proyecto.facturaMateriaPrimaCerveza to 'EmpleadoFabricaExperto'@'localhost';
Grant select, insert, update on proyecto.composicionCerveza to 'EmpleadoFabricaExperto'@'localhost';
Grant select on proyecto.distribuidora to 'EmpleadoFabricaExperto'@'localhost';
Grant select, update on proyecto.cervezaArtesanal to 'EmpleadoFabricaExperto'@'localhost'; 
Grant select on proyecto.composicionCerveza  to 'EmpleadoFabricaExperto'@'localhost'; 
Grant select, update on proyecto.materiaPrimaCerveza to 'EmpleadoFabricaExperto'@'localhost'; 
Grant insert, update on proyecto.produccion to 'EmpleadoFabricaExperto'@'localhost'; 
Grant select, insert on proyecto.sede to 'EmpleadoFabricaExperto'@'localhost';

##Grant select on mysql.proc to 'EmpleadoFabricaExperto'@'localhost';
Grant execute on procedure proyecto.registrarProduccion to 'EmpleadoFabricaExperto'@'localhost';
Grant execute on procedure proyecto.inventariomateriaprimaCerveza to 'EmpleadoFabricaExperto'@'localhost'; 
Grant execute on procedure proyecto.registrarAdquisicionMatPrimaCerveza to 'EmpleadoFabricaExperto'@'localhost';
Grant execute on procedure proyecto.registrarComposicionCerveza to 'EmpleadoFabricaExperto'@'localhost'; 
Grant execute on procedure proyecto.registrarDistribucion to 'EmpleadoFabricaExperto'@'localhost'; 
Grant execute on procedure proyecto.registrarAfiliado to 'EmpleadoFabricaExperto'@'localhost'; 
Grant execute on procedure proyecto.obtenerDistribucionesPorSede to 'EmpleadoFabricaExperto'@'localhost'; 
Grant execute on procedure proyecto.obtenerDistribucionesPorCerveza to 'EmpleadoFabricaExperto'@'localhost'; 

-- EmpleadoFabricaSimple
Grant select, update on proyecto.cervezaArtesanal to 'EmpleadoFabrica'@'localhost'; 
Grant select on proyecto.composicionCerveza  to 'EmpleadoFabrica'@'localhost'; 
Grant select, update on proyecto.materiaPrimaCerveza to 'EmpleadoFabrica'@'localhost'; 
Grant insert, update  on proyecto.produccion to 'EmpleadoFabrica'@'localhost'; 
Grant select, insert on proyecto.sede to 'EmpleadoFabrica'@'localhost'; 
Grant execute on procedure proyecto.inventariomateriaprimaCerveza to 'EmpleadoFabrica'@'localhost'; 
Grant execute on procedure proyecto.registrarProduccion to 'EmpleadoFabrica'@'localhost'; 

-- actualizar permisos
Flush privileges; 