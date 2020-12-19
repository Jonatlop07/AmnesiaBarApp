use proyecto;

DROP PROCEDURE IF EXISTS registrarfabrica; 
DROP PROCEDURE IF EXISTS registrarSede; 
DROP PROCEDURE IF EXISTS registrarEmpleadoSede; 
DROP PROCEDURE IF EXISTS registrarhorastrabajoEmpleadoSede;
DROP PROCEDURE IF EXISTS registrarEmpleadoFabrica;
DROP PROCEDURE IF EXISTS registrarEmpleado;
DROP PROCEDURE IF EXISTS registrarCervezaArtesanal;
DROP PROCEDURE IF EXISTS registrarMateriaPrimaCerveza;
DROP PROCEDURE IF EXISTS registrarDistribuidora;
DROP PROCEDURE IF EXISTS registrarAdquisicionMatPrimaCerveza;
DROP PROCEDURE IF EXISTS registrarComposicionCerveza;
DROP PROCEDURE IF EXISTS registrarProduccion;
DROP PROCEDURE IF EXISTS registrarDistribucion;
DROP PROCEDURE IF EXISTS registrarAfiliado;
DROP PROCEDURE IF EXISTS registrarLicorMarca;
DROP PROCEDURE IF EXISTS registrarAdquisicionLicor;
DROP PROCEDURE IF EXISTS registrarComida;
DROP PROCEDURE IF EXISTS registrarMateriaPrimaComida;
DROP PROCEDURE IF EXISTS registrarAdquisicionMatPrimaComida;
DROP PROCEDURE IF EXISTS registrarComposicionComida;
DROP PROCEDURE IF EXISTS registrarCliente; 
DROP PROCEDURE IF EXISTS registrarVenta;
DROP PROCEDURE IF EXISTS registrarParteVenta;
DROP PROCEDURE IF EXISTS buscarProducto;
DROP PROCEDURE IF EXISTS borrarParteVenta;
DROP PROCEDURE IF EXISTS reiniciarHorasTrabajo;
DROP PROCEDURE IF EXISTS inventariolicorSede;
DROP PROCEDURE IF EXISTS inventariomateriaprimaSede;
DROP PROCEDURE IF EXISTS inventariomateriaprimaCerveza;
DROP PROCEDURE IF EXISTS totalventasProducto;
DROP PROCEDURE IF EXISTS obtenerDistribucionesPorSede;
DROP PROCEDURE IF EXISTS obtenerDistribucionesPorCerveza;

DELIMITER $

CREATE PROCEDURE registrarfabrica (fabricaciudad VARCHAR (25), fabricacalle VARCHAR(15), fabricacarrera VARCHAR(15))
BEGIN
	INSERT INTO fabrica (fab_ciudad,fab_calle,fab_carrera) VALUES (fabricaciudad,fabricacalle,fabricacarrera);
END $


CREATE PROCEDURE registrarSede (sedeciudad VARCHAR (25), sedebarrio VARCHAR (30), sedecalle VARCHAR(15), sedecarrera VARCHAR(15))
BEGIN
	INSERT INTO sede (sed_ciudad,sed_barrio,sed_calle,sed_carrera) VALUES (sedeciudad,sedebarrio,sedecalle,sedecarrera);
END $


CREATE PROCEDURE registrarEmpleado (idempleado BIGINT, nombreempleado VARCHAR(15), apellidoempleado VARCHAR (30),
								    edadempleado INT, telefonoempleado VARCHAR(15), direccionempleado VARCHAR(30)) 
BEGIN
	IF (NOT (idempleado IN (SELECT persona_per_cedula FROM empleado))) THEN 
		INSERT INTO persona VALUES (idempleado,nombreempleado,apellidoempleado); 
		INSERT INTO empleado VALUES (idempleado, edadempleado, telefonoempleado, direccionempleado, 0);
	END IF;
END $ 


CREATE PROCEDURE registrarEmpleadoSede(idempleado BIGINT, numsede INT)
BEGIN 
	IF (idempleado IN (SELECT persona_per_cedula FROM empleado)) AND NOT (idempleado IN (SELECT empleado_persona_per_cedula FROM empleadoSede)) THEN
			INSERT INTO empleadoSede values (idempleado, 0, numsede);
	END IF;
END $


