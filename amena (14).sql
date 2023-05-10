-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 11 mai 2023 à 01:08
-- Version du serveur : 10.4.27-MariaDB
-- Version de PHP : 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `amena`
--

-- --------------------------------------------------------

--
-- Structure de la table `annonces`
--

CREATE TABLE `annonces` (
  `id_annonce` int(20) NOT NULL,
  `type` varchar(255) NOT NULL,
  `ville_dep` varchar(255) NOT NULL,
  `ville_arr` varchar(255) NOT NULL,
  `date_dep` varchar(255) NOT NULL,
  `date_arr` varchar(255) NOT NULL,
  `prix` int(20) NOT NULL,
  `description` varchar(255) NOT NULL,
  `ida_U` int(11) NOT NULL,
  `id_colis_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `annonces`
--

INSERT INTO `annonces` (`id_annonce`, `type`, `ville_dep`, `ville_arr`, `date_dep`, `date_arr`, `prix`, `description`, `ida_U`, `id_colis_id`) VALUES
(79, 'demande', 'Béja', 'Jendouba', '2002-01-12', '2002-12-12', 50, 'lkhiuguyftifdèirfièf', 85, 116),
(81, 'tt-t²', 'kbkj', 'hgii', 'knlkn', 'HH', 0, 'KKG', 72, 120),
(82, 'express', 'ariana', 'tunis', '2020-02-12', '2023-012-02', 0, 'express ', 72, 120);

-- --------------------------------------------------------

--
-- Structure de la table `appointment`
--

CREATE TABLE `appointment` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `colis`
--

CREATE TABLE `colis` (
  `id` int(11) NOT NULL,
  `id_u` int(11) NOT NULL,
  `nomExpediteur` varchar(50) NOT NULL,
  `adresseExpediteur` varchar(50) NOT NULL,
  `nomDestinataire` varchar(50) NOT NULL,
  `adresseDestinataire` varchar(50) NOT NULL,
  `poids` double NOT NULL,
  `statut` varchar(30) NOT NULL,
  `dateExpedition` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `colis`
--

INSERT INTO `colis` (`id`, `id_u`, `nomExpediteur`, `adresseExpediteur`, `nomDestinataire`, `adresseDestinataire`, `poids`, `statut`, `dateExpedition`) VALUES
(120, 72, 'a', 'a', 'a', 'a', 5, 'en attente', '2023-05-10');

-- --------------------------------------------------------

--
-- Structure de la table `colis_rec`
--

CREATE TABLE `colis_rec` (
  `id` int(11) NOT NULL,
  `id_c_id` int(11) DEFAULT NULL,
  `id_u_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `competition`
--

CREATE TABLE `competition` (
  `id` int(30) NOT NULL,
  `title` varchar(100) NOT NULL,
  `date_deb` date NOT NULL,
  `date_fin` date NOT NULL,
  `type` int(20) NOT NULL,
  `nbp` int(11) NOT NULL,
  `id_uc` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `competition`
--

INSERT INTO `competition` (`id`, `title`, `date_deb`, `date_fin`, `type`, `nbp`, `id_uc`) VALUES
(3, 'Quizzz', '2023-05-10', '2023-05-10', 2, 0, 161);

-- --------------------------------------------------------

--
-- Structure de la table `competition_end_notification`
--

CREATE TABLE `competition_end_notification` (
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `competition_user`
--

CREATE TABLE `competition_user` (
  `competition_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `doctrine_migration_versions`
--

CREATE TABLE `doctrine_migration_versions` (
  `version` varchar(191) NOT NULL,
  `executed_at` datetime DEFAULT NULL,
  `execution_time` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `doctrine_migration_versions`
--

INSERT INTO `doctrine_migration_versions` (`version`, `executed_at`, `execution_time`) VALUES
('DoctrineMigrations\\Version20230406235446', '2023-04-07 01:55:15', 8191);

-- --------------------------------------------------------

--
-- Structure de la table `documentexpedition`
--

CREATE TABLE `documentexpedition` (
  `id` int(11) NOT NULL,
  `dateSignature` date NOT NULL,
  `colis_id` int(11) NOT NULL,
  `statut` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Déchargement des données de la table `documentexpedition`
--

INSERT INTO `documentexpedition` (`id`, `dateSignature`, `colis_id`, `statut`, `description`) VALUES
(58, '2023-05-04', 112, 'non-signe', 'qsdsqd');

-- --------------------------------------------------------

--
-- Structure de la table `evaluation`
--

CREATE TABLE `evaluation` (
  `id` int(11) NOT NULL,
  `note` int(11) NOT NULL,
  `idTransporteur` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `gifts`
--

CREATE TABLE `gifts` (
  `id` int(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` varchar(100) NOT NULL,
  `value` varchar(100) NOT NULL,
  `id_c_id` int(11) NOT NULL,
  `photo` varchar(255) NOT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `gifts`
--

INSERT INTO `gifts` (`id`, `name`, `description`, `value`, `id_c_id`, `photo`, `user_id`) VALUES
(1, 'Smart Watch', 'mibro-smart-watch-watch-c2-white-740003', '1500', 11, 'http://localhost/img/6c52a5d7b6b6d567aa6c35935eeee3d9.jpg', 48),
(2, 'kaleya', 'tefal-set-deux-poeles-28cm-24-cm-extra-cook-clean-antiadhesive-490892', '500', 11, 'http://localhost/img/18b4c791cfa75c16a8b9f0b9a672d8c0.jpg', 48);

-- --------------------------------------------------------

--
-- Structure de la table `message`
--

CREATE TABLE `message` (
  `id` int(11) NOT NULL,
  `content` varchar(255) NOT NULL,
  `timestamp` datetime NOT NULL,
  `receiverId` int(11) NOT NULL,
  `senderId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `message`
--

INSERT INTO `message` (`id`, `content`, `timestamp`, `receiverId`, `senderId`) VALUES
(115, 'sqsd\n', '2023-05-10 00:00:00', 73, 72),
(116, 'dd', '2023-05-10 00:00:00', 74, 72);

-- --------------------------------------------------------

--
-- Structure de la table `messenger_messages`
--

CREATE TABLE `messenger_messages` (
  `id` bigint(20) NOT NULL,
  `body` longtext NOT NULL,
  `headers` longtext NOT NULL,
  `queue_name` varchar(190) NOT NULL,
  `created_at` datetime NOT NULL,
  `available_at` datetime NOT NULL,
  `delivered_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `quizquestion`
--

CREATE TABLE `quizquestion` (
  `id` int(11) NOT NULL,
  `question` varchar(255) NOT NULL,
  `answer1` varchar(255) NOT NULL,
  `answer2` varchar(255) NOT NULL,
  `answer3` varchar(255) NOT NULL,
  `correct_answer` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `reclamations`
--

CREATE TABLE `reclamations` (
  `id` int(11) NOT NULL,
  `idC` int(11) NOT NULL,
  `objet` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

CREATE TABLE `reservation` (
  `idRes` int(11) NOT NULL,
  `date_deb` date NOT NULL,
  `date_fin` date NOT NULL,
  `somme` float NOT NULL,
  `etat` varchar(30) NOT NULL,
  `idV` int(11) NOT NULL,
  `id_trans_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `reservation`
--

INSERT INTO `reservation` (`idRes`, `date_deb`, `date_fin`, `somme`, `etat`, `idV`, `id_trans_id`) VALUES
(115, '2023-04-27', '2023-04-27', 200, 'En attente', 66, 23),
(116, '2023-04-27', '2023-04-29', 300, 'En retard', 66, 23),
(117, '2023-04-27', '2023-05-04', 800, 'Terminée', 66, 23),
(118, '2023-05-01', '2023-05-05', 500, 'En retard', 66, 23),
(119, '2023-05-30', '2023-06-08', 1000, 'Confirmée', 66, 23),
(120, '2023-05-03', '2023-05-06', 400, 'Terminée', 66, 23),
(121, '2023-05-06', '2023-06-10', 3600, 'Terminée', 66, 23),
(123, '2023-04-22', '2023-05-02', 800, 'En retard', 66, 23),
(124, '2023-05-11', '2023-06-01', 2640, 'Confirmée', 67, 23),
(125, '2023-05-11', '2023-05-25', 300, 'Annulée', 69, 23),
(126, '2023-05-06', '2023-05-11', 120, 'Annulée', 69, 48),
(127, '2023-05-06', '2023-05-20', 1500, 'Confirmée', 66, 48),
(128, '2023-05-06', '2023-05-12', 3500, 'Confirmée', 71, 26),
(129, '2023-05-27', '2023-05-31', 2500, 'En attente', 71, 26),
(130, '2023-05-12', '2023-05-14', 1000, 'En attente', 71, NULL),
(131, '2023-05-11', '2023-05-12', 45, 'En attente', 70, 107),
(132, '2023-06-10', '2023-06-13', 60, 'En attente', 69, 110);

-- --------------------------------------------------------

--
-- Structure de la table `reset_password_request`
--

CREATE TABLE `reset_password_request` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `selector` varchar(20) NOT NULL,
  `hashed_token` varchar(100) NOT NULL,
  `requested_at` datetime NOT NULL COMMENT '(DC2Type:datetime_immutable)',
  `expires_at` datetime NOT NULL COMMENT '(DC2Type:datetime_immutable)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `email` varchar(180) NOT NULL,
  `roles` longtext DEFAULT NULL COMMENT '(DC2Type:json)',
  `password` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `adress` varchar(255) DEFAULT NULL,
  `cin` varchar(255) DEFAULT NULL,
  `date_naissance` date DEFAULT NULL,
  `date_creation_c` date DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `score` varchar(255) DEFAULT NULL,
  `numtel` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `compte_ex` date DEFAULT NULL,
  `token_ex` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `email`, `roles`, `password`, `nom`, `prenom`, `adress`, `cin`, `date_naissance`, `date_creation_c`, `status`, `token`, `score`, `numtel`, `image`, `compte_ex`, `token_ex`) VALUES
(72, 'aymenzouaoui97@gmail.com', '', 'GVE/3J2k+3KkoF62aRdUjTyQ/5TVQZ4fI2PuqJ3+4d0=', 'dev', 'aymen', 'aaaaa', '07471255', '2023-05-26', '2023-05-08', 0, 'Jg1YeW4cjPx3AO+yvF9Bp4fj4YYcolhNYUmta5YICcE=', '00', '+21695398941', 'http://localhost/img/useravatar.jpg', '2023-05-08', '2023-05-08'),
(73, 'aymen.zouavoui97@gmail.com', '[\"ROLE_TRANSPORTEUR\"]', 'GVE/3J2k+3KkoF62aRdUjTyQ/5TVQZ4fI2PuqJ3+4d0=', 'dev', 'aymen', '545646zdazd', '07471255', '2023-05-16', '2023-05-08', 0, 'mqGjpvz9JegZhW5gCkBHsX1yI3Tti9LT66m4Ht79yW0=', NULL, '+21695398941', 'http://localhost/img/1a8417446cd15bede20b4ca10e4a2b95.png', '2023-05-08', '2023-05-08'),
(74, 'bjadja@gmail.com', '', 'Password1', 'mohamed', 'hsssan', 'tuniskk', '07471255', '2023-05-08', '2023-05-08', 0, 'y5owr2AH5w5lCU76qBfRXnwNWgnMSNsuWg8oM5vN0zQ=', '00', '21852963', 'http://localhost/img/useravatar.jpg', '2023-05-08', '2023-05-08'),
(75, 'arinaarian', '[]', 'Catvcatv11', '07471255', 'omar@gmail.com', 'omar', 'jbhihih', '2023-05-08', '2023-05-08', NULL, 'XoCfwoDe2l5MP25DY7XnJ/W7DwQ0+3Q9ekcr3SdxOMY=', '00', '+21695398941', 'http://localhost/img/useravatar.jpg', '2023-05-08', '2023-05-08'),
(81, 'arina arian', '[]', 'Catvcatv11', '07471255', 'aymen@gmail.com', 'zoauoui', 'aymen', '2023-05-08', '2023-05-08', NULL, 'JU0XzziyMJfw33iwQrfPOFhfncpyocxpTPbtRsQ6rLM=', '00', '+21695398941', 'http://localhost/img/useravatar.jpg', '2023-05-08', '2023-05-08'),
(82, 'arina manouba', '[]', 'Catvcatv11', '07471255', 'aymen12@gmail.com', 'bargawi', 'mohamed', '2023-05-08', '2023-05-08', NULL, '3XqHyuDSGCMIpEEOh+CW5kNSayZvhNRLlrvV9VRLUCU=', '00', '+21695398941', 'http://localhost/img/useravatar.jpg', '2023-05-08', '2023-05-08'),
(83, 'email', '[]', 'passoword', 'nom', 'prenom', 'numtell', 'cin', '2023-05-08', '2023-05-08', NULL, '3Q5U6h8lL26c1A+koVLVDijJWSRCHhZeRkZry81gDhg=', '00', 'adress', 'http://localhost/img/useravatar.jpg', '2023-05-08', '2023-05-08'),
(84, 'aymen.zoauoui@esprit.tn', '[]', 'Catvcatv11*', 'zouaoui', 'aymen', '+21695398941', '0404047', '2023-05-09', '2023-05-09', NULL, 'aMoiga5FImyGZTirHwBdRy2FE61Zhmu3aI/59g8sSms=', '00', 'sokra sokra', 'http://localhost/img/useravatar.jpg', '2023-05-09', '2023-05-09'),
(85, 'aymenzouaoui971@gmail.com', '[\"ROLE_TRANSPORTEUR\"]', 'GVE/3J2k+3KkoF62aRdUjTyQ/5TVQZ4fI2PuqJ3+4d0=', 'Zouaoui', 'Aymen', 'mhamdia arina', '07471255', '2023-05-25', '2023-05-09', 0, 'y0ny7uQQ0JBANMAIMyoJcyDxWFikWXs1WnaTu676Gys=', '00', '+21695398941', 'http://localhost/img/useravatar.jpg', '2023-05-09', '2023-05-09'),
(87, 'bjadeeja@gmail.com', '[]', 'GVE/3J2k+3KkoF62aRdUjTyQ/5TVQZ4fI2PuqJ3+4d0=', 'mohamed', 'hsssan', 'tunisiaaa', '11125874', '2022-02-01', '2023-05-10', NULL, 'sdg8Nvwvosf1BXh7sIwVfQnQdozUwK23GpxVElP0lqM=', '00', '55555', 'http://localhost/img/useravatar.jpg', '2023-05-10', '2023-05-10'),
(88, 'aaa', '[]', 'pmWkWSBCL51Bfkhn79xPuKBKHz//H6B+mY6G9/eieuM=', 'aaa', 'aaa', '111', 'aaa', '2023-05-09', '2023-05-10', NULL, 'dkimkLXzCpKWKF1ir+3nYjUD2fGDCI6aD67VXgp7ZEU=', '00', 'aaaa', 'http://localhost/img/useravatar.jpg', '2023-05-10', '2023-05-10'),
(90, 'aaahihi', '[]', 'NJDrAOAB8gwjOoAtVVnwoHnid/iBsmBzsNcMkboM0ro=', 'aaasqdd', 'aaabjhgh', '111jo', 'aaaihihihi', '2023-05-09', '2023-05-10', NULL, 'MbFBxDdHR+hzcuLVOSZFODOh++GGOwe4IMi2g9OUn4I=', '00', 'aaaahhoj', 'http://localhost/img/useravatar.jpg', '2023-05-10', '2023-05-10'),
(92, 'aaahihijjj', '[]', 'NJDrAOAB8gwjOoAtVVnwoHnid/iBsmBzsNcMkboM0ro=', 'aaasqddbkb', 'aaabjhghjbjh', '111jo', 'aaaihihihijbvjb', '2023-05-09', '2023-05-10', NULL, 'dfRR6XEDPv2iVYW1PDEPXCpDYLuRK9cnEwUX/d8rMCE=', '00', 'aaaahhoj', 'http://localhost/img/useravatar.jpg', '2023-05-10', '2023-05-10'),
(93, 'emassil@gamil.com', '[]', '10/w7o2juYBrGMh32/KbveULW9jk2tejpyUAD+uC6PE=', 'nom', 'prenom', 'numtel555', 'cin', '2023-05-09', '2023-05-10', NULL, 'MhgLwHEEe9u+2aSMBKPHkXJNxX9QSE5N9f8SZrOdmrI=', '00', 'adress', 'http://localhost/img/useravatar.jpg', '2023-05-10', '2023-05-10'),
(94, 'IHIHI', '[]', 'DkDIvgv8ItFjwNjStAA8+V8BnyyuPPm9tus5CLiWzcM=', 'JJK', 'IIJHI', 'HIHIHI', 'HIIHI', '2023-05-10', '2023-05-10', NULL, 'AdVLvRwZmO1LHxa1JxguiB0viewnGAKdKHY/2ncdjHU=', '00', 'HIHIHI', 'http://localhost/img/useravatar.jpg', '2023-05-10', '2023-05-10'),
(95, 'IHIHIKIJIJJOJO', '[]', 'ADXop9GIi9qkro1Sck3FtzexMxDJLEdlePVnqrg/2Wk=', 'JJKKNKN', 'IIJHIJOJJ', 'HIHIHIJOJO', 'HIIHIOJJOJ', '2023-05-10', '2023-05-10', NULL, 'z2W/4YYRCwImF4oa/pXd3L0FoPzCIE3FtFSFyWcFUJ8=', '00', 'HIHIHI', 'http://localhost/img/useravatar.jpg', '2023-05-10', '2023-05-10'),
(97, 'IHIHIKIJIJJOJOIHHU', '[]', 'ADXop9GIi9qkro1Sck3FtzexMxDJLEdlePVnqrg/2Wk=', 'JJKKNKNUHUH', 'IIJHIJOJJBUH', 'HIHIHIJOJO', 'HIIHIOJJOJBUH', '2023-12-10', '2023-05-10', NULL, 'i9aBQ87NIbT0R2vvloN8bOP2xEkxnWo4vkCxS44udJA=', '00', 'HIHIHI', 'http://localhost/img/useravatar.jpg', '2023-05-10', '2023-05-10'),
(98, 'ojojoj', '[]', 'kVZM1iDh/MNgWjE7TrXXIoOMYIJoElhWxhell+zhyqc=', 'knnkjnkin', 'ojojo', 'joj', 'jojoj', '2023-05-10', '2023-05-10', NULL, '1JY7RVpLD1TGUU6llnTxlcueXtr7+SXZ6+YowL2NkZ0=', '00', 'joojo', 'http://localhost/img/useravatar.jpg', '2023-05-10', '2023-05-10'),
(99, '07471255', '[]', 'ee21d0af', 'uhuhuhuh', 'ubbuhuh', '+21695398941', 'uhuhu', '2023-05-01', '2023-05-10', NULL, 'ZerKgcneYLud9PezFTDG+wvmQbIM6/MdKBeHdRstAbg=', '00', 'nhihjjoijooj', 'http://localhost/img/useravatar.jpg', '2023-05-10', '2023-05-10'),
(100, 'email.com', '[]', '5a4327c2', 'nom', 'prenom1', '+21695398941', '07471255', '2023-05-01', '2023-05-10', NULL, '0AWIlEW7nJFSRBFsQzF+xf+UD3NvkYS6BqvzqQnLcxk=', '00', 'nhihjjoijooj', 'http://localhost/img/useravatar.jpg', '2023-05-10', '2023-05-10'),
(101, 'ema1il.com', '[]', '5a4327c2', 'nom', 'prenom1', '+21695398941', '07471255', '2023-05-01', '2023-05-10', NULL, 'bB/Fm8riC3Jri1woYNa4IWkrLkdtrdcjL/bHqEekF/o=', '00', 'adress2', 'http://localhost/img/useravatar.jpg', '2023-05-10', '2023-05-10'),
(102, 'email1', '[]', '76afb0cf', 'nom1n', 'prenom2', 'numtel1', 'cin2', '2023-05-10', '2023-05-10', NULL, 'HTABUkuwo+OLHc6Y112hBdI7Ch6BKUmHPMrhFjpDnXE=', '00', 'adress1', 'http://localhost/img/useravatar.jpg', '2023-05-10', '2023-05-10'),
(104, 'aymenzouaoui@g.com', '[\"ROLE_TRANSPORTEUR\"]', '3969e34b', 'hhih', 'ihihii', 'uguguh', 'ihohoih', '2023-05-10', '2023-05-10', NULL, 'h5/KuO8XI4F95HmzR6vJjrwqbFzz7rDEvLKs4H8xcVU=', '00', '+21695398941', 'http://localhost/img/useravatar.jpg', '2023-05-10', '2023-05-10'),
(105, 'hadi@gmail.com', '[\"ROLE_TRANSPORTEUR\"]', '3969e34b', 'hhihih', 'hiihih', 'ljojooj', 'hihihih', '2023-05-10', '2023-05-10', NULL, 'EEGx+hdS/ca0U/Q+VE/96SZzLhLdTa3fNCi1EYrYDF4=', '00', '+21695398941', 'http://localhost/img/1a8417446cd15bede20b4ca10e4a2b95.png', '2023-05-10', '2023-05-10'),
(107, 'rayen@gmail.com', '[\"ROLE_TRANSPORTEUR\"]', '3969e34b', 'rayen', 'klai', 'tunis', '12345678', '2023-05-10', '2023-05-10', NULL, 'YscxW2lqNSNNZsYWfgKKWZ4/zw74kp8IfgzT0dff780=', '00', '12345678', 'http://localhost/img/1a8417446cd15bede20b4ca10e4a2b95.png', '2023-05-10', '2023-05-10'),
(108, 'aymenzouaoui@gmail.com', '[\"ROLE_TRANSPORTEUR\"]', '3eb4280e', 'aymen', 'zouaoui', 'ariana araina', '07471255', '2023-05-10', '2023-05-10', NULL, 'omb5q8VsRMDC+m7Toh/UkdsO2eCzsKcJLTu/KyBd7vg=', '00', '95398941', 'http://localhost/img/useravatar.jpg', '2023-05-10', '2023-05-10'),
(110, 'aymenzoauoui@gmail.com', '[\"ROLE_TRANSPORTEUR\"]', '3eb4280e', 'zouaoui', 'aymen', 'araina ', '07471255', '2023-05-10', '2023-05-10', NULL, 'jS/FOumB2zmQYoduHfu2FiZ2Om0dJhH7KrE1HIdMu6g=', '00', '+21692398941', 'http://localhost/img/useravatar.jpg', '2023-05-10', '2023-05-10'),
(111, 'aymen.zoua@gmail.com', '[\"ROLE_TRANSPORTEUR\"]', 'McnrBeDI80epA89TkX3+x5rDdpUiIyFQN0rJmuMcAMg=', 'aymen', 'zouaoui', 'arian', '07471255', '2023-05-10', '2023-05-10', NULL, 'o2HhideyvFdyhjJ5vh3tJy1AK2PS23W4LmtrbasIBBg=', '00', '+21695398941', 'http://localhost/img/useravatar.jpg', '2023-05-10', '2023-05-10'),
(112, 'aymenaymen@gmail.com', '[\"ROLE_TRANSPORTEUR\"]', 'GVE/3J2k+3KkoF62aRdUjTyQ/5TVQZ4fI2PuqJ3+4d0=', 'aymen', 'zaza', 'arianaraina', '07471255', '2023-05-10', '2023-05-10', NULL, '5BDHKD8t0z2BmSLghuYrAwcAPuZFD/56pBCc6Uw9Jko=', '00', '+21695398941', 'http://localhost/img/useravatar.jpg', '2023-05-10', '2023-05-10'),
(113, 'omara@esprit.tn', 'client', 'GVE/3J2k+3KkoF62aRdUjTyQ/5TVQZ4fI2PuqJ3+4d0=', 'omar', 'aa', 'Password1', '07471255', '2023-05-09', '2023-05-10', 1, '6489c698-13a6-41e9-a7f6-aefcf2b680e2', '0', '+21695398941', 'http://localhost/img/useravatar.jpg', '2023-05-10', '2023-05-10');

-- --------------------------------------------------------

--
-- Structure de la table `validation`
--

CREATE TABLE `validation` (
  `id` int(11) NOT NULL,
  `idu` int(11) NOT NULL,
  `imageA` varchar(255) NOT NULL,
  `imageB` varchar(255) NOT NULL,
  `valide` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `vehicule`
--

CREATE TABLE `vehicule` (
  `id` int(11) NOT NULL,
  `type` varchar(30) NOT NULL,
  `immat` varchar(30) NOT NULL,
  `etat` tinyint(1) NOT NULL,
  `kilometrage` varchar(30) NOT NULL,
  `chevaux` int(11) NOT NULL,
  `marque` varchar(30) NOT NULL,
  `modele` varchar(30) NOT NULL,
  `lpec` varchar(15) NOT NULL,
  `prix` float NOT NULL,
  `img` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `vehicule`
--

INSERT INTO `vehicule` (`id`, `type`, `immat`, `etat`, `kilometrage`, `chevaux`, `marque`, `modele`, `lpec`, `prix`, `img`) VALUES
(66, 'Voiture', '110 tun 120', 0, '1', 1, 'honday', 'g3', 'rouge', 100, 'http://localhost/img/bbdc89d97d02b7ba641a5bf7d6422134.jpg'),
(67, 'Camion', '178 tun 7578', 1, '100', 5, 'om', '540', 'Tunis', 120, 'http://localhost/img/5706d0b74e6784918e2cff66781a8ef1.jpg'),
(69, 'Voiture', 'zdaaze', 0, '20', 20, 'zdaazrare', 'azrzrazr', 'azerazr', 20, 'http://localhost/img/b0e8fab97dad6c81b888768977aabc9a.jpg'),
(70, 'Voiture', '110 tun 125', 0, '1', 8, 'pgt', 'a500', 'tunis', 45, 'http://localhost/img/ecdbb88c9bd8a7bebf5a7caa21c4a101.jpg'),
(71, 'Voiture', '110 tun 255', 1, '1', 5, 'bmw', 'a', 'tunis', 500, 'http://localhost/img/1b3d99b9124e3528bdbd8a77ab86e073.jpg');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `annonces`
--
ALTER TABLE `annonces`
  ADD PRIMARY KEY (`id_annonce`),
  ADD KEY `c_idua` (`ida_U`),
  ADD KEY `c_idcolis` (`id_colis_id`);

--
-- Index pour la table `appointment`
--
ALTER TABLE `appointment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_FE38F844A76ED395` (`user_id`);

--
-- Index pour la table `colis`
--
ALTER TABLE `colis`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_470BDFF935F8C041` (`id_u`);

--
-- Index pour la table `colis_rec`
--
ALTER TABLE `colis_rec`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `competition`
--
ALTER TABLE `competition`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `competition_end_notification`
--
ALTER TABLE `competition_end_notification`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `competition_user`
--
ALTER TABLE `competition_user`
  ADD PRIMARY KEY (`competition_id`,`user_id`),
  ADD KEY `IDX_83D0485B7B39D312` (`competition_id`),
  ADD KEY `IDX_83D0485BA76ED395` (`user_id`);

--
-- Index pour la table `doctrine_migration_versions`
--
ALTER TABLE `doctrine_migration_versions`
  ADD PRIMARY KEY (`version`);

--
-- Index pour la table `documentexpedition`
--
ALTER TABLE `documentexpedition`
  ADD PRIMARY KEY (`id`),
  ADD KEY `colis_id` (`colis_id`);

--
-- Index pour la table `evaluation`
--
ALTER TABLE `evaluation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_1323A575FCC8ED84` (`idTransporteur`);

--
-- Index pour la table `gifts`
--
ALTER TABLE `gifts`
  ADD PRIMARY KEY (`id`),
  ADD KEY `Fk_CG` (`id_c_id`);

--
-- Index pour la table `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_B6BD307FE9A89CDD` (`receiverId`),
  ADD KEY `IDX_B6BD307FF0D67FFD` (`senderId`);

--
-- Index pour la table `messenger_messages`
--
ALTER TABLE `messenger_messages`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_75EA56E0FB7336F0` (`queue_name`),
  ADD KEY `IDX_75EA56E0E3BD61CE` (`available_at`),
  ADD KEY `IDX_75EA56E016BA31DB` (`delivered_at`);

--
-- Index pour la table `quizquestion`
--
ALTER TABLE `quizquestion`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `reclamations`
--
ALTER TABLE `reclamations`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_r` (`idC`);

--
-- Index pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`idRes`),
  ADD KEY `FK_42C849553BDE73DF` (`idV`),
  ADD KEY `FK_42C8495563346B17` (`id_trans_id`);

--
-- Index pour la table `reset_password_request`
--
ALTER TABLE `reset_password_request`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_7CE748AA76ED395` (`user_id`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UNIQ_8D93D649E7927C74` (`email`);

--
-- Index pour la table `validation`
--
ALTER TABLE `validation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_16AC5B6E99B902AD` (`idu`);

--
-- Index pour la table `vehicule`
--
ALTER TABLE `vehicule`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `annonces`
--
ALTER TABLE `annonces`
  MODIFY `id_annonce` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=83;

--
-- AUTO_INCREMENT pour la table `appointment`
--
ALTER TABLE `appointment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `colis`
--
ALTER TABLE `colis`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=122;

--
-- AUTO_INCREMENT pour la table `colis_rec`
--
ALTER TABLE `colis_rec`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `competition`
--
ALTER TABLE `competition`
  MODIFY `id` int(30) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `competition_end_notification`
--
ALTER TABLE `competition_end_notification`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `competition_user`
--
ALTER TABLE `competition_user`
  MODIFY `competition_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `documentexpedition`
--
ALTER TABLE `documentexpedition`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=59;

--
-- AUTO_INCREMENT pour la table `evaluation`
--
ALTER TABLE `evaluation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT pour la table `gifts`
--
ALTER TABLE `gifts`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `message`
--
ALTER TABLE `message`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=117;

--
-- AUTO_INCREMENT pour la table `messenger_messages`
--
ALTER TABLE `messenger_messages`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `quizquestion`
--
ALTER TABLE `quizquestion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `reclamations`
--
ALTER TABLE `reclamations`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT pour la table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `idRes` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=133;

--
-- AUTO_INCREMENT pour la table `reset_password_request`
--
ALTER TABLE `reset_password_request`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=114;

--
-- AUTO_INCREMENT pour la table `validation`
--
ALTER TABLE `validation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `vehicule`
--
ALTER TABLE `vehicule`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=72;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `appointment`
--
ALTER TABLE `appointment`
  ADD CONSTRAINT `FK_FE38F844A76ED395` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `competition_user`
--
ALTER TABLE `competition_user`
  ADD CONSTRAINT `FK_83D0485BA76ED395` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `evaluation`
--
ALTER TABLE `evaluation`
  ADD CONSTRAINT `FK_1323A575FCC8ED84` FOREIGN KEY (`idTransporteur`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `message`
--
ALTER TABLE `message`
  ADD CONSTRAINT `FK_B6BD307FE9A89CDD` FOREIGN KEY (`receiverId`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FK_B6BD307FF0D67FFD` FOREIGN KEY (`senderId`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `reclamations`
--
ALTER TABLE `reclamations`
  ADD CONSTRAINT `fk_r` FOREIGN KEY (`idC`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `reset_password_request`
--
ALTER TABLE `reset_password_request`
  ADD CONSTRAINT `FK_7CE748AA76ED395` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `validation`
--
ALTER TABLE `validation`
  ADD CONSTRAINT `FK_16AC5B6E99B902AD` FOREIGN KEY (`idu`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
