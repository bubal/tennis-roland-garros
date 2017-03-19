DROP TABLE IF EXISTS `acces`;
CREATE TABLE `acces` (
  `id_acces` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `login` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  PRIMARY KEY (`id_acces`),
  UNIQUE KEY `login` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

INSERT INTO `acces` VALUES 
(1, 'admin', 'admin'),
(2, 'organisateur', 'organisateur'),
(3, 'test', 'test');

/* ----------------------- */

DROP TABLE IF EXISTS `niveau_arbitre`;
CREATE TABLE `niveau_arbitre` (
  `id_niveau` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nom` varchar(30) NOT NULL,
  `description` varchar(50),
  PRIMARY KEY (`id_niveau`),
  UNIQUE KEY `nom_niveau` (`nom`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

INSERT INTO `niveau_arbitre` VALUES 
(1,'A1','Débutant'),
(2,'A2','Confirmé'),
(3,'A3','Expert'),
(4,'A4','Super Expert');

/* ----------------------- */

DROP TABLE IF EXISTS `types_qualifications`;
CREATE TABLE `types_qualifications` (
  `id_qualification` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  PRIMARY KEY (`id_qualification`),
  UNIQUE KEY `nom_qualification` (`nom`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

INSERT INTO `types_qualifications` VALUES
(1,'Wildcards'),
(2,'Par classement ATP/WPA'),
(3,'Épreuve de qualification'),
(4,'Par QCM');

/* ----------------------- */

DROP TABLE IF EXISTS `tournois`;
CREATE TABLE `tournois` (
  `id_tournoi` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nom` varchar(30) NOT NULL,
  `nbr_sets` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id_tournoi`),
  UNIQUE KEY `nom_tournoi` (`nom`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

INSERT INTO `tournois` VALUES 
(1,'Simple Messieurs',3),
(2,'Simple Dames',2),
(3,'Simple Juniors Messieurs',2),
(4,'Simple Juniors Dames',2),
(5,'Double Messieurs',3),
(6,'Double Dames',2),
(7,'Triple Messieurs',3);

/* ----------------------- */

DROP TABLE IF EXISTS `pays`;
CREATE TABLE `pays` (
  `id_pays` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nom` varchar(30) NOT NULL,
  PRIMARY KEY (`id_pays`),
  UNIQUE KEY `nom_pays` (`nom`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

INSERT INTO `pays` VALUES
(1,'Angleterre'),
(2,'Allemagne'),
(3,'Chine'),
(4,'Espagne'),
(5,'Serbie'),
(6,'Suisse'),
(7,'France'),
(8,'Italie'),
(9,'Roumanie'),
(10,'États-Unis'),
(11,'Brésil'),
(12,'Bulgarie'),
(13,'Canada'),
(14,'Grèce'),
(15,'Mexique'),
(16,'Islande'),
(17,'Pérou'),
(18,'Colombie');

/* ----------------------- */

DROP TABLE IF EXISTS `courts`;
CREATE TABLE `courts` (
  `id_court` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nom` varchar(30) NOT NULL,
  PRIMARY KEY (`id_court`),
  UNIQUE KEY `nom_courts` (`nom`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

INSERT INTO `courts` VALUES
(1,'Philippe-Chatrier'),
(2,'Suzanne-Lenglen'),
(3,'Court n°1'),
(4,'Court n°5');

/* ----------------------- */

DROP TABLE IF EXISTS `arbitres`;
CREATE TABLE `arbitres` (
  `id_arbitre` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nom` varchar(30) NOT NULL,
  `prenom` varchar(30) NOT NULL,
  `sexe` char(1) NOT NULL,
  `id_pays` int(11) unsigned NOT NULL,
  `id_niveau` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id_arbitre`),
  CONSTRAINT `niveau_arbitre` FOREIGN KEY (`id_niveau`) REFERENCES `niveau_arbitre` (`id_niveau`),
  CONSTRAINT `pays_arbitre` FOREIGN KEY (`id_pays`) REFERENCES `pays` (`id_pays`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

INSERT INTO `arbitres` VALUES 
(1, 'Manzotti', 'Robin', 'H', 8, 3),
(2, 'Conry', 'Jean-Christophe', 'H', 7, 2),
(3, 'Antolsky', 'Arnaud', 'H', 2, 3),
(4, 'Chatellard', 'Mathilde', 'F', 7, 2),
(5, 'David', 'Laure', 'F', 1, 3),
(6, 'Neveux', 'Stéphanie', 'F', 7, 3),
(7, 'Sore', 'Guillaume', 'H', 2, 1),
(8, 'Comelli', 'Amélie', 'F', 8, 1),
(9, 'Salmon', 'Léo', 'H', 1, 2);

/* ----------------------- */

DROP TABLE IF EXISTS `joueurs`;
CREATE TABLE `joueurs` (
  `id_joueur` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nom` varchar(30) NOT NULL,
  `prenom` varchar(30) NOT NULL,
  `sexe` char(1) NOT NULL,
  `id_pays` int(11) unsigned NOT NULL,
  `classement` int(11) unsigned NOT NULL,
  `id_qualification` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id_joueur`),
  CONSTRAINT `pays_joueur` FOREIGN KEY (`id_pays`) REFERENCES `pays` (`id_pays`),
  CONSTRAINT `qualification_joueur` FOREIGN KEY (`id_qualification`) REFERENCES `types_qualifications` (`id_qualification`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

INSERT INTO `joueurs` VALUES 
(1, 'Murray', 'Andy', 'H', 1, 11540, 2),
(2, 'Djokovic', 'Novak', 'H', 5, 9825, 2),
(3, 'Wawrinka', 'Stan', 'H', 6, 5695, 2),
(4, 'Monfils', 'Gaël', 'H', 7, 3445, 2),
(5, 'Almagro', 'Nicolas', 'H', 4, 0, 1),
(6, 'Fognini', 'Fabio', 'H', 8, 0, 3),
(7, 'Williams', 'Serena', 'F', 10, 7780, 2),
(8, 'Kerber', 'Angélique', 'F', 2, 7115, 2);

/* ----------------------- */

DROP TABLE IF EXISTS `matchs`;
CREATE TABLE `matchs` (
  `id_match` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `id_tournoi` int(11) unsigned NOT NULL,
  `id_court` int(11) unsigned NOT NULL,
  `id_arbitre` int(11) unsigned NOT NULL,
  `id_joueur1` int(11) unsigned NOT NULL,
  `id_joueur2` int(11) unsigned NOT NULL,
  `date` date NOT NULL,
  `heure_debut` timestamp DEFAULT NULL,
  `heure_fin` timestamp DEFAULT NULL,
  `sets_joueur1` int(11) unsigned NOT NULL,
  `sets_joueur2` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id_match`),
  CONSTRAINT `ref_arbitre` FOREIGN KEY (`id_arbitre`) REFERENCES `arbitres` (`id_arbitre`),
  CONSTRAINT `ref_court` FOREIGN KEY (`id_court`) REFERENCES `courts` (`id_court`),
  CONSTRAINT `ref_joueur1` FOREIGN KEY (`id_joueur1`) REFERENCES `joueurs` (`id_joueur`),
  CONSTRAINT `ref_joueur2` FOREIGN KEY (`id_joueur2`) REFERENCES `joueurs` (`id_joueur`),
  CONSTRAINT `ref_tournoi` FOREIGN KEY (`id_tournoi`) REFERENCES `tournois` (`id_tournoi`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

INSERT INTO `matchs` VALUES 
(1,1,2,4,4,6,'2017-02-08','2017-02-08 10:00:00','2017-02-08 12:12:17',3,0),
(2,1,1,4,4,1,'2017-09-02',NULL,NULL,0,0);

