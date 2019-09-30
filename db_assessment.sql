-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 30 Sep 2019 pada 02.59
-- Versi server: 10.4.6-MariaDB
-- Versi PHP: 7.3.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_assessment`
--

DELIMITER $$
--
-- Prosedur
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `add_all_assessment` (`participant_a` VARCHAR(11), `sylabus_a` VARCHAR(11))  BEGIN
	declare id_baru VARCHAR(11);
	SELECT MAX(id+0)+1 INTO id_baru FROM tb_tr_assessment;
	IF (id_baru is null)
		THEN SET id_baru = '1';
	END IF;
	
	insert into tb_tr_assessment (id, score, participant, sylabus)
	values (id_baru, null, participant_a, sylabus_a);
    END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `add_all_assessment_detail` (`id_p` VARCHAR(11), `lesson_criteria_ad` VARCHAR(11), `assessment_ad` VARCHAR(11))  BEGIN
	insert into tb_tr_assessment_detail (id, tb_tr_assessment_detail.`date`, score, lesson_criteria, assessment)
	values (id_p, null, null, lesson_criteria_ad, assessment_ad);
    END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `assessment_score` (`id_a` VARCHAR(11), `score_a` FLOAT)  BEGIN
	update tb_tr_assessment set
		score = score_a
	where id = id_a;
    END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `participant_score_grade` (`id_p` VARCHAR(11), `score_p` VARCHAR(11))  BEGIN
	declare grade_p varchar(11);
	set grade_p = grade(score_p);
	
	update tb_tr_participant set
		grade	= grade_p,
		score	= score_p
	where id = id_p;
    END$$

--
-- Fungsi
--
CREATE DEFINER=`root`@`localhost` FUNCTION `grade` (`score_p` FLOAT) RETURNS VARCHAR(11) CHARSET latin1 BEGIN
	DECLARE grade_p	VARCHAR(11);
	
	IF (score_p < 50)
		THEN SET grade_p = 'E';
	ELSEIF (score_p < 60)
		THEN SET grade_p = 'D';
	ELSEIF (score_p < 70)
		THEN SET grade_p = 'C';
	ELSEIF (score_p < 85)
		THEN SET grade_p = 'B';
	ELSEIF (score_p <= 100)
		THEN SET grade_p = 'A';
	ELSE
		SET grade_p = 'Error';
	END IF;
	
	return (grade_p);
    END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_m_batch`
--

