-- MySQL Workbench Synchronization
-- Generated: 2016-07-13 11:57
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: mpayet

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

ALTER TABLE `mydb`.`costo_adicional`
DROP FOREIGN KEY `fk_costo_adicional_estimacion1`;

ALTER TABLE `mydb`.`costo_adicional`
CHANGE COLUMN `descripcion` `descripcion` VARCHAR(500) NULL DEFAULT NULL ,
CHANGE COLUMN `costo` `costo` DOUBLE NULL DEFAULT NULL ,
ADD COLUMN `moneda` VARCHAR(3) NULL DEFAULT NULL AFTER `costo`,
ADD INDEX `fk_costo_adicional_estimacion2_idx` (`idestimacion` ASC),
DROP INDEX `fk_costo_adicional_estimacion1_idx` ;

ALTER TABLE `mydb`.`costo_adicional`
ADD CONSTRAINT `fk_costo_adicional_estimacion2`
  FOREIGN KEY (`idestimacion`)
  REFERENCES `mydb`.`estimacion` (`idestimacion`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
