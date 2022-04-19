-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 17, 2019 at 02:18 AM
-- Server version: 10.1.19-MariaDB
-- PHP Version: 7.0.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pharmacie`
--

-- --------------------------------------------------------

--
-- Table structure for table `commande`
--

CREATE TABLE `commande` (
  `numcommande` varchar(20) NOT NULL,
  `codefournisseur` varchar(20) NOT NULL,
  `datecommande` date NOT NULL,
  `montant` decimal(20,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `commande`
--

INSERT INTO `commande` (`numcommande`, `codefournisseur`, `datecommande`, `montant`) VALUES
('1', '1', '2014-07-07', '650'),
('2', '1', '2014-07-06', '56'),
('3', '1', '2018-12-30', '750');

-- --------------------------------------------------------

--
-- Table structure for table `fournisseur`
--

CREATE TABLE `fournisseur` (
  `codefournisseur` varchar(20) NOT NULL,
  `nomfournisseur` varchar(20) DEFAULT NULL,
  `adressefournisseur` varchar(20) NOT NULL,
  `villefournisseur` varchar(20) NOT NULL,
  `telefournisseur` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fournisseur`
--

INSERT INTO `fournisseur` (`codefournisseur`, `nomfournisseur`, `adressefournisseur`, `villefournisseur`, `telefournisseur`) VALUES
('1', 'saidal', 'kach blasa', 'alger', 123456789),
('2', 'testPH', 'wad rhiw', 'relezan', 164823515);

-- --------------------------------------------------------

--
-- Table structure for table `lignecommande`
--

CREATE TABLE `lignecommande` (
  `numcommande` varchar(20) NOT NULL,
  `codemedicament` varchar(20) NOT NULL,
  `qtemedicament` decimal(20,0) NOT NULL,
  `prix_achat` decimal(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `lignecommande`
--

INSERT INTO `lignecommande` (`numcommande`, `codemedicament`, `qtemedicament`, `prix_achat`) VALUES
('3', '1', '30', '25');

-- --------------------------------------------------------

--
-- Table structure for table `medicament`
--

CREATE TABLE `medicament` (
  `codemedicament` varchar(20) NOT NULL,
  `code` varchar(20) NOT NULL,
  `libelle` varchar(20) NOT NULL,
  `prix` decimal(20,0) NOT NULL,
  `observation` varchar(20) NOT NULL,
  `posologie` varchar(20) NOT NULL,
  `nomMed` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `medicament`
--

INSERT INTO `medicament` (`codemedicament`, `code`, `libelle`, `prix`, `observation`, `posologie`, `nomMed`) VALUES
('1', '1', '25', '50', 'vent sont ordonas', 'pour enfant', 'catagen');

-- --------------------------------------------------------

--
-- Table structure for table `typemedicament`
--

CREATE TABLE `typemedicament` (
  `code` varchar(20) NOT NULL,
  `designation` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `typemedicament`
--

INSERT INTO `typemedicament` (`code`, `designation`) VALUES
('1', 'poudr'),
('2', 'Sirop'),
('3', 'capsol');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(20) NOT NULL,
  `login` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `login`, `password`) VALUES
(1, 'aaa', 'aaa'),
(2, 'oifae', '123');

-- --------------------------------------------------------

--
-- Table structure for table `vente`
--

CREATE TABLE `vente` (
  `numvente` int(20) NOT NULL,
  `datevente` date NOT NULL,
  `montant` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `vente`
--

INSERT INTO `vente` (`numvente`, `datevente`, `montant`) VALUES
(1, '2014-11-02', 120),
(2, '2018-12-30', 100),
(3, '2018-12-30', 150);

-- --------------------------------------------------------

--
-- Table structure for table `ventemedicament`
--

CREATE TABLE `ventemedicament` (
  `codemedicament` varchar(20) NOT NULL,
  `numvente` int(20) NOT NULL,
  `qtevente` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ventemedicament`
--

INSERT INTO `ventemedicament` (`codemedicament`, `numvente`, `qtevente`) VALUES
('1', 2, 2),
('1', 3, 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `commande`
--
ALTER TABLE `commande`
  ADD PRIMARY KEY (`numcommande`),
  ADD KEY `com-cle-etr-four` (`codefournisseur`);

--
-- Indexes for table `fournisseur`
--
ALTER TABLE `fournisseur`
  ADD PRIMARY KEY (`codefournisseur`);

--
-- Indexes for table `lignecommande`
--
ALTER TABLE `lignecommande`
  ADD KEY `lig-cle-etr-com` (`numcommande`),
  ADD KEY `lig-cle-etr-med` (`codemedicament`);

--
-- Indexes for table `medicament`
--
ALTER TABLE `medicament`
  ADD PRIMARY KEY (`codemedicament`),
  ADD KEY `med-cle-etr-typ` (`code`);

--
-- Indexes for table `typemedicament`
--
ALTER TABLE `typemedicament`
  ADD PRIMARY KEY (`code`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `vente`
--
ALTER TABLE `vente`
  ADD PRIMARY KEY (`numvente`);

--
-- Indexes for table `ventemedicament`
--
ALTER TABLE `ventemedicament`
  ADD KEY `ven-cle-etr-med` (`codemedicament`),
  ADD KEY `ven-cle-etr-ven` (`numvente`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `commande`
--
ALTER TABLE `commande`
  ADD CONSTRAINT `com-cle-etr-four` FOREIGN KEY (`codefournisseur`) REFERENCES `fournisseur` (`codefournisseur`);

--
-- Constraints for table `lignecommande`
--
ALTER TABLE `lignecommande`
  ADD CONSTRAINT `lig-cle-etr-com` FOREIGN KEY (`numcommande`) REFERENCES `commande` (`numcommande`),
  ADD CONSTRAINT `lig-cle-etr-med` FOREIGN KEY (`codemedicament`) REFERENCES `medicament` (`codemedicament`);

--
-- Constraints for table `medicament`
--
ALTER TABLE `medicament`
  ADD CONSTRAINT `med-cle-etr-type` FOREIGN KEY (`code`) REFERENCES `typemedicament` (`code`);

--
-- Constraints for table `ventemedicament`
--
ALTER TABLE `ventemedicament`
  ADD CONSTRAINT `ven-cle-etr-med` FOREIGN KEY (`codemedicament`) REFERENCES `medicament` (`codemedicament`),
  ADD CONSTRAINT `ven-cle-etr-ven` FOREIGN KEY (`numvente`) REFERENCES `vente` (`numvente`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
