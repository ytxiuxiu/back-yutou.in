/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# table kno_node_editions
# ------------------------------------------------------------

DROP TABLE IF EXISTS `kno_node_editions`;

CREATE TABLE `kno_node_editions` (
  `edition_id` char(32) NOT NULL,
  `node_id` char(32) NOT NULL,
  `user_id` char(32) NOT NULL,
  `name` varchar(30) NOT NULL DEFAULT '',
  `path` text NOT NULL,
  `small` varchar(60) DEFAULT NULL,
  `content` mediumtext,
  `created_at` datetime NOT NULL,
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`edition_id`),
  KEY `fk_node_id` (`node_id`),
  KEY `fk_user_id` (`user_id`),
  CONSTRAINT `fk_e_node_id` FOREIGN KEY (`node_id`) REFERENCES `kno_nodes` (`node_id`),
  CONSTRAINT `fk_e_user_id` FOREIGN KEY (`user_id`) REFERENCES `pc_users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `kno_node_editions` WRITE;
/*!40000 ALTER TABLE `kno_node_editions` DISABLE KEYS */;

INSERT INTO `kno_node_editions` (`edition_id`, `node_id`, `user_id`, `name`, `path`, `small`, `content`, `created_at`, `deleted`)
VALUES
	('98626ed2a48040e49b9c9a61d4b9ba61','ac53da715187458bb3582258134185dc','110229703345144338356','Root','/',NULL,NULL,'2016-03-18 15:03:18',b'0');

/*!40000 ALTER TABLE `kno_node_editions` ENABLE KEYS */;
UNLOCK TABLES;


# table kno_nodes
# ------------------------------------------------------------

DROP TABLE IF EXISTS `kno_nodes`;

CREATE TABLE `kno_nodes` (
  `node_id` char(32) NOT NULL,
  `current_edition` char(32) DEFAULT NULL,
  PRIMARY KEY (`node_id`),
  KEY `fk_current_edition` (`current_edition`),
  CONSTRAINT `fk_n_current_edition` FOREIGN KEY (`current_edition`) REFERENCES `kno_node_editions` (`edition_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `kno_nodes` WRITE;
/*!40000 ALTER TABLE `kno_nodes` DISABLE KEYS */;

INSERT INTO `kno_nodes` (`node_id`, `current_edition`)
VALUES
	('ac53da715187458bb3582258134185dc','98626ed2a48040e49b9c9a61d4b9ba61');

/*!40000 ALTER TABLE `kno_nodes` ENABLE KEYS */;
UNLOCK TABLES;


# table pc_action_categories
# ------------------------------------------------------------

DROP TABLE IF EXISTS `pc_action_categories`;

