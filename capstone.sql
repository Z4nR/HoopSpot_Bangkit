/*Create database `capstone`*/
CREATE DATABASE `capstone`;

/*Create table `parking_area` & `hoops_entity` for datbase `capstone`*/
CREATE TABLE `parking_area` (
  `park_id` int(11) NOT NULL AUTO_INCREMENT,
  `park_name` varchar(35) DEFAULT NULL,
  `park_img` varchar(100) DEFAULT NULL,
  `park_address` varchar(50) DEFAULT NULL,
  `park_status` varchar(15) DEFAULT NULL,
  `available_park` int(11) DEFAULT NULL,
  `parking_location` varchar(30) DEFAULT NULL,
  `parking_space` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`park_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4

CREATE TABLE `hoops_entity` (
  `place_id` int(11) NOT NULL AUTO_INCREMENT,
  `place_name` varchar(35) DEFAULT NULL,
  `place_img` varchar(100) DEFAULT NULL,
  `place_address` varchar(50) DEFAULT NULL,
  `park_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`place_id`),
  KEY `park_id` (`park_id`),
  CONSTRAINT `hoops_entity_ibfk_1` FOREIGN KEY (`park_id`) REFERENCES `parking_area` (`park_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4

/*Insert data to table `parking_area` & `hoops_entity`*/
INSERT INTO `parking_area`(`park_name`, `park_img`, `park_address`, `park_status`, `available_park`, `parking_location`, `parking_space`) values
('central park', 'http://centralCCTV.com', 'jl.sekayu barat', 'tersedia', 10, 'lantai 1', 'berjarak');

INSERT INTO `hoops_entity`(`place_name`, `place_img`, `place_address`, `park_id`) values
('semarang', 'http://map.co.id', 'jl.sekayu barat', '4');