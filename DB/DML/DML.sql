USE yum_db;

/* DML para poblar la BDD con datos ficticios. */

/* DML personas */
INSERT INTO `persona` (`nombre`, `apellidoPaterno`, `apellidoMaterno`, `password`, `correoElectronico`) VALUES ('Alberto', 'Arándano', 'Álvarez', 'password', 'a@arandano.com');
INSERT INTO `persona` (`nombre`, `apellidoPaterno`, `apellidoMaterno`, `password`, `correoElectronico`) VALUES ('Berta', 'Bolívar', 'Bolsa', '$2a$10$BMD.aVwWA98TfUZHMyChaucuP9yyAfQQ2iU5ya1hfMizuZJ/PaQ/C', 'b@bolivar.com');
INSERT INTO `persona` (`nombre`, `apellidoPaterno`, `apellidoMaterno`, `password`, `correoElectronico`) VALUES ('Carla', 'Cortes', 'Contreras', 'password', 'c@caldo.com');

/* DML admin */
INSERT INTO `administrador` (`idPersona`) VALUES ('1');

/* DML cliente, su carrito y su dirección*/
INSERT INTO `cliente` (`salt`, `telefono`, `idPersona`) VALUES ('1', '5565678960','2');
INSERT INTO `carrito` (`idCliente`) VALUES ('1');
INSERT INTO `direccion` (`delegacion`, `colonia`, `calle`, `num_interior`, `num_exterior`) VALUES ('Benito Juarez', 'Portales', 'Saratoga', '87', null);
/* Agregamos la dirección y ponemos 1 en default, ya que será la dirección por defecto que se use
* para la repartación de la orden. Si no fuera default, ponemos 0.*/
INSERT INTO `direccionescliente` (`idDireccion`, `idCliente`) VALUES ('1', '1');

/* DML repartidor */
INSERT INTO `repartidor` (`idPersona`) VALUES ('3');

/* DML categorias */
INSERT INTO `categoria` (`nombre`) VALUES ('Botanas');
INSERT INTO `categoria` (`nombre`) VALUES ('Carnes');
/* Para alimentos sin categoria */
INSERT INTO `categoria` (`idCategoria`, `nombre`) VALUES ('100', 'Sin categoria');

/* DML alimentos */
INSERT INTO `alimento` (`nombre`, `precio`, `descripcion`, `idCategoria`) VALUES ('Papas Fritas', '15', 'La papa perfecta para cuando tienes flojera', '1');
INSERT INTO `alimento` (`nombre`, `precio`, `descripcion`, `idCategoria`) VALUES ('Chuleta', '100', '1 kilogramo de chuleta', '2');
INSERT INTO `alimento` (`nombre`, `precio`, `descripcion`, `idCategoria`) VALUES ('Fritos', '7', 'Papa especial para acompañar con cerveza', '1');
INSERT INTO `alimento` (`nombre`, `precio`, `descripcion`, `idCategoria`) VALUES ('Arrachera', '300', '1 kilogramo de carne de los dioses', '2');

/* DML para crear una órden */
/* Primero poblamos los alimentos del carrito. */
INSERT INTO `alimentoscarrito` (`idCarrito`, `idAlimento`, `cantidad`) VALUES ('1', '1', '2');
INSERT INTO `alimentoscarrito` (`idCarrito`, `idAlimento`, `cantidad`) VALUES ('1', '2', '1');

/* Una vez que el cliente pida confirmar la órden, creamos un registro
 * para la órden que queremos almacenar del cliente */
INSERT INTO `ordenescliente` (`idCliente`) VALUES ('1');

/* Luego copiamos los alimentos del carrito
* para que se almacenen usando la llave que recibimos en la inserción anterior.
*/
INSERT INTO `alimentosorden` (`idOrdenesCliente`, `idAlimento`, `cantidad`)
SELECT /*Aquí iría la llave ->*/ '1', `idAlimento`, `cantidad`
FROM `alimentoscarrito` WHERE `idCarrito` = '1';

/* Una vez guardados los alimentos, idealemnente tendríamos que borrar los alimentos del carrito 
* DELETE FROM `alimentoscarrito` WHERE `idCarrito` = '1';
*/

/* Finalmente ya podemos generar la órden en la BDD usando la llave que usamos en la tabla alimentosorden
* para así llevar registro de las órdenes de cada cliente.
* (Nota: al incio una orden tendrá el repartidor vacío, porque aún nadie toma la órden hasta que el estado
* sea: listo para entregar) 
*/
INSERT INTO `orden` (`fecha`, `estado`, `calificacion`, `idCliente`, `idOrdenesCliente`, `idDireccionCliente`, `idRepartidor`)
VALUES ('2020-05-31', '1', null, '1', '1', '1', null);
