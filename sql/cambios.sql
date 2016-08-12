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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-08-12 16:31:06
