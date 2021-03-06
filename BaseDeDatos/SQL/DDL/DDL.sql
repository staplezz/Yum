/*
********************************************************
Base de datos para la empresa 'YUM', que representa
un restaurante manejado por un solo administrador y que
puede contratar a empleados para repartir las órdenes.
********************************************************
DBMS: MySQL 8.0
Versión 1.1
********************************************************
*/

CREATE DATABASE IF NOT EXISTS `yum_db`;
USE `yum_db`;

/* Tabla para representar a una persona dentro de la BDD, en
este caso serían los clientes y los repartidores que comparten
como atributo nombre, apellidos y correo electrónico. */
CREATE TABLE `Persona`(
	`idPersona` int NOT NULL AUTO_INCREMENT,
	`nombre` varchar(30) NOT NULL,
	`apellidoPaterno` varchar(20) NOT NULL,
	`apellidoMaterno` varchar(20) NOT NULL,
	`correoElectronico` varchar(50) NOT NULL,
	PRIMARY KEY (`idPersona`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;
 
/* Tabla para representar al administrador,
usamos un cifrado SHA1 para almacenar la contraseña del
administrador con un valor 'salt'. */
CREATE TABLE `Administrador`(
	`idAdministrador` int NOT NULL AUTO_INCREMENT,    
	`adminPassword` char(40) NOT NULL,
	`salt` int NOT NULL,
	CONSTRAINT administrador_pk PRIMARY KEY (idAdministrador)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

/* Tabla para representar un cliente en nuestro sistema,
un cliente tendrá dirección, teléfono, email y otra información básica.
Se usará cifrado SHA1 junto con un valor salt, generado aleatoriamente. */
CREATE TABLE `Cliente`(
	`idCliente` int NOT NULL AUTO_INCREMENT,
	`userPassword` char(40) NOT NULL,
	`salt` int NOT NULL,
	`telefono` varchar(10) NOT NULL,
	`idPersona` int NOT NULL,
	PRIMARY KEY (`idCliente`),
	FOREIGN KEY (`idPersona`) REFERENCES `Persona` (`idPersona`) 
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

/* Tabla para representar las direcciones de un cliente en el sistema,
un cliente puede tener varias direcciones */
CREATE TABLE `DireccionesCLiente`(
	`idCliente` int NOT NULL,
    `direccion` varchar(200) NOT NULL,
    PRIMARY KEY (`idCliente`, `direccion`),
    FOREIGN KEY (`idCliente`) REFERENCES `Cliente` (`idCliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/* Tabla para representar a un repartidor dentro del sistema. */
CREATE TABLE `Repartidor`(
	`idRepartidor` int NOT NULL AUTO_INCREMENT,
	`idPersona` int NOT NULL,
	PRIMARY KEY (`idRepartidor`),
	FOREIGN KEY (`idPersona`) REFERENCES `Persona`(`idPersona`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

/* Tabla para representar la categoría de un alimento */
CREATE TABLE `Categoria`(
	`idCategoria` int NOT NULL AUTO_INCREMENT,
	`nombre` varchar(30) NOT NULL,
	PRIMARY KEY (`idCategoria`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

/* Tabla para representar un alimento con sus características.*/
CREATE TABLE `Alimento`(
	`idAlimento` int NOT NULL AUTO_INCREMENT,
	`nombre` varchar(30) NOT NULL,
	`precio` decimal(6,2) NOT NULL CHECK (`precio` >= 0),
	`descripcion` varchar(100) NOT NULL,
	`idCategoria` int NOT NULL,
	PRIMARY KEY (`idAlimento`),
	FOREIGN KEY (`idCategoria`) REFERENCES `Categoria` (`idCategoria`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

/* Tabla para representar el carrito de un cliente. */
CREATE TABLE `Carrito`(
	`idCarrito` int NOT NULL AUTO_INCREMENT,
	`idCliente` int NOT NULL,
	PRIMARY KEY (`idCarrito`),
	FOREIGN KEY (`idCliente`) REFERENCES `Cliente` (`idCliente`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

/* Tabla para representar los alimentos que estarán dentro del carrito. */
CREATE TABLE `AlimentosCarrito`(
	`idCarrito` int NOT NULL,
	`idAlimento` int NOT NULL,
	FOREIGN KEY(`idCarrito`) REFERENCES `Carrito` (`idCarrito`),
 	FOREIGN KEY(`idAlimento`) REFERENCES `Alimento` (`idAlimento`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

/* Tabla para representar una órden de un cliente con sus características. */
CREATE TABLE `Orden`(
    `idOrden` int NOT NULL AUTO_INCREMENT,
    `fecha` date NOT NULL,
    `estado` tinyint NOT NULL,
    `calificacion` tinyint ,
    `idCliente` int NOT NULL,
    `idCarrito` int NOT NULL,
    `idRepartidor` int NOT NULL,
    PRIMARY KEY (`idOrden`),
    FOREIGN KEY(`idCliente`) REFERENCES `Cliente` (`idCliente`),
    FOREIGN KEY(`idCarrito`) REFERENCES `Carrito` (`idCarrito`),
    FOREIGN KEY(`idRepartidor`) REFERENCES `Repartidor` (`idRepartidor`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;
 
 /* Tabla para representar todas las órdenes de un cliente */
CREATE TABLE `OrdenesCliente`(
	`idCliente` int NOT NULL,
	`idOrden` int NOT NULL,
	FOREIGN KEY (`idCliente`) REFERENCES `Cliente` (`idCliente`),
	FOREIGN KEY (`idOrden`) REFERENCES `Orden` (`idOrden`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;
