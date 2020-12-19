CREATE SCHEMA IF NOT EXISTS proyecto;

use proyecto;

DROP TABLE IF EXISTS distribucionSede;
DROP TABLE IF EXISTS distribucion;
DROP TABLE IF EXISTS produccion;
DROP TABLE IF EXISTS parteVenta;
DROP TABLE IF EXISTS facturaLicor;
DROP TABLE IF EXISTS composicionCerveza;
DROP TABLE IF EXISTS composicionComida;
DROP TABLE IF EXISTS empleadoSede;
DROP TABLE IF EXISTS empleadoFabrica;
DROP TABLE IF EXISTS facturaMateriaPrimaComida;
DROP TABLE IF EXISTS facturaMateriaPrimaCerveza;
DROP TABLE IF EXISTS afiliado;
DROP TABLE IF EXISTS materiaPrimaCerveza;
DROP TABLE IF EXISTS distribuidora;
DROP TABLE IF EXISTS licorMarca;
DROP TABLE IF EXISTS cervezaArtesanal;
DROP TABLE IF EXISTS fabrica;
DROP TABLE IF EXISTS licor;
DROP TABLE IF EXISTS comida;
DROP TABLE IF EXISTS producto;
DROP TABLE IF EXISTS venta;
DROP TABLE IF EXISTS materiaPrimaComida;
DROP TABLE IF EXISTS sede;
DROP TABLE IF EXISTS empleado;
DROP TABLE IF EXISTS cliente;
DROP TABLE IF EXISTS persona;

CREATE TABLE persona (
  per_cedula BIGINT NOT NULL,
  per_nombre VARCHAR(45) NOT NULL,
  per_apellido VARCHAR(45) NOT NULL,
  PRIMARY KEY (per_cedula)
);

CREATE TABLE cliente (
  persona_per_cedula BIGINT NOT NULL,
  PRIMARY KEY (persona_per_cedula),
  FOREIGN KEY (persona_per_cedula) references persona (per_cedula)
);

CREATE TABLE empleado (
  persona_per_cedula BIGINT NOT NULL,
  emp_edad INT(2) NOT NULL check(emp_edad > 0),
  emp_telefono VARCHAR(15) NULL,
  emp_direccion VARCHAR(30) NULL,
  emp_salario DOUBLE NOT NULL check(emp_salario >= 0),
  PRIMARY KEY (persona_per_cedula),
  FOREIGN KEY (persona_per_cedula) references persona (per_cedula)
);

CREATE TABLE sede (
  sed_numeroSede INT(2) NOT NULL AUTO_INCREMENT,
  sed_ciudad VARCHAR(25) NOT NULL,
  sed_barrio VARCHAR(30) NOT NULL,
  sed_calle VARCHAR(15) NULL,
  sed_carrera VARCHAR(15) NULL,
  PRIMARY KEY (sed_numeroSede)
);

CREATE TABLE materiaPrimaComida (
  mpco_nombre VARCHAR(30) NOT NULL,
  mpco_cantidad DOUBLE NOT NULL check(mpco_cantidad >= 0),
  sede_sed_id INT(2) NOT NULL,
  PRIMARY KEY (mpco_nombre),
  FOREIGN KEY (sede_sed_id) references sede (sed_numeroSede)
);

CREATE TABLE venta (
  ven_id INT NOT NULL AUTO_INCREMENT,
  cliente_persona_per_cedula BIGINT NOT NULL,
  sede_sed_numeroSede INT(2) NOT NULL,
  ven_precio DOUBLE NOT NULL check(ven_precio > 0),
  ven_fecha DATE NOT NULL,
  PRIMARY KEY (ven_id, cliente_persona_per_cedula, sede_sed_numeroSede),
  FOREIGN KEY (cliente_persona_per_cedula) REFERENCES cliente (persona_per_cedula),
  FOREIGN KEY (sede_sed_numeroSede) REFERENCES sede (sed_numeroSede)
);

CREATE TABLE producto (
  pro_nombre VARCHAR(30) NOT NULL,
  pro_precio DOUBLE NOT NULL check(pro_precio > 0),
  sede_sed_numeroSede INT(2) NOT NULL,
  PRIMARY KEY (pro_nombre),
  FOREIGN KEY (sede_sed_numeroSede) references sede (sed_numeroSede)
);

CREATE TABLE comida (
  producto_pro_nombre VARCHAR(30) NOT NULL,
  PRIMARY KEY (producto_pro_nombre),
  FOREIGN KEY (producto_pro_nombre) references producto (pro_nombre)
);

CREATE TABLE licor (
  producto_pro_nombre VARCHAR(30) NOT NULL,
  lic_presentacion_ml DOUBLE NOT NULL check (lic_presentacion_ml > 0),
  PRIMARY KEY (producto_pro_nombre),
  FOREIGN KEY (producto_pro_nombre) references producto (pro_nombre)
);