CREATE TABLE `tb_m_batch` (
  `id` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_m_batch`
--

INSERT INTO `tb_m_batch` (`id`) VALUES
('13'),
('14'),
('15');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_m_batch_class`
--

CREATE TABLE `tb_m_batch_class` (
  `id` varchar(11) NOT NULL,
  `batch` varchar(11) NOT NULL,
  `class` varchar(11) NOT NULL,
  `trainer` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_m_batch_class`
--

INSERT INTO `tb_m_batch_class` (`id`, `batch`, `class`, `trainer`) VALUES
('14/jv', '14', 'jv', '15009'),
('15/nt', '15', 'nt', '15000');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_m_classes`
--

CREATE TABLE `tb_m_classes` (
  `id` varchar(11) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_m_classes`
--

INSERT INTO `tb_m_classes` (`id`, `name`) VALUES
('jv', 'Java'),
('nt', '.Net');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_m_criteria`
--

CREATE TABLE `tb_m_criteria` (
  `id` varchar(11) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_m_criteria`
--

INSERT INTO `tb_m_criteria` (`id`, `name`) VALUES
('1', 'Discipline'),
('10', 'Attitude'),
('2', 'Hospitality'),
('3', 'Understanding of the Lesson'),
('4', 'Interaction with Audience'),
('5', 'Presentation Technique'),
('6', 'Communication with Coleague'),
('7', 'Activity in Class'),
('8', 'Reacting to Problems'),
('9', 'Problem Solving Skill');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_m_employee`
--

CREATE TABLE `tb_m_employee` (
  `id` varchar(11) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `birth_place` varchar(50) NOT NULL,
  `birth_date` date NOT NULL,
  `gender` enum('MALE','FEMALE') NOT NULL,
  `nationality` varchar(50) NOT NULL,
  `photo` varchar(100) DEFAULT NULL,
  `is_delete` tinyint(1) NOT NULL,
  `email` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_m_employee`
--

INSERT INTO `tb_m_employee` (`id`, `first_name`, `last_name`, `birth_place`, `birth_date`, `gender`, `nationality`, `photo`, `is_delete`, `email`) VALUES
('1', 'admin', 'admin', 'admin', '2019-09-23', 'FEMALE', 'WNI', NULL, 0, 'mii.bootcamp29@gmail.com'),
('15000', 'Armanda', 'Dimass', 'Jakarta', '2019-09-12', 'MALE', 'WNI', '', 0, 'a@gmail.com'),
('15002', 'Yosua', 'Eden', 'Jakarta', '2019-09-11', 'MALE', 'WNI', '', 0, 'yosuak24@gmail.com'),
('15003', 'Ezra', 'Inti', 'Jakarta', '2019-09-17', 'MALE', 'WNI', '', 0, 'ezz@gmail.com'),
('15004', 'Khrisna', 'Dewa', 'Jakarta', '2019-09-02', 'MALE', 'WNA', '', 0, 'haha@gmail.com'),
('15005', 'Wahyu', 'Kuncoro', 'Jakarta', '2019-09-25', 'MALE', 'WNI', '', 0, 'wahyukncro@gmail.com'),
('15006', 'Affan', 'The Love', 'Jakarta', '2019-09-05', 'MALE', 'WNI', '', 0, 'axffan@gmail.com'),
('15007', 'Wirya', 'Gokil', 'Jakarta', '2019-09-11', 'MALE', 'WNI', '', 0, 'wehijin@gmail.com'),
('15008', 'Rheza', 'Andhika', 'Jakarta', '2019-09-17', 'MALE', 'WNI', '', 0, 'saputrareza979@gmail.com'),
('15009', 'Mus', 'Tofa', 'Jakarta', '2019-09-09', 'MALE', 'WNI', '', 0, 'mustofaalisahid@gmail.com'),
('15010', 'Dave', 'Erliando', 'Jakarta', '2019-09-16', 'MALE', 'WNI', '', 0, 'dav3rliando@gmail.com');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_m_lesson`
--

CREATE TABLE `tb_m_lesson` (
  `id` varchar(11) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_m_lesson`
--

INSERT INTO `tb_m_lesson` (`id`, `name`) VALUES
('db', 'Database'),
('er', 'ERD'),
('js', 'JSP'),
('lp', 'Looping'),
('ne1', '.Net 1'),
('ne2', '.Net 2'),
('ne3', '.Net 3'),
('ne4', '.Net 4'),
('sd', 'SDLC'),
('se', 'Servlet');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_m_lesson_criteria`
--

CREATE TABLE `tb_m_lesson_criteria` (
  `id` varchar(11) NOT NULL,
  `percentage` float NOT NULL,
  `sylabus` varchar(11) NOT NULL,
  `criteria` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_m_lesson_criteria`
--

INSERT INTO `tb_m_lesson_criteria` (`id`, `percentage`, `sylabus`, `criteria`) VALUES
('1', 50, '1', '1'),
('10', 50, '5', '2'),
('11', 50, '6', '3'),
('12', 50, '6', '4'),
('13', 50, '7', '7'),
('14', 50, '7', '9'),
('15', 50, '8', '10'),
('16', 50, '8', '3'),
('17', 50, '9', '7'),
('18', 50, '9', '6'),
('19', 50, '10', '5'),
('2', 50, '1', '3'),
('20', 50, '10', '2'),
('21', 50, '11', '4'),
('22', 50, '11', '5'),
('23', 50, '12', '7'),
('24', 50, '12', '9'),
('25', 50, '13', '10'),
('26', 50, '13', '4'),
('27', 50, '14', '2'),
('28', 50, '14', '5'),
('3', 50, '2', '2'),
('4', 50, '2', '4'),
('5', 50, '3', '6'),
('6', 50, '3', '7'),
('7', 50, '4', '9'),
('8', 50, '4', '10'),
('9', 50, '5', '1');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_m_role`
--

CREATE TABLE `tb_m_role` (
  `id` varchar(5) NOT NULL,
  `name` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_m_role`
--

INSERT INTO `tb_m_role` (`id`, `name`) VALUES
('1', 'Admin'),
('2', 'Trainer'),
('3', 'Participant'),
('4', 'Employee');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_m_status`
--

CREATE TABLE `tb_m_status` (
  `id` varchar(11) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_m_status`
--

INSERT INTO `tb_m_status` (`id`, `name`) VALUES
('-1', 'Not Verified'),
('0', 'Verified'),
('1', 'Failed 1'),
('2', 'Failed 2'),
('3', 'Locked');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_m_sylabus`
--

CREATE TABLE `tb_m_sylabus` (
  `id` varchar(11) NOT NULL,
  `percentage` float NOT NULL,
  `lesson` varchar(11) NOT NULL,
  `class` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_m_sylabus`
--

INSERT INTO `tb_m_sylabus` (`id`, `percentage`, `lesson`, `class`) VALUES
('1', 15, 'db', 'jv'),
('10', 15, 'sd', 'nt'),
('11', 10, 'ne1', 'nt'),
('12', 10, 'ne2', 'nt'),
('13', 10, 'ne3', 'nt'),
('14', 10, 'ne4', 'nt'),
('2', 10, 'lp', 'jv'),
('3', 20, 'er', 'jv'),
('4', 15, 'sd', 'jv'),
('5', 20, 'js', 'jv'),
('6', 20, 'se', 'jv'),
('7', 15, 'db', 'nt'),
('8', 10, 'lp', 'nt'),
('9', 20, 'er', 'nt');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_tr_account`
--

CREATE TABLE `tb_tr_account` (
  `id` varchar(11) NOT NULL,
  `password` varchar(100) NOT NULL,
  `token` varchar(100) DEFAULT NULL,
  `status` varchar(11) NOT NULL,
  `verif_time` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_tr_account`
--

INSERT INTO `tb_tr_account` (`id`, `password`, `token`, `status`, `verif_time`) VALUES
('1', '$2a$10$WLPmOCzD1oCUiVZmH2bBSOExFd.I7EQbLu9wuQJPMrg0jf3oPpL4C', NULL, '0', '2019-09-23 05:20:55'),
('15000', '$2a$10$WLPmOCzD1oCUiVZmH2bBSOExFd.I7EQbLu9wuQJPMrg0jf3oPpL4C', NULL, '0', '2019-09-25 07:31:10'),
('15004', '$2a$10$WLPmOCzD1oCUiVZmH2bBSOExFd.I7EQbLu9wuQJPMrg0jf3oPpL4C', NULL, '0', '2019-09-25 07:20:46'),
('15009', '$2a$10$WLPmOCzD1oCUiVZmH2bBSOExFd.I7EQbLu9wuQJPMrg0jf3oPpL4C', NULL, '0', '2019-09-23 05:29:37');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_tr_assessment`
--

CREATE TABLE `tb_tr_assessment` (
  `id` varchar(11) NOT NULL,
  `score` float DEFAULT NULL,
  `participant` varchar(11) NOT NULL,
  `sylabus` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_tr_assessment`
--

INSERT INTO `tb_tr_assessment` (`id`, `score`, `participant`, `sylabus`) VALUES
('1', 85, '15002', '1'),
('10', 94.5, '15003', '4'),
('11', 75, '15003', '5'),
('12', 97, '15003', '6'),
('13', 85, '15004', '1'),
('14', 75, '15004', '2'),
('15', 85, '15004', '3'),
('16', 94.5, '15004', '4'),
('17', 75, '15004', '5'),
('18', 97, '15004', '6'),
('19', 40, '15005', '10'),
('2', 75, '15002', '2'),
('20', NULL, '15005', '11'),
('21', NULL, '15005', '12'),
('22', NULL, '15005', '13'),
('23', NULL, '15005', '14'),
('24', NULL, '15005', '7'),
('25', NULL, '15005', '8'),
('26', NULL, '15005', '9'),
('27', 42.5, '15006', '10'),
('28', NULL, '15006', '11'),
('29', NULL, '15006', '12'),
('3', 80, '15002', '3'),
('30', NULL, '15006', '13'),
('31', NULL, '15006', '14'),
('32', NULL, '15006', '7'),
('33', NULL, '15006', '8'),
('34', NULL, '15006', '9'),
('35', 45, '15007', '10'),
('36', NULL, '15007', '11'),
('37', NULL, '15007', '12'),
('38', NULL, '15007', '13'),
('39', NULL, '15007', '14'),
('4', 94.5, '15002', '4'),
('40', NULL, '15007', '7'),
('41', NULL, '15007', '8'),
('42', NULL, '15007', '9'),
('5', 75, '15002', '5'),
('6', 94.5, '15002', '6'),
('7', 82.5, '15003', '1'),
('8', 75, '15003', '2'),
('9', 82.5, '15003', '3');

--
-- Trigger `tb_tr_assessment`
--
DELIMITER $$
CREATE TRIGGER `add_detail_assessment` AFTER INSERT ON `tb_tr_assessment` FOR EACH ROW BEGIN
    
	DECLARE done INT DEFAULT FALSE;
	DECLARE criteria_p VARCHAR(11);
	/*deklarasi criteria apa aja yang ada di detail*/
	DECLARE list_criteria CURSOR FOR 
		(SELECT tb_m_lesson_criteria.`id` FROM 
		tb_m_lesson_criteria JOIN tb_tr_assessment ON (tb_m_lesson_criteria.`sylabus` = tb_tr_assessment.`sylabus`)
		WHERE tb_tr_assessment.`id` = new.id);
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
	
	open list_criteria;
	set_criteria : loop
		fetch list_criteria into criteria_p;
		IF done THEN 
			LEAVE set_criteria;
		END IF;
		call add_all_assessment_detail(Concat(criteria_p,"/",new.id), criteria_p, new.id);
	end loop set_criteria;
	close list_criteria;
    END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `participant_score` AFTER UPDATE ON `tb_tr_assessment` FOR EACH ROW BEGIN
	DECLARE total_score FLOAT;
	
	SELECT SUM(tb_tr_assessment.`score` * (tb_m_sylabus.`percentage`/100)) into total_score 
	FROM tb_tr_assessment JOIN tb_m_sylabus ON (tb_tr_assessment.`sylabus` = tb_m_sylabus.`id`)
	WHERE tb_tr_assessment.`participant` = old.participant;
	
	call participant_score_grade(old.participant, total_score);
    END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_tr_assessment_detail`
--

CREATE TABLE `tb_tr_assessment_detail` (
  `id` varchar(11) NOT NULL,
  `date` date DEFAULT NULL,
  `score` float DEFAULT NULL,
  `lesson_criteria` varchar(11) NOT NULL,
  `assessment` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_tr_assessment_detail`
--

INSERT INTO `tb_tr_assessment_detail` (`id`, `date`, `score`, `lesson_criteria`, `assessment`) VALUES
('1/1', NULL, 90, '1', '1'),
('1/13', NULL, 90, '1', '13'),
('1/7', NULL, 85, '1', '7'),
('10/11', NULL, 80, '10', '11'),
('10/17', NULL, 80, '10', '17'),
('10/5', NULL, 80, '10', '5'),
('11/12', NULL, 95, '11', '12'),
('11/18', NULL, 95, '11', '18'),
('11/6', NULL, 90, '11', '6'),
('12/12', NULL, 99, '12', '12'),
('12/18', NULL, 99, '12', '18'),
('12/6', NULL, 99, '12', '6'),
('13/24', NULL, NULL, '13', '24'),
('13/32', NULL, NULL, '13', '32'),
('13/40', NULL, NULL, '13', '40'),
('14/24', NULL, NULL, '14', '24'),
('14/32', NULL, NULL, '14', '32'),
('14/40', NULL, NULL, '14', '40'),
('15/25', NULL, NULL, '15', '25'),
('15/33', NULL, NULL, '15', '33'),
('15/41', NULL, NULL, '15', '41'),
('16/25', NULL, NULL, '16', '25'),
('16/33', NULL, NULL, '16', '33'),
('16/41', NULL, NULL, '16', '41'),
('17/26', NULL, NULL, '17', '26'),
('17/34', NULL, NULL, '17', '34'),
('17/42', NULL, NULL, '17', '42'),
('18/26', NULL, NULL, '18', '26'),
('18/34', NULL, NULL, '18', '34'),
('18/42', NULL, NULL, '18', '42'),
('19/19', NULL, 80, '19', '19'),
('19/27', NULL, 85, '19', '27'),
('19/35', NULL, 90, '19', '35'),
('2/1', NULL, 80, '2', '1'),
('2/13', NULL, 80, '2', '13'),
('2/7', NULL, 80, '2', '7'),
('20/19', NULL, NULL, '20', '19'),
('20/27', NULL, NULL, '20', '27'),
('20/35', NULL, NULL, '20', '35'),
('21/20', NULL, NULL, '21', '20'),
('21/28', NULL, NULL, '21', '28'),
('21/36', NULL, NULL, '21', '36'),
('22/20', NULL, NULL, '22', '20'),
('22/28', NULL, NULL, '22', '28'),
('22/36', NULL, NULL, '22', '36'),
('23/21', NULL, NULL, '23', '21'),
('23/29', NULL, NULL, '23', '29'),
('23/37', NULL, NULL, '23', '37'),
('24/21', NULL, NULL, '24', '21'),
('24/29', NULL, NULL, '24', '29'),
('24/37', NULL, NULL, '24', '37'),
('25/22', NULL, NULL, '25', '22'),
('25/30', NULL, NULL, '25', '30'),
('25/38', NULL, NULL, '25', '38'),
('26/22', NULL, NULL, '26', '22'),
('26/30', NULL, NULL, '26', '30'),
('26/38', NULL, NULL, '26', '38'),
('27/23', NULL, NULL, '27', '23'),
('27/31', NULL, NULL, '27', '31'),
('27/39', NULL, NULL, '27', '39'),
('28/23', NULL, NULL, '28', '23'),
('28/31', NULL, NULL, '28', '31'),
('28/39', NULL, NULL, '28', '39'),
('3/14', NULL, 80, '3', '14'),
('3/2', NULL, 80, '3', '2'),
('3/8', NULL, 80, '3', '8'),
('4/14', NULL, 70, '4', '14'),
('4/2', NULL, 70, '4', '2'),
('4/8', NULL, 70, '4', '8'),
('5/15', NULL, 80, '5', '15'),
('5/3', NULL, 80, '5', '3'),
('5/9', NULL, 80, '5', '9'),
('6/15', NULL, 90, '6', '15'),
('6/3', NULL, 80, '6', '3'),
('6/9', NULL, 85, '6', '9'),
('7/10', NULL, 99, '7', '10'),
('7/16', NULL, 99, '7', '16'),
('7/4', NULL, 99, '7', '4'),
('8/10', NULL, 90, '8', '10'),
('8/16', NULL, 90, '8', '16'),
('8/4', NULL, 90, '8', '4'),
('9/11', NULL, 70, '9', '11'),
('9/17', NULL, 70, '9', '17'),
('9/5', NULL, 70, '9', '5');

--
-- Trigger `tb_tr_assessment_detail`
--
DELIMITER $$
CREATE TRIGGER `assessment_score` AFTER UPDATE ON `tb_tr_assessment_detail` FOR EACH ROW BEGIN
	declare total_score float;
	
	SELECT SUM(tb_tr_assessment_detail.`score` * (tb_m_lesson_criteria.`percentage`/100)) into total_score
	FROM tb_m_lesson_criteria JOIN tb_tr_assessment_detail ON (tb_m_lesson_criteria.`id` = tb_tr_assessment_detail.`lesson_criteria`)
	WHERE tb_tr_assessment_detail.`assessment` = old.assessment;
	
	call assessment_score(old.assessment, total_score);
    END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_tr_employee_role`
--

CREATE TABLE `tb_tr_employee_role` (
  `id` varchar(11) NOT NULL,
  `employee` varchar(11) NOT NULL,
  `role` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_tr_employee_role`
--

INSERT INTO `tb_tr_employee_role` (`id`, `employee`, `role`) VALUES
('1', '15000', '2'),
('10', '15009', '2'),
('11', '15010', '2'),
('12', '1', '1'),
('3', '15002', '3'),
('4', '15003', '3'),
('5', '15004', '3'),
('6', '15005', '3'),
('7', '15006', '3'),
('8', '15007', '3'),
('9', '15008', '3');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_tr_participant`
--

CREATE TABLE `tb_tr_participant` (
  `id` varchar(11) NOT NULL,
  `grade` varchar(11) DEFAULT NULL,
  `score` float DEFAULT NULL,
  `batch_class` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_tr_participant`
--

INSERT INTO `tb_tr_participant` (`id`, `grade`, `score`, `batch_class`) VALUES
('15002', 'B', 84.325, '14/jv'),
('15003', 'B', 84.95, '14/jv'),
('15004', 'A', 85.825, '14/jv'),
('15005', 'E', 6, '15/nt'),
('15006', 'E', 6.375, '15/nt'),
('15007', 'E', 6.75, '15/nt');

--
-- Trigger `tb_tr_participant`
--
DELIMITER $$
CREATE TRIGGER `add_participant` AFTER INSERT ON `tb_tr_participant` FOR EACH ROW BEGIN
    
	DECLARE done INT DEFAULT FALSE;
	DECLARE sylabus_p VARCHAR(11);
	/*deklarasi sylabus apa aja yang ada di kelas*/
	DECLARE list_sylabus CURSOR FOR 
		(SELECT tb_m_sylabus.`id` FROM tb_m_sylabus 
		JOIN tb_m_batch_class ON (tb_m_sylabus.`class` = tb_m_batch_class.`class`)
		JOIN tb_tr_participant ON (tb_tr_participant.`batch_class` = tb_m_batch_class.`id`)
		WHERE tb_tr_participant.`id` = new.id);
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
	
	/*mulai looping buat insert ke assessment*/
	open list_sylabus;
	set_assessment : loop
		fetch list_sylabus into sylabus_p;
		if done then 
			leave set_assessment;
		end if;
		call add_all_assessment(new.id, sylabus_p);
	end loop set_assessment;
	close list_sylabus;
    END
$$
DELIMITER ;

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `tb_m_batch`
--
ALTER TABLE `tb_m_batch`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `tb_m_batch_class`
--
ALTER TABLE `tb_m_batch_class`
  ADD PRIMARY KEY (`id`),
  ADD KEY `batch` (`batch`),
  ADD KEY `class` (`class`),
  ADD KEY `trainer` (`trainer`),
  ADD KEY `trainer_2` (`trainer`);

--
-- Indeks untuk tabel `tb_m_classes`
--
ALTER TABLE `tb_m_classes`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `tb_m_criteria`
--
ALTER TABLE `tb_m_criteria`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `tb_m_employee`
--
ALTER TABLE `tb_m_employee`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id` (`id`),
  ADD KEY `id_2` (`id`),
  ADD KEY `id_3` (`id`);

--
-- Indeks untuk tabel `tb_m_lesson`
--
ALTER TABLE `tb_m_lesson`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `tb_m_lesson_criteria`
--
ALTER TABLE `tb_m_lesson_criteria`
  ADD PRIMARY KEY (`id`),
  ADD KEY `sylabus` (`sylabus`),
  ADD KEY `criteria` (`criteria`);

--
-- Indeks untuk tabel `tb_m_role`
--
ALTER TABLE `tb_m_role`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `tb_m_status`
--
ALTER TABLE `tb_m_status`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `tb_m_sylabus`
--
ALTER TABLE `tb_m_sylabus`
  ADD PRIMARY KEY (`id`),
  ADD KEY `lesson` (`lesson`),
  ADD KEY `class` (`class`);

--
-- Indeks untuk tabel `tb_tr_account`
--
ALTER TABLE `tb_tr_account`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id` (`id`),
  ADD KEY `id_2` (`id`),
  ADD KEY `status` (`status`);

--
-- Indeks untuk tabel `tb_tr_assessment`
--
ALTER TABLE `tb_tr_assessment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `sylabus` (`sylabus`),
  ADD KEY `participant` (`participant`);

--
-- Indeks untuk tabel `tb_tr_assessment_detail`
--
ALTER TABLE `tb_tr_assessment_detail`
  ADD PRIMARY KEY (`id`),
  ADD KEY `lesson_criteria` (`lesson_criteria`),
  ADD KEY `assessment` (`assessment`);

--
-- Indeks untuk tabel `tb_tr_employee_role`
--
ALTER TABLE `tb_tr_employee_role`
  ADD PRIMARY KEY (`id`),
  ADD KEY `employee` (`employee`),
  ADD KEY `role` (`role`);

--
-- Indeks untuk tabel `tb_tr_participant`
--
ALTER TABLE `tb_tr_participant`
  ADD PRIMARY KEY (`id`),
  ADD KEY `batch_class` (`batch_class`);

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `tb_m_batch_class`
--
ALTER TABLE `tb_m_batch_class`
  ADD CONSTRAINT `tb_m_batch_class_ibfk_1` FOREIGN KEY (`batch`) REFERENCES `tb_m_batch` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `tb_m_batch_class_ibfk_2` FOREIGN KEY (`class`) REFERENCES `tb_m_classes` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `tb_m_batch_class_ibfk_3` FOREIGN KEY (`trainer`) REFERENCES `tb_m_employee` (`id`);

--
-- Ketidakleluasaan untuk tabel `tb_m_lesson_criteria`
--
ALTER TABLE `tb_m_lesson_criteria`
  ADD CONSTRAINT `tb_m_lesson_criteria_ibfk_1` FOREIGN KEY (`sylabus`) REFERENCES `tb_m_sylabus` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `tb_m_lesson_criteria_ibfk_2` FOREIGN KEY (`criteria`) REFERENCES `tb_m_criteria` (`id`) ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `tb_m_sylabus`
--
ALTER TABLE `tb_m_sylabus`
  ADD CONSTRAINT `tb_m_sylabus_ibfk_1` FOREIGN KEY (`lesson`) REFERENCES `tb_m_lesson` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `tb_m_sylabus_ibfk_2` FOREIGN KEY (`class`) REFERENCES `tb_m_classes` (`id`) ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `tb_tr_account`
--
ALTER TABLE `tb_tr_account`
  ADD CONSTRAINT `tb_tr_account_ibfk_1` FOREIGN KEY (`id`) REFERENCES `tb_m_employee` (`id`),
  ADD CONSTRAINT `tb_tr_account_ibfk_2` FOREIGN KEY (`status`) REFERENCES `tb_m_status` (`id`) ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `tb_tr_assessment`
--
ALTER TABLE `tb_tr_assessment`
  ADD CONSTRAINT `tb_tr_assessment_ibfk_1` FOREIGN KEY (`participant`) REFERENCES `tb_tr_participant` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `tb_tr_assessment_ibfk_2` FOREIGN KEY (`sylabus`) REFERENCES `tb_m_sylabus` (`id`) ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `tb_tr_assessment_detail`
--
ALTER TABLE `tb_tr_assessment_detail`
  ADD CONSTRAINT `tb_tr_assessment_detail_ibfk_1` FOREIGN KEY (`lesson_criteria`) REFERENCES `tb_m_lesson_criteria` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `tb_tr_assessment_detail_ibfk_2` FOREIGN KEY (`assessment`) REFERENCES `tb_tr_assessment` (`id`) ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `tb_tr_employee_role`
--
ALTER TABLE `tb_tr_employee_role`
  ADD CONSTRAINT `tb_tr_employee_role_ibfk_1` FOREIGN KEY (`role`) REFERENCES `tb_m_role` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `tb_tr_employee_role_ibfk_2` FOREIGN KEY (`employee`) REFERENCES `tb_m_employee` (`id`) ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `tb_tr_participant`
--
ALTER TABLE `tb_tr_participant`
  ADD CONSTRAINT `tb_tr_participant_ibfk_1` FOREIGN KEY (`batch_class`) REFERENCES `tb_m_batch_class` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `tb_tr_participant_ibfk_2` FOREIGN KEY (`id`) REFERENCES `tb_m_employee` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
