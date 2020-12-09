-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema sigeins_bd
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema sigeins_bd
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sigeins_bd` DEFAULT CHARACTER SET utf8 ;
USE `sigeins_bd` ;

-- -----------------------------------------------------
-- Table `sigeins_bd`.`Apartado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sigeins_bd`.`Apartado` (
  `idApartado` INT NOT NULL AUTO_INCREMENT,
  `TituloApartado` VARCHAR(45) NULL,
  PRIMARY KEY (`idApartado`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sigeins_bd`.`InformacionInstitucional`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sigeins_bd`.`InformacionInstitucional` (
  `idInformacion` INT NOT NULL AUTO_INCREMENT,
  `idApartado` INT NOT NULL,
  `DetalleInformacion` VARCHAR(300) NULL,
  `AdjuntoInformacion` VARCHAR(200) NULL,
  PRIMARY KEY (`idInformacion`, `idApartado`),
  INDEX `fk_InformacionInstitucional_Apartado_idx` (`idApartado` ASC) VISIBLE,
  CONSTRAINT `fk_InformacionInstitucional_Apartado`
    FOREIGN KEY (`idApartado`)
    REFERENCES `sigeins_bd`.`Apartado` (`idApartado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sigeins_bd`.`AreaInstitucional`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sigeins_bd`.`AreaInstitucional` (
  `idAreaInstitucional` INT NOT NULL AUTO_INCREMENT,
  `TituloAreaInstitucional` VARCHAR(45) NOT NULL,
  `DescripcionAreaInstitucional` VARCHAR(140) NOT NULL,
  PRIMARY KEY (`idAreaInstitucional`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sigeins_bd`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sigeins_bd`.`Usuario` (
  `idUsuario` INT NOT NULL AUTO_INCREMENT,
  `PseudonimoUsuario` VARCHAR(45) NULL,
  `AutenticacionUsuario` VARCHAR(45) NOT NULL,
  `EmailUsuario` VARCHAR(145) NULL,
  `idAreaInstitucional` INT NOT NULL,
  PRIMARY KEY (`idUsuario`, `idAreaInstitucional`),
  UNIQUE INDEX `PseudonimoUsuario_UNIQUE` (`PseudonimoUsuario` ASC) VISIBLE,
  INDEX `fk_Usuario_AreaInstitucional1_idx` (`idAreaInstitucional` ASC) VISIBLE,
  CONSTRAINT `fk_Usuario_AreaInstitucional1`
    FOREIGN KEY (`idAreaInstitucional`)
    REFERENCES `sigeins_bd`.`AreaInstitucional` (`idAreaInstitucional`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sigeins_bd`.`Noticia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sigeins_bd`.`Noticia` (
  `idNoticia` INT NOT NULL AUTO_INCREMENT,
  `FechaEmisionNoticia` DATE NULL,
  `ContenidoNoticia` TEXT(700) NULL,
  `AdjuntoNoticia` VARCHAR(255) NULL,
  PRIMARY KEY (`idNoticia`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sigeins_bd`.`DetalleNoticia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sigeins_bd`.`DetalleNoticia` (
  `idAreaInstitucional` INT NOT NULL,
  `idNoticia` INT NOT NULL,
  PRIMARY KEY (`idAreaInstitucional`, `idNoticia`),
  INDEX `fk_AreaInstitucional_has_Noticia_Noticia1_idx` (`idNoticia` ASC) VISIBLE,
  INDEX `fk_AreaInstitucional_has_Noticia_AreaInstitucional1_idx` (`idAreaInstitucional` ASC) VISIBLE,
  CONSTRAINT `fk_AreaInstitucional_has_Noticia_AreaInstitucional1`
    FOREIGN KEY (`idAreaInstitucional`)
    REFERENCES `sigeins_bd`.`AreaInstitucional` (`idAreaInstitucional`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_AreaInstitucional_has_Noticia_Noticia1`
    FOREIGN KEY (`idNoticia`)
    REFERENCES `sigeins_bd`.`Noticia` (`idNoticia`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sigeins_bd`.`OfertaEducativa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sigeins_bd`.`OfertaEducativa` (
  `idOfertaEducativa` INT NOT NULL AUTO_INCREMENT,
  `TituloOfertaEducativa` VARCHAR(145) NOT NULL,
  `ObjetivoGeneralOfertaEducativa` VARCHAR(255) NULL,
  `AcercaDeOfertaEducativa` VARCHAR(255) NULL,
  `ReticulaOfertaEducativa` VARCHAR(45) NULL,
  `PlanEstudiosOfertaEducativa` VARCHAR(255) NULL,
  `AdjuntoOfertaEducativa` VARCHAR(255) NULL,
  `AdjuntoObjetivoOfertaEducativa` VARCHAR(255) NULL,
  `AdjuntoAcercaOfertaEducativa` VARCHAR(255) NULL,
  PRIMARY KEY (`idOfertaEducativa`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sigeins_bd`.`Aspirante`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sigeins_bd`.`Aspirante` (
  `idAspirante` INT NOT NULL AUTO_INCREMENT,
  `NombreAspirante` VARCHAR(45) NULL,
  `ApellidoPaternoAspirante` VARCHAR(50) NULL,
  `ApellidoMaternoAspirante` VARCHAR(45) NULL,
  `EmailAspirante` VARCHAR(100) NULL,
  `idOfertaEducativa` INT NOT NULL,
  PRIMARY KEY (`idAspirante`, `idOfertaEducativa`),
  INDEX `fk_Aspirante_OfertaEducativa1_idx` (`idOfertaEducativa` ASC) VISIBLE,
  CONSTRAINT `fk_Aspirante_OfertaEducativa1`
    FOREIGN KEY (`idOfertaEducativa`)
    REFERENCES `sigeins_bd`.`OfertaEducativa` (`idOfertaEducativa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sigeins_bd`.`HistorialCambios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sigeins_bd`.`HistorialCambios` (
  `idUsuario` INT NOT NULL,
  `idInformacion` INT NOT NULL,
  `FechaHistorialCambios` DATE NOT NULL,
  `AnteriorHistorialCambios` VARCHAR(300) NULL,
  PRIMARY KEY (`idUsuario`, `idInformacion`),
  INDEX `fk_Usuario_has_InformacionInstitucional_InformacionInstituc_idx` (`idInformacion` ASC) VISIBLE,
  INDEX `fk_Usuario_has_InformacionInstitucional_Usuario1_idx` (`idUsuario` ASC) VISIBLE,
  CONSTRAINT `fk_Usuario_has_InformacionInstitucional_Usuario1`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `sigeins_bd`.`Usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Usuario_has_InformacionInstitucional_InformacionInstitucio1`
    FOREIGN KEY (`idInformacion`)
    REFERENCES `sigeins_bd`.`InformacionInstitucional` (`idInformacion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sigeins_bd`.`ModalidadEducativa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sigeins_bd`.`ModalidadEducativa` (
  `idModalidadEducativa` INT NOT NULL,
  `NombreModalidadEducativa` VARCHAR(45) NULL,
  `DescripcionModalidadEducativa` VARCHAR(45) NULL,
  `SemestresModalidadEducativa` INT NULL,
  PRIMARY KEY (`idModalidadEducativa`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sigeins_bd`.`Promocion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sigeins_bd`.`Promocion` (
  `idPromocion` INT NOT NULL AUTO_INCREMENT,
  `TituloPromocion` VARCHAR(150) NULL,
  `FechaInicio` DATE NULL,
  `FechaFinal` DATE NULL,
  `Descripcion` VARCHAR(255) NULL,
  `PorcentajePromocion` INT NULL,
  `AdjuntoPromocion` VARCHAR(255) NULL,
  `idAreaInstitucional` INT NOT NULL,
  PRIMARY KEY (`idPromocion`, `idAreaInstitucional`),
  INDEX `fk_Promocion_AreaInstitucional1_idx` (`idAreaInstitucional` ASC) VISIBLE,
  CONSTRAINT `fk_Promocion_AreaInstitucional1`
    FOREIGN KEY (`idAreaInstitucional`)
    REFERENCES `sigeins_bd`.`AreaInstitucional` (`idAreaInstitucional`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sigeins_bd`.`DetallePromocion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sigeins_bd`.`DetallePromocion` (
  `idOfertaEducativa` INT NOT NULL,
  `idPromocion` INT NOT NULL,
  PRIMARY KEY (`idOfertaEducativa`, `idPromocion`),
  INDEX `fk_OfertaEducativa_has_Promocion_Promocion1_idx` (`idPromocion` ASC) VISIBLE,
  INDEX `fk_OfertaEducativa_has_Promocion_OfertaEducativa1_idx` (`idOfertaEducativa` ASC) VISIBLE,
  CONSTRAINT `fk_OfertaEducativa_has_Promocion_OfertaEducativa1`
    FOREIGN KEY (`idOfertaEducativa`)
    REFERENCES `sigeins_bd`.`OfertaEducativa` (`idOfertaEducativa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_OfertaEducativa_has_Promocion_Promocion1`
    FOREIGN KEY (`idPromocion`)
    REFERENCES `sigeins_bd`.`Promocion` (`idPromocion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sigeins_bd`.`DetalleOfertaEducativa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sigeins_bd`.`DetalleOfertaEducativa` (
  `idOfertaEducativa` INT NOT NULL,
  `idModalidadEducativa` INT NOT NULL,
  PRIMARY KEY (`idOfertaEducativa`, `idModalidadEducativa`),
  INDEX `fk_OfertaEducativa_has_ModalidadEducativa_ModalidadEducativ_idx` (`idModalidadEducativa` ASC) VISIBLE,
  INDEX `fk_OfertaEducativa_has_ModalidadEducativa_OfertaEducativa1_idx` (`idOfertaEducativa` ASC) VISIBLE,
  CONSTRAINT `fk_OfertaEducativa_has_ModalidadEducativa_OfertaEducativa1`
    FOREIGN KEY (`idOfertaEducativa`)
    REFERENCES `sigeins_bd`.`OfertaEducativa` (`idOfertaEducativa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_OfertaEducativa_has_ModalidadEducativa_ModalidadEducativa1`
    FOREIGN KEY (`idModalidadEducativa`)
    REFERENCES `sigeins_bd`.`ModalidadEducativa` (`idModalidadEducativa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
