-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Feb 28, 2021 at 12:42 AM
-- Server version: 8.0.18
-- PHP Version: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mediat`
--

-- --------------------------------------------------------

--
-- Table structure for table `audio`
--

DROP TABLE IF EXISTS `audio`;
CREATE TABLE IF NOT EXISTS `audio` (
  `ID` int(6) NOT NULL AUTO_INCREMENT,
  `DURATION` varchar(10) NOT NULL,
  `ID_DOC` int(6) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID_DOC` (`ID_DOC`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `author`
--

DROP TABLE IF EXISTS `author`;
CREATE TABLE IF NOT EXISTS `author` (
  `ID` int(6) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(32) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `author`
--

INSERT INTO `author` (`ID`, `NAME`) VALUES
(1, 'Ayoub EL AOUFI'),
(2, 'Amr EL KHASSAL');

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
CREATE TABLE IF NOT EXISTS `book` (
  `ID` int(6) NOT NULL AUTO_INCREMENT,
  `ISBN` varchar(32) NOT NULL,
  `NBR_PAGES` int(5) NOT NULL,
  `ID_DOC` int(6) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID_DOC` (`ID_DOC`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `book`
--

INSERT INTO `book` (`ID`, `ISBN`, `NBR_PAGES`, `ID_DOC`) VALUES
(1, 'ISBN-123456', 69, 15);

-- --------------------------------------------------------

--
-- Table structure for table `document`
--

DROP TABLE IF EXISTS `document`;
CREATE TABLE IF NOT EXISTS `document` (
  `ID` int(6) NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(64) NOT NULL,
  `DESCRIPTION` text NOT NULL,
  `NAME_OF_FILE` varchar(64) NOT NULL,
  `PATH` varchar(255) NOT NULL,
  `ID_TYPE` int(11) NOT NULL,
  `ID_AUTHOR` int(6) NOT NULL,
  `ID_GENRE` int(6) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID_TYPE` (`ID_TYPE`,`ID_AUTHOR`,`ID_GENRE`),
  KEY `ID_GENRE` (`ID_GENRE`),
  KEY `ID_AUTHOR` (`ID_AUTHOR`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `document`
--

INSERT INTO `document` (`ID`, `TITLE`, `DESCRIPTION`, `NAME_OF_FILE`, `PATH`, `ID_TYPE`, `ID_AUTHOR`, `ID_GENRE`) VALUES
(14, 'image title', 'image description', 'if_only_44444.PNG', 'C:/Users/ayoub/Desktop/', 3, 1, 7),
(15, 'book title', 'book description', 'exo2.pdf', 'C:/Users/ayoub/Desktop/', 4, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `genre`
--

DROP TABLE IF EXISTS `genre`;
CREATE TABLE IF NOT EXISTS `genre` (
  `ID` int(6) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(32) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `genre`
--

INSERT INTO `genre` (`ID`, `NAME`) VALUES
(1, 'Computer Science'),
(2, 'pog'),
(6, 'Science Fiction'),
(7, 'Art'),
(12, 'new genre');

-- --------------------------------------------------------

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
CREATE TABLE IF NOT EXISTS `image` (
  `ID` int(6) NOT NULL AUTO_INCREMENT,
  `RESOLUTION` varchar(10) NOT NULL,
  `ID_DOC` int(6) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID_DOC` (`ID_DOC`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `image`
--

INSERT INTO `image` (`ID`, `RESOLUTION`, `ID_DOC`) VALUES
(2, '1080x1080', 14);

-- --------------------------------------------------------

--
-- Table structure for table `type`
--

DROP TABLE IF EXISTS `type`;
CREATE TABLE IF NOT EXISTS `type` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(32) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `type`
--

INSERT INTO `type` (`ID`, `NAME`) VALUES
(1, 'Audio'),
(2, 'Video'),
(3, 'Image'),
(4, 'Book'),
(5, 'ToBeDetermined');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `ID` int(6) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`ID`, `username`, `password`) VALUES
(1, 'admin', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `video`
--

DROP TABLE IF EXISTS `video`;
CREATE TABLE IF NOT EXISTS `video` (
  `ID` int(6) NOT NULL AUTO_INCREMENT,
  `DURATION` varchar(10) NOT NULL,
  `QUALITY` varchar(10) NOT NULL,
  `ID_DOC` int(6) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID_DOC` (`ID_DOC`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `audio`
--
ALTER TABLE `audio`
  ADD CONSTRAINT `audio_ibfk_1` FOREIGN KEY (`ID_DOC`) REFERENCES `document` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `book`
--
ALTER TABLE `book`
  ADD CONSTRAINT `book_ibfk_1` FOREIGN KEY (`ID_DOC`) REFERENCES `document` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `document`
--
ALTER TABLE `document`
  ADD CONSTRAINT `document_ibfk_1` FOREIGN KEY (`ID_TYPE`) REFERENCES `type` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `document_ibfk_2` FOREIGN KEY (`ID_GENRE`) REFERENCES `genre` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `document_ibfk_3` FOREIGN KEY (`ID_AUTHOR`) REFERENCES `author` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `image`
--
ALTER TABLE `image`
  ADD CONSTRAINT `image_ibfk_1` FOREIGN KEY (`ID_DOC`) REFERENCES `document` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `video`
--
ALTER TABLE `video`
  ADD CONSTRAINT `video_ibfk_1` FOREIGN KEY (`ID_DOC`) REFERENCES `document` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
