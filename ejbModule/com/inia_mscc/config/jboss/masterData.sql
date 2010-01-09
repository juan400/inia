DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
`id` int(10) unsigned NOT NULL,
`name` varchar(64) collate utf8_spanish_ci NOT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
CREATE TABLE `user` (
`id` int(10) unsigned NOT NULL auto_increment,
`login` varchar(128) collate utf8_spanish_ci NOT NULL,
`password` varchar(128) collate utf8_spanish_ci NOT NULL,
`roleId` int(10) unsigned NOT NULL,
PRIMARY KEY (`id`),
KEY `ndx_user_roleId` (`roleId`),
CONSTRAINT `fk_user_roleId` FOREIGN KEY (`roleId`) REFERENCES `role`
(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;


INSERT INTO `role` (`id`,`name`) VALUES
(1,'ADMINISTRADOR'),
(2,'USER');
INSERT INTO `user` (`id`,`login`,`password`,`roleId`) VALUES
(1,'admin','erwin',1),
(2,'user','erwin',2);