-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jan 18, 2021 at 06:40 PM
-- Server version: 8.0.21
-- PHP Version: 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `girasol`
--

-- --------------------------------------------------------

--
-- Table structure for table `administrador`
--

CREATE TABLE `administrador` (
  `idadministrador` bigint NOT NULL,
  `nombre` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `apellidos` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `id_usuario` bigint NOT NULL,
  `dni` varchar(10) COLLATE utf8mb4_general_ci NOT NULL,
  `sexo` varchar(2) COLLATE utf8mb4_general_ci NOT NULL,
  `edad` int NOT NULL,
  `telefono` int NOT NULL,
  `email` varchar(225) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `administrador`
--

INSERT INTO `administrador` (`idadministrador`, `nombre`, `apellidos`, `id_usuario`, `dni`, `sexo`, `edad`, `telefono`, `email`) VALUES
(1, 'sara', 'oliete lopez', 1, '2345678M', 'F', 19, 609734567, 'saraoliete@girasol.com');

-- --------------------------------------------------------

--
-- Table structure for table `cliente`
--

CREATE TABLE `cliente` (
  `idcliente` bigint NOT NULL,
  `nombre` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `apellidos` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `dni` varchar(10) COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `sexo` varchar(1) COLLATE utf8mb4_general_ci NOT NULL,
  `edad` int NOT NULL,
  `localidad` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `id_usuario` bigint NOT NULL,
  `telefono` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `cliente`
--

INSERT INTO `cliente` (`idcliente`, `nombre`, `apellidos`, `dni`, `email`, `sexo`, `edad`, `localidad`, `id_usuario`, `telefono`) VALUES
(1, 'carla', 'casitos buenos', '123456M', 'carlacasitos@gmail.com', 'F', 20, 'Madrid', 2, 654789234),
(2, 'fernando', 'mador de leones', '234567C', 'fernandomador@gmail.com', 'M', 35, 'Sevilla', 3, 687654321);

-- --------------------------------------------------------

--
-- Table structure for table `habitacion`
--

CREATE TABLE `habitacion` (
  `idhabitacion` bigint NOT NULL,
  `numero_camas` int NOT NULL,
  `precio` double(10,2) NOT NULL,
  `id_tipohabitacion` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `habitacion`
--

INSERT INTO `habitacion` (`idhabitacion`, `numero_camas`, `precio`, `id_tipohabitacion`) VALUES
(1, 1, 15.00, 1),
(2, 2, 17.00, 2),
(3, 3, 19.00, 3),
(4, 4, 21.00, 4),
(5, 1, 23.00, 5),
(6, 1, 25.00, 6),
(7, 2, 12.00, 7),
(8, 2, 25.00, 8),
(9, 1, 10.00, 9),
(10, 1, 30.00, 10),
(11, 3, 36.00, 11);

-- --------------------------------------------------------

--
-- Table structure for table `pension`
--

CREATE TABLE `pension` (
  `idpension` int NOT NULL,
  `tipo` varchar(225) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `descripcion` varchar(225) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `precio` double(4,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pension`
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
-- Table structure for table `reserva`
--

CREATE TABLE `reserva` (
  `idreserva` bigint NOT NULL,
  `fecha_inicio` datetime DEFAULT NULL,
  `id_pension` int NOT NULL,
  `id_habitacion` bigint NOT NULL,
  `id_administrador` bigint NOT NULL,
  `id_cliente` bigint NOT NULL,
  `cama_supletoria` tinyint(1) DEFAULT NULL,
  `precio_final` double(4,2) NOT NULL,
  `fecha_final` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `reserva`
--

INSERT INTO `reserva` (`idreserva`, `fecha_inicio`, `id_pension`, `id_habitacion`, `id_administrador`, `id_cliente`, `cama_supletoria`, `precio_final`, `fecha_final`) VALUES
(1, '2021-01-18 19:24:05', 5, 1, 1, 1, 1, 25.00, '2021-01-19 19:24:05'),
(2, '2021-01-25 19:26:14', 1, 2, 1, 2, 0, 17.00, '2021-01-26 19:26:14');

-- --------------------------------------------------------

--
-- Table structure for table `tipo_habitacion`
--

CREATE TABLE `tipo_habitacion` (
  `id_tipohabitacion` int NOT NULL,
  `nombre` varchar(250) COLLATE utf8mb4_general_ci NOT NULL,
  `descripcion` varchar(255) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tipo_habitacion`
--

INSERT INTO `tipo_habitacion` (`id_tipohabitacion`, `nombre`, `descripcion`) VALUES
(1, 'Individual', 'una habitación asignada a una persona.'),
(2, 'Doble', 'una habitación asignada a dos personas. '),
(3, 'Triple', 'una habitación asignada a tres personas. '),
(4, 'Quad', 'una habitación asignada a cuatro personas. '),
(5, 'Queen', 'una habitación con una cama de matrimonio.'),
(6, 'King', 'una habitación con una cama king-size.'),
(7, 'Twin', 'una habitación con dos camas.'),
(8, 'Doble-doble', 'una habitación con dos camas dobles.'),
(9, 'Estudio', 'una habitación con una cama de estudio, un sofá que se puede convertir en una cama.'),
(10, 'Mini-Suite o Junior Suite', 'una habitación individual con una cama y una sala de estar. '),
(11, 'Master Suite', 'un salón o sala de estar conectada a tres dormitorios ');

-- --------------------------------------------------------

--
-- Table structure for table `tipo_usuario`
--

CREATE TABLE `tipo_usuario` (
  `id_tipousuario` int NOT NULL,
  `nombre` varchar(255) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tipo_usuario`
--

INSERT INTO `tipo_usuario` (`id_tipousuario`, `nombre`) VALUES
(1, 'administrador'),
(2, 'cliente');

-- --------------------------------------------------------

--
-- Table structure for table `usuario`
--

CREATE TABLE `usuario` (
  `idusuario` bigint NOT NULL,
  `nombre` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(512) COLLATE utf8mb4_general_ci NOT NULL,
  `id_tipousuario` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `usuario`
--

INSERT INTO `usuario` (`idusuario`, `nombre`, `password`, `id_tipousuario`) VALUES
(1, 'saraoliete', 'girasol', 1),
(2, 'carlacasitos', 'girasol', 2),
(3, 'fernandito98', 'girasol', 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `administrador`
--
ALTER TABLE `administrador`
  ADD PRIMARY KEY (`idadministrador`),
  ADD KEY `usuario_administrador` (`id_usuario`);

--
-- Indexes for table `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`idcliente`),
  ADD KEY `usuario_cliente` (`id_usuario`);

--
-- Indexes for table `habitacion`
--
ALTER TABLE `habitacion`
  ADD PRIMARY KEY (`idhabitacion`),
  ADD KEY `tipo habitacion` (`id_tipohabitacion`);

--
-- Indexes for table `pension`
--
ALTER TABLE `pension`
  ADD PRIMARY KEY (`idpension`);

--
-- Indexes for table `reserva`
--
ALTER TABLE `reserva`
  ADD PRIMARY KEY (`idreserva`),
  ADD KEY `pension` (`id_pension`),
  ADD KEY `habitacion` (`id_habitacion`),
  ADD KEY `administrador` (`id_administrador`),
  ADD KEY `cliente` (`id_cliente`);

--
-- Indexes for table `tipo_habitacion`
--
ALTER TABLE `tipo_habitacion`
  ADD PRIMARY KEY (`id_tipohabitacion`);

--
-- Indexes for table `tipo_usuario`
--
ALTER TABLE `tipo_usuario`
  ADD PRIMARY KEY (`id_tipousuario`);

--
-- Indexes for table `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`idusuario`),
  ADD KEY `tipo usuario` (`id_tipousuario`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `administrador`
--
ALTER TABLE `administrador`
  ADD CONSTRAINT `usuario_administrador` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`idusuario`);

--
-- Constraints for table `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `usuario_cliente` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`idusuario`);

--
-- Constraints for table `habitacion`
--
ALTER TABLE `habitacion`
  ADD CONSTRAINT `tipo habitacion` FOREIGN KEY (`id_tipohabitacion`) REFERENCES `tipo_habitacion` (`id_tipohabitacion`);

--
-- Constraints for table `reserva`
--
ALTER TABLE `reserva`
  ADD CONSTRAINT `administrador` FOREIGN KEY (`id_administrador`) REFERENCES `administrador` (`idadministrador`),
  ADD CONSTRAINT `cliente` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`idcliente`),
  ADD CONSTRAINT `habitacion` FOREIGN KEY (`id_habitacion`) REFERENCES `habitacion` (`idhabitacion`),
  ADD CONSTRAINT `pension` FOREIGN KEY (`id_pension`) REFERENCES `pension` (`idpension`);

--
-- Constraints for table `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `tipo usuario` FOREIGN KEY (`id_tipousuario`) REFERENCES `tipo_usuario` (`id_tipousuario`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
