-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 10, 2025 at 12:45 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pbo`
--
-- Cek apakah database sudah ada
DROP DATABASE IF EXISTS pbo;

-- Buat database baru
CREATE DATABASE pbo;

-- Gunakan database yang baru dibuat
USE pbo;
-- --------------------------------------------------------

--
-- Table structure for table `ads`
--

CREATE TABLE `ads` (
  `id` int(11) NOT NULL,
  `content` varchar(255) NOT NULL,
  `duration` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ads`
--

INSERT INTO `ads` (`id`, `content`, `duration`) VALUES
(1, 'https://goshopkey.com/wp-content/uploads/2023/05/Olatte.jpg', 15.5),
(2, 'https://www.publikasimedia.com/wp-content/uploads/2022/06/image-11-1024x683.png', 20),
(3, 'https://1.bp.blogspot.com/-GXMH1zlPZgI/XXtveKKtwdI/AAAAAAAAJVc/lx7xVlTLF6AXLdrUOL5_sfT1uVQN8gtngCLcBGAsYHQ/s1600/iklan%2Bminuman%2Bcoca%2Bcola.jpg', 10);

-- --------------------------------------------------------

--
-- Table structure for table `artist`
--

CREATE TABLE `artist` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `bio` varchar(255) NOT NULL,
  `gambar` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `artist`
--

INSERT INTO `artist` (`id`, `name`, `bio`, `gambar`) VALUES
(1, 'Bernadya', 'artis indo, indie', 'https://image-cdn-ak.spotifycdn.com/image/ab67616d00001e02cb1e5f7d0942bf9196c1e711'),
(2, 'Eminem', 'Rapper', 'https://image-cdn-ak.spotifycdn.com/image/ab67616d00001e026ca5c90113b30c3c43ffb8f4'),
(3, 'Anonymus', 'Podcast dari Indonesia', 'https://image-cdn-fa.spotifycdn.com/image/ab67656300005f1f88a1592ce7c11453e98096a4'),
(4, 'Wave to Earth', 'Korean Artist', 'https://image-cdn-ak.spotifycdn.com/image/ab67616d00001e02da5d5aeeabacacc1263c0f4b');

-- --------------------------------------------------------

--
-- Table structure for table `track`
--

CREATE TABLE `track` (
  `id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `genre` varchar(255) NOT NULL,
  `duration` double NOT NULL,
  `streamCount` int(11) NOT NULL DEFAULT 0,
  `likeCount` int(11) NOT NULL DEFAULT 0,
  `shareCount` int(11) NOT NULL DEFAULT 0,
  `artist_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `track`
--

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

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `premium` tinyint(1) DEFAULT 1,
  `price` double DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `email`, `password`, `premium`, `price`) VALUES
(1, 'prem1', 'john.doe@example.com', 'prem1', 1, 10.99),
(2, 'prem2', 'jane.smith@example.com', 'prem2', 1, 10.99),
(3, 'prem3', 'emma.brown@example.com', 'prem3', 1, 10.99),
(4, 'free1', 'alice.wonder@example.com', 'free1', 0, 0),
(5, 'free2', 'bob.martin@example.com', 'free2', 0, 0),
(6, 'free3', 'charlie.jones@example.com', 'free3', 0, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `ads`
--
ALTER TABLE `ads`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `artist`
--
ALTER TABLE `artist`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `track`
--
ALTER TABLE `track`
  ADD PRIMARY KEY (`id`),
  ADD KEY `artist_id` (`artist_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `ads`
--
ALTER TABLE `ads`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `artist`
--
ALTER TABLE `artist`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `track`
--
ALTER TABLE `track`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `track`
--
ALTER TABLE `track`
  ADD CONSTRAINT `track_ibfk_1` FOREIGN KEY (`artist_id`) REFERENCES `artist` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
