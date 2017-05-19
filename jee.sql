-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Client :  127.0.0.1
-- Généré le :  Ven 19 Mai 2017 à 17:37
-- Version du serveur :  5.7.14
-- Version de PHP :  5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `jee`
--

-- --------------------------------------------------------

--
-- Structure de la table `article`
--

CREATE TABLE `article` (
  `ID` int(11) NOT NULL,
  `LIB` varchar(255) NOT NULL,
  `DESCRIPTION` text NOT NULL,
  `PRIX_HT` double NOT NULL,
  `TAUX_TVA` float NOT NULL,
  `STOCK` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `article`
--

INSERT INTO `article` (`ID`, `LIB`, `DESCRIPTION`, `PRIX_HT`, `TAUX_TVA`, `STOCK`) VALUES
(1, 'LibArticle1', 'Description1', 10.5, 0.2, 10),
(2, 'LibArticle2', 'Description2', 1.5, 0.05, 1),
(3, 'MonArticle3', 'Description Article 3', 10, 0.2, 15),
(4, 'MonArticle4', 'Description Article 4', 20, 0.05, 5);

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `ID` int(11) NOT NULL,
  `NOM` varchar(255) NOT NULL,
  `PRENOM` varchar(255) NOT NULL,
  `VILLE` varchar(255) NOT NULL,
  `ADRESSE` varchar(255) NOT NULL,
  `TEL` varchar(10) NOT NULL,
  `MAIL` varchar(255) NOT NULL,
  `MDP` varchar(255) NOT NULL,
  `LAST_CONNEXION` date NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `client`
--

INSERT INTO `client` (`ID`, `NOM`, `PRENOM`, `VILLE`, `ADRESSE`, `TEL`, `MAIL`, `MDP`, `LAST_CONNEXION`) VALUES
(1, 'pochon', 'amaury', 'TOulouse', 'Mon adresse', '', 'mail@test.fr', 'mdp', '2017-05-03');

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

CREATE TABLE `commande` (
  `ID` int(11) NOT NULL,
  `DATE_COMMANDE` date NOT NULL,
  `ID_CLIENT` int(11) NOT NULL,
  `ID_TOURNEE` int(11) DEFAULT NULL,
  `ID_STATUT` int(11) NOT NULL,
  `ID_COMPTE` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `commande`
--

INSERT INTO `commande` (`ID`, `DATE_COMMANDE`, `ID_CLIENT`, `ID_TOURNEE`, `ID_STATUT`, `ID_COMPTE`) VALUES
(1, '2017-05-03', 1, NULL, 1, 0),
(2, '2017-05-09', 2, NULL, 1, 0),
(3, '2017-05-03', 2, NULL, 1, 0),
(4, '2017-05-09', 2, NULL, 1, 0),
(12, '2017-05-19', 1, NULL, 1, 1),
(11, '2017-05-19', 1, NULL, 1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `compte`
--

CREATE TABLE `compte` (
  `ID` int(11) NOT NULL,
  `NUM_CARTE` varchar(50) NOT NULL,
  `CRYPTO` varchar(3) NOT NULL,
  `SOLDE` double NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `compte`
--

INSERT INTO `compte` (`ID`, `NUM_CARTE`, `CRYPTO`, `SOLDE`) VALUES
(1, '123456789', '123', 50);

-- --------------------------------------------------------

--
-- Structure de la table `ligne`
--

CREATE TABLE `ligne` (
  `ID` int(11) NOT NULL,
  `ID_ARTICLE` int(11) NOT NULL,
  `ID_COMMANDE` int(11) NOT NULL,
  `QTE` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `ligne`
--

INSERT INTO `ligne` (`ID`, `ID_ARTICLE`, `ID_COMMANDE`, `QTE`) VALUES
(1, 1, 1, 2),
(2, 3, 1, 4),
(3, 1, 11, 3),
(4, 2, 11, 2),
(5, 3, 11, 1),
(6, 1, 12, 1),
(7, 2, 12, 4);

-- --------------------------------------------------------

--
-- Structure de la table `statut`
--

CREATE TABLE `statut` (
  `ID` int(11) NOT NULL,
  `LIB` varchar(50) NOT NULL,
  `DESCRIPTION` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `statut`
--

INSERT INTO `statut` (`ID`, `LIB`, `DESCRIPTION`) VALUES
(1, 'ENCOURS', 'En cours (paiement non validé)'),
(2, 'PAIEMENTVALID', 'En cours (paiement validé)'),
(3, 'ENVOYE', 'Commande envoyé'),
(4, 'RECU', 'Commande recu');

-- --------------------------------------------------------

--
-- Structure de la table `tournee`
--

CREATE TABLE `tournee` (
  `ID` int(11) NOT NULL,
  `DATE_TOURNEE` date NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Index pour les tables exportées
--

--
-- Index pour la table `article`
--
ALTER TABLE `article`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `commande`
--
ALTER TABLE `commande`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `compte`
--
ALTER TABLE `compte`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `ligne`
--
ALTER TABLE `ligne`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_ARTICLE` (`ID_ARTICLE`),
  ADD KEY `ID_COMMANDE` (`ID_COMMANDE`);

--
-- Index pour la table `statut`
--
ALTER TABLE `statut`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `tournee`
--
ALTER TABLE `tournee`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `article`
--
ALTER TABLE `article`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT pour la table `client`
--
ALTER TABLE `client`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `commande`
--
ALTER TABLE `commande`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT pour la table `compte`
--
ALTER TABLE `compte`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `ligne`
--
ALTER TABLE `ligne`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT pour la table `statut`
--
ALTER TABLE `statut`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT pour la table `tournee`
--
ALTER TABLE `tournee`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
