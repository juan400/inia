delete from `inia_mscc_db`.`tl_seg_trpe_transaccionperfil`;
delete from `inia_mscc_db`.`tl_adm_tran_transaccion`;

ALTER TABLE `inia_mscc_db`.`tl_adm_tran_transaccion` AUTO_INCREMENT = 1;

INSERT INTO `inia_mscc_db`.`tl_adm_tran_transaccion`
(`tran_num_id_estado`,
`tran_str_codigo`,
`tran_str_descripcion`,
`tran_bol_definida`,
`tran_str_uri`,
`tran_str_codigo_base`,
`tran_str_descripcion_base`,
`tran_dte_timestamp`)

VALUES

(default,'001','Login',default,'/Servicios/SEG/SEG001.jsp','SEG001','Login',NOW()),
(default,'002','Registro de usuarios',default,'/Servicios/SEG/SEG002.jsp','SEG002','Registro Usuario',NOW()),
(default,'003','Activar Usuario',default,'/Servicios/SEG/SEG003.jsp','SEG003','Activar Usuario',NOW()),
(default,'004','Modificar Usuario',default,'/Servicios/SEG/SEG004.jsp','SEG004','Modificar Usuario',NOW()),
(default,'005','Baja Usuario',default,'/Servicios/SEG/SEG005.jsp','SEG005','Baja Usuario',NOW()),
(default,'006','Recuperar Contraseña',default,'/Servicios/SEG/SEG006.jsp','SEG006','Recuperar Contraseña',NOW()),
(default,'007','Cambiar Contraseña',default,'/Servicios/SEG/SEG007.jsp','SEG007','Cambiar Contraseña',NOW()),
(default,'008','Ver Perfiles',default,'/Servicios/SEG/SEG008.jsp','SEG008','Ver Perfiles',NOW()),
(default,'009','Crear Perfil',default,'/Servicios/SEG/SEG009.jsp','SEG009','Crear Perfil',NOW()),
(default,'010','Modificar Perfil',default,'/Servicios/SEG/SEG010.jsp','SEG010','Modificar Perfil',NOW()),
(default,'011','Solicitud Privilegios',default,'/Servicios/SEG/SEG011.jsp','SEG011','Solicitud Privilegios',NOW()),
(default,'012','Autorizar Solicitud Privilegios',default,'/Servicios/SEG/SEG011.jsp','SEG012','Autorizar Solicitud Privilegios',NOW()),
(default,'013','Denegar Solicitud Privilegios',default,'/Servicios/SEG/SEG011.jsp','SEG013','Denegar Solicitud Privilegios',NOW()),
(default,'014','Ver Transacciónes',default,'/Servicios/ADM/ADM001.jsp','ADM001','Ver Transacciónes',NOW()),
(default,'015','Modificar Transacción',default,'/Servicios/ADM/ADM002.jsp','ADM002','Modificar Transacción',NOW()),
(default,'016','Lista de Criterios',default,'/Servicios/ADM/ADM003.jsp','ADM003','Listas de Criterio',NOW()),
(default,'017','Relación País, Dpto, Cuidad',default,'/Servicios/ADM/ADM004.jsp','ADM004','Relación País Departamentos Ciudades',NOW()),
(default,'018','Ver Regiones',default,'/Servicios/ADM/ADM005.jsp','ADM005','Ver Regiones',NOW()),
(default,'019','Crear Regiones',default,'/Servicios/ADM/ADM005.jsp','ADM006','Crear Regiones',NOW()),
(default,'020','Modificar Región',default,'/Servicios/ADM/ADM005.jsp','ADM007','Modificar Regiones',NOW()),
(default,'021','Ver Usuarios',default,'/Servicios/ADM/ADM005.jsp','ADM008','Ver Usuarios',NOW()),
(default,'022','Modificar Perfil Usuario',default,'/Servicios/ADM/ADM005.jsp','ADM009','Modificar Perfil Usuario',NOW()),
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