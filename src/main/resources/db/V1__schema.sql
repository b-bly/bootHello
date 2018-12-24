DROP SCHEMA IF EXISTS `beer-rater`;

CREATE SCHEMA `beer-rater`;

use `beer-rater`;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `beer`;

CREATE TABLE `beer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `company` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `style` varchar(45) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `review_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_REVIEW_idx` (`review_id`),
  CONSTRAINT `FK_REVIEW` 
  FOREIGN KEY (`review_id`) 
  REFERENCES `review` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `review`;

CREATE TABLE `review` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comment` varchar(256) DEFAULT NULL,
  `beer_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK__BEER_idx` (`beer_id`),
  CONSTRAINT `FK_BEER` 
  FOREIGN KEY (`beer_id`) 
  REFERENCES `beer` (`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  KEY `FK_USER_idx` (`user_id`),
  CONSTRAINT `FK_USER` FOREIGN KEY (`user_id`) 
  REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


SET FOREIGN_KEY_CHECKS = 1;
