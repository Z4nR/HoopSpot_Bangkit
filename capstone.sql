-- MariaDB dump 10.17  Distrib 10.4.11-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: capstone
-- ------------------------------------------------------
-- Server version	10.4.11-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `hoops_entity`
--

DROP TABLE IF EXISTS `hoops_entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hoops_entity` (
  `place_id` int(11) NOT NULL AUTO_INCREMENT,
  `place_name` varchar(50) NOT NULL,
  `place_img` varchar(200) DEFAULT NULL,
  `place_address` varchar(150) NOT NULL,
  PRIMARY KEY (`place_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoops_entity`
--

LOCK TABLES `hoops_entity` WRITE;
/*!40000 ALTER TABLE `hoops_entity` DISABLE KEYS */;
INSERT INTO `hoops_entity` VALUES (1,'Mall Paragon City Semarang','https://www.intiwhiz.com/images/planner/paragon.jpg','Jl. Pemuda No.118, Sekayu, Kec. Semarang Tengah, Kota Semarang, Jawa Tengah 50132'),(2,'JAVA MALL Semarang','https://1.bp.blogspot.com/-gJchitJHX-A/X78CqGvJTOI/AAAAAAAAXAM/tycGLRuJvCQctOy8xmkUKlBg3jR2HJ15ACLcBGAsYHQ/s1000/JAVA%2BMALL%2BSEMARANG.jpg','Jl. MT. Haryono No.801, Karangkidul, Kec. Semarang Sel., Kota Semarang, Jawa Tengah 50241'),(3,'DP Mall Semarang','https://joss.co.id/data/uploads/2020/03/dp-mall-semarang.jpg','Jl. Pemuda No.150, Sekayu, Kec. Semarang Tengah, Kota Semarang, Jawa Tengah 50132');
/*!40000 ALTER TABLE `hoops_entity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parking`
--

DROP TABLE IF EXISTS `parking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parking` (
  `park_id` int(11) NOT NULL AUTO_INCREMENT,
  `park_name` varchar(50) NOT NULL,
  `park_img` varchar(200) DEFAULT NULL,
  `park_address` varchar(150) NOT NULL,
  `park_layout` varchar(150) DEFAULT NULL,
  `park_video` varchar(200) DEFAULT NULL,
  `place_id` int(11) NOT NULL,
  PRIMARY KEY (`park_id`),
  KEY `place_id` (`place_id`),
  CONSTRAINT `parking_ibfk_1` FOREIGN KEY (`place_id`) REFERENCES `hoops_entity` (`place_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parking`
--

LOCK TABLES `parking` WRITE;
/*!40000 ALTER TABLE `parking` DISABLE KEYS */;
INSERT INTO `parking` VALUES (1,'Parkir Paragon Mall Semarang','https://www.kai-pavement.com/files/img_84461319227780.jpg','Jl. Pemuda No.121, Sekayu, Kec. Semarang Tengah, Kota Semarang, Jawa Tengah 50132',NULL,'/',1),(2,'Parkir Paragon Mall Semarang','https://cdns.klimg.com/otosia.com/p/headline/650x325/0000458180.jpg','Jl. Pemuda No.118, Sekayu, Kec. Semarang Tengah, Kota Semarang, Jawa Tengah 50132',NULL,'/index_parking2',1),(3,'Parkir JAVA MALL Semarang','https://assets.bwbx.io/images/users/iqjWHBFdfxIU/intTFNX2AHxk/v0/1200x820.jpg','Jl. MT. Haryono No.801, Karangkidul, Kec. Semarang Sel., Kota Semarang, Jawa Tengah 50241',NULL,'/index_parking3',2),(4,'Parkir JAVA MALL Semarang','https://advancelocal-adapter-image-uploads.s3.amazonaws.com/image.syracuse.com/home/syr-media/width2048/img/news/photo/2018/08/07/lot3jpg-3e9c430b73f37e79.jpg','Jl. MT. Haryono No.801, Karangkidul, Kec. Semarang Sel., Kota Semarang, Jawa Tengah 50241',NULL,'/index_parking4',2),(5,'Parkir DP Mall Semarang','https://static.giggster.com/images/location/1e520fa4-feaf-4d71-a11d-cfcd9bba7b73/93807fd3-d877-4fa7-a5e1-182d442b6abf/full_hd_retina.jpeg','Jl. Pemuda No.150, Sekayu, Kec. Semarang Tengah, Kota Semarang, Jawa Tengah 50132',NULL,'/index_parking5',3);
/*!40000 ALTER TABLE `parking` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-07-03  0:10:18
