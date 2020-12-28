-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 03-12-2020 a las 10:52:38
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
  `TELEFONO` varchar(14) NOT NULL,
  `FEC_ALTA_CLIENTE` date DEFAULT NULL COMMENT 'Fecha de alta del cliente en la aplicación'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `CLIENTE`
--

INSERT INTO `CLIENTE` (`ID_CLIENTE`, `NOMBRE`, `APELLIDOS`, `FEC_NACIMIENTO`, `DNI`, `DIRECCION`, `PAIS`, `ID_TPO_CLIENTE_FK`, `DIR_EMAIL`, `TELEFONO`, `FEC_ALTA_CLIENTE`) VALUES
(1, 'Pedro', 'Perez Martinez', '2010-09-01', 1925222, 'Lopez de Hoyos, 32, 3º A', 'España', 1, 'dire@movistar.com', '4343244234', '2020-10-01'),
(2, 'Rosa', 'Perez Ruiz', '2010-09-01', 1925222, 'Lopez de Hoyos, 10, 3º A', 'España', 1, 'jlbu@gmail.com', '917724822', '2020-07-01'),
(3, 'Rodrigo', 'Perez Molina', '2017-08-08', 32321233, 'Lopez de Ayala, 3 , 4º F', 'España', 1, '', '', '2020-04-01'),
(4, 'Tomas', 'Perez Ruiz', '2010-08-08', 32125533, 'Luis de Hoyos, 3 , 4º F', 'España', 1, 'u23da@movistar.com', '', NULL),
(5, 'Jorge', 'Semprum', '2010-09-11', 1925233, 'Lopez de Hoyos, 15, 3º A', 'España', 2, '', '606353433', '2020-06-01'),
(6, 'Pedro', 'Sanchez', '2000-09-11', 19235233, 'Moncloa, 15, 3º A', 'España', 2, '', '', NULL),
(7, 'Silvia', 'Garcia Sanchez', '1994-09-19', 135233, 'Vinateros, 15, 3º A', 'España', 2, '', '', NULL),
(8, 'Almudena', 'Garcia Ruiz', '1993-09-19', 11135233, 'Avda. Moratalaz, 15, 3º A', 'España', 2, 'pabede@gmail.com', '917724422', '2020-02-01'),
(9, 'Luis', 'Sanchez', '2018-12-02', 232332, 'Lopez de Hoyos, 55, 3º A', 'España', 1, 'psanche@gmail.com', '606454534', '2020-05-01');

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
  `user_id` int(11) NOT NULL COMMENT 'Identificador unico de usuario',
  `email` varchar(45) NOT NULL COMMENT 'email usuario para operaciones automaticas',
  `full_name` varchar(45) NOT NULL COMMENT 'Alias del usuario',
  `password` varchar(64) NOT NULL COMMENT 'Clave de usuario para entrar en la apliciacion',
  `enabled` tinyint(1) DEFAULT NULL COMMENT 'Identifica si el usuario esta activo  como usario en la aplicaion',
  `username` varchar(30) NOT NULL COMMENT 'Nombre o alias para visualizar en el Web en lugar de username',
  `indempleado` tinyint(1) DEFAULT NULL COMMENT 'Identifica si el usuario es empleado',
  `fecaltausuario` datetime DEFAULT NULL COMMENT 'Fecha y hora de alta de un usuario de la aplicacion',
  `fecbajausuario` datetime DEFAULT NULL COMMENT 'Fecha y hora de baja de un usuario de la aplicacion',
  `idempleadofk` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`user_id`, `email`, `full_name`, `password`, `enabled`, `username`, `indempleado`, `fecaltausuario`, `fecbajausuario`, `idempleadofk`) VALUES
(1, 'jlbuenome.andro@gmail.com', 'Patrick', '$2a$10$cTUErxQqYVyU2qmQGIktpup5chLEdhD2zpzNEyYqmxrHHJbSNDOG.', 1, 'patrick', 0, '2020-11-02 22:09:24', NULL, NULL),
(3, 'jlbuenome3.andro@gmail.com', 'Pedro', '$2a$10$cTUErxQqYVyU2qmQGIktpup5chLEdhD2zpzNEyYqmxrHHJbSNDOG.', 1, 'pedro', 0, '2020-10-01 22:09:44', NULL, NULL),
(4, 'jlbuenome4.andro@gmail.como', 'Alex', '$2a$10$.tP2OH3dEG0zms7vek4ated5AiQ.EGkncii0OpCcGq4bckS9NOULu', 1, 'alex', 0, '2020-09-09 22:09:51', NULL, NULL),
(5, 'jlbuenome5.andro@gmail.com', 'John', '$2a$10$E2UPv7arXmp3q0LzVzCBNeb4B4AtbTAGjkefVDnSztOwE7Gix6kea', 1, 'john', 0, '2020-03-02 22:09:58', NULL, NULL),
(6, 'jlbuenome6.andro@gmail.com', 'Mamhm', '$2a$10$GQT8bfLMaLYwlyUysnGwDu6HMB5G.tin5MKT/uduv2Nez0.DmhnOq', 1, 'namhm', 0, '2020-04-08 22:10:06', NULL, NULL),
(7, 'jlbuenome7.andro@gmail.com', 'Administrador', '$2a$10$yjz2r/OiPJ2ms1khNbuQ4elUzuQXBUkQefnWG3NPOSloIDAhMh7MK', 1, 'admin', 0, '2020-03-01 22:10:12', NULL, NULL);

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
  ADD UNIQUE KEY `email_UNIQUE` (`email`),
  ADD KEY `EMPLEADO_IDEMPLEADO_FK` (`idempleadofk`);

--
-- Indices de la tabla `users_roles`
--
ALTER TABLE `users_roles`
  ADD KEY `role_id` (`role_id`),
  ADD KEY `user_id` (`user_id`);

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
-- AUTO_INCREMENT de la tabla `roles`
--
ALTER TABLE `roles`
  MODIFY `role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Identificador unico de usuario', AUTO_INCREMENT=8;

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
-- Filtros para la tabla `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `EMPLEADO_IDEMPLEADO_FK` FOREIGN KEY (`idempleadofk`) REFERENCES `EMPLEADO` (`ID_EMPLEADO`);

--
-- Filtros para la tabla `users_roles`
--
ALTER TABLE `users_roles`
  ADD CONSTRAINT `users_roles_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`),
  ADD CONSTRAINT `users_roles_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
