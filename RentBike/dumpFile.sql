-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: rent_bike
-- ------------------------------------------------------
-- Server version	8.0.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bike_master`
--


CREATE SCHEMA rent_bike;

USE rent_bike;

DROP TABLE IF EXISTS `bike_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bike_master` (
  `bike_id` int NOT NULL AUTO_INCREMENT,
  `bike_number` varchar(25) NOT NULL,
  `model_name` varchar(25) DEFAULT NULL,
  `spec` varchar(100) DEFAULT NULL,
  `owner_station` int DEFAULT NULL,
  `bike_status` varchar(1) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`bike_id`),
  KEY `station_fk` (`owner_station`),
  CONSTRAINT `station_fk` FOREIGN KEY (`owner_station`) REFERENCES `station_master` (`station_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1008 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bike_master`
--

LOCK TABLES `bike_master` WRITE;
/*!40000 ALTER TABLE `bike_master` DISABLE KEYS */;
INSERT INTO `bike_master` VALUES (1000,'GL 89 HGF','Bianchi',NULL,335,'A','2021-10-22 00:00:00',NULL),(1001,'GL 61 MOL','Merida',NULL,338,'A','2021-10-22 00:00:00',NULL),(1002,'GL 82 BGF','Pirahna Blaze',NULL,338,'A','2021-10-22 00:00:00',NULL),(1003,'GL 61 ERT','Haibike',NULL,333,'A','2021-10-22 00:00:00',NULL),(1004,'GL 51 SMR','Avento',NULL,338,'A','2021-10-22 00:00:00',NULL),(1005,'GL 51 DFZ','Cuda Kinetic',NULL,334,'D','2021-10-22 00:00:00',NULL),(1006,'GL 72 FDG','Wisper',NULL,337,'A','2021-10-22 00:00:00',NULL),(1007,'GL 25 QRT','Merida',NULL,336,'A','2021-10-22 00:00:00',NULL);
/*!40000 ALTER TABLE `bike_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booking_details`
--

DROP TABLE IF EXISTS `booking_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking_details` (
  `booking_id` int NOT NULL AUTO_INCREMENT,
  `booking_no` varchar(10) DEFAULT NULL,
  `cust_id` int NOT NULL,
  `bike_id` int NOT NULL,
  `total_cost` double DEFAULT NULL,
  `booking_date_time` datetime DEFAULT NULL,
  `booking_status` varchar(2) DEFAULT NULL,
  `drop_date_time` datetime DEFAULT NULL,
  `pickup_station` int DEFAULT NULL,
  `drop_station` int DEFAULT NULL,
  PRIMARY KEY (`booking_id`),
  KEY `custid_fk` (`cust_id`),
  KEY `bikeid_fk` (`bike_id`),
  CONSTRAINT `bikeid_fk` FOREIGN KEY (`bike_id`) REFERENCES `bike_master` (`bike_id`),
  CONSTRAINT `custid_fk` FOREIGN KEY (`cust_id`) REFERENCES `user_master` (`cust_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking_details`
--

LOCK TABLES `booking_details` WRITE;
/*!40000 ALTER TABLE `booking_details` DISABLE KEYS */;
INSERT INTO `booking_details` VALUES (1,'RR/1',1,1003,1,'2021-11-03 17:28:33','C','2021-11-03 18:50:11',334,337),(2,'RR/2',5,1000,3,'2021-11-03 15:33:36','C','2021-11-03 18:50:43',333,334),(3,'RR/3',2,1004,1,'2021-11-03 18:06:26','C','2021-11-03 18:51:05',338,338),(4,'RR/4',1,1002,2,'2021-11-03 18:00:23','C','2021-11-03 19:11:51',335,338),(5,'RR/5',1,1000,1,'2021-11-03 19:22:19','C','2021-11-03 19:23:01',334,335),(6,'RR/6',1,1001,1,'2021-11-04 11:58:36','C','2021-11-04 11:58:48',333,338);
/*!40000 ALTER TABLE `booking_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `repair_request`
--

DROP TABLE IF EXISTS `repair_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `repair_request` (
  `req_no` int NOT NULL AUTO_INCREMENT,
  `bike_id` int DEFAULT NULL,
  `request_by` int DEFAULT NULL,
  `created_datetime` datetime DEFAULT NULL,
  `issue_desc` varchar(100) DEFAULT NULL,
  `req_status` varchar(1) DEFAULT NULL,
  `repaired_datetime` datetime DEFAULT NULL,
  `repaired_by` int DEFAULT NULL,
  PRIMARY KEY (`req_no`),
  KEY `bike_fk` (`bike_id`),
  KEY `requestby_fk` (`request_by`),
  KEY `repairedby_fk` (`repaired_by`),
  CONSTRAINT `bike_fk` FOREIGN KEY (`bike_id`) REFERENCES `bike_master` (`bike_id`),
  CONSTRAINT `repairedby_fk` FOREIGN KEY (`repaired_by`) REFERENCES `user_master` (`cust_id`),
  CONSTRAINT `requestby_fk` FOREIGN KEY (`request_by`) REFERENCES `user_master` (`cust_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `repair_request`
