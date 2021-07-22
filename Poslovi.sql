-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jul 22, 2021 at 11:20 AM
-- Server version: 5.7.31
-- PHP Version: 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `poslovi`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin` (
  `Id` int(11) NOT NULL,
  `Ime` varchar(30) COLLATE utf8mb4_bin NOT NULL,
  `Prezime` varchar(30) COLLATE utf8mb4_bin NOT NULL,
  KEY `Id` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`Id`, `Ime`, `Prezime`) VALUES
(27, 'Pera', 'Peric');

-- --------------------------------------------------------

--
-- Table structure for table `glavna`
--

DROP TABLE IF EXISTS `glavna`;
CREATE TABLE IF NOT EXISTS `glavna` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(20) COLLATE utf8mb4_bin NOT NULL,
  `Password` varchar(60) COLLATE utf8mb4_bin NOT NULL,
  `Mail` varchar(254) COLLATE utf8mb4_bin NOT NULL,
  `Type` int(11) NOT NULL,
  `Token` varchar(32) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `glavna`
--

INSERT INTO `glavna` (`Id`, `Username`, `Password`, `Mail`, `Type`, `Token`) VALUES
(27, 'Admin', '$2a$10$TFdsM1AUNQ6R2k/fFwWAtur4wZlg7syTcEf.7X6q74Bl4AQx1x/6y', 'admin@gmail.com', 3, 'uktW42mck429V22bdXgeco3IcTta6kH'),
(28, 'Bane', '$2a$10$mxCSjcbBUZTQNr9HrdtHr.fI9SAqaqHJ0de5bAxORGd3e5Kxslc5y', 'bane@gmail.com', 2, '0cOUlhAPE8hnkR1o4pny9hPmiIu91fci'),
(29, 'Zika', '$2a$10$63neYvkTmySyfCt8pFdiROsb3GcL4gvVsUunFT1hXSbMyQChh3LP6', 'zika@gmail.com', 2, 'S12k8wfqYCcWbDfa10bms1tBbfQ7xw1L'),
(30, 'Mika', '$2a$10$Cx62qG/7VxzaFIg0JaQfROcfLuI7ivtC4PpAQP6oEXgORU1JET/jC', 'mika@gmail.com', 1, 'Bi53L2iDzMw4Wta0sPJgj534zfCkvpbY'),
(31, 'Aca', '$2a$10$8TWsf5c5rF2XlfLgjz88aOrGUh6XHovmh3AS4Y5oR2vZH8zqgVPvu', 'aca@gmail.com', 1, 'BlqtgP1rMn3ufgFv1i3b5aVWxgVYgz9A'),
(32, 'Cira', '$2a$10$iLAZPbEe.gdKPprZ5Hcb9u8C0cPTzft9wVDtQ4WzmIGRu0eG3BYKa', 'ciraboxkg@gmail.com', 2, 'dSa4fBef58WUTHU6wEA5t8hpD2AKP7zq'),
(33, 'fdsfsdfsd', '$2a$10$2nrBDETJpvMtpSFSWAvcmOCgEpJEHc.O4Jl.Hem7TT/i.lFQhYHEi', 'fsdfdsfsddf@gmail.com', 1, 'cjMg05pxiJborH12oat0PGPp9Ydl5p7f'),
(34, 'hfghfghfghfg', '$2a$10$lbrEzviwg12LJl/wEBXWe.BYFDhJ03pvWLebp14Ux8LJXV1Om1BUK', 'hgfhfg@gmail.com', 2, 'zckhzBBbGqumpvgHvdIV9p9g6bbzbahq');

-- --------------------------------------------------------

--
-- Table structure for table `kategorije`
--

DROP TABLE IF EXISTS `kategorije`;
CREATE TABLE IF NOT EXISTS `kategorije` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Naziv` varchar(30) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- --------------------------------------------------------

--
-- Table structure for table `komentari`
--

