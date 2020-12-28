-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 06-11-2020 a las 11:12:08
-- Versión del servidor: 10.4.14-MariaDB
-- Versión de PHP: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `springboot`
--

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
(6, 1, 100, 'Servicio Imprenta', 22, '2020-10-11', NULL, NULL),
(7, 1, 1234, 'Servicio res', 12, '2020-10-11', 4, '2020/0021'),
(8, 1, 1234, '13r', 12, '2020-10-11', 3, '2020/0023'),
(9, 1, 1234, '13r', 12, '2020-10-11', 2, '2020/0023');

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
-- Estructura de tabla para la tabla `ROLE`
--

CREATE TABLE `ROLE` (
  `ID_ROLE` int(11) NOT NULL,
  `NOM_ROLE` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `ROLE`
--

INSERT INTO `ROLE` (`ID_ROLE`, `NOM_ROLE`) VALUES
(1, 'USER'),
(2, 'CREATOR'),
(3, 'EDITOR'),
(4, 'ADMIN'),
(5, 'USER'),
(6, 'CREATOR'),
(7, 'EDITOR'),
(8, 'ADMIN'),
(9, 'USER'),
(10, 'CREATOR'),
(11, 'EDITOR'),
(12, 'ADMIN'),
(13, 'USER'),
(14, 'CREATOR'),
(15, 'EDITOR'),
(16, 'ADMIN'),
(17, 'USER'),
(18, 'CREATOR'),
(19, 'EDITOR'),
(20, 'ADMIN');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

CREATE TABLE `roles` (
  `role_id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`role_id`, `name`) VALUES
(1, 'USER'),
(2, 'CREATOR'),
(3, 'EDITOR'),
(4, 'ADMIN');

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

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `email` varchar(45) NOT NULL,
  `full_name` varchar(45) NOT NULL,
  `password` varchar(64) NOT NULL,
  `enabled` tinyint(4) DEFAULT NULL,
  `username` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`user_id`, `email`, `full_name`, `password`, `enabled`, `username`) VALUES
(1, '0', '', '$2a$10$cTUErxQqYVyU2qmQGIktpup5chLEdhD2zpzNEyYqmxrHHJbSNDOG.', 1, 'patrick'),
(3, '1', '', '$2a$10$cTUErxQqYVyU2qmQGIktpup5chLEdhD2zpzNEyYqmxrHHJbSNDOG.', 1, 'patrick'),
(4, '2', '', '$2a$10$.tP2OH3dEG0zms7vek4ated5AiQ.EGkncii0OpCcGq4bckS9NOULu', 1, 'alex'),
(5, '3', '', '$2a$10$E2UPv7arXmp3q0LzVzCBNeb4B4AtbTAGjkefVDnSztOwE7Gix6kea', 1, 'john'),
(6, '4', '', '$2a$10$GQT8bfLMaLYwlyUysnGwDu6HMB5G.tin5MKT/uduv2Nez0.DmhnOq', 1, 'namhm'),
(7, '5', '', '$2a$10$yjz2r/OiPJ2ms1khNbuQ4elUzuQXBUkQefnWG3NPOSloIDAhMh7MK', 1, 'admin');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users_roles`
--

CREATE TABLE `users_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `users_roles`
--

INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES
(1, 1),
(1, 1),
(3, 3),
(4, 2),
(4, 3),
(5, 4),
(7, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `USUARIO`
--

CREATE TABLE `USUARIO` (
  `ID_USUARIO` int(11) NOT NULL,
  `DIR_EMAIL` varchar(45) NOT NULL,
  `NOMBRE` varchar(45) NOT NULL,
  `PASSWORD` varchar(64) NOT NULL,
  `ENABLED` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `USUARIO`
--

INSERT INTO `USUARIO` (`ID_USUARIO`, `DIR_EMAIL`, `NOMBRE`, `PASSWORD`, `ENABLED`) VALUES
(1, '', 'patrick', '$2a$10$cTUErxQqYVyU2qmQGIktpup5chLEdhD2zpzNEyYqmxrHHJbSNDOG.', 1),
(2, '', 'alex', '$2a$10$.tP2OH3dEG0zms7vek4ated5AiQ.EGkncii0OpCcGq4bckS9NOULu', 1),
(3, '', 'john', '$2a$10$E2UPv7arXmp3q0LzVzCBNeb4B4AtbTAGjkefVDnSztOwE7Gix6kea', 1),
(4, '', 'namhm', '$2a$10$GQT8bfLMaLYwlyUysnGwDu6HMB5G.tin5MKT/uduv2Nez0.DmhnOq', 1),
(5, '', 'admin', '$2a$10$IqTJTjn39IU5.7sSCDQxzu3xug6z/LPU6IF0azE/8CkHCwYEnwBX.', 1),
(6, '', 'patrick', '$2a$10$cTUErxQqYVyU2qmQGIktpup5chLEdhD2zpzNEyYqmxrHHJbSNDOG.', 1),
(7, '', 'alex', '$2a$10$.tP2OH3dEG0zms7vek4ated5AiQ.EGkncii0OpCcGq4bckS9NOULu', 1),
(8, '', 'john', '$2a$10$E2UPv7arXmp3q0LzVzCBNeb4B4AtbTAGjkefVDnSztOwE7Gix6kea', 1),
(9, '', 'namhm', '$2a$10$GQT8bfLMaLYwlyUysnGwDu6HMB5G.tin5MKT/uduv2Nez0.DmhnOq', 1),
(10, '', 'admin', '$2a$10$IqTJTjn39IU5.7sSCDQxzu3xug6z/LPU6IF0azE/8CkHCwYEnwBX.', 1),
(11, '', 'patrick', '$2a$10$cTUErxQqYVyU2qmQGIktpup5chLEdhD2zpzNEyYqmxrHHJbSNDOG.', 1),
(12, '', 'alex', '$2a$10$.tP2OH3dEG0zms7vek4ated5AiQ.EGkncii0OpCcGq4bckS9NOULu', 1),
(13, '', 'john', '$2a$10$E2UPv7arXmp3q0LzVzCBNeb4B4AtbTAGjkefVDnSztOwE7Gix6kea', 1),
(14, '', 'namhm', '$2a$10$GQT8bfLMaLYwlyUysnGwDu6HMB5G.tin5MKT/uduv2Nez0.DmhnOq', 1),
(15, '', 'admin', '$2a$10$IqTJTjn39IU5.7sSCDQxzu3xug6z/LPU6IF0azE/8CkHCwYEnwBX.', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `USUARIO_ROLE`
--

CREATE TABLE `USUARIO_ROLE` (
  `ID_USUARIO_FK` int(11) NOT NULL,
  `ID_ROLE_FK` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `USUARIO_ROLE`
--

INSERT INTO `USUARIO_ROLE` (`ID_USUARIO_FK`, `ID_ROLE_FK`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 2),
(4, 3),
(5, 4);

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
-- Indices de la tabla `ROLE`
--
ALTER TABLE `ROLE`
  ADD PRIMARY KEY (`ID_ROLE`);

--
-- Indices de la tabla `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`role_id`);

--
-- Indices de la tabla `TPO_CLIENTE`
--
ALTER TABLE `TPO_CLIENTE`
  ADD PRIMARY KEY (`ID_TPO_CLIENTE`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `email_UNIQUE` (`email`);

--
-- Indices de la tabla `users_roles`
--
ALTER TABLE `users_roles`
  ADD KEY `role_id` (`role_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indices de la tabla `USUARIO`
--
ALTER TABLE `USUARIO`
  ADD PRIMARY KEY (`ID_USUARIO`);

--
-- Indices de la tabla `USUARIO_ROLE`
--
ALTER TABLE `USUARIO_ROLE`
  ADD KEY `role_fk` (`ID_ROLE_FK`),
  ADD KEY `usuario_fk` (`ID_USUARIO_FK`);

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
  MODIFY `ID_FACTURA` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `FORMA_PAGO`
--
ALTER TABLE `FORMA_PAGO`
  MODIFY `ID_FOR_PAGO` int(1) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `ROLE`
--
ALTER TABLE `ROLE`
  MODIFY `ID_ROLE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `roles`
--
ALTER TABLE `roles`
  MODIFY `role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `USUARIO`
--
ALTER TABLE `USUARIO`
  MODIFY `ID_USUARIO` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

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

--
-- Filtros para la tabla `users_roles`
--
ALTER TABLE `users_roles`
  ADD CONSTRAINT `users_roles_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`),
  ADD CONSTRAINT `users_roles_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

--
-- Filtros para la tabla `USUARIO_ROLE`
--
ALTER TABLE `USUARIO_ROLE`
  ADD CONSTRAINT `role_fk` FOREIGN KEY (`ID_ROLE_FK`) REFERENCES `ROLE` (`ID_ROLE`),
  ADD CONSTRAINT `usuario_fk` FOREIGN KEY (`ID_USUARIO_FK`) REFERENCES `USUARIO` (`ID_USUARIO`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
