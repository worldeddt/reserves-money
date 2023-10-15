-- MariaDB dump 10.19-11.1.2-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: point
-- ------------------------------------------------------
-- Server version       11.1.2-MariaDB-1:11.1.2+maria~ubu2204

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `accumulated_money_history`
--

DROP TABLE IF EXISTS `accumulated_money_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accumulated_money_history` (
                                             `rmh_amount` int(11) NOT NULL DEFAULT 0 COMMENT '적립금 잔액',
                                             `rmh_index` int(11) NOT NULL AUTO_INCREMENT,
                                             `rmh_price` int(11) NOT NULL DEFAULT 0 COMMENT '적립/사용 금액',
                                             `rmh_rmu_index` int(11) NOT NULL,
                                             `register_datetime` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '등록일시',
                                             `update_datetime` datetime DEFAULT '0000-00-00 00:00:00' COMMENT '갱신일시',
                                             `rmh_purpose` varchar(1000) NOT NULL DEFAULT '' COMMENT '적립/사용 목적 및 사유',
                                             `rmh_status` enum('ACCUMUL','USED') NOT NULL DEFAULT 'ACCUMUL' COMMENT '적립/사용 상태 (ACCUMUL:적립, USED:사용)',
                                             PRIMARY KEY (`rmh_index`),
                                             KEY `FK3nsbkoqynqsd2fpwxryqi8pwa` (`rmh_rmu_index`),
                                             CONSTRAINT `FK3nsbkoqynqsd2fpwxryqi8pwa` FOREIGN KEY (`rmh_rmu_index`) REFERENCES `accumulated_money_user` (`rmu_index`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accumulated_money_history`
--

LOCK TABLES `accumulated_money_history` WRITE;
/*!40000 ALTER TABLE `accumulated_money_history` DISABLE KEYS */;
INSERT INTO `accumulated_money_history` VALUES
                                            (0,1,20,1,'2023-09-21 08:14:28','2023-09-21 08:33:02','회원 리뷰 리워드','ACCUMUL'),
                                            (0,2,20,1,'2023-09-21 08:19:34','2023-09-21 09:02:53','회원 리뷰 리워드','ACCUMUL'),
                                            (0,3,20,1,'2023-09-21 08:21:01','2023-09-21 11:01:01','회원 리뷰 리워드','ACCUMUL'),
                                            (0,4,50,1,'2023-09-21 08:33:02','2023-09-21 08:33:02','회원 리뷰 리워드 사용','USED'),
                                            (0,5,20,1,'2023-09-21 08:48:03','2023-09-21 11:01:28','회원 리뷰 리워드','ACCUMUL'),
                                            (0,6,500,1,'2023-09-21 08:49:44','2023-09-21 22:33:19','회원 리뷰 리워드','ACCUMUL'),
                                            (0,7,500,1,'2023-09-21 08:54:22','2023-09-21 22:35:05','회원 리뷰 리워드','ACCUMUL'),
                                            (0,8,100,1,'2023-09-21 09:02:53','2023-09-21 09:02:53','그냥','USED'),
                                            (0,9,500,1,'2023-09-21 09:05:12','2023-09-21 22:36:14','회원 리뷰 리워드','ACCUMUL'),
                                            (0,10,200,1,'2023-09-21 11:01:01','2023-09-21 11:01:01','사용','USED'),
                                            (0,11,120,1,'2023-09-21 11:01:28','2023-09-21 11:01:28','사용','USED'),
                                            (80,12,120,1,'2023-09-21 22:32:49','2023-09-21 22:36:14','리뷰 리워드','ACCUMUL'),
                                            (0,13,120,1,'2023-09-21 22:33:07','2023-09-21 22:33:07','사용','USED'),
                                            (0,14,300,1,'2023-09-21 22:33:19','2023-09-21 22:33:19','사용','USED'),
                                            (0,15,300,1,'2023-09-21 22:34:23','2023-09-21 22:34:23','사용','USED'),
                                            (1000,16,1000,1,'2023-09-21 22:34:59','2023-09-21 22:34:59','리뷰 리워드','ACCUMUL'),
                                            (0,17,600,1,'2023-09-21 22:35:05','2023-09-21 22:35:05','사용','USED'),
                                            (0,18,120,1,'2023-09-21 22:36:14','2023-09-21 22:36:14','사용','USED');
/*!40000 ALTER TABLE `accumulated_money_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `accumulated_money_user`
--

DROP TABLE IF EXISTS `accumulated_money_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accumulated_money_user` (
                                          `rmu_index` int(11) NOT NULL AUTO_INCREMENT,
                                          `register_datetime` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '등록일시',
                                          `rmu_point` bigint(20) NOT NULL DEFAULT 0 COMMENT '유저 포인트',
                                          `update_datetime` datetime DEFAULT '0000-00-00 00:00:00' COMMENT '갱신일시',
                                          `rmu_user_uuid` varchar(1000) NOT NULL DEFAULT '' COMMENT '유저 전용 사용자 식별 번호',
                                          `rmu_id` varchar(50) NOT NULL DEFAULT '' COMMENT '아이디',
                                          `rmu_password` varchar(500) NOT NULL DEFAULT '' COMMENT '비밀번호',
                                          `rmu_status` enum('ACTIVE','DELETED') NOT NULL DEFAULT 'ACTIVE' COMMENT '유저 상태',
                                          PRIMARY KEY (`rmu_index`),
                                          UNIQUE KEY `UK_8soox8hg2sgxg29a4fxqi3w50` (`rmu_user_uuid`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accumulated_money_user`
--

LOCK TABLES `accumulated_money_user` WRITE;
/*!40000 ALTER TABLE `accumulated_money_user` DISABLE KEYS */;
INSERT INTO `accumulated_money_user` VALUES
    (1,'2023-09-21 00:56:03',790,'2023-09-21 22:36:14','1ee57ce3-3542-6c0e-be4a-018eca932050','xodid7986','1111dd2222@','ACTIVE');
/*!40000 ALTER TABLE `accumulated_money_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-15  7:34:17