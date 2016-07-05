-- MySQL dump 10.16  Distrib 10.1.12-MariaDB, for Win32 (AMD64)
--
-- Host: localhost    Database: mydb
-- ------------------------------------------------------
-- Server version	10.1.12-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `actor`
--

DROP TABLE IF EXISTS `actor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor` (
  `idactor` int(11) NOT NULL AUTO_INCREMENT,
  `idestimacion` int(6) NOT NULL,
  `complejidad` int(1) DEFAULT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  `idplataforma` int(11) NOT NULL,
  PRIMARY KEY (`idactor`),
  KEY `fk_actor_estimacion1_idx` (`idestimacion`),
  KEY `fk_actor_plataforma1_idx` (`idplataforma`),
  CONSTRAINT `fk_actor_estimacion1` FOREIGN KEY (`idestimacion`) REFERENCES `estimacion` (`idestimacion`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_actor_plataforma1` FOREIGN KEY (`idplataforma`) REFERENCES `plataforma` (`idplataforma`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor`
--

LOCK TABLES `actor` WRITE;
/*!40000 ALTER TABLE `actor` DISABLE KEYS */;
INSERT INTO `actor` VALUES (61,1,1,'Actor',1),(62,11192,3,'Sistema Guidewire',2),(63,11192,1,'Cajero BCP',5),(64,11192,1,'Analista BCP',5),(65,11192,2,'Sistema Teletransfer',5),(66,11192,1,'Analista de Cobranzas PPS',5),(67,11192,3,'Sistema Acsel/X',1),(68,11192,2,'Sistema de Cobranzas BCP',5);
/*!40000 ALTER TABLE `actor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `caso_de_uso`
--

DROP TABLE IF EXISTS `caso_de_uso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `caso_de_uso` (
  `idcaso` int(11) NOT NULL AUTO_INCREMENT,
  `idestimacion` int(6) NOT NULL,
  `complejidad` int(1) NOT NULL,
  `descripcion` varchar(45) NOT NULL,
  `idplataforma` int(11) NOT NULL,
  PRIMARY KEY (`idcaso`),
  KEY `fk_caso_de_uso_estimacion_idx` (`idestimacion`),
  KEY `fk_caso_de_uso_plataforma1_idx` (`idplataforma`),
  CONSTRAINT `fk_caso_de_uso_estimacion` FOREIGN KEY (`idestimacion`) REFERENCES `estimacion` (`idestimacion`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_caso_de_uso_plataforma1` FOREIGN KEY (`idplataforma`) REFERENCES `plataforma` (`idplataforma`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `caso_de_uso`
--

LOCK TABLES `caso_de_uso` WRITE;
/*!40000 ALTER TABLE `caso_de_uso` DISABLE KEYS */;
INSERT INTO `caso_de_uso` VALUES (65,1,2,'Caso 1',2),(66,11192,2,'Búsqueda de Documentos',5),(67,11192,1,'Relación de Ingresos',5),(68,11192,2,'Criterio de Pago',5),(69,11192,1,'Filtros de Búsqueda del Cliente',5),(70,11192,3,'Extorno de Pagos',5),(71,11192,2,'Validación de Operaciones',5),(72,11192,2,'Envio de Correos de Alerta - Configurable',5);
/*!40000 ALTER TABLE `caso_de_uso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `costo_adicional`
--

DROP TABLE IF EXISTS `costo_adicional`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `costo_adicional` (
  `idestimacion` int(6) NOT NULL,
  `numcosto` int(11) NOT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  `costo` float DEFAULT NULL,
  PRIMARY KEY (`numcosto`,`idestimacion`),
  KEY `fk_costo_adicional_estimacion1_idx` (`idestimacion`),
  CONSTRAINT `fk_costo_adicional_estimacion1` FOREIGN KEY (`idestimacion`) REFERENCES `estimacion` (`idestimacion`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `costo_adicional`
--

LOCK TABLES `costo_adicional` WRITE;
/*!40000 ALTER TABLE `costo_adicional` DISABLE KEYS */;
/*!40000 ALTER TABLE `costo_adicional` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cronograma`
--

DROP TABLE IF EXISTS `cronograma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cronograma` (
  `idestimacion` int(6) NOT NULL,
  `idtarea` int(11) NOT NULL,
  `incluir` tinyint(1) DEFAULT NULL,
  `porcentaje` float DEFAULT NULL,
  `recursos` int(11) DEFAULT NULL,
  `dias` float DEFAULT NULL,
  `horas` float DEFAULT NULL,
  PRIMARY KEY (`idestimacion`,`idtarea`),
  KEY `fk_cronograma_estimacion1_idx` (`idestimacion`),
  KEY `fk_cronograma_tarea1_idx` (`idtarea`),
  CONSTRAINT `fk_cronograma_estimacion1` FOREIGN KEY (`idestimacion`) REFERENCES `estimacion` (`idestimacion`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cronograma_tarea1` FOREIGN KEY (`idtarea`) REFERENCES `tarea` (`idtarea`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cronograma`
--

LOCK TABLES `cronograma` WRITE;
/*!40000 ALTER TABLE `cronograma` DISABLE KEYS */;
INSERT INTO `cronograma` VALUES (1,1,1,NULL,1,5,40),(1,2,1,0.3,1,7.21,17.31),(1,3,1,0.2,1,7.21,57.71),(1,4,1,0.4,1,14.43,115.42),(1,5,1,0.4,1,14.43,115.42),(1,6,0,0,0,0,0),(1,7,1,0.15,1,41.07,49.28),(2,1,1,NULL,1,NULL,NULL),(2,2,1,0.3,1,NULL,NULL),(2,3,1,0.2,1,NULL,NULL),(2,4,1,0.4,1,NULL,NULL),(2,5,1,0.3,1,NULL,NULL),(2,6,1,0.1,1,NULL,NULL),(2,7,1,0.15,1,NULL,NULL),(11192,1,1,NULL,1,15,120),(11192,2,1,0.3,1,45.05,108.12),(11192,3,1,0.2,1,45.05,360.38),(11192,4,1,0.4,2,45.05,720.77),(11192,5,1,0.3,1,67.57,540.58),(11192,6,1,0.1,1,22.52,180.19),(11192,7,1,0.3,1,195.19,468.46);
/*!40000 ALTER TABLE `cronograma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estimacion`
--

DROP TABLE IF EXISTS `estimacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estimacion` (
  `idestimacion` int(6) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `eds` varchar(50) NOT NULL,
  `fechacalculo` datetime DEFAULT NULL,
  `puntos` float DEFAULT NULL,
  `esfuerzo` float DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`idestimacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estimacion`
--

LOCK TABLES `estimacion` WRITE;
/*!40000 ALTER TABLE `estimacion` DISABLE KEYS */;
INSERT INTO `estimacion` VALUES (1,'Estimación','Eds','2016-06-13 11:53:06',12.35,345.87,1),(2,'123','123',NULL,0,0,0),(11192,'Pagos en línea a través del BCP','Quiñónez, Patricia','2016-06-17 11:14:17',72.5,2030.04,8);
/*!40000 ALTER TABLE `estimacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `factor`
--

DROP TABLE IF EXISTS `factor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `factor` (
  `idfactor` int(11) NOT NULL AUTO_INCREMENT,
  `tipofactor` int(1) DEFAULT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `descripcion` varchar(500) DEFAULT NULL,
  `peso` float DEFAULT NULL,
  `minimo` int(1) DEFAULT NULL,
  `maximo` int(1) DEFAULT NULL,
  PRIMARY KEY (`idfactor`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `factor`
--

LOCK TABLES `factor` WRITE;
/*!40000 ALTER TABLE `factor` DISABLE KEYS */;
INSERT INTO `factor` VALUES (1,2,'Sistema Distribuido','¿El sistema tiene una arquitectura centralizada o distribuida?',2,0,5),(2,2,'Desempeño, Tiempo de Respuesta','¿El cliente del sistema necesita que sea éste rápido? ¿Es el tiempo de respuesta un criterio importante?',1,0,5),(3,2,'Eficiencia de Usuario Final','¿La aplicación se debe diseñar para incrementar la eficiencia del usuario final?',1,0,5),(4,2,'Grado de Complejidad del Procesamiento Interno','¿La aplicación tiene lógica elaborada o procesamiento matemático?',1,0,5),(5,2,'Reusabilidad del Código','¿Se requiere cuidar la reusabilidad?',1,0,5),(6,2,'Facilidad de Instalación','¿El cliente está buscando facilidad en la instalación?',0.5,0,5),(7,2,'Portabilidad','¿Se busca una implantación que soporte diversas plataformas tecnológicas?',2,0,5),(8,2,'Facilidad al Cambio','¿Se busca una alta capacidad de adecuación en el futuro?',1,0,5),(9,2,'Concurrencia','¿Se busca que una gran cantidad de usuarios trabajen bajo características de bloqueo a secciones críticas de datos?',1,0,5),(10,2,'Características Especiales Seguridad','¿Se busca tener mecanismos de seguridad?',1,0,5),(11,2,'Proporciona Acceso Directo a Software de Terceros','¿El proyecto depende del uso de software de terceros?',1,0,5),(12,2,'Se requieren facilidades de Capacitación al usuario','¿Qué necesidades y facilidades de capacitación hacia los usuarios se requieren sobre el sistema o la aplicación?',1,0,5),(13,2,'Grado de complejidad del proceso de certificación','Grado de complejidad del proceso de certificación',1,0,5),(14,1,'Familiaridad con el modelo del proyecto utilizado','',1.5,0,5),(15,1,'Experiencia en la Aplicación','',0.5,0,5),(16,1,'Experiencia en Orientación a Objetos','',1,0,5),(17,1,'Capacidades del Líder Técnico','',0.5,0,5),(18,1,'Motivación','',1,0,5),(19,1,'Requerimientos Estables','',2,0,5),(20,1,'Desarrolladores de Tiempo Parcial','',-1,0,5),(21,1,'Lenguaje de Programación','',-2,0,5);
/*!40000 ALTER TABLE `factor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `factor_estimacion`
--

DROP TABLE IF EXISTS `factor_estimacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `factor_estimacion` (
  `idfactorestimacion` int(11) NOT NULL AUTO_INCREMENT,
  `idestimacion` int(6) NOT NULL,
  `idfactor` int(11) NOT NULL,
  `valor` int(1) DEFAULT NULL,
  PRIMARY KEY (`idfactorestimacion`),
  KEY `fk_factor_has_estimacion_estimacion1_idx` (`idestimacion`),
  KEY `fk_factor_has_estimacion_factor1_idx` (`idfactor`),
  CONSTRAINT `fk_factor_has_estimacion_estimacion1` FOREIGN KEY (`idestimacion`) REFERENCES `estimacion` (`idestimacion`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_factor_has_estimacion_factor1` FOREIGN KEY (`idfactor`) REFERENCES `factor` (`idfactor`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=113667 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `factor_estimacion`
--

LOCK TABLES `factor_estimacion` WRITE;
/*!40000 ALTER TABLE `factor_estimacion` DISABLE KEYS */;
INSERT INTO `factor_estimacion` VALUES (113604,1,6,3),(113605,1,5,3),(113606,1,7,3),(113607,1,11,3),(113608,1,18,3),(113609,1,8,3),(113610,1,16,3),(113611,1,19,3),(113612,1,9,3),(113613,1,2,3),(113614,1,10,3),(113615,1,13,3),(113616,1,1,3),(113617,1,20,3),(113618,1,4,3),(113619,1,17,3),(113620,1,3,3),(113621,1,14,3),(113622,1,12,3),(113623,1,15,3),(113624,1,21,3),(113625,2,2,0),(113626,2,21,0),(113627,2,10,0),(113628,2,17,0),(113629,2,14,0),(113630,2,19,0),(113631,2,6,0),(113632,2,8,0),(113633,2,15,0),(113634,2,5,0),(113635,2,11,0),(113636,2,12,0),(113637,2,3,0),(113638,2,4,0),(113639,2,9,0),(113640,2,18,0),(113641,2,1,0),(113642,2,16,0),(113643,2,20,0),(113644,2,13,0),(113645,2,7,0),(113646,11192,3,1),(113647,11192,5,4),(113648,11192,10,5),(113649,11192,15,5),(113650,11192,2,4),(113651,11192,18,4),(113652,11192,12,2),(113653,11192,16,5),(113654,11192,21,4),(113655,11192,9,1),(113656,11192,13,4),(113657,11192,7,5),(113658,11192,8,0),(113659,11192,19,4),(113660,11192,14,3),(113661,11192,6,0),(113662,11192,11,5),(113663,11192,17,5),(113664,11192,20,0),(113665,11192,1,5),(113666,11192,4,4);
/*!40000 ALTER TABLE `factor_estimacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lval`
--

DROP TABLE IF EXISTS `lval`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lval` (
  `tipolval` varchar(20) NOT NULL,
  `codlval` int(11) NOT NULL,
  `valor` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`tipolval`,`codlval`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lval`
--

LOCK TABLES `lval` WRITE;
/*!40000 ALTER TABLE `lval` DISABLE KEYS */;
INSERT INTO `lval` VALUES ('Complejidad',1,'Baja'),('Complejidad',2,'Media'),('Complejidad',3,'Alta'),('Desviación',1,'0.3'),('TipoCosto',1,'Calculado'),('TipoCosto',2,'Fijo'),('TipoCosto',3,'Gestión'),('TipoFactor',1,'Ambiente'),('TipoFactor',2,'Técnico'),('TipoPunto',1,'Actor'),('TipoPunto',2,'Caso de Uso');
/*!40000 ALTER TABLE `lval` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plataforma`
--

DROP TABLE IF EXISTS `plataforma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `plataforma` (
  `idplataforma` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `factorproductividad` float NOT NULL,
  PRIMARY KEY (`idplataforma`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plataforma`
--

LOCK TABLES `plataforma` WRITE;
/*!40000 ALTER TABLE `plataforma` DISABLE KEYS */;
INSERT INTO `plataforma` VALUES (1,'Acsel/X',28),(2,'GuideWire',28),(3,'Apps Lotus Notes',28),(4,'Novasys',28),(5,'Apps .NET',28),(6,'Apps Java',28),(7,'Business Objects',28),(8,'BPM',28),(9,'HP Exstream',28);
/*!40000 ALTER TABLE `plataforma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proveedor` (
  `idproveedor` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `costohora` float DEFAULT NULL,
  PRIMARY KEY (`idproveedor`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedor`
--

LOCK TABLES `proveedor` WRITE;
/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;
INSERT INTO `proveedor` VALUES (1,'Pacífico',40),(2,'Accenture',100.63),(3,'Itera',106.24);
/*!40000 ALTER TABLE `proveedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `punto`
--

DROP TABLE IF EXISTS `punto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `punto` (
  `tipo` int(1) NOT NULL,
  `complejidad` int(1) NOT NULL,
  `puntos` int(2) DEFAULT NULL,
  PRIMARY KEY (`tipo`,`complejidad`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `punto`
--

LOCK TABLES `punto` WRITE;
/*!40000 ALTER TABLE `punto` DISABLE KEYS */;
INSERT INTO `punto` VALUES (1,1,1),(1,2,2),(1,3,3),(2,1,5),(2,2,10),(2,3,15);
/*!40000 ALTER TABLE `punto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tarea`
--

DROP TABLE IF EXISTS `tarea`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tarea` (
  `idtarea` int(11) NOT NULL AUTO_INCREMENT,
  `idproveedor` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `incluir` tinyint(1) DEFAULT NULL,
  `orden` int(11) DEFAULT NULL,
  `porcentaje` float DEFAULT NULL,
  `tipocosto` int(1) DEFAULT NULL,
  `disenotecnico` int(1) DEFAULT NULL,
  PRIMARY KEY (`idtarea`),
  KEY `fk_tarea_proveedor1_idx` (`idproveedor`),
  CONSTRAINT `fk_tarea_proveedor1` FOREIGN KEY (`idproveedor`) REFERENCES `proveedor` (`idproveedor`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tarea`
--

LOCK TABLES `tarea` WRITE;
/*!40000 ALTER TABLE `tarea` DISABLE KEYS */;
INSERT INTO `tarea` VALUES (1,2,'Definición Funcional',1,1,NULL,2,0),(2,2,'Acompañamiento',1,2,0.3,4,0),(3,2,'Diseño Técnico',1,3,0.2,1,1),(4,2,'Construcción',1,4,0.4,1,0),(5,3,'Certificación',1,5,0.3,1,0),(6,3,'Pruebas de Stress',1,6,0.1,1,0),(7,2,'Gestión',1,7,0.15,3,0);
/*!40000 ALTER TABLE `tarea` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-20 12:17:46
