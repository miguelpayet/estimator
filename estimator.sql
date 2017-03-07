-- MySQL dump 10.13  Distrib 5.7.17, for Linux (x86_64)
--
-- Host: localhost    Database: estimator
-- ------------------------------------------------------
-- Server version	5.7.17-0ubuntu0.16.04.1

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
) ENGINE=InnoDB AUTO_INCREMENT=140 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor`
--

LOCK TABLES `actor` WRITE;
/*!40000 ALTER TABLE `actor` DISABLE KEYS */;
INSERT INTO `actor` VALUES (62,11192,3,'Sistema Guidewire'),(63,11192,1,'Cajero BCP'),(64,11192,1,'Analista BCP'),(65,11192,2,'Sistema Teletransfer'),(66,11192,1,'Analista de Cobranzas PPS'),(67,11192,3,'Sistema Acsel/X'),(68,11192,2,'Sistema de Cobranzas BCP'),(73,11312,3,'Usuario de Emisión'),(74,11544,3,'Ejecutivo de Reaseguros'),(75,11544,3,'Emisor'),(76,11544,2,'GW'),(77,11544,3,'Contador'),(78,11544,3,'Actuario'),(79,11544,2,'Acsel/X'),(81,11544,2,'Ambiente de Integración'),(82,11598,2,'Sistema MIC'),(83,11598,1,'Sistema CRM'),(84,11598,1,'Analista Reporteador'),(85,11598,1,'Usuario Call Center'),(86,11659,2,'Workload para ejecutar extracción y convertir resultado a archivos CSV'),(87,11594,3,'Plataforma'),(88,11594,1,'Procesos automatizados'),(109,11794,3,'Emisor'),(110,11947,1,'Servidor de Impresión'),(111,11947,3,'Usuario Emisor'),(112,12101,3,'Ejecutivo de Unidad de Pagos'),(113,12101,3,'Ejecutivo de Cumplimiento'),(115,12104,3,'Usuario Configuración de Intermediarios'),(116,12104,1,'Workload de proceso batch'),(120,115942,3,'Ejecutivo de cobranza'),(121,115942,3,'Emisión'),(122,12278,3,'Ejecutivo de Cobranzas'),(123,12278,3,'Emisor'),(124,11950,2,'Proceso de Prima Diferida'),(125,11950,3,'Emisor'),(126,11950,3,'Jefe de Reaseguros'),(127,12284,2,'Novasys'),(128,12284,2,'On Base PVida'),(129,12284,2,'OSB'),(130,12284,2,'Servicio Siniestro'),(131,12284,2,'AcselX'),(132,1159412,3,'Ejecutivo de Cobranza'),(133,1159412,3,'Emisor'),(134,1159413,3,'Rol Responsable de Cta. Cte. Reaseguros'),(135,1159414,3,'Responsable de generación y pago de lotes de detracción'),(136,1159414,1,'Sistema SUNAT Operaciones en Línea'),(137,1159415,3,'Emisor'),(138,1159415,3,'Contador'),(139,1159415,3,'Ejecutivo de Unidad de Pagos');
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
) ENGINE=InnoDB AUTO_INCREMENT=208 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `caso_de_uso`
--

LOCK TABLES `caso_de_uso` WRITE;
/*!40000 ALTER TABLE `caso_de_uso` DISABLE KEYS */;
INSERT INTO `caso_de_uso` VALUES (66,11192,2,'Búsqueda de Documentos',5),(67,11192,1,'Relación de Ingresos',5),(68,11192,2,'Criterio de Pago',5),(69,11192,1,'Filtros de Búsqueda del Cliente',5),(70,11192,3,'Extorno de Pagos',5),(71,11192,2,'Validación de Operaciones',5),(72,11192,2,'Envio de Correos de Alerta - Configurable',5),(75,11312,1,'Emitir póliza con planes antiguos de Cascos',1),(76,11312,3,'Emitir póliza con planes nuevos de Cascos',1),(77,11544,3,'Generar reporte de RRC y Prima Diferida',1),(78,11544,3,'Calcular la prima diferida cedida',1),(79,11544,3,'Calcular la reserva de riesgo en curso',2),(80,11544,3,'Calcular la prima diferida directa',1),(81,11544,2,'Generar reporte de Avisos Anulados',1),(82,11544,1,'Calcular la fecha de convenio',2),(83,11544,1,'Calcular la fecha de convenio',1),(84,11544,3,'Generar reporte de Sustento de RRC',1),(85,11544,3,'Calcular la prima diferida directa',2),(86,11544,3,'Calcular la reserva de riesgo en curso',1),(87,11544,3,'Calcular la prima diferida cedida con el cálculo antiguo',1),(88,11544,1,'Calcular el porcentaje de gasto por ramo',2),(89,11544,3,'Calcular la RRC con el cálculo antiguo',2),(90,11544,3,'Calcular la prima diferida directa con el cálculo antiguo',1),(91,11544,3,'Calcular la prima diferida directa con el cálculo antiguo',2),(92,11544,3,'Calcular la RRC con el cálculo antiguo',1),(93,11544,1,'Calcular el porcentaje de gasto por ramo',1),(94,11544,3,'Calcular la prima diferida cedida con el cálculo antiguo',2),(96,11598,1,'Generar Reporte SAP BO',7),(97,11598,1,'Copiar Información del CRM hacia el MIC',5),(98,11598,2,'Generar Proceso de Equivalencias AX y GW',10),(99,11598,1,'Generar Proceso SFTP de Fuentes Externas',10),(100,11598,1,'Copiar Información de Fuentes Externas hacia el MIC',10),(101,11598,2,'Carga diaria incremental',10),(102,11659,1,'Cálcular el movimiento del cliente por siniestros',1),(103,11659,1,'Calcular el movimiento del cliente por primas',1),(104,11659,3,'Extraer indicadores y movimiento anual de clientes',1),(105,11594,2,'Validar cuotas pendientes en una vigencia anterior en EDI',1),(106,11594,1,'Validar cuotas pendientes en una vigencia anterior en la búsqueda',1),(107,11594,2,'Validar cuotas pendientes en una vigencia anterior EDI',2),(108,11594,2,'Validar cuotas pendientes en una vigencia anterior CAUT',1),(109,11594,1,'Validar cuota pendiente en una vigencia anterior en la confirmación de la RI',1),(110,11594,2,'Validar cuotas pendientes en una vigencia anterior CAUT',2),(131,11794,2,'Generar reporte de cúmulos',7),(132,11794,3,'Cargar direcciones de cúmulo',1),(133,11794,1,'Cambiar contratante/asegurado',1),(134,11794,1,'Exportar direcciones de cúmulo',1),(135,11794,3,'Generar log de carga de cúmulos',1),(136,11947,3,'Crear reporte Welcome Pack',1),(137,11947,2,'Configurar Servidor de Impresión',1),(138,11947,3,'Modificar Activación de Certificados',1),(139,12101,2,'Cargar la información de obligaciones únicas al Repositorio RO',1),(140,12101,1,'Administrar el listado de las PEP',1),(142,12101,1,'Administrar el listado de Cargos',1),(143,12101,2,'Generar el Reporte de Operaciones (Únicas y Múltiples)',1),(144,12101,1,'Registrar el tipo de cambio promedio',1),(145,12101,1,'Administrar el listado de Ocupaciones',1),(146,12101,2,'Cargar la información de acreencias únicas al Repositorio RO',1),(147,12101,1,'Cargar la información de acreencias múltiples al Repositorio RO',1),(148,12101,1,'Cargar la información de obligaciones múltiples al Repositorio RO',1),(149,12101,1,'Administrar el listado de Oficinas',1),(153,12104,1,'Configurar las frecuencias de liquidación de los intermediarios',1),(154,12104,1,'Carga de frecuencias de pre-liquidación de GW',1),(155,12104,2,'Proceso batch de pre-liquidación',1),(164,115942,2,'Reporte de anulaciones AX',1),(165,115942,2,'Anulación individual AX',1),(166,115942,2,'Proceso Batch GW',2),(167,115942,2,'Reporte de anulaciones GW',2),(168,115942,1,'Proceso Batch AX',1),(169,115942,2,'Anulación individual GW',2),(170,115942,3,'Anulación SBS AX',1),(171,12278,3,'Emitir aviso de cobranza',1),(172,12278,1,'Configurar planes de financiamiento',1),(173,12278,1,'Configurar refinanciamiento de planes',1),(174,12278,3,'Refinanciar aviso de cobranza',1),(175,11950,3,'Distribuir la prima de reaseguro según el método de valor declarado',1),(176,11950,3,'Generar la prima diferida considerando el método de valor declarado',1),(177,11950,1,'Copiar el contrato de reaseguros',1),(178,11950,3,'Configurar el contrato de reaseguros',1),(179,115942,3,'Anulación SBS GW',2),(180,12284,3,'Consultar Servicio Web',6),(181,12278,1,'Calcular cuota inicial en la emisión',1),(182,12278,1,'Calcular cuota inicial en el refinanciamiento',1),(183,1159412,2,'Reporte de anulación AX',1),(184,1159412,1,'Proceso Batch',1),(185,1159412,3,'Anulación SBS',1),(186,1159412,2,'Anulación individual AX',1),(187,1159413,1,'Modificar el proceso de selección automática de cta. cte. facultativos',1),(188,1159413,1,'Modificar la función de exportar a Excel la cta. cte. proporcionales',1),(189,1159413,1,'Crear función de exportar a Excel la cta. cte. facultativos',1),(190,1159413,1,'Modificar la función de exportar a Excel la cta. cte. no proporcionales',1),(191,1159413,2,'Crear la función de importación de Excel de la cta. cte. facultativos',1),(192,1159413,2,'Crear la función de importación de Excel de la cta. cte. proporcionales',1),(193,1159413,2,'Crear la función de importación de Excel de la cta. cte. no proporcionales',1),(194,1159413,1,'Modificar la conciliación cta.cte. facultativos para agregar el número de conciliación parcial',1),(195,1159413,1,'Modificar la conciliación cta.cte. proporcionales para agregar el número de conciliación parcial',1),(196,1159413,1,'Modificar la conciliación cta.cte. no poroporcionales para agregar el número de conciliación parcial',1),(197,1159414,1,'Mantenimiento de la tabla de equivalencias via ticket',1),(198,1159414,2,'Generación del archivo de texto para cargar a SUNAT (EPS)',1),(199,1159414,3,'Selección automática de las obligaciones para generar el lote masivo (EPS)',1),(200,1159414,1,'Selección automática de las obligaciones para generar el lote masivo (PPS)',1),(201,1159414,1,'Generación del archivo de texto para cargar a SUNAT (PPS)',1),(202,1159415,1,'Emitir obligaciones manuales',1),(203,1159415,2,'Procesar el cierre de mes',1),(204,1159415,1,'Emitir acreencias manuales',1),(205,1159415,1,'Emitir ajustes de comisión',1),(206,1159415,1,'Configurar la malla contable',1),(207,1159415,1,'Emitir primas',1);
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `costo_adicional`
--

LOCK TABLES `costo_adicional` WRITE;
/*!40000 ALTER TABLE `costo_adicional` DISABLE KEYS */;
INSERT INTO `costo_adicional` VALUES (2,11544,'Almacenamiento',8796.81,'PEN'),(3,11598,'Cinta de Backup 3 TB',106.24,'PEN'),(4,11598,'Storage',824.45,'PEN'),(5,11598,'Backup',129.08,'PEN'),(6,11598,'Licencias BO (15 Usuarios)',143175,'PEN'),(7,11598,'Enlace dedicado 2 Mbits',7968,'PEN'),(8,11598,'Disco BD',2656,'PEN');
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
  `recursos` float DEFAULT NULL,
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
INSERT INTO `cronograma` VALUES (11192,1,1,NULL,1,15,120),(11192,2,1,0.3,1,35.45,85.08),(11192,3,1,0.2,1,35.45,283.58),(11192,4,1,0.4,2,35.45,567.17),(11192,5,1,0.3,1,53.17,425.38),(11192,6,1,0.1,1,17.72,141.79),(11192,7,1,0.3,1,156.79,376.3),(11312,1,1,NULL,1,7,56),(11312,2,1,0.3,1,10.7,25.67),(11312,3,1,0.2,1,10.69,85.55),(11312,4,1,0.4,1,21.39,171.1),(11312,5,1,0.4,1,21.39,171.1),(11312,6,0,0,0,0,0),(11312,7,1,0.15,1,60.47,72.56),(11544,1,1,NULL,1,23,184),(11544,2,1,0.3,1,100.89,242.14),(11544,3,1,0.2,1.5,100.89,1210.72),(11544,4,1,0.4,1.5,201.79,2421.43),(11544,5,1,0.4,1.5,201.79,2421.43),(11544,6,0,0,0,0,0),(11544,7,1,0.4,1,527.47,1687.9),(11594,1,1,NULL,1,7,56),(11594,2,1,0.3,1,23.42,56.21),(11594,3,1,0.2,1,23.42,187.39),(11594,4,1,0.4,1,46.85,374.77),(11594,5,1,0.3,1,35.14,281.08),(11594,6,1,0.1,1,11.71,93.69),(11594,7,1,0.15,1,124.12,148.94),(11598,1,1,NULL,1,5,40),(11598,2,1,0.3,1,32.5,78),(11598,3,1,0.2,1,32.5,259.97),(11598,4,1,0.5,3,27.08,649.93),(11598,5,1,0.3,2,24.37,389.96),(11598,6,0,0,0,0,0),(11598,7,1,0.3,1,88.95,213.48),(11659,1,1,NULL,1,10,80),(11659,2,1,0.3,1,11.5,27.6),(11659,3,1,0.2,1,11.5,92.01),(11659,4,1,0.4,2,11.5,184.02),(11659,5,1,0.4,1.5,15.34,184.02),(11659,6,0,0,0,0,0),(11659,7,1,0.2,1,48.34,77.34),(11794,1,1,NULL,1,10,80),(11794,2,1,0.3,1,18.03,43.27),(11794,3,1,0.2,1,18.03,144.23),(11794,4,1,0.4,1,36.06,288.46),(11794,5,1,0.4,1,36.06,288.46),(11794,6,0,0,0,0,0),(11794,7,1,0.2,1,100.15,160.24),(11947,1,1,NULL,1,5,40),(11947,2,1,0.3,1,12.2,29.28),(11947,3,1,0.2,1,12.2,97.56),(11947,4,1,0.45,1,27.44,219.51),(11947,5,1,0.35,1,21.34,170.73),(11947,6,0,0,0,0,0),(11947,7,1,0.15,1,65.98,79.18),(11950,1,1,NULL,1,18,144),(11950,2,1,0.3,1,15.84,38.02),(11950,3,1,0.2,1.5,15.84,190.03),(11950,4,1,0.4,1.5,31.67,380.06),(11950,5,1,0.4,1.5,31.67,380.06),(11950,6,0,0,0,0,0),(11950,7,1,0.3,1,97.18,233.23),(12101,1,1,NULL,1,10,80),(12101,2,1,0.3,1,20.12,48.29),(12101,3,1,0.2,1,20.12,160.93),(12101,4,1,0.4,1,40.23,321.86),(12101,5,1,0.4,1,40.23,321.86),(12101,6,0,0,0,0,0),(12101,7,1,0.2,1,110.58,176.93),(12104,1,1,NULL,1,NULL,NULL),(12104,2,1,0.3,1,11.64,27.94),(12104,3,1,0.2,1,11.64,93.15),(12104,4,1,0.4,1,23.29,186.3),(12104,5,1,0.3,1,17.47,139.72),(12104,6,1,0.1,1,5.82,46.57),(12104,7,1,0.15,1,58.22,69.86),(12278,1,1,NULL,1,10,80),(12278,2,1,0.3,1,13.71,32.9),(12278,3,1,0.2,1,13.71,109.66),(12278,4,1,0.3,1,20.56,164.5),(12278,5,1,0.5,1,34.27,274.16),(12278,6,0,0,0,0,0),(12278,7,1,0.2,1,78.54,125.66),(12284,1,1,NULL,1,8,64),(12284,2,1,0.3,1,6.95,16.68),(12284,3,1,0.2,1,6.95,55.63),(12284,4,1,0.4,1,13.91,111.25),(12284,5,1,0.3,1,10.43,83.44),(12284,6,1,0.1,1,3.48,27.81),(12284,7,1,0.15,1,57.77,69.32),(12284,8,1,NULL,1,15,0),(115942,1,1,NULL,1,18,144),(115942,2,1,0.3,1,45.21,108.5),(115942,3,1,0.2,1,45.21,361.71),(115942,4,1,0.4,1,90.43,723.42),(115942,5,1,0.4,1,90.43,723.42),(115942,6,0,0,0,0,0),(115942,7,1,0.3,1,244.07,585.77),(1159412,1,1,NULL,1,NULL,NULL),(1159412,2,1,0.3,1,23.83,57.19),(1159412,3,1,0.2,1,23.83,190.67),(1159412,4,1,0.4,1,47.67,381.33),(1159412,5,1,0.3,1,35.75,286),(1159412,6,1,0.1,1,11.92,95.33),(1159412,7,1,0.15,1,119.17,143),(1159412,8,1,NULL,1,NULL,NULL),(1159413,1,1,NULL,1,10,80),(1159413,2,1,0.3,1,17.38,41.71),(1159413,3,1,0.2,1,17.38,139.02),(1159413,4,1,0.4,1,34.76,278.04),(1159413,5,1,0.3,1,26.07,208.53),(1159413,6,1,0.1,1,8.69,69.51),(1159413,7,1,0.15,1,111.9,134.28),(1159413,8,1,NULL,1,15,0),(1159414,1,1,NULL,1,10,80),(1159414,2,1,0.3,1,13.97,33.53),(1159414,3,1,0.2,1,13.97,111.72),(1159414,4,1,0.4,1,27.93,223.45),(1159414,5,1,0.4,1,27.93,223.45),(1159414,6,0,0,0,0,0),(1159414,7,1,0.15,1,79.83,95.8),(1159414,8,1,NULL,1,NULL,NULL),(1159415,1,1,NULL,1,10,80),(1159415,2,1,0.3,1,17.2,41.28),(1159415,3,1,0.2,1,17.2,137.63),(1159415,4,1,0.3,1,25.81,206.44),(1159415,5,1,0.5,1,43.01,344.07),(1159415,6,0,0,0,0,0),(1159415,7,1,0.2,1,186.02,297.63),(1159415,8,1,NULL,1,90,0);
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
  `nombre` varchar(512) DEFAULT NULL,
  `eds` varchar(50) NOT NULL,
  `fechacalculo` datetime DEFAULT NULL,
  `puntos` float DEFAULT NULL,
  `esfuerzo` float DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `idusuario` int(11) NOT NULL,
  `numestimacion` int(11) DEFAULT NULL,
  `numfase` int(11) DEFAULT NULL,
  PRIMARY KEY (`idestimacion`),
  UNIQUE KEY `numero` (`numestimacion`,`numfase`),
  KEY `fk_estimacion_usuario1_idx` (`idusuario`),
  CONSTRAINT `fk_estimacion_usuario1` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1159416 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estimacion`
