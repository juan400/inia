delete FROM `inia_mscc_db`.`tl_seg_usua_usuario`;

delete from `inia_mscc_db`.`tl_seg_daus_datosusuario`;


ALTER TABLE `inia_mscc_db`.`tl_seg_daus_datosusuario` AUTO_INCREMENT = 1;

INSERT INTO `inia_mscc_db`.`tl_seg_daus_datosusuario`
(`daus_num_id_estado`,
`daus_str_nombre`,
`daus_dte_timestamp`,
`daus_str_apellido`,
`daus_str_email`,
`daus_str_telefono`,
`daus_str_celular`,
`daus_str_direccion`,
`daus_num_id_pais`,
`daus_num_id_departamento`,
`daus_num_id_ciudad`,
`daus_dte_fecha_registro`,
`daus_num_id_perfil`)

VALUES

('Activo','admin','2010-01-01 00:00:01','admin','admin@inia.com',null,null,'-',21,474,2,'2010-01-01 00:00:01',1);


ALTER TABLE `inia_mscc_db`.`tl_seg_usua_usuario` AUTO_INCREMENT = 1;

INSERT INTO `inia_mscc_db`.`tl_seg_usua_usuario`
(`usua_str_login`,
`usua_str_password`,
`usua_bol_activado`, 
`usua_dte_ultimo_acceso`,
`usua_str_estado_usuario`,
`usua_num_id_dato_usuario`,
`usua_str_frase`,
`usua_str_codigo_activacion`)

VALUES

('admin','+GW1NiOxIf007lQmx5Llwzr4wic=',1,default,'Activo',1,'ProyectoFinal',54032683);