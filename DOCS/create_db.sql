SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `pfmdb` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `pfmdb` ;

-- -----------------------------------------------------
-- Table `pfmdb`.`User`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `pfmdb`.`User` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `username` VARCHAR(255) NOT NULL ,
  `password` VARCHAR(255) NOT NULL ,
  `email` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) ,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pfmdb`.`Account`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `pfmdb`.`Account` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  `userId` INT UNSIGNED NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_Account_User` (`userId` ASC) ,
  CONSTRAINT `fk_Account_User`
    FOREIGN KEY (`userId` )
    REFERENCES `pfmdb`.`User` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pfmdb`.`MoneyAccount`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `pfmdb`.`MoneyAccount` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `code` CHAR(3) NOT NULL ,
  `accountId` INT UNSIGNED NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_Money_Account1` (`accountId` ASC) ,
  UNIQUE INDEX `accountId_code_UNIQUE` (`code` ASC, `accountId` ASC) ,
  CONSTRAINT `fk_Money_Account1`
    FOREIGN KEY (`accountId` )
    REFERENCES `pfmdb`.`Account` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pfmdb`.`Category`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `pfmdb`.`Category` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  `userId` INT UNSIGNED NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_Category_User1` (`userId` ASC) ,
  CONSTRAINT `fk_Category_User1`
    FOREIGN KEY (`userId` )
    REFERENCES `pfmdb`.`User` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pfmdb`.`Expense`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `pfmdb`.`Expense` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `amount` INT UNSIGNED NOT NULL ,
  `date` DATETIME NOT NULL ,
  `categoryId` INT UNSIGNED NOT NULL ,
  `moneyAccountId` INT UNSIGNED NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_Expense_Category1` (`categoryId` ASC) ,
  INDEX `fk_Expense_MoneyAccount1` (`moneyAccountId` ASC) ,
  CONSTRAINT `fk_Expense_Category1`
    FOREIGN KEY (`categoryId` )
    REFERENCES `pfmdb`.`Category` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Expense_MoneyAccount1`
    FOREIGN KEY (`moneyAccountId` )
    REFERENCES `pfmdb`.`MoneyAccount` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pfmdb`.`Source`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `pfmdb`.`Source` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) NOT NULL ,
  `userId` INT UNSIGNED NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_Source_User1` (`userId` ASC) ,
  CONSTRAINT `fk_Source_User1`
    FOREIGN KEY (`userId` )
    REFERENCES `pfmdb`.`User` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pfmdb`.`Income`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `pfmdb`.`Income` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `amount` INT UNSIGNED NOT NULL ,
  `date` DATETIME NOT NULL ,
  `sourceId` INT UNSIGNED NOT NULL ,
  `moneyAccountId` INT UNSIGNED NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_Income_Source1` (`sourceId` ASC) ,
  INDEX `fk_Income_MoneyAccount1` (`moneyAccountId` ASC) ,
  CONSTRAINT `fk_Income_Source1`
    FOREIGN KEY (`sourceId` )
    REFERENCES `pfmdb`.`Source` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Income_MoneyAccount1`
    FOREIGN KEY (`moneyAccountId` )
    REFERENCES `pfmdb`.`MoneyAccount` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pfmdb`.`Transfer`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `pfmdb`.`Transfer` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `date` DATETIME NOT NULL ,
  `fromId` INT UNSIGNED NOT NULL ,
  `toId` INT UNSIGNED NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_Transfer_MoneyAccount1` (`fromId` ASC) ,
  INDEX `fk_Transfer_MoneyAccount2` (`toId` ASC) ,
  CONSTRAINT `fk_Transfer_MoneyAccount1`
    FOREIGN KEY (`fromId` )
    REFERENCES `pfmdb`.`MoneyAccount` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Transfer_MoneyAccount2`
    FOREIGN KEY (`toId` )
    REFERENCES `pfmdb`.`MoneyAccount` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
