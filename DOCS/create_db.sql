SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `pfmdb` ;
CREATE SCHEMA IF NOT EXISTS `pfmdb` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `pfmdb` ;

-- -----------------------------------------------------
-- Table `pfmdb`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pfmdb`.`User` ;

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
DROP TABLE IF EXISTS `pfmdb`.`Account` ;

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
-- Table `pfmdb`.`Category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pfmdb`.`Category` ;

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
-- Table `pfmdb`.`Currency`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pfmdb`.`Currency` ;

CREATE  TABLE IF NOT EXISTS `pfmdb`.`Currency` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `code` CHAR(3) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pfmdb`.`Expense`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pfmdb`.`Expense` ;

CREATE  TABLE IF NOT EXISTS `pfmdb`.`Expense` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `amount` DOUBLE UNSIGNED NOT NULL ,
  `date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `categoryId` INT UNSIGNED NOT NULL ,
  `accountId` INT UNSIGNED NOT NULL ,
  `currencyId` INT UNSIGNED NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_Expense_Category1` (`categoryId` ASC) ,
  INDEX `fk_Expense_Account1` (`accountId` ASC) ,
  INDEX `fk_Expense_Currency1` (`currencyId` ASC) ,
  CONSTRAINT `fk_Expense_Category1`
    FOREIGN KEY (`categoryId` )
    REFERENCES `pfmdb`.`Category` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Expense_Account1`
    FOREIGN KEY (`accountId` )
    REFERENCES `pfmdb`.`Account` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Expense_Currency1`
    FOREIGN KEY (`currencyId` )
    REFERENCES `pfmdb`.`Currency` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pfmdb`.`Source`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pfmdb`.`Source` ;

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
DROP TABLE IF EXISTS `pfmdb`.`Income` ;

