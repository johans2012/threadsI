CREATE TABLE  `cubetrendsproject`.`citizen` (
`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
`edad` INT NOT NULL ,
`sexo` VARCHAR( 30 ) NOT NULL ,
`nombre` VARCHAR( 30 ) NOT NULL ,
`empleo` VARCHAR( 30 ) NOT NULL ,
`salario` INT NOT NULL ,
`eps` VARCHAR( 30 ) NOT NULL ,
`compensacion` VARCHAR( 30 ) NOT NULL
) ENGINE = INNODB;