CREATE TABLE fabrica (
  fab_id INT(2) NOT NULL AUTO_INCREMENT,
  fab_ciudad VARCHAR(25) NOT NULL,
  fab_calle VARCHAR(15) NULL,
  fab_carrera VARCHAR(15) NULL,
  PRIMARY KEY (fab_id)
);

CREATE TABLE cervezaArtesanal (
  licor_producto_pro_nombre VARCHAR(30) NOT NULL,
  cer_tipo VARCHAR(30) NOT NULL,
  cer_cantidadSede DOUBLE NOT NULL check (cer_cantidadSede >= 0),
  cer_cantidadFabrica DOUBLE NOT NULL check (cer_cantidadFabrica >= 0),
  fabrica_fab_id INT(2) NOT NULL,
  PRIMARY KEY (licor_producto_pro_nombre),
  FOREIGN KEY (fabrica_fab_id) references fabrica (fab_id),
  FOREIGN KEY (licor_producto_pro_nombre) references licor (producto_pro_nombre)
);

CREATE TABLE licorMarca (
  licor_producto_pro_nombre VARCHAR(30) NOT NULL,
  lic_cantidad INT NOT NULL check(lic_cantidad >= 0),
  PRIMARY KEY (licor_producto_pro_nombre),
  FOREIGN KEY (licor_producto_pro_nombre) references licor (producto_pro_nombre)
);

CREATE TABLE distribuidora (
  dis_nombre VARCHAR(45) NOT NULL,
  dis_telefono VARCHAR(15) NULL,
  PRIMARY KEY (dis_nombre)
);

CREATE TABLE materiaPrimaCerveza (
  mpce_nombre VARCHAR(30) NOT NULL,
  mpce_cantidad DOUBLE NOT NULL check(mpce_cantidad >= 0),
  fabrica_fab_ID INT(2) NOT NULL,
  PRIMARY KEY (mpce_nombre),
  FOREIGN KEY (fabrica_fab_ID) references fabrica (fab_id)
);

CREATE TABLE afiliado (
  afi_id BIGINT NOT NULL check(afi_id > 0),
  afi_telefono VARCHAR(15) NULL,
  PRIMARY KEY (afi_id)
);

CREATE TABLE facturaMateriaPrimaCerveza (
  distribuidora_dis_nombre VARCHAR(45) NOT NULL,
  materiaPrimaCerveza_mpce_nombre VARCHAR(30) NOT NULL,
  fce_precio DOUBLE NOT NULL check(fce_precio >= 0),
  fce_cantidad DOUBLE NOT NULL check(fce_cantidad >= 0),
  fce_fecha DATE NOT NULL,
  PRIMARY KEY (distribuidora_dis_nombre, materiaPrimaCerveza_mpce_nombre, fce_fecha, fce_precio),
  FOREIGN KEY (distribuidora_dis_nombre) references distribuidora (dis_nombre),
  FOREIGN KEY (materiaPrimaCerveza_mpce_nombre) references materiaPrimaCerveza (mpce_nombre)
);

CREATE TABLE facturaMateriaPrimaComida (
  distribuidora_dis_nombre VARCHAR(45) NOT NULL,
  materiaPrimaComida_mpco_nombre VARCHAR(30) NOT NULL,
  fco_precio DOUBLE NOT NULL check(fco_precio >= 0),
  fco_cantidad DOUBLE NOT NULL check(fco_cantidad >= 0),
  fco_fecha DATE NOT NULL,
  PRIMARY KEY (distribuidora_Dis_Nombre, materiaPrimaComida_mpco_nombre, fco_fecha, fco_precio),
  FOREIGN KEY (distribuidora_dis_nombre) references distribuidora (dis_nombre),
  FOREIGN KEY (materiaPrimaComida_mpco_nombre) references materiaPrimaComida (mpco_nombre)
);

CREATE TABLE empleadoFabrica (
  empleado_persona_per_cedula BIGINT NOT NULL,
  fabrica_fab_id INT(2) NOT NULL,
  PRIMARY KEY (empleado_persona_per_cedula),
  FOREIGN KEY (empleado_persona_per_cedula) references empleado (persona_per_cedula),
  FOREIGN KEY (fabrica_fab_id) references fabrica (fab_id)
);

CREATE TABLE empleadoSede (
  empleado_persona_per_cedula BIGINT NOT NULL,
  empSed_per_horasTrabajo INT NULL,
  sede_sed_numeroSede INT(2) NOT NULL,
  PRIMARY KEY (empleado_persona_per_cedula),
  FOREIGN KEY (empleado_persona_per_cedula) references empleado (persona_per_cedula),
  FOREIGN KEY (sede_sed_numeroSede) references sede (sed_numeroSede)
);