CREATE PROCEDURE registrarhorastrabajoEmpleadoSede (idempleado BIGINT, horas INT) 
BEGIN
	START TRANSACTION;
		UPDATE empleadoSede SET empSed_per_horasTrabajo = horas WHERE empleado_persona_per_cedula = idempleado;
		UPDATE empleado SET emp_salario = (horas * 4000) WHERE persona_per_cedula = idempleado;
    COMMIT;
END $


CREATE PROCEDURE registrarEmpleadoFabrica(idempleado BIGINT, numfab INT) 
BEGIN 
	IF ((idempleado IN (SELECT persona_per_cedula FROM empleado)) AND NOT (idempleado IN (SELECT empleado_persona_per_cedula FROM empleadoFabrica))) THEN
		START TRANSACTION;
			INSERT INTO empleadoFabrica values (idempleado, numfab);
			UPDATE empleado SET emp_salario = 828116 WHERE persona_per_cedula = idempleado;
		COMMIT;
	END IF;
END $


CREATE PROCEDURE registrarCervezaArtesanal(nombre VARCHAR(30), precio DOUBLE, numSed INT(2), mililitros DOUBLE, tipo VARCHAR(30), idFab INT(2))
BEGIN
	DECLARE _nombre VARCHAR(30);
	SET _nombre = CONCAT(nombre, ' ', numSed);

	IF (NOT ((_nombre, numSed) IN (SELECT pro_nombre, sede_sed_numeroSede FROM producto))) THEN
		START TRANSACTION;
			INSERT INTO producto VALUES (_nombre, precio, numSed);
			INSERT INTO licor VALUES (_nombre, mililitros);
			INSERT INTO cervezaArtesanal VALUES (_nombre, tipo, 0, 0, idFab);
        COMMIT;
	END IF;
END $


CREATE PROCEDURE registrarMateriaPrimaCerveza(nombreMat VARCHAR(30), idFab INT(2))
BEGIN
	SET nombreMat = CONCAT(nombreMat, ' ', idFab);
	IF (NOT (nombreMat IN (SELECT mpce_nombre FROM materiaPrimaCerveza))) THEN
		INSERT INTO materiaPrimaCerveza VALUES (nombreMat, 0, idFab);
	END IF;
END $


CREATE PROCEDURE registrarDistribuidora(nombreDist VARCHAR(45), telDistribuidora VARCHAR(15) )
BEGIN
	IF (NOT (nombreDist IN (SELECT dis_nombre FROM distribuidora))) THEN
		INSERT INTO distribuidora VALUES (nombreDist, telDistribuidora);
	END IF;
END $


CREATE PROCEDURE registrarAdquisicionMatPrimaCerveza(nombreDist VARCHAR(45), nombreMat VARCHAR(30), precioCompra DOUBLE, cantidadCompra DOUBLE, fechaCompra DATE)
BEGIN
	IF (NOT ((nombreMat, nombreDist, precioCompra, fechaCompra) IN (SELECT * FROM vw_facturaMateriaPrimaCerveza))) THEN
		INSERT INTO facturaMateriaPrimaCerveza VALUES (nombreDist, nombreMat, precioCompra, cantidadCompra, fechaCompra);
	ELSE
		UPDATE facturaMateriaPrimaCerveza SET fce_cantidad = fce_cantidad + cantidadCompra
		WHERE fce_fecha = fechaCompra AND materiaPrimaCerveza_mpce_nombre = nombreMat
		AND distribuidora_dis_nombre = nombreDist AND fce_precio = precioCompra;
	END IF;
		UPDATE materiaPrimaCerveza SET mpce_cantidad = mpce_cantidad + cantidadCompra WHERE mpce_nombre = nombreMat;
END $


CREATE PROCEDURE registrarComposicionCerveza(nombreCerveza VARCHAR(30), nombreMat VARCHAR(30), cantidad DOUBLE, idFabrica INT(2))
BEGIN 
	DECLARE pertenecenMismaFabrica BOOL DEFAULT FALSE;
    SET pertenecenMismaFabrica = ((SELECT fabrica_fab_id FROM cervezaArtesanal WHERE licor_producto_pro_nombre = nombreCerveza) 
    = (SELECT fabrica_fab_ID FROM materiaPrimaCerveza WHERE mpce_nombre = nombreMat));
    
	IF (pertenecenMismaFabrica AND NOT ((nombreCerveza, nombreMat) IN (SELECT * FROM vw_composicionCerveza))) THEN
		INSERT INTO composicionCerveza VALUES (nombreCerveza, nombreMat, cantidad);
	ELSEIF (pertenecenMismaFabrica) THEN
		UPDATE composicionCerveza SET cmp_cantidad = cantidad 
        WHERE cervezaArtesanal_nombre = nombreCerveza AND materiaPrimaCerveza_mpce_nombre = nombreMat;
    END IF;
