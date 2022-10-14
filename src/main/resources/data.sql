-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema tournament
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `tournament` ;

-- -----------------------------------------------------
-- Schema tournament
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `tournament` DEFAULT CHARACTER SET utf8 ;
USE `tournament` ;

-- -----------------------------------------------------
-- Table `tournament`.`team`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tournament`.`team` ;

CREATE TABLE IF NOT EXISTS `tournament`.`team` (
                                                   `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
                                                   `name` VARCHAR(20) NOT NULL,
                                                   `capitan` VARCHAR(20) NOT NULL,
                                                   `coach` VARCHAR(20) NOT NULL,
                                                   PRIMARY KEY (`id`),
                                                   UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
                                                   UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tournament`.`tournament`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tournament`.`tournament` ;

CREATE TABLE IF NOT EXISTS `tournament`.`tournament` (
                                                         `id` INT NOT NULL AUTO_INCREMENT,
                                                         `matches` VARCHAR(1000) NOT NULL,
                                                         `winner` VARCHAR(50) NOT NULL,
                                                         PRIMARY KEY (`id`),
                                                         UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
    ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;