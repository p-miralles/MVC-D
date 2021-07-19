/*
SQLyog Ultimate v9.02 
MySQL - 5.6.17 : Database - prog2
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`prog2` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `prog2`;

/*Table structure for table `alumno` */

DROP TABLE IF EXISTS `alumno`;

CREATE TABLE `alumno` (
  `dni` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido` varchar(45) DEFAULT NULL,
  `fechaNac` date DEFAULT NULL,
  `domicilio` varchar(45) DEFAULT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  `codInscripcion` int(11) DEFAULT NULL,
  PRIMARY KEY (`dni`),
  UNIQUE KEY `alu_dni_UNIQUE` (`dni`),
  KEY `FK_inscripcion_idx` (`codInscripcion`),
  CONSTRAINT `FK_inscripcion` FOREIGN KEY (`codInscripcion`) REFERENCES `inscripcion` (`inscCodigo`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `alumno` */

insert  into `alumno`(`dni`,`nombre`,`apellido`,`fechaNac`,`domicilio`,`telefono`,`codInscripcion`) values (2222,'elvio','riva','2010-09-09','Mendoza','1466',4435),(323232,'Roxana','Arenas','2005-01-01','M Garcia 79 - GC','261444777',4437),(10468787,'Juan','Lopez','1954-02-05','San Juan Capital','2622365988',4436),(29777888,'Enzo','Perez','1978-10-09','Bs As 2000','1132659965',4436),(32555444,'Maria','Paz','2000-05-05','Alvear 211','261478964',4436);

/*Table structure for table `carrera` */

DROP TABLE IF EXISTS `carrera`;

CREATE TABLE `carrera` (
  `carreraCod` int(11) NOT NULL,
  `carreraNombre` varchar(45) DEFAULT NULL,
  `carreraDuracion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`carreraCod`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `carrera` */

insert  into `carrera`(`carreraCod`,`carreraNombre`,`carreraDuracion`) values (101,'TU Programacion','2 años'),(546,'Licenciatura en Higiene y Seguridad','3 Años'),(666,'Borreme Profesor','1 año'),(5464,'Licenciatura en Enología','2 Años');

/*Table structure for table `cursado` */

DROP TABLE IF EXISTS `cursado`;

CREATE TABLE `cursado` (
  `cursadoDniAlumno` int(11) DEFAULT NULL,
  `cursadoCodMateria` int(11) DEFAULT NULL,
  `cursadoNota` double DEFAULT NULL,
  `key` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`key`),
  KEY `FK_materia_idx` (`cursadoCodMateria`),
  KEY `FK_alumno_idx` (`cursadoDniAlumno`),
  CONSTRAINT `FK_materia` FOREIGN KEY (`cursadoCodMateria`) REFERENCES `materia` (`matCod`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `cursado` */

insert  into `cursado`(`cursadoDniAlumno`,`cursadoCodMateria`,`cursadoNota`,`key`) values (2222,136,10,1),(2222,137,4,2),(29777888,136,10,3),(29777888,137,7,4);

/*Table structure for table `inscripcion` */

DROP TABLE IF EXISTS `inscripcion`;

CREATE TABLE `inscripcion` (
  `inscCodigo` int(11) NOT NULL,
  `inscNombre` varchar(45) DEFAULT NULL,
  `inscFecha` date DEFAULT NULL,
  `inscCodCarrera` int(11) DEFAULT NULL,
  PRIMARY KEY (`inscCodigo`),
  KEY `FK_carrera_idx` (`inscCodCarrera`),
  CONSTRAINT `FK_carrera` FOREIGN KEY (`inscCodCarrera`) REFERENCES `carrera` (`carreraCod`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `inscripcion` */

insert  into `inscripcion`(`inscCodigo`,`inscNombre`,`inscFecha`,`inscCodCarrera`) values (4435,'Ingreso 2010','2010-09-09',546),(4436,'Pre 2020','2020-01-01',546),(4437,'Ingreso 2021','2010-09-09',101);

/*Table structure for table `materia` */

DROP TABLE IF EXISTS `materia`;

CREATE TABLE `materia` (
  `matCod` int(11) NOT NULL,
  `matNom` varchar(45) DEFAULT NULL,
  `matProfDNI` int(11) DEFAULT NULL,
  PRIMARY KEY (`matCod`),
  KEY `FK_profesor_idx` (`matProfDNI`),
  CONSTRAINT `FK_profesor` FOREIGN KEY (`matProfDNI`) REFERENCES `profesor` (`dni`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `materia` */

insert  into `materia`(`matCod`,`matNom`,`matProfDNI`) values (136,'Programacion',1111),(137,'Lab.Comp 2',1111),(138,'Matemática',32117222),(140,'Estadística',32117222);

/*Table structure for table `profesor` */

DROP TABLE IF EXISTS `profesor`;

CREATE TABLE `profesor` (
  `dni` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido` varchar(45) DEFAULT NULL,
  `fechaNac` varchar(45) DEFAULT NULL,
  `domicilio` varchar(45) DEFAULT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`dni`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `profesor` */

insert  into `profesor`(`dni`,`nombre`,`apellido`,`fechaNac`,`domicilio`,`telefono`) values (1111,'Alberto','Rodriguez','2010-09-09','Derqui 125 - GC','264464646'),(32117222,'Eusebio','Barcala','2010-09-09','Barraquero 100 - Mza.','2616333999'),(35133453,'Andrea','Arenas','2010-09-09','MB C11 Urquiza Gllen','26178987854');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
