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
  `direccion` VARCHAR(150) NOT NULL,
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
  `fecha` VARCHAR(80) NOT NULL,
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

INSERT INTO `restaurante` (`idRestaurante`, `nombreRest`, `direccion`, `horario`, `tiempoEstimado`, `costoEntrega`) VALUES ('1', 'El Tejaban', 'María Rodríguez Del Toro De Lazarín 6-D, Bocanegra, Morelia, Michoacán 58150', 'Lunes a Domingo de 9:00 am a 8:00 pm', '55-65 min', '25');
INSERT INTO `restaurante` (`idRestaurante`, `nombreRest`, `direccion`, `horario`, `tiempoEstimado`, `costoEntrega`) VALUES ('2', 'Hamburguesas Mi Barrio', 'Martin Castrejon 684, Morelia, MIC 58040', 'Lunes a Domingo de 12:00 pm a 11:30 pm', '50-60 min', '34');
INSERT INTO `restaurante` (`idRestaurante`, `nombreRest`, `direccion`, `horario`, `tiempoEstimado`, `costoEntrega`) VALUES ('3', 'Loncheria La Esperanza', 'Calle Plan De Ayala 51, Morelia, 58000, Michoacán De Ocampo, Mx, Michoacán de Ocampo 58000', 'Lunes a Domingo de 9:00 am a 8:00 pm', '40-50 min', '21');
INSERT INTO `restaurante` (`idRestaurante`, `nombreRest`, `direccion`, `horario`, `tiempoEstimado`, `costoEntrega`) VALUES ('4', 'Tortas y Tacos Ahogados Guadalajarra', 'Valentín Gómez Farías Enfrente Del Mercado De Dulces, Morelia, MIC 58000', 'Lunes a Domingo de 9:30 am a 5:00 pm', '55-65 min', '24');
INSERT INTO `restaurante` (`idRestaurante`, `nombreRest`, `direccion`, `horario`, `tiempoEstimado`, `costoEntrega`) VALUES ('5', 'Las Venezolanas', 'Calle De Santiago Tapia 153, Centro Histórico, Morelia, Michoacán 58000', 'Lunes a Domingo de 13:00 pm a 10:00 pm', '40-50 min', '30');
INSERT INTO `restaurante` (`idRestaurante`, `nombreRest`, `direccion`, `horario`, `tiempoEstimado`, `costoEntrega`) VALUES ('6', 'Tu Cocina Morelia', 'Calle Naranjo 157, Morelia, Michoacán de Ocampo 58160', 'Lunes a Sábado de 12:00 pm a 7:00 pm', '40-50 min', '38');


INSERT INTO `platillos` (`idPlatillos`, `nombrePlatillo`, `precio`, `Restaurante_idRestaurante`) VALUES ('1', 'Mole Rojo con Pollo', '100.00', '1');
INSERT INTO `platillos` (`idPlatillos`, `nombrePlatillo`, `precio`, `Restaurante_idRestaurante`) VALUES ('2', 'Chilaquiles Sencillos', '65.00', '1');
INSERT INTO `platillos` (`idPlatillos`, `nombrePlatillo`, `precio`, `Restaurante_idRestaurante`) VALUES ('3', 'Quesadilla', '40.00', '1');


INSERT INTO `platillos` (`idPlatillos`, `nombrePlatillo`, `precio`, `Restaurante_idRestaurante`) VALUES ('4', 'Hamburguesa Sencilla', '99.00', '2');
INSERT INTO `platillos` (`idPlatillos`, `nombrePlatillo`, `precio`, `Restaurante_idRestaurante`) VALUES ('5', 'Royal Burguer', '153.00', '2'), ('6', 'Papas a la Francesa', '60.00', '2');


INSERT INTO `platillos` (`idPlatillos`, `nombrePlatillo`, `precio`, `Restaurante_idRestaurante`) VALUES ('7', 'Torta Cubana', '47.50', '3'), ('8', 'Enchiladas Verdes', '52.50', '3');
INSERT INTO `platillos` (`idPlatillos`, `nombrePlatillo`, `precio`, `Restaurante_idRestaurante`) VALUES ('9', 'Chilaquiles', '68.50', '3');


INSERT INTO `platillos` (`idPlatillos`, `nombrePlatillo`, `precio`, `Restaurante_idRestaurante`) VALUES ('10', 'Torta Ahogada', '34.00', '4'), ('11', 'Orden de Tacos Ahogados', '38.00', '4');
INSERT INTO `platillos` (`idPlatillos`, `nombrePlatillo`, `precio`, `Restaurante_idRestaurante`) VALUES ('12', 'Costra de Bistec con Chorizo', '27.00', '4');


INSERT INTO `platillos` (`idPlatillos`, `nombrePlatillo`, `precio`, `Restaurante_idRestaurante`) VALUES ('13', 'Hamburguesa de Res', '40.00', '5'), ('14', 'Hot-Dog', '25.00', '5');
INSERT INTO `platillos` (`idPlatillos`, `nombrePlatillo`, `precio`, `Restaurante_idRestaurante`) VALUES ('15', 'Hamburguesa de Pollo', '45.00', '5');


INSERT INTO `platillos` (`idPlatillos`, `nombrePlatillo`, `precio`, `Restaurante_idRestaurante`) VALUES ('16', 'Enchiladas Suizas con Pollo', '68.00', '6'), ('17', 'Filete de Pescado', '65.00', '6');
INSERT INTO `platillos` (`idPlatillos`, `nombrePlatillo`, `precio`, `Restaurante_idRestaurante`) VALUES ('18', 'Pechuga de Pollo', '68.00', '6');
