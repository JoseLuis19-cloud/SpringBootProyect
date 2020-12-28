-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 30-10-2020 a las 12:20:40
-- Versión del servidor: 10.4.14-MariaDB
-- Versión de PHP: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

--
-- Base de datos: `springboot`
--
CREATE DATABASE IF NOT EXISTS `springboot` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `springboot`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `CLIENTE`
--

CREATE TABLE `CLIENTE` (
  `ID_CLIENTE` int(11) NOT NULL,
  `NOMBRE` varchar(30) NOT NULL,
  `APELLIDOS` varchar(40) NOT NULL,
  `FEC_NACIMIENTO` date NOT NULL,
  `DNI` int(14) NOT NULL,
  `DIRECCION` varchar(50) NOT NULL,
  `PAIS` varchar(35) NOT NULL,
  `ID_TPO_CLIENTE_FK` int(11) NOT NULL,
  `DIR_EMAIL` varchar(35) NOT NULL,
  `TELEFONO` varchar(14) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `CLIENTE`
--

INSERT INTO `CLIENTE` (`ID_CLIENTE`, `NOMBRE`, `APELLIDOS`, `FEC_NACIMIENTO`, `DNI`, `DIRECCION`, `PAIS`, `ID_TPO_CLIENTE_FK`, `DIR_EMAIL`, `TELEFONO`) VALUES
(1, 'Pedro', 'Perez Martinez', '2010-09-01', 1925222, 'Lopez de Hoyos, 32, 3º A', 'España', 1, 'dire@movistar.com', '4343244234'),
(2, 'Rosa', 'Perez Ruiz', '2010-09-01', 1925222, 'Lopez de Hoyos, 10, 3º A', 'España', 1, 'jlbu@gmail.com', '917724822'),
(3, 'Rodrigo', 'Perez Molina', '2017-08-08', 32321233, 'Lopez de Ayala, 3 , 4º F', 'España', 1, '', ''),
(4, 'Tomas', 'Perez Ruiz', '2010-08-08', 32125533, 'Luis de Hoyos, 3 , 4º F', 'España', 1, 'u23da@movistar.com', ''),
(5, 'Jorge', 'Semprum', '2010-09-11', 1925233, 'Lopez de Hoyos, 15, 3º A', 'España', 2, '', '606353433'),
(6, 'Pedro', 'Sanchez', '2000-09-11', 19235233, 'Moncloa, 15, 3º A', 'España', 2, '', ''),
(7, 'Silvia', 'Garcia Sanchez', '1994-09-19', 135233, 'Vinateros, 15, 3º A', 'España', 2, '', ''),
(8, 'Almudena', 'Garcia Ruiz', '1993-09-19', 11135233, 'Avda. Moratalaz, 15, 3º A', 'España', 2, 'pabede@gmail.com', ''),
(9, 'Luis', 'Sanchez', '2018-12-02', 232332, 'Lopez de Hoyos, 55, 3º A', 'España', 1, 'psanche@gmail.com', '606454534');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `FACTURA`
--

CREATE TABLE `FACTURA` (
  `ID_FACTURA` int(11) NOT NULL,
  `ID_CLIENTE_FK` int(11) NOT NULL,
  `IMP_FACTURA` float NOT NULL,
  `CONCEPTO` varchar(100) NOT NULL,
  `POR_IVA` int(11) NOT NULL,
  `FEC_FACTURA` date DEFAULT NULL,
  `ID_FOR_PAGO_FK` int(1) DEFAULT NULL,
  `NUM_FACTURA` varchar(14) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `FACTURA`
--

INSERT INTO `FACTURA` (`ID_FACTURA`, `ID_CLIENTE_FK`, `IMP_FACTURA`, `CONCEPTO`, `POR_IVA`, `FEC_FACTURA`, `ID_FOR_PAGO_FK`, `NUM_FACTURA`) VALUES
(1, 1, 112, 'Servicio', 22, '2020-10-11', NULL, '2020/0001'),
(2, 1, 1124, 'Servicio', 22, '2022-10-11', NULL, '2020/0002'),
(3, 1, 1123, 'Servicio', 22, '2022-10-11', NULL, '2020/0003'),
(4, 1, 100, 'Servicio', 22, '2020-10-11', NULL, '2020/0004'),
(5, 1, 100, 'Servicio', 22, '2020-10-11', NULL, '2020/0005'),
(6, 1, 100, 'Servicio Imprenta', 22, '2020-10-11', NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `FORMA_PAGO`
--

CREATE TABLE `FORMA_PAGO` (
  `ID_FOR_PAGO` int(1) NOT NULL,
  `DES_FOR_PAGO` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `FORMA_PAGO`
--

INSERT INTO `FORMA_PAGO` (`ID_FOR_PAGO`, `DES_FOR_PAGO`) VALUES
(1, 'Pago al Contado'),
(2, 'Pago a 30 días'),
(3, 'Pago a 60 días'),
(4, 'Pago a 90 días');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

CREATE TABLE `persona` (
  `nombre` varchar(25) NOT NULL,
  `apellidos` varchar(25) NOT NULL,
  `edad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `persona`
--

INSERT INTO `persona` (`nombre`, `apellidos`, `edad`) VALUES
('Ana', 'Ruiz', 11),
('Jose Luis', 'B', 12);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `TPO_CLIENTE`
--

CREATE TABLE `TPO_CLIENTE` (
  `ID_TPO_CLIENTE` int(11) NOT NULL,
  `DES_TPO_CLIENTE` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `TPO_CLIENTE`
--

INSERT INTO `TPO_CLIENTE` (`ID_TPO_CLIENTE`, `DES_TPO_CLIENTE`) VALUES
(1, 'Cliente normal'),
(2, 'Cliente VIP'),
(3, 'Cliente Jubilado');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `CLIENTE`
--
ALTER TABLE `CLIENTE`
  ADD PRIMARY KEY (`ID_CLIENTE`),
  ADD KEY `ID_TPO_CLIENTE_FK` (`ID_TPO_CLIENTE_FK`);

--
-- Indices de la tabla `FACTURA`
--
ALTER TABLE `FACTURA`
  ADD PRIMARY KEY (`ID_FACTURA`),
  ADD UNIQUE KEY `ID_FACTURA` (`ID_FACTURA`),
  ADD KEY `ID_CLIENTE_FK` (`ID_CLIENTE_FK`),
  ADD KEY `ID_FOR_PAGO_FK` (`ID_FOR_PAGO_FK`);

--
-- Indices de la tabla `FORMA_PAGO`
--
ALTER TABLE `FORMA_PAGO`
  ADD PRIMARY KEY (`ID_FOR_PAGO`);

--
-- Indices de la tabla `persona`
--
ALTER TABLE `persona`
  ADD PRIMARY KEY (`nombre`);

--
-- Indices de la tabla `TPO_CLIENTE`
--
ALTER TABLE `TPO_CLIENTE`
  ADD PRIMARY KEY (`ID_TPO_CLIENTE`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `CLIENTE`
--
ALTER TABLE `CLIENTE`
  MODIFY `ID_CLIENTE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `FACTURA`
--
ALTER TABLE `FACTURA`
  MODIFY `ID_FACTURA` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `FORMA_PAGO`
--
ALTER TABLE `FORMA_PAGO`
  MODIFY `ID_FOR_PAGO` int(1) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `CLIENTE`
--
ALTER TABLE `CLIENTE`
  ADD CONSTRAINT `cliente_ibfk_1` FOREIGN KEY (`ID_TPO_CLIENTE_FK`) REFERENCES `TPO_CLIENTE` (`ID_TPO_CLIENTE`),
  ADD CONSTRAINT `cliente_ibfk_2` FOREIGN KEY (`ID_TPO_CLIENTE_FK`) REFERENCES `TPO_CLIENTE` (`ID_TPO_CLIENTE`);

--
-- Filtros para la tabla `FACTURA`
--
ALTER TABLE `FACTURA`
  ADD CONSTRAINT `factura_ibfk_1` FOREIGN KEY (`ID_FOR_PAGO_FK`) REFERENCES `FORMA_PAGO` (`ID_FOR_PAGO`);
COMMIT;
