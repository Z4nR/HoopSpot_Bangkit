/*Create database `capstone`*/
CREATE DATABASE `capstone`;

/*Create table `hoops_entity` & `parking_area` for datbase `capstone`*/
CREATE TABLE `hoops_entity` (
 `place_id` int(11) NOT NULL AUTO_INCREMENT,
 `place_name` varchar(20) NOT NULL,
 `place_img` varchar(50) DEFAULT NULL,
 `place_address` varchar(50) NOT NULL,
 PRIMARY KEY (`place_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `parking_area` (
 `park_id` int(11) NOT NULL AUTO_INCREMENT,
 `park_name` varchar(20) NOT NULL,
 `park_img` varchar(50) DEFAULT NULL,
 `park_address` varchar(20) NOT NULL,
 `park_status` varchar(20) NOT NULL,
 `available_park` int(11) NOT NULL,
 `parking_location` varchar(50) NOT NULL,
 `parking_space` varchar(50) NOT NULL,
 `place_id` int(11) NOT NULL,
 PRIMARY KEY (`park_id`),
 KEY `place_id` (`place_id`),
 CONSTRAINT `parking_area_ibfk_1` FOREIGN KEY (`place_id`) REFERENCES `hoops_entity` (`place_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Insert data to table `hoops_entity` place_id in parking_area must same in hoops_entity because this table constraint relation*/
INSERT INTO `parking_area` (`park_id`, `park_name`, `park_img`, `park_address`, `park_status`, `available_park`, `parking_location`, `parking_space`, `place_id`)
 VALUES (NULL, 'park A', NULL, 'Road 123 Point 4.5', 'Empty', '23', 'Maps', 'Image', '1');
INSERT INTO `parking_area` (`park_id`, `park_name`, `park_img`, `park_address`, `park_status`, `available_park`, `parking_location`, `parking_space`, `place_id`)
 VALUES (NULL, 'Park B', NULL, 'Road 123 Point 2.9', 'Full', '23', 'Maps', 'Image', '1');
INSERT INTO `parking_area` (`park_id`, `park_name`, `park_img`, `park_address`, `park_status`, `available_park`, `parking_location`, `parking_space`, `place_id`)
 VALUES (NULL, 'Park C', NULL, 'Road 123 Point 3.5', 'Full', '23', 'Maps', 'Image', '1');

 INSERT INTO `hoops_entity` (`place_id`, `place_name`, `place_img`, `place_address`)
  VALUES (NULL, 'Place A', NULL, 'Road 123');

/*INSERT INTO `hoops_entity`(
    `place_name` ,
    `place_img` ,
    `place_address` ,
    `parking_area`
)
VALUES(
    'Javamall' ,
    'http://javamall.com' ,
    'jl.sekayu 3 no 47' ,
    JSON_MERGE_PRESERVE(
        '{"park_id": "2"}' ,
        '{"park_name": "central park"}' ,
        '{"park_img": "http://ccjavamall.com"}' ,
        '{"park_address": "jl.sekayu 3 no 4"}' ,
        '{"park_status": "tersedia"}' ,
        '{"available_park": "10"}' ,
        '{"parking_location": "lantai 1"}' ,
        '{"parking_space": "berjarak"}'
    )
);*/