CREATE TABLE composicionComida (
  comida_producto_pro_nombre VARCHAR(30) NOT NULL,
  materiaPrimaComida_mpco_nombre VARCHAR(30) NOT NULL,
  cmp_cantidad DOUBLE NOT NULL check(cmp_cantidad >= 0),
  PRIMARY KEY (comida_producto_pro_nombre, materiaPrimaComida_mpco_nombre),
  FOREIGN KEY (comida_producto_pro_nombre) references comida (producto_pro_nombre),
  FOREIGN KEY (materiaPrimaComida_mpco_nombre) references materiaPrimaComida (mpco_nombre)
);

CREATE TABLE composicionCerveza (
  cervezaArtesanal_nombre VARCHAR(30) NOT NULL,
  materiaPrimaCerveza_mpce_nombre VARCHAR(30) NOT NULL,
  cmp_cantidad DOUBLE NOT NULL check(cmp_cantidad >= 0),
  PRIMARY KEY (cervezaArtesanal_nombre, materiaPrimaCerveza_mpce_nombre),
  FOREIGN KEY (cervezaArtesanal_nombre) references cervezaArtesanal (licor_producto_pro_nombre),
  FOREIGN KEY (materiaPrimaCerveza_mpce_nombre) references materiaPrimaCerveza (mpce_nombre)
);

CREATE TABLE facturaLicor (
  licorMarca_licor_producto_pro_nombre VARCHAR(30) NOT NULL,
  distribuidora_dis_nombre VARCHAR(45) NOT NULL,
  fli_precio DOUBLE NOT NULL check(fli_precio >= 0),
  fli_cantidad INT NOT NULL check(fli_cantidad >= 0),
  fli_fecha DATE NOT NULL,
  PRIMARY KEY (licorMarca_licor_producto_pro_nombre, distribuidora_dis_nombre, fli_fecha, fli_precio),
  FOREIGN KEY (licorMarca_licor_producto_pro_nombre) references licorMarca (licor_producto_pro_nombre),
  FOREIGN KEY (distribuidora_dis_nombre) references distribuidora (dis_nombre)
);

CREATE TABLE parteVenta (
  par_id INT NOT NULL check(par_id >= 0),
  venta_ven_id INT NOT NULL,
  venta_cliente_persona_per_cedula BIGINT NOT NULL,
  venta_sede_sed_numeroSede INT(2) NOT NULL,
  producto_pro_nombre VARCHAR(30) NOT NULL,
  par_cantidad INT NOT NULL check(par_cantidad >= 0),
  PRIMARY KEY (par_id, venta_ven_id, venta_cliente_persona_per_cedula, venta_sede_sed_numeroSede),
  FOREIGN KEY (venta_ven_id , venta_cliente_persona_per_cedula , venta_sede_sed_numeroSede) 
  references venta (ven_id , cliente_persona_per_cedula , sede_sed_numeroSede),
  FOREIGN KEY (producto_pro_nombre) references producto (pro_nombre)
);

CREATE TABLE produccion (
  pro_id INT NOT NULL AUTO_INCREMENT,
  pro_fecha DATE NOT NULL,
  pro_cantidad DOUBLE NOT NULL check(pro_cantidad >= 0),
  fabrica_fab_id INT(2) NOT NULL,
  cervezaArtesanal_licor_producto_pro_nombre VARCHAR(30) NOT NULL,
  PRIMARY KEY (pro_id),
  FOREIGN KEY (fabrica_fab_id) references fabrica (fab_id),
  FOREIGN KEY (cervezaArtesanal_licor_producto_pro_nombre) references cervezaArtesanal (licor_producto_pro_nombre)
);

CREATE TABLE distribucion (
  dis_id INT NOT NULL AUTO_INCREMENT,
  dis_fecha DATE NOT NULL,
  dis_cantidad DOUBLE NOT NULL,
  cervezaArtesanal_licor_producto_pro_nombre VARCHAR(30) NOT NULL,
  afiliado_afi_id BIGINT NULL,
  PRIMARY KEY (dis_id),
  FOREIGN KEY (cervezaArtesanal_licor_producto_pro_nombre) references cervezaArtesanal (licor_producto_pro_nombre),
  FOREIGN KEY (afiliado_afi_id) references afiliado (afi_id)
);

CREATE TABLE distribucionSede (
  sede_sed_numeroSede INT(2) NOT NULL,
  distribucion_dis_id INT NOT NULL,
  PRIMARY KEY (sede_sed_numeroSede, distribucion_dis_id),
  FOREIGN KEY (sede_sed_numeroSede) references sede (sed_numeroSede),
  FOREIGN KEY (distribucion_dis_id) references distribucion (dis_id)
);