CREATE TABLE `pc_action_categories` (
  `category_id` char(32) NOT NULL,
  `category_name` varchar(255) NOT NULL,
  `category_description` varchar(255) NOT NULL,
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `pc_action_categories` WRITE;
/*!40000 ALTER TABLE `pc_action_categories` DISABLE KEYS */;

INSERT INTO `pc_action_categories` (`category_id`, `category_name`, `category_description`, `deleted`)
VALUES
	('58414478729d4f0c8aa785db968d23e1','knowledge.map','Knowledge Map',b'0');

/*!40000 ALTER TABLE `pc_action_categories` ENABLE KEYS */;
UNLOCK TABLES;


# table pc_actions
# ------------------------------------------------------------

DROP TABLE IF EXISTS `pc_actions`;

CREATE TABLE `pc_actions` (
  `action_id` char(32) NOT NULL,
  `action_name` varchar(255) NOT NULL,
  `category_id` char(32) NOT NULL,
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  `action_description` varchar(255) NOT NULL,
  PRIMARY KEY (`action_id`),
  KEY `fk_category_id` (`category_id`),
  CONSTRAINT `fk_a_category_id` FOREIGN KEY (`category_id`) REFERENCES `pc_action_categories` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `pc_actions` WRITE;
/*!40000 ALTER TABLE `pc_actions` DISABLE KEYS */;

INSERT INTO `pc_actions` (`action_id`, `action_name`, `category_id`, `deleted`, `action_description`)
VALUES
	('9000cb02efac46669cb55f6fe8e144df','knowledge.map.edit','58414478729d4f0c8aa785db968d23e1',b'0',''),
	('a5bd492864944350a88d1d07ab941e62','knowledge.map.view','58414478729d4f0c8aa785db968d23e1',b'0','');

/*!40000 ALTER TABLE `pc_actions` ENABLE KEYS */;
UNLOCK TABLES;


# table pc_group_action
# ------------------------------------------------------------

DROP TABLE IF EXISTS `pc_group_action`;

CREATE TABLE `pc_group_action` (
  `group_id` char(32) NOT NULL,
  `action_id` char(32) NOT NULL,
  PRIMARY KEY (`group_id`,`action_id`),
  KEY `fk_action_id` (`action_id`),
  CONSTRAINT `fk_ga_action_id` FOREIGN KEY (`action_id`) REFERENCES `pc_actions` (`action_id`),
  CONSTRAINT `fk_ga_group_id` FOREIGN KEY (`group_id`) REFERENCES `pc_groups` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `pc_group_action` WRITE;
/*!40000 ALTER TABLE `pc_group_action` DISABLE KEYS */;

INSERT INTO `pc_group_action` (`group_id`, `action_id`)
VALUES
	('a9dde49e9fe54072aaed87ee84ae427e','a5bd492864944350a88d1d07ab941e62'),
	('f6592f191c604bc29efe9483080f281c','a5bd492864944350a88d1d07ab941e62');

/*!40000 ALTER TABLE `pc_group_action` ENABLE KEYS */;
UNLOCK TABLES;


# table pc_groups
# ------------------------------------------------------------

DROP TABLE IF EXISTS `pc_groups`;

CREATE TABLE `pc_groups` (
  `group_id` char(32) NOT NULL,
  `group_name` char(32) NOT NULL,
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `pc_groups` WRITE;
/*!40000 ALTER TABLE `pc_groups` DISABLE KEYS */;

INSERT INTO `pc_groups` (`group_id`, `group_name`, `deleted`)
VALUES
	('2b49013700104e198ea421c76a8547b6','admin',b'0'),
	('93c3046735034c50a4575413a136e7ef','root',b'0'),
	('a9dde49e9fe54072aaed87ee84ae427e','default',b'0'),
	('f6592f191c604bc29efe9483080f281c','no-login',b'0');

/*!40000 ALTER TABLE `pc_groups` ENABLE KEYS */;
UNLOCK TABLES;


# table pc_users
# ------------------------------------------------------------

DROP TABLE IF EXISTS `pc_users`;

CREATE TABLE `pc_users` (
  `user_id` char(21) NOT NULL,
  `group_id` char(32) NOT NULL DEFAULT 'a9dde49e9fe54072aaed87ee84ae427e',
  `email` varchar(255) NOT NULL,
  `family_name` varchar(45) NOT NULL,
  `given_name` varchar(45) NOT NULL,
  `picture_url` varchar(255) DEFAULT NULL,
  `registered_at` datetime DEFAULT NULL,
  `last_loged_in_at` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `uni_email` (`email`),
  KEY `fk_u_group_id` (`group_id`),
  CONSTRAINT `fk_u_group_id` FOREIGN KEY (`group_id`) REFERENCES `pc_groups` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `pc_users` WRITE;
/*!40000 ALTER TABLE `pc_users` DISABLE KEYS */;

INSERT INTO `pc_users` (`user_id`, `group_id`, `email`, `family_name`, `given_name`, `picture_url`, `registered_at`, `last_loged_in_at`)
VALUES
	('110229703345144338356','93c3046735034c50a4575413a136e7ef','ytxiuxiu@gmail.com','Liu','Johnny',NULL,'2016-03-14 23:43:20','2016-04-06 19:10:38');

/*!40000 ALTER TABLE `pc_users` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
