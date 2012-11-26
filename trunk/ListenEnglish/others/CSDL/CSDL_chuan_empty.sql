SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `listenenglish` DEFAULT CHARACTER SET latin1 ;
USE `listenenglish` ;

-- -----------------------------------------------------
-- Table `listenenglish`.`lesson`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `listenenglish`.`lesson` (
  `id` INT(11) NOT NULL ,
  `name` VARCHAR(255) NULL DEFAULT NULL ,
  `level` TINYINT(4) NULL DEFAULT NULL ,
  `previewFile` VARCHAR(255) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `listenenglish`.`user`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `listenenglish`.`user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `password` VARCHAR(32) NULL DEFAULT NULL ,
  `username` VARCHAR(32) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `listenenglish`.`listen`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `listenenglish`.`listen` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `userID` INT(11) NOT NULL ,
  `lessID` INT(11) NOT NULL ,
  `time` DATE NULL DEFAULT NULL ,
  `score` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_User_has_Lession_Lession1_idx` (`lessID` ASC) ,
  INDEX `fk_User_has_Lession_User_idx` (`userID` ASC) ,
  CONSTRAINT `fk_User_has_Lession_Lession1`
    FOREIGN KEY (`lessID` )
    REFERENCES `listenenglish`.`lesson` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_has_Lession_User`
    FOREIGN KEY (`userID` )
    REFERENCES `listenenglish`.`user` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 16
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `listenenglish`.`track`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `listenenglish`.`track` (
  `id` INT(11) NOT NULL ,
  `length` INT(11) NULL DEFAULT NULL ,
  `audioFile` VARCHAR(255) NULL DEFAULT NULL ,
  `script` TEXT NULL DEFAULT NULL ,
  `suggest` VARCHAR(255) NULL DEFAULT NULL ,
  `lessID` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_track_lesson1` (`lessID` ASC) ,
  CONSTRAINT `fk_track_lesson1`
    FOREIGN KEY (`lessID` )
    REFERENCES `listenenglish`.`lesson` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
