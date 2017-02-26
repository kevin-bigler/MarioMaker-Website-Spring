SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

DROP DATABASE IF EXISTS `mario_maker_tracker`;
CREATE DATABASE `mario_maker_tracker` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mario_maker_tracker`;

-- Course
CREATE TABLE `course` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `nintendo_id` varchar(19) NOT NULL,
  `name` text DEFAULT NULL,
  `image_url` text DEFAULT NULL,
  `style` text DEFAULT NULL, -- mode (SMB1, SMB2, SMW, NSMB)
  `world_record_holder` text DEFAULT NULL,
  `world_record_time` text DEFAULT NULL,
  `clear_rate` text DEFAULT NULL,
  `star_count` text DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `level_code` (`level_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;