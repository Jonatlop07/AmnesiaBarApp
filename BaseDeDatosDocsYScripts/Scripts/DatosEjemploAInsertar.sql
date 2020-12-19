use proyecto;

CALL registrarfabrica ('Briceño', null, null);

CALL registrarSede ('Zipaquirá', 'Plaza de la independencia', '4', '6');
CALL registrarSede ('Zipaquirá', 'Algarra', '7', '10');

CALL registrarEmpleado (100054043, 'Julian', 'Maldonado Contreras', 23, '8826733', 'CRA 15 No 3-6');
CALL registrarEmpleado (94324215, 'María Ofelia', 'Rodríguez Marroquín', 35, '3126948321', 'CRA 8 No 2–42');
-- nuevos registros 
CALL registrarEmpleado (1000156134,'Juliana' ,'Paez Gaona' ,34 , '3114455656' ,'CRA 7 No 3-56' );
CALL registrarEmpleado (1442428974,'Angela' ,'Rosal Agaton' ,24 , '3456784562' ,'CRA 8 No 1-55' );
CALL registrarEmpleado (5743829220,'Valentina' ,'Quitian Quitian' ,22 , '31974568432' ,'CRA 7 No 22-24' );
CALL registrarEmpleado (8293184893,'Julietha' ,'Vanegas Lopez' ,34 , '3143775044' ,'CRA 4 No 1-12' );
CALL registrarEmpleado (4588274319,'Johanny' ,'Guerrero Quesada' ,28 , '3117864581' ,'CRA 12 No 12-22' );
CALL registrarEmpleado (5489230202,'Vivian' ,'Lopez Prada' ,23 , '3143894567' ,'CRA 8 No 02-34' );
CALL registrarEmpleado (5345535345,'Camila ' ,'Velandia' ,22 , '3183227856' ,'CRA 7 No 35-77' );
CALL registrarEmpleado (5236326234,'Laura' ,'Guerrero Barajas' ,19 , '3144567834' ,'CRA 13 No 05-34' );
CALL registrarEmpleado (8678678644,'Alejandra' ,'Castañeda Oregon' ,31 , '3123567842' ,'CRA 11 No 11-32' );
CALL registrarEmpleado (1493436276,'Diana' ,'Carvajal Alvarado' ,27 , '3143307755' ,'CRA 13 No 02-07' );
CALL registrarEmpleado (6324588732,'Sofia' ,'Alvarado Salazar' ,26 , '3118875437' ,'CRA 7 No 14-31' );

CALL registrarEmpleadoSede (100054043, 1);
CALL registrarEmpleadoSede (94324215, 1);
-- nuevos registros 
CALL registrarEmpleadoSede (1000156134, 1);
CALL registrarEmpleadoSede (1442428974, 1);
CALL registrarEmpleadoSede (5743829220, 1);
CALL registrarEmpleadoSede (8293184893, 1);
CALL registrarEmpleadoSede (4588274319, 1);
CALL registrarEmpleadoSede (5489230202, 1);
CALL registrarEmpleadoSede (5345535345, 1);
CALL registrarEmpleadoSede (8678678644, 1);
CALL registrarEmpleadoSede (1493436276, 1);
CALL registrarEmpleadoSede (6324588732, 1);

CALL registrarhorastrabajoEmpleadoSede (94324215, 201);
CALL registrarhorastrabajoEmpleadoSede (100054043, 203);
-- nuevos registros 
CALL registrarhorastrabajoEmpleadoSede (1000156134, 203 );
CALL registrarhorastrabajoEmpleadoSede (1442428974, 207 );
CALL registrarhorastrabajoEmpleadoSede (5743829220, 210);
CALL registrarhorastrabajoEmpleadoSede (8293184893, 199);
CALL registrarhorastrabajoEmpleadoSede (4588274319, 211);
CALL registrarhorastrabajoEmpleadoSede (5489230202, 205);
CALL registrarhorastrabajoEmpleadoSede (5345535345, 204);
CALL registrarhorastrabajoEmpleadoSede (8678678644, 202);
CALL registrarhorastrabajoEmpleadoSede (1493436276, 209);
CALL registrarhorastrabajoEmpleadoSede (6324588732, 200);

CALL reiniciarHorasTrabajo (1);

