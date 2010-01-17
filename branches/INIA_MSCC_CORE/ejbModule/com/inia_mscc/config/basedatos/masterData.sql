use inia_mscc_db;

delete from tl_seg_usua_usuario;

INSERT INTO `tl_seg_usua_usuario` (
`usua_str_login`
,`usua_str_password`
,`usua_bol_activado`
,`usua_dte_ultimo_acceso`
,`usua_str_estado_usuario`) VALUES
('admin','admin',1,'1970-01-01 00:00:01','Inactivo'),
('juan','juan',1,'1970-01-01 00:00:01','Inactivo');


SELECT * FROM tl_seg_usua_usuario t;