-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3309
-- Generation Time: Jan 03, 2025 at 01:25 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

-- Database: `tubes`

DROP DATABASE tubes;
CREATE DATABASE tubes;
USE tubes;

-- --------------------------------------------------------

-- Table structure for table `ads`

CREATE TABLE `ads` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `content` VARCHAR(255) NOT NULL,
  `duration` DOUBLE NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table `ads`

INSERT INTO `ads` (`id`, `content`, `duration`) VALUES
(1, 'https://goshopkey.com/wp-content/uploads/2023/05/Olatte.jpg', 15.5),
(2, 'https://www.publikasimedia.com/wp-content/uploads/2022/06/image-11-1024x683.png', 20),
(3, 'https://1.bp.blogspot.com/-GXMH1zlPZgI/XXtveKKtwdI/AAAAAAAAJVc/lx7xVlTLF6AXLdrUOL5_sfT1uVQN8gtngCLcBGAsYHQ/s1600/iklan%2Bminuman%2Bcoca%2Bcola.jpg', 10);

-- --------------------------------------------------------

-- Table structure for table `artist`

CREATE TABLE `artist` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `bio` VARCHAR(255) NOT NULL,
  `gambar` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table `artist`

INSERT INTO `artist` (`id`, `name`, `bio`, `gambar`) VALUES
(1, 'Bernadya', 'artis indo, indie', 'https://image-cdn-ak.spotifycdn.com/image/ab67616d00001e02da5d5aeeabacacc1263c0f4b'),
(2, 'Eminem', 'Rapper', 'https://image-cdn-ak.spotifycdn.com/image/ab67616d00001e02cb1e5f7d0942bf9196c1e711'),
(3, 'Anonymus', 'Podcast dari Indonesia', 'https://image-cdn-ak.spotifycdn.com/image/ab67616d00001e026ca5c90113b30c3c43ffb8f4'),
(4, 'Wave to Earth', 'Korean Artist', 'https://image-cdn-fa.spotifycdn.com/image/ab67656300005f1f88a1592ce7c11453e98096a4');

-- --------------------------------------------------------

-- Table structure for table `track`

CREATE TABLE `track` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NOT NULL,
  `genre` VARCHAR(255) NOT NULL,
  `duration` DOUBLE NOT NULL,
  `streamCount` INT(11) NOT NULL DEFAULT 0,
  `likeCount` INT(11) NOT NULL DEFAULT 0,
  `shareCount` INT(11) NOT NULL DEFAULT 0,
  `artist_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`artist_id`) REFERENCES `artist`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Data untuk tabel `track`

INSERT INTO `track` (`id`, `title`, `genre`, `duration`, `streamCount`, `likeCount`, `shareCount`, `artist_id`) VALUES
(26, 'Terlintas', 'Indie', 3.55, 50, 20, 10, 1),
(27, 'Masa Sepi', 'Indie', 4.15, 20, 10, 20, 1),
(28, 'Sinyal-sinyal', 'Indie', 6.24, 30, 20, 20, 1),
(29, 'Satu Bulan', 'Indie', 4.2, 100, 20, 10, 1),
(30, 'bad', 'pop', 5.01, 20, 10, 10, 4),
(31, 'light.', 'pop', 3.5, 500, 400, 800, 4),
(32, 'love.', 'pop', 4.46, 500, 400, 700, 4),
(33, 'season', 'pop', 5.3, 50, 40, 60, 4),
(34, 'Mockingbird', 'Rap', 1.33, 40, 90, 10, 2),
(35, 'Without Me', 'Rap', 9.33, 90, 10, 20, 2),
(36, 'Godzilla', 'Rap', 6.33, 30, 50, 40, 2),
(37, 'Smack That', 'Rap', 3.33, 70, 40, 20, 2),
(38, 'Semua Urusanku', 'Podcast', 19.33, 50, 50, 10, 3),
(39, 'Lama Berlalu', 'Podcast', 28.33, 90, 30, 40, 3),
(40, 'Kemarin Tidak Nyata', 'Podcast', 31.33, 30, 60, 30, 3),
(41, 'Yang Sebentar Itu', 'Podcast', 41.33, 60, 20, 50, 3);

-- --------------------------------------------------------

-- Table structure for table `user`

CREATE TABLE `user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `premium` TINYINT(1) DEFAULT 1,
  `price` DOUBLE DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table `user`

INSERT INTO `user` (`id`, `username`, `email`, `password`, `premium`, `price`) VALUES
(1, 'prem1', 'john.doe@example.com', 'prem1', 1, 10.99),
(2, 'prem2', 'jane.smith@example.com', 'prem2', 1, 10.99),
(3, 'prem3', 'emma.brown@example.com', 'prem3', 1, 10.99),
(4, 'free1', 'alice.wonder@example.com', 'free1', 0, 0),
(5, 'free2', 'bob.martin@example.com', 'free2', 0, 0),
(6, 'free3', 'charlie.jones@example.com', 'free3', 0, 0);

-


COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
