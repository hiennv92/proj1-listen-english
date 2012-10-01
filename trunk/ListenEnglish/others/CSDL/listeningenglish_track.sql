CREATE DATABASE  IF NOT EXISTS `listeningenglish` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `listeningenglish`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: listeningenglish
-- ------------------------------------------------------
-- Server version	5.5.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `track`
--

DROP TABLE IF EXISTS `track`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `track` (
  `id` int(11) NOT NULL,
  `length` int(11) DEFAULT NULL,
  `audioFile` varchar(45) DEFAULT NULL,
  `scriptFile` varchar(45) DEFAULT NULL,
  `lessID` int(11) NOT NULL,
  `suggest` text,
  PRIMARY KEY (`id`,`lessID`),
  KEY `fk_Track_Lession1_idx` (`lessID`),
  CONSTRAINT `fk_Track_Lession1` FOREIGN KEY (`lessID`) REFERENCES `lession` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `track`
--

LOCK TABLES `track` WRITE;
/*!40000 ALTER TABLE `track` DISABLE KEYS */;
INSERT INTO `track` VALUES (1,8,'1.mp3','1.txt',1,' '),(2,8,'2.mp3','2.txt',1,' '),(3,8,'3.mp3','3.txt',2,' '),(4,8,'4.mp3','4.txt',3,' '),(5,8,'5.mp3','5.txt',3,' '),(6,8,'6.mp3','6.txt',4,' '),(7,8,'7.mp3','7.txt',5,' '),(8,8,'8.mp3','8.txt',6,' '),(9,20,'9.mp3','9.txt',6,' '),(10,20,'10.mp3','10.txt',6,' '),(11,20,'11.mp3','11.txt',6,' '),(12,20,'12.mp3','12.txt',6,' '),(13,20,'13.mp3','13.txt',6,' ');
/*!40000 ALTER TABLE `track` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-10-01 18:30:27
