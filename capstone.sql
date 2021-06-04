/*Create database `capstone`*/
CREATE DATABASE `capstone`;

/*Create table `hoops_entity` & `parking` for database `capstone`*/
CREATE TABLE `hoops_entity` (
 `place_id` int(11) NOT NULL AUTO_INCREMENT,
 `place_name` varchar(50) NOT NULL,
 `place_img` varchar(200) DEFAULT NULL,
 `place_address` varchar(150) NOT NULL,
 PRIMARY KEY (`place_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `parking` (
 `park_id` int(11) NOT NULL AUTO_INCREMENT,
 `park_name` varchar(50) NOT NULL,
 `park_img` varchar(200) DEFAULT NULL,
 `park_address` varchar(150) NOT NULL,
 `park_layout` varchar(150) DEFAULT NULL,
 `place_id` int(11) NOT NULL,
 PRIMARY KEY (`park_id`),
 KEY `place_id` (`place_id`),
 CONSTRAINT `parking_ibfk_1` FOREIGN KEY (`place_id`) REFERENCES `hoops_entity` (`place_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Insert data to table `hoops_entity` place_id in parking must same in hoops_entity because this table constraint relation*/INSERT INTO `hoops_entity` (`place_id`, `place_name`, `place_img`, `place_address`)
INSERT INTO `hoops_entity` (`place_id`, `place_name`, `place_img`, `place_address`)
 VALUES (NULL, 'Mall Paragon City Semarang', 'https://www.intiwhiz.com/images/planner/paragon.jpg', 'Jl. Pemuda No.118, Sekayu, Kec. Semarang Tengah, Kota Semarang, Jawa Tengah 50132');
INSERT INTO `hoops_entity` (`place_id`, `place_name`, `place_img`, `place_address`)
 VALUES (NULL, 'JAVA MALL Semarang', 'https://1.bp.blogspot.com/-gJchitJHX-A/X78CqGvJTOI/AAAAAAAAXAM/tycGLRuJvCQctOy8xmkUKlBg3jR2HJ15ACLcBGAsYHQ/s1000/JAVA%2BMALL%2BSEMARANG.jpg', 'Jl. MT. Haryono No.801, Karangkidul, Kec. Semarang Sel., Kota Semarang, Jawa Tengah 50241');
INSERT INTO `hoops_entity` (`place_id`, `place_name`, `place_img`, `place_address`)
 VALUES (NULL, 'DP Mall Semarang', 'https://joss.co.id/data/uploads/2020/03/dp-mall-semarang.jpg', 'Jl. Pemuda No.150, Sekayu, Kec. Semarang Tengah, Kota Semarang, Jawa Tengah 50132');

INSERT INTO `parking` (`park_id`, `park_name`, `park_img`, `park_address`, `park_layout`, `place_id`)
 VALUES (NULL, 'Parkir Paragon Mall Semarang', 'https://www.kai-pavement.com/files/img_84461319227780.jpg', 'Jl. Pemuda No.121, Sekayu, Kec. Semarang Tengah, Kota Semarang, Jawa Tengah 50132', NULL, '1');
INSERT INTO `parking` (`park_id`, `park_name`, `park_img`, `park_address`, `park_layout`, `place_id`)
 VALUES (NULL, 'Parkir Paragon Mall Semarang', 'https://cdns.klimg.com/otosia.com/p/headline/650x325/0000458180.jpg', 'Jl. Pemuda No.118, Sekayu, Kec. Semarang Tengah, Kota Semarang, Jawa Tengah 50132', NULL, '1');
INSERT INTO `parking` (`park_id`, `park_name`, `park_img`, `park_address`, `park_layout`, `place_id`)
 VALUES (NULL, 'Parkir JAVA MALL Semarang', 'https://assets.bwbx.io/images/users/iqjWHBFdfxIU/intTFNX2AHxk/v0/1200x820.jpg', 'Jl. MT. Haryono No.801, Karangkidul, Kec. Semarang Sel., Kota Semarang, Jawa Tengah 50241', NULL, '2');
INSERT INTO `parking` (`park_id`, `park_name`, `park_img`, `park_address`, `park_layout`, `place_id`)
 VALUES (NULL, 'Parkir JAVA MALL Semarang', 'https://advancelocal-adapter-image-uploads.s3.amazonaws.com/image.syracuse.com/home/syr-media/width2048/img/news/photo/2018/08/07/lot3jpg-3e9c430b73f37e79.jpg', 'Jl. MT. Haryono No.801, Karangkidul, Kec. Semarang Sel., Kota Semarang, Jawa Tengah 50241',NULL,'2');
INSERT INTO `parking` (`park_id`, `park_name`, `park_img`, `park_address`, `park_layout`, `place_id`)
 VALUES (NULL, 'Parkir DP Mall Semarang', 'https://static.giggster.com/images/location/1e520fa4-feaf-4d71-a11d-cfcd9bba7b73/93807fd3-d877-4fa7-a5e1-182d442b6abf/full_hd_retina.jpeg', 'Jl. Pemuda No.150, Sekayu, Kec. Semarang Tengah, Kota Semarang, Jawa Tengah 50132', NULL, '3');
INSERT INTO `parking` (`park_id`, `park_name`, `park_img`, `park_address`, `park_layout`, `place_id`)
 VALUES (NULL, 'Parkir DP Mall Semarang', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSXI8mHMDUnk9EXrEIV1hHQNG9X00-RRcqj_A&usqp=CAU', 'Jl. Pemuda No.150, Sekayu, Kec. Semarang Tengah, Kota Semarang, Jawa Tengah 50132', NULL, '3');

/* INSERT INTO `hoops_entity`( --Development Interest
    `place_name` ,
    `place_img` ,
    `place_address` ,
    `parking`
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
); */