END $


CREATE PROCEDURE registrarProduccion(fechaPro DATE, cantidadPro DOUBLE, idFab INT(2), nombreCerveza VARCHAR(30))
BEGIN
	DECLARE _nombreCerveza VARCHAR(30) DEFAULT '';
	DECLARE nombreMateria VARCHAR(30) DEFAULT '';
	DECLARE cantRestante DOUBLE DEFAULT 0.0;
	DECLARE termina BOOL DEFAULT FALSE;
	DECLARE numInsuficiencias INT DEFAULT 0;
    DECLARE delimitador VARCHAR(3);

	DECLARE cr_materias CURSOR
	FOR SELECT mpce_nombre, (mpce_cantidad - ((cantidadPro / 1000) * cmp_cantidad)) AS cantidadRestante
		FROM cervezaArtesanal JOIN composicionCerveza ON (licor_producto_pro_nombre = cervezaArtesanal_nombre)
		JOIN materiaPrimaCerveza ON (materiaPrimaCerveza_mpce_nombre = mpce_nombre)
		WHERE cervezaArtesanal_nombre = nombreCerveza AND materiaPrimaCerveza.fabrica_fab_id = idFab;

	DECLARE CONTINUE HANDLER
	FOR SQLSTATE '02000'
		SET termina = TRUE;

	IF (nombreCerveza IN (SELECT licor_producto_pro_nombre FROM cervezaArtesanal)) THEN
		SET delimitador = CONCAT(' ', (SELECT sede_sed_numeroSede FROM producto WHERE pro_nombre = nombreCerveza));
		SET _nombreCerveza = SUBSTRING_INDEX(nombreCerveza, delimitador, 1);
        
		SELECT COUNT(*) INTO numInsuficiencias FROM (SELECT mpce_cantidad, mpce_nombre, (mpce_cantidad - (cantidadPro / 1000) * cmp_cantidad) 
        AS cantidadGast FROM composicionCerveza JOIN materiaPrimaCerveza ON (materiaPrimaCerveza_mpce_nombre = mpce_nombre)
		WHERE cervezaArtesanal_nombre = nombreCerveza) AS comparacionCantidades WHERE cantidadGast > mpce_cantidad;
		START TRANSACTION;
			IF (numInsuficiencias <= 0) THEN
				OPEN cr_materias;
					Recorre_Cursor: LOOP
						FETCH cr_materias INTO nombreMateria, cantRestante;

						IF termina THEN
							LEAVE Recorre_Cursor;
						END IF;

						UPDATE materiaPrimaCerveza SET mpce_cantidad = cantRestante
						WHERE mpce_nombre = nombreMateria;
					END LOOP;
				CLOSE cr_materias;
				
				IF (NOT ((idFab, nombreCerveza, fechaPro) IN (SELECT * FROM vw_produccion))) THEN
					INSERT INTO produccion (pro_fecha, pro_cantidad, fabrica_fab_id,
											cervezaArtesanal_licor_producto_pro_nombre) 
					VALUES (fechaPro, cantidadPro, idFab, nombreCerveza);
					UPDATE cervezaArtesanal SET cer_cantidadFabrica = cer_cantidadFabrica + cantidadPro
					WHERE licor_producto_pro_nombre LIKE CONCAT(_nombreCerveza, '%') AND fabrica_fab_id = idFab;
				ELSE 
					UPDATE produccion SET pro_cantidad = pro_cantidad + cantidadPro 
					WHERE (
						pro_fecha = fechaPro
						AND fabrica_fab_id = idFab
						AND cervezaArtesanal_licor_producto_pro_nombre = nombreCerveza);
					
					UPDATE cervezaArtesanal SET cer_cantidadFabrica = cer_cantidadFabrica + cantidadPro
					WHERE licor_producto_pro_nombre LIKE CONCAT(_nombreCerveza, '%') AND fabrica_fab_id = idFab;
				END IF;
			END IF;
        COMMIT;
	END IF;
