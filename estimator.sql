-- MySQL dump 10.16  Distrib 10.1.12-MariaDB, for Win32 (AMD64)
--
-- Host: localhost    Database: estimator
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
-- Table structure for table `complejidad`
--

DROP TABLE IF EXISTS `complejidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `complejidad` (
  `idcomplejidad` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idcomplejidad`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `complejidad`
--

LOCK TABLES `complejidad` WRITE;
/*!40000 ALTER TABLE `complejidad` DISABLE KEYS */;
INSERT INTO `complejidad` VALUES (1,'Baja'),(2,'Media'),(3,'Alta');
/*!40000 ALTER TABLE `complejidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cronograma`
--

DROP TABLE IF EXISTS `cronograma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cronograma` (
  `idestimacion` int(11) NOT NULL,
  `idtarea` int(11) NOT NULL,
  `porcentaje` float DEFAULT NULL,
  `horas` float DEFAULT NULL,
  PRIMARY KEY (`idestimacion`,`idtarea`),
  KEY `fk_estimacion_has_tarea_tarea1_idx` (`idtarea`),
  KEY `fk_estimacion_has_tarea_estimacion1_idx` (`idestimacion`),
  CONSTRAINT `fk_estimacion_has_tarea_estimacion1` FOREIGN KEY (`idestimacion`) REFERENCES `estimacion` (`idestimacion`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_estimacion_has_tarea_tarea1` FOREIGN KEY (`idtarea`) REFERENCES `tarea` (`idtarea`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cronograma`
--

LOCK TABLES `cronograma` WRITE;
/*!40000 ALTER TABLE `cronograma` DISABLE KEYS */;
/*!40000 ALTER TABLE `cronograma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estimacion`
--

DROP TABLE IF EXISTS `estimacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estimacion` (
  `idestimacion` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`idestimacion`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estimacion`
--

LOCK TABLES `estimacion` WRITE;
/*!40000 ALTER TABLE `estimacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `estimacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estimacion_factor`
--

DROP TABLE IF EXISTS `estimacion_factor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estimacion_factor` (
  `idestimacionfactor` int(11) NOT NULL AUTO_INCREMENT,
  `idestimacion` int(11) NOT NULL,
  `idfactor` int(11) NOT NULL,
  `valor` int(11) DEFAULT NULL,
  PRIMARY KEY (`idestimacionfactor`),
  KEY `fk_estimacion_has_factor_factor1_idx` (`idfactor`),
  KEY `fk_estimacion_has_factor_estimacion1_idx` (`idestimacion`),
  CONSTRAINT `fk_estimacion_has_factor_estimacion1` FOREIGN KEY (`idestimacion`) REFERENCES `estimacion` (`idestimacion`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_estimacion_has_factor_factor1` FOREIGN KEY (`idfactor`) REFERENCES `factor` (`idfactor`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estimacion_factor`
--

LOCK TABLES `estimacion_factor` WRITE;
/*!40000 ALTER TABLE `estimacion_factor` DISABLE KEYS */;
/*!40000 ALTER TABLE `estimacion_factor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `factor`
--

DROP TABLE IF EXISTS `factor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `factor` (
  `idfactor` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `valorminimo` int(11) DEFAULT NULL,
  `valormaximo` int(11) DEFAULT NULL,
  `peso` float DEFAULT NULL,
  `idtipofactor` int(11) NOT NULL,
  PRIMARY KEY (`idfactor`,`idtipofactor`),
  KEY `fk_factor_clase1_idx` (`idtipofactor`),
  CONSTRAINT `fk_factor_clase1` FOREIGN KEY (`idtipofactor`) REFERENCES `tipo_de_factor` (`idtipofactor`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `factor`
--

LOCK TABLES `factor` WRITE;
/*!40000 ALTER TABLE `factor` DISABLE KEYS */;
/*!40000 ALTER TABLE `factor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funcionalidad`
--

DROP TABLE IF EXISTS `funcionalidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `funcionalidad` (
  `idfuncionalidad` int(11) NOT NULL AUTO_INCREMENT,
  `idestimacion` int(11) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `puntajefuncional` int(11) DEFAULT NULL,
  PRIMARY KEY (`idfuncionalidad`),
  KEY `fk_funcionalidad_estimacion` (`idestimacion`),
  CONSTRAINT `fk_funcionalidad_estimacion` FOREIGN KEY (`idestimacion`) REFERENCES `estimacion` (`idestimacion`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcionalidad`
--

LOCK TABLES `funcionalidad` WRITE;
/*!40000 ALTER TABLE `funcionalidad` DISABLE KEYS */;
/*!40000 ALTER TABLE `funcionalidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funcionalidad_interfaz`
--

DROP TABLE IF EXISTS `funcionalidad_interfaz`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `funcionalidad_interfaz` (
  `idfuncionalidadinterfaz` int(11) NOT NULL AUTO_INCREMENT,
  `idfuncionalidad` int(11) NOT NULL,
  `idinterfaz` int(11) NOT NULL,
  PRIMARY KEY (`idfuncionalidadinterfaz`),
  KEY `fk_interfaz_has_funcionalidad_funcionalidad1_idx` (`idfuncionalidad`),
  KEY `fk_interfaz_has_funcionalidad_interfaz1_idx` (`idinterfaz`),
  CONSTRAINT `fk_interfaz_has_funcionalidad_funcionalidad1` FOREIGN KEY (`idfuncionalidad`) REFERENCES `funcionalidad` (`idfuncionalidad`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_interfaz_has_funcionalidad_interfaz1` FOREIGN KEY (`idinterfaz`) REFERENCES `interfaz` (`idinterfaz`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcionalidad_interfaz`
--

LOCK TABLES `funcionalidad_interfaz` WRITE;
/*!40000 ALTER TABLE `funcionalidad_interfaz` DISABLE KEYS */;
/*!40000 ALTER TABLE `funcionalidad_interfaz` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funcionalidad_plataforma`
--

DROP TABLE IF EXISTS `funcionalidad_plataforma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `funcionalidad_plataforma` (
  `idfuncionalidadplataforma` int(11) NOT NULL AUTO_INCREMENT,
  `idfuncionalidad` int(11) NOT NULL,
  `idplataforma` int(11) NOT NULL,
  PRIMARY KEY (`idfuncionalidadplataforma`),
  KEY `fk_funcionalidad_has_plataforma_plataforma1_idx` (`idplataforma`),
  KEY `fk_funcionalidad_has_plataforma_funcionalidad1_idx` (`idfuncionalidad`),
  CONSTRAINT `fk_funcionalidad_has_plataforma_funcionalidad1` FOREIGN KEY (`idfuncionalidad`) REFERENCES `funcionalidad` (`idfuncionalidad`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_funcionalidad_has_plataforma_plataforma1` FOREIGN KEY (`idplataforma`) REFERENCES `plataforma` (`idplataforma`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcionalidad_plataforma`
--

LOCK TABLES `funcionalidad_plataforma` WRITE;
/*!40000 ALTER TABLE `funcionalidad_plataforma` DISABLE KEYS */;
/*!40000 ALTER TABLE `funcionalidad_plataforma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `interfaz`
--

DROP TABLE IF EXISTS `interfaz`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `interfaz` (
  `idinterfaz` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `idinterfazvalor` int(11) NOT NULL,
  PRIMARY KEY (`idinterfaz`),
  KEY `fk_interfaz_interfaz_valor1_idx` (`idinterfazvalor`),
  CONSTRAINT `fk_interfaz_interfaz_valor1` FOREIGN KEY (`idinterfazvalor`) REFERENCES `interfaz_valor` (`idinterfazvalor`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interfaz`
--

LOCK TABLES `interfaz` WRITE;
/*!40000 ALTER TABLE `interfaz` DISABLE KEYS */;
/*!40000 ALTER TABLE `interfaz` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `interfaz_valor`
--

DROP TABLE IF EXISTS `interfaz_valor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `interfaz_valor` (
  `idinterfazvalor` int(11) NOT NULL AUTO_INCREMENT,
  `idcomplejidad` int(11) NOT NULL,
  `idtipointerfaz` int(11) NOT NULL,
  `idtipocambio` int(11) NOT NULL,
  `puntajefuncional` int(11) DEFAULT NULL,
  PRIMARY KEY (`idinterfazvalor`),
  KEY `fk_interfaz_valor_complejidad1_idx` (`idcomplejidad`),
  KEY `fk_interfaz_valor_especie1_idx` (`idtipointerfaz`),
  KEY `fk_interfaz_valor_tipo_de_cambio1_idx` (`idtipocambio`),
  CONSTRAINT `fk_interfaz_valor_complejidad1` FOREIGN KEY (`idcomplejidad`) REFERENCES `complejidad` (`idcomplejidad`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_interfaz_valor_especie1` FOREIGN KEY (`idtipointerfaz`) REFERENCES `tipo_de_interfaz` (`idtipointerfaz`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_interfaz_valor_tipo_de_cambio1` FOREIGN KEY (`idtipocambio`) REFERENCES `tipo_de_cambio` (`idtipocambio`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interfaz_valor`
--

LOCK TABLES `interfaz_valor` WRITE;
/*!40000 ALTER TABLE `interfaz_valor` DISABLE KEYS */;
/*!40000 ALTER TABLE `interfaz_valor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plataforma`
--

DROP TABLE IF EXISTS `plataforma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `plataforma` (
  `idplataforma` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `idplataformavalor` int(11) NOT NULL,
  PRIMARY KEY (`idplataforma`),
  KEY `fk_plataforma_plataforma_valor1_idx` (`idplataformavalor`),
  CONSTRAINT `fk_plataforma_plataforma_valor1` FOREIGN KEY (`idplataformavalor`) REFERENCES `plataforma_valor` (`idplataformavalor`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plataforma`
--

LOCK TABLES `plataforma` WRITE;
/*!40000 ALTER TABLE `plataforma` DISABLE KEYS */;
/*!40000 ALTER TABLE `plataforma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plataforma_valor`
--

DROP TABLE IF EXISTS `plataforma_valor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `plataforma_valor` (
  `idplataformavalor` int(11) NOT NULL AUTO_INCREMENT,
  `idtipocambio` int(11) NOT NULL,
  `idcomplejidad` int(11) NOT NULL,
  `idtipoplataforma` int(11) NOT NULL,
  `puntajefuncional` int(11) DEFAULT NULL,
  PRIMARY KEY (`idplataformavalor`),
  KEY `fk_plataforma_valor_tipo1_idx` (`idtipocambio`),
  KEY `fk_plataforma_valor_complejidad1_idx` (`idcomplejidad`),
  KEY `fk_plataforma_valor_tipo_de_plataforma1_idx` (`idtipoplataforma`),
  CONSTRAINT `fk_plataforma_valor_complejidad1` FOREIGN KEY (`idcomplejidad`) REFERENCES `complejidad` (`idcomplejidad`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_plataforma_valor_tipo1` FOREIGN KEY (`idtipocambio`) REFERENCES `tipo_de_cambio` (`idtipocambio`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_plataforma_valor_tipo_de_plataforma1` FOREIGN KEY (`idtipoplataforma`) REFERENCES `tipo_de_plataforma` (`idtipoplataforma`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plataforma_valor`
--

LOCK TABLES `plataforma_valor` WRITE;
/*!40000 ALTER TABLE `plataforma_valor` DISABLE KEYS */;
/*!40000 ALTER TABLE `plataforma_valor` ENABLE KEYS */;
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
  `costoxhora` float DEFAULT NULL,
  PRIMARY KEY (`idproveedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedor`
--

LOCK TABLES `proveedor` WRITE;
/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;
/*!40000 ALTER TABLE `proveedor` ENABLE KEYS */;
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
  `peso` float DEFAULT NULL,
  PRIMARY KEY (`idtarea`,`idproveedor`),
  KEY `fk_tarea_proveedor1_idx` (`idproveedor`),
  CONSTRAINT `fk_tarea_proveedor1` FOREIGN KEY (`idproveedor`) REFERENCES `proveedor` (`idproveedor`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tarea`
--

LOCK TABLES `tarea` WRITE;
/*!40000 ALTER TABLE `tarea` DISABLE KEYS */;
/*!40000 ALTER TABLE `tarea` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_de_cambio`
--

DROP TABLE IF EXISTS `tipo_de_cambio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_de_cambio` (
  `idtipocambio` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL COMMENT 'nuevo\nmodificado\n',
  PRIMARY KEY (`idtipocambio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_de_cambio`
--

LOCK TABLES `tipo_de_cambio` WRITE;
/*!40000 ALTER TABLE `tipo_de_cambio` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo_de_cambio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_de_factor`
--

DROP TABLE IF EXISTS `tipo_de_factor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_de_factor` (
  `idtipofactor` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL COMMENT 'ambiente\ntécnico\n',
  PRIMARY KEY (`idtipofactor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_de_factor`
--

LOCK TABLES `tipo_de_factor` WRITE;
/*!40000 ALTER TABLE `tipo_de_factor` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo_de_factor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_de_interfaz`
--

DROP TABLE IF EXISTS `tipo_de_interfaz`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_de_interfaz` (
  `idtipointerfaz` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL COMMENT 'web service\nosb\nworkload\ndemonio\nbiztalk\n\n',
  PRIMARY KEY (`idtipointerfaz`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_de_interfaz`
--

LOCK TABLES `tipo_de_interfaz` WRITE;
/*!40000 ALTER TABLE `tipo_de_interfaz` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo_de_interfaz` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_de_plataforma`
--

DROP TABLE IF EXISTS `tipo_de_plataforma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_de_plataforma` (
  `idtipoplataforma` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idtipoplataforma`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_de_plataforma`
--

LOCK TABLES `tipo_de_plataforma` WRITE;
/*!40000 ALTER TABLE `tipo_de_plataforma` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo_de_plataforma` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-11 12:01:23