--

LOCK TABLES `estimacion` WRITE;
/*!40000 ALTER TABLE `estimacion` DISABLE KEYS */;
INSERT INTO `estimacion` VALUES (11192,'Pagos en línea a través del BCP','Angela Valencia','2016-08-13 15:06:04',57.96,1623,122,5,11192,1),(11312,'Nuevo Reporte de Impresión para Cascos','Kenji Dettleff','2016-06-21 15:54:25',17.1,478.71,7,3,11312,1),(11544,'Nueva RRC y Prima Diferida','Kenji Dettleff','2016-09-12 12:01:33',217.34,6237.58,273,3,11544,1),(11594,'Mejoras de anulación de polizas - Fase 1','Angela Valencia','2016-09-21 10:44:30',34.55,992.93,164,5,11594,1),(11598,'Base de Datos Incidentes','Hernán George','2016-09-13 10:44:08',45.68,1278.96,5,6,11598,1),(11659,'Riesgo Clientes LAFT','Miguel Payet','2016-09-20 10:58:06',19.29,540.05,6,1,11659,1),(11794,'Mejorar el proceso de ingreso de direcciones de riesgo','Kenji Dettleff','2016-09-22 12:37:09',28.61,801.15,113,3,11794,1),(11947,'Emisión Masiva de Productos Colectivos','Miguel Payet','2016-10-06 17:44:09',18.85,527.8,68,1,11947,1),(11950,'Recalcular monto de prima cedida para terremoto','Miguel Payet','2016-11-29 10:39:33',39.08,1094.15,3,1,11950,1),(12101,'Modificar el Reporte de Operaciones por Nueva Regulación','Kenji Dettleff','2016-11-09 11:46:48',31.59,884.66,112,3,12101,1),(12104,'Preliquidación en Batch ACSEL/X','Miguel Payet','2016-12-20 22:01:46',16.63,465.74,2,1,12104,1),(12278,'Modificar Cálculo TCEA - Tasa Diaria','Kenji Dettleff','2016-12-07 10:39:14',24.64,689.92,105,3,12278,1),(12284,'Implementar el servicio de consulta de siniestros de salud (EPS/AMED)','Hernán George','2016-12-12 15:42:57',12.22,342.13,4,6,12284,1),(115942,'Mejora en la anulación de polizas','Angela Valencia','2016-11-28 14:48:29',67.36,1952.56,35,5,11954,1),(1159412,'Mejora en la anulación de polizas AX - Fase II','Angela Valencia','2016-12-13 15:57:40',34.05,953.33,1,5,11954,2),(1159413,'Mejorar el proceso de conciliación de cta. cte.','Miguel Payet','2017-01-16 10:35:08',27.68,775.1,2,1,12421,1),(1159414,'Optimizar el proceso de gen. y pago de detracción','Miguel Payet','2017-01-27 10:01:10',22.81,638.62,14,1,12577,1),(1159415,'Modificar el Plan de Cuentas para Comisiones','Kenji Dettleff','2017-02-17 09:14:24',27.43,768.14,3,3,12724,1);
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
INSERT INTO `factor` VALUES (1,2,'Sistema Distribuido','¿El sistema tiene una arquitectura centralizada o distribuida? [0=Mayormente centralizada, 5=Mayormente distribuida]',2,0,5),(2,2,'Desempeño, Tiempo de Respuesta','¿Es el tiempo de respuesta un criterio importante? [0=Menor, 5=Mayor]',1,0,5),(3,2,'Eficiencia de Usuario Final','¿La aplicación se debe diseñar para incrementar la eficiencia del usuario final? [0=Menor, 5=Mayor]',1,0,5),(4,2,'Grado de Complejidad del Procesamiento Interno','¿La aplicación tiene lógica elaborada o procesamiento matemático? [0=Menor, 5=Mayor]',1,0,5),(5,2,'Reusabilidad del Código','¿Se requiere cuidar la reusabilidad? [0=Menor, 5=Mayor]',1,0,5),(6,2,'Facilidad de Instalación','¿El cliente está buscando facilidad en la instalación? [0=Menor, 5=Mayor]',0.5,0,5),(7,2,'Facilidad de Uso','Facilidad de Uso [0=Menor, 5=Mayor]',0.5,0,5),(8,2,'Portabilidad','¿Se busca una solución que soporte diversas plataformas tecnológicas? [0=Menor, 5=Mayor]',2,0,5),(9,2,'Facilidad al Cambio','¿Se busca una alta capacidad de adecuación en el futuro? [0=Menor, 5=Mayor]',1,0,5),(10,2,'Concurrencia / procesamiento paralelo','¿Se busca que una gran cantidad de usuarios trabajen bajo características de bloqueo a secciones críticas de datos? [0=Menor, 5=Mayor]',1,0,5),(11,2,'Características de Seguridad','¿Se busca tener mecanismos de seguridad? [0=Menor, 5=Mayor]',1,0,5),(12,2,'Proporciona Acceso Directo a Software de Terceros','¿El proyecto depende del uso de software de terceros? [0=Menor, 5=Mayor]',1,0,5),(13,2,'Capacitación al Usuario','Complejidad y dificultad de la capacitación requerida hacia los usuarios sobre el sistema o la aplicación.  [0=Menor, 5=Mayor]',1,0,5),(14,1,'Familiaridad con el modelo del proyecto utilizado','Conocimiento de la metodología de proyecto y herramientas asociadas. [0=Menor, 5=Mayor]',1.5,0,5),(15,1,'Experiencia en la Aplicación','Conocimiento de las aplicaciones del negocio relacionadas al proyecto. [0=Menor, 5=Mayor]',0.5,0,5),(16,1,'Experiencia en Orientación a Objetos','Conocimiento del paradigma y modelo de programación de orientación a objectos. [0=Menor, 5=Mayor]',1,0,5),(17,1,'Capacidades del Líder Técnico','Capacidad de gestión de recursos y experiencia del líder técnico (JP) del proyecto. [0=Menor, 5=Mayor]',0.5,0,5),(18,1,'Motivación','Motivación, capacidad de auto-gestión y eficiencia del equipo de trabajo. [0=Menor, 5=Mayor]',1,0,5),(19,1,'Requerimientos Estables','Probabilidad de que no varíe el alcance de los requerimientos [0=Menor, 5=Mayor]',2,0,5),(20,1,'Desarrolladores de Tiempo Parcial','¿Hay desarrolladores de tiempo parcial? [0=Menor, 5=Mayor]',-1,0,5),(21,1,'Dificultad del Lenguaje de Programación','Nivel de dificultad de los lenguajes de programación usados. [0=Menor, 5=Mayor]',-1,0,5);
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
) ENGINE=InnoDB AUTO_INCREMENT=114066 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `factor_estimacion`
--

