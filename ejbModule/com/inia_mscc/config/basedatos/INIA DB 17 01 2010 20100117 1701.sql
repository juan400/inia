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
  `daus_str_nombre` varchar(220) NOT NULL,
  `daus_str_apellido` varchar(220) NOT NULL,
  `daus_dte_fecha_registro` datetime NOT NULL,
  `daus_num_id_pais` bigint(20) DEFAULT NULL,
  `daus_num_id_depto` bigint(20) DEFAULT NULL,
  `daus_num_id_ciudad` bigint(20) DEFAULT NULL,
  `daus_str_direccion` varchar(250) DEFAULT NULL,
  `daus_num_codigo_postal` int(11) DEFAULT NULL,
  `daus_str_telefono` varchar(45) DEFAULT NULL,
  `daus_str_email` varchar(220) DEFAULT NULL,
  `daus_num_id_perfil` bigint(20) NOT NULL,
  `daus_num_id_usuario` bigint(20) NOT NULL,
  `daus_num_id_estado` bigint(20) NOT NULL,
  PRIMARY KEY (`daus_num_id`),
  KEY `FK_daus_num_id_perfil` (`daus_num_id_perfil`),
  KEY `FK_daus_num_id_usuario` (`daus_num_id_usuario`),
  KEY `FK_daus_num_id_estado` (`daus_num_id_estado`),
  CONSTRAINT `FK_daus_num_id_estado` FOREIGN KEY (`daus_num_id_estado`) REFERENCES `tl_adm_esta_estado` (`esta_num_id`),
  CONSTRAINT `FK_daus_num_id_perfil` FOREIGN KEY (`daus_num_id_perfil`) REFERENCES `tl_seg_perf_perfil` (`perf_num_id`),
  CONSTRAINT `FK_daus_num_id_usuario` FOREIGN KEY (`daus_num_id_usuario`) REFERENCES `tl_seg_usua_usuario` (`usua_num_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tl_seg_daus_datosusuario`
--

/*!40000 ALTER TABLE `tl_seg_daus_datosusuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `tl_seg_daus_datosusuario` ENABLE KEYS */;


--
-- Definition of table `tl_seg_perf_perfil`
--

DROP TABLE IF EXISTS `tl_seg_perf_perfil`;
CREATE TABLE `tl_seg_perf_perfil` (
  `perf_num_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `perf_num_id_estado` bigint(20) NOT NULL,
  PRIMARY KEY (`perf_num_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tl_seg_perf_perfil`
--

/*!40000 ALTER TABLE `tl_seg_perf_perfil` DISABLE KEYS */;
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
  PRIMARY KEY (`usua_num_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tl_seg_usua_usuario`
--

/*!40000 ALTER TABLE `tl_seg_usua_usuario` DISABLE KEYS */;
INSERT INTO `tl_seg_usua_usuario` (`usua_num_id`,`usua_str_login`,`usua_str_password`,`usua_bol_activado`,`usua_dte_ultimo_acceso`,`usua_str_estado_usuario`) VALUES 
 (3,'admin','admin',1,'1970-01-01 00:00:01','Inactivo'),
 (4,'juan','juan',1,'1970-01-01 00:00:01','Inactivo');
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
