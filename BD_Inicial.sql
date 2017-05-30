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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla sbd_inebxela.administrador: ~0 rows (aproximadamente)
DELETE FROM `administrador`;
/*!40000 ALTER TABLE `administrador` DISABLE KEYS */;
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
  CONSTRAINT `fk_Curso_has_CicloEscolar_Curso1` FOREIGN KEY (`Curso_Id`) REFERENCES `curso` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Curso_has_CicloEscolar_CicloEscolar1` FOREIGN KEY (`CicloEscolar_Id`) REFERENCES `cicloescolar` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Componentes_Grado1` FOREIGN KEY (`Grado_Id`) REFERENCES `grado` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Componentes_Catedratico1` FOREIGN KEY (`Catedratico_Id`) REFERENCES `catedratico` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla sbd_inebxela.asignacioncat: ~0 rows (aproximadamente)
DELETE FROM `asignacioncat`;
/*!40000 ALTER TABLE `asignacioncat` DISABLE KEYS */;
/*!40000 ALTER TABLE `asignacioncat` ENABLE KEYS */;


-- Volcando estructura para tabla sbd_inebxela.asignacionest
DROP TABLE IF EXISTS `asignacionest`;
CREATE TABLE IF NOT EXISTS `asignacionest` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `CicloEscolar_Id` int(11) NOT NULL,
  `Grado_Id` int(11) NOT NULL,
  `Estudiante_Id` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk_CicloEscolar_has_Grado_Grado1_idx` (`Grado_Id`),
  KEY `fk_CicloEscolar_has_Grado_CicloEscolar1_idx` (`CicloEscolar_Id`),
  KEY `fk_Asignacion_Estudiante1_idx` (`Estudiante_Id`),
  CONSTRAINT `fk_CicloEscolar_has_Grado_CicloEscolar1` FOREIGN KEY (`CicloEscolar_Id`) REFERENCES `cicloescolar` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_CicloEscolar_has_Grado_Grado1` FOREIGN KEY (`Grado_Id`) REFERENCES `grado` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Asignacion_Estudiante1` FOREIGN KEY (`Estudiante_Id`) REFERENCES `estudiante` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla sbd_inebxela.asignacionest: ~0 rows (aproximadamente)
DELETE FROM `asignacionest`;
/*!40000 ALTER TABLE `asignacionest` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla sbd_inebxela.catedratico: ~0 rows (aproximadamente)
DELETE FROM `catedratico`;
/*!40000 ALTER TABLE `catedratico` DISABLE KEYS */;
/*!40000 ALTER TABLE `catedratico` ENABLE KEYS */;


