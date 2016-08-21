-- MySQL dump 10.13  Distrib 5.7.13, for Linux (x86_64)
--
-- Host: localhost    Database: estimator
-- ------------------------------------------------------
-- Server version	5.7.13-0ubuntu0.16.04.2

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
  `descripcion` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`idactor`),
  KEY `fk_actor_estimacion1_idx` (`idestimacion`),
  CONSTRAINT `fk_actor_estimacion1` FOREIGN KEY (`idestimacion`) REFERENCES `estimacion` (`idestimacion`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor`
--

LOCK TABLES `actor` WRITE;
/*!40000 ALTER TABLE `actor` DISABLE KEYS */;
INSERT INTO `actor` VALUES (61,1,1,'Actor'),(62,11192,3,'Sistema Guidewire'),(63,11192,1,'Cajero BCP'),(64,11192,1,'Analista BCP'),(65,11192,2,'Sistema Teletransfer'),(66,11192,1,'Analista de Cobranzas PPS'),(67,11192,3,'Sistema Acsel/X'),(68,11192,2,'Sistema de Cobranzas BCP'),(71,10248,2,'Interfaz entre Acsel/X y Paperless'),(72,10248,3,'Usuario de cobranzas'),(73,11312,3,'Usuario de Emisión'),(74,11544,3,'Ejecutivo de Reaseguros'),(75,11544,3,'Emisor'),(76,11544,2,'Integración entre GW y el ambiente de Integración'),(77,11544,3,'Contador'),(78,11544,3,'Actuario'),(79,11544,2,'Interfaz entre Acsel/X y el ambiente de Integración'),(80,11544,2,'Interfaz entre módulos de GW');
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
  `descripcion` varchar(500) NOT NULL,
  `idplataforma` int(11) NOT NULL,
  PRIMARY KEY (`idcaso`),
  KEY `fk_caso_de_uso_estimacion_idx` (`idestimacion`),
  KEY `fk_caso_de_uso_plataforma1_idx` (`idplataforma`),
  CONSTRAINT `fk_caso_de_uso_estimacion` FOREIGN KEY (`idestimacion`) REFERENCES `estimacion` (`idestimacion`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_caso_de_uso_plataforma1` FOREIGN KEY (`idplataforma`) REFERENCES `plataforma` (`idplataforma`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `caso_de_uso`
--

LOCK TABLES `caso_de_uso` WRITE;
/*!40000 ALTER TABLE `caso_de_uso` DISABLE KEYS */;
INSERT INTO `caso_de_uso` VALUES (65,1,2,'Caso 1',2),(66,11192,2,'Búsqueda de Documentos',5),(67,11192,1,'Relación de Ingresos',5),(68,11192,2,'Criterio de Pago',5),(69,11192,1,'Filtros de Búsqueda del Cliente',5),(70,11192,3,'Extorno de Pagos',5),(71,11192,2,'Validación de Operaciones',5),(72,11192,2,'Envio de Correos de Alerta - Configurable',5),(74,10248,1,'Validar impresión de documentos electrónicos',1),(75,11312,1,'Emitir póliza con planes antiguos de Cascos',1),(76,11312,3,'Emitir póliza con planes nuevos de Cascos',1),(77,11544,3,'Generar reporte de RRC y Prima Diferida',1),(78,11544,2,'Calcular la prima diferida cedida',1),(79,11544,3,'Calcular la reserva de riesgo en curso',2),(80,11544,3,'Calcular la prima diferida directa',1),(81,11544,2,'Generar reporte de Avisos Anulados',1),(82,11544,1,'Calcular la fecha de convenio',2),(83,11544,1,'Calcular la fecha de convenio',1),(84,11544,3,'Generar reporte de Sustento de RRC',1),(85,11544,3,'Calcular la prima diferida directa',2),(86,11544,3,'Calcular la reserva de riesgo en curso',1),(87,11544,1,'Calcular la prima diferida cedida con el cálculo antiguo',1),(88,11544,1,'Calcular el porcentaje de gasto por ramo',2),(89,11544,1,'Calcular la RRC con el cálculo antiguo',2),(90,11544,1,'Calcular la prima diferida directa con el cálculo antiguo',1),(91,11544,1,'Calcular la prima diferida directa con el cálculo antiguo',2),(92,11544,1,'Calcular la RRC con el cálculo antiguo',1),(93,11544,1,'Calcular el porcentaje de gasto por ramo',1),(94,11544,1,'Calcular la prima diferida cedida con el cálculo antiguo',2);
/*!40000 ALTER TABLE `caso_de_uso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `costo_adicional`
--

DROP TABLE IF EXISTS `costo_adicional`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `costo_adicional` (
  `idcosto` int(11) NOT NULL AUTO_INCREMENT,
  `idestimacion` int(6) NOT NULL,
  `descripcion` varchar(500) DEFAULT NULL,
  `costo` double DEFAULT NULL,
  `moneda` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`idcosto`),
  KEY `fk_costo_adicional_estimacion2_idx` (`idestimacion`),
  CONSTRAINT `fk_costo_adicional_estimacion2` FOREIGN KEY (`idestimacion`) REFERENCES `estimacion` (`idestimacion`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `costo_adicional`
--

LOCK TABLES `costo_adicional` WRITE;
/*!40000 ALTER TABLE `costo_adicional` DISABLE KEYS */;
INSERT INTO `costo_adicional` VALUES (2,11544,'Almacenamiento',8796.81,'PEN');
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
INSERT INTO `cronograma` VALUES (1,1,1,NULL,1,5,40),(1,2,1,0.3,1,7.21,17.31),(1,3,1,0.2,1,7.21,57.71),(1,4,1,0.4,1,14.43,115.42),(1,5,1,0.4,1,14.43,115.42),(1,6,0,0,0,0,0),(1,7,1,0.15,1,41.07,49.28),(2,1,1,NULL,1,NULL,NULL),(2,2,1,0.3,1,NULL,NULL),(2,3,1,0.2,1,NULL,NULL),(2,4,1,0.4,1,NULL,NULL),(2,5,1,0.3,1,NULL,NULL),(2,6,1,0.1,1,NULL,NULL),(2,7,1,0.15,1,NULL,NULL),(10248,1,1,NULL,1,NULL,NULL),(10248,2,1,0.3,1,NULL,NULL),(10248,3,1,0.2,1,NULL,NULL),(10248,4,1,0.4,1,NULL,NULL),(10248,5,1,0.3,1,NULL,NULL),(10248,6,1,0.1,1,NULL,NULL),(10248,7,1,0.15,1,NULL,NULL),(11192,1,1,NULL,1,15,120),(11192,2,1,0.3,1,35.45,85.08),(11192,3,1,0.2,1,35.45,283.58),(11192,4,1,0.4,2,35.45,567.17),(11192,5,1,0.3,1,53.17,425.38),(11192,6,1,0.1,1,17.72,141.79),(11192,7,1,0.3,1,156.79,376.3),(11312,1,1,NULL,1,7,56),(11312,2,1,0.3,1,10.7,25.67),(11312,3,1,0.2,1,10.69,85.55),(11312,4,1,0.4,1,21.39,171.1),(11312,5,1,0.4,1,21.39,171.1),(11312,6,0,0,0,0,0),(11312,7,1,0.15,1,60.47,72.56),(11544,1,1,NULL,1,23,184),(11544,2,1,0.3,1,87.07,208.97),(11544,3,1,0.2,1,87.07,696.56),(11544,4,1,0.4,1,174.14,1393.12),(11544,5,1,0.4,1,174.14,1393.12),(11544,6,0,0,0,0,0),(11544,7,1,0.4,1,458.35,1466.72);
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
  `idusuario` int(11) NOT NULL,
  PRIMARY KEY (`idestimacion`),
  KEY `fk_estimacion_usuario1_idx` (`idusuario`),
  CONSTRAINT `fk_estimacion_usuario1` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estimacion`
--

LOCK TABLES `estimacion` WRITE;
/*!40000 ALTER TABLE `estimacion` DISABLE KEYS */;
INSERT INTO `estimacion` VALUES (1,'Estimación','Eds','2016-06-13 11:53:06',11.16,312.59,14,1),(2,'123','123',NULL,0,0,0,1),(10248,'Facturación Electrónica','Kenji Dettleff',NULL,8.4,235.2,2,1),(11192,'Pagos en línea a través del BCP','Angela Valencia','2016-08-13 15:06:04',57.96,1623,51,1),(11312,'Nuevo Reporte de Impresión para Cascos','Kenji Dettleff','2016-06-21 15:54:25',17.1,478.71,7,1),(11544,'Nueva RRC y Prima Diferida','Kenji Dettleff','2016-08-19 09:22:04',138.42,3875.77,17,3);
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
INSERT INTO `factor` VALUES (1,2,'Sistema Distribuido','¿El sistema tiene una arquitectura centralizada o distribuida?',2,0,5),(2,2,'Desempeño, Tiempo de Respuesta','¿El cliente del sistema necesita que sea éste rápido? ¿Es el tiempo de respuesta un criterio importante?',1,0,5),(3,2,'Eficiencia de Usuario Final','¿La aplicación se debe diseñar para incrementar la eficiencia del usuario final?',1,0,5),(4,2,'Grado de Complejidad del Procesamiento Interno','¿La aplicación tiene lógica elaborada o procesamiento matemático?',1,0,5),(5,2,'Reusabilidad del Código','¿Se requiere cuidar la reusabilidad?',1,0,5),(6,2,'Facilidad de Instalación','¿El cliente está buscando facilidad en la instalación?',0.5,0,5),(7,2,'Facilidad de Uso','Facilidad de Uso',0.5,0,5),(8,2,'Portabilidad','¿Se busca una solución que soporte diversas plataformas tecnológicas?',2,0,5),(9,2,'Facilidad al Cambio','¿Se busca una alta capacidad de adecuación en el futuro?',1,0,5),(10,2,'Concurrencia / procesamiento paralelo','¿Se busca que una gran cantidad de usuarios trabajen bajo características de bloqueo a secciones críticas de datos?',1,0,5),(11,2,'Características de Seguridad','¿Se busca tener mecanismos de seguridad?',1,0,5),(12,2,'Proporciona Acceso Directo a Software de Terceros','¿El proyecto depende del uso de software de terceros?',1,0,5),(13,2,'Capacitación al Usuario','Complejidad y dificultad de la capacitación requerida hacia los usuarios sobre el sistema o la aplicación.',1,0,5),(14,1,'Familiaridad con el modelo del proyecto utilizado','Conocimiento de la metodología de proyecto y herramientas asociadas.',1.5,0,5),(15,1,'Experiencia en la Aplicación','Conocimiento de las aplicaciones del negocio relacionadas al proyecto.',0.5,0,5),(16,1,'Experiencia en Orientación a Objetos','Conocimiento del paradigma y modelo de programación de orientación a objectos.',1,0,5),(17,1,'Capacidades del Líder Técnico','Capacidad de gestión de recursos y experiencia del líder técnico (JP) del proyecto.',0.5,0,5),(18,1,'Motivación','Motivación, capacidad de auto-gestión y eficiencia del equipo de trabajo',1,0,5),(19,1,'Requerimientos Estables','Probabilidad de variación en el alcance de los requerimientos',2,0,5),(20,1,'Desarrolladores de Tiempo Parcial','¿Hay desarrolladores de tiempo parcial?',-1,0,5),(21,1,'Dificultad del Lenguaje de Programación','Nivel de dificultad de los lenguajes de programación usados.',-1,0,5);
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
) ENGINE=InnoDB AUTO_INCREMENT=113730 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `factor_estimacion`
--

LOCK TABLES `factor_estimacion` WRITE;
/*!40000 ALTER TABLE `factor_estimacion` DISABLE KEYS */;
INSERT INTO `factor_estimacion` VALUES (113604,1,6,3),(113605,1,5,3),(113606,1,7,3),(113607,1,11,3),(113608,1,18,3),(113609,1,8,3),(113610,1,16,3),(113611,1,19,3),(113612,1,9,3),(113613,1,2,3),(113614,1,10,3),(113615,1,13,3),(113616,1,1,3),(113617,1,20,3),(113618,1,4,3),(113619,1,17,3),(113620,1,3,3),(113621,1,14,3),(113622,1,12,3),(113623,1,15,3),(113624,1,21,3),(113625,2,2,0),(113626,2,21,0),(113627,2,10,0),(113628,2,17,0),(113629,2,14,0),(113630,2,19,0),(113631,2,6,0),(113632,2,8,0),(113633,2,15,0),(113634,2,5,0),(113635,2,11,0),(113636,2,12,0),(113637,2,3,0),(113638,2,4,0),(113639,2,9,0),(113640,2,18,0),(113641,2,1,0),(113642,2,16,0),(113643,2,20,0),(113644,2,13,0),(113645,2,7,0),(113646,11192,3,1),(113647,11192,5,4),(113648,11192,10,5),(113649,11192,15,5),(113650,11192,2,4),(113651,11192,18,4),(113652,11192,12,2),(113653,11192,16,5),(113654,11192,21,4),(113655,11192,9,1),(113656,11192,13,4),(113657,11192,7,5),(113658,11192,8,0),(113659,11192,19,4),(113660,11192,14,3),(113661,11192,6,0),(113662,11192,11,5),(113663,11192,17,5),(113664,11192,20,0),(113665,11192,1,5),(113666,11192,4,4),(113667,10248,1,0),(113668,10248,6,0),(113669,10248,13,0),(113670,10248,20,0),(113671,10248,9,0),(113672,10248,14,0),(113673,10248,3,0),(113674,10248,21,0),(113675,10248,12,0),(113676,10248,10,0),(113677,10248,17,0),(113678,10248,15,0),(113679,10248,4,0),(113680,10248,19,0),(113681,10248,8,0),(113682,10248,5,0),(113683,10248,16,0),(113684,10248,18,0),(113685,10248,11,0),(113686,10248,2,0),(113687,10248,7,0),(113688,11312,16,5),(113689,11312,17,3),(113690,11312,20,0),(113691,11312,6,1),(113692,11312,13,1),(113693,11312,18,1),(113694,11312,15,5),(113695,11312,2,2),(113696,11312,5,3),(113697,11312,1,3),(113698,11312,8,0),(113699,11312,21,2),(113700,11312,4,5),(113701,11312,7,0),(113702,11312,19,0),(113703,11312,12,1),(113704,11312,14,5),(113705,11312,10,1),(113706,11312,11,0),(113707,11312,3,0),(113708,11312,9,0),(113709,11544,16,4),(113710,11544,2,4),(113711,11544,10,2),(113712,11544,12,0),(113713,11544,4,5),(113714,11544,7,4),(113715,11544,11,4),(113716,11544,20,0),(113717,11544,5,5),(113718,11544,9,4),(113719,11544,6,0),(113720,11544,8,0),(113721,11544,18,3),(113722,11544,1,5),(113723,11544,13,5),(113724,11544,19,3),(113725,11544,15,3),(113726,11544,21,2),(113727,11544,3,2),(113728,11544,14,5),(113729,11544,17,3);
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
INSERT INTO `lval` VALUES ('Complejidad',1,'Baja'),('Complejidad',2,'Media'),('Complejidad',3,'Alta'),('Desviación',1,'0.3'),('EDS',1,'Angela Valencia'),('EDS',2,'Diego Ccallo'),('EDS',3,'Edson Jimenez'),('EDS',4,'Hernán George'),('EDS',5,'Kenji Dettleff'),('EDS',6,'Miguel Payet'),('EDS',7,'Patricia Quiñonez'),('Moneda',1,'PEN'),('Moneda',2,'USD'),('ProductividadActor',1,'28'),('TipoCosto',1,'Calculado'),('TipoCosto',2,'Fijo'),('TipoCosto',3,'Gestión'),('TipoFactor',1,'Ambiente'),('TipoFactor',2,'Técnico'),('TipoPunto',1,'Actor'),('TipoPunto',2,'Caso de Uso');
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
INSERT INTO `plataforma` VALUES (1,'Acsel/X',28),(2,'GuideWire',30),(3,'Apps Lotus Notes',28),(4,'Novasys',28),(5,'Apps .NET',28),(6,'Apps Java',28),(7,'Business Objects',28),(8,'BPM',28),(9,'HP Exstream',28);
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
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rol` (
  `idrol` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idrol`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'usuario'),(2,'administrador');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
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

--
-- Table structure for table `tokenpassword`
--

DROP TABLE IF EXISTS `tokenpassword`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tokenpassword` (
  `idtoken` int(11) NOT NULL AUTO_INCREMENT,
  `creacion` datetime DEFAULT NULL,
  `tipo` varchar(45) DEFAULT NULL,
  `token` varchar(512) DEFAULT NULL,
  `usuario` varchar(45) DEFAULT NULL,
  `valido` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`idtoken`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tokenpassword`
--

LOCK TABLES `tokenpassword` WRITE;
/*!40000 ALTER TABLE `tokenpassword` DISABLE KEYS */;
INSERT INTO `tokenpassword` VALUES (1,'2016-08-15 10:18:35','U','3fdada8aa37315a77a80b90012edcb98792f112a48078ec7f11d0e018aae235c','kenji',1);
/*!40000 ALTER TABLE `tokenpassword` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `idusuario` int(11) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(45) DEFAULT NULL,
  `nombre` varchar(150) DEFAULT NULL,
  `apellidos` varchar(150) DEFAULT NULL,
  `email` varchar(150) DEFAULT NULL,
  `password` varchar(512) DEFAULT NULL,
  `salt` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`idusuario`),
  UNIQUE KEY `codigo_UNIQUE` (`codigo`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'miguel','miguel','oiausu','mpayet@me.com','/T8YShFeQsAEmZmr3z/vZCAd41DDV6+My43UZBM4KFs=','e9LvbfMRvPAcIMqseLaKxg=='),(2,'admin','admin','apellido','mpayet@fastmail.fm',NULL,NULL),(3,'kenji','kenji','dettleff','kdettleff@pacifico.com.pe','/T8YShFeQsAEmZmr3z/vZCAd41DDV6+My43UZBM4KFs=','e9LvbfMRvPAcIMqseLaKxg==');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_rol`
--

DROP TABLE IF EXISTS `usuario_rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario_rol` (
  `idusuario` int(11) NOT NULL,
  `idrol` int(11) NOT NULL,
  PRIMARY KEY (`idusuario`,`idrol`),
  KEY `fk_usuario_has_rol_rol1_idx` (`idrol`),
  KEY `fk_usuario_has_rol_usuario_idx` (`idusuario`),
  CONSTRAINT `fk_usuario_has_rol_rol1` FOREIGN KEY (`idrol`) REFERENCES `rol` (`idrol`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuario_has_rol_usuario` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_rol`
--

LOCK TABLES `usuario_rol` WRITE;
/*!40000 ALTER TABLE `usuario_rol` DISABLE KEYS */;
INSERT INTO `usuario_rol` VALUES (1,1),(3,1),(2,2);
/*!40000 ALTER TABLE `usuario_rol` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-08-19 20:00:08
