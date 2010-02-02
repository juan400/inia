delete from `inia_mscc_db`.`tl_seg_trpe_transaccionperfil`;
delete from `inia_mscc_db`.`tl_adm_tran_transaccion`;

ALTER TABLE `inia_mscc_db`.`tl_adm_tran_transaccion` AUTO_INCREMENT = 1;

INSERT INTO `inia_mscc_db`.`tl_adm_tran_transaccion`
(`tran_num_id_estado`,
`tran_str_codigo`,
`tran_str_descirpcion`,
`tran_bol_definida`,
`tran_str_uri`,
`tran_str_codigo_base`,
`tran_str_descripcion_base`,
`tran_dte_timestamp`)

VALUES

(default,null,null,default,'/Servicios/SEG/SEG001.jsp','SEG001','Login',NOW()),
(default,null,null,default,'/Servicios/SEG/SEG002.jsp','SEG002','Registro Usuario',NOW()),
(default,null,null,default,'/Servicios/SEG/SEG003.jsp','SEG003','Activar Usuario',NOW()),
(default,null,null,default,'/Servicios/SEG/SEG004.jsp','SEG004','Modificar Usuario',NOW()),
(default,null,null,default,'/Servicios/SEG/SEG005.jsp','SEG005','Baja Usuario',NOW()),
(default,null,null,default,'/Servicios/SEG/SEG006.jsp','SEG006','Recuperar Contraseña',NOW()),
(default,null,null,default,'/Servicios/SEG/SEG007.jsp','SEG007','Cambiar Contraseña',NOW()),
(default,null,null,default,'/Servicios/SEG/SEG008.jsp','SEG008','Ver Perfiles',NOW()),
(default,null,null,default,'/Servicios/SEG/SEG009.jsp','SEG009','Crear Perfil Usuario',NOW()),
(default,null,null,default,'/Servicios/SEG/SEG010.jsp','SEG010','Modificar Perfil Usuario',NOW()),
(default,null,null,default,'/Servicios/SEG/SEG011.jsp','SEG011','Solicitud Privilegios',NOW()),
(default,null,null,default,'/Servicios/SEG/SEG011.jsp','SEG012','Autorizar Solicitud Privilegios',NOW()),
(default,null,null,default,'/Servicios/SEG/SEG011.jsp','SEG013','Denegar Solicitud Privilegios',NOW()),
(default,null,null,default,'/Servicios/ADM/ADM001.jsp','ADM001','Identificar Transacción',NOW()),
(default,null,null,default,'/Servicios/ADM/ADM002.jsp','ADM002','Modificar Transacción',NOW()),
(default,null,null,default,'/Servicios/ADM/ADM003.jsp','ADM003','Listas de Criterio',NOW()),
(default,null,null,default,'/Servicios/ADM/ADM004.jsp','ADM004','Relación País Departamentos Ciudades',NOW()),
(default,null,null,default,'/Servicios/ADM/ADM005.jsp','ADM005','Gestión de Regiones',NOW()),
(default,null,null,default,'/Servicios/GEM/GEM001.jsp','GEM001','Crear Cultivo',NOW()),
(default,null,null,default,'/Servicios/GEM/GEM002.jsp','GEM002','Modificar Cultivo',NOW()),
(default,null,null,default,'/Servicios/GEM/GEM003.jsp','GEM003','Crear Propiedades Cultivo',NOW()),
(default,null,null,default,'/Servicios/GEM/GEM004.jsp','GEM004','Modificar Propiedades de un Cultivo',NOW()),
(default,null,null,default,'/Servicios/GEM/GEM005.jsp','GEM005','Ingresar Escenario',NOW()),
(default,null,null,default,'/Servicios/GEM/GEM006.jsp','GEM006','Modificar Escenario',NOW()),
(default,null,null,default,'/Servicios/GEM/GEM007.jsp','GEM007','Ingresar Python de MSCC',NOW()),
(default,null,null,default,'/Servicios/GEM/GEM008.jsp','GEM008','Modificar Python de MSCC',NOW()),
(default,null,null,default,'/Servicios/GEM/GEM009.jsp','GEM009','Anular Python de MSCC',NOW()),
(default,null,null,default,'/Servicios/EJE/EJE001.jsp','EJE001','Ejecución de MSCC',NOW()),
(default,null,null,default,'/Servicios/EJE/EJE002.jsp','EJE002','Ejecución de MSCC',NOW());