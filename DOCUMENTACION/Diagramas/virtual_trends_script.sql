-- MySQL Script generated by MySQL Workbench
-- Fri Oct 27 21:33:08 2023
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema VirtualTrends
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema VirtualTrends
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `VirtualTrends` DEFAULT CHARACTER SET utf8 ;
USE `VirtualTrends` ;

-- -----------------------------------------------------
-- Table `VirtualTrends`.`Contact`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `VirtualTrends`.`Contact` (
  `idContacto` INT NOT NULL,
  `email_contact` VARCHAR(45) NOT NULL,
  `asunto` VARCHAR(45) NOT NULL,
  `mensaje` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idContacto`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `VirtualTrends`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `VirtualTrends`.`Usuario` (
  `idUsuario` INT NOT NULL AUTO_INCREMENT,
  `nombre_completo` VARCHAR(45) NOT NULL,
  `dni` INT NOT NULL,
  `direccion` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `Reserva_idReserva` INT NOT NULL,
  `Contact_idContacto` INT NOT NULL,
  PRIMARY KEY (`idUsuario`),
  INDEX `fk_Usuario_Contact1_idx` (`Contact_idContacto` ASC) VISIBLE,
  CONSTRAINT `fk_Usuario_Contact1`
    FOREIGN KEY (`Contact_idContacto`)
    REFERENCES `VirtualTrends`.`Contact` (`idContacto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `VirtualTrends`.`Reserva`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `VirtualTrends`.`Reserva` (
  `idReserva` INT NOT NULL,
  `Usuario_idUsuario` INT NOT NULL,
  PRIMARY KEY (`idReserva`),
  INDEX `fk_Reserva_Usuario1_idx` (`Usuario_idUsuario` ASC) VISIBLE,
  CONSTRAINT `fk_Reserva_Usuario1`
    FOREIGN KEY (`Usuario_idUsuario`)
    REFERENCES `VirtualTrends`.`Usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `VirtualTrends`.`Video`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `VirtualTrends`.`Video` (
  `idVideo` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `descripcion` VARCHAR(45) NOT NULL,
  `Reserva_idReserva` INT NOT NULL,
  PRIMARY KEY (`idVideo`),
  INDEX `fk_Video_Reserva1_idx` (`Reserva_idReserva` ASC) VISIBLE,
  CONSTRAINT `fk_Video_Reserva1`
    FOREIGN KEY (`Reserva_idReserva`)
    REFERENCES `VirtualTrends`.`Reserva` (`idReserva`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `VirtualTrends`.`Turnero`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `VirtualTrends`.`Turnero` (
  `idCalendario` INT NOT NULL,
  `fecha` DATE NOT NULL,
  `hora` DATETIME NOT NULL,
  `comprobante` VARCHAR(45) NULL,
  `Reserva_idReserva` INT NOT NULL,
  PRIMARY KEY (`idCalendario`),
  INDEX `fk_Turno_Reserva1_idx` (`Reserva_idReserva` ASC) VISIBLE,
  CONSTRAINT `fk_Turno_Reserva1`
    FOREIGN KEY (`Reserva_idReserva`)
    REFERENCES `VirtualTrends`.`Reserva` (`idReserva`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `VirtualTrends`.`Admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `VirtualTrends`.`Admin` (
  `idAdmin` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `codigo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idAdmin`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `VirtualTrends`.`Rol_has_usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `VirtualTrends`.`Rol_has_usuario` (
  `Rol_idRol` INT NOT NULL,
  `Usuario_idUsuario` INT NOT NULL,
  PRIMARY KEY (`Rol_idRol`, `Usuario_idUsuario`),
  INDEX `fk_Rol_has_usuario_Usuario1_idx` (`Usuario_idUsuario` ASC) VISIBLE,
  CONSTRAINT `fk_Rol_has_usuario_Rol1`
    FOREIGN KEY (`Rol_idRol`)
    REFERENCES `VirtualTrends`.`Admin` (`idAdmin`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Rol_has_usuario_Usuario1`
    FOREIGN KEY (`Usuario_idUsuario`)
    REFERENCES `VirtualTrends`.`Usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