-- Volcando estructura para tabla sbd_inebxela.cicloescolar
DROP TABLE IF EXISTS `cicloescolar`;
CREATE TABLE IF NOT EXISTS `cicloescolar` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Anio` varchar(4) NOT NULL,
  `Listo` tinyint(1) DEFAULT '0',
  `Cerrado` tinyint(1) DEFAULT '0',
  `CantidadEstudiantes` int(11) DEFAULT '0',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla sbd_inebxela.cicloescolar: ~0 rows (aproximadamente)
DELETE FROM `cicloescolar`;
/*!40000 ALTER TABLE `cicloescolar` DISABLE KEYS */;
/*!40000 ALTER TABLE `cicloescolar` ENABLE KEYS */;


-- Volcando estructura para tabla sbd_inebxela.curso
DROP TABLE IF EXISTS `curso`;
CREATE TABLE IF NOT EXISTS `curso` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla sbd_inebxela.curso: ~0 rows (aproximadamente)
DELETE FROM `curso`;
/*!40000 ALTER TABLE `curso` DISABLE KEYS */;
/*!40000 ALTER TABLE `curso` ENABLE KEYS */;


-- Volcando estructura para tabla sbd_inebxela.encargado
DROP TABLE IF EXISTS `encargado`;
CREATE TABLE IF NOT EXISTS `encargado` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nombres` varchar(25) DEFAULT NULL,
  `Apellidos` varchar(20) DEFAULT NULL,
  `Direccion` varchar(75) DEFAULT NULL,
  `DPI` varchar(13) DEFAULT NULL,
  `FechaNacimiento` date DEFAULT NULL,
  `Trabajo` varchar(25) DEFAULT NULL,
  `Municipio_Id` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk_Encargado_Municipio1_idx` (`Municipio_Id`),
  CONSTRAINT `fk_Encargado_Municipio1` FOREIGN KEY (`Municipio_Id`) REFERENCES `municipio` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla sbd_inebxela.encargado: ~0 rows (aproximadamente)
DELETE FROM `encargado`;
/*!40000 ALTER TABLE `encargado` DISABLE KEYS */;
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
  `RelacionEncargado` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk_Estudiante_Municipio1_idx` (`Municipio_Id`),
  KEY `fk_Estudiante_Encargado1_idx` (`Encargado_Id`),
  CONSTRAINT `fk_Estudiante_Municipio1` FOREIGN KEY (`Municipio_Id`) REFERENCES `municipio` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Estudiante_Encargado1` FOREIGN KEY (`Encargado_Id`) REFERENCES `encargado` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla sbd_inebxela.estudiante: ~0 rows (aproximadamente)
DELETE FROM `estudiante`;
/*!40000 ALTER TABLE `estudiante` DISABLE KEYS */;
/*!40000 ALTER TABLE `estudiante` ENABLE KEYS */;


-- Volcando estructura para tabla sbd_inebxela.grado
DROP TABLE IF EXISTS `grado`;
CREATE TABLE IF NOT EXISTS `grado` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(10) DEFAULT NULL,
  `Seccion` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla sbd_inebxela.grado: ~0 rows (aproximadamente)
DELETE FROM `grado`;
/*!40000 ALTER TABLE `grado` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla sbd_inebxela.libro: ~0 rows (aproximadamente)
DELETE FROM `libro`;
/*!40000 ALTER TABLE `libro` DISABLE KEYS */;
/*!40000 ALTER TABLE `libro` ENABLE KEYS */;


-- Volcando estructura para función sbd_inebxela.modificarUsuario
DROP FUNCTION IF EXISTS `modificarUsuario`;
DELIMITER //
CREATE DEFINER=`root`@`localhost` FUNCTION `modificarUsuario`(_id INT, _anteriorContrasenia VARCHAR(25), _nuevaContrasenia VARCHAR(10), _cambiarNombre INT, _nuevoNombre VARCHAR(15)) RETURNS int(11)
BEGIN
	DECLARE vExisteRegistro INT DEFAULT 1;
	DECLARE vContraseniaEncript VARCHAR(25);
	DECLARE vResultado INT DEFAULT 1;
	DECLARE vContraseniaActual VARCHAR(50);
	DECLARE cUsuario CURSOR FOR SELECT Contrasenia FROM Usuarios WHERE Id = _id;
	DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET vExisteRegistro = 0;
	
	OPEN cUsuario;
	SELECT VALOR INTO vContraseniaEncript FROM variables_globales.variables_globales WHERE NOMBRE = 'InebXela_Password';
	
	FETCH cUsuario INTO vContraseniaActual;
	IF (vExisteRegistro = 1) THEN
		-- Comparo si las contraseñas son iguales
		IF (vContraseniaActual = AES_ENCRYPT(_anteriorContrasenia, vContraseniaEncript)) THEN
			-- Procedo a la actualización del registro
			IF (_cambiarNombre = 1) THEN
				UPDATE Usuarios SET NombreUsuario = _nuevoNombre, Contrasenia = AES_ENCRYPT(_nuevaContrasenia, vContraseniaEncript) WHERE Id = _id;
			ELSE
				UPDATE Usuarios SET Contrasenia = AES_ENCRYPT(_nuevaContrasenia, vContraseniaEncript) WHERE Id = _id;
			END IF;
		ELSE
			SET vResultado = 0;
		END IF;
	ELSE
		SET vResultado = -1;
	END IF;
	-- Los valores de retorno para vResultado son:
	--		-1: Si el Id del registro que se quiere modificar (_id) no existe en la tabla Usuarios
	--		 0: Si la Contrasenia del registro que se quiere modificar no coincide con _anteriorContrasenia
	-- 	 1: Si el cambio se realiza con éxito
	RETURN vResultado;
END//
DELIMITER ;


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
  `Nota1` float DEFAULT '-1',
  `Nota2` float DEFAULT '-1',
  `Nota3` float DEFAULT '-1',
  `Nota4` float DEFAULT '-1',
  `NotaRecuperacion` float DEFAULT '-1',
  `NotaFinal` float DEFAULT '-1',
  PRIMARY KEY (`Id`),
  KEY `fk_Estudiante_has_Curso_Curso1_idx` (`Curso_Id`),
  KEY `fk_Estudiante_has_Curso_Estudiante1_idx` (`Estudiante_Id`),
  KEY `fk_Notas_AsignacionEST1_idx` (`AsignacionEST_Id`),
  CONSTRAINT `fk_Estudiante_has_Curso_Estudiante1` FOREIGN KEY (`Estudiante_Id`) REFERENCES `estudiante` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Estudiante_has_Curso_Curso1` FOREIGN KEY (`Curso_Id`) REFERENCES `curso` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Notas_AsignacionEST1` FOREIGN KEY (`AsignacionEST_Id`) REFERENCES `asignacionest` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla sbd_inebxela.notas: ~0 rows (aproximadamente)
DELETE FROM `notas`;
/*!40000 ALTER TABLE `notas` DISABLE KEYS */;
/*!40000 ALTER TABLE `notas` ENABLE KEYS */;


-- Volcando estructura para función sbd_inebxela.nuevoUsuario
DROP FUNCTION IF EXISTS `nuevoUsuario`;
DELIMITER //
CREATE DEFINER=`root`@`localhost` FUNCTION `nuevoUsuario`(_nombreUsuario VARCHAR(25), _contraseniaUsuario VARCHAR(25), _tipo INT, _catedraticoId INT, _administradorId INT) RETURNS int(11)
BEGIN
	DECLARE vContraseniaEncript VARCHAR(25);
	DECLARE vContExistentes INT;
	DECLARE vUsuarioCreado INT DEFAULT -1;
	SELECT VALOR INTO vContraseniaEncript FROM variables_globales.variables_globales WHERE NOMBRE = 'InebXela_Password';
	-- Debido a que la comparación de cadenas es insensible a mayúsculas y minúsculas, una opción es encriptar tanto el
	-- nombre del Nuevo Usuario y los existentes y comparar dicho valor encriptado (que para este caso, es sensible en la comparación)
	-- Obtengo la cantidad de registros que coincidan (dicho valor puede ser 0 o 1)
	SELECT COUNT(*) INTO vContExistentes FROM Usuarios WHERE AES_ENCRYPT(NombreUsuario, vContraseniaEncript) = AES_ENCRYPT(_nombreUsuario, vContraseniaEncript);
	IF (vContExistentes = 0) THEN
	-- Si la cuenta es para un Administrador, _administradorId deber ser != 0; en caso contrario _catedraticoId debe ser != 0
		IF (_administradorId != 0) THEN	-- Se creará una cuenta para un Administrador
			INSERT INTO Usuarios(NombreUsuario, Contrasenia, Tipo, Administrador_Id) VALUES
				(_nombreUsuario, AES_ENCRYPT(_contraseniaUsuario, vContraseniaEncript), _tipo, _administradorId);
		ELSE	-- Se creará una cuenta para un Catedrático
			INSERT INTO Usuarios(NombreUsuario, Contrasenia, Tipo, Catedratico_Id) VALUES
				(_nombreUsuario, AES_ENCRYPT(_contraseniaUsuario, vContraseniaEncript), _tipo, _catedraticoId);
		END IF;
		SET vUsuarioCreado = 1;	-- En cualquier caso, se crea la cuenta de Usuario
	END IF;
	RETURN vUsuarioCreado;
	-- Retorna 1 si el Usuario es creado, y -1 en caso de que ya exista uno con el mismo nombre
END//
DELIMITER ;


-- Volcando estructura para procedimiento sbd_inebxela.obtenerListaUsuarios
DROP PROCEDURE IF EXISTS `obtenerListaUsuarios`;
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerListaUsuarios`(_usuarioAdmin VARCHAR(25), _contraseniaAdmin VARCHAR(25))
BEGIN
	DECLARE vExisteRegistro INT;
	DECLARE vContraseniaEncript VARCHAR(25);
	DECLARE vIdAdmin INT;
	SELECT VALOR INTO vContraseniaEncript FROM variables_globales.variables_globales WHERE NOMBRE = 'InebXela_Password';
	SELECT COUNT(Id) INTO vExisteRegistro FROM Usuarios WHERE AES_ENCRYPT(NombreUsuario, vContraseniaEncript) = AES_ENCRYPT(_usuarioAdmin, vContraseniaEncript) AND Contrasenia = AES_ENCRYPT(_contraseniaAdmin, vContraseniaEncript) AND Tipo = 1;
	
	IF (vExisteRegistro != 0) THEN	-- Si el Usuario o Contraseña del Administrador es correcto.
		SELECT Id, NombreUsuario, AES_DECRYPT(Contrasenia, vContraseniaEncript) Contrasenia, Tipo, Catedratico_Id, Administrador_Id FROM Usuarios;
	ELSE	-- Si el Usuario o Contraseña es incorrecto: realizo una consulta que nunca me devolverá registro alguno
		SELECT Id, NombreUsuario, AES_DECRYPT(Contrasenia, vContraseniaEncript) Contrasenia, Tipo, Catedratico_Id, Administrador_Id FROM Usuarios WHERE Tipo = 5;
	END IF;
