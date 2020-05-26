USE yum_db;

INSERT INTO `yum_db`.`persona` (`nombre`, `apellidoPaterno`, `apellidoMaterno`, `password`, `correoElectronico`) VALUES ('Alberto', 'Arándano', 'Álvarez', 'password', 'a@arandano.com');
INSERT INTO `yum_db`.`persona` (`nombre`, `apellidoPaterno`, `apellidoMaterno`, `password`, `correoElectronico`) VALUES ('Berta', 'Bolívar', 'Bolsa', 'password', 'b@bolivar.com');
INSERT INTO `yum_db`.`persona` (`nombre`, `apellidoPaterno`, `apellidoMaterno`, `password`, `correoElectronico`) VALUES ('Carla', 'Cortes', 'Contreras', 'password', 'c@caldo.com');

INSERT INTO `yum_db`.`administrador` (`idPersona`) VALUES ('1');

INSERT INTO `yum_db`.`categoria` (`nombre`) VALUES ('Botanas');
INSERT INTO `yum_db`.`categoria` (`nombre`) VALUES ('Carnes');

INSERT INTO `yum_db`.`alimento` (`nombre`, `precio`, `descripcion`, `idCategoria`) VALUES ('Papas Fritas', '15', 'La papa perfecta para cuando tienes flojera', '1');
INSERT INTO `yum_db`.`alimento` (`nombre`, `precio`, `descripcion`, `idCategoria`) VALUES ('Chuleta', '100', '1 kilogramo de chuleta', '2');
INSERT INTO `yum_db`.`alimento` (`nombre`, `precio`, `descripcion`, `idCategoria`) VALUES ('Fritos', '7', 'Papa especial para acompañar con cerveza', '1');
INSERT INTO `yum_db`.`alimento` (`nombre`, `precio`, `descripcion`, `idCategoria`) VALUES ('Arrachera', '300', '1 kilogramo de carne de los dioses', '2');
