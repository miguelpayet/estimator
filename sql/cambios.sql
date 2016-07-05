-- MySQL Workbench Synchronization
-- Generated: 2016-07-05 16:45
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: mpayet

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

ALTER TABLE `mydb`.`estimacion`
ADD COLUMN `idusuario` INT(11) NOT NULL AFTER `version`,
ADD INDEX `fk_estimacion_usuario1_idx` (`idusuario` ASC);

ALTER TABLE `mydb`.`estimacion`
ADD CONSTRAINT `fk_estimacion_usuario1`
  FOREIGN KEY (`idusuario`)
  REFERENCES `mydb`.`usuario` (`idusuario`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
