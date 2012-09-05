SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `buildit` DEFAULT CHARACTER SET utf8 ;
USE `buildit` ;

-- -----------------------------------------------------
-- Table `buildit`.`price_list`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `buildit`.`price_list` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT ,
  `NAME` VARCHAR(100) CHARACTER SET 'latin1' NOT NULL ,
  `PRICE` DOUBLE NOT NULL ,
  `UNIT` VARCHAR(20) CHARACTER SET 'latin1' NOT NULL ,
  PRIMARY KEY (`ID`) )
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `buildit`.`hire_requests`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `buildit`.`hire_requests` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `price_list_id` INT(11) NOT NULL ,
  `start_date` DATE NOT NULL ,
  `end_date` DATE NOT NULL ,
  `status` VARCHAR(20) CHARACTER SET 'latin1' NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_price_list_id` (`price_list_id` ASC) ,
  CONSTRAINT `fk_price_list_id`
    FOREIGN KEY (`price_list_id` )
    REFERENCES `buildit`.`price_list` (`ID` ))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
