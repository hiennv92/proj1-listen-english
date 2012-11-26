CREATE DATABASE  IF NOT EXISTS `listeningenglish` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `listeningenglish`;
-- MySQL dump 10.13  Distrib 5.5.28, for debian-linux-gnu (i686)
--
-- Host: localhost    Database: listeningenglish
-- ------------------------------------------------------
-- Server version	5.5.28-0ubuntu0.12.04.2

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
  `audioFile` varchar(255) DEFAULT NULL,
  `script` text,
  `suggest` varchar(255) DEFAULT NULL,
  `lessID` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_track_lesson1` (`lessID`),
  CONSTRAINT `fk_track_lesson1` FOREIGN KEY (`lessID`) REFERENCES `lesson` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `track`
--

LOCK TABLES `track` WRITE;
/*!40000 ALTER TABLE `track` DISABLE KEYS */;
INSERT INTO `track` VALUES (1,11937,'tracks/1.mp3','In the early days of human history, people survived by hunting wild animals, or gathering wild grains and plants for food.',' 7,6,8.',1),(2,11153,'tracks/2.mp3','Then, some people learned to grow crops and raise animals for food. They were the first farmers.',' 1,11.5.',3),(3,14262,'tracks/3.mp3','Since the sixteenth century, the word farm has meant agricultural land. But a much older meaning of the word farm is linked to economics.',' 4,7.13.',4),(4,9272,'tracks/4.mp3','The word farm comes from the Latin word, firma, which means an unchanging payment.',' 6[Latin]1,1,5.',1),(5,11676,'tracks/5.mp3','Experts say the earliest meaning of the English word farm was a yearly payment made as a tax or rent.',' 7[English]12.',1),(6,11232,'tracks/6.mp3','Farmers in early England did not own their land. They paid every year to use agricultural lands.',' 3[England]5.8.',2),(7,12590,'tracks/7.mp3','In England, farmers used hawthorn trees along the edges of property. They called this row of hawthorns a hedge.',' 1[Endland],9.8.',2),(8,19147,'tracks/8.mp3','Hedging fields was how careful farmers marked and protected them. Soon, people began to use the word hedging to describe steps that could be taken to protect against financial loss.',' 10.1,19.',5),(9,23170,'tracks/9.mp3','Hedging is common among gamblers who make large bets. A gambler bets a lot of money on one team. But, to be on the safe side, he also places a smaller bet on the other team, to reduce a possible loss.',' 9.10.1,6,10,5.',6);
/*!40000 ALTER TABLE `track` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lesson`
--

DROP TABLE IF EXISTS `lesson`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lesson` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `level` tinyint(4) DEFAULT NULL,
  `previewFile` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lesson`
--

LOCK TABLES `lesson` WRITE;
/*!40000 ALTER TABLE `lesson` DISABLE KEYS */;
INSERT INTO `lesson` VALUES (1,'Bài 1',1,'tracks/1.mp3'),(2,'Bài 2',1,'tracks/2.mp3'),(3,'Bài 3',2,'tracks/4.mp3'),(4,'Bài 4',2,'tracks/6.mp3'),(5,'Bài 5',3,'tracks/7.mp3'),(6,'Bài 6',3,'tracks/8.mp3');
/*!40000 ALTER TABLE `lesson` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (7,'12345','sang');
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
  `time` date DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_User_has_Lession_Lession1_idx` (`lessID`),
  KEY `fk_User_has_Lession_User_idx` (`userID`),
  CONSTRAINT `fk_User_has_Lession_Lession1` FOREIGN KEY (`lessID`) REFERENCES `lesson` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_has_Lession_User` FOREIGN KEY (`userID`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `listen`
--

LOCK TABLES `listen` WRITE;
/*!40000 ALTER TABLE `listen` DISABLE KEYS */;
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

-- Dump completed on 2012-11-26 10:08:56
