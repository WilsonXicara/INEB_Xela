-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         5.5.33a-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             8.0.0.4396
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Volcando estructura de base de datos para sbd_inebxela
DROP DATABASE IF EXISTS `sbd_inebxela`;
CREATE DATABASE IF NOT EXISTS `sbd_inebxela` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `sbd_inebxela`;


-- Volcando estructura para tabla sbd_inebxela.administrador
DROP TABLE IF EXISTS `administrador`;
CREATE TABLE IF NOT EXISTS `administrador` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nombres` varchar(25) DEFAULT NULL,
  `Apellidos` varchar(20) DEFAULT NULL,
  `Direccion` varchar(75) DEFAULT NULL,
  `Dpi` varchar(15) DEFAULT NULL,
  `Sexo` varchar(45) DEFAULT NULL,
  `Municipio_Id` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk_Administrador_Municipio1_idx` (`Municipio_Id`),
  CONSTRAINT `fk_Administrador_Municipio1` FOREIGN KEY (`Municipio_Id`) REFERENCES `municipio` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla sbd_inebxela.administrador: ~1 rows (aproximadamente)
DELETE FROM `administrador`;
/*!40000 ALTER TABLE `administrador` DISABLE KEYS */;
INSERT INTO `administrador` (`Id`, `Nombres`, `Apellidos`, `Direccion`, `Dpi`, `Sexo`, `Municipio_Id`) VALUES
	(1, 'Ana Maria', 'Aguilar Morales', 'Xela', '123456780901', 'Femenino', 15);
/*!40000 ALTER TABLE `administrador` ENABLE KEYS */;