END $


CREATE PROCEDURE registrarDistribucion(fecha DATE, cantidad DOUBLE, nombreCerveza VARCHAR(30), idAfiliado BIGINT, telefono VARCHAR(15), numSed INT (2), idFab INT(2))
BEGIN
	DECLARE cantMil DOUBLE DEFAULT 0.0;
	DECLARE delimitador VARCHAR(3);
    DECLARE _nombreCerveza VARCHAR(30) DEFAULT '';
    DECLARE idDis INT DEFAULT 0;

    IF (cantidad <= (SELECT cer_cantidadFabrica FROM cervezaArtesanal WHERE licor_producto_pro_nombre = nombreCerveza)) THEN
		SET delimitador = CONCAT(' ', (SELECT sede_sed_numeroSede FROM producto WHERE pro_nombre = nombreCerveza));
		SET _nombreCerveza = SUBSTRING_INDEX(nombreCerveza, delimitador, 1);
        
        START TRANSACTION;
			UPDATE cervezaArtesanal SET cer_cantidadFabrica = cer_cantidadFabrica - cantidad
			WHERE licor_producto_pro_nombre LIKE CONCAT(_nombreCerveza, '%') AND fabrica_fab_id = idFab;
				
			IF ((fecha, nombreCerveza) IN (SELECT dis_fecha, cervezaArtesanal_licor_producto_pro_nombre FROM distribucion)) THEN
				UPDATE distribucion SET dis_cantidad = dis_cantidad + cantidad 
				WHERE dis_fecha = fecha AND cervezaArtesanal_licor_producto_pro_nombre = nombreCerveza;
			ELSE
				INSERT INTO distribucion (dis_fecha, dis_cantidad, cervezaArtesanal_licor_producto_pro_nombre, afiliado_afi_id) VALUES 
					(fecha, cantidad, nombreCerveza , idAfiliado);
			END IF;
			
			IF (idAfiliado IS NULL) THEN
				SELECT dis_id INTO idDis FROM distribucion JOIN cervezaArtesanal ON (cervezaArtesanal_licor_producto_pro_nombre = licor_producto_pro_nombre)
				WHERE dis_fecha = fecha AND cervezaArtesanal_licor_producto_pro_nombre = nombreCerveza AND fabrica_fab_id = idFab;
				
				IF (NOT ((numSed, idDis) IN (SELECT sede_Sed_numeroSede, distribucion_dis_id FROM distribucionSede))) THEN
					INSERT INTO distribucionSede VALUES (numSed, idDis);
				END IF;
				
				UPDATE cervezaArtesanal SET cer_cantidadSede = cer_cantidadSede + cantidad WHERE licor_producto_pro_nombre = nombreCerveza;
			ELSE 
				CALL registrarAfiliado(idAfiliado, telefono);
			END IF;
		COMMIT;
    END IF;
END $


CREATE PROCEDURE registrarAfiliado(idAfiliado BIGINT, telefono VARCHAR(15))
BEGIN
	IF (NOT (idAfiliado IN (SELECT afi_id FROM afiliado))) THEN
		INSERT INTO afiliado VALUES (idAfiliado, telefono);
	ELSE 
		UPDATE afiliado SET afi_telefono = telefono WHERE afi_id = idAfiliado;
	END IF;
END $


CREATE PROCEDURE registrarLicorMarca(nombre VARCHAR(30), precioPro DOUBLE, numSed INT(2), pre_ml DOUBLE)
BEGIN
    SET nombre = CONCAT(nombre, ' ', numSed);
	IF (NOT ((nombre, numSed) IN (SELECT pro_nombre, sede_sed_numeroSede FROM producto))) THEN
		START TRANSACTION;
			INSERT INTO producto VALUES (nombre, precioPro, numSed);
			INSERT INTO licor VALUES (nombre, pre_ml);
			INSERT INTO licorMarca VALUES (nombre, 0);
        COMMIT;
	END IF;
END $


