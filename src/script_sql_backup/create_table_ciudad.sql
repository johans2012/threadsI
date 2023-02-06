CREATE TABLE  `cubetrendsimulator`.`ciudad` (
`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
`nombre` VARCHAR( 50 ) NOT NULL ,
`capital` BLOB NOT NULL ,
`location` VARCHAR( 50 ) NOT NULL ,
`date` DATE NOT NULL ,
`temperatura` INT NOT NULL ,
`departamento` VARCHAR( 50 ) NOT NULL ,
`trends` BLOB NOT NULL ,
`altitud` INT NOT NULL ,
`area` FLOAT NOT NULL ,
`habitantes` INT NOT NULL
) ENGINE = INNODB;