END//
DELIMITER ;


-- Volcando estructura para procedimiento sbd_inebxela.obtenerUsuario
DROP PROCEDURE IF EXISTS `obtenerUsuario`;
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerUsuario`(_usuario VARCHAR(15), _contrasenia VARCHAR(25))
BEGIN
	DECLARE vContraseniaEncript VARCHAR(25);
	SELECT VALOR INTO vContraseniaEncript FROM variables_globales.variables_globales WHERE NOMBRE = 'InebXela_Password';
	SELECT * FROM Usuarios WHERE AES_ENCRYPT(NombreUsuario, vContraseniaEncript) = AES_ENCRYPT(_usuario, vContraseniaEncript) AND Contrasenia = AES_ENCRYPT(_contrasenia, vContraseniaEncript);
END//
DELIMITER ;


-- Volcando estructura para tabla sbd_inebxela.paquetelibro
DROP TABLE IF EXISTS `paquetelibro`;
CREATE TABLE IF NOT EXISTS `paquetelibro` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Codigo` varchar(5) DEFAULT NULL,
  `Descripcion` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla sbd_inebxela.paquetelibro: ~0 rows (aproximadamente)
DELETE FROM `paquetelibro`;
/*!40000 ALTER TABLE `paquetelibro` DISABLE KEYS */;
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
  CONSTRAINT `fk_PaqueteLibro_has_Estudiante_PaqueteLibro1` FOREIGN KEY (`PaqueteLibro_Id`) REFERENCES `paquetelibro` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_PaqueteLibro_has_Estudiante_Estudiante1` FOREIGN KEY (`Estudiante_Id`) REFERENCES `estudiante` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
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
  `AsignacionEST_Id` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk_Reporte_AsignacionEST1_idx` (`AsignacionEST_Id`),
  CONSTRAINT `fk_Reporte_AsignacionEST1` FOREIGN KEY (`AsignacionEST_Id`) REFERENCES `asignacionest` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
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
  CONSTRAINT `fk_Teléfono_Catedrático1` FOREIGN KEY (`Catedratico_Id`) REFERENCES `catedratico` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Teléfono_Administrador1` FOREIGN KEY (`Administrador_Id`) REFERENCES `administrador` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Telefono_Encargado1` FOREIGN KEY (`Encargado_Id`) REFERENCES `encargado` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla sbd_inebxela.telefono: ~0 rows (aproximadamente)
