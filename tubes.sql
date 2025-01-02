-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 02, 2025 at 07:56 AM
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
-- Database: `tubes`
--

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
-- Table structure for table `album`
--

CREATE TABLE `album` (
  `id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `release_date` date DEFAULT NULL,
  `artist_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `album`
--

INSERT INTO `album` (`id`, `title`, `release_date`, `artist_id`) VALUES
(36, 'Thriller', '1982-11-30', 1),
(37, 'Back in Black', '1980-07-25', 2),
(38, 'The Dark Side of the Moon', '1973-03-01', 3),
(39, 'Abbey Road', '1969-09-26', 4),
(40, 'Nevermind', '1991-09-24', 5);

-- --------------------------------------------------------

--
-- Table structure for table `artist`
--

CREATE TABLE `artist` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `bio` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `artist`
--

INSERT INTO `artist` (`id`, `name`, `bio`) VALUES
(1, 'Michael Jackson', 'Known as the King of Pop, Michael Jackson was a global music icon with groundbreaking albums like Thriller.'),
(2, 'AC/DC', 'An Australian rock band formed in 1973, known for high-energy performances and iconic songs like Back in Black.'),
(3, 'Pink Floyd', 'An English rock band renowned for their progressive and psychedelic music, especially the album The Dark Side of the Moon.'),
(4, 'The Beatles', 'A legendary British rock band from Liverpool that revolutionized music in the 1960s with hits like Hey Jude and Let It Be.'),
(5, 'Nirvana', 'An American rock band that popularized grunge music in the 1990s with albums like Nevermind.');

-- --------------------------------------------------------

--
-- Table structure for table `free_user`
--

CREATE TABLE `free_user` (
  `id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `premium` tinyint(1) DEFAULT 0,
  `price` double DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `free_user`
--

INSERT INTO `free_user` (`id`, `username`, `email`, `password`, `premium`, `price`) VALUES
(1, 'alice_wonder', 'alice.wonder@example.com', 'passwordAlice123', 0, 0),
(2, 'bob_martin', 'bob.martin@example.com', 'passwordBob456', 0, 0),
(3, 'charlie_jones', 'charlie.jones@example.com', 'passwordCharlie789', 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `playlist`
--

CREATE TABLE `playlist` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `playlist`
--

INSERT INTO `playlist` (`id`, `name`, `description`) VALUES
(1, 'Top Hits 2025', 'A collection of the top hits of the year 2025.'),
(2, 'Classic Rock', 'Timeless rock songs from the 70s and 80s.'),
(3, 'Relax & Unwind', 'Chill and relaxing tracks for a stress-free evening.'),
(4, 'Workout Jams', 'High-energy tracks to keep you motivated during workouts.'),
(5, 'Indie Favorites', 'Handpicked tracks from the best indie artists.');

-- --------------------------------------------------------

--
-- Table structure for table `playlist_track`
--

CREATE TABLE `playlist_track` (
  `playlist_id` int(11) NOT NULL,
  `track_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `playlist_track`
--

INSERT INTO `playlist_track` (`playlist_id`, `track_id`) VALUES
(1, 26),
(2, 27),
(3, 28),
(4, 29),
(5, 30);

-- --------------------------------------------------------

--
-- Table structure for table `premium_user`
--

CREATE TABLE `premium_user` (
  `id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `premium` tinyint(1) DEFAULT 1,
  `price` double DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `premium_user`
--

INSERT INTO `premium_user` (`id`, `username`, `email`, `password`, `premium`, `price`) VALUES
(1, 'john_doe', 'john.doe@example.com', 'passwordJohn123', 1, 10.99),
(2, 'jane_smith', 'jane.smith@example.com', 'passwordJane456', 1, 10.99),
(3, 'emma_brown', 'emma.brown@example.com', 'passwordEmma789', 1, 10.99);

-- --------------------------------------------------------

--
-- Table structure for table `track`
--

CREATE TABLE `track` (
  `id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `genre` varchar(100) DEFAULT NULL,
  `duration` double NOT NULL,
  `streamCount` int(11) DEFAULT 0,
  `likeCount` int(11) DEFAULT 0,
  `shareCount` int(11) DEFAULT 0,
  `artist_id` int(11) DEFAULT NULL,
  `album_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `track`
--

INSERT INTO `track` (`id`, `title`, `genre`, `duration`, `streamCount`, `likeCount`, `shareCount`, `artist_id`, `album_id`) VALUES
(26, 'Billie Jean', 'Pop', 4.54, 1000000, 500000, 200000, 1, 36),
(27, 'Back in Black', 'Rock', 4.15, 800000, 400000, 150000, 2, 37),
(28, 'Comfortably Numb', 'Rock', 6.24, 1200000, 600000, 250000, 3, 38),
(29, 'Come Together', 'Rock', 4.2, 900000, 450000, 180000, 4, 39),
(30, 'Smells Like Teen Spirit', 'Grunge', 5.01, 1500000, 750000, 300000, 5, 40);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `ads`
--
ALTER TABLE `ads`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `album`
--
ALTER TABLE `album`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `artist`
--
ALTER TABLE `artist`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `free_user`
--
ALTER TABLE `free_user`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `playlist`
--
ALTER TABLE `playlist`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `playlist_track`
--
ALTER TABLE `playlist_track`
  ADD PRIMARY KEY (`playlist_id`,`track_id`),
  ADD KEY `track_id` (`track_id`);

--
-- Indexes for table `premium_user`
--
ALTER TABLE `premium_user`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `track`
--
ALTER TABLE `track`
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
-- AUTO_INCREMENT for table `album`
--
ALTER TABLE `album`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT for table `artist`
--
ALTER TABLE `artist`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `free_user`
--
ALTER TABLE `free_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `playlist`
--
ALTER TABLE `playlist`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `premium_user`
--
ALTER TABLE `premium_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `track`
--
ALTER TABLE `track`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `album`
--
ALTER TABLE `album`
  ADD CONSTRAINT `album_ibfk_1` FOREIGN KEY (`artist_id`) REFERENCES `artist` (`id`) ON DELETE SET NULL;

--
-- Constraints for table `playlist_track`
--
ALTER TABLE `playlist_track`
  ADD CONSTRAINT `playlist_track_ibfk_1` FOREIGN KEY (`playlist_id`) REFERENCES `playlist` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `playlist_track_ibfk_2` FOREIGN KEY (`track_id`) REFERENCES `track` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `track`
--
ALTER TABLE `track`
  ADD CONSTRAINT `track_ibfk_1` FOREIGN KEY (`artist_id`) REFERENCES `artist` (`id`) ON DELETE SET NULL,
  ADD CONSTRAINT `track_ibfk_2` FOREIGN KEY (`album_id`) REFERENCES `album` (`id`) ON DELETE SET NULL;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
