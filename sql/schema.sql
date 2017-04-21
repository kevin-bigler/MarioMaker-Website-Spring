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
  -- `creator_id` int(10) DEFAULT NULL,
  `creator_nintendo_id` text DEFAULT NULL,
  `main_image_url` text DEFAULT NULL,
  `full_image_url` text DEFAULT NULL,
  `upload_date` date DEFAULT NULL,
  `gameskin` text DEFAULT NULL, -- mode (SMB1, SMB2, SMW, NSMB)
  `miiverse_comments_url` text DEFAULT NULL,
  `difficulty_rank` text DEFAULT NULL,
  `clear_rate` text DEFAULT NULL,
  `number_stars` text DEFAULT NULL,
  `number_footprints` text DEFAULT NULL,
  `number_shares` text DEFAULT NULL,
  `number_clears` text DEFAULT NULL,
  `number_attempts` text DEFAULT NULL,
  `number_comments` text DEFAULT NULL,
  `tag` text DEFAULT NULL,
  -- `world_record_holder_id` int(10) DEFAULT NULL,
  `world_record_holder_nintendo_id` text DEFAULT NULL,
  `world_record_time` text DEFAULT NULL,
  -- `first_clear_player_id` int(10) DEFAULT NULL,
  `first_clear_player_nintendo_id` text DEFAULT NULL,
  `recent_players_nintendo_ids` text DEFAULT NULL,
  `cleared_by_players_nintendo_ids` text DEFAULT NULL,
  `starred_by_players_nintendo_ids` text DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nintendo_id` (`nintendo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- CourseSnapshot
CREATE TABLE `course_snapshot` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `nintendo_id` varchar(19) NOT NULL,
  `name` text DEFAULT NULL,
  -- `creator_id` int(10) DEFAULT NULL,
  `creator_nintendo_id` text DEFAULT NULL,
  `main_image_url` text DEFAULT NULL,
  `full_image_url` text DEFAULT NULL,
  `upload_date` date DEFAULT NULL,
  `gameskin` text DEFAULT NULL, -- mode (SMB1, SMB2, SMW, NSMB)
  `miiverse_comments_url` text DEFAULT NULL,
  `difficulty_rank` text DEFAULT NULL,
  `clear_rate` text DEFAULT NULL,
  `number_stars` text DEFAULT NULL,
  `number_footprints` text DEFAULT NULL,
  `number_shares` text DEFAULT NULL,
  `number_clears` text DEFAULT NULL,
  `number_attempts` text DEFAULT NULL,
  `number_comments` text DEFAULT NULL,
  `tag` text DEFAULT NULL,
  -- `world_record_holder_id` int(10) DEFAULT NULL,
  `world_record_holder_nintendo_id` text DEFAULT NULL,
  `world_record_time` text DEFAULT NULL,
  -- `first_clear_player_id` int(10) DEFAULT NULL,
  `first_clear_player_nintendo_id` text DEFAULT NULL,
  `recent_players_nintendo_ids` text DEFAULT NULL,
  `cleared_by_players_nintendo_ids` text DEFAULT NULL,
  `starred_by_players_nintendo_ids` text DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nintendo_id` (`nintendo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- Player
CREATE TABLE `player` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nintendo_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nintendo_id` (`nintendo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- PlayerSnapshot
CREATE TABLE `player_snapshot` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nintendo_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nintendo_id` (`nintendo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;