LOCK TABLES `factor_estimacion` WRITE;
/*!40000 ALTER TABLE `factor_estimacion` DISABLE KEYS */;
INSERT INTO `factor_estimacion` VALUES (113646,11192,3,1),(113647,11192,5,4),(113648,11192,10,5),(113649,11192,15,5),(113650,11192,2,4),(113651,11192,18,4),(113652,11192,12,2),(113653,11192,16,5),(113654,11192,21,4),(113655,11192,9,1),(113656,11192,13,4),(113657,11192,7,5),(113658,11192,8,0),(113659,11192,19,4),(113660,11192,14,3),(113661,11192,6,0),(113662,11192,11,5),(113663,11192,17,5),(113664,11192,20,0),(113665,11192,1,5),(113666,11192,4,4),(113688,11312,16,5),(113689,11312,17,3),(113690,11312,20,0),(113691,11312,6,1),(113692,11312,13,1),(113693,11312,18,1),(113694,11312,15,5),(113695,11312,2,2),(113696,11312,5,3),(113697,11312,1,3),(113698,11312,8,0),(113699,11312,21,2),(113700,11312,4,5),(113701,11312,7,0),(113702,11312,19,0),(113703,11312,12,1),(113704,11312,14,5),(113705,11312,10,1),(113706,11312,11,0),(113707,11312,3,0),(113708,11312,9,0),(113709,11544,16,4),(113710,11544,2,5),(113711,11544,10,2),(113712,11544,12,0),(113713,11544,4,5),(113714,11544,7,4),(113715,11544,11,4),(113716,11544,20,0),(113717,11544,5,5),(113718,11544,9,4),(113719,11544,6,0),(113720,11544,8,0),(113721,11544,18,3),(113722,11544,1,5),(113723,11544,13,5),(113724,11544,19,2),(113725,11544,15,3),(113726,11544,21,3),(113727,11544,3,2),(113728,11544,14,5),(113729,11544,17,2),(113730,11598,4,5),(113731,11598,12,5),(113732,11598,19,3),(113733,11598,2,3),(113734,11598,3,0),(113735,11598,7,2),(113736,11598,8,5),(113737,11598,14,4),(113738,11598,21,3),(113739,11598,18,3),(113740,11598,1,5),(113741,11598,6,3),(113742,11598,10,0),(113743,11598,17,4),(113744,11598,20,4),(113745,11598,15,4),(113746,11598,13,0),(113747,11598,16,1),(113748,11598,11,1),(113749,11598,5,2),(113750,11598,9,2),(113751,11659,3,0),(113752,11659,7,5),(113753,11659,18,3),(113754,11659,15,5),(113755,11659,19,4),(113756,11659,6,5),(113757,11659,8,0),(113758,11659,9,5),(113759,11659,1,0),(113760,11659,13,3),(113761,11659,11,2),(113762,11659,5,3),(113763,11659,10,3),(113764,11659,16,3),(113765,11659,17,3),(113766,11659,4,5),(113767,11659,2,5),(113768,11659,21,5),(113769,11659,14,5),(113770,11659,12,0),(113771,11659,20,0),(113772,11594,11,3),(113773,11594,17,1),(113774,11594,21,1),(113775,11594,7,1),(113776,11594,16,2),(113777,11594,19,5),(113778,11594,12,0),(113779,11594,2,2),(113780,11594,10,3),(113781,11594,18,4),(113782,11594,4,1),(113783,11594,20,1),(113784,11594,6,2),(113785,11594,9,2),(113786,11594,15,3),(113787,11594,5,1),(113788,11594,3,1),(113789,11594,1,2),(113790,11594,8,1),(113791,11594,14,3),(113792,11594,13,1),(113793,11794,13,4),(113794,11794,17,4),(113795,11794,11,0),(113796,11794,12,0),(113797,11794,1,4),(113798,11794,5,3),(113799,11794,7,5),(113800,11794,14,5),(113801,11794,3,5),(113802,11794,18,3),(113803,11794,4,2),(113804,11794,8,0),(113805,11794,16,4),(113806,11794,6,0),(113807,11794,9,4),(113808,11794,19,4),(113809,11794,20,0),(113810,11794,2,3),(113811,11794,10,0),(113812,11794,15,5),(113813,11794,21,0),(113814,11947,4,0),(113815,11947,7,0),(113816,11947,13,1),(113817,11947,12,0),(113818,11947,17,3),(113819,11947,18,3),(113820,11947,3,0),(113821,11947,6,0),(113822,11947,14,5),(113823,11947,8,0),(113824,11947,10,0),(113825,11947,19,5),(113826,11947,9,0),(113827,11947,15,4),(113828,11947,11,0),(113829,11947,1,0),(113830,11947,5,0),(113831,11947,2,2),(113832,11947,20,0),(113833,11947,21,4),(113834,11947,16,4),(113835,12101,16,5),(113836,12101,12,0),(113837,12101,13,3),(113838,12101,11,0),(113839,12101,20,0),(113840,12101,6,0),(113841,12101,18,3),(113842,12101,7,0),(113843,12101,17,4),(113844,12101,1,5),(113845,12101,14,5),(113846,12101,8,0),(113847,12101,9,5),(113848,12101,21,0),(113849,12101,2,3),(113850,12101,4,3),(113851,12101,15,5),(113852,12101,5,5),(113853,12101,10,0),(113854,12101,3,0),(113855,12101,19,5),(113856,12104,12,0),(113857,12104,11,1),(113858,12104,1,0),(113859,12104,8,0),(113860,12104,10,0),(113861,12104,16,3),(113862,12104,2,5),(113863,12104,13,3),(113864,12104,6,0),(113865,12104,7,3),(113866,12104,3,2),(113867,12104,15,3),(113868,12104,17,3),(113869,12104,18,3),(113870,12104,19,3),(113871,12104,14,4),(113872,12104,5,5),(113873,12104,9,2),(113874,12104,4,4),(113875,12104,21,2),(113876,12104,20,0),(113898,115942,4,5),(113899,115942,10,4),(113900,115942,3,5),(113901,115942,14,5),(113902,115942,9,5),(113903,115942,1,5),(113904,115942,12,0),(113905,115942,15,5),(113906,115942,13,5),(113907,115942,7,5),(113908,115942,2,5),(113909,115942,21,1),(113910,115942,5,5),(113911,115942,18,1),(113912,115942,16,5),(113913,115942,20,0),(113914,115942,8,0),(113915,115942,11,0),(113916,115942,17,5),(113917,115942,19,3),(113918,115942,6,0),(113919,12278,13,3),(113920,12278,18,3),(113921,12278,9,5),(113922,12278,10,0),(113923,12278,16,5),(113924,12278,3,0),(113925,12278,15,5),(113926,12278,5,5),(113927,12278,12,0),(113928,12278,8,0),(113929,12278,14,5),(113930,12278,1,5),(113931,12278,17,4),(113932,12278,21,0),(113933,12278,2,3),(113934,12278,19,5),(113935,12278,7,0),(113936,12278,20,0),(113937,12278,4,2),(113938,12278,11,0),(113939,12278,6,0),(113940,11950,7,0),(113941,11950,12,0),(113942,11950,16,3),(113943,11950,19,4),(113944,11950,21,4),(113945,11950,6,0),(113946,11950,3,0),(113947,11950,13,3),(113948,11950,8,0),(113949,11950,1,0),(113950,11950,2,3),(113951,11950,10,0),(113952,11950,11,0),(113953,11950,5,3),(113954,11950,14,5),(113955,11950,17,0),(113956,11950,15,0),(113957,11950,20,0),(113958,11950,4,5),(113959,11950,9,3),(113960,11950,18,3),(113961,12284,9,2),(113962,12284,15,3),(113963,12284,16,5),(113964,12284,18,5),(113965,12284,4,1),(113966,12284,17,3),(113967,12284,21,3),(113968,12284,11,1),(113969,12284,6,1),(113970,12284,3,1),(113971,12284,20,0),(113972,12284,10,3),(113973,12284,2,5),(113974,12284,5,3),(113975,12284,13,0),(113976,12284,14,5),(113977,12284,12,0),(113978,12284,8,0),(113979,12284,7,1),(113980,12284,1,4),(113981,12284,19,5),(113982,1159412,6,5),(113983,1159412,7,0),(113984,1159412,16,5),(113985,1159412,17,5),(113986,1159412,1,5),(113987,1159412,12,0),(113988,1159412,14,5),(113989,1159412,11,0),(113990,1159412,19,3),(113991,1159412,4,5),(113992,1159412,3,5),(113993,1159412,5,5),(113994,1159412,18,1),(113995,1159412,2,5),(113996,1159412,20,0),(113997,1159412,21,1),(113998,1159412,9,5),(113999,1159412,15,5),(114000,1159412,10,4),(114001,1159412,8,0),(114002,1159412,13,5),(114003,1159413,19,5),(114004,1159413,8,0),(114005,1159413,21,1),(114006,1159413,2,2),(114007,1159413,6,0),(114008,1159413,14,5),(114009,1159413,4,2),(114010,1159413,1,0),(114011,1159413,18,3),(114012,1159413,17,3),(114013,1159413,11,0),(114014,1159413,13,2),(114015,1159413,12,0),(114016,1159413,9,0),(114017,1159413,16,4),(114018,1159413,3,3),(114019,1159413,5,0),(114020,1159413,7,0),(114021,1159413,10,0),(114022,1159413,15,4),(114023,1159413,20,0),(114024,1159414,10,0),(114025,1159414,21,3),(114026,1159414,7,1),(114027,1159414,16,5),(114028,1159414,18,3),(114029,1159414,20,0),(114030,1159414,5,1),(114031,1159414,19,4),(114032,1159414,6,0),(114033,1159414,4,3),(114034,1159414,15,3),(114035,1159414,14,4),(114036,1159414,11,0),(114037,1159414,17,4),(114038,1159414,2,3),(114039,1159414,12,0),(114040,1159414,1,0),(114041,1159414,3,2),(114042,1159414,9,1),(114043,1159414,8,0),(114044,1159414,13,1),(114045,1159415,12,0),(114046,1159415,10,0),(114047,1159415,7,0),(114048,1159415,14,5),(114049,1159415,11,0),(114050,1159415,2,0),(114051,1159415,3,3),(114052,1159415,15,5),(114053,1159415,17,3),(114054,1159415,21,0),(114055,1159415,5,5),(114056,1159415,9,3),(114057,1159415,19,2),(114058,1159415,16,5),(114059,1159415,18,2),(114060,1159415,1,5),(114061,1159415,4,2),(114062,1159415,6,0),(114063,1159415,13,3),(114064,1159415,8,0),(114065,1159415,20,0);
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plataforma`
--

LOCK TABLES `plataforma` WRITE;
/*!40000 ALTER TABLE `plataforma` DISABLE KEYS */;
INSERT INTO `plataforma` VALUES (1,'Acsel/X',28),(2,'GuideWire',30),(3,'Apps Lotus Notes',28),(4,'Novasys',28),(5,'Apps .NET',28),(6,'Apps Java',28),(7,'Business Objects',28),(8,'BPM',28),(9,'HP Exstream',28),(10,'MIC',28);
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
  `rol` int(1) DEFAULT NULL,
  PRIMARY KEY (`idtarea`),
  KEY `fk_tarea_proveedor1_idx` (`idproveedor`),
  CONSTRAINT `fk_tarea_proveedor1` FOREIGN KEY (`idproveedor`) REFERENCES `proveedor` (`idproveedor`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tarea`