CALL registrarEmpleado (8956204743, 'Juan Andrés', 'Bastidas', 38, '8511143', 'CRA 6 No 04-10');
CALL registrarEmpleado (7432895311, 'Mateo', 'Escobar Rincón', 48, '3118436578', 'Calle 32 No 04-32');
-- nuevos registros 
CALL registrarEmpleado (1001452892, 'Mateo', 'Cifuentes Paez', 27, '3133113789','CRA 7 No 05-45');
CALL registrarEmpleado (5426234211, 'Julian', 'Lopez Obredor', 31, '3123446751','CRA 6 No 24-24');
CALL registrarEmpleado (1023324645, 'Sebastian', 'Tijuan Cuña', 28, '3113566312','CRA 2 No 05-31');
CALL registrarEmpleado (5456902342, 'Ruben', 'Celestino Triana', 29, '3115637817','CRA 7 No 13-21');
CALL registrarEmpleado (1043100234, 'Dario', 'Paez Gomez', 30, '3143775412','CRA 11 No 24-31');
CALL registrarEmpleado (1023475563, 'Antonia', 'Quintin Lame', 21, '3143893671','CRA 13 No 31- 43');
CALL registrarEmpleado (1023042302, 'Vanessa', 'Triana Furquel', 25, '3113147856','CRA 07 No 24-07');
CALL registrarEmpleado (1002249536, 'Camilo', 'Duvan Pinto', 24, '3143563218','CRA 04 No 12-97');
CALL registrarEmpleado (8923415523, 'Tomas', 'Romero Romero', 22, '3133457821','CRA 08 No 34-07');

CALL registrarEmpleadoFabrica (89562047, 1);
CALL registrarEmpleadoFabrica (74328953, 1);
-- nuevos registros 
CALL registrarEmpleadoFabrica (1001452892,1);
CALL registrarEmpleadoFabrica (5426234211,1);
CALL registrarEmpleadoFabrica (1023324645,1);
CALL registrarEmpleadoFabrica (5456902342,1);
CALL registrarEmpleadoFabrica (1043100234,1);
CALL registrarEmpleadoFabrica (1023475563,1);
CALL registrarEmpleadoFabrica (1023042302,1);
CALL registrarEmpleadoFabrica (1002249536,1);
CALL registrarEmpleadoFabrica (8923415523,1);

CALL registrarCervezaArtesanal ('ArteNegra', 3400.0, 1, 350.0, 'Stout', 1);
CALL registrarCervezaArtesanal ('ArteNegra', 3400.0, 2, 350.0, 'Stout', 1);
CALL registrarCervezaArtesanal ('ArteRoja', 3400.0, 1, 350.0, 'Roja', 1);
CALL registrarCervezaArtesanal ('ArtePremium', 4800.0, 1, 650.0, 'Abadia', 1);

CALL registrarMateriaPrimaCerveza ('Cebada (kg)', 1);
CALL registrarMateriaPrimaCerveza ('Agua (l)', 1);
CALL registrarMateriaPrimaCerveza ('Lupulo (kg)', 1);
CALL registrarMateriaPrimaCerveza ('Levadura (kg)', 1);
CALL registrarMateriaPrimaCerveza ('Azucar (kg)', 1);
CALL registrarMateriaPrimaCerveza ('Malta (kg)', 1);

CALL registrarDistribuidora('Distribuidora Trago', '8524001');
CALL registrarDistribuidora('Mercados Uno A', '3227008038');
CALL registrarDistribuidora('Supermercados Napoles', null);
-- nuevos registros 
CALL registrarDistribuidora('MercaFruver', '3227008038');
CALL registrarDistribuidora('SurtiCervza', '3115672424');
CALL registrarDistribuidora('DistriTrago', '8143589');
CALL registrarDistribuidora('Tragosurti', '8765491');
CALL registrarDistribuidora('BBC', '8564123');
CALL registrarDistribuidora('Beermark', '3116759741');
CALL registrarDistribuidora('duffBeer', '3140765641');
CALL registrarDistribuidora('SurtiBeer', '3148975641');
CALL registrarDistribuidora('fruticervez', '3130774811');
CALL registrarDistribuidora('BearBeer', '3145631781');
CALL registrarDistribuidora('CervzaBeer', '3116741891');

