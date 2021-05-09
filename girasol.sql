-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 06-02-2021 a las 19:55:12
-- Versión del servidor: 10.4.14-MariaDB
-- Versión de PHP: 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `girasol`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `habitacion`
--

CREATE TABLE `habitacion` (
  `idhabitacion` bigint(20) NOT NULL,
  `id_tipohabitacion` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `habitacion`
--

INSERT INTO `habitacion` (`idhabitacion`, `id_tipohabitacion`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10),
(11, 11);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pension`
--

CREATE TABLE `pension` (
  `idpension` bigint(20) NOT NULL,
  `tipo` varchar(225) DEFAULT NULL,
  `descripcion` varchar(225) DEFAULT NULL,
  `precio` double(4,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `pension`
--

INSERT INTO `pension` (`idpension`, `tipo`, `descripcion`, `precio`) VALUES
(1, 'SA', 'Sólo alojamiento', 0.00),
(2, 'AD o HD', 'Alojamiento y desayuno', 2.00),
(3, 'HM', 'primer día alojamiento segundo media pensión y tercer desayuno. Esta combinación se suele ofrecer en escapadas de fin de semana o en paquetes de nieve.', 5.00),
(4, 'MP', 'media pensión. Es decir, dos comidas que suelen ser el desayuno y la cena.', 7.00),
(5, 'PC', 'pensión completa, que incluye desayuno, almuerzo y cena. No suelen incluir las bebidas.', 10.00),
(6, 'Todo incluido', 'es una pensión completa con más servicios adicionales como pueden ser las bebidas, tentempiés durante el día o lo que el hotel oferte.', 15.00),
(7, 'SP', 'según programa. En este caso tienes que consultar con el hotel.', 16.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reserva`
--

CREATE TABLE `reserva` (
  `idreserva` bigint(20) NOT NULL,
  `id_usuario` bigint(20) NOT NULL,
  `id_pension` bigint(20) NOT NULL,
  `id_habitacion` bigint(20) NOT NULL,
  `cama_supletoria` tinyint(1) DEFAULT NULL,
  `fecha_llegada` datetime DEFAULT NULL,
  `fecha_final` datetime NOT NULL,
  `precio_final` double(4,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `reserva`
--

INSERT INTO `reserva` (`idreserva`, `id_usuario`, `id_pension`, `id_habitacion`, `cama_supletoria`, `fecha_llegada`, `fecha_final`, `precio_final`) VALUES
(1, 2, 5, 1, 1, '2021-01-18 19:24:05', '2021-01-19 19:24:05', 25.00),
(2, 3, 1, 2, 0, '2021-01-25 19:26:14', '2021-01-26 19:26:14', 17.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_habitacion`
--

CREATE TABLE `tipo_habitacion` (
  `id_tipohabitacion` bigint(20) NOT NULL,
  `nombre` varchar(250) NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  `numero_camas` int(11) NOT NULL,
  `precio` double(4,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tipo_habitacion`
--

INSERT INTO `tipo_habitacion` (`id_tipohabitacion`, `nombre`, `descripcion`, `numero_camas`, `precio`) VALUES
(1, 'Individual', 'una habitación asignada a una persona.', 1, 15.00),
(2, 'Doble', 'una habitación asignada a dos personas. ', 2, 17.00),
(3, 'Triple', 'una habitación asignada a tres personas. ', 3, 19.00),
(4, 'Quad', 'una habitación asignada a cuatro personas. ', 4, 21.00),
(5, 'Queen', 'una habitación con una cama de matrimonio.', 1, 23.00),
(6, 'King', 'una habitación con una cama king-size.', 1, 25.00),
(7, 'Twin', 'una habitación con dos camas.', 2, 17.00),
(8, 'Doble-doble', 'una habitación con dos camas dobles.', 2, 25.00),
(9, 'Estudio', 'una habitación con una cama de estudio, un sofá que se puede convertir en una cama.', 1, 10.00),
(10, 'Mini-Suite o Junior Suite', 'una habitación individual con una cama y una sala de estar. ', 1, 30.00),
(11, 'Master Suite', 'un salón o sala de estar conectada a tres dormitorios ', 3, 36.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_usuario`
--

CREATE TABLE `tipo_usuario` (
  `id_tipousuario` bigint(20) NOT NULL,
  `nombre` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tipo_usuario`
--

INSERT INTO `tipo_usuario` (`id_tipousuario`, `nombre`) VALUES
(1, 'administrador'),
(2, 'cliente');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `idusuario` bigint(20) NOT NULL,
  `nombreusuario` varchar(225) NOT NULL,
  `password` varchar(512) NOT NULL,
  `id_tipousuario` bigint(20) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `apellidos` varchar(255) NOT NULL,
  `dni` varchar(10) NOT NULL,
  `sexo` char(1) NOT NULL,
  `email` varchar(255) NOT NULL,
  `localidad` varchar(255) NOT NULL,
  `telefono` int(9) NOT NULL,
  `edad` int(3) NOT NULL,
  `token` varchar(512) DEFAULT NULL,
  `validado` tinyint(1) NOT NULL,
  `activo` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`idusuario`, `nombreusuario`, `password`, `id_tipousuario`, `nombre`, `apellidos`, `dni`, `sexo`, `email`, `localidad`, `telefono`, `edad`, `token`, `validado`, `activo`) VALUES
(1, 'saraoliete', 'girasol', 1, 'sara', 'oliete lopez', '23940406M', 'F', 'saraoliete@girasol.com', 'valencia', 644365783, 19, NULL, 1, 1),
(2, 'carlacasitos', 'girasol', 2, 'carla', 'casitos buenos', '38493789L', 'F', 'carlacasitos@gmail.com', 'madrid', 600896723, 22, NULL, 1, 1),
(3, 'fernandito98', 'girasol', 2, 'fernando', 'coslada martinez', '67789542K', 'M', 'fernandocoslada@gmail.com', 'madrid', 678900423, 22, NULL, 1, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `habitacion`
--
ALTER TABLE `habitacion`
  ADD PRIMARY KEY (`idhabitacion`),
  ADD KEY `tipo habitacion` (`id_tipohabitacion`);

--
-- Indices de la tabla `pension`
--
ALTER TABLE `pension`
  ADD PRIMARY KEY (`idpension`);

--
-- Indices de la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD PRIMARY KEY (`idreserva`),
  ADD KEY `pension` (`id_pension`),
  ADD KEY `habitacion` (`id_habitacion`),
  ADD KEY `usuario` (`id_usuario`);

--
-- Indices de la tabla `tipo_habitacion`
--
ALTER TABLE `tipo_habitacion`
  ADD PRIMARY KEY (`id_tipohabitacion`);

--
-- Indices de la tabla `tipo_usuario`
--
ALTER TABLE `tipo_usuario`
  ADD PRIMARY KEY (`id_tipousuario`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`idusuario`),
  ADD KEY `tipo usuario` (`id_tipousuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `habitacion`
--
ALTER TABLE `habitacion`
  MODIFY `idhabitacion` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `reserva`
--
ALTER TABLE `reserva`
  MODIFY `idreserva` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `idusuario` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `habitacion`
--
ALTER TABLE `habitacion`
  ADD CONSTRAINT `tipo habitacion` FOREIGN KEY (`id_tipohabitacion`) REFERENCES `tipo_habitacion` (`id_tipohabitacion`);

--
-- Filtros para la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD CONSTRAINT `habitacion` FOREIGN KEY (`id_habitacion`) REFERENCES `habitacion` (`idhabitacion`),
  ADD CONSTRAINT `pension` FOREIGN KEY (`id_pension`) REFERENCES `pension` (`idpension`),
  ADD CONSTRAINT `usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`idusuario`);

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `tipo usuario` FOREIGN KEY (`id_tipousuario`) REFERENCES `tipo_usuario` (`id_tipousuario`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
