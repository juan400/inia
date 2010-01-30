-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.40-community


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
-- Definition of table `country`
--

DROP TABLE IF EXISTS `country`;
CREATE TABLE `country` (
  `iso` char(2) NOT NULL,
  `name` varchar(80) NOT NULL,
  `printable_name` varchar(80) NOT NULL,
  `iso3` char(3) DEFAULT NULL,
  `numcode` smallint(6) unsigned DEFAULT NULL,
  PRIMARY KEY (`iso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `country`
--

/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` (`iso`,`name`,`printable_name`,`iso3`,`numcode`) VALUES 
 ('AD','ANDORRA','Andorra','AND',20),
 ('AE','UNITED ARAB EMIRATES','United Arab Emirates','ARE',784),
 ('AF','AFGHANISTAN','Afghanistan','AFG',4),
 ('AG','ANTIGUA AND BARBUDA','Antigua and Barbuda','ATG',28),
 ('AI','ANGUILLA','Anguilla','AIA',660),
 ('AL','ALBANIA','Albania','ALB',8),
 ('AM','ARMENIA','Armenia','ARM',51),
 ('AN','NETHERLANDS ANTILLES','Netherlands Antilles','ANT',530),
 ('AO','ANGOLA','Angola','AGO',24),
 ('AQ','ANTARCTICA','Antarctica',NULL,NULL),
 ('AR','ARGENTINA','Argentina','ARG',32),
 ('AS','AMERICAN SAMOA','American Samoa','ASM',16),
 ('AT','AUSTRIA','Austria','AUT',40),
 ('AU','AUSTRALIA','Australia','AUS',36),
 ('AW','ARUBA','Aruba','ABW',533),
 ('AZ','AZERBAIJAN','Azerbaijan','AZE',31),
 ('BA','BOSNIA AND HERZEGOVINA','Bosnia and Herzegovina','BIH',70),
 ('BB','BARBADOS','Barbados','BRB',52),
 ('BD','BANGLADESH','Bangladesh','BGD',50),
 ('BE','BELGIUM','Belgium','BEL',56),
 ('BF','BURKINA FASO','Burkina Faso','BFA',854),
 ('BG','BULGARIA','Bulgaria','BGR',100),
 ('BH','BAHRAIN','Bahrain','BHR',48),
 ('BI','BURUNDI','Burundi','BDI',108),
 ('BJ','BENIN','Benin','BEN',204),
 ('BM','BERMUDA','Bermuda','BMU',60),
 ('BN','BRUNEI DARUSSALAM','Brunei Darussalam','BRN',96),
 ('BO','BOLIVIA','Bolivia','BOL',68),
 ('BR','BRAZIL','Brazil','BRA',76),
 ('BS','BAHAMAS','Bahamas','BHS',44),
 ('BT','BHUTAN','Bhutan','BTN',64),
 ('BV','BOUVET ISLAND','Bouvet Island',NULL,NULL),
 ('BW','BOTSWANA','Botswana','BWA',72),
 ('BY','BELARUS','Belarus','BLR',112),
 ('BZ','BELIZE','Belize','BLZ',84),
 ('CA','CANADA','Canada','CAN',124),
 ('CC','COCOS (KEELING) ISLANDS','Cocos (Keeling) Islands',NULL,NULL),
 ('CD','CONGO, THE DEMOCRATIC REPUBLIC OF THE','Congo, the Democratic Republic of the','COD',180),
 ('CF','CENTRAL AFRICAN REPUBLIC','Central African Republic','CAF',140),
 ('CG','CONGO','Congo','COG',178),
 ('CH','SWITZERLAND','Switzerland','CHE',756),
 ('CI','COTE D\'IVOIRE','Cote D\'Ivoire','CIV',384),
 ('CK','COOK ISLANDS','Cook Islands','COK',184),
 ('CL','CHILE','Chile','CHL',152),
 ('CM','CAMEROON','Cameroon','CMR',120),
 ('CN','CHINA','China','CHN',156),
 ('CO','COLOMBIA','Colombia','COL',170),
 ('CR','COSTA RICA','Costa Rica','CRI',188),
 ('CS','SERBIA AND MONTENEGRO','Serbia and Montenegro',NULL,NULL),
 ('CU','CUBA','Cuba','CUB',192),
 ('CV','CAPE VERDE','Cape Verde','CPV',132),
 ('CX','CHRISTMAS ISLAND','Christmas Island',NULL,NULL),
 ('CY','CYPRUS','Cyprus','CYP',196),
 ('CZ','CZECH REPUBLIC','Czech Republic','CZE',203),
 ('DE','GERMANY','Germany','DEU',276),
 ('DJ','DJIBOUTI','Djibouti','DJI',262),
 ('DK','DENMARK','Denmark','DNK',208),
 ('DM','DOMINICA','Dominica','DMA',212),
 ('DO','DOMINICAN REPUBLIC','Dominican Republic','DOM',214),
 ('DZ','ALGERIA','Algeria','DZA',12),
 ('EC','ECUADOR','Ecuador','ECU',218),
 ('EE','ESTONIA','Estonia','EST',233),
 ('EG','EGYPT','Egypt','EGY',818),
 ('EH','WESTERN SAHARA','Western Sahara','ESH',732),
 ('ER','ERITREA','Eritrea','ERI',232),
 ('ES','SPAIN','Spain','ESP',724),
 ('ET','ETHIOPIA','Ethiopia','ETH',231),
 ('FI','FINLAND','Finland','FIN',246),
 ('FJ','FIJI','Fiji','FJI',242),
 ('FK','FALKLAND ISLANDS (MALVINAS)','Falkland Islands (Malvinas)','FLK',238),
 ('FM','MICRONESIA, FEDERATED STATES OF','Micronesia, Federated States of','FSM',583),
 ('FO','FAROE ISLANDS','Faroe Islands','FRO',234),
 ('FR','FRANCE','France','FRA',250),
 ('GA','GABON','Gabon','GAB',266),
 ('GB','UNITED KINGDOM','United Kingdom','GBR',826),
 ('GD','GRENADA','Grenada','GRD',308),
 ('GE','GEORGIA','Georgia','GEO',268),
 ('GF','FRENCH GUIANA','French Guiana','GUF',254),
 ('GH','GHANA','Ghana','GHA',288),
 ('GI','GIBRALTAR','Gibraltar','GIB',292),
 ('GL','GREENLAND','Greenland','GRL',304),
 ('GM','GAMBIA','Gambia','GMB',270),
 ('GN','GUINEA','Guinea','GIN',324),
 ('GP','GUADELOUPE','Guadeloupe','GLP',312),
 ('GQ','EQUATORIAL GUINEA','Equatorial Guinea','GNQ',226),
 ('GR','GREECE','Greece','GRC',300),
 ('GS','SOUTH GEORGIA AND THE SOUTH SANDWICH ISLANDS','South Georgia and the South Sandwich Islands',NULL,NULL),
 ('GT','GUATEMALA','Guatemala','GTM',320),
 ('GU','GUAM','Guam','GUM',316),
 ('GW','GUINEA-BISSAU','Guinea-Bissau','GNB',624),
 ('GY','GUYANA','Guyana','GUY',328),
 ('HK','HONG KONG','Hong Kong','HKG',344),
 ('HM','HEARD ISLAND AND MCDONALD ISLANDS','Heard Island and Mcdonald Islands',NULL,NULL),
 ('HN','HONDURAS','Honduras','HND',340),
 ('HR','CROATIA','Croatia','HRV',191),
 ('HT','HAITI','Haiti','HTI',332),
 ('HU','HUNGARY','Hungary','HUN',348),
 ('ID','INDONESIA','Indonesia','IDN',360),
 ('IE','IRELAND','Ireland','IRL',372),
 ('IL','ISRAEL','Israel','ISR',376),
 ('IN','INDIA','India','IND',356),
 ('IO','BRITISH INDIAN OCEAN TERRITORY','British Indian Ocean Territory',NULL,NULL),
 ('IQ','IRAQ','Iraq','IRQ',368),
 ('IR','IRAN, ISLAMIC REPUBLIC OF','Iran, Islamic Republic of','IRN',364),
 ('IS','ICELAND','Iceland','ISL',352),
 ('IT','ITALY','Italy','ITA',380),
 ('JM','JAMAICA','Jamaica','JAM',388),
 ('JO','JORDAN','Jordan','JOR',400),
 ('JP','JAPAN','Japan','JPN',392),
 ('KE','KENYA','Kenya','KEN',404),
 ('KG','KYRGYZSTAN','Kyrgyzstan','KGZ',417),
 ('KH','CAMBODIA','Cambodia','KHM',116),
 ('KI','KIRIBATI','Kiribati','KIR',296),
 ('KM','COMOROS','Comoros','COM',174),
 ('KN','SAINT KITTS AND NEVIS','Saint Kitts and Nevis','KNA',659),
 ('KP','KOREA, DEMOCRATIC PEOPLE\'S REPUBLIC OF','Korea, Democratic People\'s Republic of','PRK',408),
 ('KR','KOREA, REPUBLIC OF','Korea, Republic of','KOR',410),
 ('KW','KUWAIT','Kuwait','KWT',414),
 ('KY','CAYMAN ISLANDS','Cayman Islands','CYM',136),
 ('KZ','KAZAKHSTAN','Kazakhstan','KAZ',398),
 ('LA','LAO PEOPLE\'S DEMOCRATIC REPUBLIC','Lao People\'s Democratic Republic','LAO',418),
 ('LB','LEBANON','Lebanon','LBN',422),
 ('LC','SAINT LUCIA','Saint Lucia','LCA',662),
 ('LI','LIECHTENSTEIN','Liechtenstein','LIE',438),
 ('LK','SRI LANKA','Sri Lanka','LKA',144),
 ('LR','LIBERIA','Liberia','LBR',430),
 ('LS','LESOTHO','Lesotho','LSO',426),
 ('LT','LITHUANIA','Lithuania','LTU',440),
 ('LU','LUXEMBOURG','Luxembourg','LUX',442),
 ('LV','LATVIA','Latvia','LVA',428),
 ('LY','LIBYAN ARAB JAMAHIRIYA','Libyan Arab Jamahiriya','LBY',434),
 ('MA','MOROCCO','Morocco','MAR',504),
 ('MC','MONACO','Monaco','MCO',492),
 ('MD','MOLDOVA, REPUBLIC OF','Moldova, Republic of','MDA',498),
 ('MG','MADAGASCAR','Madagascar','MDG',450),
 ('MH','MARSHALL ISLANDS','Marshall Islands','MHL',584),
 ('MK','MACEDONIA, THE FORMER YUGOSLAV REPUBLIC OF','Macedonia, the Former Yugoslav Republic of','MKD',807),
 ('ML','MALI','Mali','MLI',466),
 ('MM','MYANMAR','Myanmar','MMR',104),
 ('MN','MONGOLIA','Mongolia','MNG',496),
 ('MO','MACAO','Macao','MAC',446),
 ('MP','NORTHERN MARIANA ISLANDS','Northern Mariana Islands','MNP',580),
 ('MQ','MARTINIQUE','Martinique','MTQ',474),
 ('MR','MAURITANIA','Mauritania','MRT',478),
 ('MS','MONTSERRAT','Montserrat','MSR',500),
 ('MT','MALTA','Malta','MLT',470),
 ('MU','MAURITIUS','Mauritius','MUS',480),
 ('MV','MALDIVES','Maldives','MDV',462),
 ('MW','MALAWI','Malawi','MWI',454),
 ('MX','MEXICO','Mexico','MEX',484),
 ('MY','MALAYSIA','Malaysia','MYS',458),
 ('MZ','MOZAMBIQUE','Mozambique','MOZ',508),
 ('NA','NAMIBIA','Namibia','NAM',516),
 ('NC','NEW CALEDONIA','New Caledonia','NCL',540),
 ('NE','NIGER','Niger','NER',562),
 ('NF','NORFOLK ISLAND','Norfolk Island','NFK',574),
 ('NG','NIGERIA','Nigeria','NGA',566),
 ('NI','NICARAGUA','Nicaragua','NIC',558),
 ('NL','NETHERLANDS','Netherlands','NLD',528),
 ('NO','NORWAY','Norway','NOR',578),
 ('NP','NEPAL','Nepal','NPL',524),
 ('NR','NAURU','Nauru','NRU',520),
 ('NU','NIUE','Niue','NIU',570),
 ('NZ','NEW ZEALAND','New Zealand','NZL',554),
 ('OM','OMAN','Oman','OMN',512),
 ('PA','PANAMA','Panama','PAN',591),
 ('PE','PERU','Peru','PER',604),
 ('PF','FRENCH POLYNESIA','French Polynesia','PYF',258),
 ('PG','PAPUA NEW GUINEA','Papua New Guinea','PNG',598),
 ('PH','PHILIPPINES','Philippines','PHL',608),
 ('PK','PAKISTAN','Pakistan','PAK',586),
 ('PL','POLAND','Poland','POL',616),
 ('PM','SAINT PIERRE AND MIQUELON','Saint Pierre and Miquelon','SPM',666),
 ('PN','PITCAIRN','Pitcairn','PCN',612),
 ('PR','PUERTO RICO','Puerto Rico','PRI',630),
 ('PS','PALESTINIAN TERRITORY, OCCUPIED','Palestinian Territory, Occupied',NULL,NULL),
 ('PT','PORTUGAL','Portugal','PRT',620),
 ('PW','PALAU','Palau','PLW',585),
 ('PY','PARAGUAY','Paraguay','PRY',600),
 ('QA','QATAR','Qatar','QAT',634),
 ('RE','REUNION','Reunion','REU',638),
 ('RO','ROMANIA','Romania','ROM',642),
 ('RU','RUSSIAN FEDERATION','Russian Federation','RUS',643),
 ('RW','RWANDA','Rwanda','RWA',646),
 ('SA','SAUDI ARABIA','Saudi Arabia','SAU',682),
 ('SB','SOLOMON ISLANDS','Solomon Islands','SLB',90),
 ('SC','SEYCHELLES','Seychelles','SYC',690),
 ('SD','SUDAN','Sudan','SDN',736),
 ('SE','SWEDEN','Sweden','SWE',752),
 ('SG','SINGAPORE','Singapore','SGP',702),
 ('SH','SAINT HELENA','Saint Helena','SHN',654),
 ('SI','SLOVENIA','Slovenia','SVN',705),
 ('SJ','SVALBARD AND JAN MAYEN','Svalbard and Jan Mayen','SJM',744),
 ('SK','SLOVAKIA','Slovakia','SVK',703),
 ('SL','SIERRA LEONE','Sierra Leone','SLE',694),
 ('SM','SAN MARINO','San Marino','SMR',674),
 ('SN','SENEGAL','Senegal','SEN',686),
 ('SO','SOMALIA','Somalia','SOM',706),
 ('SR','SURINAME','Suriname','SUR',740),
 ('ST','SAO TOME AND PRINCIPE','Sao Tome and Principe','STP',678),
 ('SV','EL SALVADOR','El Salvador','SLV',222),
 ('SY','SYRIAN ARAB REPUBLIC','Syrian Arab Republic','SYR',760),
 ('SZ','SWAZILAND','Swaziland','SWZ',748),
 ('TC','TURKS AND CAICOS ISLANDS','Turks and Caicos Islands','TCA',796),
 ('TD','CHAD','Chad','TCD',148),
 ('TF','FRENCH SOUTHERN TERRITORIES','French Southern Territories',NULL,NULL),
 ('TG','TOGO','Togo','TGO',768),
 ('TH','THAILAND','Thailand','THA',764),
 ('TJ','TAJIKISTAN','Tajikistan','TJK',762),
 ('TK','TOKELAU','Tokelau','TKL',772),
 ('TL','TIMOR-LESTE','Timor-Leste',NULL,NULL),
 ('TM','TURKMENISTAN','Turkmenistan','TKM',795),
 ('TN','TUNISIA','Tunisia','TUN',788),
 ('TO','TONGA','Tonga','TON',776),
 ('TR','TURKEY','Turkey','TUR',792),
 ('TT','TRINIDAD AND TOBAGO','Trinidad and Tobago','TTO',780),
 ('TV','TUVALU','Tuvalu','TUV',798),
 ('TW','TAIWAN, PROVINCE OF CHINA','Taiwan, Province of China','TWN',158),
 ('TZ','TANZANIA, UNITED REPUBLIC OF','Tanzania, United Republic of','TZA',834),
 ('UA','UKRAINE','Ukraine','UKR',804),
 ('UG','UGANDA','Uganda','UGA',800),
 ('UM','UNITED STATES MINOR OUTLYING ISLANDS','United States Minor Outlying Islands',NULL,NULL),
 ('US','UNITED STATES','United States','USA',840),
 ('UY','URUGUAY','Uruguay','URY',858),
 ('UZ','UZBEKISTAN','Uzbekistan','UZB',860),
 ('VA','HOLY SEE (VATICAN CITY STATE)','Holy See (Vatican City State)','VAT',336),
 ('VC','SAINT VINCENT AND THE GRENADINES','Saint Vincent and the Grenadines','VCT',670),
 ('VE','VENEZUELA','Venezuela','VEN',862),
 ('VG','VIRGIN ISLANDS, BRITISH','Virgin Islands, British','VGB',92),
 ('VI','VIRGIN ISLANDS, U.S.','Virgin Islands, U.s.','VIR',850),
 ('VN','VIET NAM','Viet Nam','VNM',704),
 ('VU','VANUATU','Vanuatu','VUT',548),
 ('WF','WALLIS AND FUTUNA','Wallis and Futuna','WLF',876),
 ('WS','SAMOA','Samoa','WSM',882),
 ('YE','YEMEN','Yemen','YEM',887),
 ('YT','MAYOTTE','Mayotte',NULL,NULL),
 ('ZA','SOUTH AFRICA','South Africa','ZAF',710),
 ('ZM','ZAMBIA','Zambia','ZMB',894),
 ('ZW','ZIMBABWE','Zimbabwe','ZWE',716);
/*!40000 ALTER TABLE `country` ENABLE KEYS */;


--
-- Definition of table `estados`
--

DROP TABLE IF EXISTS `estados`;
CREATE TABLE `estados` (
  `id` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `estado` varchar(100) NOT NULL,
  `relacion` varchar(100) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `codigo_pais` (`relacion`)
) ENGINE=MyISAM AUTO_INCREMENT=4293 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `estados`
--

/*!40000 ALTER TABLE `estados` DISABLE KEYS */;
INSERT INTO `estados` (`id`,`estado`,`relacion`) VALUES 
 (1,'Badakhshan','1'),
 (2,'Badghis','1'),
 (3,'Baghlan','1'),
 (4,'Balkh','1'),
 (5,'Bamian','1'),
 (6,'Farah','1'),
 (7,'Faryab','1'),
 (8,'Ghazni','1'),
 (9,'Ghowr','1'),
 (10,'Helmand','1'),
 (11,'Herat','1'),
 (12,'Jowzjan','1'),
 (13,'Kabol','1'),
 (14,'Kandahar','1'),
 (15,'Kapisa','1'),
 (16,'Khowst','1'),
 (17,'Konar','1'),
 (18,'Konar','1'),
 (19,'Kondoz','1'),
 (20,'Laghman','1'),
 (21,'Laghman','1'),
 (22,'Lowgar','1'),
 (23,'Nangarhar','1'),
 (24,'Nimruz','1'),
 (25,'Nurestan','1'),
 (26,'Oruzgan','1'),
 (27,'Paktia','1'),
 (28,'Paktia','1'),
 (29,'Paktika','1'),
 (30,'Parvan','1'),
 (31,'Samangan','1'),
 (32,'Sar-e Pol','1'),
 (33,'Takhar','1'),
 (34,'Vardak','1'),
 (35,'Zabol','1'),
 (36,'Berat','2'),
 (37,'Diber','2'),
 (38,'Durres','2'),
 (39,'Elbasan','2'),
 (40,'Fier','2'),
 (41,'Gjirokaster','2'),
 (42,'Korce','2'),
 (43,'Kukes','2'),
 (44,'Lezhe','2'),
 (45,'Shkoder','2'),
 (46,'Tirane','2'),
 (47,'Vlore','2'),
 (48,'Baden-Wberg Bayern','3'),
 (49,'Berlin','3'),
 (50,'Brandenburg','3'),
 (51,'Bremen','3'),
 (52,'Hamburg','3'),
 (53,'Hessen','3'),
 (54,'Mecklenburg-Vorpommern','3'),
 (55,'Niedersachsen','3'),
 (56,'Nordrhein-Westfalen','3'),
 (57,'Rheinland-Pfalz','3'),
 (58,'Saarland','3'),
 (59,'Sachsen','3'),
 (60,'Sachsen-Anhalt','3'),
 (61,'Schleswig-Holstein','3'),
 (62,'Thuringen','3'),
 (63,'American Samoa (General)','4'),
 (64,'Andorra la Vella','5'),
 (65,'Canillo','5'),
 (66,'Encamp','5'),
 (67,'Escaldes-Engordany','5'),
 (68,'La Massana','5'),
 (69,'Ordino','5'),
 (70,'Sant Julia de Loria','5'),
 (71,'Barbuda','5'),
 (72,'Saint George','5'),
 (73,'Saint John','5'),
 (74,'Saint Mary','5'),
 (75,'Saint Paul','5'),
 (76,'Saint Peter','5'),
 (77,'Saint Philip','5'),
 (78,'Bengo','6'),
 (79,'Benguela','6'),
 (80,'Bie','6'),
 (81,'Cabinda','6'),
 (82,'Cuando Cubango','6'),
 (83,'Cuanza Norte','6'),
 (84,'Cuanza Sul','6'),
 (85,'Cunene','6'),
 (86,'Huambo','6'),
 (87,'Huila','6'),
 (88,'Luanda','6'),
 (89,'Luanda Province','6'),
 (90,'Lunda Norte','6'),
 (91,'Lunda Sul','6'),
 (92,'Malanje','6'),
 (93,'Moxico','6'),
 (94,'Uige','6'),
 (95,'Zaire','6'),
 (96,'Anguilla (General)','7'),
 (97,'Barbuda','8'),
 (98,'Saint George','8'),
 (99,'Saint John','8'),
 (100,'Saint Mary','8'),
 (101,'Saint Paul','8'),
 (102,'Saint Peter','8'),
 (103,'Saint Philip','8'),
 (104,'Netherlands Antilles (General)','9'),
 (105,'Antarctica (General)','10'),
 (106,'Al Bahah','11'),
 (107,'Al Hudud ash Shamaliyah','11'),
 (108,'Al Jawf','11'),
 (109,'Al Jawf','11'),
 (110,'Al Madinah','11'),
 (111,'Al Qasim','11'),
 (112,'Al Qurayyat','11'),
 (113,'Ar Riyad','11'),
 (114,'Ash Sharqiyah','11'),
 (115,'Hail','11'),
 (116,'Jizan','11'),
 (117,'Makkah','11'),
 (118,'Najran','11'),
 (119,'Tabuk','11'),
 (120,'Adrar','12'),
 (121,'Ain Defla','12'),
 (122,'Ain Temouchent','12'),
 (123,'Alger','12'),
 (124,'Annaba','12'),
 (125,'Batna','12'),
 (126,'Bechar','12'),
 (127,'Bejaia','12'),
 (128,'Biskra','12'),
 (129,'Blida','12'),
 (130,'Bordj Bou Arreridj','12'),
 (131,'Bouira','12'),
 (132,'Boumerdes','12'),
 (133,'Chlef','12'),
 (134,'Constantine','12'),
 (135,'Djelfa','12'),
 (136,'El Bayadh','12'),
 (137,'El Oued','12'),
 (138,'El Tarf','12'),
 (139,'Ghardaia','12'),
 (140,'Guelma','12'),
 (141,'Illizi','12'),
 (142,'Jijel','12'),
 (143,'Khenchela','12'),
 (144,'Laghouat','12'),
 (145,'Msila','12'),
 (146,'Mascara','12'),
 (147,'Medea','12'),
 (148,'Mila','12'),
 (149,'Mostaganem','12'),
 (150,'Naama','12'),
 (151,'Oran','12'),
 (152,'Ouargla','12'),
 (153,'Oum el Bouaghi','12'),
 (154,'Relizane','12'),
 (155,'Saida','12'),
 (156,'Setif','12'),
 (157,'Sidi Bel Abbes','12'),
 (158,'Skikda','12'),
 (159,'Souk Ahras','12'),
 (160,'Tamanghasset','12'),
 (161,'Tebessa','12'),
 (162,'Tiaret','12'),
 (163,'Tindouf','12'),
 (164,'Tipaza','12'),
 (165,'Tissemsilt','12'),
 (166,'Tizi Ouzou','12'),
 (167,'Tlemcen','12'),
 (168,'Buenos Aires','13'),
 (169,'Catamarca','13'),
 (170,'Chaco','13'),
 (171,'Chubut','13'),
 (172,'Cordoba','13'),
 (173,'Corrientes','13'),
 (174,'Distrito Federal','13'),
 (175,'Entre Rios','13'),
 (176,'Formosa','13'),
 (177,'Jujuy','13'),
 (178,'La Pampa','13'),
 (179,'La Rioja','13'),
 (180,'Mendoza','13'),
 (181,'Misiones','13'),
 (182,'Neuquen','13'),
 (183,'Rio Negro','13'),
 (184,'Salta','13'),
 (185,'San Juan','13'),
 (186,'San Luis','13'),
 (187,'Santa Cruz','13'),
 (188,'Santa Fe','13'),
 (189,'Santiago del Estero','13'),
 (190,'Tierra del Fuego','13'),
 (191,'Tucuman','13'),
 (192,'Aragatsotn','14'),
 (193,'Ararat','14'),
 (194,'Armavir','14'),
 (195,'Gegharkunik','14'),
 (196,'Kotayk','14'),
 (197,'Lorri','14'),
 (198,'Shirak','14'),
 (199,'Syunik','14'),
 (200,'Tavush','14'),
 (201,'Vayots Dzor','14'),
 (202,'Yerevan','14'),
 (203,'Aruba (General)','15'),
 (205,'Australian Capital Territory','17'),
 (206,'New South Wales','17'),
 (207,'Northern Territory','1');
/*!40000 ALTER TABLE `estados` ENABLE KEYS */;


--
-- Definition of table `pai_pais`
--

DROP TABLE IF EXISTS `pai_pais`;
CREATE TABLE `pai_pais` (
  `PAI_PK` int(11) NOT NULL AUTO_INCREMENT,
  `PAI_ISONUM` smallint(6) DEFAULT NULL,
  `PAI_ISO2` char(2) DEFAULT NULL,
  `PAI_ISO3` char(3) DEFAULT NULL,
  `PAI_NOMBRE` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`PAI_PK`)
) ENGINE=InnoDB AUTO_INCREMENT=241 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pai_pais`
--

/*!40000 ALTER TABLE `pai_pais` DISABLE KEYS */;
INSERT INTO `pai_pais` (`PAI_PK`,`PAI_ISONUM`,`PAI_ISO2`,`PAI_ISO3`,`PAI_NOMBRE`) VALUES 
 (1,4,'AF','AFG','Afganistán'),
 (2,248,'AX','ALA','Islas Gland'),
 (3,8,'AL','ALB','Albania'),
 (4,276,'DE','DEU','Alemania'),
 (5,20,'AD','AND','Andorra'),
 (6,24,'AO','AGO','Angola'),
 (7,660,'AI','AIA','Anguilla'),
 (8,10,'AQ','ATA','Antártida'),
 (9,28,'AG','ATG','Antigua y Barbuda'),
 (10,530,'AN','ANT','Antillas Holandesas'),
 (11,682,'SA','SAU','Arabia Saudí'),
 (12,12,'DZ','DZA','Argelia'),
 (13,32,'AR','ARG','Argentina'),
 (14,51,'AM','ARM','Armenia'),
 (15,533,'AW','ABW','Aruba'),
 (16,36,'AU','AUS','Australia'),
 (17,40,'AT','AUT','Austria'),
 (18,31,'AZ','AZE','Azerbaiyán'),
 (19,44,'BS','BHS','Bahamas'),
 (20,48,'BH','BHR','Bahréin'),
 (21,50,'BD','BGD','Bangladesh'),
 (22,52,'BB','BRB','Barbados'),
 (23,112,'BY','BLR','Bielorrusia'),
 (24,56,'BE','BEL','Bélgica'),
 (25,84,'BZ','BLZ','Belice'),
 (26,204,'BJ','BEN','Benin'),
 (27,60,'BM','BMU','Bermudas'),
 (28,64,'BT','BTN','Bhután'),
 (29,68,'BO','BOL','Bolivia'),
 (30,70,'BA','BIH','Bosnia y Herzegovina'),
 (31,72,'BW','BWA','Botsuana'),
 (32,74,'BV','BVT','Isla Bouvet'),
 (33,76,'BR','BRA','Brasil'),
 (34,96,'BN','BRN','Brunéi'),
 (35,100,'BG','BGR','Bulgaria'),
 (36,854,'BF','BFA','Burkina Faso'),
 (37,108,'BI','BDI','Burundi'),
 (38,132,'CV','CPV','Cabo Verde'),
 (39,136,'KY','CYM','Islas Caimán'),
 (40,116,'KH','KHM','Camboya'),
 (41,120,'CM','CMR','Camerún'),
 (42,124,'CA','CAN','Canadá'),
 (43,140,'CF','CAF','República Centroafricana'),
 (44,148,'TD','TCD','Chad'),
 (45,203,'CZ','CZE','República Checa'),
 (46,152,'CL','CHL','Chile'),
 (47,156,'CN','CHN','China'),
 (48,196,'CY','CYP','Chipre'),
 (49,162,'CX','CXR','Isla de Navidad'),
 (50,336,'VA','VAT','Ciudad del Vaticano'),
 (51,166,'CC','CCK','Islas Cocos'),
 (52,170,'CO','COL','Colombia'),
 (53,174,'KM','COM','Comoras'),
 (54,180,'CD','COD','República Democrática del Congo'),
 (55,178,'CG','COG','Congo'),
 (56,184,'CK','COK','Islas Cook'),
 (57,408,'KP','PRK','Corea del Norte'),
 (58,410,'KR','KOR','Corea del Sur'),
 (59,384,'CI','CIV','Costa de Marfil'),
 (60,188,'CR','CRI','Costa Rica'),
 (61,191,'HR','HRV','Croacia'),
 (62,192,'CU','CUB','Cuba'),
 (63,208,'DK','DNK','Dinamarca'),
 (64,212,'DM','DMA','Dominica'),
 (65,214,'DO','DOM','República Dominicana'),
 (66,218,'EC','ECU','Ecuador'),
 (67,818,'EG','EGY','Egipto'),
 (68,222,'SV','SLV','El Salvador'),
 (69,784,'AE','ARE','Emiratos Árabes Unidos'),
 (70,232,'ER','ERI','Eritrea'),
 (71,703,'SK','SVK','Eslovaquia'),
 (72,705,'SI','SVN','Eslovenia'),
 (73,724,'ES','ESP','España'),
 (74,581,'UM','UMI','Islas ultramarinas de Estados Unidos'),
 (75,840,'US','USA','Estados Unidos'),
 (76,233,'EE','EST','Estonia'),
 (77,231,'ET','ETH','Etiopía'),
 (78,234,'FO','FRO','Islas Feroe'),
 (79,608,'PH','PHL','Filipinas'),
 (80,246,'FI','FIN','Finlandia'),
 (81,242,'FJ','FJI','Fiyi'),
 (82,250,'FR','FRA','Francia'),
 (83,266,'GA','GAB','Gabón'),
 (84,270,'GM','GMB','Gambia'),
 (85,268,'GE','GEO','Georgia'),
 (86,239,'GS','SGS','Islas Georgias del Sur y Sandwich del Sur'),
 (87,288,'GH','GHA','Ghana'),
 (88,292,'GI','GIB','Gibraltar'),
 (89,308,'GD','GRD','Granada'),
 (90,300,'GR','GRC','Grecia'),
 (91,304,'GL','GRL','Groenlandia'),
 (92,312,'GP','GLP','Guadalupe'),
 (93,316,'GU','GUM','Guam'),
 (94,320,'GT','GTM','Guatemala'),
 (95,254,'GF','GUF','Guayana Francesa'),
 (96,324,'GN','GIN','Guinea'),
 (97,226,'GQ','GNQ','Guinea Ecuatorial'),
 (98,624,'GW','GNB','Guinea-Bissau'),
 (99,328,'GY','GUY','Guyana'),
 (100,332,'HT','HTI','Haití'),
 (101,334,'HM','HMD','Islas Heard y McDonald'),
 (102,340,'HN','HND','Honduras'),
 (103,344,'HK','HKG','Hong Kong'),
 (104,348,'HU','HUN','Hungría'),
 (105,356,'IN','IND','India'),
 (106,360,'ID','IDN','Indonesia'),
 (107,364,'IR','IRN','Irán'),
 (108,368,'IQ','IRQ','Iraq'),
 (109,372,'IE','IRL','Irlanda'),
 (110,352,'IS','ISL','Islandia'),
 (111,376,'IL','ISR','Israel'),
 (112,380,'IT','ITA','Italia'),
 (113,388,'JM','JAM','Jamaica'),
 (114,392,'JP','JPN','Japón'),
 (115,400,'JO','JOR','Jordania'),
 (116,398,'KZ','KAZ','Kazajstán'),
 (117,404,'KE','KEN','Kenia'),
 (118,417,'KG','KGZ','Kirguistán'),
 (119,296,'KI','KIR','Kiribati'),
 (120,414,'KW','KWT','Kuwait'),
 (121,418,'LA','LAO','Laos'),
 (122,426,'LS','LSO','Lesotho'),
 (123,428,'LV','LVA','Letonia'),
 (124,422,'LB','LBN','Líbano'),
 (125,430,'LR','LBR','Liberia'),
 (126,434,'LY','LBY','Libia'),
 (127,438,'LI','LIE','Liechtenstein'),
 (128,440,'LT','LTU','Lituania'),
 (129,442,'LU','LUX','Luxemburgo'),
 (130,446,'MO','MAC','Macao'),
 (131,807,'MK','MKD','ARY Macedonia'),
 (132,450,'MG','MDG','Madagascar'),
 (133,458,'MY','MYS','Malasia'),
 (134,454,'MW','MWI','Malawi'),
 (135,462,'MV','MDV','Maldivas'),
 (136,466,'ML','MLI','Malí'),
 (137,470,'MT','MLT','Malta'),
 (138,238,'FK','FLK','Islas Malvinas'),
 (139,580,'MP','MNP','Islas Marianas del Norte'),
 (140,504,'MA','MAR','Marruecos'),
 (141,584,'MH','MHL','Islas Marshall'),
 (142,474,'MQ','MTQ','Martinica'),
 (143,480,'MU','MUS','Mauricio'),
 (144,478,'MR','MRT','Mauritania'),
 (145,175,'YT','MYT','Mayotte'),
 (146,484,'MX','MEX','México'),
 (147,583,'FM','FSM','Micronesia'),
 (148,498,'MD','MDA','Moldavia'),
 (149,492,'MC','MCO','Mónaco'),
 (150,496,'MN','MNG','Mongolia'),
 (151,500,'MS','MSR','Montserrat'),
 (152,508,'MZ','MOZ','Mozambique'),
 (153,104,'MM','MMR','Myanmar'),
 (154,516,'NA','NAM','Namibia'),
 (155,520,'NR','NRU','Nauru'),
 (156,524,'NP','NPL','Nepal'),
 (157,558,'NI','NIC','Nicaragua'),
 (158,562,'NE','NER','Níger'),
 (159,566,'NG','NGA','Nigeria'),
 (160,570,'NU','NIU','Niue'),
 (161,574,'NF','NFK','Isla Norfolk'),
 (162,578,'NO','NOR','Noruega'),
 (163,540,'NC','NCL','Nueva Caledonia'),
 (164,554,'NZ','NZL','Nueva Zelanda'),
 (165,512,'OM','OMN','Omán'),
 (166,528,'NL','NLD','Países Bajos'),
 (167,586,'PK','PAK','Pakistán'),
 (168,585,'PW','PLW','Palau'),
 (169,275,'PS','PSE','Palestina'),
 (170,591,'PA','PAN','Panamá'),
 (171,598,'PG','PNG','Papúa Nueva Guinea'),
 (172,600,'PY','PRY','Paraguay'),
 (173,604,'PE','PER','Perú'),
 (174,612,'PN','PCN','Islas Pitcairn'),
 (175,258,'PF','PYF','Polinesia Francesa'),
 (176,616,'PL','POL','Polonia'),
 (177,620,'PT','PRT','Portugal'),
 (178,630,'PR','PRI','Puerto Rico'),
 (179,634,'QA','QAT','Qatar'),
 (180,826,'GB','GBR','Reino Unido'),
 (181,638,'RE','REU','Reunión'),
 (182,646,'RW','RWA','Ruanda'),
 (183,642,'RO','ROU','Rumania'),
 (184,643,'RU','RUS','Rusia'),
 (185,732,'EH','ESH','Sahara Occidental'),
 (186,90,'SB','SLB','Islas Salomón'),
 (187,882,'WS','WSM','Samoa'),
 (188,16,'AS','ASM','Samoa Americana'),
 (189,659,'KN','KNA','San Cristóbal y Nevis'),
 (190,674,'SM','SMR','San Marino'),
 (191,666,'PM','SPM','San Pedro y Miquelón'),
 (192,670,'VC','VCT','San Vicente y las Granadinas'),
 (193,654,'SH','SHN','Santa Helena'),
 (194,662,'LC','LCA','Santa Lucía'),
 (195,678,'ST','STP','Santo Tomé y Príncipe'),
 (196,686,'SN','SEN','Senegal'),
 (197,891,'CS','SCG','Serbia y Montenegro'),
 (198,690,'SC','SYC','Seychelles'),
 (199,694,'SL','SLE','Sierra Leona'),
 (200,702,'SG','SGP','Singapur'),
 (201,760,'SY','SYR','Siria'),
 (202,706,'SO','SOM','Somalia'),
 (203,144,'LK','LKA','Sri Lanka'),
 (204,748,'SZ','SWZ','Suazilandia'),
 (205,710,'ZA','ZAF','Sudáfrica'),
 (206,736,'SD','SDN','Sudán'),
 (207,752,'SE','SWE','Suecia'),
 (208,756,'CH','CHE','Suiza'),
 (209,740,'SR','SUR','Surinam'),
 (210,744,'SJ','SJM','Svalbard y Jan Mayen'),
 (211,764,'TH','THA','Tailandia'),
 (212,158,'TW','TWN','Taiwán'),
 (213,834,'TZ','TZA','Tanzania'),
 (214,762,'TJ','TJK','Tayikistán'),
 (215,86,'IO','IOT','Territorio Británico del Océano Índico'),
 (216,260,'TF','ATF','Territorios Australes Franceses'),
 (217,626,'TL','TLS','Timor Oriental'),
 (218,768,'TG','TGO','Togo'),
 (219,772,'TK','TKL','Tokelau'),
 (220,776,'TO','TON','Tonga'),
 (221,780,'TT','TTO','Trinidad y Tobago'),
 (222,788,'TN','TUN','Túnez'),
 (223,796,'TC','TCA','Islas Turcas y Caicos'),
 (224,795,'TM','TKM','Turkmenistán'),
 (225,792,'TR','TUR','Turquía'),
 (226,798,'TV','TUV','Tuvalu'),
 (227,804,'UA','UKR','Ucrania'),
 (228,800,'UG','UGA','Uganda'),
 (229,858,'UY','URY','Uruguay'),
 (230,860,'UZ','UZB','Uzbekistán'),
 (231,548,'VU','VUT','Vanuatu'),
 (232,862,'VE','VEN','Venezuela'),
 (233,704,'VN','VNM','Vietnam'),
 (234,92,'VG','VGB','Islas Vírgenes Británicas'),
 (235,850,'VI','VIR','Islas Vírgenes de los Estados Unidos'),
 (236,876,'WF','WLF','Wallis y Futuna'),
 (237,887,'YE','YEM','Yemen'),
 (238,262,'DJ','DJI','Yibuti'),
 (239,894,'ZM','ZMB','Zambia'),
 (240,716,'ZW','ZWE','Zimbabue');
/*!40000 ALTER TABLE `pai_pais` ENABLE KEYS */;


--
-- Definition of table `paises`
--

DROP TABLE IF EXISTS `paises`;
CREATE TABLE `paises` (
  `id` int(3) unsigned NOT NULL DEFAULT '0',
  `pais` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `paises`
--

/*!40000 ALTER TABLE `paises` DISABLE KEYS */;
INSERT INTO `paises` (`id`,`pais`) VALUES 
 (1,'Afganistán'),
 (2,'Albania'),
 (3,'Alemania'),
 (4,'American Samoa'),
 (5,'Andorra'),
 (6,'Angola'),
 (7,'Anguila'),
 (8,'Antigua and Barbuda'),
 (9,'Antillas Holandesas'),
 (10,'Antártida'),
 (11,'Arabia Saudita'),
 (12,'Argelia'),
 (13,'Argentina'),
 (14,'Armenia'),
 (15,'Aruba'),
 (17,'Australia'),
 (18,'Austria'),
 (19,'Azerbaijan'),
 (20,'Bahamas'),
 (21,'Bahrein'),
 (22,'Bangladesh'),
 (23,'Barbados'),
 (24,'Belice'),
 (25,'Benin'),
 (26,'Bermuda'),
 (27,'Bielorrusia'),
 (28,'Bolivia'),
 (29,'Bosnia y Herzegovina'),
 (30,'Botsuana'),
 (31,'Bouvet Island'),
 (32,'Brasil'),
 (33,'British Indian Ocean Territory'),
 (34,'Brunei Darussalam'),
 (35,'Bulgaria'),
 (36,'Burkina Faso'),
 (37,'Burundi'),
 (38,'Bután'),
 (39,'Bélgica'),
 (40,'Cabo Verda'),
 (41,'Camboya'),
 (42,'Camerún'),
 (44,'Canadá'),
 (45,'Chad'),
 (46,'Chile'),
 (47,'China'),
 (48,'Chipre'),
 (49,'Colombia'),
 (50,'Comores'),
 (51,'Congo'),
 (52,'Corea del Norte'),
 (53,'Corea del Sur'),
 (54,'Costa Rica'),
 (55,'Cote D Ivoire'),
 (56,'Croacia'),
 (57,'Cuba'),
 (58,'Dinamarca'),
 (59,'Djibouti'),
 (60,'Dominica'),
 (61,'East Timor'),
 (62,'Ecuador'),
 (63,'Egipto'),
 (64,'El Salvador'),
 (65,'El Vaticano'),
 (66,'Emiratos Arabes Unidos'),
 (67,'Eritrea'),
 (68,'Eslovaquia'),
 (69,'Eslovenia'),
 (70,'España'),
 (71,'Estados Unidos'),
 (72,'Estonia'),
 (73,'Etiopía'),
 (74,'Fiji'),
 (75,'Filipinas'),
 (76,'Finlandia'),
 (77,'Francia'),
 (78,'French Guiana'),
 (79,'French Polynesia'),
 (80,'French Southern Territories'),
 (81,'Gabón'),
 (82,'Gambia'),
 (83,'Georgia'),
 (84,'Ghana'),
 (85,'Gibraltar'),
 (86,'Granada'),
 (87,'Grecia'),
 (88,'Groenlandia'),
 (89,'Guadalupe'),
 (90,'Guam'),
 (91,'Guatemala'),
 (92,'Guinea'),
 (93,'Guinea Ecuatorial'),
 (94,'Guinea-Bissau'),
 (95,'Guyana'),
 (96,'Haití'),
 (97,'Heard Island and McDonald Isla'),
 (98,'Holanda'),
 (99,'Honduras'),
 (100,'Hong Kong'),
 (101,'Hungría'),
 (102,'India'),
 (103,'Indonesia'),
 (104,'Iraq'),
 (105,'Irlanda'),
 (106,'Isalas Cocos'),
 (107,'Isla Christmas'),
 (108,'Islandia'),
 (109,'Islas Caimán'),
 (110,'Islas Cook'),
 (111,'Islas Feroe'),
 (112,'Islas Malvinas'),
 (113,'Islas Marshall'),
 (114,'Islas Mauricio'),
 (115,'Islas Salomón'),
 (116,'Islas Sandwhich'),
 (117,'Islas Turks y Caicos'),
 (118,'Islas Wallis y Futuna'),
 (119,'Israel'),
 (120,'Italia'),
 (121,'Jamaica'),
 (122,'Japón'),
 (123,'Jordania'),
 (124,'Kazakhstán'),
 (125,'Kenia'),
 (126,'Kiribati'),
 (127,'Kuwait'),
 (128,'Kyrgyzstán'),
 (129,'Laos'),
 (130,'Latvia'),
 (131,'Lesoto'),
 (132,'Liberia'),
 (133,'Libia'),
 (134,'Liechtenstein'),
 (135,'Lituania'),
 (136,'Luxemburgo'),
 (137,'Líbano'),
 (138,'Macao'),
 (139,'Macedonia'),
 (140,'Madagascar'),
 (141,'Malasia'),
 (142,'Malaui'),
 (143,'Maldivas'),
 (144,'Malta'),
 (145,'Malí'),
 (146,'Marruecos'),
 (147,'Martinique'),
 (148,'Mauritania'),
 (149,'Mayotte'),
 (150,'Micronesia'),
 (151,'Moldavia'),
 (152,'Mongolia'),
 (153,'Montserrat'),
 (154,'Mozambique'),
 (155,'Myanmar'),
 (156,'México'),
 (157,'Mónaco'),
 (158,'Namibia'),
 (159,'Nauru'),
 (160,'Nepal'),
 (161,'Nicaragua'),
 (162,'Nigeria'),
 (163,'Niue'),
 (164,'Norfolk Island'),
 (165,'Northern Mariana Islands'),
 (166,'Noruega'),
 (167,'Nueva Caledonia'),
 (168,'Nueva Zelanda'),
 (169,'Níger'),
 (170,'Omán'),
 (171,'Pakistán'),
 (172,'Palau'),
 (173,'Palestinian Territory'),
 (174,'Panamá'),
 (175,'Papúa Nueva Guinea'),
 (176,'Paraguay'),
 (177,'Perú'),
 (178,'Pitcairn'),
 (179,'Polonia'),
 (180,'Portugal'),
 (181,'Puerto Rico'),
 (182,'Qatar'),
 (183,'Reino Unido'),
 (184,'República Centroafricana'),
 (185,'República Checa'),
 (186,'República Democrática del Cong'),
 (187,'República Dominicana'),
 (188,'República Islámica de Irán'),
 (189,'Ruanda'),
 (190,'Rumania'),
 (191,'Rusian'),
 (192,'Saint Kitts and Nevis'),
 (193,'Saint Pierre y Miquelon'),
 (194,'Samoa'),
 (195,'San Marino'),
 (196,'San Vicente y Las Granadinas'),
 (197,'Santa Elena'),
 (198,'Santa Lucía'),
 (199,'Sao Tome and Principe'),
 (200,'Senegal'),
 (201,'Serbia y Montenegro'),
 (202,'Seychelles'),
 (203,'Sierra Leona'),
 (204,'Singapur'),
 (205,'Siria'),
 (206,'Somalía'),
 (207,'Sri Lanka'),
 (208,'Suazilandia'),
 (209,'Sudáfrica'),
 (210,'Sudán'),
 (211,'Suecia'),
 (212,'Suiza'),
 (213,'Surinam'),
 (214,'Svalbard and Jan Mayen'),
 (215,'Tailandia'),
 (216,'Taiwan'),
 (217,'Tajikistán'),
 (218,'Tanzania'),
 (219,'Togo'),
 (220,'Tonga'),
 (221,'Toquelau'),
 (222,'Trinidad y Tobago'),
 (223,'Turkmenistán'),
 (224,'Turquía'),
 (225,'Tuvalu'),
 (226,'Túnez'),
 (227,'Ucrania'),
 (228,'Uganda'),
 (229,'United States Minor Outlying I'),
 (230,'Uruguay'),
 (231,'Uzbekistan'),
 (232,'Vanuatu'),
 (233,'Venezuela'),
 (234,'Vietnam'),
 (235,'Virgin Islands British'),
 (236,'Virgin Islands U.S.'),
 (237,'Western Sahara'),
 (238,'Yemen'),
 (239,'Zaire'),
 (240,'Zambia'),
 (241,'Zimbabue');
/*!40000 ALTER TABLE `paises` ENABLE KEYS */;


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
  `licr_str_tabla` varchar(220) NOT NULL,
  `licr_num_id_estado` int(11) NOT NULL,
  `licr_num_id_lista_dependencia` bigint(20) NOT NULL,
  PRIMARY KEY (`licr_num_id`),
  UNIQUE KEY `codigo` (`licr_str_codigo`,`licr_str_tabla`),
  KEY `fk_TL_ADM_LICR_ListasCriterio_TL_ADM_LICR_ListasCriterio1` (`licr_num_id_lista_dependencia`),
  CONSTRAINT `fk_TL_ADM_LICR_ListasCriterio_TL_ADM_LICR_ListasCriterio1` FOREIGN KEY (`licr_num_id_lista_dependencia`) REFERENCES `tl_adm_licr_listascriterio` (`licr_num_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tl_adm_licr_listascriterio`
--

/*!40000 ALTER TABLE `tl_adm_licr_listascriterio` DISABLE KEYS */;
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
  `regi_codigo` varchar(6) NOT NULL,
  PRIMARY KEY (`regi_num_id`),
  UNIQUE KEY `unique_codigo` (`regi_codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tl_adm_regi_regionclimatica`
--

/*!40000 ALTER TABLE `tl_adm_regi_regionclimatica` DISABLE KEYS */;
/*!40000 ALTER TABLE `tl_adm_regi_regionclimatica` ENABLE KEYS */;


--
-- Definition of table `tl_adm_tran_transaccion`
--

DROP TABLE IF EXISTS `tl_adm_tran_transaccion`;
CREATE TABLE `tl_adm_tran_transaccion` (
  `tran_num_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tran_num_id_estado` bigint(20) NOT NULL,
  `tran_str_codigo` varchar(6) NOT NULL,
  `tran_str_descirpcion` varchar(220) DEFAULT NULL,
  PRIMARY KEY (`tran_num_id`),
  UNIQUE KEY `unique_codigo` (`tran_str_codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tl_adm_tran_transaccion`
--

/*!40000 ALTER TABLE `tl_adm_tran_transaccion` DISABLE KEYS */;
/*!40000 ALTER TABLE `tl_adm_tran_transaccion` ENABLE KEYS */;


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
  `cult_num_id_esta` bigint(20) NOT NULL,
  PRIMARY KEY (`cult_num_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tl_gem_cult_cultivo`
--

/*!40000 ALTER TABLE `tl_gem_cult_cultivo` DISABLE KEYS */;
/*!40000 ALTER TABLE `tl_gem_cult_cultivo` ENABLE KEYS */;


--
-- Definition of table `tl_gem_pasc_propiedadesasociadas`
--

DROP TABLE IF EXISTS `tl_gem_pasc_propiedadesasociadas`;
CREATE TABLE `tl_gem_pasc_propiedadesasociadas` (
  `pasc_num_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pasc_num_orden` int(11) NOT NULL,
  `pasc_num_id_cultivo` bigint(20) NOT NULL,
  `pasc_num_id_propiedad` bigint(20) NOT NULL,
  `pasc_bol_en_uso` tinyint(1) NOT NULL,
  PRIMARY KEY (`pasc_num_id`),
  UNIQUE KEY `unica` (`pasc_num_id_cultivo`,`pasc_num_id_propiedad`,`pasc_num_orden`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tl_gem_pasc_propiedadesasociadas`
--

/*!40000 ALTER TABLE `tl_gem_pasc_propiedadesasociadas` DISABLE KEYS */;
/*!40000 ALTER TABLE `tl_gem_pasc_propiedadesasociadas` ENABLE KEYS */;


--
-- Definition of table `tl_gem_prcu_propiedadescultivo`
--

DROP TABLE IF EXISTS `tl_gem_prcu_propiedadescultivo`;
CREATE TABLE `tl_gem_prcu_propiedadescultivo` (
  `prcu_num_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `prcu_str_codigo` varchar(10) NOT NULL,
  `prcu_str_descripcion` varchar(220) NOT NULL,
  `prcu_str_unidad_medida` varchar(220) DEFAULT NULL,
  PRIMARY KEY (`prcu_num_id`),
  UNIQUE KEY `unique_codigo` (`prcu_str_codigo`)
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
  `daus_dte_fecha_registro` datetime NOT NULL,
  `daus_num_id_perfil` bigint(20) NOT NULL,
  PRIMARY KEY (`daus_num_id`),
  KEY `FK_daus_num_id_pais` (`daus_num_id_pais`),
  KEY `FK_daus_num_id_departamentos` (`daus_num_id_departamento`),
  KEY `FK_daus_num_id_ciudad` (`daus_num_id_ciudad`),
  KEY `FK_daus_num_id_perfil` (`daus_num_id_perfil`),
  CONSTRAINT `FK_daus_num_id_perfil` FOREIGN KEY (`daus_num_id_perfil`) REFERENCES `tl_seg_perf_perfil` (`perf_num_id`),
  CONSTRAINT `FK_daus_num_id_ciudad` FOREIGN KEY (`daus_num_id_ciudad`) REFERENCES `tl_adm_ciud_ciudad` (`ciud_num_id`),
  CONSTRAINT `FK_daus_num_id_departamentos` FOREIGN KEY (`daus_num_id_departamento`) REFERENCES `tl_adm_deto_departamento` (`deto_num_id`),
  CONSTRAINT `FK_daus_num_id_pais` FOREIGN KEY (`daus_num_id_pais`) REFERENCES `tl_adm_pais_pais` (`pais_num_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tl_seg_daus_datosusuario`
--

/*!40000 ALTER TABLE `tl_seg_daus_datosusuario` DISABLE KEYS */;
INSERT INTO `tl_seg_daus_datosusuario` (`daus_num_id`,`daus_num_id_estado`,`daus_str_nombre`,`daus_dte_timestamp`,`daus_str_apellido`,`daus_str_email`,`daus_str_telefono`,`daus_str_celular`,`daus_str_direccion`,`daus_num_id_pais`,`daus_num_id_departamento`,`daus_num_id_ciudad`,`daus_dte_fecha_registro`,`daus_num_id_perfil`) VALUES 
 (1,'Activo','Juan Andres','2010-01-30 12:46:09','Pio','juan400@gmail.com','adsf','asdf','asdf',21,474,NULL,'2010-01-30 12:46:09',3);
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
  `trpe_num_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `trpe_num_id_perfile` bigint(20) NOT NULL,
  `trpe_num_id_transaccion` bigint(20) NOT NULL,
  PRIMARY KEY (`trpe_num_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tl_seg_trpe_transaccionperfil`
--

/*!40000 ALTER TABLE `tl_seg_trpe_transaccionperfil` DISABLE KEYS */;
/*!40000 ALTER TABLE `tl_seg_trpe_transaccionperfil` ENABLE KEYS */;


--
-- Definition of table `tl_seg_usua_usuario`
--

DROP TABLE IF EXISTS `tl_seg_usua_usuario`;
CREATE TABLE `tl_seg_usua_usuario` (
  `usua_num_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `usua_str_login` varchar(20) NOT NULL,
  `usua_str_password` varchar(13) NOT NULL,
  `usua_bol_activado` tinyint(1) NOT NULL DEFAULT '0',
  `usua_dte_ultimo_acceso` datetime NOT NULL DEFAULT '1970-01-01 00:00:01',
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
 (1,'juan400','81373981',0,'2010-01-30 12:46:09','Registrado',1,'Ingrese su frase secreta','81373981');
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
