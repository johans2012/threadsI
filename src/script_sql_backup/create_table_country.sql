CREATE TABLE  `cubetrendstestshema`.`country` (
`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
`nombre` VARCHAR( 50 ) NOT NULL ,
`capital` VARCHAR( 50 ) NOT NULL ,
`habitantes` INT NOT NULL ,
`trends` BLOB NOT NULL ,
`temperatura` INT NOT NULL ,
`area` FLOAT NOT NULL ,
`altitud` INT NOT NULL ,
`location` INT NOT NULL
) ENGINE = INNODB;


