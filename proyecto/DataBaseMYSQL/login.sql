-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 21-12-2020 a las 16:11:44
-- Versión del servidor: 10.4.17-MariaDB
-- Versión de PHP: 8.0.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `login`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `course`
--

CREATE TABLE `course` (
  `id` int(50) NOT NULL,
  `username` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `course`
--

INSERT INTO `course` (`id`, `username`, `email`) VALUES
(1, '', ''),
(2, 'users@users.com', 'admib'),
(3, 'ElManotas', 'HCD3'),
(7, 'yo', 'tret');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `documentspdf`
--

CREATE TABLE `documentspdf` (
  `id` int(100) NOT NULL,
  `title` varchar(100) NOT NULL,
  `location` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `documentspdf`
--

INSERT INTO `documentspdf` (`id`, `title`, `location`) VALUES
(14, 'http://192.168.1.66/proyecto/PDFS/0.pdf', 'PDFS/0.pdf'),
(15, 'http://192.168.1.66/proyecto/PDFS/14.pdf', 'PDFS/14.pdf'),
(16, 'http://192.168.1.66/proyecto/PDFS/15.pdf', 'PDFS/15.pdf'),
(17, 'http://192.168.1.66/proyecto/PDFS/16.pdf', 'PDFS/16.pdf');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `exams`
--

CREATE TABLE `exams` (
  `id` int(50) NOT NULL,
  `textexam` text NOT NULL,
  `a` varchar(100) NOT NULL,
  `b` varchar(50) NOT NULL,
  `c` varchar(300) NOT NULL,
  `d` varchar(50) NOT NULL,
  `correctAnswer` varchar(50) NOT NULL,
  `img` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `exams`
--

INSERT INTO `exams` (`id`, `textexam`, `a`, `b`, `c`, `d`, `correctAnswer`, `img`) VALUES
(1, 'rrt', 'r', 't', 'r', 'r', 'a', 'http://192.168.1.66/proyecto/uploads/uploads/0.png'),
(2, '', '', '', '', '', '', 'http://192.168.1.66/proyecto/uploads/uploads/1.png'),
(3, 'Pregunta', 'respuesta1', 'respuesta1', 'respuesta1', 'respuesta1', 'd', 'http://192.168.1.66/proyecto/uploads/uploads/2.png');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `notes`
--

CREATE TABLE `notes` (
  `id` int(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `note` int(100) NOT NULL,
  `time` int(50) NOT NULL,
  `rank` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `notes`
--

INSERT INTO `notes` (`id`, `username`, `note`, `time`, `rank`) VALUES
(1, 'test', 3, 4, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `teacher`
--

CREATE TABLE `teacher` (
  `id` int(50) NOT NULL,
  `username` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `fullname` text NOT NULL,
  `course` varchar(50) NOT NULL,
  `tipe` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `teacher`
--

INSERT INTO `teacher` (`id`, `username`, `email`, `password`, `fullname`, `course`, `tipe`) VALUES
(2, 'admin', 'admin', '$2y$10$mC2YvIsn1.LKqOtyCAmSo.WJpd8u9py0pNldUN6BinMQm2GNSFnDG', 'admin', 'admin', 'admin');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `id` int(50) NOT NULL,
  `fullname` text NOT NULL,
  `email` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `tipe` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`id`, `fullname`, `email`, `username`, `password`, `tipe`) VALUES
(1, 'Michael Negrete', 'users@users.com', 'ElManotas', '$2y$10$Zdfd96.0oYvTCodBgOn4au1uLMIOlx3g.heFqPtqWzg', 'user'),
(2, 'hola', 'hola', 'hola', '$2y$10$1nlNXAyMgpy.3ikRcen14OUUAKNdQrNuVJwEV.0qQVh', 'user'),
(3, 'yo', 'yo', 'yo', '$2y$10$rvBiYBn..m7ts4FwSspSk.0DNm4FrWPe7xGnpjcZJPE', 'user'),
(4, 'q', 'q', 'q', '$2y$10$RJ/BvMWV3mhs3aB62qIzEOP4xxszgfwEInIEfe4SdDW', 'user'),
(5, 'w', 'w', 'w', '$2y$10$s/JKOpgbvE51Cr8MdqPnBO3a0cg3aQJrn/Tj6EU7U8FNkxI1KW5Ey', 'user');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indices de la tabla `documentspdf`
--
ALTER TABLE `documentspdf`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `exams`
--
ALTER TABLE `exams`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `notes`
--
ALTER TABLE `notes`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `teacher`
--
ALTER TABLE `teacher`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `course` (`course`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `course`
--
ALTER TABLE `course`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de la tabla `documentspdf`
--
ALTER TABLE `documentspdf`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de la tabla `exams`
--
ALTER TABLE `exams`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `notes`
--
ALTER TABLE `notes`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `teacher`
--
ALTER TABLE `teacher`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `users`
--
ALTER TABLE `users`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