DROP TABLE IF EXISTS `komentari`;
CREATE TABLE IF NOT EXISTS `komentari` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `IdOglasa` int(11) NOT NULL,
  `IdUsera` int(11) NOT NULL,
  `Tekst` varchar(300) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `IdOglasa` (`IdOglasa`),
  KEY `IdRadnika` (`IdUsera`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `komentari`
--

INSERT INTO `komentari` (`Id`, `IdOglasa`, `IdUsera`, `Tekst`) VALUES
(1, 2, 31, 'alo aco'),
(2, 2, 30, 'molim'),
(3, 3, 27, 'olijoijouiiuo');

-- --------------------------------------------------------

--
-- Table structure for table `lajkovioglasa`
--

DROP TABLE IF EXISTS `lajkovioglasa`;
CREATE TABLE IF NOT EXISTS `lajkovioglasa` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `IdOglasa` int(11) NOT NULL,
  `IdRadnika` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `IDOglasa` (`IdOglasa`),
  KEY `IDRadnika` (`IdRadnika`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `lajkovioglasa`
--

INSERT INTO `lajkovioglasa` (`Id`, `IdOglasa`, `IdRadnika`) VALUES
(1, 3, 31);

-- --------------------------------------------------------

--
-- Table structure for table `oglas`
--

DROP TABLE IF EXISTS `oglas`;
CREATE TABLE IF NOT EXISTS `oglas` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Naziv` varchar(30) COLLATE utf8mb4_bin NOT NULL,
  `IdPoslodavca` int(11) NOT NULL,
  `Datum-Postavljanja` date NOT NULL,
  `Datum-Isteka` date DEFAULT NULL,
  `Lajkovi` int(11) NOT NULL,
  `Posete` int(11) NOT NULL,
  `Kategorija` varchar(30) COLLATE utf8mb4_bin NOT NULL,
  `PodKategorije` varchar(30) COLLATE utf8mb4_bin NOT NULL,
  `Plata` int(11) DEFAULT NULL,
  `Slika` varchar(300) COLLATE utf8mb4_bin NOT NULL,
  `Tekst` varchar(300) COLLATE utf8mb4_bin NOT NULL,
  `Remote` tinyint(1) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `IdPoslodavca` (`IdPoslodavca`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `oglas`
--

INSERT INTO `oglas` (`Id`, `Naziv`, `IdPoslodavca`, `Datum-Postavljanja`, `Datum-Isteka`, `Lajkovi`, `Posete`, `Kategorija`, `PodKategorije`, `Plata`, `Slika`, `Tekst`, `Remote`) VALUES
(1, 'Programer', 28, '2021-07-20', '2021-07-23', 0, 0, 'IT', 'Java', 1000, 'Slika', 'Veoma dobar oglas', 0),
(2, 'Programer', 32, '2021-07-20', '2021-07-23', 0, 0, 'IT', 'Java', 5432, 'dasdas', 'dasdasdas', 0),
(3, 'Programer', 29, '2021-07-20', '2021-07-30', 0, 0, 'IT', 'Java', 1000, 'hgfhfghfg', 'hgfhgfhfg', 1);

-- --------------------------------------------------------

--
-- Table structure for table `podkategorije`
--

DROP TABLE IF EXISTS `podkategorije`;
CREATE TABLE IF NOT EXISTS `podkategorije` (
  `IdKategorije` int(11) NOT NULL,
  `Naziv` varchar(30) COLLATE utf8mb4_bin NOT NULL,
  KEY `IdKategorije` (`IdKategorije`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- --------------------------------------------------------

--
-- Table structure for table `poslodavac`
--

DROP TABLE IF EXISTS `poslodavac`;
CREATE TABLE IF NOT EXISTS `poslodavac` (
  `Id` int(11) NOT NULL,
  `Naziv` varchar(30) COLLATE utf8mb4_bin NOT NULL,
  `Adresa` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  `Slika` varchar(300) COLLATE utf8mb4_bin NOT NULL,
  `Ocena` double DEFAULT NULL,
  KEY `Id` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `poslodavac`
--

INSERT INTO `poslodavac` (`Id`, `Naziv`, `Adresa`, `Slika`, `Ocena`) VALUES
(28, 'Branislav inc', 'Petra Prvog', 'C:\\fakepath\\projekatproba.sql', NULL),
(29, 'Branislav inc', 'Petra Prvog', 'C:\\fakepath\\projekatproba.sql', NULL),
(32, 'Old', 'dsadasdas', 'C:\\fakepath\\pom.xml', NULL),
(34, 'gfdgfdgdf', 'gdfgdfgfd', 'C:\\fakepath\\42 perfect clears IN A ROW! Jstris PC mode.mp4', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `prijave`
--

DROP TABLE IF EXISTS `prijave`;
CREATE TABLE IF NOT EXISTS `prijave` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `IdOglasa` int(11) NOT NULL,
  `IdRadnika` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `IdOglasa` (`IdOglasa`),
  KEY `IdRadnika` (`IdRadnika`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `prijave`
--

INSERT INTO `prijave` (`Id`, `IdOglasa`, `IdRadnika`) VALUES
(1, 1, 31),
(4, 2, 31),
(5, 3, 31);

-- --------------------------------------------------------

--
-- Table structure for table `radnik`
--

DROP TABLE IF EXISTS `radnik`;
CREATE TABLE IF NOT EXISTS `radnik` (
  `Id` int(11) NOT NULL,
  `Ime` varchar(30) COLLATE utf8mb4_bin NOT NULL,
  `Prezime` varchar(30) COLLATE utf8mb4_bin NOT NULL,
  `Date` date DEFAULT NULL,
  `Slika` varchar(300) COLLATE utf8mb4_bin NOT NULL,
  KEY `Id` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `radnik`
--

INSERT INTO `radnik` (`Id`, `Ime`, `Prezime`, `Date`, `Slika`) VALUES
(30, 'Mika', 'Mikic', '2000-10-10', 'C:\\fakepath\\ProjekatTest.iml'),
(31, 'Aca', 'Acic', '2001-10-10', 'C:\\fakepath\\ProjekatTest.iml'),
(33, 'gfdgfdg', 'gdfgdf', '2000-10-10', 'C:\\fakepath\\pom.xml');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `Admin_ibfk_1` FOREIGN KEY (`Id`) REFERENCES `glavna` (`Id`);

--
-- Constraints for table `komentari`
--
ALTER TABLE `komentari`
  ADD CONSTRAINT `Komentari_ibfk_1` FOREIGN KEY (`IdOglasa`) REFERENCES `oglas` (`Id`),
  ADD CONSTRAINT `Komentari_ibfk_2` FOREIGN KEY (`IdUsera`) REFERENCES `glavna` (`Id`);

--
-- Constraints for table `lajkovioglasa`
--
ALTER TABLE `lajkovioglasa`
  ADD CONSTRAINT `LajkoviOglasa_ibfk_1` FOREIGN KEY (`IdOglasa`) REFERENCES `oglas` (`Id`),
  ADD CONSTRAINT `LajkoviOglasa_ibfk_2` FOREIGN KEY (`IdRadnika`) REFERENCES `radnik` (`Id`);

--
-- Constraints for table `oglas`
--
ALTER TABLE `oglas`
  ADD CONSTRAINT `Oglas_ibfk_1` FOREIGN KEY (`IdPoslodavca`) REFERENCES `poslodavac` (`Id`);

--
-- Constraints for table `podkategorije`
--
ALTER TABLE `podkategorije`
  ADD CONSTRAINT `podkategorije_ibfk_1` FOREIGN KEY (`IdKategorije`) REFERENCES `kategorije` (`Id`);

--
-- Constraints for table `poslodavac`
--
ALTER TABLE `poslodavac`
  ADD CONSTRAINT `Poslodavac_ibfk_1` FOREIGN KEY (`Id`) REFERENCES `glavna` (`Id`);

--
-- Constraints for table `prijave`
--
ALTER TABLE `prijave`
  ADD CONSTRAINT `Prijave_ibfk_1` FOREIGN KEY (`IdOglasa`) REFERENCES `oglas` (`Id`),
  ADD CONSTRAINT `Prijave_ibfk_2` FOREIGN KEY (`IdRadnika`) REFERENCES `radnik` (`Id`);

--
-- Constraints for table `radnik`
--
ALTER TABLE `radnik`
  ADD CONSTRAINT `Radnik_ibfk_1` FOREIGN KEY (`Id`) REFERENCES `glavna` (`Id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
