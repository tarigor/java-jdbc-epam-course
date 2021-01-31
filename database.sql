-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.22 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             11.2.0.6213
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Dumping data for table shop.items: ~5 rows (approximately)
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` (`item_name`, `item_desc`, `price`) VALUES
	('BOSCH1234', 'laundry machine', 435),
	('BOSCH5678', 'blender', 45),
	('LG1234', 'LED TV', 456),
	('PANASONIC123', 'audio player', 45),
	('SONY896', 'video player', 45);
/*!40000 ALTER TABLE `items` ENABLE KEYS */;

-- Dumping data for table shop.orders: ~3 rows (approximately)
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` (`order_id`, `items`, `order_date`) VALUES
	(111, 'BOSCH1234/LG1234/SONY896', '2021-01-31 08:42:00'),
	(222, 'SONY896/PANASONIC123/BOSCH1234', '2020-11-07 09:12:40'),
	(444, 'LG1234/PANASONIC123', '2020-04-27 11:22:44'),
	(445, 'LG1234/SONY896', '2021-01-31 00:00:00'),
	(446, 'PANASONIC1234/LG1234', '2021-01-31 00:00:00'),
	(450, 'BOSCH1234/LG1234/SONY896/LG1234/SONY896/PANASONIC123/LG1234', '2021-01-31 00:00:00'),
	(452, 'BOSCH1234/LG1234/SONY896/LG1234/SONY896/PANASONIC123/LG1234/BOSCH1234/LG1234/SONY896/LG1234/SONY896/PANASONIC123/LG1234', '2021-01-31 00:00:00');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;

-- Dumping data for table shop.order_items: ~9 rows (approximately)
/*!40000 ALTER TABLE `order_items` DISABLE KEYS */;
INSERT INTO `order_items` (`item_order_id`, `items`, `amount`) VALUES
	(111, 'BOSCH1234', 1),
	(111, 'LG1234', 1),
	(111, 'SONY896', 1),
	(222, 'PANASONIC123', 1),
	(222, 'SONY896', 2),
	(222, 'BOSCH1234', 1),
	(444, 'LG1234', 1),
	(444, 'PANASONIC123', 2),
	(445, 'LG1234', 1),
	(445, 'SONY896', 3),
	(446, 'PANASONIC123', 1),
	(446, 'LG1234', 1),
	(450, 'BOSCH1234', 1),
	(450, 'LG1234', 1),
	(450, 'SONY896', 1),
	(450, 'LG1234', 1),
	(450, 'SONY896', 3),
	(450, 'PANASONIC123', 1),
	(450, 'LG1234', 1),
	(452, 'BOSCH1234', 1),
	(452, 'LG1234', 1),
	(452, 'SONY896', 1),
	(452, 'LG1234', 1),
	(452, 'SONY896', 3),
	(452, 'PANASONIC123', 1),
	(452, 'LG1234', 1),
	(452, 'BOSCH1234', 1),
	(452, 'LG1234', 1),
	(452, 'SONY896', 1),
	(452, 'LG1234', 1),
	(452, 'SONY896', 3),
	(452, 'PANASONIC123', 1),
	(452, 'LG1234', 1);
/*!40000 ALTER TABLE `order_items` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
