-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 23-05-2025 a las 19:12:05
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `gestion_eventos`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `consultar_disponibilidad` (IN `p_id_evento` INT, OUT `p_capacidad_restante` INT)   BEGIN
  SELECT capacidad_disponible INTO p_capacidad_restante FROM evento WHERE id_evento = p_id_evento;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `registrar_compra` (IN `p_id_evento` INT, IN `p_id_usuario` INT, IN `p_cantidad` INT)   BEGIN
  DECLARE v_capacidad_disponible INT;

  SELECT capacidad_disponible INTO v_capacidad_disponible FROM evento WHERE id_evento = p_id_evento;

  IF v_capacidad_disponible < p_cantidad THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'No hay suficiente capacidad disponible';
  ELSE
    INSERT INTO entrada (id_evento, id_usuario, cantidad_entradas, fecha_compra)
    VALUES (p_id_evento, p_id_usuario, p_cantidad, NOW());

    UPDATE evento SET capacidad_disponible = capacidad_disponible - p_cantidad WHERE id_evento = p_id_evento;
  END IF;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `entrada`
--

CREATE TABLE `entrada` (
  `id_entrada` int(11) NOT NULL,
  `id_evento` int(11) NOT NULL,
  `comprador` varchar(100) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `cantidad` int(11) NOT NULL,
  `fecha_compra` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `entrada`
--

INSERT INTO `entrada` (`id_entrada`, `id_evento`, `comprador`, `email`, `cantidad`, `fecha_compra`) VALUES
(1, 1, 'Ana Pérez', 'ana.perez@email.com', 2, '2025-06-01'),
(2, 1, 'Luis García', 'luis.garcia@email.com', 4, '2025-06-05'),
(3, 2, 'María López', 'maria.lopez@email.com', 1, '2025-05-15'),
(4, 3, 'Carlos Martínez', 'carlos.martinez@email.com', 3, '2025-07-20'),
(5, 4, 'Elena Torres', 'elena.torres@email.com', 2, '2025-08-01'),
(6, 5, 'Jorge Díaz', 'jorge.diaz@email.com', 1, '2025-09-10'),
(7, 6, 'Lucía Fernández', 'lucia.fernandez@email.com', 5, '2025-07-15'),
(8, 7, 'Pedro Sánchez', 'pedro.sanchez@email.com', 2, '2025-10-20');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `evento`
--

CREATE TABLE `evento` (
  `id_evento` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `tipo` varchar(50) DEFAULT NULL,
  `lugar` varchar(100) NOT NULL,
  `fecha` date NOT NULL,
  `precio` decimal(8,2) NOT NULL,
  `capacidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `evento`
--

INSERT INTO `evento` (`id_evento`, `nombre`, `tipo`, `lugar`, `fecha`, `precio`, `capacidad`) VALUES
(1, 'Concierto de Rock', 'Concierto', 'Auditorio Nacional', '2025-07-15', 50.00, 3000),
(2, 'Obra de Teatro: Hamlet', 'Teatro', 'Teatro Principal', '2025-06-10', 35.50, 800),
(3, 'Partido de Fútbol', 'Deportivo', 'Estadio Municipal', '2025-08-20', 4.00, 20000),
(4, 'Festival de Jazz', 'Concierto', 'Plaza Central', '2025-09-05', 40.00, 1500),
(5, 'Conferencia Tecnológica', 'Conferencia', 'Centro de Convenciones', '2025-10-12', 100.00, 500),
(6, 'Exposición de Arte', 'Exposición', 'Museo de Arte Moderno', '2025-07-30', 20.00, 1200),
(7, 'Concierto de Música Clásica', 'Concierto', 'Sala de Conciertos', '2025-11-01', 60.00, 1000),
(9, 'Evento Test JUnit', 'Concierto', 'Auditorio', '2025-06-15', 1000.00, 1000);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `entrada`
--
ALTER TABLE `entrada`
  ADD PRIMARY KEY (`id_entrada`),
  ADD KEY `id_evento` (`id_evento`);

--
-- Indices de la tabla `evento`
--
ALTER TABLE `evento`
  ADD PRIMARY KEY (`id_evento`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `entrada`
--
ALTER TABLE `entrada`
  MODIFY `id_entrada` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `evento`
--
ALTER TABLE `evento`
  MODIFY `id_evento` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `entrada`
--
ALTER TABLE `entrada`
  ADD CONSTRAINT `entrada_ibfk_1` FOREIGN KEY (`id_evento`) REFERENCES `evento` (`id_evento`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
