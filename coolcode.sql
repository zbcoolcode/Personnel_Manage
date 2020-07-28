/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.6.5-m8-log : Database - personnels
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`personnels` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `personnels`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `id` varchar(50) COLLATE utf8_bin NOT NULL,
  `name` varchar(50) COLLATE utf8_bin NOT NULL,
  `password` varchar(50) COLLATE utf8_bin NOT NULL,
  `role` varchar(20) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`,`password`),
  KEY `role_id` (`role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `admin` */

insert  into `admin`(`id`,`name`,`password`,`role`) values ('1111','彬少','12345','admin'),('123','小彬121','123322','admin'),('123456','小芳','123456','admin'),('123457','小泽','123','super'),('123458','小黄','4314124','super');

/*Table structure for table `departments` */

DROP TABLE IF EXISTS `departments`;

CREATE TABLE `departments` (
  `department_id` int(10) NOT NULL AUTO_INCREMENT,
  `department_name` varchar(30) COLLATE utf8_bin NOT NULL,
  `department_manager` varchar(30) COLLATE utf8_bin NOT NULL,
  `department_address` varchar(50) COLLATE utf8_bin NOT NULL,
  `department_count` int(10) DEFAULT '0',
  PRIMARY KEY (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `departments` */

insert  into `departments`(`department_id`,`department_name`,`department_manager`,`department_address`,`department_count`) values (1,'经营管理部门','凯文·杜兰特jjj','创业大厦1层vvv',4),(71,'后勤管理部','怀特','仓库一楼兼1号办公室',2),(72,'技术支持部','小彬','创业大厦12层321',3),(73,'行政部','彬少','12楼办公室',0);

/*Table structure for table `employees` */

DROP TABLE IF EXISTS `employees`;

CREATE TABLE `employees` (
  `department_id` int(10) NOT NULL,
  `employee_id` int(20) NOT NULL,
  `name` varchar(30) COLLATE utf8_bin NOT NULL,
  `gender` varchar(4) COLLATE utf8_bin DEFAULT NULL,
  `hiredate` date DEFAULT NULL,
  `address` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  `phone` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `job_id` int(10) NOT NULL,
  `salary` int(20) DEFAULT NULL,
  `password` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`employee_id`),
  KEY `fk_departments_employees` (`department_id`),
  KEY `fk_jobs_employees` (`job_id`),
  CONSTRAINT `fk_departments_employees` FOREIGN KEY (`department_id`) REFERENCES `departments` (`department_id`),
  CONSTRAINT `fk_jobs_employees` FOREIGN KEY (`job_id`) REFERENCES `jobs` (`job_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `employees` */

insert  into `employees`(`department_id`,`employee_id`,`name`,`gender`,`hiredate`,`address`,`birthdate`,`phone`,`job_id`,`salary`,`password`) values (1,1008,'科西嘉零','女','2020-03-03','广东省广州市','2020-03-01','13117878306',1,2000,'123456'),(71,1021,'丽娜','男','2020-03-04','俄罗斯','2019-10-09','06623-115230',1,10,'12345612'),(71,1022,'欧尼尔','男','2020-03-10','德国','2020-03-17','5033-225233',4,31412412,'123456'),(72,1231,'夏斌','男','2020-04-21','普宁平林','2020-04-09','13117878306',3,5000,'123456'),(1,1232,'bzini ler','男','2020-03-10','广东省广州市','2020-03-04','13117878306',3,52001231,'123456'),(72,1267,'eurake','女','2020-07-08','广东省41','2020-07-16','123454321',2,22222,'root1'),(72,1341,'3414','男','2020-07-06','1314广东普宁431平林','2020-06-30','112233',2,3143141,'123321123'),(1,123112,'bzi2ni ler','1','2020-07-08','广东省广州市','2020-07-09','13117878306',1,5200,'123456'),(1,132455,'bzini ler','男','2020-03-11','广东省广州市','2020-03-18','13117878306',1,31343,'123456');

/*Table structure for table `jobs` */

DROP TABLE IF EXISTS `jobs`;

CREATE TABLE `jobs` (
  `job_id` int(10) NOT NULL AUTO_INCREMENT,
  `job_name` varchar(30) COLLATE utf8_bin NOT NULL,
  `job_count` int(10) DEFAULT '0',
  PRIMARY KEY (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `jobs` */

insert  into `jobs`(`job_id`,`job_name`,`job_count`) values (1,'临时工',8),(2,'实习生',0),(3,'基层员工',0),(4,'中层员工',0),(6,'管理层经理3213213',0);

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(20) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `role` */

insert  into `role`(`role_id`,`role_name`) values (1,'admin'),(2,'superadmin');

/* Trigger structure for table `employees` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `dep_count` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `dep_count` AFTER INSERT ON `employees` FOR EACH ROW BEGIN
    update departments set department_count=department_count+1 where department_id=new.department_id;
    END */$$


DELIMITER ;

/* Trigger structure for table `employees` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `dep_update_counto` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `dep_update_counto` BEFORE UPDATE ON `employees` FOR EACH ROW BEGIN
    UPDATE departments SET department_count =department_count-1 WHERE department_id=old.department_id;
     
    END */$$


DELIMITER ;

/* Trigger structure for table `employees` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `dep_update_countn` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `dep_update_countn` AFTER UPDATE ON `employees` FOR EACH ROW BEGIN
     UPDATE departments SET department_count =department_count+1 WHERE department_id=new.department_id;
    END */$$


DELIMITER ;

/* Trigger structure for table `employees` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `dep_subcount` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `dep_subcount` BEFORE DELETE ON `employees` FOR EACH ROW BEGIN
    UPDATE departments SET department_count=department_count-1 WHERE department_id=old.department_id;
    END */$$


DELIMITER ;

/* Procedure structure for procedure `procedure_departments` */

/*!50003 DROP PROCEDURE IF EXISTS  `procedure_departments` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `procedure_departments`()
BEGIN
	SELECT `department_name`,COUNT(`department_count`) FROM departments
	GROUP BY `department_id`;
END */$$
DELIMITER ;

/* Procedure structure for procedure `procedure_jobs` */

/*!50003 DROP PROCEDURE IF EXISTS  `procedure_jobs` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `procedure_jobs`()
BEGIN
	SELECT `job_name`,COUNT(`job_count`) FROM jobs
	GROUP BY `job_id`;
END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
