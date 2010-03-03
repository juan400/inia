-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.41-community


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema inia_mscc_db
--

CREATE DATABASE IF NOT EXISTS inia_mscc_db;
USE inia_mscc_db;

--
-- Definition of table `tl_adc_acpa_archivoclimaticoparametro`
--

DROP TABLE IF EXISTS `tl_adc_acpa_archivoclimaticoparametro`;
CREATE TABLE `tl_adc_acpa_archivoclimaticoparametro` (
  `acpa_num_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `acpa_num_id_archivo` bigint(20) NOT NULL,
  `acpa_num_id_parametro` bigint(20) NOT NULL,
  `acpa_num_codigo` varchar(6) NOT NULL,
  PRIMARY KEY (`acpa_num_id`),
  UNIQUE KEY `unique_codigo` (`acpa_num_codigo`),
  KEY `FK_acpa_num_id_parametro` (`acpa_num_id_parametro`),
  KEY `FK_acpa_num_id_archivo` (`acpa_num_id_archivo`),
  CONSTRAINT `FK_acpa_num_id_archivo` FOREIGN KEY (`acpa_num_id_archivo`) REFERENCES `tl_adc_arcl_archivoclimatologico` (`arcl_num_id`),
  CONSTRAINT `FK_acpa_num_id_parametro` FOREIGN KEY (`acpa_num_id_parametro`) REFERENCES `tl_adc_pacl_parametroclimatologico` (`pacl_num_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tl_adc_acpa_archivoclimaticoparametro`
--

/*!40000 ALTER TABLE `tl_adc_acpa_archivoclimaticoparametro` DISABLE KEYS */;
/*!40000 ALTER TABLE `tl_adc_acpa_archivoclimaticoparametro` ENABLE KEYS */;


--
-- Definition of table `tl_adc_arcl_archivoclimatologico`
--

DROP TABLE IF EXISTS `tl_adc_arcl_archivoclimatologico`;
CREATE TABLE `tl_adc_arcl_archivoclimatologico` (
  `arcl_num_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `arcl_dte_fecha` datetime NOT NULL,
  `arcl_str_nombre` varchar(220) NOT NULL,
  `arcl_num_id_estado` bigint(20) NOT NULL,
  `arcl_num_id_ubicacion` bigint(20) NOT NULL,
  `arcl_num_id_region` bigint(20) NOT NULL,
  PRIMARY KEY (`arcl_num_id`),
  KEY `FK_arcl_num_id_estado` (`arcl_num_id_estado`),
  KEY `FK_arcl_num_id_ubicacion` (`arcl_num_id_ubicacion`),
  KEY `FK_arcl_num_id_region` (`arcl_num_id_region`),
  CONSTRAINT `FK_arcl_num_id_estado` FOREIGN KEY (`arcl_num_id_estado`) REFERENCES `tl_adm_esta_estado` (`esta_num_id`),
  CONSTRAINT `FK_arcl_num_id_region` FOREIGN KEY (`arcl_num_id_region`) REFERENCES `tl_adm_regi_regionclimatica` (`regi_num_id`),
  CONSTRAINT `FK_arcl_num_id_ubicacion` FOREIGN KEY (`arcl_num_id_ubicacion`) REFERENCES `tl_gem_ubar_ubicacionarchivo` (`ubar_num_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tl_adc_arcl_archivoclimatologico`
--

/*!40000 ALTER TABLE `tl_adc_arcl_archivoclimatologico` DISABLE KEYS */;
/*!40000 ALTER TABLE `tl_adc_arcl_archivoclimatologico` ENABLE KEYS */;


--
-- Definition of table `tl_adc_pacl_parametroclimatologico`
--

DROP TABLE IF EXISTS `tl_adc_pacl_parametroclimatologico`;
CREATE TABLE `tl_adc_pacl_parametroclimatologico` (
  `pacl_num_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pacl_str_codigo` varchar(6) NOT NULL,
  `pacl_str_descripcion` varchar(220) NOT NULL,
  `pacl_str_unidad_medida` varchar(220) DEFAULT NULL,
  PRIMARY KEY (`pacl_num_id`),
  UNIQUE KEY `unique_codigo` (`pacl_str_codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tl_adc_pacl_parametroclimatologico`
--

/*!40000 ALTER TABLE `tl_adc_pacl_parametroclimatologico` DISABLE KEYS */;
/*!40000 ALTER TABLE `tl_adc_pacl_parametroclimatologico` ENABLE KEYS */;


--
-- Definition of table `tl_adm_ciud_ciudad`
--

DROP TABLE IF EXISTS `tl_adm_ciud_ciudad`;
CREATE TABLE `tl_adm_ciud_ciudad` (
  `ciud_num_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ciud_str_nombre` varchar(220) NOT NULL,
  `ciud_num_id_departamento` bigint(20) NOT NULL,
  PRIMARY KEY (`ciud_num_id`),
  KEY `FK_deto_num_id` (`ciud_num_id_departamento`),
  CONSTRAINT `FK_deto_num_id` FOREIGN KEY (`ciud_num_id_departamento`) REFERENCES `tl_adm_deto_departamento` (`deto_num_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tl_adm_ciud_ciudad`
--

/*!40000 ALTER TABLE `tl_adm_ciud_ciudad` DISABLE KEYS */;
INSERT INTO `tl_adm_ciud_ciudad` (`ciud_num_id`,`ciud_str_nombre`,`ciud_num_id_departamento`) VALUES 
 (1,'Montevideo',480),
 (2,'Rosario',474),
 (3,'Colonia del Sacramento',474),
 (4,'Nueva Helvecia',474),
 (5,'Comonia Valdense',474);
/*!40000 ALTER TABLE `tl_adm_ciud_ciudad` ENABLE KEYS */;


--
-- Definition of table `tl_adm_deto_departamento`
--

DROP TABLE IF EXISTS `tl_adm_deto_departamento`;
CREATE TABLE `tl_adm_deto_departamento` (
  `deto_num_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `deto_str_nombre` varchar(220) NOT NULL,
  `deto_num_id_pais` bigint(20) NOT NULL,
  PRIMARY KEY (`deto_num_id`),
  KEY `FK_pais_num_id` (`deto_num_id_pais`),
  CONSTRAINT `FK_pais_num_id` FOREIGN KEY (`deto_num_id_pais`) REFERENCES `tl_adm_pais_pais` (`pais_num_id`)
) ENGINE=InnoDB AUTO_INCREMENT=490 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tl_adm_deto_departamento`
--

/*!40000 ALTER TABLE `tl_adm_deto_departamento` DISABLE KEYS */;
INSERT INTO `tl_adm_deto_departamento` (`deto_num_id`,`deto_str_nombre`,`deto_num_id_pais`) VALUES 
 (1,'Buenos Aires',1),
 (2,'Capital Federal',1),
 (3,'Catamarca',1),
 (4,'Chaco',1),
 (5,'Chubut',1),
 (6,'Cordoba',1),
 (7,'Corrientes',1),
 (8,'Entre Rios',1),
 (9,'Formosa',1),
 (10,'Jujuy',1),
 (11,'La Pampa',1),
 (12,'La Rioja',1),
 (13,'Mendoza',1),
 (14,'Misiones',1),
 (15,'Neuquen',1),
 (16,'Rio Negro',1),
 (17,'Salta',1),
 (18,'San Juan',1),
 (19,'San Luis',1),
 (20,'Santa Cruz',1),
 (21,'Santa Fe',1),
 (22,'Santiago del Estero',1),
 (23,'Tierra del Fuego',1),
 (24,'Tucuman',1),
 (25,'Chuquisaca',2),
 (26,'Cochabamba',2),
 (27,'Beni',2),
 (28,'La Paz',2),
 (29,'Oruro',2),
 (30,'Pando',2),
 (31,'Potosi',2),
 (32,'Santa Cruz',2),
 (33,'Tarija',2),
 (34,'Acre',3),
 (35,'Alagoas',3),
 (36,'Amapa',3),
 (37,'Amazonas',3),
 (38,'Bahia',3),
 (39,'Ceara',3),
 (40,'Distrito Federal',3),
 (41,'Espirito Santo',3),
 (42,'Goias',3),
 (43,'Maranhao',3),
 (44,'Mato Grosso',3),
 (45,'Mato Grosso do Sul',3),
 (46,'Minas Gerais',3),
 (47,'Para',3),
 (48,'Paraiba',3),
 (49,'Parana',3),
 (50,'Pernambuco',3),
 (51,'Piaui',3),
 (52,'Rio de Janeiro',3),
 (53,'Rio Grande do Norte',3),
 (54,'Rio Grande do Sul',3),
 (55,'Rondonia',3),
 (56,'Roraima',3),
 (57,'Santa Catarina',3),
 (58,'Sao Paulo',3),
 (59,'Sergipe',3),
 (60,'Tocantins',3),
 (61,'Alberta',4),
 (62,'British Columbia',4),
 (63,'Manitoba',4),
 (64,'New Brunswick',4),
 (65,'Newfoundland and Labrador',4),
 (66,'Northwest Territories',4),
 (67,'Nova Scotia',4),
 (68,'Nunavut',4),
 (69,'Ontario',4),
 (70,'Prince Edward Island',4),
 (71,'Quebec',4),
 (72,'Saskatchewan',4),
 (73,'Yukon Territory',4),
 (74,'Antofagasta',5),
 (75,'Araucania',5),
 (76,'Atacama',5),
 (77,'Bio-Bio',5),
 (78,'Coquimbo',5),
 (79,'Libertador General B.',5),
 (80,'Los Lagos',5),
 (81,'Magallanes',5),
 (82,'Maule',5),
 (83,'Santiago',5),
 (84,'Tarapaca',5),
 (85,'Valparaiso',5),
 (86,'Amazonas',6),
 (87,'Antioquia',6),
 (88,'Arauca',6),
 (89,'Atlantico',6),
 (90,'Distrito Capital de Bogota',6),
 (91,'Bolivar',6),
 (92,'Boyaca',6),
 (93,'Caldas',6),
 (94,'Caqueta',6),
 (95,'Casanare',6),
 (96,'Cauca',6),
 (97,'Cesar',6),
 (98,'Choco',6),
 (99,'Cordoba',6),
 (100,'Cundinamarca',6),
 (101,'Guainia',6),
 (102,'Guaviare',6),
 (103,'Huila',6),
 (104,'La Guajira',6),
 (105,'Magdalena',6),
 (106,'Meta',6),
 (107,'Narino',6),
 (108,'Norte de Santander',6),
 (109,'Putumayo',6),
 (110,'Quindio',6),
 (111,'Risaralda',6),
 (112,'San Andres y Providencia',6),
 (113,'Santander',6),
 (114,'Sucre',6),
 (115,'Tolima',6),
 (116,'Valle del Cauca',6),
 (117,'Vaupes',6),
 (118,'Vichada',6),
 (119,'Alibori',7),
 (120,'Atakora',7),
 (121,'Atlantique',7),
 (122,'Borgou',7),
 (123,'Collines',7),
 (124,'Kouffo',7),
 (125,'Donga',7),
 (126,'Littoral',7),
 (127,'Mono',7),
 (128,'Oueme',7),
 (129,'Plateau',7),
 (130,'Zou',7),
 (131,'Camaguey',8),
 (132,'Ciego de Avila',8),
 (133,'Cienfuegos',8),
 (134,'Ciudad de La Habana',8),
 (135,'Granma',8),
 (136,'Guantanamo',8),
 (137,'Holguin',8),
 (138,'Isla de la Juventud',8),
 (139,'La Habana',8),
 (140,'Las Tunas',8),
 (141,'Matanzas',8),
 (142,'Pinar del Rio',8),
 (143,'Sancti Spiritus',8),
 (144,'Santiago de Cuba',8),
 (145,'Villa Clara',8),
 (146,'Azuay',9),
 (147,'Bolivar',9),
 (148,'Canar',9),
 (149,'Carchi',9),
 (150,'Chimborazo',9),
 (151,'Cotopaxi',9),
 (152,'El Oro',9),
 (153,'Esmeraldas',9),
 (154,'Galapagos',9),
 (155,'Guayas',9),
 (156,'Imbabura',9),
 (157,'Loja',9),
 (158,'Los Rios',9),
 (159,'Manabi',9),
 (160,'Morona-Santiago',9),
 (161,'Napo',9),
 (162,'Orellana',9),
 (163,'Pastaza',9),
 (164,'Pichincha',9),
 (165,'Sucumbios',9),
 (166,'Tungurahua',9),
 (167,'Zamora-Chinchipe',9),
 (168,'Ahuachapan',10),
 (169,'Cabanas',10),
 (170,'Chalatenango',10),
 (171,'Cuscatlan',10),
 (172,'La Libertad',10),
 (173,'La Paz',10),
 (174,'La Union',10),
 (175,'Morazan',10),
 (176,'San Miguel',10),
 (177,'San Salvador',10),
 (178,'Santa Ana',10),
 (179,'San Vicente',10),
 (180,'Sonsonate',10),
 (181,'Usulutan',10),
 (182,'Andalucia',11),
 (183,'Aragon',11),
 (184,'Asturias',11),
 (185,'Baleares',11),
 (186,'Ceuta',11),
 (187,'Canarias',11),
 (188,'Cantabria',11),
 (189,'Castilla-La Mancha',11),
 (190,'Castilla y Leon',11),
 (191,'Catalu�a',11),
 (192,'Comunidad Valenciana',11),
 (193,'Extremadura',11),
 (194,'Galicia',11),
 (195,'La Rioja',11),
 (196,'Madrid',11),
 (197,'Melilla',11),
 (198,'Murcia',11),
 (199,'Navarra',11),
 (200,'Pais Vasco',11),
 (201,'Alabama',12),
 (202,'Alaska',12),
 (203,'Arizona',12),
 (204,'Arkansas',12),
 (205,'California',12),
 (206,'Colorado',12),
 (207,'Connecticut',12),
 (208,'Delaware',12),
 (209,'District of Columbia',12),
 (210,'Florida',12),
 (211,'Georgia',12),
 (212,'Hawaii',12),
 (213,'Idaho',12),
 (214,'Illinois',12),
 (215,'Indiana',12),
 (216,'Iowa',12),
 (217,'Kansas',12),
 (218,'Kentucky',12),
 (219,'Louisiana',12),
 (220,'Maine',12),
 (221,'Maryland',12),
 (222,'Massachusetts',12),
 (223,'Michigan',12),
 (224,'Minnesota',12),
 (225,'Mississippi',12),
 (226,'Missouri',12),
 (227,'Montana',12),
 (228,'Nebraska',12),
 (229,'Nevada',12),
 (230,'New Hampshire',12),
 (231,'New Jersey',12),
 (232,'New Mexico',12),
 (233,'New York',12),
 (234,'North Carolina',12),
 (235,'North Dakota',12),
 (236,'Ohio',12),
 (237,'Oklahoma',12),
 (238,'Oregon',12),
 (239,'Pennsylvania',12),
 (240,'Rhode Island',12),
 (241,'South Carolina',12),
 (242,'South Dakota',12),
 (243,'Tennessee',12),
 (244,'Texas',12),
 (245,'Utah',12),
 (246,'Vermont',12),
 (247,'Virginia',12),
 (248,'Washington',12),
 (249,'West Virginia',12),
 (250,'Wisconsin',12),
 (251,'Wyoming',12),
 (252,'Alta Verapaz',13),
 (253,'Baja Verapaz',13),
 (254,'Chimaltenango',13),
 (255,'Chiquimula',13),
 (256,'El Progreso',13),
 (257,'Escuintla',13),
 (258,'Guatemala',13),
 (259,'Huehuetenango',13),
 (260,'Izabal, Jalapa',13),
 (261,'Jutiapa',13),
 (262,'Peten',13),
 (263,'Quetzaltenango',13),
 (264,'Quiche',13),
 (265,'Retalhuleu',13),
 (266,'Sacatepequez',13),
 (267,'San Marcos',13),
 (268,'Santa Rosa',13),
 (269,'Solola',13),
 (270,'Suchitepequez',13),
 (271,'Totonicapan',13),
 (272,'Zacapa',13),
 (273,'Atlantida',14),
 (274,'Choluteca',14),
 (275,'Colon',14),
 (276,'Comayagua',14),
 (277,'Copan',14),
 (278,'Cortes',14),
 (279,'El Paraiso',14),
 (280,'Francisco Morazan',14),
 (281,'Gracias a Dios',14),
 (282,'Intibuca',14),
 (283,'Islas de la Bahia',14),
 (284,'La Paz',14),
 (285,'Lempira',14),
 (286,'Ocotepeque',14),
 (287,'Olancho',14),
 (288,'Santa Barbara',14),
 (289,'Valle',14),
 (290,'Yoro',14),
 (291,'Aguascalientes',15),
 (292,'Baja California',15),
 (293,'Baja California Sur',15),
 (294,'Campeche',15),
 (295,'Chiapas',15),
 (296,'Chihuahua',15),
 (297,'Coahuila de Zaragoza',15),
 (298,'Colima',15),
 (299,'Distrito Federal',15),
 (300,'Durango',15),
 (301,'Guanajuato',15),
 (302,'Guerrero',15),
 (303,'Hidalgo',15),
 (304,'Jalisco',15),
 (305,'Mexico',15),
 (306,'Michoacan de Ocampo',15),
 (307,'Morelos',15),
 (308,'Nayarit',15),
 (309,'Nuevo Leon',15),
 (310,'Oaxaca',15),
 (311,'Puebla',15),
 (312,'Queretaro de Arteaga',15),
 (313,'Quintana Roo',15),
 (314,'San Luis Potosi',15),
 (315,'Sinaloa',15),
 (316,'Sonora',15),
 (317,'Tabasco',15),
 (318,'Tamaulipas',15),
 (319,'Tlaxcala',15),
 (320,'Veracruz-Llave',15),
 (321,'Yucatan',15),
 (322,'Zacatecas',15),
 (323,'Atlantico Norte',16),
 (324,'Atlantico Sur',16),
 (325,'Boaco',16),
 (326,'Carazo',16),
 (327,'Chinandega',16),
 (328,'Chontales',16),
 (329,'Esteli',16),
 (330,'Granada',16),
 (331,'Jinotega',16),
 (332,'Leon',16),
 (333,'Madriz',16),
 (334,'Managua',16),
 (335,'Masaya',16),
 (336,'Matagalpa',16),
 (337,'Nueva Segovia',16),
 (338,'Rio San Juan',16),
 (339,'Rivas',16),
 (340,'Bocas del Toro',17),
 (341,'Chiriqui',17),
 (342,'Cocle',17),
 (343,'Colon',17),
 (344,'Darien',17),
 (345,'Herrera',17),
 (346,'Los Santos',17),
 (347,'Panama',17),
 (348,'San Blas',17),
 (349,'Kuna Yala',17),
 (350,'Veraguas',17),
 (351,'Alto Paraguay',18),
 (352,'Alto Parana',18),
 (353,'Amambay',18),
 (354,'Asuncion',18),
 (355,'Boqueron',18),
 (356,'Caaguazu',18),
 (357,'Caazapa',18),
 (358,'Canindeyu',18),
 (359,'Central',18),
 (360,'Concepcion',18),
 (361,'Cordillera',18),
 (362,'Guaira',18),
 (363,'Itapua',18),
 (364,'Misiones',18),
 (365,'Neembucu',18),
 (366,'Paraguari',18),
 (367,'Presidente Hayes',18),
 (368,'San Pedro',18),
 (369,'Amazonas',19),
 (370,'Ancash',19),
 (371,'Apurimac',19),
 (372,'Arequipa',19),
 (373,'Ayacucho',19),
 (374,'Cajamarca',19),
 (375,'Callao',19),
 (376,'Cusco',19),
 (377,'Huancavelica',19),
 (378,'Huanuco',19),
 (379,'Ica',19),
 (380,'Junin',19),
 (381,'La Libertad',19),
 (382,'Lambayeque',19),
 (383,'Lima',19),
 (384,'Madre de Dios',19),
 (385,'Moquegua',19),
 (386,'Pasco',19),
 (387,'Piura',19),
 (388,'Puno',19),
 (389,'San Martin',19),
 (390,'Tacna',19),
 (391,'Tumbes',19),
 (392,'Ucayali',19),
 (393,'Adjuntas',20),
 (394,'Aguada',20),
 (395,'Aguadilla',20),
 (396,'Aguas Buenas',20),
 (397,'Aibonito',20),
 (398,'Anasco',20),
 (399,'Arecibo',20),
 (400,'Arroyo',20),
 (401,'Barceloneta',20),
 (402,'Barranquitas',20),
 (403,'Bayamon',20),
 (404,'Cabo Rojo',20),
 (405,'Caguas',20),
 (406,'Camuy',20),
 (407,'Canovanas',20),
 (408,'Carolina',20),
 (409,'Catano',20),
 (410,'Cayey',20),
 (411,'Ceiba',20),
 (412,'Ciales',20),
 (413,'Cidra',20),
 (414,'Coamo',20),
 (415,'Comerio',20),
 (416,'Corozal',20),
 (417,'Culebra',20),
 (418,'Dorado',20),
 (419,'Fajardo',20),
 (420,'Florida',20),
 (421,'Guanica',20),
 (422,'Guayama',20),
 (423,'Guayanilla',20),
 (424,'Guaynabo',20),
 (425,'Gurabo',20),
 (426,'Hatillo',20),
 (427,'Hormigueros',20),
 (428,'Humacao',20),
 (429,'Isabela',20),
 (430,'Jayuya',20),
 (431,'Juana Diaz',20),
 (432,'Juncos',20),
 (433,'Lajas',20),
 (434,'Lares',20),
 (435,'Las Marias',20),
 (436,'Las Piedras',20),
 (437,'Loiza',20),
 (438,'Luquillo',20),
 (439,'Manati',20),
 (440,'Maricao',20),
 (441,'Maunabo',20),
 (442,'Mayaguez',20),
 (443,'Moca',20),
 (444,'Morovis',20),
 (445,'Naguabo',20),
 (446,'Naranjito',20),
 (447,'Orocovis',20),
 (448,'Patillas',20),
 (449,'Penuelas',20),
 (450,'Ponce',20),
 (451,'Quebradillas',20),
 (452,'Rincon',20),
 (453,'Rio Grande',20),
 (454,'Sabana Grande',20),
 (455,'Salinas',20),
 (456,'San German',20),
 (457,'San Juan',20),
 (458,'San Lorenzo',20),
 (459,'San Sebastian',20),
 (460,'Santa Isabel',20),
 (461,'Toa Alta',20),
 (462,'Toa Baja',20),
 (463,'Trujillo Alto',20),
 (464,'Utuado',20),
 (465,'Vega Alta',20),
 (466,'Vega Baja',20),
 (467,'Vieques',20),
 (468,'Villalba',20),
 (469,'Yabucoa',20),
 (470,'Yauco',20),
 (471,'Artigas',21),
 (472,'Canelones',21),
 (473,'Cerro Largo',21),
 (474,'Colonia',21),
 (475,'Durazno',21),
 (476,'Flores',21),
 (477,'Florida',21),
 (478,'Lavalleja',21),
 (479,'Maldonado',21),
 (480,'Montevideo',21),
 (481,'Paysandu',21),
 (482,'Rio Negro',21),
 (483,'Rivera',21),
 (484,'Rocha',21),
 (485,'Salto',21),
 (486,'San Jose',21),
 (487,'Soriano',21),
 (488,'Tacuarembo',21),
 (489,'Treinta y Tres',21);
/*!40000 ALTER TABLE `tl_adm_deto_departamento` ENABLE KEYS */;


--
-- Definition of table `tl_adm_esta_estado`
--

DROP TABLE IF EXISTS `tl_adm_esta_estado`;
CREATE TABLE `tl_adm_esta_estado` (
  `esta_num_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `esta_str_descripcion` varchar(220) NOT NULL,
  `esta_str_codigo` varchar(3) NOT NULL,
  PRIMARY KEY (`esta_num_id`),
  UNIQUE KEY `unique_codigo` (`esta_str_codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tl_adm_esta_estado`
--

/*!40000 ALTER TABLE `tl_adm_esta_estado` DISABLE KEYS */;
/*!40000 ALTER TABLE `tl_adm_esta_estado` ENABLE KEYS */;


--
-- Definition of table `tl_adm_licr_listascriterio`
--

DROP TABLE IF EXISTS `tl_adm_licr_listascriterio`;
CREATE TABLE `tl_adm_licr_listascriterio` (
  `licr_num_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `licr_str_codigo` varchar(3) NOT NULL,
  `licr_str_descripcion` varchar(220) NOT NULL,
  `licr_str_estado` varchar(10) NOT NULL DEFAULT 'Activo',
  PRIMARY KEY (`licr_num_id`),
  UNIQUE KEY `codigo` (`licr_str_codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tl_adm_licr_listascriterio`
--

/*!40000 ALTER TABLE `tl_adm_licr_listascriterio` DISABLE KEYS */;
INSERT INTO `tl_adm_licr_listascriterio` (`licr_num_id`,`licr_str_codigo`,`licr_str_descripcion`,`licr_str_estado`) VALUES 
 (1,'FER','Fertilizantes','Activo'),
 (2,'CON','Indice de Coneat','Activo'),
 (3,'PAR','Parmetros de Salida','Activo');
/*!40000 ALTER TABLE `tl_adm_licr_listascriterio` ENABLE KEYS */;


--
-- Definition of table `tl_adm_pais_pais`
--

DROP TABLE IF EXISTS `tl_adm_pais_pais`;
CREATE TABLE `tl_adm_pais_pais` (
  `pais_num_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pais_str_nombre` varchar(220) NOT NULL,
  PRIMARY KEY (`pais_num_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tl_adm_pais_pais`
--

/*!40000 ALTER TABLE `tl_adm_pais_pais` DISABLE KEYS */;
INSERT INTO `tl_adm_pais_pais` (`pais_num_id`,`pais_str_nombre`) VALUES 
 (1,'Argentina'),
 (2,'Bolivia'),
 (3,'Brasil'),
 (4,'Canada'),
 (5,'Chile'),
 (6,'Colombia'),
 (7,'Costa Rica'),
 (8,'Cuba'),
 (9,'Ecuador'),
 (10,'El Salvador'),
 (11,'España'),
 (12,'Estados Unidos'),
 (13,'Guatemala'),
 (14,'Honduras'),
 (15,'Mexico'),
 (16,'Nicaragua'),
 (17,'Panama'),
 (18,'Paraguay'),
 (19,'Perú'),
 (20,'Puerto Rico'),
 (21,'Uruguay');
/*!40000 ALTER TABLE `tl_adm_pais_pais` ENABLE KEYS */;


--
-- Definition of table `tl_adm_regi_regionclimatica`
--

DROP TABLE IF EXISTS `tl_adm_regi_regionclimatica`;
CREATE TABLE `tl_adm_regi_regionclimatica` (
  `regi_num_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `regi_str_descripcion` varchar(220) NOT NULL,
  `regi_str_codigo` varchar(6) NOT NULL,
  `regi_str_nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`regi_num_id`),
  UNIQUE KEY `unique_codigo` (`regi_str_codigo`) USING BTREE,
  KEY `index_nombre` (`regi_str_nombre`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tl_adm_regi_regionclimatica`
--

/*!40000 ALTER TABLE `tl_adm_regi_regionclimatica` DISABLE KEYS */;
INSERT INTO `tl_adm_regi_regionclimatica` (`regi_num_id`,`regi_str_descripcion`,`regi_str_codigo`,`regi_str_nombre`) VALUES 
 (1,'Descripcion prueba 2','1','La Estanzuela'),
 (2,'Prueba de actualizacion 3','2','Las Brujas'),
 (3,'','3','Estacion Experimental Tacuarembo');
/*!40000 ALTER TABLE `tl_adm_regi_regionclimatica` ENABLE KEYS */;


--
-- Definition of table `tl_adm_tran_transaccion`
--

DROP TABLE IF EXISTS `tl_adm_tran_transaccion`;
CREATE TABLE `tl_adm_tran_transaccion` (
  `tran_num_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tran_num_id_estado` varchar(10) NOT NULL DEFAULT 'Activo',
  `tran_str_codigo` varchar(6) DEFAULT NULL,
  `tran_str_descripcion` varchar(220) DEFAULT NULL,
  `tran_bol_definida` tinyint(1) NOT NULL DEFAULT '0',
  `tran_str_uri` varchar(250) NOT NULL,
  `tran_str_codigo_base` varchar(6) NOT NULL,
  `tran_str_descripcion_base` varchar(220) NOT NULL,
  `tran_dte_timestamp` datetime NOT NULL,
  PRIMARY KEY (`tran_num_id`),
  UNIQUE KEY `unique_codigo` (`tran_str_codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tl_adm_tran_transaccion`
--

/*!40000 ALTER TABLE `tl_adm_tran_transaccion` DISABLE KEYS */;
INSERT INTO `tl_adm_tran_transaccion` (`tran_num_id`,`tran_num_id_estado`,`tran_str_codigo`,`tran_str_descripcion`,`tran_bol_definida`,`tran_str_uri`,`tran_str_codigo_base`,`tran_str_descripcion_base`,`tran_dte_timestamp`) VALUES 
 (1,'Activo','001','Login',0,'/Servicios/SEG/SEG001.jsp','SEG001','Login','2010-02-16 12:52:30'),
 (2,'Activo','002','Registro de usuarios',0,'/Servicios/SEG/SEG002.jsp','SEG002','Registro Usuario','2010-02-16 12:52:30'),
 (3,'Activo','003','Activar Usuario',0,'/Servicios/SEG/SEG003.jsp','SEG003','Activar Usuario','2010-02-16 12:52:30'),
 (4,'Activo','004','Modificar Usuario',0,'/Servicios/SEG/SEG004.jsp','SEG004','Modificar Usuario','2010-02-16 12:52:30'),
 (5,'Activo','005','Baja Usuario',0,'/Servicios/SEG/SEG005.jsp','SEG005','Baja Usuario','2010-02-16 12:52:30'),
 (6,'Activo','006','Recuperar Contraseña',0,'/Servicios/SEG/SEG006.jsp','SEG006','Recuperar Contraseña','2010-02-16 12:52:30'),
 (7,'Activo','007','Cambiar Contraseña',0,'/Servicios/SEG/SEG007.jsp','SEG007','Cambiar Contraseña','2010-02-16 12:52:30'),
 (8,'Activo','008','Ver Perfiles',0,'/Servicios/SEG/SEG008.jsp','SEG008','Ver Perfiles','2010-02-16 12:52:30'),
 (9,'Activo','009','Crear Perfil',0,'/Servicios/SEG/SEG009.jsp','SEG009','Crear Perfil','2010-02-16 12:52:30'),
 (10,'Activo','010','Modificar Perfil',0,'/Servicios/SEG/SEG010.jsp','SEG010','Modificar Perfil','2010-02-16 12:52:30'),
 (11,'Activo','011','Solicitud Privilegios',0,'/Servicios/SEG/SEG011.jsp','SEG011','Solicitud Privilegios','2010-02-16 12:52:30'),
 (12,'Activo','012','Autorizar Solicitud Privilegios',0,'/Servicios/SEG/SEG012.jsp','SEG012','Autorizar Solicitud Privilegios','2010-02-16 12:52:30'),
 (13,'Activo','013','Denegar Solicitud Privilegios',0,'/Servicios/SEG/SEG013jsp','SEG013','Denegar Solicitud Privilegios','2010-02-16 12:52:30'),
 (14,'Activo','014','Ver Transacciónes',0,'/Servicios/ADM/ADM001.jsp','ADM001','Ver Transacciónes','2010-02-16 12:52:30'),
 (15,'Activo','015','Modificar Transacción',0,'/Servicios/ADM/ADM002.jsp','ADM002','Modificar Transacción','2010-02-16 12:52:30'),
 (16,'Activo','016','Lista de Criterios',0,'/Servicios/ADM/ADM003.jsp','ADM003','Listas de Criterio','2010-02-16 12:52:30'),
 (17,'Activo','017','Relación País, Dpto, Cuidad',0,'/Servicios/ADM/ADM004.jsp','ADM004','Relación País Departamentos Ciudades','2010-02-16 12:52:30'),
 (18,'Activo','018','Ver Regiones',0,'/Servicios/ADM/ADM005.jsp','ADM005','Ver Regiones','2010-02-16 12:52:30'),
 (19,'Activo','019','Crear Regiones',0,'/Servicios/ADM/ADM005.jsp','ADM006','Crear Regiones','2010-02-16 12:52:30'),
 (20,'Activo','020','Modificar Región',0,'/Servicios/ADM/ADM005.jsp','ADM007','Modificar Regiones','2010-02-16 12:52:30'),
 (21,'Activo','021','Ver Usuarios',0,'/Servicios/ADM/ADM005.jsp','ADM008','Ver Usuarios','2010-02-16 12:52:30'),
 (22,'Activo','022','Modificar Perfil Usuario',0,'/Servicios/ADM/ADM005.jsp','ADM009','Modificar Perfil Usuario','2010-02-16 12:52:30'),
 (23,'Activo','023','Crear Cultivo',0,'/Servicios/GEM/GEM001.jsp','GEM001','Crear Cultivo','2010-02-16 12:52:30'),
 (24,'Activo','024','Modificar Cultivo',0,'/Servicios/GEM/GEM002.jsp','GEM002','Modificar Cultivo','2010-02-16 12:52:30'),
 (25,'Activo','025','Crear Propiedades Cultivo',0,'/Servicios/GEM/GEM003.jsp','GEM003','Crear Propiedades Cultivo','2010-02-16 12:52:30'),
 (26,'Activo','026','Modificar Propiedades de un Cultivo',0,'/Servicios/GEM/GEM004.jsp','GEM004','Modificar Propiedades de un Cultivo','2010-02-16 12:52:30'),
 (27,'Activo','027','Ingresar Escenario',0,'/Servicios/GEM/GEM005.jsp','GEM005','Ingresar Escenario','2010-02-16 12:52:30'),
 (28,'Activo','028','Modificar Escenario',0,'/Servicios/GEM/GEM006.jsp','GEM006','Modificar Escenario','2010-02-16 12:52:30'),
 (29,'Activo','029','Ingresar Python de MSCC',0,'/Servicios/GEM/GEM007.jsp','GEM007','Ingresar Python de MSCC','2010-02-16 12:52:30'),
 (30,'Activo','030','Modificar Python de MSCC',0,'/Servicios/GEM/GEM008.jsp','GEM008','Modificar Python de MSCC','2010-02-16 12:52:30'),
 (31,'Activo','031','Anular Python de MSCC',0,'/Servicios/GEM/GEM009.jsp','GEM009','Anular Python de MSCC','2010-02-16 12:52:30'),
 (32,'Activo','032','Ejecución de MSCC',0,'/Servicios/EJE/EJE001.jsp','EJE001','Ejecución de MSCC','2010-02-16 12:52:30'),
 (33,'Activo','033','Ejecución de MSCC',0,'/Servicios/EJE/EJE002.jsp','EJE002','Ejecución de MSCC','2010-02-16 12:52:30');
/*!40000 ALTER TABLE `tl_adm_tran_transaccion` ENABLE KEYS */;


--
-- Definition of table `tl_adm_vase_valorseleccion`
--

DROP TABLE IF EXISTS `tl_adm_vase_valorseleccion`;
CREATE TABLE `tl_adm_vase_valorseleccion` (
  `vase_num_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `vase_str_codigo` varchar(6) NOT NULL,
  `vase_str_descripcion` varchar(220) NOT NULL,
  `vase_str_unidad` varchar(45) DEFAULT NULL,
  `vase_num_id_listacriterio` bigint(20) NOT NULL,
  PRIMARY KEY (`vase_num_id`),
  KEY `FK_vase_num_id_listacriterio` (`vase_num_id_listacriterio`),
  CONSTRAINT `FK_vase_num_id_listacriterio` FOREIGN KEY (`vase_num_id_listacriterio`) REFERENCES `tl_adm_licr_listascriterio` (`licr_num_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tl_adm_vase_valorseleccion`
--

/*!40000 ALTER TABLE `tl_adm_vase_valorseleccion` DISABLE KEYS */;
/*!40000 ALTER TABLE `tl_adm_vase_valorseleccion` ENABLE KEYS */;


--
-- Definition of table `tl_eje_arsa_archivosalida`
--

DROP TABLE IF EXISTS `tl_eje_arsa_archivosalida`;
CREATE TABLE `tl_eje_arsa_archivosalida` (
  `arsa_num_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `arsa_str_nombre` varchar(220) NOT NULL,
  `arsa_dte_fecha` datetime NOT NULL,
  `arsa_num_id_ubicacion` bigint(20) NOT NULL,
  `arsa_num_id_escenacio` bigint(20) NOT NULL,
  PRIMARY KEY (`arsa_num_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tl_eje_arsa_archivosalida`
--

/*!40000 ALTER TABLE `tl_eje_arsa_archivosalida` DISABLE KEYS */;
/*!40000 ALTER TABLE `tl_eje_arsa_archivosalida` ENABLE KEYS */;


--
-- Definition of table `tl_eje_esce_escenacio`
--

DROP TABLE IF EXISTS `tl_eje_esce_escenacio`;
CREATE TABLE `tl_eje_esce_escenacio` (
  `esce_num_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `esce_dte_fecha` datetime NOT NULL,
  `esce_num_id_usuario` bigint(20) NOT NULL,
  `esce_num_id_region_climatica` bigint(20) NOT NULL,
  `esce_num_id_cultivo` bigint(20) NOT NULL,
  `esce_str_valores_propiedades` longtext NOT NULL,
  PRIMARY KEY (`esce_num_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tl_eje_esce_escenacio`
--

/*!40000 ALTER TABLE `tl_eje_esce_escenacio` DISABLE KEYS */;
/*!40000 ALTER TABLE `tl_eje_esce_escenacio` ENABLE KEYS */;


--
-- Definition of table `tl_gem_arpy_archivopython`
--

DROP TABLE IF EXISTS `tl_gem_arpy_archivopython`;
CREATE TABLE `tl_gem_arpy_archivopython` (
  `arpy_num_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `arpy_str_nombre` varchar(220) NOT NULL,
  `arpy_dte_fecha` datetime NOT NULL,
  `arpy_num_id_estado` bigint(20) NOT NULL,
  `arpy_num_id_ubicacion_archivo` bigint(20) NOT NULL,
  `arpy_num_id_cultivo` bigint(20) NOT NULL,
  PRIMARY KEY (`arpy_num_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tl_gem_arpy_archivopython`
--

/*!40000 ALTER TABLE `tl_gem_arpy_archivopython` DISABLE KEYS */;
/*!40000 ALTER TABLE `tl_gem_arpy_archivopython` ENABLE KEYS */;


--
-- Definition of table `tl_gem_cult_cultivo`
--

DROP TABLE IF EXISTS `tl_gem_cult_cultivo`;
CREATE TABLE `tl_gem_cult_cultivo` (
  `cult_num_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cult_str_descripcion` varchar(220) NOT NULL,
  `cult_num_id_estado` varchar(10) NOT NULL,
  `cult_str_nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`cult_num_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tl_gem_cult_cultivo`
--

/*!40000 ALTER TABLE `tl_gem_cult_cultivo` DISABLE KEYS */;
INSERT INTO `tl_gem_cult_cultivo` (`cult_num_id`,`cult_str_descripcion`,`cult_num_id_estado`,`cult_str_nombre`) VALUES 
 (27,'Cultivo de trigo','Activo','Trigo'),
 (28,'Cultivo de Soja','Inactivo','Soja'),
 (29,'Cultivo de Maiz','Inactivo','Maiz');
/*!40000 ALTER TABLE `tl_gem_cult_cultivo` ENABLE KEYS */;


--
-- Definition of table `tl_gem_prcu_propiedadescultivo`
--

DROP TABLE IF EXISTS `tl_gem_prcu_propiedadescultivo`;
CREATE TABLE `tl_gem_prcu_propiedadescultivo` (
  `prcu_num_id` bigint(20) NOT NULL DEFAULT '0',
  `prcu_str_codigo` varchar(10) NOT NULL,
  `prcu_str_nombre` varchar(220) NOT NULL,
  `prcu_str_unidad_medida` varchar(220) DEFAULT NULL,
  `prcu_str_tipo` varchar(45) DEFAULT NULL,
  `prcu_num_id_cultivo` bigint(20) NOT NULL,
  PRIMARY KEY (`prcu_num_id`),
  UNIQUE KEY `unique_codigo` (`prcu_str_codigo`,`prcu_num_id_cultivo`) USING BTREE,
  KEY `FK_prcu_num_id_cultivo` (`prcu_num_id_cultivo`) USING BTREE,
  CONSTRAINT `FK_tl_gem_prcu_propiedadescultivo_1` FOREIGN KEY (`prcu_num_id_cultivo`) REFERENCES `tl_gem_cult_cultivo` (`cult_num_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tl_gem_prcu_propiedadescultivo`
--

/*!40000 ALTER TABLE `tl_gem_prcu_propiedadescultivo` DISABLE KEYS */;
/*!40000 ALTER TABLE `tl_gem_prcu_propiedadescultivo` ENABLE KEYS */;


--
-- Definition of table `tl_gem_tiar_tipoarchivo`
--

DROP TABLE IF EXISTS `tl_gem_tiar_tipoarchivo`;
CREATE TABLE `tl_gem_tiar_tipoarchivo` (
  `tiar_num_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tiar_str_cogido` varchar(3) NOT NULL,
  `tiar_str_descripcion` varchar(220) NOT NULL,
  PRIMARY KEY (`tiar_num_id`),
  UNIQUE KEY `unique_codigo` (`tiar_str_cogido`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tl_gem_tiar_tipoarchivo`
--

/*!40000 ALTER TABLE `tl_gem_tiar_tipoarchivo` DISABLE KEYS */;
/*!40000 ALTER TABLE `tl_gem_tiar_tipoarchivo` ENABLE KEYS */;


--
-- Definition of table `tl_gem_ubar_ubicacionarchivo`
--

DROP TABLE IF EXISTS `tl_gem_ubar_ubicacionarchivo`;
CREATE TABLE `tl_gem_ubar_ubicacionarchivo` (
  `ubar_num_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ubar_str_path` text NOT NULL,
  `ubar_str_tipo_archivo` varchar(220) NOT NULL,
  PRIMARY KEY (`ubar_num_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tl_gem_ubar_ubicacionarchivo`
--

/*!40000 ALTER TABLE `tl_gem_ubar_ubicacionarchivo` DISABLE KEYS */;
/*!40000 ALTER TABLE `tl_gem_ubar_ubicacionarchivo` ENABLE KEYS */;


--
-- Definition of table `tl_seg_daus_datosusuario`
--

DROP TABLE IF EXISTS `tl_seg_daus_datosusuario`;
CREATE TABLE `tl_seg_daus_datosusuario` (
  `daus_num_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daus_num_id_estado` varchar(45) NOT NULL,
  `daus_str_nombre` varchar(220) NOT NULL,
  `daus_dte_timestamp` datetime DEFAULT NULL,
  `daus_str_apellido` varchar(220) NOT NULL,
  `daus_str_email` varchar(220) NOT NULL,
  `daus_str_telefono` varchar(45) DEFAULT NULL,
  `daus_str_celular` varchar(9) DEFAULT NULL,
  `daus_str_direccion` varchar(220) NOT NULL,
  `daus_num_id_pais` bigint(20) DEFAULT NULL,
  `daus_num_id_departamento` bigint(20) DEFAULT NULL,
  `daus_num_id_ciudad` bigint(20) DEFAULT NULL,
  `daus_dte_fecha_registro` datetime NOT NULL DEFAULT '2010-01-01 00:00:01',
  `daus_num_id_perfil` bigint(20) NOT NULL,
  PRIMARY KEY (`daus_num_id`),
  KEY `FK_daus_num_id_pais` (`daus_num_id_pais`),
  KEY `FK_daus_num_id_departamentos` (`daus_num_id_departamento`),
  KEY `FK_daus_num_id_ciudad` (`daus_num_id_ciudad`),
  KEY `FK_daus_num_id_perfil` (`daus_num_id_perfil`),
  CONSTRAINT `FK_daus_num_id_ciudad` FOREIGN KEY (`daus_num_id_ciudad`) REFERENCES `tl_adm_ciud_ciudad` (`ciud_num_id`),
  CONSTRAINT `FK_daus_num_id_departamentos` FOREIGN KEY (`daus_num_id_departamento`) REFERENCES `tl_adm_deto_departamento` (`deto_num_id`),
  CONSTRAINT `FK_daus_num_id_pais` FOREIGN KEY (`daus_num_id_pais`) REFERENCES `tl_adm_pais_pais` (`pais_num_id`),
  CONSTRAINT `FK_daus_num_id_perfil` FOREIGN KEY (`daus_num_id_perfil`) REFERENCES `tl_seg_perf_perfil` (`perf_num_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tl_seg_daus_datosusuario`
--

/*!40000 ALTER TABLE `tl_seg_daus_datosusuario` DISABLE KEYS */;
INSERT INTO `tl_seg_daus_datosusuario` (`daus_num_id`,`daus_num_id_estado`,`daus_str_nombre`,`daus_dte_timestamp`,`daus_str_apellido`,`daus_str_email`,`daus_str_telefono`,`daus_str_celular`,`daus_str_direccion`,`daus_num_id_pais`,`daus_num_id_departamento`,`daus_num_id_ciudad`,`daus_dte_fecha_registro`,`daus_num_id_perfil`) VALUES 
 (1,'Activo','admin','2010-01-01 00:00:01','admin','admin@inia.com',NULL,NULL,'-',21,474,2,'2010-01-01 00:00:01',1);
/*!40000 ALTER TABLE `tl_seg_daus_datosusuario` ENABLE KEYS */;


--
-- Definition of table `tl_seg_perf_perfil`
--

DROP TABLE IF EXISTS `tl_seg_perf_perfil`;
CREATE TABLE `tl_seg_perf_perfil` (
  `perf_num_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `perf_str_estado` varchar(45) NOT NULL,
  `perf_str_nombre` varchar(45) NOT NULL,
  `perf_str_descripcion` varchar(220) DEFAULT NULL,
  `perf_bol_fijo` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`perf_num_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tl_seg_perf_perfil`
--

/*!40000 ALTER TABLE `tl_seg_perf_perfil` DISABLE KEYS */;
INSERT INTO `tl_seg_perf_perfil` (`perf_num_id`,`perf_str_estado`,`perf_str_nombre`,`perf_str_descripcion`,`perf_bol_fijo`) VALUES 
 (1,'Activo','Administrador','Administrador del sistema',1),
 (2,'Activo','Investigador','Investigadores de MSCC',1),
 (3,'Activo','Publico','Público en general',1);
/*!40000 ALTER TABLE `tl_seg_perf_perfil` ENABLE KEYS */;


--
-- Definition of table `tl_seg_trpe_transaccionperfil`
--

DROP TABLE IF EXISTS `tl_seg_trpe_transaccionperfil`;
CREATE TABLE `tl_seg_trpe_transaccionperfil` (
  `trpe_num_id_perfil` bigint(20) NOT NULL,
  `trpe_num_id_transaccion` bigint(20) NOT NULL,
  PRIMARY KEY (`trpe_num_id_transaccion`,`trpe_num_id_perfil`) USING BTREE,
  KEY `FK_trpe_num_id_transaccion` (`trpe_num_id_transaccion`),
  KEY `FK_trpe_num_id_perfil` (`trpe_num_id_perfil`),
  CONSTRAINT `FK_trpe_num_id_perfil` FOREIGN KEY (`trpe_num_id_perfil`) REFERENCES `tl_seg_perf_perfil` (`perf_num_id`),
  CONSTRAINT `FK_trpe_num_id_transaccion` FOREIGN KEY (`trpe_num_id_transaccion`) REFERENCES `tl_adm_tran_transaccion` (`tran_num_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tl_seg_trpe_transaccionperfil`
--

/*!40000 ALTER TABLE `tl_seg_trpe_transaccionperfil` DISABLE KEYS */;
INSERT INTO `tl_seg_trpe_transaccionperfil` (`trpe_num_id_perfil`,`trpe_num_id_transaccion`) VALUES 
 (1,1),
 (1,2),
 (1,3),
 (1,4),
 (1,5),
 (1,6),
 (1,7),
 (1,8),
 (1,9),
 (1,10),
 (1,11),
 (1,12),
 (1,13),
 (1,14),
 (1,15),
 (1,16),
 (1,17),
 (1,18),
 (1,19),
 (1,20),
 (1,21),
 (1,22),
 (1,23),
 (1,24),
 (1,25),
 (1,26),
 (1,27),
 (1,28),
 (1,29);
/*!40000 ALTER TABLE `tl_seg_trpe_transaccionperfil` ENABLE KEYS */;


--
-- Definition of table `tl_seg_usua_usuario`
--

DROP TABLE IF EXISTS `tl_seg_usua_usuario`;
CREATE TABLE `tl_seg_usua_usuario` (
  `usua_num_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `usua_str_login` varchar(20) NOT NULL,
  `usua_str_password` varchar(220) NOT NULL,
  `usua_bol_activado` tinyint(1) NOT NULL DEFAULT '0',
  `usua_dte_ultimo_acceso` datetime NOT NULL DEFAULT '2010-01-01 00:00:01',
  `usua_str_estado_usuario` varchar(45) NOT NULL DEFAULT 'Ninguno',
  `usua_num_id_dato_usuario` bigint(20) NOT NULL,
  `usua_str_frase` varchar(250) NOT NULL,
  `usua_str_codigo_activacion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`usua_num_id`),
  KEY `FK_usua_num_id_dato_usuario` (`usua_num_id_dato_usuario`),
  CONSTRAINT `FK_usua_num_id_dato_usuario` FOREIGN KEY (`usua_num_id_dato_usuario`) REFERENCES `tl_seg_daus_datosusuario` (`daus_num_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tl_seg_usua_usuario`
--

/*!40000 ALTER TABLE `tl_seg_usua_usuario` DISABLE KEYS */;
INSERT INTO `tl_seg_usua_usuario` (`usua_num_id`,`usua_str_login`,`usua_str_password`,`usua_bol_activado`,`usua_dte_ultimo_acceso`,`usua_str_estado_usuario`,`usua_num_id_dato_usuario`,`usua_str_frase`,`usua_str_codigo_activacion`) VALUES 
 (1,'admin','+GW1NiOxIf007lQmx5Llwzr4wic=',1,'2010-02-17 20:37:21','Activo',1,'ProyectoFinal','54032683');
/*!40000 ALTER TABLE `tl_seg_usua_usuario` ENABLE KEYS */;


--
-- Definition of procedure `todos`
--

DROP PROCEDURE IF EXISTS `todos`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ $$
CREATE DEFINER=`inia_db`@`%` PROCEDURE `todos`()
BEGIN
select * from user;
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
