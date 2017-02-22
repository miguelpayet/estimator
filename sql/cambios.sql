CREATE TABLE `visita` (
  `idvisita` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` datetime DEFAULT NULL,
  `request` varchar(1000) DEFAULT NULL,
  `usuario` varchar(50) DEFAULT NULL,
  `duracion` bigint(20) DEFAULT NULL,
  `protocol` varchar(10) DEFAULT NULL,
  `useragent` varchar(200) DEFAULT NULL,
  `platform` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`idvisita`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8;
