delete FROM tl_seg_usua_usuario ;

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