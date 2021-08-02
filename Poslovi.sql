-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Aug 02, 2021 at 08:16 AM
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
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

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
(45, 'Petar123', '$2a$10$R9OdPq0cqOLd1QyoyDvcz.Gyl1byDXU46vkIjcXFigQgqxMSrqbb6', 'cifdsraboxkg@gmfdsail.com', 2, 'Pmk39og3arFoOhznAyES9hq04fz6ec6z'),
(46, 'Mika123', '$2a$10$3ScbtGHGAqb4T2AZcxVmt.rpkyC6ZUH90/YFYzjX7VLgOjbnMpe8i', 'ciradsaboxkg@gmdasail.com', 1, 'rKDieiYP78ijdj7AcTy34D9yVB0lcf80'),
(47, 'Lazar', '$2a$10$yarY8dMT9bg6iitZfjopGuVyFhchzb75eV20IndHEs4OxBk8Nj2fK', 'cirabodsadasxkg@gmail.com', 2, '9lm8w4Fh6fuBm4tngbXlqxIMx9r9zA67'),
(48, 'Peraaaaa', '$2a$10$VLmN2Pq3P7N7ai3ULwva1OB94crnvkLsoBaPf9IaGcPSBJRl/gh3C', 'ciradsadasdasboxkg@gmail.com', 2, '7282Z5pm7xFoD5waeBee3yj9d5f9Rhrw'),
(49, 'gdfgdfgdf', '$2a$10$6kGDK/8r7EpWW3rVY9LTUe/SlmCUACQGjp0Hy4DSX1oSog0/jrCOm', 'cirafdsfdsboxkg@gmail.com', 2, '52rWWbXYBQCXK4vwbg6y9sfxdhj8mRv9'),
(50, 'hgfhfghfg', '$2a$10$t9wdL4ct4cLHu31RejbBSu2csYWFhpBf5fF9G8JisgeY/yBE9eHsi', 'cirahgfhfgfghboxkg@gmail.com', 2, 'qOgvNSf0AfFyVv1gcVa80kXEtsCgAv2C'),
(51, 'fsdfsdfsd', '$2a$10$3vlRBhrGQLqL.i.aqemQBuDl8ZnHwzxyW.nT4jvDsXAWr6308FsQ.', 'cirabfdsfsdoxkg@gmail.com', 2, 'rJu067h9FFE7xAiIRjfV1ntRQawtr0Zv'),
(52, 'hhfghfg', '$2a$10$H4dcjkeiSEyFlaZVQ5Vvv.YeXJs/riXEAE4a4PYPcZEPgXhOSrECu', 'cirabhgfhfgoxkg@gmail.com', 2, 'j90ilp6r5qWvCv4bwt5Y3mkYbF5n9RLf'),
(53, 'hgfhghgfhfgf', '$2a$10$M37YMRWIe5GN0x9kt0H/B.tEVSjxygDc3tVW52a86zEj/tQDrhqxe', 'cirahfghgfboxkg@gmail.com', 1, 'dxmVK39f7D23T8e1kE3g9ws8S66cLAcS');

-- --------------------------------------------------------

--
-- Table structure for table `kategorije`
--

DROP TABLE IF EXISTS `kategorije`;
CREATE TABLE IF NOT EXISTS `kategorije` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Naziv` varchar(30) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `kategorije`
--

INSERT INTO `kategorije` (`Id`, `Naziv`) VALUES
(1, 'Administration'),
(2, 'Animals'),
(3, 'Computing and ICT'),
(4, 'Construction'),
(5, 'Design, arts');

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
(3, 'Programer', 29, '2021-07-20', '2021-07-30', 1, 0, 'IT', 'Java', 1000, 'hgfhfghfg', 'hgfhgfhfg', 1);

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

--
-- Dumping data for table `podkategorije`
--

INSERT INTO `podkategorije` (`IdKategorije`, `Naziv`) VALUES
(1, 'Administrative assistant'),
(1, ' Car rental agent'),
(1, 'Compliance officer'),
(2, 'Agricultural consultant'),
(2, 'Animal technician'),
(2, 'Dog handler'),
(3, 'App developer'),
(3, 'Big data engineer'),
(3, 'Database administrator'),
(4, 'Architect'),
(4, 'Bricklayer'),
(4, ' Building technician'),
(5, 'Animator'),
(5, 'Graphic designer'),
(5, 'Illustrator');

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
(45, 'fsdfsd', 'fdssdf', 'Petar123.', NULL),
(47, 'dsdas', 'dsadsa', 'Lazar.png', NULL),
(48, 'dsadsa', 'dsadsa', 'Peraaaaa.png', NULL),
(49, 'fdsfsd', 'fsdfsdfds', 'gdfgdfgdf.mkv', NULL),
(50, 'hfghfg', 'hgfhfg', 'hgfhfghfg.png', NULL),
(51, 'fdsfds', 'fdsfds', 'fsdfsdfsd.mkv', NULL),
(52, 'hgfhfg', 'hgfhfg', 'hhfghfg.png', NULL);

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
(46, 'dsadas', 'dsadas', '2000-10-10', 'Mika123.png'),
(53, 'hgfhfg', 'hghfg', '2000-02-02', 'hgfhghgfhfgf.png');

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