DELETE FROM `telefono`;
/*!40000 ALTER TABLE `telefono` DISABLE KEYS */;
/*!40000 ALTER TABLE `telefono` ENABLE KEYS */;


-- Volcando estructura para tabla sbd_inebxela.usuarios
DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE IF NOT EXISTS `usuarios` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `NombreUsuario` varchar(15) DEFAULT NULL,
  `Contrasenia` blob NOT NULL,
  `Tipo` varchar(1) DEFAULT NULL,
  `Catedratico_Id` int(11) DEFAULT NULL,
  `Administrador_Id` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk_Usuarios_Catedratico1_idx` (`Catedratico_Id`),
  KEY `fk_Usuarios_Administrador1_idx` (`Administrador_Id`),
  CONSTRAINT `fk_Usuarios_Catedratico1` FOREIGN KEY (`Catedratico_Id`) REFERENCES `catedratico` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Usuarios_Administrador1` FOREIGN KEY (`Administrador_Id`) REFERENCES `administrador` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla sbd_inebxela.usuarios: ~0 rows (aproximadamente)
DELETE FROM `usuarios`;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;


-- Volcando estructura para disparador sbd_inebxela.crearAsignacion
DROP TRIGGER IF EXISTS `crearAsignacion`;
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `crearAsignacion` AFTER INSERT ON `asignacionest` FOR EACH ROW BEGIN
	DECLARE vUltimaFila INT DEFAULT 0;
	DECLARE vIdCiclo INT;
	DECLARE vIdGrado INT;
	DECLARE vIdCurso INT;
	-- El cursor se cargará con todos los Cursos correspondientes al Ciclo Escolar y Grado al que se asigna el estudiante
	DECLARE cCursos CURSOR FOR SELECT CicloEscolar.Id idCicloEscolar, Grado.Id idGrado, Curso.Id idCurso FROM CicloEscolar
		INNER JOIN AsignacionCAT ON CicloEscolar.Id = AsignacionCAT.CicloEscolar_Id
		INNER JOIN Grado ON AsignacionCAT.Grado_Id = Grado.Id
		INNER JOIN Curso ON AsignacionCAT.Curso_Id = Curso.Id
		WHERE CicloEscolar_Id = NEW.CicloEscolar_Id AND Grado_Id = NEW.Grado_Id;
	DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET vUltimaFila = 1;
	
	OPEN cCursos;
	
	FETCH cCursos INTO vIdCiclo, vIdGrado, vIdCurso;
	WHILE NOT vUltimaFila DO
		-- Creación de la Nota para el i-ésimo curso
		INSERT INTO Notas(AsignacionEST_Id, Estudiante_Id, Curso_Id) VALUES(NEW.Id, NEW.Estudiante_Id, vIdCurso);
		FETCH cCursos INTO vIdCiclo, vIdGrado, vIdCurso;
	END WHILE;
	
	CLOSE cCursos;
	
	-- Ahora aumento el contador de Cantidad de Estudiantes en CicloEscolar
	UPDATE CicloEscolar SET CantidadEstudiantes = CantidadEstudiantes + 1 WHERE Id = NEW.CicloEscolar_Id;
	
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;


-- Volcando estructura de base de datos para variables_globales
DROP DATABASE IF EXISTS `variables_globales`;
CREATE DATABASE IF NOT EXISTS `variables_globales` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `variables_globales`;


-- Volcando estructura para tabla variables_globales.variables_globales
DROP TABLE IF EXISTS `variables_globales`;
CREATE TABLE IF NOT EXISTS `variables_globales` (
  `NOMBRE` varchar(25) DEFAULT NULL,
  `VALOR` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla variables_globales.variables_globales: ~1 rows (aproximadamente)
DELETE FROM `variables_globales`;
/*!40000 ALTER TABLE `variables_globales` DISABLE KEYS */;
INSERT INTO `variables_globales` (`NOMBRE`, `VALOR`) VALUES
	('InebXela_Password', 'InebXelaQuetgo');
/*!40000 ALTER TABLE `variables_globales` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