CREATE PROCEDURE registrarAdquisicionLicor(nombrePro VARCHAR(30), nombreDist VARCHAR(45), precioCompra DOUBLE, cantidadCompra INT, fechaCompra DATE)
BEGIN
	START TRANSACTION;
		IF  (NOT ((nombrePro, nombreDist, fechaCompra) IN (SELECT * FROM vw_facturaLicor))) THEN
			INSERT INTO facturaLicor VALUES (nombrePro, nombreDist, precioCompra, cantidadCompra, fechaCompra);
		ELSE
			UPDATE facturaLicor SET fli_cantidad = fli_cantidad + cantidadCompra 
			WHERE ( 
				fli_fecha = fechaCompra 
				AND licorMarca_licor_producto_pro_nombre = nombrePro
				AND distribuidora_dis_nombre = nombreDist);
		END IF;
		
		UPDATE licorMarca SET lic_cantidad = lic_cantidad + cantidadCompra WHERE licor_producto_pro_nombre = nombrePro;
    COMMIT;
END $


CREATE PROCEDURE registrarComida(nombre VARCHAR(30), precio DOUBLE, numSed INT(2))
BEGIN
    SET nombre = CONCAT(nombre, ' ', numSed);
	IF (NOT ((nombre, numSed) IN (SELECT pro_nombre, sede_sed_numeroSede FROM producto))) THEN
		START TRANSACTION;
			INSERT INTO producto VALUES (nombre, precio, numSed);
			INSERT INTO comida VALUES (nombre);
        COMMIT;
	END IF;
END $


CREATE PROCEDURE registrarMateriaPrimaComida(nombreMat VARCHAR(30), numSed INT(2))
BEGIN
	SET nombreMat = CONCAT(nombreMat, ' ', numSed);
	IF (NOT (nombreMat IN (SELECT mpco_nombre FROM materiaPrimaComida))) THEN
		INSERT INTO materiaPrimaComida VALUES (nombreMat, 0, numSed);
	END IF;
END $


CREATE PROCEDURE registrarAdquisicionMatPrimaComida (nombreDist VARCHAR(45), nombreMat VARCHAR(30), precioCompra DOUBLE, cantidadCompra DOUBLE, fechaCompra DATE)
BEGIN
	START TRANSACTION;
		IF (NOT ((nombreMat, nombreDist, precioCompra, fechaCompra) IN (SELECT * FROM vw_facturaMateriaPrimaComida))) THEN
			INSERT INTO facturaMateriaPrimaComida VALUES (nombreDist, nombreMat, precioCompra, cantidadCompra, fechaCompra);
		ELSE
			UPDATE facturaMateriaPrimaComida SET fco_cantidad = fco_cantidad + cantidadCompra 
			WHERE fco_fecha = fechaCompra AND materiaPrimaComida_mpco_nombre = nombreMat
			AND distribuidora_dis_nombre = nombreDist AND fco_precio = precioCompra;
		END IF;
		
		UPDATE materiaPrimaComida SET mpco_cantidad = mpco_cantidad + cantidadCompra WHERE mpco_nombre = nombreMat;
	COMMIT;
END $


CREATE PROCEDURE registrarComposicionComida (nombreComida VARCHAR(30), nombreMat VARCHAR(30), cantidadMat DOUBLE, idSede INT(2))
BEGIN
	DECLARE pertenecenMismaSede BOOL DEFAULT FALSE;
    SET pertenecenMismaSede = ((SELECT sede_sed_numeroSede FROM producto WHERE pro_nombre = nombreComida) 
    = (SELECT sede_sed_id FROM materiaPrimaComida WHERE mpco_nombre = nombreMat));
    
	IF (pertenecenMismaSede AND NOT ((nombreComida, nombreMat) IN (SELECT * FROM vw_composicionComida))) THEN
		INSERT INTO composicionComida VALUES (nombreComida, nombreMat, cantidadMat);
	ELSEIF pertenecenMismaSede THEN
		UPDATE composicionComida SET cmp_cantidad = cantidadMat 
        WHERE comida_producto_pro_nombre = nombreComida AND materiaPrimaComida_mpco_nombre = nombreMat;
	END IF;
END $


CREATE PROCEDURE registrarCliente (idcliente BIGINT, nombrecliente VARCHAR(45), apellidocliente VARCHAR (45))
BEGIN 
	IF (NOT (idcliente IN (SELECT persona_per_cedula FROM cliente))) THEN
		START TRANSACTION;
			INSERT INTO persona VALUES (idcliente, nombrecliente, apellidocliente);
			INSERT INTO cliente VALUES (idcliente);
        COMMIT;
	END IF;
