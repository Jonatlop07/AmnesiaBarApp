use proyecto;

DROP VIEW IF EXISTS vw_consultaVentas;
DROP VIEW IF EXISTS vw_comprasLicoresFecha;
DROP VIEW IF EXISTS vw_comprasMateriaPrimaComidaFecha;
DROP VIEW IF EXISTS vw_comprasMateriaPrimaCervezaFecha;
DROP VIEW IF EXISTS vw_compras;
DROP VIEW IF EXISTS vw_totalComprasFecha;
DROP VIEW IF EXISTS vw_totalVentasFecha;
DROP VIEW IF EXISTS vw_totalComprasHoy;
DROP VIEW IF EXISTS vw_totalVentasHoy;
DROP VIEW IF EXISTS vw_inventarioLicorMarca;
DROP VIEW IF EXISTS vw_inventarioCerveza;
DROP VIEW IF EXISTS vw_totalProduccionPorCerveza;

DROP VIEW IF EXISTS vw_totalDistribucionesSedes;
DROP VIEW IF EXISTS vw_totalventasProducto;
DROP VIEW IF EXISTS vw_inventariomateriaprimacomida;
DROP VIEW IF EXISTS vw_cant_licores;
DROP VIEW IF EXISTS vw_inventariolicorsede;
DROP VIEW IF EXISTS vw_materiaPrimaGastada;
DROP VIEW IF EXISTS vw_registroMateriaPrimaFecha;
DROP VIEW IF EXISTS vw_parteVenta;
DROP VIEW IF EXISTS vw_facturaLicor;
DROP VIEW IF EXISTS vw_composicionComida;
DROP VIEW IF EXISTS vw_composicionCerveza;
DROP VIEW IF EXISTS vw_facturaMateriaPrimaComida;
DROP VIEW IF EXISTS vw_facturaMateriaPrimaCerveza;
DROP VIEW IF EXISTS vw_produccion;

CREATE VIEW vw_consultaVentas AS
SELECT ven_fecha, ven_id, venta.sede_sed_numeroSede as num_sede, cliente_persona_per_cedula as cedula,
	    par_id as item, pro_nombre, par_cantidad, pro_precio, ven_precio as total
FROM (
	venta JOIN parteVenta ON (ven_id = venta_ven_id 
							  AND cliente_persona_per_cedula = venta_cliente_persona_per_cedula 
							  AND sede_sed_numeroSede = venta_sede_sed_numeroSede)
	JOIN producto ON (producto_pro_nombre = pro_nombre)
) ORDER BY ven_id DESC, num_sede, par_id;

CREATE VIEW vw_comprasLicoresFecha AS SELECT fli_fecha as fecha, SUM(fli_precio) as precio FROM facturaLicor GROUP BY fli_fecha;
CREATE VIEW vw_comprasMateriaPrimaComidaFecha AS SELECT fco_fecha as fecha, SUM(fco_precio) as precio FROM facturaMateriaPrimaComida GROUP BY fco_fecha;
CREATE VIEW vw_comprasMateriaPrimaCervezaFecha AS SELECT fce_fecha as fecha, SUM(fce_precio) as precio FROM facturaMateriaPrimaCerveza GROUP BY fce_fecha;

/*CREATE VIEW vw_compras AS SELECT * FROM (
	vw_comprasLicoresFecha
    UNION vw_comprasMateriaPrimaComidaFecha
    UNION vw_comprasMateriaPrimaCervezaFecha
);*/

CREATE VIEW vw_compras AS SELECT * FROM vw_comprasLicoresFecha union SELECT * FROM vw_comprasMateriaPrimaComidaFecha union SELECT * FROM vw_comprasMateriaPrimaCervezaFecha;

CREATE VIEW vw_totalComprasFecha AS SELECT fecha, SUM(precio) as totalCompras FROM vw_compras GROUP BY fecha;
CREATE VIEW vw_totalVentasFecha AS SELECT ven_fecha as fecha, sum(ven_precio) as totalVentas FROM venta GROUP BY ven_fecha;  
CREATE VIEW vw_totalComprasHoy AS SELECT fecha, SUM(precio) as totalCompras FROM vw_compras WHERE fecha = CURDATE();
CREATE VIEW vw_totalVentasHoy AS SELECT ven_fecha as fecha, sum(ven_precio) as totalVentas FROM venta WHERE ven_fecha = CURDATE(); 
CREATE VIEW vw_totalventasProducto AS SELECT pro_nombre, SUM(par_cantidad) as cantidadTotal, SUM(par_cantidad * pro_precio) as totalVenta
FROM producto JOIN parteVenta ON (pro_nombre = producto_pro_nombre) 
GROUP BY pro_nombre ORDER BY venta_sede_sed_numeroSede, cantidadTotal DESC;

