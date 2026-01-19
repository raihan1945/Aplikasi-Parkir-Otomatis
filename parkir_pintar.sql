-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 19 Jan 2026 pada 11.53
-- Versi server: 10.4.32-MariaDB
-- Versi PHP: 8.1.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `parkir_pintar`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `kendaraan`
--

CREATE TABLE `kendaraan` (
  `id_kendaraan` int(10) NOT NULL,
  `plat_nomor` varchar(100) NOT NULL,
  `jenis_kendaraan` varchar(100) NOT NULL,
  `waktu_masuk` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `waktu_keluar` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `kendaraan`
--

INSERT INTO `kendaraan` (`id_kendaraan`, `plat_nomor`, `jenis_kendaraan`, `waktu_masuk`, `waktu_keluar`) VALUES
(5, 'B3091XYZ', 'Motor', '2026-01-19 17:31:46', '2026-01-19 17:31:46'),
(6, 'B6535XYZ', 'Motor', '2026-01-19 17:32:22', '2026-01-19 17:32:22'),
(7, 'B5553XYZ', 'Mobil', '2026-01-17 00:30:24', '2026-01-17 00:30:24'),
(8, 'B9550XYZ', 'Motor', '2026-01-17 01:15:02', NULL),
(9, 'B4078XYZ', 'Mobil', '2026-01-17 01:15:02', NULL),
(10, 'B3811XYZ', 'Mobil', '2026-01-17 01:15:02', NULL),
(11, 'B5359XYZ', 'Mobil', '2026-01-17 01:15:02', NULL),
(12, 'B9681XYZ', 'Motor', '2026-01-17 01:15:02', NULL),
(13, 'B1374XYZ', 'Mobil', '2026-01-17 01:15:02', NULL),
(14, 'B1586XYZ', 'Motor', '2026-01-17 01:15:02', NULL),
(15, 'B4755XYZ', 'Mobil', '2026-01-17 01:15:02', NULL),
(16, 'B7130XYZ', 'Mobil', '2026-01-17 01:15:02', NULL),
(17, 'B5008XYZ', 'Motor', '2026-01-17 01:15:02', NULL),
(18, 'B2656XYZ', 'Mobil', '2026-01-17 01:15:02', NULL),
(19, 'B6277XYZ', 'Mobil', '2026-01-17 01:15:02', NULL),
(20, 'B9983XYZ', 'Mobil', '2026-01-17 01:15:02', NULL),
(21, 'B3941XYZ', 'Mobil', '2026-01-17 01:15:02', NULL),
(22, 'B5612XYZ', 'Mobil', '2026-01-17 01:15:02', NULL),
(23, 'B9100XYZ', 'Mobil', '2026-01-17 01:15:02', NULL),
(24, 'B6856XYZ', 'Mobil', '2026-01-17 01:15:02', NULL),
(25, 'B1982XYZ', 'Mobil', '2026-01-17 01:15:02', NULL),
(26, 'B6905XYZ', 'Mobil', '2026-01-17 01:15:02', NULL),
(27, 'B7011XYZ', 'Mobil', '2026-01-17 01:15:02', NULL),
(28, 'B8677XYZ', 'Motor', '2026-01-17 01:15:02', NULL),
(29, 'B2550XYZ', 'Mobil', '2026-01-17 01:15:02', NULL),
(30, 'B3818XYZ', 'Mobil', '2026-01-17 01:15:02', NULL),
(31, 'B5510XYZ', 'Motor', '2026-01-17 01:15:02', NULL),
(32, 'B4863XYZ', 'Motor', '2026-01-17 01:15:02', NULL),
(33, 'B1297XYZ', 'Mobil', '2026-01-17 01:15:02', NULL),
(34, 'B8556XYZ', 'Motor', '2026-01-17 01:15:02', NULL),
(35, 'B3453XYZ', 'Motor', '2026-01-17 01:15:02', NULL),
(36, 'B7906XYZ', 'Mobil', '2026-01-17 01:15:02', NULL),
(37, 'B2155XYZ', 'Motor', '2026-01-17 01:15:02', NULL),
(38, 'B6210XYZ', 'Motor', '2026-01-17 01:15:02', NULL),
(39, 'B2398XYZ', 'Motor', '2026-01-17 01:15:02', NULL),
(40, 'B2298XYZ', 'Motor', '2026-01-17 01:15:02', NULL),
(41, 'B4358XYZ', 'Motor', '2026-01-17 01:15:02', NULL),
(42, 'B1610XYZ', 'Mobil', '2026-01-17 01:15:02', NULL),
(43, 'B1901XYZ', 'Motor', '2026-01-17 01:15:02', NULL),
(44, 'B9281XYZ', 'Mobil', '2026-01-17 01:15:02', NULL),
(45, 'B9215XYZ', 'Motor', '2026-01-17 01:15:02', NULL),
(46, 'B9026XYZ', 'Mobil', '2026-01-17 01:15:02', NULL),
(47, 'B1375XYZ', 'Motor', '2026-01-17 01:15:02', NULL),
(48, 'B2344XYZ', 'Motor', '2026-01-17 01:15:02', NULL),
(49, 'B8604XYZ', 'Motor', '2026-01-17 01:15:02', NULL),
(50, 'B6635XYZ', 'Motor', '2026-01-17 01:15:02', NULL),
(51, 'B6553XYZ', 'Mobil', '2026-01-17 01:15:02', NULL),
(52, 'B2312XYZ', 'Motor', '2026-01-17 01:15:02', NULL),
(53, 'B4738XYZ', 'Motor', '2026-01-17 01:15:02', NULL),
(54, 'B8909XYZ', 'Motor', '2026-01-17 01:15:02', NULL),
(55, 'B1079XYZ', 'Mobil', '2026-01-17 01:15:02', NULL),
(56, 'B2932XYZ', 'Mobil', '2026-01-17 01:15:02', NULL),
(57, 'B8502XYZ', 'Motor', '2026-01-17 01:15:02', NULL),
(58, 'B6655XYZ', 'Mobil', '2026-01-17 01:15:02', NULL),
(59, 'B3189XYZ', 'Mobil', '2026-01-17 01:15:02', NULL),
(60, 'B2895XYZ', 'Mobil', '2026-01-19 17:52:37', '2026-01-19 17:52:37'),
(61, 'B8947XYZ', 'Mobil', '2026-01-17 01:15:02', NULL),
(62, 'B5073XYZ', 'Motor', '2026-01-17 01:15:02', NULL),
(63, 'B9586XYZ', 'Motor', '2026-01-17 01:15:02', NULL),
(64, 'B2517XYZ', 'Mobil', '2026-01-17 01:15:02', NULL),
(65, 'B9661XYZ', 'Mobil', '2026-01-17 01:15:02', NULL),
(66, 'B9310XYZ', 'Motor', '2026-01-17 01:15:02', NULL),
(67, 'B8376XYZ', 'Motor', '2026-01-17 01:15:02', NULL),
(68, 'B3789XYZ', 'Motor', '2026-01-17 01:15:02', NULL),
(69, 'B8779XYZ', 'Motor', '2026-01-17 01:15:02', NULL),
(70, 'B1734XYZ', 'Motor', '2026-01-17 01:15:02', NULL),
(71, 'B9219XYZ', 'Motor', '2026-01-17 01:15:02', NULL),
(72, 'B8521XYZ', 'Mobil', '2026-01-17 01:15:02', NULL),
(73, 'B3103XYZ', 'Motor', '2026-01-17 01:15:02', NULL),
(74, 'B7090XYZ', 'Mobil', '2026-01-17 01:15:02', NULL),
(75, 'B1395XYZ', 'Motor', '2026-01-17 01:15:02', NULL),
(76, 'B5101XYZ', 'Mobil', '2026-01-17 01:15:02', NULL),
(77, 'B9772XYZ', 'Motor', '2026-01-17 01:15:02', NULL),
(78, 'B6865XYZ', 'Mobil', '2026-01-17 01:15:02', NULL),
(79, 'B8075XYZ', 'Motor', '2026-01-17 01:15:02', NULL),
(80, 'B3358XYZ', 'Mobil', '2026-01-17 01:15:02', NULL),
(81, 'B5901XYZ', 'Motor', '2026-01-17 01:15:02', NULL),
(82, 'B1161XYZ', 'Motor', '2026-01-17 01:15:02', NULL),
(83, 'B6633XYZ', 'Motor', '2026-01-17 01:15:02', NULL),
(84, 'B8340XYZ', 'Motor', '2026-01-17 01:15:02', NULL),
(85, 'B8503XYZ', 'Motor', '2026-01-17 01:15:02', NULL),
(86, 'B8922XYZ', 'Mobil', '2026-01-17 01:15:02', NULL),
(87, 'B8855XYZ', 'Mobil', '2026-01-17 01:15:02', NULL),
(88, 'B8815XYZ', 'Motor', '2026-01-17 01:15:02', NULL),
(89, 'B5669XYZ', 'Motor', '2026-01-17 01:15:02', NULL),
(90, 'B3284XYZ', 'Mobil', '2026-01-17 01:15:02', NULL),
(91, 'B4628XYZ', 'Motor', '2026-01-17 01:15:02', NULL),
(92, 'B6348XYZ', 'Mobil', '2026-01-17 01:15:02', NULL),
(93, 'B4688XYZ', 'Motor', '2026-01-17 01:15:02', NULL),
(94, 'B7585XYZ', 'Motor', '2026-01-17 01:15:02', NULL),
(95, 'B7566XYZ', 'Motor', '2026-01-17 01:15:02', NULL),
(96, 'B6398XYZ', 'Motor', '2026-01-17 01:15:02', NULL),
(97, 'B6329XYZ', 'Motor', '2026-01-17 01:15:02', NULL),
(98, 'B1770XYZ', 'Mobil', '2026-01-17 01:15:02', NULL),
(99, 'B8636XYZ', 'Motor', '2026-01-17 01:15:02', NULL),
(100, 'B5700XYZ', 'Mobil', '2026-01-17 01:15:02', NULL),
(101, 'AB8648JX', 'Mobil', '2026-01-19 17:30:28', NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE `user` (
  `id_user` int(10) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`id_user`, `username`, `password`) VALUES
(1, 'admin', 'admin');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `kendaraan`
--
ALTER TABLE `kendaraan`
  ADD PRIMARY KEY (`id_kendaraan`);

--
-- Indeks untuk tabel `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `kendaraan`
--
ALTER TABLE `kendaraan`
  MODIFY `id_kendaraan` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=102;

--
-- AUTO_INCREMENT untuk tabel `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
