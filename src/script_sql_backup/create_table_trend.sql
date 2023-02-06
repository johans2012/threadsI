CREATE TABLE  `cubetrendstestshema`.`trend` (
`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
`ubicacion` VARCHAR( 30 ) NOT NULL ,
`inicio` INT NOT NULL ,
`finalizacion` INT NOT NULL ,
`intervalo` INT NOT NULL ,
`busqueda` VARCHAR( 30 ) NOT NULL ,
`categoria` VARCHAR( 30 ) NOT NULL ,
`tipoBusqueda` VARCHAR( 30 ) NOT NULL ,
`incXdia` INT NOT NULL ,
`incXSemana` INT NOT NULL ,
`incXIntervalo` INT NOT NULL
) ENGINE = INNODB;

INSERT INTO `cubetrendstestshema`.`trend` (`id`, `ubicacion`, `inicio`, `finalizacion`, 
`intervalo`, `busqueda`, `categoria`, `tipoBusqueda`, `incXdia`, `incXSemana`, `incXIntervalo`)
 VALUES (NULL, 'ubicacion', '1', '1', '10', 'find test', 'yotutube', 'user test find', '1', '1', '1');

INSERT INTO `cubetrendstestshema`.`trend` (`id`, `ubicacion`, `inicio`, `finalizacion`, 
`intervalo`, `busqueda`, `categoria`, `tipoBusqueda`, `incXdia`, `incXSemana`, `incXIntervalo`)
 VALUES (NULL, 'ubicacion', '1', '1', '10', 'find test', 'yotutube', 'user test find', '1', '1', '1');

INSERT INTO `cubetrendstestshema`.`trend` (`id`, `ubicacion`, `inicio`, `finalizacion`, 
`intervalo`, `busqueda`, `categoria`, `tipoBusqueda`, `incXdia`, `incXSemana`, `incXIntervalo`)
 VALUES (NULL, 'ubicacion', '1', '1', '10', 'find test', 'yotutube', 'user test find', '1', '1', '1');

INSERT INTO `cubetrendstestshema`.`trend` (`id`, `ubicacion`, `inicio`, `finalizacion`, 
`intervalo`, `busqueda`, `categoria`, `tipoBusqueda`, `incXdia`, `incXSemana`, `incXIntervalo`)
 VALUES (NULL, 'ubicacion', '1', '1', '10', 'find test', 'yotutube', 'user test find', '1', '1', '1');

INSERT INTO `cubetrendstestshema`.`trend` (`id`, `ubicacion`, `inicio`, `finalizacion`, 
`intervalo`, `busqueda`, `categoria`, `tipoBusqueda`, `incXdia`, `incXSemana`, `incXIntervalo`)
 VALUES (NULL, 'ubicacion', '1', '1', '10', 'find test', 'yotutube', 'user test find', '1', '1', '1');
