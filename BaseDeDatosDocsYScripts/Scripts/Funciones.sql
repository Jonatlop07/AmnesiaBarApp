use proyecto;

DROP FUNCTION IF EXISTS calcularMontoTotalVentasCliente;
DROP FUNCTION IF EXISTS aplicarDescuentoCliente;

DELIMITER $
CREATE FUNCTION calcularMontoTotalVentasCliente (cedula INT) RETURNS DOUBLE
BEGIN
	DECLARE montoTotal DOUBLE DEFAULT 0.0;
	SELECT SUM (ven_precio) INTO montoTotal FROM venta WHERE cliente_persona_per_cedula = cedula;
	RETURN montoTotal;
END $

CREATE FUNCTION aplicarDescuentoCliente (idVenta INT, descuento DOUBLE) RETURNS BOOL
BEGIN
	DECLARE descontado BOOL DEFAULT FALSE;
	IF descuento > 0.0 AND descuento < 1.0 THEN
		UPDATE venta SET ven_precio = ven_precio - descuento * ven_precio
		WHERE ven_id = idVenta;
		SET descontado = TRUE;
    END IF;
	RETURN descontado;
END $

DELIMITER ;