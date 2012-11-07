CREATE DATABASE  IF NOT EXISTS `listeningenglish` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `listeningenglish`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: listeningenglish
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
INSERT INTO `track` VALUES (1,29626,'tracks/1.mp3','script/1.txt',1,' '),(2,40703,'tracks/2.mp3','script/2.txt',1,' '),(3,54897,'tracks/3.mp3','script/3.txt',1,' '),(4,64108,'tracks/4.mp3','script/4.txt',2,' '),(5,75720,'tracks/5.mp3','script/5.txt',3,' '),(6,86878,'tracks/6.mp3','script/6.txt',4,' '),(7,99414,'tracks/7.mp3','script/7.txt',5,' '),(8,118502,'tracks/8.mp3','script/8.txt',6,' '),(9,194000,'tracks/9.mp3','script/9.txt',6,' ');
/*!40000 ALTER TABLE `track` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lession`
--

DROP TABLE IF EXISTS `lession`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lession` (
  `id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `level` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lession`
--

LOCK TABLES `lession` WRITE;
/*!40000 ALTER TABLE `lession` DISABLE KEYS */;
INSERT INTO `lession` VALUES (1,'Bai1',1),(2,'Bai2',1),(3,'Bai3',2),(4,'Bai4',1),(5,'Bai5',2),(6,'Bai6',3);
/*!40000 ALTER TABLE `lession` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(32) DEFAULT NULL,
  `username` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'12345','duc'),(2,'12345','sang'),(3,'12345','duong'),(4,'12345','Dat'),(5,'hn2221992','papo');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `listen`
--

DROP TABLE IF EXISTS `listen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `listen` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) NOT NULL,
  `lessID` int(11) NOT NULL,
  `Time` date DEFAULT NULL,
  `Score` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`,`userID`,`lessID`),
  KEY `fk_User_has_Lession_Lession1_idx` (`lessID`),
  KEY `fk_User_has_Lession_User_idx` (`userID`),
  CONSTRAINT `fk_User_has_Lession_Lession1` FOREIGN KEY (`lessID`) REFERENCES `lession` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_has_Lession_User` FOREIGN KEY (`userID`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `listen`
--

LOCK TABLES `listen` WRITE;
/*!40000 ALTER TABLE `listen` DISABLE KEYS */;
INSERT INTO `listen` VALUES (1,1,1,'2012-09-23',100),(2,1,2,'2012-09-23',120),(3,4,2,'2012-09-23',115),(4,4,1,'2012-09-23',89),(5,1,3,'2012-09-24',125),(6,1,3,'2012-09-24',125),(7,1,3,'2012-09-24',125),(8,1,3,'2012-09-24',125),(9,1,3,'2012-09-24',125),(10,1,3,'2012-09-24',125),(11,1,3,'2012-09-24',125),(12,1,3,'2012-09-24',125),(13,1,3,'2012-09-24',125),(14,1,3,'2012-09-24',125),(15,4,2,'2012-09-24',123);
/*!40000 ALTER TABLE `listen` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-11-07 17:50:12
