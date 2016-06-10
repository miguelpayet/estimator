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
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=113604 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-10 15:46:26