CALL registrarAdquisicionMatPrimaCerveza('Mercados Uno A', 'Cebada (kg) 1', 81360.0, 10, '2019/10/22');
CALL registrarAdquisicionMatPrimaCerveza('Mercados Uno A', 'Agua (l) 1', 60000.0, 180, '2019/10/22');
CALL registrarAdquisicionMatPrimaCerveza('Supermercados Napoles', 'Lupulo (kg) 1', 30520, 0.4, '2019/10/22');
CALL registrarAdquisicionMatPrimaCerveza('Mercados Uno A', 'Levadura (kg) 1', 11000.0, 0.25, '2019/10/22');
CALL registrarAdquisicionMatPrimaCerveza('Mercados Uno A', 'Azucar (kg) 1', 2660, 1, '2019/10/22');
CALL registrarAdquisicionMatPrimaCerveza('Supermercados Napoles', 'Malta (kg) 1', 123200, 10, '2019/10/22');
-- nuevos registros 
CALL registrarAdquisicionMatPrimaCerveza('MercaFruver', 'Agua (l) 1', 60000.0, 180, '2020/01/11');
CALL registrarAdquisicionMatPrimaCerveza('SurtiCervza', 'Cebada (kg) 1',81360.0 , 10, '2020/01/26');
CALL registrarAdquisicionMatPrimaCerveza('DistriTrago', 'Lupulo (kg) 1', 30520, 0.4, '2020/01/27');
CALL registrarAdquisicionMatPrimaCerveza('Tragosurti', 'Levadura (kg) 1', 11000.0, 0.25, '2020/01/21');
CALL registrarAdquisicionMatPrimaCerveza('BBC', 'Azucar (kg) 1', 2660, 1, '2020/01/30');
CALL registrarAdquisicionMatPrimaCerveza('Beermark', 'Malta (kg) 1', 123200, 10, '2020/01/10');
CALL registrarAdquisicionMatPrimaCerveza('duffBeer', 'Cebada (kg) 1', 81360.0, 10, '2020/01/08');
CALL registrarAdquisicionMatPrimaCerveza('SurtiBeer', 'Agua (l) 1', 60000.0, 180, '2020/01/20');
CALL registrarAdquisicionMatPrimaCerveza('fruticervez', 'Lupulo (kg) 1', 30520, 0.4, '2020/01/22');
CALL registrarAdquisicionMatPrimaCerveza('BearBeer', 'Levadura (kg) 1', 11000.0, 0.25, '2020/01/22');
CALL registrarAdquisicionMatPrimaCerveza('CervzaBeer', 'Azucar (kg) 1', 2660, 1, '2020/01/19');


CALL registrarComposicionCerveza('ArteNegra 1', 'Cebada (kg) 1', 0.175, 1);
CALL registrarComposicionCerveza('ArteNegra 1', 'Agua (l) 1', 1.5, 1);
CALL registrarComposicionCerveza('ArteNegra 1', 'Lupulo (kg) 1', 0.03, 1);
CALL registrarComposicionCerveza('ArteNegra 1', 'Levadura (kg) 1', 0.01, 1);
CALL registrarComposicionCerveza('ArteNegra 1', 'Azucar (kg) 1', 0.05, 1);
CALL registrarComposicionCerveza('ArteNegra 1', 'Malta (kg) 1', 0.0875,1);
CALL registrarComposicionCerveza('ArteRoja 1', 'Cebada (kg) 1', 0.175, 1);
CALL registrarComposicionCerveza('ArteRoja 1', 'Agua (l) 1', 1.5, 1);
CALL registrarComposicionCerveza('ArteRoja 1', 'Lupulo (kg) 1', 0.03, 1);
CALL registrarComposicionCerveza('ArteRoja 1', 'Levadura (kg) 1', 0.01, 1);
CALL registrarComposicionCerveza('ArteRoja 1', 'Azucar (kg) 1', 0.05, 1);
CALL registrarComposicionCerveza('ArteRoja 1', 'Malta (kg) 1', 0.0875, 1);
CALL registrarComposicionCerveza('ArtePremium 1', 'Cebada (kg) 1', 0.175, 1);
CALL registrarComposicionCerveza('ArtePremium 1', 'Agua (l) 1', 1.5, 1);
CALL registrarComposicionCerveza('ArtePremium 1', 'Lupulo (kg) 1', 0.03, 1);
CALL registrarComposicionCerveza('ArtePremium 1', 'Levadura (kg) 1', 0.01, 1);
CALL registrarComposicionCerveza('ArtePremium 1', 'Azucar (kg) 1', 0.05, 1);
CALL registrarComposicionCerveza('ArtePremium 1', 'Malta (kg) 1', 0.0875, 1);
      
CALL registrarProduccion('2019/10/22', 5000.0, 1, 'ArteNegra 1');
CALL registrarProduccion('2019/10/22', 5000.0, 1, 'ArteNegra 2');
CALL registrarProduccion('2019/10/22', 5000.0, 1, 'ArteRoja 1');
CALL registrarProduccion('2019/10/22', 5000.0, 1, 'ArtePremium 1');