-- Volcando estructura para tabla sbd_inebxela.asignacioncat
DROP TABLE IF EXISTS `asignacioncat`;
CREATE TABLE IF NOT EXISTS `asignacioncat` (
  `CicloEscolar_Id` int(11) DEFAULT NULL,
  `Grado_Id` int(11) DEFAULT NULL,
  `Curso_Id` int(11) DEFAULT NULL,
  `Catedratico_Id` int(11) DEFAULT NULL,
  KEY `fk_Curso_has_CicloEscolar_CicloEscolar1_idx` (`CicloEscolar_Id`),
  KEY `fk_Curso_has_CicloEscolar_Curso1_idx` (`Curso_Id`),
  KEY `fk_Componentes_Grado1_idx` (`Grado_Id`),
  KEY `fk_Componentes_Catedratico1_idx` (`Catedratico_Id`),
  CONSTRAINT `fk_Componentes_Catedratico1` FOREIGN KEY (`Catedratico_Id`) REFERENCES `catedratico` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Componentes_Grado1` FOREIGN KEY (`Grado_Id`) REFERENCES `grado` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Curso_has_CicloEscolar_CicloEscolar1` FOREIGN KEY (`CicloEscolar_Id`) REFERENCES `cicloescolar` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Curso_has_CicloEscolar_Curso1` FOREIGN KEY (`Curso_Id`) REFERENCES `curso` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla sbd_inebxela.asignacioncat: ~43 rows (aproximadamente)
DELETE FROM `asignacioncat`;
/*!40000 ALTER TABLE `asignacioncat` DISABLE KEYS */;
INSERT INTO `asignacioncat` (`CicloEscolar_Id`, `Grado_Id`, `Curso_Id`, `Catedratico_Id`) VALUES
	(3, 1, 1, 1),
	(1, 1, 3, 2),
	(3, 2, 5, 3),
	(3, 2, 7, 4),
	(3, 3, 9, 5),
	(3, 3, 11, 6),
	(3, 4, 13, 7),
	(3, 4, 15, 1),
	(3, 5, 17, 6),
	(1, 5, 19, 2),
	(3, 6, 21, 3),
	(3, 6, 20, 1),
	(4, 1, 2, 1),
	(4, 1, 4, 2),
	(4, 2, 6, 3),
	(4, 2, 8, 4),
	(2, 3, 10, 5),
	(4, 3, 12, 6),
	(4, 4, 14, 7),
	(4, 4, 16, 1),
	(2, 5, 18, 6),
	(1, 5, 20, 2),
	(4, 6, 21, 3),
	(4, 6, 17, 1),
	(3, 1, 1, 7),
	(5, 1, 3, 6),
	(5, 2, 5, 5),
	(4, 2, 7, 4),
	(5, 3, 9, 3),
	(5, 3, 11, 2),
	(5, 4, 13, 1),
	(5, 4, 15, 1),
	(5, 5, 17, 3),
	(5, 5, 19, 2),
	(5, 6, 21, 5),
	(5, 6, 20, 6),
	(5, 7, NULL, NULL),
	(5, 8, NULL, NULL),
	(5, NULL, 22, NULL),
	(5, NULL, 1, NULL),
	(5, 7, 22, 1),
	(5, 8, 1, 4),
	(5, 8, 22, 2);
/*!40000 ALTER TABLE `asignacioncat` ENABLE KEYS */;


-- Volcando estructura para tabla sbd_inebxela.asignacionest
DROP TABLE IF EXISTS `asignacionest`;
CREATE TABLE IF NOT EXISTS `asignacionest` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `CicloEscolar_Id` int(11) NOT NULL,
  `Grado_Id` int(11) NOT NULL,
  `Estudiante_Id` int(11) NOT NULL,
  `Aula` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk_CicloEscolar_has_Grado_Grado1_idx` (`Grado_Id`),
  KEY `fk_CicloEscolar_has_Grado_CicloEscolar1_idx` (`CicloEscolar_Id`),
  KEY `fk_Asignacion_Estudiante1_idx` (`Estudiante_Id`),
  CONSTRAINT `fk_Asignacion_Estudiante1` FOREIGN KEY (`Estudiante_Id`) REFERENCES `estudiante` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_CicloEscolar_has_Grado_CicloEscolar1` FOREIGN KEY (`CicloEscolar_Id`) REFERENCES `cicloescolar` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_CicloEscolar_has_Grado_Grado1` FOREIGN KEY (`Grado_Id`) REFERENCES `grado` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla sbd_inebxela.asignacionest: ~8 rows (aproximadamente)
DELETE FROM `asignacionest`;
/*!40000 ALTER TABLE `asignacionest` DISABLE KEYS */;
INSERT INTO `asignacionest` (`Id`, `CicloEscolar_Id`, `Grado_Id`, `Estudiante_Id`, `Aula`) VALUES
	(1, 4, 4, 5, 'Aula 15'),
	(2, 5, 1, 1, 'Aula01'),
	(3, 5, 1, 2, 'Aula1A'),
	(4, 5, 3, 3, 'Aula2A'),
	(5, 5, 6, 4, 'Aula3B'),
	(6, 5, 4, 6, 'Aula2B'),
	(7, 4, 5, 8, 'Aula3A'),
	(8, 1, 1, 7, 'Auallll');
/*!40000 ALTER TABLE `asignacionest` ENABLE KEYS */;


-- Volcando estructura para tabla sbd_inebxela.catedratico
DROP TABLE IF EXISTS `catedratico`;
CREATE TABLE IF NOT EXISTS `catedratico` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nombres` varchar(25) DEFAULT NULL,
  `Apellidos` varchar(20) DEFAULT NULL,
  `Direccion` varchar(75) DEFAULT NULL,
  `DPI` varchar(15) DEFAULT NULL,
  `Sexo` varchar(10) DEFAULT NULL,
  `Etnia` varchar(15) DEFAULT NULL,
  `Municipio_Id` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk_Catedratico_Municipio1_idx` (`Municipio_Id`),
  CONSTRAINT `fk_Catedratico_Municipio1` FOREIGN KEY (`Municipio_Id`) REFERENCES `municipio` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla sbd_inebxela.catedratico: ~7 rows (aproximadamente)
DELETE FROM `catedratico`;
/*!40000 ALTER TABLE `catedratico` DISABLE KEYS */;
INSERT INTO `catedratico` (`Id`, `Nombres`, `Apellidos`, `Direccion`, `DPI`, `Sexo`, `Etnia`, `Municipio_Id`) VALUES
	(1, 'Óscar', 'Perez Say', '10va. av. 20-13 zona 1', '2130251360903', 'M', 'Ladina', 12),
	(2, 'Mildred', 'Santos Pérez', '15va. av. 20-13 zona 2', '2130213640903', 'F', 'Ladina', 6),
	(3, 'Astrid', 'Santos Puac', '15va. av. 30-13 zona 5', '8130213640908', 'F', 'Ladina', 1),
	(4, 'María de los Ángeles', 'de Paz Rojas', '5va. av. 20-13 zona 2', '296321364098', 'F', 'Ladina', 8),
	(5, 'Cristian Fernando', 'Santizo Paredes', 'Colonia Telma Quixtan', '596132540902', 'M', 'Ladina', 2),
	(6, 'Pablo', 'German Orozco', 'Colonia las Cruces', '4698301290907', 'M', 'Ladina', 7),
	(7, 'Silvana', 'Barrios', '1ra. av. 12-21 zona 2', '1246931240905', 'F', 'Maya', 2);
/*!40000 ALTER TABLE `catedratico` ENABLE KEYS */;


-- Volcando estructura para tabla sbd_inebxela.cicloescolar
DROP TABLE IF EXISTS `cicloescolar`;
CREATE TABLE IF NOT EXISTS `cicloescolar` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Anio` varchar(4) NOT NULL,
  `CantidadEstudiantes` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla sbd_inebxela.cicloescolar: ~6 rows (aproximadamente)
DELETE FROM `cicloescolar`;
/*!40000 ALTER TABLE `cicloescolar` DISABLE KEYS */;
INSERT INTO `cicloescolar` (`Id`, `Anio`, `CantidadEstudiantes`) VALUES
	(1, '2013', 0),
	(2, '2014', 0),
	(3, '2015', 0),
	(4, '2016', 0),
	(5, '2017', 0),
	(6, '2018', NULL);
/*!40000 ALTER TABLE `cicloescolar` ENABLE KEYS */;


-- Volcando estructura para tabla sbd_inebxela.curso
DROP TABLE IF EXISTS `curso`;
CREATE TABLE IF NOT EXISTS `curso` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla sbd_inebxela.curso: ~22 rows (aproximadamente)
DELETE FROM `curso`;
/*!40000 ALTER TABLE `curso` DISABLE KEYS */;
INSERT INTO `curso` (`Id`, `Nombre`) VALUES
	(1, 'Matemáticas I'),
	(2, 'Matemáticas II'),
	(3, 'Matemáticas III'),
	(4, 'Comunicación y Lenguaje I'),
	(5, 'Comunicación y Lenguaje II'),
	(6, 'Comunicación y Lenguaje III'),
	(7, 'Ciencias Sociales I'),
	(8, 'Ciencias Sociales II'),
	(9, 'Ciencias Sociales III'),
	(10, 'Productividad y Desarrollo I'),
	(11, 'Productividad y Desarrollo II'),
	(12, 'Productividad y Desarrollo III'),
	(13, 'Danza I'),
	(14, 'Danza II'),
	(15, 'Danza III'),
	(16, 'Educación Física I'),
	(17, 'Educación Física II'),
	(18, 'Educación Física III'),
	(19, 'Inglés I'),
	(20, 'Inglés II'),
	(21, 'Inglés III'),
	(22, 'Frances');
/*!40000 ALTER TABLE `curso` ENABLE KEYS */;


-- Volcando estructura para tabla sbd_inebxela.encargado
DROP TABLE IF EXISTS `encargado`;
CREATE TABLE IF NOT EXISTS `encargado` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) DEFAULT NULL,
  `Direccion` varchar(75) DEFAULT NULL,
  `DPI` varchar(13) DEFAULT NULL,
  `FechaNacimiento` date DEFAULT NULL,
  `Relacion` varchar(10) DEFAULT NULL,
  `Trabajo` varchar(25) DEFAULT NULL,
  `Municipio_Id` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk_Encargado_Municipio1_idx` (`Municipio_Id`),
  CONSTRAINT `fk_Encargado_Municipio1` FOREIGN KEY (`Municipio_Id`) REFERENCES `municipio` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla sbd_inebxela.encargado: ~7 rows (aproximadamente)
DELETE FROM `encargado`;
/*!40000 ALTER TABLE `encargado` DISABLE KEYS */;
INSERT INTO `encargado` (`Id`, `Nombre`, `Direccion`, `DPI`, `FechaNacimiento`, `Relacion`, `Trabajo`, `Municipio_Id`) VALUES
	(1, 'Raúl Reyes Reyes', '8va. av. 8-69 zona 2', '1289231610901', '1960-07-15', 'Padre', 'Maestro', 15),
	(2, 'Fulgencio Raúl Reyes Reyes', 'Cantón Chuicavioc', '1024896120901', '1975-08-23', 'Padre', 'Agricultor', 15),
	(3, 'Jose Isabel Coyoy Coyoy', 'Cantón Llano del Pinal, sector 6', '7894561230123', '1980-08-08', 'Padre', 'Carpintero', 15),
	(4, 'Rigoberto Mendoza', '8va. av. 8-69 zona 8', '1289891610901', '1980-07-15', 'Padre', 'Maestro', 19),
	(5, 'Felipe Son', 'Cantón Xecaracoj', '1963196120901', '1985-08-23', 'Padre', 'Agricultor', 15),
	(6, 'Jose Isabel Cruz Cruz', 'Cantón Llano del Pinal, sector 6', '1234561230123', '1990-08-08', 'Padre', 'Carpintero', 15),
	(7, 'Hugo', 'Con el otro hugo', '1234567891235', '1875-05-01', 'Papá', 'No se', 3);
/*!40000 ALTER TABLE `encargado` ENABLE KEYS */;


-- Volcando estructura para tabla sbd_inebxela.estudiante
DROP TABLE IF EXISTS `estudiante`;
CREATE TABLE IF NOT EXISTS `estudiante` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `CodigoPersonal` varchar(7) DEFAULT NULL,
  `CUI` varchar(15) DEFAULT NULL,
  `Nombres` varchar(25) DEFAULT NULL,
  `Apellidos` varchar(20) DEFAULT NULL,
  `FechaNacimiento` date DEFAULT NULL,
  `Direccion` varchar(75) DEFAULT NULL,
  `Sexo` varchar(1) DEFAULT NULL,
  `Etnia` varchar(15) DEFAULT NULL,
  `CapacidadDiferente` tinyint(1) DEFAULT NULL,
  `TipoCapacidad` varchar(100) DEFAULT NULL,
  `Municipio_Id` int(11) NOT NULL,
  `Encargado_Id` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk_Estudiante_Municipio1_idx` (`Municipio_Id`),
  KEY `fk_Estudiante_Encargado1_idx` (`Encargado_Id`),
  CONSTRAINT `fk_Estudiante_Encargado1` FOREIGN KEY (`Encargado_Id`) REFERENCES `encargado` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Estudiante_Municipio1` FOREIGN KEY (`Municipio_Id`) REFERENCES `municipio` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla sbd_inebxela.estudiante: ~13 rows (aproximadamente)
DELETE FROM `estudiante`;
/*!40000 ALTER TABLE `estudiante` DISABLE KEYS */;
INSERT INTO `estudiante` (`Id`, `CodigoPersonal`, `CUI`, `Nombres`, `Apellidos`, `FechaNacimiento`, `Direccion`, `Sexo`, `Etnia`, `CapacidadDiferente`, `TipoCapacidad`, `Municipio_Id`, `Encargado_Id`) VALUES
	(1, 'C456ASD', '5962132549612', 'Carlos Raúl', 'Reyes Mejía', '2002-06-19', '15av. 2-01 zona 2', 'M', 'Ladina', 0, NULL, 15, 1),
	(2, 'C456ÑLK', '1254697830128', 'Melvin Adolfo', 'Reyes Santizo', '2002-01-01', '16va. calle 2-2 zona 3', 'M', 'Maya', 0, NULL, 20, 2),
	(3, 'C123ÑLK', '1201246931254', 'Aura Marina', 'Fernández Santizo', '2002-01-01', 'Cantón las Cruces', 'F', 'Maya', 0, NULL, 10, 2),
	(4, 'C987ÑLK', '1201246931254', 'Juan Esteban', 'Coyoy Coyoy', '2002-01-01', 'El Pedregal', 'M', 'Maya', 0, NULL, 15, 1),
	(5, 'C123ASD', '1203569782105', 'Gladys Mariela', 'Coyoy Coyoy', '2003-05-15', 'Los Olivos', 'F', 'Maya', 0, NULL, 15, 1),
	(6, 'C789ASD', '8120356978210', 'Juan', 'Coyoy Mejía', '2003-05-15', 'Cantón los Pinabetes', 'F', 'Maya', 0, NULL, 15, 3),
	(7, 'C654ASD', '5962132549612', 'Brandon', 'Mejía', '2002-06-19', '19av. 4-4 zona 2', 'M', 'Ladina', 0, NULL, 15, 6),
	(8, 'C654ÑLK', '8554697830128', 'Adolfo', 'Santizo', '2002-01-01', 'Canton las Cruces', 'M', 'Maya', 0, NULL, 1, 5),
	(9, 'C321ÑLK', '4501943531254', 'Mariana', 'Pérez Hernández', '2002-08-01', 'Condiminio Los Pinos', 'F', 'Maya', 0, NULL, 3, 4),
	(10, 'C789ÑLK', '9801249831254', 'Joselline Betzabé', 'Cuá López', '2002-01-01', 'Trigales', 'F', 'Maya', 0, NULL, 5, 3),
	(11, 'C321ASD', '5803569782102', 'Ashley Shijaya', 'Ixcaraguá Soto', '2003-05-15', '8va. calle 20-20 zona 5', 'F', 'Garifuna', 0, NULL, 7, 2),
	(12, 'C987ASD', '1003569982108', 'Juan Pablo', 'de Paz Méndez', '2003-05-15', 'Cantón Chuicavioc', 'M', 'Xinca', 0, NULL, 9, 1),
	(13, 'a123aaa', '1234567891234', 'Hugo', 'Tzul', '1996-01-30', 'mi casa', 'M', 'Maya', 0, NULL, 3, 7);
/*!40000 ALTER TABLE `estudiante` ENABLE KEYS */;


-- Volcando estructura para tabla sbd_inebxela.grado
DROP TABLE IF EXISTS `grado`;
CREATE TABLE IF NOT EXISTS `grado` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(10) DEFAULT NULL,
  `Seccion` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla sbd_inebxela.grado: ~8 rows (aproximadamente)
DELETE FROM `grado`;
/*!40000 ALTER TABLE `grado` DISABLE KEYS */;
INSERT INTO `grado` (`Id`, `Nombre`, `Seccion`) VALUES
	(1, 'Primero', 'A'),
	(2, 'Primero', 'B'),
	(3, 'Segundo', 'A'),
	(4, 'Segundo', 'B'),
	(5, 'Tercero', 'A'),
	(6, 'Tercero', 'B'),
	(7, 'Primero', '1'),
	(8, 'Segundo', '1');
/*!40000 ALTER TABLE `grado` ENABLE KEYS */;


-- Volcando estructura para tabla sbd_inebxela.libro
DROP TABLE IF EXISTS `libro`;
CREATE TABLE IF NOT EXISTS `libro` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Codigo` varchar(5) DEFAULT NULL,
  `Nombre` varchar(45) DEFAULT NULL,
  `Autor` varchar(20) DEFAULT NULL,
  `Editorial` varchar(20) DEFAULT NULL,
  `Estado` varchar(1) DEFAULT NULL,
  `PaqueteLibro_Codigo` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk_Libro_Paquete_libro1_idx` (`PaqueteLibro_Codigo`),
  CONSTRAINT `fk_Libro_Paquete_libro1` FOREIGN KEY (`PaqueteLibro_Codigo`) REFERENCES `paquetelibro` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla sbd_inebxela.libro: ~4 rows (aproximadamente)
DELETE FROM `libro`;
/*!40000 ALTER TABLE `libro` DISABLE KEYS */;
INSERT INTO `libro` (`Id`, `Codigo`, `Nombre`, `Autor`, `Editorial`, `Estado`, `PaqueteLibro_Codigo`) VALUES
	(1, 'A11AB', 'Mate', 'Pupusa', 'tortilla', '5', 1),
	(2, 'A11AC', 'Español', 'pupusa', 'tortilla', '4', 1),
	(3, 'A11AD', 'Ciencias Sociales', 'Oscarina', 'tortilla', '3', 1),
	(4, 'A11AE', 'Naturales', 'Chofito', 'Tortilla', '4', 1);
/*!40000 ALTER TABLE `libro` ENABLE KEYS */;


-- Volcando estructura para tabla sbd_inebxela.municipio
DROP TABLE IF EXISTS `municipio`;
CREATE TABLE IF NOT EXISTS `municipio` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla sbd_inebxela.municipio: ~24 rows (aproximadamente)
DELETE FROM `municipio`;
/*!40000 ALTER TABLE `municipio` DISABLE KEYS */;
INSERT INTO `municipio` (`Id`, `Nombre`) VALUES
	(1, 'Almolonga'),
	(2, 'Cabricán'),
	(3, 'Cajolá'),
	(4, 'Cantel'),
	(5, 'Coatepeque'),
	(6, 'Colomba Costa Cuca'),
	(7, 'Concepción Chiquirichapa'),
	(8, 'El Palmar'),
	(9, 'Flores Costa Cuca'),
	(10, 'Génova Costa Cuca'),
	(11, 'Huitán'),
	(12, 'La Esperanza'),
	(13, 'Olintepeque'),
	(14, 'Palestina de los Altos'),
	(15, 'Quetzaltenango'),
	(16, 'Salcajá'),
	(17, 'San Carlos Sija'),
	(18, 'San Francisco La Unión'),
	(19, 'San Juan Ostuncalco'),
	(20, 'San Martín Sacatepéquez'),
	(21, 'San Mateo'),
	(22, 'San Miguel Sigüilá'),
	(23, 'Sibilia'),
	(24, 'Zunil');
/*!40000 ALTER TABLE `municipio` ENABLE KEYS */;


-- Volcando estructura para tabla sbd_inebxela.notas
DROP TABLE IF EXISTS `notas`;
CREATE TABLE IF NOT EXISTS `notas` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `AsignacionEST_Id` int(11) NOT NULL,
  `Estudiante_Id` int(11) NOT NULL,
  `Curso_Id` int(11) NOT NULL,
  `Nota1` float DEFAULT NULL,
  `Nota2` float DEFAULT NULL,
  `Nota3` float DEFAULT NULL,
  `Nota4` float DEFAULT NULL,
  `NotaRecuperacion` float DEFAULT NULL,
  `NotaFinal` float DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk_Estudiante_has_Curso_Curso1_idx` (`Curso_Id`),
  KEY `fk_Estudiante_has_Curso_Estudiante1_idx` (`Estudiante_Id`),
  KEY `fk_Notas_AsignacionEST1_idx` (`AsignacionEST_Id`),
  CONSTRAINT `fk_Estudiante_has_Curso_Curso1` FOREIGN KEY (`Curso_Id`) REFERENCES `curso` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Estudiante_has_Curso_Estudiante1` FOREIGN KEY (`Estudiante_Id`) REFERENCES `estudiante` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Notas_AsignacionEST1` FOREIGN KEY (`AsignacionEST_Id`) REFERENCES `asignacionest` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla sbd_inebxela.notas: ~8 rows (aproximadamente)
DELETE FROM `notas`;
/*!40000 ALTER TABLE `notas` DISABLE KEYS */;
INSERT INTO `notas` (`Id`, `AsignacionEST_Id`, `Estudiante_Id`, `Curso_Id`, `Nota1`, `Nota2`, `Nota3`, `Nota4`, `NotaRecuperacion`, `NotaFinal`) VALUES
	(1, 3, 2, 3, 0, 0, 0, 0, 0, 0),
	(2, 4, 3, 9, 0, 0, 0, 0, 0, 0),
	(3, 4, 3, 11, 0, 0, 0, 0, 0, 0),
	(4, 5, 4, 21, 0, 0, 0, 0, 0, 0),
	(5, 5, 4, 20, 0, 0, 0, 0, 0, 0),
	(6, 6, 6, 13, 0, 0, 0, 0, 0, 0),
	(7, 6, 6, 15, 0, 0, 0, 0, 0, 0),
	(8, 8, 7, 3, 0, 0, 0, 0, 0, 0);
/*!40000 ALTER TABLE `notas` ENABLE KEYS */;


-- Volcando estructura para tabla sbd_inebxela.paquetelibro
DROP TABLE IF EXISTS `paquetelibro`;
CREATE TABLE IF NOT EXISTS `paquetelibro` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Codigo` varchar(5) DEFAULT NULL,
  `Descripcion` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla sbd_inebxela.paquetelibro: ~5 rows (aproximadamente)
DELETE FROM `paquetelibro`;
/*!40000 ALTER TABLE `paquetelibro` DISABLE KEYS */;
INSERT INTO `paquetelibro` (`Id`, `Codigo`, `Descripcion`) VALUES
	(1, '1AA01', 'ME pela a mi la verga'),
	(2, '2BB01', 'Valio madres'),
	(3, '3CC01', 'Me pela el huevo'),
	(4, '4DD01', 'Gordito nos quiere'),
	(5, '5EE01', 'Casa');
/*!40000 ALTER TABLE `paquetelibro` ENABLE KEYS */;


-- Volcando estructura para tabla sbd_inebxela.prestamo
DROP TABLE IF EXISTS `prestamo`;
CREATE TABLE IF NOT EXISTS `prestamo` (
  `PaqueteLibro_Id` int(11) NOT NULL,
  `Estudiante_Id` int(11) NOT NULL,
  `CodigoBoleta` varchar(10) DEFAULT NULL,
  `FechaPago` datetime DEFAULT NULL,
  `Monto` float DEFAULT NULL,
  PRIMARY KEY (`PaqueteLibro_Id`,`Estudiante_Id`),
  KEY `fk_PaqueteLibro_has_Estudiante_Estudiante1_idx` (`Estudiante_Id`),
  KEY `fk_PaqueteLibro_has_Estudiante_PaqueteLibro1_idx` (`PaqueteLibro_Id`),
  CONSTRAINT `fk_PaqueteLibro_has_Estudiante_Estudiante1` FOREIGN KEY (`Estudiante_Id`) REFERENCES `estudiante` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_PaqueteLibro_has_Estudiante_PaqueteLibro1` FOREIGN KEY (`PaqueteLibro_Id`) REFERENCES `paquetelibro` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla sbd_inebxela.prestamo: ~0 rows (aproximadamente)
DELETE FROM `prestamo`;
/*!40000 ALTER TABLE `prestamo` DISABLE KEYS */;
/*!40000 ALTER TABLE `prestamo` ENABLE KEYS */;


-- Volcando estructura para tabla sbd_inebxela.reporte
DROP TABLE IF EXISTS `reporte`;
CREATE TABLE IF NOT EXISTS `reporte` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Fecha` datetime DEFAULT NULL,
  `Descripcion` varchar(25) DEFAULT NULL,
  `Estudiante_Id` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk_Reporte_Estudiante1_idx` (`Estudiante_Id`),
  CONSTRAINT `fk_Reporte_Estudiante1` FOREIGN KEY (`Estudiante_Id`) REFERENCES `estudiante` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla sbd_inebxela.reporte: ~0 rows (aproximadamente)
DELETE FROM `reporte`;
/*!40000 ALTER TABLE `reporte` DISABLE KEYS */;
/*!40000 ALTER TABLE `reporte` ENABLE KEYS */;


-- Volcando estructura para tabla sbd_inebxela.telefono
DROP TABLE IF EXISTS `telefono`;
CREATE TABLE IF NOT EXISTS `telefono` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Telefono` varchar(8) DEFAULT NULL,
  `Catedratico_Id` int(11) DEFAULT NULL,
  `Administrador_Id` int(11) DEFAULT NULL,
  `Encargado_Id` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk_Teléfono_Catedrático1_idx` (`Catedratico_Id`),
  KEY `fk_Teléfono_Administrador1_idx` (`Administrador_Id`),
  KEY `fk_Telefono_Encargado1_idx` (`Encargado_Id`),
  CONSTRAINT `fk_Telefono_Encargado1` FOREIGN KEY (`Encargado_Id`) REFERENCES `encargado` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Teléfono_Administrador1` FOREIGN KEY (`Administrador_Id`) REFERENCES `administrador` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Teléfono_Catedrático1` FOREIGN KEY (`Catedratico_Id`) REFERENCES `catedratico` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla sbd_inebxela.telefono: ~11 rows (aproximadamente)
DELETE FROM `telefono`;
/*!40000 ALTER TABLE `telefono` DISABLE KEYS */;
INSERT INTO `telefono` (`Id`, `Telefono`, `Catedratico_Id`, `Administrador_Id`, `Encargado_Id`) VALUES
	(1, '53333265', NULL, NULL, 1),
	(2, '53203149', NULL, NULL, 2),
	(3, '53596135', NULL, NULL, 3),
	(4, '56333265', 1, NULL, NULL),
	(5, '40333265', 2, NULL, NULL),
	(6, '30333265', 3, NULL, NULL),
	(7, '53893065', 4, NULL, NULL),
	(8, '53300065', 5, NULL, NULL),
	(9, '53153265', 6, NULL, NULL),
	(10, '40564127', 7, NULL, NULL),
	(11, '57612410', NULL, 1, NULL);
/*!40000 ALTER TABLE `telefono` ENABLE KEYS */;


-- Volcando estructura para tabla sbd_inebxela.usuarios
DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE IF NOT EXISTS `usuarios` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `NombreUsuario` varchar(15) DEFAULT NULL,
  `Contrasenia` varchar(10) NOT NULL,
  `Tipo` varchar(1) DEFAULT NULL,
  `Catedratico_Id` int(11) DEFAULT NULL,
  `Administrador_Id` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk_Usuarios_Catedratico1_idx` (`Catedratico_Id`),
  KEY `fk_Usuarios_Administrador1_idx` (`Administrador_Id`),
  CONSTRAINT `fk_Usuarios_Administrador1` FOREIGN KEY (`Administrador_Id`) REFERENCES `administrador` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Usuarios_Catedratico1` FOREIGN KEY (`Catedratico_Id`) REFERENCES `catedratico` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla sbd_inebxela.usuarios: ~1 rows (aproximadamente)
DELETE FROM `usuarios`;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` (`Id`, `NombreUsuario`, `Contrasenia`, `Tipo`, `Catedratico_Id`, `Administrador_Id`) VALUES
	(1, 'prueba', '1234', '1', NULL, 1);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
