-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Aug 16, 2021 at 12:30 PM
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
(56, 'Pera', 'Peric'),
(63, 'Zoran', 'Zokic');

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
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `glavna`
--

INSERT INTO `glavna` (`Id`, `Username`, `Password`, `Mail`, `Type`, `Token`) VALUES
(56, 'Admin', '$2a$10$rUDKqbe8IAmQfDpTxwd7luX.Co6zXl2ebXV318tASM99zruhE2joy', 'admin@imigmail.com', 3, 'uktW42mck429V22bdXgeco3IcTta6kH'),
(57, 'Cira', '$2a$10$5CoZENPEix0gKo8HHoCM6eujwY4gQPEF8DsTQJrK7e5d6SXVra4om', 'ciraboxkg@gmail.com', 2, 'SbBN4ehdnweCCrivZ4tFuBXipgghH87b'),
(58, 'Bane', '$2a$10$hhg89QrRgjYnHIWmLRSxZ.4du5gfwowHFjpFfUZZFy/5ioIv2uN32', 'wafflemynxyt@gmail.com', 2, 'Czo23MXv8cGeIdrx1BkOu90DVk8mnunQ'),
(59, 'Zika', '$2a$10$iMx8brB1RywqOlnheMv7zecQ.0RJQ7aR7ERTIxIs/zOgfc6pIzB0e', 'Zika@imigmail.com', 1, '4w84hEOl8O4BhisWM1qLBP7Amz8r3muh'),
(60, 'Mika', '$2a$10$I3.wSzET66OX9IXWzmagqOF4alDnCA2/xhm3AejqtTuDow78sewSC', 'Mika@imigmail.com', 1, 'o3IeLFBnnnYRxL1VcrMBkzJbirW5h4QN'),
(61, 'Aca', '$2a$10$.I7mEfPp7vIcBMDpuf8G5Or4JHGlDDgzKvjlSxNKcGEc1i5Gelcjm', 'Aca@imigmail.com', 1, 'zVqpVSZlvSoFDc0dRoUquA697H0buSP2'),
(62, 'Ana', '$2a$10$ERy1tsoI4tI2rH0nfTkM5ufcaIZaPs4D2wL9PS2KglrPAGDoKbcX6', 'Ana@imigmail.com', 1, 'dhzz8yPf6hp0gq9pYaqblyfa09jdg378'),
(63, 'Admin1', '$2a$10$Fr/3J2SfiedKpKs8Q0sVaOpKrDGVEvOPZ.HHdZng/WS4p3wBu1g3m', 'Admin1@imigmail.com', 3, 'wMa23EeEQtucBueoCT7YfwbPZPnhvqjc');

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
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `komentari`
--

INSERT INTO `komentari` (`Id`, `IdOglasa`, `IdUsera`, `Tekst`) VALUES
(21, 4, 57, 'Prijavete se na oglas obavezno.'),
(22, 4, 58, 'Moji oglas je bolji'),
(23, 6, 58, 'Odlican posao'),
(24, 4, 61, 'Prijavicu se obavezno'),
(25, 4, 60, 'Opasan Oglas'),
(26, 6, 60, 'Dobar'),
(27, 4, 62, 'Da'),
(28, 8, 62, 'Pogledajte moju prijavu'),
(29, 4, 56, 'Admin Komentar');

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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `lajkovioglasa`
--

INSERT INTO `lajkovioglasa` (`Id`, `IdOglasa`, `IdRadnika`) VALUES
(4, 4, 61),
(5, 4, 60),
(6, 6, 60),
(7, 4, 62),
(8, 8, 62);

-- --------------------------------------------------------

--
-- Table structure for table `ocene`
--