CALL registrarDistribucion('2019/10/23', 1000.0, 'ArteNegra 1', NULL, NULL, 1, 1);
CALL registrarDistribucion('2019/10/23', 1000.0, 'ArteRoja 1', NULL, NULL, 1, 1);
CALL registrarDistribucion('2019/10/23', 1000.0, 'ArtePremium 1', NULL, NULL, 1, 1);
CALL registrarDistribucion('2019/10/23', 1000.0, 'ArteNegra 1', 87686764, '3125874562', 1, 1);

CALL registrarLicorMarca('Cerveza Corona', 3200.0, 1, 320.0);
CALL registrarLicorMarca('Crema de Whisky', 44800.0, 1, 750.0);
CALL registrarLicorMarca('Aguardiente', 84600.0, 1, 2000.0);
CALL registrarLicorMarca('Whisky', 93100.0, 1, 1000.0);
    
CALL registrarAdquisicionLicor('Cerveza Corona 1', 'Distribuidora Trago', 203000.0, 70, '2019/10/24');
CALL registrarAdquisicionLicor('Crema de Whisky 1', 'Distribuidora Trago', 498000.0, 12, '2019/10/24');
CALL registrarAdquisicionLicor('Aguardiente 1', 'Distribuidora Trago', 626400.0, 8, '2019/10/24');


CALL registrarComida('Snacks', 6000.0, 1);
CALL registrarComida('Mini picada', 8000.0, 1);
CALL registrarComida('Chicharrón carnudo', 10000.0, 1);
CALL registrarComida('Tocinada', 12000.0, 1);

CALL registrarMateriaPrimaComida('Snacks', 1);
CALL registrarMateriaPrimaComida('Morcilla', 1);
CALL registrarMateriaPrimaComida('Longaniza', 1);
CALL registrarMateriaPrimaComida('Papa criolla', 1);
CALL registrarMateriaPrimaComida('Carne de res', 1);
CALL registrarMateriaPrimaComida('Chicharrón', 1);
CALL registrarMateriaPrimaComida('Papa pastusa', 1);
CALL registrarMateriaPrimaComida('Tocino', 1);
CALL registrarMateriaPrimaComida('Cebolla', 1);

CALL registrarAdquisicionMatPrimaComida('Supermercados Napoles', 'Snacks 1', 52000.0, 10.0, '2019/10/22');
CALL registrarAdquisicionMatPrimaComida('Supermercados Napoles', 'Morcilla 1', 17500.0, 7.0, '2019/10/22');
CALL registrarAdquisicionMatPrimaComida('Supermercados Napoles', 'Longaniza 1', 21000.0, 7.0, '2019/10/22');
CALL registrarAdquisicionMatPrimaComida('Supermercados Napoles', 'Papa criolla 1', 50000.0, 20.0, '2019/10/22');
CALL registrarAdquisicionMatPrimaComida('Supermercados Napoles', 'Carne de res 1', 16000.0, 8.0, '2019-10-22');
CALL registrarAdquisicionMatPrimaComida('Supermercados Napoles', 'Chicharrón 1', 18000.0, 3.0, '2019/10/22');
CALL registrarAdquisicionMatPrimaComida('Mercados Uno A', 'Papa pastusa 1', 5000.0, 5.0, '2019/10/22');
CALL registrarAdquisicionMatPrimaComida('Supermercados Napoles', 'Tocino 1', 21000.0, 3.0, '2019/10/22');
CALL registrarAdquisicionMatPrimaComida('Mercados Uno A', 'Cebolla 1', 3750.0, 2.5, '2019/10/22');
-- nuevos registros 

CALL registrarComposicionComida('Snacks 1', 'Snacks 1', 1, 1);
CALL registrarComposicionComida('Mini picada 1', 'Morcilla 1', 0.25, 1);
CALL registrarComposicionComida('Mini picada 1', 'Longaniza 1', 0.25, 1);
CALL registrarComposicionComida('Mini picada 1', 'Papa criolla 1', 0.5, 1);
CALL registrarComposicionComida('Mini picada 1', 'Carne de res 1', 0.25, 1);
CALL registrarComposicionComida('Chicharrón carnudo 1', 'Chicharrón 1', 0.5, 1);
CALL registrarComposicionComida('Chicharrón carnudo 1', 'Papa pastusa 1', 0.5, 1);
CALL registrarComposicionComida('Tocinada 1', 'Tocino 1', 0.5, 1);
CALL registrarComposicionComida('Tocinada 1', 'Cebolla 1', 0.25, 1);
CALL registrarComposicionComida('Tocinada 1', 'Papa pastusa 1', 0.25, 1);

