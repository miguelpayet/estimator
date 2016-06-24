-- MySQL Workbench Synchronization
-- Generated: 2016-06-23 13:25
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: mpayet

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE TABLE IF NOT EXISTS `estimator`.`usuario` (
  `idusuario` INT(11) NOT NULL,
  `apellidos` VARCHAR(45) NULL DEFAULT NULL,
  `nombre` VARCHAR(45) NULL DEFAULT NULL,
  `codigo` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `password` VARCHAR(512) NULL DEFAULT NULL,
  `salt` VARCHAR(512) NULL DEFAULT NULL,
  PRIMARY KEY (`idusuario`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `estimator`.`rol` (
  `idrol` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idrol`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `estimator`.`tokenpassword` (
  `idtoken` INT(11) NOT NULL AUTO_INCREMENT,
  `creacion` DATETIME NULL DEFAULT NULL,
  `tipo` VARCHAR(45) NULL DEFAULT NULL,
  `token` VARCHAR(512) NULL DEFAULT NULL,
  `usuario` VARCHAR(45) NULL DEFAULT NULL,
  `valido` TINYINT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`idtoken`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `estimator`.`usuario_rol` (
  `idrol` INT(11) NOT NULL,
  `idusuario` INT(11) NOT NULL,
  PRIMARY KEY (`idrol`, `idusuario`),
  INDEX `fk_rol_has_usuario_usuario1_idx` (`idusuario` ASC),
  INDEX `fk_rol_has_usuario_rol1_idx` (`idrol` ASC),
  CONSTRAINT `fk_rol_has_usuario_rol1`
    FOREIGN KEY (`idrol`)
    REFERENCES `estimator`.`rol` (`idrol`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_rol_has_usuario_usuario1`
    FOREIGN KEY (`idusuario`)
    REFERENCES `estimator`.`usuario` (`idusuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- MySQL Workbench Synchronization
-- Generated: 2016-06-23 13:29
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: mpayet

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

ALTER TABLE `mydb`.`usuario`
CHANGE COLUMN `idusuario` `idusuario` INT(11) NOT NULL AUTO_INCREMENT ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


CREATE TABLE IF NOT EXISTS `mydb`.`costo_adicional` (
  `idcosto` INT NOT NULL AUTO_INCREMENT,
  `idestimacion` INT(6) NOT NULL,
  `descripcion` VARCHAR(500) NULL,
  `costo` DOUBLE NULL,
  PRIMARY KEY (`idcosto`),
  INDEX `fk_costo_adicional_estimacion2_idx` (`idestimacion` ASC),
  CONSTRAINT `fk_costo_adicional_estimacion2`
    FOREIGN KEY (`idestimacion`)
    REFERENCES `mydb`.`estimacion` (`idestimacion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB


update factor set nombre='Facilidad de Uso', peso = 0.5, descripcion = 'Facilidad de Uso' where idfactor=7;
update factor set nombre='Portabilidad', peso = 2, descripcion = '¿Se busca una solución que soporte diversas plataformas tecnológicas?' where idfactor=8;
update factor set nombre='Facilidad al Cambio', peso = 1, descripcion = '¿Se busca una alta capacidad de adecuación en el futuro?' where idfactor=9;
update factor set nombre='Concurrencia / procesamiento paralelo', peso = 1, descripcion = '¿Se busca que una gran cantidad de usuarios trabajen bajo características de bloqueo a secciones críticas de datos?' where idfactor=10;
update factor set nombre='Características de Seguridad', peso = 1, descripcion = '¿Se busca tener mecanismos de seguridad?' where idfactor=11;
update factor set nombre='Proporciona Acceso Directo a Software de Terceros', peso = 1, descripcion = '¿El proyecto depende del uso de software de terceros?' where idfactor=12;
update factor set nombre='Capacitación al Usuario', peso = 1, descripcion = '¿Qué necesidades y facilidades de capacitación hacia los usuarios se requieren sobre el sistema o la aplicación?' where idfactor=13;

update factor set nombre = 'Dificultad del Lenguaje de Programación', peso = -1 where idfactor = 21;

update factor set descripcion = 'Complejidad y dificultad de la capacitación requerida hacia los usuarios sobre el sistema o la aplicación.' where idfactor=13;
update factor set descripcion = 'Conocimiento de la metodología de proyecto y herramientas asociadas.' where idfactor = 14;
update factor set descripcion = 'Conocimiento de las aplicaciones del negocio relacionadas al proyecto.' where idfactor = 15;
update factor set descripcion = 'Conocimiento del paradigma y modelo de programación de orientación a objectos.' where idfactor = 16;
update factor set descripcion = 'Capacidad de gestión de recursos y experiencia del líder técnico (JP) del proyecto.' where idfactor = 17;
update factor set descripcion = 'Motivación, capacidad de auto-gestión y eficiencia del equipo de trabajo' where idfactor = 18;
update factor set descripcion = 'Probabilidad de variación en el alcance de los requerimientos' where idfactor = 19;
update factor set descripcion = '¿Hay desarrolladores de tiempo parcial?' where idfactor = 20;
update factor set descripcion = 'Nivel de dificultad de los lenguajes de programación usados.' where idfactor = 21;
