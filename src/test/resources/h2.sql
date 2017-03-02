SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

CREATE DATABASE IF NOT EXISTS `tennis` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `tennis`;

CREATE TABLE IF NOT EXISTS `acces` (
`id_acces` int(11) unsigned NOT NULL,
  `login` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='Contient la liste de logins pouvant se connecter au logiciel';

INSERT INTO `acces` (`id_acces`, `login`, `password`) VALUES
(1, 'admin', 'admin'),
(2, 'organisateur', 'organisateur');

CREATE TABLE IF NOT EXISTS `arbitres` (
`id_arbitre` int(11) unsigned NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `sexe` char(1) NOT NULL,
  `id_pays` int(11) unsigned NOT NULL,
  `id_niveau` int(11) unsigned NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='Contient la liste des arbitres';

INSERT INTO `arbitres` (`id_arbitre`, `nom`, `prenom`, `sexe`, `id_pays`, `id_niveau`) VALUES
(1, 'Manzotti', 'Robin', 'M', 8, 3),
(2, 'Conry', 'Jean-Christophe', 'M', 7, 2),
(3, 'Antolsky', 'Arnaud', 'M', 2, 3),
(4, 'Chatellard', 'Mathilde', 'F', 7, 2),
(5, 'David', 'Laure', 'F', 1, 3),
(6, 'Neveux', 'Stéphanie', 'F', 7, 3),
(7, 'Sore', 'Guillaume', 'M', 2, 1),
(8, 'Comelli', 'Amélie', 'F', 8, 1),
(9, 'Salmon', 'Léo', 'M', 1, 2);

CREATE TABLE IF NOT EXISTS `courts` (
`id_court` int(11) unsigned NOT NULL,
  `nom` varchar(50) NOT NULL,
  `statut` enum('LIBRE','OCCUPE') NOT NULL DEFAULT 'LIBRE'
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='Liste les différents court de tennis ainsi que leur statut';

INSERT INTO `courts` (`id_court`, `nom`, `statut`) VALUES
(1, 'Philippe-Chatrier', 'LIBRE'),
(2, 'Suzanne-Lenglen', 'LIBRE'),
(3, 'Court n°1', 'OCCUPE');

CREATE TABLE IF NOT EXISTS `joueurs` (
`id_joueur` int(11) unsigned NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `sexe` char(1) NOT NULL,
  `id_pays` int(11) unsigned NOT NULL,
  `classement` int(11) unsigned NOT NULL,
  `id_qualification` int(11) unsigned NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='Liste les joueurs et joueuses participants aux tournois';

INSERT INTO `joueurs` (`id_joueur`, `nom`, `prenom`, `sexe`, `id_pays`, `classement`, `id_qualification`) VALUES
(1, 'Murray', 'Andy', 'H', 1, 11540, 2),
(2, 'Djokovic', 'Novak', 'H', 5, 9825, 2),
(3, 'Wawrinka', 'Stan', 'H', 6, 5695, 2),
(4, 'Monfils', 'Gaël', 'H', 7, 3445, 2),
(5, 'Almagro', 'Nicolas', 'H', 4, 0, 1),
(6, 'Fognini', 'Fabio', 'H', 8, 0, 3),
(7, 'Williams', 'Serena', 'F', 10, 7780, 2),
(8, 'Kerber', 'Angélique', 'F', 2, 7115, 2);

CREATE TABLE IF NOT EXISTS `matchs` (
`id_match` int(11) unsigned NOT NULL,
  `id_tournoi` int(11) unsigned NOT NULL,
  `id_court` int(11) unsigned NOT NULL,
  `id_arbitre` int(11) unsigned NOT NULL,
  `id_joueur1` int(11) unsigned NOT NULL,
  `id_joueur2` int(11) unsigned NOT NULL,
  `date` date NOT NULL,
  `heure_debut` time DEFAULT NULL,
  `heure_fin` time DEFAULT NULL,
  `statut` enum('PREPA','EN_COURS','PASSE','ANNULE') NOT NULL,
  `sets_joueur1` int(11) unsigned DEFAULT NULL,
  `sets_joueur2` int(11) unsigned DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='Contient tous les matchs et les résultats associés';

INSERT INTO `matchs` (`id_match`, `id_tournoi`, `id_court`, `id_arbitre`, `id_joueur1`, `id_joueur2`, `date`, `heure_debut`, `heure_fin`, `statut`, `sets_joueur1`, `sets_joueur2`) VALUES
(1, 1, 2, 4, 4, 6, '2017-02-08', '10:00:00', '12:12:17', 'PASSE', 3, 0),
(2, 2, 3, 3, 8, 7, '2017-02-13', '15:17:19', NULL, 'EN_COURS', NULL, NULL);

CREATE TABLE IF NOT EXISTS `niveau_arbitre` (
`id_niveau` int(11) unsigned NOT NULL,
  `nom` varchar(20) NOT NULL,
  `description` text
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

INSERT INTO `niveau_arbitre` (`id_niveau`, `nom`, `description`) VALUES
(1, 'A1', 'Débutant'),
(2, 'A2', 'Confirmé'),
(3, 'A3', 'Expert');

CREATE TABLE IF NOT EXISTS `pays` (
`id_pays` int(11) unsigned NOT NULL,
  `nom` varchar(50) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

INSERT INTO `pays` (`id_pays`, `nom`) VALUES
(2, 'Allemagne'),
(1, 'Angleterre'),
(11, 'Brésil'),
(12, 'Bulgarie'),
(13, 'Canada'),
(3, 'Chine'),
(4, 'Espagne'),
(10, 'États-Unis'),
(7, 'France'),
(14, 'Grèce'),
(18, 'Islande'),
(8, 'Italie'),
(15, 'Mexique'),
(19, 'Pérou'),
(9, 'Roumanie'),
(5, 'Serbie'),
(6, 'Suisse');

CREATE TABLE IF NOT EXISTS `tournois` (
`id_tournoi` int(11) unsigned NOT NULL,
  `nom` varchar(100) NOT NULL,
  `nbr_sets` tinyint(3) unsigned NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='Liste les différents tournois';

INSERT INTO `tournois` (`id_tournoi`, `nom`, `nbr_sets`) VALUES
(1, 'Simple Messieurs', 3),
(2, 'Simple Dames', 2),
(3, 'Simple Juniors Messieurs', 2),
(4, 'Simple Juniors Dames', 2),
(5, 'Double Messieurs', 3),
(6, 'Double Dames', 2);

CREATE TABLE IF NOT EXISTS `types_qualifications` (
`id_qualification` int(11) unsigned NOT NULL,
  `nom` varchar(100) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

INSERT INTO `types_qualifications` (`id_qualification`, `nom`) VALUES
(3, 'Épreuve de qualification'),
(2, 'Par classement ATP/WPA'),
(1, 'Wildcards');


ALTER TABLE `acces`
 ADD PRIMARY KEY (`id_acces`), ADD UNIQUE KEY `login` (`login`);

ALTER TABLE `arbitres`
 ADD PRIMARY KEY (`id_arbitre`), ADD KEY `pays_idx` (`id_pays`), ADD KEY `niveau_idx` (`id_niveau`), ADD KEY `pays_arbitre_idx` (`id_pays`), ADD KEY `niveau_arbitre_idx` (`id_niveau`);

ALTER TABLE `courts`
 ADD PRIMARY KEY (`id_court`), ADD UNIQUE KEY `nom` (`nom`);

ALTER TABLE `joueurs`
 ADD PRIMARY KEY (`id_joueur`), ADD KEY `pays_joueur_idx` (`id_pays`), ADD KEY `qualification_joueur_idx` (`id_qualification`);

ALTER TABLE `matchs`
 ADD PRIMARY KEY (`id_match`), ADD KEY `ref_tournoi_idx` (`id_tournoi`), ADD KEY `ref_court_idx` (`id_court`), ADD KEY `ref_arbitre_idx` (`id_arbitre`), ADD KEY `ref_joueur1_idx` (`id_joueur1`), ADD KEY `ref_joueur2_idx` (`id_joueur2`);

ALTER TABLE `niveau_arbitre`
 ADD PRIMARY KEY (`id_niveau`), ADD UNIQUE KEY `nom` (`nom`);

ALTER TABLE `pays`
 ADD PRIMARY KEY (`id_pays`), ADD UNIQUE KEY `nom` (`nom`);

ALTER TABLE `tournois`
 ADD PRIMARY KEY (`id_tournoi`), ADD UNIQUE KEY `nom` (`nom`);

ALTER TABLE `types_qualifications`
 ADD PRIMARY KEY (`id_qualification`), ADD UNIQUE KEY `nom` (`nom`);


ALTER TABLE `acces`
MODIFY `id_acces` int(11) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
ALTER TABLE `arbitres`
MODIFY `id_arbitre` int(11) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=10;
ALTER TABLE `courts`
MODIFY `id_court` int(11) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
ALTER TABLE `joueurs`
MODIFY `id_joueur` int(11) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=9;
ALTER TABLE `matchs`
MODIFY `id_match` int(11) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
ALTER TABLE `niveau_arbitre`
MODIFY `id_niveau` int(11) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
ALTER TABLE `pays`
MODIFY `id_pays` int(11) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=20;
ALTER TABLE `tournois`
MODIFY `id_tournoi` int(11) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
ALTER TABLE `types_qualifications`
MODIFY `id_qualification` int(11) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;

ALTER TABLE `arbitres`
ADD CONSTRAINT `niveau_arbitre` FOREIGN KEY (`id_niveau`) REFERENCES `niveau_arbitre` (`id_niveau`),
ADD CONSTRAINT `pays_arbitre` FOREIGN KEY (`id_pays`) REFERENCES `pays` (`id_pays`);

ALTER TABLE `joueurs`
ADD CONSTRAINT `pays_joueur` FOREIGN KEY (`id_pays`) REFERENCES `pays` (`id_pays`),
ADD CONSTRAINT `qualification_joueur` FOREIGN KEY (`id_qualification`) REFERENCES `types_qualifications` (`id_qualification`);

ALTER TABLE `matchs`
ADD CONSTRAINT `ref_arbitre` FOREIGN KEY (`id_arbitre`) REFERENCES `arbitres` (`id_arbitre`),
ADD CONSTRAINT `ref_court` FOREIGN KEY (`id_court`) REFERENCES `courts` (`id_court`),
ADD CONSTRAINT `ref_joueur1` FOREIGN KEY (`id_joueur1`) REFERENCES `joueurs` (`id_joueur`),
ADD CONSTRAINT `ref_joueur2` FOREIGN KEY (`id_joueur2`) REFERENCES `joueurs` (`id_joueur`),
ADD CONSTRAINT `ref_tournoi` FOREIGN KEY (`id_tournoi`) REFERENCES `tournois` (`id_tournoi`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