CALL registrarCliente(64564573, 'Olga Lucía', 'Pineda Rodríguez');
CALL registrarCliente(79878754, 'Iván Manuel', 'Poveda Castellanos');
CALL registrarCliente(94576623, 'Juliana Andrea', 'Alvarado López');
CALL registrarCliente(79343655, 'Jonny', 'Jiménez Salazar');
CALL registrarCliente(100089435, 'Jorge Enrique', 'Oviedo Cortés');
-- nuevos registros 
CALL registrarCliente(89413671, 'Hector Duvan', 'Gomez Gomez');
CALL registrarCliente(56712391, 'Dario Hernan', 'Muñoz Rincon');
CALL registrarCliente(89512313, 'Giovvany', 'Salazar acevedo');
CALL registrarCliente(56741289, 'Ricardo', 'Uriel Gutierrez');
CALL registrarCliente(17812719, 'Marcelo Adrian', 'Perez Guzman');
CALL registrarCliente(45671289, 'Laura Valentina', 'Angarita Paez');
CALL registrarCliente(87821021, 'Jessica Carolina', 'Villamil Turing');
CALL registrarCliente(51723810, 'Monica Rosalba', 'Lovelace Turing');
CALL registrarCliente(56171234, 'Sandra Milena', 'Moreno Moreno');
CALL registrarCliente(51237563, 'Jenn Haitsu', 'Zapata Gonzales');
CALL registrarCliente(89123441, 'Carolina', 'Garjona Flores');
CALL registrarCliente(78192458, 'Marcela Carolina ', 'Rodriguez Pojas');
CALL registrarCliente(56471890, 'Adriana Marcela', 'Gomez Muñoz');
CALL registrarCliente(47821902, 'Tatiana Sofia', 'Rincon Moreno');
CALL registrarCliente(56748102, 'Diana Carolina', 'Castellanos Lopez');


CALL registrarVenta(79878754, 1, '2019/10/25');
CALL registrarVenta(79343655, 1, '2019/10/25');
CALL registrarVenta(79878754, 1, '2019/10/25');
CALL registrarVenta(94576623, 1, '2019/10/26');
CALL registrarVenta(79878754, 1, '2019/10/27');
CALL registrarVenta(64564573, 1, '2019/10/27');
CALL registrarVenta(64564573, 1, '2019/10/27');
CALL registrarVenta(79878754, 1, '2019/10/27');
CALL registrarVenta(94576623, 1, '2019/10/28');
    
CALL registrarParteVenta(1, 1, 'Snacks 1', 1);
CALL registrarParteVenta(1, 2, 'Mini picada 1', 2);
CALL registrarParteVenta(2, 2, 'Cerveza Corona 1', 2);
CALL registrarParteVenta(1, 3, 'Cerveza Corona 1', 1);
CALL registrarParteVenta(1, 4, 'Chicharrón carnudo 1', 1);
CALL registrarParteVenta(2, 4, 'Mini picada 1', 2);
CALL registrarParteVenta(3, 4, 'ArteNegra 1', 2);
CALL registrarParteVenta(1, 5, 'ArteRoja 1', 1);
CALL registrarParteVenta(1, 6, 'Tocinada 1', 2);
CALL registrarParteVenta(2, 6, 'ArtePremium 1', 2);
CALL registrarParteVenta(1, 7, 'Mini picada 1', 3);
CALL registrarParteVenta(2, 7, 'Cerveza Corona 1', 1);
CALL registrarParteVenta(1, 8, 'Chicharrón carnudo 1', 1);
CALL registrarParteVenta(2, 8, 'ArteNegra 1', 2);
CALL registrarParteVenta(1, 9, 'Mini picada 1', 1);
CALL registrarParteVenta(2, 9, 'Cerveza Corona 1', 2);

CALL inventarioLicorSede(1);
CALL inventariomateriaprimaSede(1);
CALL inventariomateriaprimaCerveza(1);
CALL totalventasProducto('Snacks 1');
CALL totalventasProducto('Cerveza Corona 1');
CALL totalventasProducto('ArteNegra 1');
CALL obtenerDistribucionesPorSede(1);
CALL obtenerDistribucionesPorCerveza('ArteNegra 1');