CREATE VIEW vw_inventarioLicorMarca AS 
SELECT sede_sed_numeroSede, pro_nombre, pro_precio, lic_presentacion_ml, lic_cantidad
FROM (
	producto JOIN licor ON (pro_nombre = producto_pro_nombre) 
	JOIN licorMarca ON (producto_pro_nombre = licor_producto_pro_nombre)
) ORDER BY (sede_sed_numeroSede);

CREATE VIEW vw_inventarioCerveza AS
SELECT sede_sed_numeroSede, pro_nombre, pro_precio, lic_presentacion_ml, cer_cantidadSede, cer_cantidadFabrica
FROM (
	producto JOIN licor ON (pro_nombre = producto_pro_nombre) 
	JOIN cervezaArtesanal ON (producto_pro_nombre = licor_producto_pro_nombre)
) ORDER BY (sede_sed_numeroSede);

CREATE VIEW vw_cant_licores AS SELECT licor_producto_pro_nombre, cer_cantidadSede as cantidad FROM cervezaArtesanal UNION SELECT licor_producto_pro_nombre, lic_cantidad as cantidad FROM licorMarca;

CREATE VIEW vw_inventariolicorsede AS SELECT sede_sed_numeroSede as num_sede, pro_nombre, pro_precio, lic_presentacion_ml, cantidad
FROM (
	producto JOIN licor ON (pro_nombre = producto_pro_nombre)
    JOIN vw_cant_licores ON (producto_pro_nombre = vw_cant_licores.licor_producto_pro_nombre)
) ORDER BY num_sede ASC, cantidad DESC;

CREATE VIEW vw_totalProduccionPorCerveza AS 
SELECT SUBSTRING_INDEX(pro_nombre, CONCAT(' ', sede_sed_numeroSede), 1) as nombreCerveza, SUM(pro_cantidad) as cantTotal, pro_nombre
FROM produccion JOIN cervezaArtesanal ON(cervezaArtesanal_licor_producto_pro_nombre = licor_producto_pro_nombre)
JOIN producto ON(licor_producto_pro_nombre = pro_nombre)
GROUP BY nombreCerveza
HAVING pro_nombre LIKE CONCAT(nombreCerveza, '%');

CREATE VIEW vw_totalDistribucionesSedes AS
SELECT cervezaArtesanal_licor_producto_pro_nombre AS nombre, SUM(dis_cantidad) AS cantidadDistribuida, sede_sed_numeroSede
FROM (
	(distribucionSede JOIN distribucion ON (distribucion_dis_id = dis_id))
    JOIN cervezaArtesanal ON (cervezaArtesanal_licor_producto_pro_nombre = licor_producto_pro_nombre)
) GROUP BY cervezaArtesanal_licor_producto_pro_nombre;

CREATE VIEW vw_inventariomateriaprimacomida AS SELECT * FROM materiaPrimaComida;
-- Consultar total de materiaPrimaGastada
CREATE VIEW vw_materiaPrimaGastada AS SELECT materiaPrimaComida_mpco_nombre AS ingrediente, SUM(par_cantidad * cmp_cantidad) AS cantidadTotal
FROM parteVenta JOIN composicionComida ON (producto_pro_nombre = comida_producto_pro_nombre) 
GROUP BY materiaPrimaComida_mpco_nombre;

-- Consultar materia prima gastada por fecha
CREATE VIEW vw_registroMateriaPrimaFecha AS 
SELECT ven_fecha, materiaPrimaComida_mpco_nombre AS ingrediente, SUM(par_cantidad * cmp_cantidad) AS cantidad
FROM venta JOIN parteVenta ON (ven_id = venta_ven_id) JOIN composicionComida ON (producto_pro_nombre = comida_producto_pro_nombre) 
GROUP BY ingrediente, ven_fecha ORDER BY ven_fecha, ingrediente;

CREATE VIEW vw_parteVenta AS SELECT par_id, venta_ven_id, producto_pro_nombre FROM parteVenta;
CREATE VIEW vw_facturaLicor AS SELECT licorMarca_licor_producto_pro_nombre, distribuidora_dis_nombre, fli_fecha FROM facturaLicor;
CREATE VIEW vw_composicionComida AS SELECT comida_producto_pro_nombre, materiaPrimaComida_mpco_nombre FROM composicionComida;
CREATE VIEW vw_composicionCerveza AS SELECT cervezaArtesanal_nombre, materiaPrimaCerveza_mpce_nombre FROM composicionCerveza;
CREATE VIEW vw_facturaMateriaPrimaComida AS SELECT materiaPrimaComida_mpco_nombre, distribuidora_dis_nombre, fco_precio, fco_fecha FROM facturaMateriaPrimaComida;
CREATE VIEW vw_facturaMateriaPrimaCerveza AS SELECT materiaPrimaCerveza_mpce_nombre, distribuidora_dis_nombre, fce_precio, fce_fecha FROM facturaMateriaPrimaCerveza;
CREATE VIEW vw_produccion AS SELECT fabrica_fab_id, cervezaArtesanal_licor_producto_pro_nombre, pro_fecha FROM produccion;