END $


CREATE PROCEDURE registrarVenta (cedula INT, numSed INT(2), fecha DATE)
BEGIN
	INSERT INTO venta (cliente_persona_per_cedula, sede_sed_numeroSede, ven_precio, ven_fecha) VALUES
			(cedula, numSed, 0.0, fecha);
END $


CREATE PROCEDURE registrarParteVenta(idParte INT, idVenta INT(2), nombrePro VARCHAR(30), cantidad INT)
BEGIN
    DECLARE cantDisponible DOUBLE DEFAULT 0;
	DECLARE tipoProducto VARCHAR(30);
	DECLARE nombreMateria VARCHAR(30) DEFAULT '';
	DECLARE cantRestante DOUBLE DEFAULT 0.0;
	DECLARE termina BOOL DEFAULT FALSE;
    
    DECLARE cr_materias CURSOR 
        		FOR SELECT mpco_nombre, (mpco_cantidad  - cantidad * cmp_cantidad) AS cantRes 
        			FROM parteVenta JOIN composicionComida ON (producto_pro_nombre = comida_producto_pro_nombre) 
				    JOIN materiaPrimaComida ON (materiaPrimaComida_mpco_nombre = mpco_nombre)
				    WHERE venta_ven_id = idVenta AND comida_producto_pro_nombre = nombrePro;
	
    DECLARE CONTINUE HANDLER
				FOR SQLSTATE '02000'
				SET termina = TRUE;

	SET tipoProducto = nombrePro;
	SET cantDisponible = cantidad;
	
	CALL buscarProducto(tipoProducto, cantDisponible);
    
    IF (tipoProducto = 'cervezaArtesanal') THEN
		SET cantDisponible = cantDisponible / (SELECT lic_presentacion_ml FROM licor WHERE producto_pro_nombre = nombrePro);
    END IF;

	IF (cantDisponible > 0 AND cantidad <= cantDisponible) THEN
		START TRANSACTION;
			IF (NOT ((idParte, idVenta, nombrePro) IN (SELECT * FROM vw_parteVenta))) THEN
				INSERT INTO parteVenta VALUES 
					(idParte, idVenta, (SELECT cliente_persona_per_cedula FROM venta WHERE ven_id = idVenta), 
					 (SELECT sede_sed_numeroSede FROM venta WHERE ven_id = idVenta), nombrePro, cantidad);
			ELSE 
				UPDATE parteVenta SET par_cantidad = par_cantidad + cantidad
				WHERE par_id = idParte AND venta_ven_id = idVenta AND producto_pro_nombre = nombrePro;
			END IF;

			IF (tipoProducto = 'comida') THEN
				OPEN cr_materias;
					Recorre_Cursor: LOOP
						FETCH cr_materias INTO nombreMateria, cantRestante;

						IF termina THEN
							LEAVE Recorre_Cursor;
						END IF;

						UPDATE materiaPrimaComida SET mpco_cantidad = cantRestante WHERE mpco_nombre = nombreMateria;
					END LOOP;
				CLOSE cr_materias;
				
				UPDATE venta 
				SET ven_precio = ven_precio + cantidad * (SELECT pro_precio FROM producto WHERE pro_nombre = nombrePro)
				WHERE ven_id = idVenta;
			ELSEIF (tipoProducto = 'licorMarca') THEN
				UPDATE licorMarca SET lic_cantidad = lic_cantidad - cantidad WHERE licor_producto_pro_nombre = nombrePro;
				
				UPDATE venta 
				SET ven_precio = ven_precio + cantidad * (SELECT pro_precio FROM producto WHERE pro_nombre = nombrePro)
				WHERE ven_id = idVenta;
			ELSEIF (tipoProducto = 'cervezaArtesanal') THEN
				UPDATE cervezaArtesanal SET cer_cantidadSede = cer_cantidadSede - cantidad * (SELECT lic_presentacion_ml
				FROM licor WHERE producto_pro_nombre = nombrePro)
				WHERE licor_producto_pro_nombre = nombrePro;
				
				UPDATE venta SET ven_precio = ven_precio + cantidad * (SELECT pro_precio FROM producto WHERE pro_nombre = nombrePro)
				WHERE ven_id = idVenta;
			END IF;
        COMMIT;
	END IF;