--

LOCK TABLES `repair_request` WRITE;
/*!40000 ALTER TABLE `repair_request` DISABLE KEYS */;
INSERT INTO `repair_request` VALUES (1,1005,2,'2021-11-03 18:53:38','Broken handle','U','2021-11-03 18:58:10',3),(2,1002,2,'2021-11-03 18:53:54','Tire Puncture','F','2021-11-03 18:57:59',3);
/*!40000 ALTER TABLE `repair_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review_response`
--

DROP TABLE IF EXISTS `review_response`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review_response` (
  `review_id` int NOT NULL AUTO_INCREMENT,
  `reviewed_by` int DEFAULT NULL,
  `booking_id` int DEFAULT NULL,
  `review_desc` varchar(100) DEFAULT NULL,
  `no_of_stars` int DEFAULT NULL,
  PRIMARY KEY (`review_id`),
  KEY `reviewedby_fk` (`reviewed_by`),
  CONSTRAINT `reviewedby_fk` FOREIGN KEY (`reviewed_by`) REFERENCES `user_master` (`cust_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review_response`
--

LOCK TABLES `review_response` WRITE;
/*!40000 ALTER TABLE `review_response` DISABLE KEYS */;
INSERT INTO `review_response` VALUES (1,1,1,'Extremely happy with the bike condition',5),(2,2,2,'very easy to use',5),(3,3,3,'Had so much fun riding the bike',5);
/*!40000 ALTER TABLE `review_response` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `station_master`
--

DROP TABLE IF EXISTS `station_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `station_master` (
  `station_id` int NOT NULL AUTO_INCREMENT,
  `station_name` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `zipcode` varchar(25) DEFAULT NULL,
  `total_capacity` int DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  PRIMARY KEY (`station_id`)
) ENGINE=InnoDB AUTO_INCREMENT=339 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `station_master`
--

LOCK TABLES `station_master` WRITE;
/*!40000 ALTER TABLE `station_master` DISABLE KEYS */;
INSERT INTO `station_master` VALUES (333,'HillHead','Hillhead, Glasgow G12 8SH','G128SH',30,'2021-10-22 00:00:00'),(334,'Buchanan Street','Buchanan Street,Glasgow G1 2LL','G12LL',50,'2021-10-22 00:00:00'),(335,'Cowcadden','Cowcadden, Glasgow','G4 0XG',30,'2021-10-22 00:00:00'),(336,'Castle Street','Castle Street, Glasgow','G11 6PJ',60,'2021-10-22 00:00:00'),(337,'Govanhill Street','Govanhill Street, Glasgow, Scotland','G42 7JZ',70,'2021-10-22 00:00:00'),(338,'Partick','Partick','G11 5DE',50,'2021-10-22 00:00:00');
/*!40000 ALTER TABLE `station_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_master`
--

DROP TABLE IF EXISTS `user_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_master` (
  `cust_id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `ph_no` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `email_id` varchar(45) DEFAULT NULL,
  `DOB` varchar(45) DEFAULT NULL,
  `gender` varchar(2) DEFAULT NULL,
  `role` varchar(2) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  PRIMARY KEY (`cust_id`),
  UNIQUE KEY `email_id_UNIQUE` (`email_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_master`
--

LOCK TABLES `user_master` WRITE;
/*!40000 ALTER TABLE `user_master` DISABLE KEYS */;
INSERT INTO `user_master` VALUES (1,'Samriti','Sadhu','01234456','Pass@123','samriti@gmail.com','30-04-1991','F','C','2021-11-03 18:25:21'),(2,'Liang','Juhao','0123456','Pass@123','liang@gmail.com','01-01-1998','M','C','2021-11-03 18:26:01'),(3,'Varsha','Joshy','01234556','Pass@123','varsha@gmail.com','01-01-1999','F','O','2021-11-03 18:26:35'),(4,'Li','Jinxin','01234567','Pass@123','Li@gmail.com','01-01-1998','M','M','2021-11-03 18:27:05'),(5,'Haridha','Shekhar','01234456','Pass@123','haridha@gmail.com','01-01-1998','F','C','2021-11-03 18:27:41'),(6,'Lyu','Xinheng','01234456','Pass@123','lyu@gmail.com','01-01-1999','M','M','2021-11-03 18:28:09'),(7,'SamritiS','Sadhu','01234567','Pass@123','sam@gmail.com','30-04-1991','F','C','2021-11-04 12:17:28');
/*!40000 ALTER TABLE `user_master` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-04 13:02:36
