use proyecto;

DROP TRIGGER IF EXISTS tr_borrarVenta;
DROP TRIGGER IF EXISTS tr_borrarEmpleado;
DROP TRIGGER IF EXISTS tr_borrarDistribucion;
DROP TRIGGER IF EXISTS tr_borrarParteVenta;
DROP TRIGGER IF EXISTS tr_borrarProduccion;

DELIMITER $

CREATE TRIGGER tr_borrarVenta BEFORE DELETE ON venta FOR EACH ROW
BEGIN
	DELETE FROM parteVenta WHERE venta_ven_id = OLD.ven_id;
END $


CREATE TRIGGER tr_borrarEmpleado BEFORE DELETE ON empleado FOR EACH ROW
BEGIN
	DELETE FROM empleadoSede WHERE empleado_persona_per_cedula = OLD.persona_per_cedula;
	DELETE FROM empleadoFabrica WHERE empleado_persona_per_cedula = OLD.persona_per_cedula;
END $


CREATE TRIGGER tr_borrarDistribucion BEFORE DELETE ON distribucion FOR EACH ROW
BEGIN 
	IF (OLD.afiliado_afi_id IS NULL) THEN
		DELETE FROM distribucionSede WHERE distribucion_dis_id = OLD.dis_id;
        UPDATE cervezaArtesanal SET cer_cantidadSede = cer_cantidadSede - OLD.dis_cantidad, 
        cer_cantidadFabrica = cer_cantidadFabrica + OLD.dis_cantidad
        WHERE licor_producto_pro_nombre = OLD.cervezaArtesanal_licor_producto_pro_nombre;
	ELSE 
        UPDATE cervezaArtesanal SET cer_cantidadFabrica = cer_cantidadFabrica + OLD.dis_cantidad
        WHERE licor_producto_pro_nombre = OLD.cervezaArtesanal_licor_producto_pro_nombre;
	END IF; 
END $


CREATE TRIGGER tr_borrarProduccion BEFORE DELETE ON produccion FOR EACH ROW
BEGIN
	DECLARE nombreCerveza VARCHAR(30) DEFAULT '';
    DECLARE delimitador VARCHAR(3);
	DECLARE termina BOOL DEFAULT FALSE;
    DECLARE nombreMateria VARCHAR(30) DEFAULT '';
	DECLARE cantRestaurada DOUBLE DEFAULT 0.0;

    DECLARE cr_materias CURSOR
	FOR SELECT mpce_nombre, (mpce_cantidad + ((OLD.pro_cantidad / 1000) * cmp_cantidad)) AS cantidadRestante
		FROM cervezaArtesanal JOIN composicionCerveza ON (licor_producto_pro_nombre = cervezaArtesanal_nombre)
		JOIN materiaPrimaCerveza ON (materiaPrimaCerveza_mpce_nombre = mpce_nombre)
		WHERE cervezaArtesanal_nombre = OLD.cervezaArtesanal_licor_producto_pro_nombre 
        AND materiaPrimaCerveza.fabrica_fab_id = OLD.fabrica_fab_id;
	
    DECLARE CONTINUE HANDLER
	FOR SQLSTATE '02000'
		SET termina = TRUE;
        
	SET delimitador = CONCAT(' ', (SELECT sede_sed_numeroSede FROM producto WHERE pro_nombre = OLD.cervezaArtesanal_licor_producto_pro_nombre));
	SET nombreCerveza = SUBSTRING_INDEX(OLD.cervezaArtesanal_licor_producto_pro_nombre, delimitador, 1);
        
    OPEN cr_materias;
		Recorre_Cursor: LOOP
			FETCH cr_materias INTO nombreMateria, cantRestaurada;

			IF termina THEN
				LEAVE Recorre_Cursor;
			END IF;

			UPDATE materiaPrimaCerveza SET mpce_cantidad = cantRestaurada
			WHERE mpce_nombre = nombreMateria;
		END LOOP;
	CLOSE cr_materias;
    
    UPDATE cervezaArtesanal SET cer_cantidadFabrica = cer_cantidadFabrica - OLD.pro_cantidad
	WHERE licor_producto_pro_nombre LIKE CONCAT(nombreCerveza, '%') AND cervezaArtesanal.fabrica_fab_id = OLD.fabrica_fab_id;
END $


CREATE TRIGGER tr_borrarParteVenta BEFORE DELETE ON parteVenta FOR EACH ROW
BEGIN
	DECLARE cantDisponible INT DEFAULT 0;
	DECLARE tipoProducto VARCHAR(30) DEFAULT '';
	DECLARE nombreMateria VARCHAR(30) DEFAULT '';
	DECLARE cantRestaurada DOUBLE DEFAULT 0.0;
	DECLARE termina BOOL DEFAULT FALSE;
    
    DECLARE cr_materias CURSOR 
        FOR SELECT mpco_nombre, (mpco_cantidad + OLD.par_cantidad * cmp_cantidad) AS cantRest 
        	FROM parteVenta JOIN composicionComida ON (producto_pro_nombre = comida_producto_pro_nombre) 
			JOIN materiaPrimaComida ON (materiaPrimaComida_mpco_nombre = mpco_nombre)
			WHERE venta_ven_id = OLD.venta_ven_id AND comida_producto_pro_nombre = OLD.producto_pro_nombre;

	DECLARE CONTINUE HANDLER
		FOR SQLSTATE '02000'
			SET termina = TRUE;

	SET cantDisponible = OLD.par_cantidad;
	SET tipoProducto = OLD.producto_pro_nombre;

	CALL buscarProducto(tipoProducto, cantDisponible);
    
	IF tipoProducto = 'comida' THEN
		OPEN cr_materias;
			Recorre_Cursor: LOOP
				FETCH cr_materias INTO nombreMateria, cantRestaurada;
				UPDATE materiaPrimaComida SET mpco_cantidad = cantRestaurada WHERE mpco_nombre = nombreMateria;

				IF termina THEN
					LEAVE Recorre_Cursor;
				END IF;
			END LOOP;
		CLOSE cr_materias;

	ELSEIF tipoProducto = 'licorMarca' THEN
		UPDATE licorMarca SET lic_cantidad = lic_cantidad + OLD.par_cantidad WHERE licor_producto_pro_nombre = OLD.producto_pro_nombre;
	ELSEIF tipoProducto = 'cervezaArtesanal' THEN
		UPDATE cervezaArtesanal SET cer_cantidadSede = cer_cantidadSede + OLD.par_cantidad * (SELECT lic_presentacion_ml FROM licor
        WHERE licor.producto_pro_nombre = OLD.producto_pro_nombre) WHERE licor_producto_pro_nombre = OLD.producto_pro_nombre;
	END IF;
END $
DELIMITER ;