--

LOCK TABLES `tarea` WRITE;
/*!40000 ALTER TABLE `tarea` DISABLE KEYS */;
INSERT INTO `tarea` VALUES (1,2,'Definición Funcional',1,1,NULL,2,0),(2,2,'Acompañamiento',1,2,0.3,3,2),(3,2,'Diseño Técnico',1,3,0.2,1,1),(4,2,'Construcción',1,4,0.4,1,0),(5,3,'Certificación',1,5,0.3,1,0),(6,3,'Pruebas de Stress',1,6,0.1,1,0),(7,2,'Gestión',1,8,0.15,3,3),(8,2,'Seguimiento',1,7,NULL,5,4);
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tokenpassword`
--

LOCK TABLES `tokenpassword` WRITE;
/*!40000 ALTER TABLE `tokenpassword` DISABLE KEYS */;
INSERT INTO `tokenpassword` VALUES (1,'2016-08-29 08:20:49','U','4ceaf609fb1301519631f63d50cdf745081a0db79ba553899e4ec1c0e702cad8','kenji',0),(2,'2017-01-11 14:47:59','U','0942623e887428a92d0197d386a6dd69f1ec335897b1bb1dc46e20270bcd25d4','miguel',1),(3,'2017-01-11 14:59:19','U','d8ff4a656c306ea4711937014e1c94960d51ff6a6a43a797b4ce8998c76f659f','Lennie',1),(4,'2016-09-12 17:45:06','E','a6e999d22894ab38f0161969480a0e9278de76e5c27044d8f7657383e82d594e','hgeorge@pacifico.com.pe',0),(5,'2016-09-13 10:03:51','U','c84694da25db427c20494c0deb5740d1f85f832bead271bb0ccb91a7d6b02bea','hernán',0),(6,'2017-02-09 16:31:51','U','85ab8bfbc509366afe5b3e0429e83aec1cc1b49ecafde77cb6a85687bcc12b0f','angela',0);
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'miguel','miguel','payet','mpayet@pacifico.com.pe','y2KWTVLZYcYPfgsetbjFMW1mEqjLVSB1ScqrNi/tKIw=','ytF97Ni6m/0assubfhsObg=='),(3,'kenji','kenji','dettleff','kdettleff@pacifico.com.pe','hyK6Cpcw7PEZLLmPIt/ocLYvFfjMPww1RWj2nrxxTbg=','MBkOje188aEROEt4XyFRFg=='),(4,'diego','diego','ccallo','dccallo@pacifico.com.pe',NULL,NULL),(5,'angela','angela','valencia','arvalencia@pacifico.com.pe','+I2tGYv1LqUrhL4rU6RWligc1IDFEajJN4B3PZU4XPY=','zVhRQelFqfN8SFS+gjdEog=='),(6,'hernan','hernán','george','hgeorge@pacifico.com.pe','RSJGXANDYBhv0OtwnuGNdsnSW0G4uGL0TQx8qyIB7oo=','WS3nU1ggruNF2mqrjSxgkQ=='),(7,'edson','edson','jimenez','ejimenez@pacifico.com.pe',NULL,NULL),(8,'luis','luis','chileno','luis.chileno.quico@accenture.com.pe',NULL,NULL),(9,'samuel','samuel','castillo','JosCastillo@pacifico.com.pe',NULL,NULL),(10,'lennie','lennie','valencia','lvalencia@pacifico.com.pe','YGq1EVJH0P6TdOweOxSvnFakQXX8nibgZAxjTuYNDuI=','ar/FpAjr51cC9BZul+kOBg==');
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
INSERT INTO `usuario_rol` VALUES (1,1),(3,1),(4,1),(5,1),(6,1),(7,1),(8,1),(9,1),(10,1);
/*!40000 ALTER TABLE `usuario_rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `visita`
--

DROP TABLE IF EXISTS `visita`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `visita`
--

LOCK TABLES `visita` WRITE;
/*!40000 ALTER TABLE `visita` DISABLE KEYS */;
/*!40000 ALTER TABLE `visita` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-02-27 19:29:05
