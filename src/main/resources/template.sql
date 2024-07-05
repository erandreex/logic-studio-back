-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.6.10-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para logic_studio
DROP DATABASE IF EXISTS `logic_studio`;
CREATE DATABASE IF NOT EXISTS `logic_studio` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `logic_studio`;

-- Volcando estructura para tabla logic_studio.autos_reservas
DROP TABLE IF EXISTS `autos_reservas`;
CREATE TABLE IF NOT EXISTS `autos_reservas` (
  `placa` varchar(50) DEFAULT NULL,
  `estado` varchar(50) DEFAULT NULL,
  `cita` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Volcando datos para la tabla logic_studio.autos_reservas: ~5 rows (aproximadamente)
DELETE FROM `autos_reservas`;
INSERT INTO `autos_reservas` (`placa`, `estado`, `cita`) VALUES
	('logic_1', 'ATENDIDA', '2024-07-05 11:30:00'),
	('logic_2', 'ATENDIDA', '2024-07-05 09:30:00'),
	('logic_2', 'PROGRAMADA', '2024-07-05 11:30:00'),
	('logic_3', 'Reserva', '2024-07-05 09:00:00'),
	('Logic_4', 'PROGRAMADO', '2024-07-05 10:30:00');

-- Volcando estructura para procedimiento logic_studio.sp_autos_lista
DROP PROCEDURE IF EXISTS `sp_autos_lista`;
DELIMITER //
CREATE PROCEDURE `sp_autos_lista`()
BEGIN

	SELECT DISTINCT(placa) FROM logic_studio.autos_reservas;

END//
DELIMITER ;

-- Volcando estructura para procedimiento logic_studio.sp_auto_placa
DROP PROCEDURE IF EXISTS `sp_auto_placa`;
DELIMITER //
CREATE PROCEDURE `sp_auto_placa`(
	IN `i_placa` VARCHAR(50)
)
BEGIN

	SELECT placa, estado, cita FROM logic_studio.autos_reservas where placa = i_placa ORDER BY cita DESC;

END//
DELIMITER ;

-- Volcando estructura para procedimiento logic_studio.sp_crear_reserva
DROP PROCEDURE IF EXISTS `sp_crear_reserva`;
DELIMITER //
CREATE PROCEDURE `sp_crear_reserva`(
	IN `i_placa` VARCHAR(50),
	IN `i_estado` VARCHAR(50),
	IN `i_cita` DATETIME
)
BEGIN

	INSERT INTO logic_studio.autos_reservas (placa, estado, cita) VALUES (i_placa, i_estado, i_cita);
		
END//
DELIMITER ;

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
