-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema db_orderit
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema db_orderit
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `db_orderit` DEFAULT CHARACTER SET utf8 ;
USE `db_orderit` ;

-- -----------------------------------------------------
-- Table `db_orderit`.`Usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_orderit`.`Usuarios` (
  `idUsuario` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nombreUsuario` VARCHAR(45) NOT NULL,
  `apPaterno` VARCHAR(45) NOT NULL,
  `apMaterno` VARCHAR(45) NOT NULL,
  `email` VARCHAR(65) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idUsuario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_orderit`.`Restaurante`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_orderit`.`Restaurante` (
  `idRestaurante` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nombreRest` VARCHAR(100) NOT NULL,
  `direccion` VARCHAR(65) NOT NULL,
  `horario` VARCHAR(150) NOT NULL,
  `tiempoEstimado` VARCHAR(45) NOT NULL,
  `costoEntrega` INT NOT NULL,
  PRIMARY KEY (`idRestaurante`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_orderit`.`Platillos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_orderit`.`Platillos` (
  `idPlatillos` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nombrePlatillo` VARCHAR(150) NOT NULL,
  `precio` DOUBLE NOT NULL,
  `Restaurante_idRestaurante` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`idPlatillos`),
  INDEX `fk_Platillos_Restaurante_idx` (`Restaurante_idRestaurante` ASC),
  CONSTRAINT `fk_Platillos_Restaurante`
    FOREIGN KEY (`Restaurante_idRestaurante`)
    REFERENCES `db_orderit`.`Restaurante` (`idRestaurante`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_orderit`.`Pedidos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_orderit`.`Pedidos` (
  `idPedidos` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `fecha` DATE NOT NULL,
  `cantidad` INT NOT NULL,
  `total` DOUBLE NOT NULL,
  `Platillos_idPlatillos` INT UNSIGNED NOT NULL,
  `Usuarios_idUsuario` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`idPedidos`),
  INDEX `fk_Pedidos_Platillos1_idx` (`Platillos_idPlatillos` ASC),
  INDEX `fk_Pedidos_Usuarios1_idx` (`Usuarios_idUsuario` ASC),
  CONSTRAINT `fk_Pedidos_Platillos1`
    FOREIGN KEY (`Platillos_idPlatillos`)
    REFERENCES `db_orderit`.`Platillos` (`idPlatillos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Pedidos_Usuarios1`
    FOREIGN KEY (`Usuarios_idUsuario`)
    REFERENCES `db_orderit`.`Usuarios` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
