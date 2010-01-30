delete from tl_seg_perf_perfil;
ALTER TABLE `inia_mscc_db`.`tl_seg_perf_perfil` AUTO_INCREMENT = 1;
INSERT INTO `tl_seg_perf_perfil` (`perf_str_estado`, `perf_str_nombre`, `perf_str_descripcion`, `perf_bol_fijo`)
VALUES
('Activo', 'Administrador', 'Administrador del sistema',1),
('Activo', 'Investigador', 'Investigadores de MSCC',1),
('Activo', 'Publico', 'Público en general',1);