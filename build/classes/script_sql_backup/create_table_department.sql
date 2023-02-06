CREATE TABLE  `cubetrendstestshema`.`department` (
`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
`name` VARCHAR( 50 ) NOT NULL ,
`ubicacion` VARCHAR( 50 ) NOT NULL ,
`cities` BLOB NOT NULL ,
`trends` BLOB NOT NULL ,
`habitantes` INT NOT NULL
) ENGINE = INNODB;