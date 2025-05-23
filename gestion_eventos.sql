-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 23-05-2025 a las 18:25:06
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
  `id_usuario` int(11) NOT NULL,
  `cantidad_entradas` int(11) NOT NULL DEFAULT 1,
  `fecha_compra` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `entrada`
--

INSERT INTO `entrada` (`id_entrada`, `id_evento`, `id_usuario`, `cantidad_entradas`, `fecha_compra`) VALUES
(1, 1, 1, 2, '2025-05-20 10:30:00'),
(2, 1, 2, 1, '2025-05-21 15:45:00'),
(3, 2, 3, 3, '2025-05-19 08:20:00'),
(4, 3, 4, 4, '2025-05-22 18:00:00'),
(5, 3, 5, 2, '2025-05-21 09:15:00');

--
-- Disparadores `entrada`
--
DELIMITER $$
CREATE TRIGGER `trg_actualizar_capacidad_insert` AFTER INSERT ON `entrada` FOR EACH ROW BEGIN
  UPDATE evento SET capacidad_disponible = capacidad_disponible - NEW.cantidad_entradas
  WHERE id_evento = NEW.id_evento;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `evento`
--

CREATE TABLE `evento` (
  `id_evento` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `tipo` varchar(50) NOT NULL,
  `fecha` date NOT NULL,
  `lugar` varchar(100) NOT NULL,
  `capacidad_total` int(11) NOT NULL DEFAULT 0 COMMENT 'Capacidad máxima del evento',
  `detalles` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL CHECK (json_valid(`detalles`)),
  `capacidad_disponible` int(11) NOT NULL DEFAULT 0 COMMENT 'Capacidad restante, actualizada con triggers y procedimientos'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `evento`
--

INSERT INTO `evento` (`id_evento`, `nombre`, `tipo`, `fecha`, `lugar`, `capacidad_total`, `detalles`, `capacidad_disponible`) VALUES
(1, 'Concierto Rock', 'Concierto', '2025-07-15', 'Auditorio Municipal', 150, '[\"Banda A\", \"Banda B\"]', 150),
(2, 'Obra de Teatro Clásico', 'Teatro', '2025-08-01', 'Teatro Nacional', 100, '{\"Director\": \"Juan Maestro\", \"Duracion\": \"2h\"}', 100),
(3, 'Festival de Jazz', 'Concierto', '2025-09-10', 'Parque Central', 300, '[\"Artista 1\", \"Artista 2\", \"Artista 3\"]', 300),
(4, 'Charla de Tecnología', 'Conferencia', '2025-06-20', 'Centro de Convenciones', 80, NULL, 80),
(5, 'Película de Estreno', 'Cine', '2025-07-05', 'Cine Multisalas', 120, NULL, 120);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id_usuario` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `email` varchar(255) NOT NULL,
  `telefono` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id_usuario`, `nombre`, `email`, `telefono`) VALUES
(1, 'Juan Pérez', 'juan@example.com', '123456789'),
(2, 'Lucía Gómez', 'lucia@example.com', NULL),
(3, 'Carlos Díaz', 'carlos@example.com', '987654321'),
(4, 'Ana Torres', 'ana@example.com', NULL),
(5, 'Miguel López', 'miguel@example.com', '555123456');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `entrada`
--
ALTER TABLE `entrada`
  ADD PRIMARY KEY (`id_entrada`),
  ADD KEY `id_evento` (`id_evento`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- Indices de la tabla `evento`
--
ALTER TABLE `evento`
  ADD PRIMARY KEY (`id_evento`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_usuario`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `entrada`
--
ALTER TABLE `entrada`
  MODIFY `id_entrada` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `evento`
--
ALTER TABLE `evento`
  MODIFY `id_evento` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `entrada`
--
ALTER TABLE `entrada`
  ADD CONSTRAINT `entrada_ibfk_1` FOREIGN KEY (`id_evento`) REFERENCES `evento` (`id_evento`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `entrada_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