CREATE  TABLE IF NOT EXISTS `pfmdb`.`Income` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `amount` DOUBLE UNSIGNED NOT NULL ,
  `date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `sourceId` INT UNSIGNED NOT NULL ,
  `accountId` INT UNSIGNED NOT NULL ,
  `currencyId` INT UNSIGNED NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_Income_Source1` (`sourceId` ASC) ,
  INDEX `fk_Income_Account1` (`accountId` ASC) ,
  INDEX `fk_Income_Currency1` (`currencyId` ASC) ,
  CONSTRAINT `fk_Income_Source1`
    FOREIGN KEY (`sourceId` )
    REFERENCES `pfmdb`.`Source` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Income_Account1`
    FOREIGN KEY (`accountId` )
    REFERENCES `pfmdb`.`Account` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Income_Currency1`
    FOREIGN KEY (`currencyId` )
    REFERENCES `pfmdb`.`Currency` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pfmdb`.`Transfer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pfmdb`.`Transfer` ;

CREATE  TABLE IF NOT EXISTS `pfmdb`.`Transfer` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `amount` DOUBLE UNSIGNED NOT NULL ,
  `date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `fromAccountId` INT UNSIGNED NOT NULL ,
  `toAccountId` INT UNSIGNED NOT NULL ,
  `currencyId` INT UNSIGNED NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_Transfer_Account1` (`fromAccountId` ASC) ,
  INDEX `fk_Transfer_Account2` (`toAccountId` ASC) ,
  INDEX `fk_Transfer_Currency1` (`currencyId` ASC) ,
  CONSTRAINT `fk_Transfer_Account1`
    FOREIGN KEY (`fromAccountId` )
    REFERENCES `pfmdb`.`Account` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Transfer_Account2`
    FOREIGN KEY (`toAccountId` )
    REFERENCES `pfmdb`.`Account` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Transfer_Currency1`
    FOREIGN KEY (`currencyId` )
    REFERENCES `pfmdb`.`Currency` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- procedure findAccountBalance
-- -----------------------------------------------------

USE `pfmdb`;
DROP procedure IF EXISTS `pfmdb`.`findAccountBalance`;

DELIMITER $$
USE `pfmdb`$$
CREATE PROCEDURE `pfmdb`.`findAccountBalance` (IN accId INT)
BEGIN
    
SELECT IFNULL(expinc.currencyId, transf.currencyId) as CURRENCYID, (IFNULL(expinc.sum, 0) + IFNULL(transf.sum, 0)) as SUM
FROM
    ((SELECT IFNULL(exp.currencyId, inc.currencyId) as currencyId, (IFNULL(inc.sum, 0) - IFNULL(exp.sum, 0)) as sum
        FROM (SELECT currencyId, sum(amount) as sum
                FROM Expense
                WHERE accountId = accId 
                GROUP BY currencyId) AS exp
        LEFT OUTER JOIN (SELECT currencyId, sum(amount) as sum
                FROM Income as inc
                WHERE accountId = accId 
                GROUP BY currencyId) as inc
        ON exp.currencyId = inc.currencyId)
    UNION
    (SELECT IFNULL(exp.currencyId, inc.currencyId) as currencyId, (IFNULL(inc.sum, 0) - IFNULL(exp.sum, 0)) as sum
        FROM (SELECT currencyId, sum(amount) as sum
                FROM Expense
                WHERE accountId = accId 
                GROUP BY currencyId) AS exp
        RIGHT OUTER JOIN (SELECT currencyId, sum(amount) as sum
                FROM Income as inc
                WHERE accountId = accId 
                GROUP BY currencyId) as inc
        ON exp.currencyId = inc.currencyId)) AS expinc
LEFT OUTER JOIN   
    ((SELECT IFNULL(inc.currencyId, exp.currencyId) as currencyId, (IFNULL(inc.sum, 0) - IFNULL(exp.sum, 0)) as sum
        FROM (SELECT currencyId, sum(amount) as sum
                FROM Transfer as inc
                WHERE toAccountId = accId 
                GROUP BY currencyId, toAccountId) AS inc
        LEFT OUTER JOIN (SELECT currencyId, sum(amount) as sum
                FROM Transfer as exp
                WHERE fromAccountId = accId 
                GROUP BY currencyId) as exp
        ON exp.currencyId = inc.currencyId)
    UNION
    (SELECT IFNULL(inc.currencyId, exp.currencyId) as currencyId, (IFNULL(inc.sum, 0) - IFNULL(exp.sum, 0)) as sum
        FROM (SELECT currencyId, sum(amount) as sum
                FROM Transfer as inc
                WHERE toAccountId = accId 
                GROUP BY currencyId, toAccountId) AS inc
        RIGHT OUTER JOIN (SELECT currencyId, sum(amount) as sum
                FROM Transfer as exp
                WHERE fromAccountId = accId 
                GROUP BY currencyId) as exp
        ON exp.currencyId = inc.currencyId)) AS transf
ON expinc.currencyID = transf.currencyId
UNION
SELECT IFNULL(expinc.currencyId, transf.currencyId) as CURRENCYID, (IFNULL(expinc.sum, 0) + IFNULL(transf.sum, 0)) as SUM
FROM
    ((SELECT IFNULL(exp.currencyId, inc.currencyId) as currencyId, (IFNULL(inc.sum, 0) - IFNULL(exp.sum, 0)) as sum
        FROM (SELECT currencyId, sum(amount) as sum
                FROM Expense
                WHERE accountId = accId 
                GROUP BY currencyId) AS exp
        LEFT OUTER JOIN (SELECT currencyId, sum(amount) as sum
                FROM Income as inc
                WHERE accountId = accId 
                GROUP BY currencyId) as inc
        ON exp.currencyId = inc.currencyId)
    UNION
    (SELECT IFNULL(exp.currencyId, inc.currencyId) as currencyId, (IFNULL(inc.sum, 0) - IFNULL(exp.sum, 0)) as sum
        FROM (SELECT currencyId, sum(amount) as sum
                FROM Expense
                WHERE accountId = accId 
                GROUP BY currencyId) AS exp
        RIGHT OUTER JOIN (SELECT currencyId, sum(amount) as sum
                FROM Income as inc
                WHERE accountId = accId 
                GROUP BY currencyId) as inc
        ON exp.currencyId = inc.currencyId)) AS expinc
RIGHT OUTER JOIN   
    ((SELECT IFNULL(inc.currencyId, exp.currencyId) as currencyId, (IFNULL(inc.sum, 0) - IFNULL(exp.sum, 0)) as sum
        FROM (SELECT currencyId, sum(amount) as sum
                FROM Transfer as inc
                WHERE toAccountId = accId 
                GROUP BY currencyId, toAccountId) AS inc
        LEFT OUTER JOIN (SELECT currencyId, sum(amount) as sum
                FROM Transfer as exp
                WHERE fromAccountId = accId 
                GROUP BY currencyId) as exp
        ON exp.currencyId = inc.currencyId)
    UNION
    (SELECT IFNULL(inc.currencyId, exp.currencyId) as currencyId, (IFNULL(inc.sum, 0) - IFNULL(exp.sum, 0)) as sum
        FROM (SELECT currencyId, sum(amount) as sum
                FROM Transfer as inc
                WHERE toAccountId = accId 
                GROUP BY currencyId, toAccountId) AS inc
        RIGHT OUTER JOIN (SELECT currencyId, sum(amount) as sum
                FROM Transfer as exp
                WHERE fromAccountId = accId 
                GROUP BY currencyId) as exp
        ON exp.currencyId = inc.currencyId)) AS transf
ON expinc.currencyID = transf.currencyId;

END
$$

DELIMITER ;
-- -----------------------------------------------------
-- procedure findJournalEntries
-- -----------------------------------------------------

USE `pfmdb`;
DROP procedure IF EXISTS `pfmdb`.`findJournalEntries`;

DELIMITER $$
USE `pfmdb`$$
CREATE PROCEDURE `pfmdb`.`findJournalEntries` (IN userId INT, 
                                                IN fromDate TIMESTAMP,
                                                IN toDate TIMESTAMP)
BEGIN

    SELECT 1 AS TYPE, exp.id AS TRANSACTIONID, 
    acc.name AS ACCOUNTNAME, cat.name AS TEXT, 
    CONCAT ('-', CAST(exp.amount AS CHAR(255)), ' ', cur.code) AS AMOUNT, 
    exp.date AS DATE FROM Expense AS exp
    JOIN Account AS acc
    ON exp.accountId = acc.id
    JOIN Category AS cat
    ON exp.categoryId = cat.id
    JOIN Currency AS cur
    ON exp.currencyId = cur.id
    WHERE acc.userId = userId AND 
    exp.date BETWEEN fromDate AND toDate 
UNION
    SELECT 2 AS TYPE, inc.id AS TRANSACTIONID, 
    acc.name AS ACCOUNTNAME, src.name AS TEXT, 
    CONCAT (CAST(inc.amount AS CHAR(255)), ' ', cur.code) AS AMOUNT, 
    inc.date AS DATE FROM Income AS inc
    JOIN Account AS acc
    ON inc.accountId = acc.id
    JOIN Source AS src
    ON inc.sourceId = src.id
    JOIN Currency AS cur
    ON inc.currencyId = cur.id
    WHERE acc.userId = userId AND 
    inc.date BETWEEN fromDate AND toDate 
UNION
    SELECT 3 AS TYPE, trs.id AS TRANSACTIONID, 
    CONCAT('from ', fromAcc.name, ' to ', toAcc.name) AS ACCOUNTNAME, 
    
    "" AS TEXT,
    CONCAT (CAST(trs.amount AS CHAR(255)), ' ', cur.code) AS AMOUNT, 
    trs.date AS DATE FROM Transfer AS trs
    JOIN Account AS fromAcc
    ON trs.fromAccountId = fromAcc.id
    JOIN Account AS toAcc
    ON trs.toAccountId = toAcc.id
    JOIN Currency AS cur
    ON trs.currencyId = cur.id
    WHERE fromAcc.userId = userId AND 
    trs.date BETWEEN fromDate  AND toDate 
ORDER BY DATE DESC;






END
$$

DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `pfmdb`.`User`
-- -----------------------------------------------------
SET AUTOCOMMIT=0;
USE `pfmdb`;
INSERT INTO `pfmdb`.`User` (`id`, `username`, `password`, `email`) VALUES ('1', 'test', 'test', 'test');

COMMIT;

-- -----------------------------------------------------
-- Data for table `pfmdb`.`Account`
-- -----------------------------------------------------
SET AUTOCOMMIT=0;
USE `pfmdb`;
INSERT INTO `pfmdb`.`Account` (`id`, `name`, `userId`) VALUES ('1', 'Hansabanka', '1');
INSERT INTO `pfmdb`.`Account` (`id`, `name`, `userId`) VALUES ('2', 'Wallet', '1');

COMMIT;

-- -----------------------------------------------------
-- Data for table `pfmdb`.`Category`
-- -----------------------------------------------------
SET AUTOCOMMIT=0;
USE `pfmdb`;
INSERT INTO `pfmdb`.`Category` (`id`, `name`, `userId`) VALUES ('1', 'Food', '1');
INSERT INTO `pfmdb`.`Category` (`id`, `name`, `userId`) VALUES ('2', 'Clothes', '1');
INSERT INTO `pfmdb`.`Category` (`id`, `name`, `userId`) VALUES ('3', 'Transport', '1');
INSERT INTO `pfmdb`.`Category` (`id`, `name`, `userId`) VALUES ('4', 'Rent', '1');

COMMIT;

-- -----------------------------------------------------
-- Data for table `pfmdb`.`Currency`
-- -----------------------------------------------------
SET AUTOCOMMIT=0;
USE `pfmdb`;
INSERT INTO `pfmdb`.`Currency` (`id`, `code`) VALUES ('1', 'LVL');
INSERT INTO `pfmdb`.`Currency` (`id`, `code`) VALUES ('2', 'EUR');
INSERT INTO `pfmdb`.`Currency` (`id`, `code`) VALUES ('3', 'USD');

COMMIT;

-- -----------------------------------------------------
-- Data for table `pfmdb`.`Expense`
-- -----------------------------------------------------
SET AUTOCOMMIT=0;
USE `pfmdb`;
INSERT INTO `pfmdb`.`Expense` (`id`, `amount`, `date`, `categoryId`, `accountId`, `currencyId`) VALUES ('1', '200', NULL, '1', '1', '1');
INSERT INTO `pfmdb`.`Expense` (`id`, `amount`, `date`, `categoryId`, `accountId`, `currencyId`) VALUES ('2', '300', NULL, '2', '2', '1');
INSERT INTO `pfmdb`.`Expense` (`id`, `amount`, `date`, `categoryId`, `accountId`, `currencyId`) VALUES ('3', '500', NULL, '2', '1', '2');

COMMIT;

-- -----------------------------------------------------
-- Data for table `pfmdb`.`Source`
-- -----------------------------------------------------
SET AUTOCOMMIT=0;
USE `pfmdb`;
INSERT INTO `pfmdb`.`Source` (`id`, `name`, `userId`) VALUES ('1', 'Salary', '1');
INSERT INTO `pfmdb`.`Source` (`id`, `name`, `userId`) VALUES ('2', 'Other', '1');

COMMIT;

-- -----------------------------------------------------
-- Data for table `pfmdb`.`Income`
-- -----------------------------------------------------
SET AUTOCOMMIT=0;
USE `pfmdb`;
INSERT INTO `pfmdb`.`Income` (`id`, `amount`, `date`, `sourceId`, `accountId`, `currencyId`) VALUES ('1', '600', NULL, '1', '1', '1');
INSERT INTO `pfmdb`.`Income` (`id`, `amount`, `date`, `sourceId`, `accountId`, `currencyId`) VALUES ('2', '500', NULL, '2', '2', '1');
INSERT INTO `pfmdb`.`Income` (`id`, `amount`, `date`, `sourceId`, `accountId`, `currencyId`) VALUES ('3', '2000', NULL, '2', '1', '2');

COMMIT;

-- -----------------------------------------------------
-- Data for table `pfmdb`.`Transfer`
-- -----------------------------------------------------
SET AUTOCOMMIT=0;
USE `pfmdb`;
INSERT INTO `pfmdb`.`Transfer` (`id`, `amount`, `date`, `fromAccountId`, `toAccountId`, `currencyId`) VALUES ('1', '200', NULL, '1', '2', '2');

COMMIT;
