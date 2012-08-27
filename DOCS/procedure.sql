SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';


USE `pfmdb`;
DROP procedure IF EXISTS `pfmdb`.`findAccountBalance`;

DELIMITER $$
USE `pfmdb`$$
CREATE PROCEDURE `pfmdb`.`findAccountBalance` (IN accId INT)
BEGIN
    
SELECT IFNULL(expinc.currencyId, transf.currencyId) as currencyId, (IFNULL(expinc.sum, 0) + IFNULL(transf.sum, 0)) as sum
FROM
    ((SELECT exp.currencyId, (IFNULL(inc.sum, 0) - IFNULL(exp.sum, 0)) as sum
        FROM (SELECT currencyId, sum(amount) as sum
                FROM expense
                WHERE accountId = accId 
                GROUP BY currencyId) AS exp
        LEFT OUTER JOIN (SELECT currencyId, sum(amount) as sum
                FROM income as inc
                WHERE accountId = accId 
                GROUP BY currencyId) as inc
        ON exp.currencyId = inc.currencyId)
    UNION
    (SELECT exp.currencyId, (IFNULL(inc.sum, 0) - IFNULL(exp.sum, 0)) as sum
        FROM (SELECT currencyId, sum(amount) as sum
                FROM expense
                WHERE accountId = accId 
                GROUP BY currencyId) AS exp
        RIGHT OUTER JOIN (SELECT currencyId, sum(amount) as sum
                FROM income as inc
                WHERE accountId = accId 
                GROUP BY currencyId) as inc
        ON exp.currencyId = inc.currencyId)) AS expinc
LEFT OUTER JOIN   
    ((SELECT inc.currencyId, (IFNULL(inc.sum, 0) - IFNULL(exp.sum, 0)) as sum
        FROM (SELECT currencyId, sum(amount) as sum
                FROM transfer as inc
                WHERE toAccountId = accId 
                GROUP BY currencyId, toAccountId) AS inc
        LEFT OUTER JOIN (SELECT currencyId, sum(amount) as sum
                FROM transfer as exp
                WHERE fromAccountId = accId 
                GROUP BY currencyId) as exp
        ON exp.currencyId = inc.currencyId)
    UNION
    (SELECT exp.currencyId, (IFNULL(inc.sum, 0) - IFNULL(exp.sum, 0)) as sum
        FROM (SELECT currencyId, sum(amount) as sum
                FROM transfer as inc
                WHERE toAccountId = accId 
                GROUP BY currencyId, toAccountId) AS inc
        RIGHT OUTER JOIN (SELECT currencyId, sum(amount) as sum
                FROM transfer as exp
                WHERE fromAccountId = accId 
                GROUP BY currencyId) as exp
        ON exp.currencyId = inc.currencyId)) AS transf
ON expinc.currencyID = transf.currencyId
UNION
SELECT IFNULL(expinc.currencyId, transf.currencyId) as currencyId, (IFNULL(expinc.sum, 0) + IFNULL(transf.sum, 0)) as sum
FROM
    ((SELECT exp.currencyId, (IFNULL(inc.sum, 0) - IFNULL(exp.sum, 0)) as sum
        FROM (SELECT currencyId, sum(amount) as sum
                FROM expense
                WHERE accountId = accId 
                GROUP BY currencyId) AS exp
        LEFT OUTER JOIN (SELECT currencyId, sum(amount) as sum
                FROM income as inc
                WHERE accountId = accId 
                GROUP BY currencyId) as inc
        ON exp.currencyId = inc.currencyId)
    UNION
    (SELECT exp.currencyId, (IFNULL(inc.sum, 0) - IFNULL(exp.sum, 0)) as sum
        FROM (SELECT currencyId, sum(amount) as sum
                FROM expense
                WHERE accountId = accId 
                GROUP BY currencyId) AS exp
        RIGHT OUTER JOIN (SELECT currencyId, sum(amount) as sum
                FROM income as inc
                WHERE accountId = accId 
                GROUP BY currencyId) as inc
        ON exp.currencyId = inc.currencyId)) AS expinc
RIGHT OUTER JOIN   
    ((SELECT inc.currencyId, (IFNULL(inc.sum, 0) - IFNULL(exp.sum, 0)) as sum
        FROM (SELECT currencyId, sum(amount) as sum
                FROM transfer as inc
                WHERE toAccountId = accId 
                GROUP BY currencyId, toAccountId) AS inc
        LEFT OUTER JOIN (SELECT currencyId, sum(amount) as sum
                FROM transfer as exp
                WHERE fromAccountId = accId 
                GROUP BY currencyId) as exp
        ON exp.currencyId = inc.currencyId)
    UNION
    (SELECT exp.currencyId, (IFNULL(inc.sum, 0) - IFNULL(exp.sum, 0)) as sum
        FROM (SELECT currencyId, sum(amount) as sum
                FROM transfer as inc
                WHERE toAccountId = accId 
                GROUP BY currencyId, toAccountId) AS inc
        RIGHT OUTER JOIN (SELECT currencyId, sum(amount) as sum
                FROM transfer as exp
                WHERE fromAccountId = accId 
                GROUP BY currencyId) as exp
        ON exp.currencyId = inc.currencyId)) AS transf
ON expinc.currencyID = transf.currencyId;

END
$$

DELIMITER ;
SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