END $


CREATE PROCEDURE buscarProducto(INOUT nombrePro VARCHAR(30), INOUT cantidad DOUBLE)
BEGIN
	DECLARE numInsuficiencias INT DEFAULT 0;

	IF (nombrePro IN (SELECT producto_pro_nombre FROM comida)) THEN
	
        SELECT COUNT(*) INTO numInsuficiencias FROM (SELECT mpco_cantidad, mpco_nombre, (cantidad * cmp_cantidad) AS cantidadGast 
        FROM composicionComida JOIN materiaPrimaComida ON (materiaPrimaComida_mpco_nombre = mpco_nombre)
		WHERE comida_producto_pro_nombre = nombrePro) AS comparacionCantidades WHERE cantidadGast > mpco_cantidad;

		IF (numInsuficiencias > 0) THEN
			SET cantidad = -1;
		END IF;
        
        SET nombrePro = 'comida';

	ELSEIF (nombrePro IN (SELECT licor_producto_pro_nombre FROM licorMarca)) THEN
		SELECT lic_cantidad INTO cantidad FROM licorMarca WHERE licor_producto_pro_nombre = nombrePro;
        SET nombrePro = 'licorMarca';

	ELSEIF (nombrePro IN (SELECT licor_producto_pro_nombre FROM cervezaArtesanal)) THEN
		SELECT cer_cantidadSede INTO cantidad FROM cervezaArtesanal
		WHERE licor_producto_pro_nombre = nombrePro;
        SET nombrePro = 'cervezaArtesanal';
	END IF;
END $


CREATE PROCEDURE borrarParteVenta (nombreProducto VARCHAR(30), idVenta INT)
BEGIN
	DECLARE cantidad INT DEFAULT 0;
	SELECT par_cantidad INTO cantidad FROM parteVenta WHERE venta_ven_id = idVenta AND producto_pro_nombre = nombreProducto;
	
    START TRANSACTION;
		UPDATE venta SET ven_precio = ven_precio - cantidad * (SELECT pro_precio FROM producto WHERE pro_nombre = OLD.producto_pro_nombre) 
		WHERE ven_id = idVenta;
		
		DELETE FROM parteVenta WHERE venta_ven_id = idVenta AND producto_pro_nombre = nombreProducto;
    COMMIT;
END $


CREATE PROCEDURE reiniciarHorasTrabajo (numsede INT) 
BEGIN
	START TRANSACTION;
		UPDATE empleadoSede SET empSed_per_horasTrabajo = 0 WHERE sede_sed_numeroSede= numsede; 
		UPDATE empleado SET emp_salario=0 WHERE persona_per_cedula IN (SELECT empleado_persona_per_cedula FROM empleadoSede WHERE sede_sed_numeroSede= numsede);
	COMMIT;
END $


CREATE PROCEDURE inventariolicorSede (numsede INT) 
BEGIN 
	SELECT pro_nombre, pro_precio, lic_presentacion_ml, cantidad FROM vw_inventariolicorsede WHERE num_sede = numsede;
END $


CREATE PROCEDURE inventariomateriaprimaSede (numsede INT) 
BEGIN 
	SELECT mpco_nombre, mpco_cantidad FROM vw_inventariomateriaprimacomida where sede_sed_id = numsede; 
END $ 


CREATE PROCEDURE inventariomateriaprimaCerveza (numFab INT)
BEGIN 
	SELECT mpce_nombre, mpce_cantidad FROM materiaPrimaCerveza WHERE fabrica_fab_id = numFab;
END $


CREATE PROCEDURE totalventasProducto (nombreproducto VARCHAR(30)) 
BEGIN 
	SELECT pro_nombre, cantidadTotal , totalVenta FROM vw_totalventasProducto WHERE pro_nombre = nombreproducto;
END $


CREATE PROCEDURE obtenerDistribucionesPorSede (idSede INT(2))
BEGIN
	SELECT * FROM vw_totalDistribucionesSedes WHERE sede_sed_numeroSede = idSede;
END $


CREATE PROCEDURE obtenerDistribucionesPorCerveza (nombreCerveza VARCHAR(30))
BEGIN
	SELECT * FROM vw_totalDistribucionesSedes WHERE nombre = nombreCerveza;
END $

DELIMITER ;