DROP TABLE IF EXISTS `ocene`;
CREATE TABLE IF NOT EXISTS `ocene` (
  `IdRadnika` int(11) NOT NULL,
  `IdPoslodavca` int(11) NOT NULL,
  `Ocena` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `ocene`
--

INSERT INTO `ocene` (`IdRadnika`, `IdPoslodavca`, `Ocena`) VALUES
(61, 57, 3),
(61, 58, 5),
(60, 57, 5),
(60, 58, 4),
(62, 57, 2),
(62, 58, 5);

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
  `Tekst` varchar(3000) COLLATE utf8mb4_bin NOT NULL,
  `Remote` tinyint(1) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `IdPoslodavca` (`IdPoslodavca`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `oglas`
--

INSERT INTO `oglas` (`Id`, `Naziv`, `IdPoslodavca`, `Datum-Postavljanja`, `Datum-Isteka`, `Lajkovi`, `Posete`, `Kategorija`, `PodKategorije`, `Plata`, `Slika`, `Tekst`, `Remote`) VALUES
(4, 'Java Programer', 57, '2021-08-16', '2021-10-10', 3, 0, 'Computing and ICT', 'App developer', 500, '2VemfSQQknHGdpIl.jpg', 'As a member of the Global Services Unit, you will be a part of a creative team responsible for all aspects of the ongoing software development from the initial specification through to developing, testing, and launching. Your focus will be to write Java and JavaScript to a high standard in a timely and scalable way that improves the code in meaningful ways. Also, as Devtech is focused on learning and development, you will be expected to participate in company-wide efforts and promote security, quality, and knowledge-sharing culture across the company.  In this role, you will report to your engineering team lead. Working with the team of Product Owners, Solution Architects, Software Developers, Quality Assurance and DevOps engineers on implementing complex back end system that powers cloud-based security product Developing high-quality solutions that are robust, resilient, and reliable Participating in technical design, code reviews, planning, and retrospective activities Interpreting design requirements and concepts to produce new or enhanced application functionalities ', 1),
(5, 'Gradjevina', 57, '2021-08-16', '2022-10-10', 0, 0, 'Construction', 'Bricklayer', -1, 'IwJMOLHW3HPXro5b.jpg', 'Potrebni Gradjevinski Majstori : Fasaderi, Limari, Zidari, Tesari, Pomocni Radnici I Radnici Za Rad Na Gradjevinskim MasinamaRadno Vreme: 07:00h do 17:00hIsplata: Na 15 danaHrana: DaSlobodni Dan: NedeljaSmestaj: Po dogovoruMesto rada: Beograd i okolina', 0),
(6, 'Veterinar', 57, '2021-08-16', '2021-11-11', 1, 0, 'Animals', 'Animal technician', 1000, 'u6XohVA0QnabEG5j.jpg', 'VeterinarPartners ', 0),
(7, 'Iznajmljivanje vozila', 58, '2021-08-16', '2022-10-10', 0, 0, 'Administration', 'Car rental agent', 500, 'fpEXGXShAO7eN9Cb.png', 'Full Job DescriptionKoons Tysons Toyota Full Time Rental AgentOur service department gets bombarded with clients, and we need a full time rental agent to help manage our fleet of service cars. Start at $13 an hour, for a sharp go getter willing to smile and hustle. Great opportunity to grow with family owned retail automotive giant! Must be tech savvy, and friendly with clients. Full benefits, and room to advance! Koons Tysons Toyota remains one of the biggest car dealerships in America. Apply today!', 0),
(8, 'Database Administrator', 58, '2021-08-16', '2022-12-10', 1, 0, 'Computing and ICT', 'App developer', 5000, 'VYNQdzjjnshUd0SR.jpg', 'Full timeType Of Hire :', 1);

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
(57, 'Cira Inc', 'Pere perica 25', 'Cira.png', 3.3333),
(58, 'Bane Inc', 'Mike Mikica 21', 'Bane.jpg', 4.6667);

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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `prijave`
--

INSERT INTO `prijave` (`Id`, `IdOglasa`, `IdRadnika`) VALUES
(10, 4, 61),
(11, 4, 60),
(12, 6, 60),
(13, 4, 62),
(14, 8, 62);

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
(59, 'Zika', 'Zikic', '2000-11-10', 'Zika.jpg'),
(60, 'Mika', 'Mikic', '2001-09-05', 'Mika.jpg'),
(61, 'Aca', 'Acic', '2001-03-01', 'Aca.jpg'),
(62, 'Ana', 'Anic', '2000-03-01', 'Ana.jpg');

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
