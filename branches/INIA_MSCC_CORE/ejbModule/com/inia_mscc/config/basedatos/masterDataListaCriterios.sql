delete from `inia_mscc_db`.`tl_adm_vase_valorseleccion`;
ALTER TABLE `inia_mscc_db`.`tl_adm_vase_valorseleccion` AUTO_INCREMENT = 1;

delete from `inia_mscc_db`.`tl_adm_licr_listascriterio`;
ALTER TABLE `inia_mscc_db`.`tl_adm_licr_listascriterio` AUTO_INCREMENT = 1;
INSERT INTO `tl_adm_licr_listascriterio` (`licr_str_codigo`, `licr_str_descripcion`, `licr_num_id_estado`)
VALUES
('FER', 'Fertilizantes', 'Activo'),
('CON', 'Indice de Coneat', 'Activo'),
('PAR', 'Parametros de Salida', 'Activo');