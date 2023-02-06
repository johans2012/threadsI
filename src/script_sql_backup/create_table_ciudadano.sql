CREATE TABLE  `cubetrendsimulator`.`ciudadano` (
`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
`edad` INT NOT NULL ,
`sexo` VARCHAR( 50 ) NOT NULL ,
`nombre` VARCHAR( 50 ) NOT NULL ,
`empleo` VARCHAR( 50 ) NOT NULL ,
`salario` INT NOT NULL ,
`eps` VARCHAR( 50 ) NOT NULL ,
`caja_pension` VARCHAR( 50 ) NOT NULL
) ENGINE = INNODB;

