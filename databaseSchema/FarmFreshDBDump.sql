CREATE DATABASE  IF NOT EXISTS `farmadmin` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `farmadmin`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: farmadmin
-- ------------------------------------------------------
-- Server version	5.5.53

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `userpass`
--

DROP TABLE IF EXISTS `userpass`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userpass` (
  `Username` varchar(50) NOT NULL,
  `Password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userpass`
--

LOCK TABLES `userpass` WRITE;
/*!40000 ALTER TABLE `userpass` DISABLE KEYS */;
INSERT INTO `userpass` VALUES ('amy.freelance.dev@gmail.com','password'),('amy.loughlin.radtke@gmail.com','password'),('a_new_one@gmail.com','11111'),('jones_family@verizon.net','password'),('person2@gmail.com','password'),('radtke.purchases@gmail.com','password'),('radtke2_family@verizon.net','pass'),('radtke3_family@verizon.net','A'),('radtke4_family@verizon.net','4'),('radtke7_family@verizon.net','7'),('radtke9_family@verizon.net',''),('radtke_family2@verizon.net','aaa'),('sally_family@verizon.net','password'),('test2@gmail.com','password');
/*!40000 ALTER TABLE `userpass` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userrole`
--

DROP TABLE IF EXISTS `userrole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userrole` (
  `Username` varchar(50) NOT NULL,
  `Rolename` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Username`),
  KEY `fk_UserRole_UserPass_idx` (`Username`,`Rolename`),
  CONSTRAINT `fk_UserRole_UserPass` FOREIGN KEY (`Username`) REFERENCES `userpass` (`Username`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userrole`
--

LOCK TABLES `userrole` WRITE;
/*!40000 ALTER TABLE `userrole` DISABLE KEYS */;
INSERT INTO `userrole` VALUES ('amy.freelance.dev@gmail.com','super_user'),('amy.loughlin.radtke@gmail.com','admin'),('a_new_one@gmail.com','user'),('jones_family@verizon.net','user'),('person2@gmail.com','user'),('radtke.purchases@gmail.com','user'),('radtke2_family@verizon.net','user'),('radtke3_family@verizon.net','user'),('radtke4_family@verizon.net','user'),('radtke7_family@verizon.net','user'),('radtke9_family@verizon.net','user'),('radtke_family2@verizon.net','user'),('sally_family@verizon.net','user'),('test2@gmail.com','user');
/*!40000 ALTER TABLE `userrole` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-20  8:42:49
CREATE DATABASE  IF NOT EXISTS `farmfresh` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `farmfresh`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: farmfresh
-- ------------------------------------------------------
-- Server version	5.5.53

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invoice` (
  `InvoiceID` int(11) NOT NULL AUTO_INCREMENT,
  `UserID` int(11) NOT NULL,
  `InvoiceDate` datetime NOT NULL,
  `TotalAmount` double(8,2) DEFAULT NULL,
  `IsProcessed` char(1) NOT NULL DEFAULT 'n' COMMENT 'The Invoice has been paid for at this point - checkout has been complete.\nInProcess is false at this point.  IsProcessed will be set to true after the items in\nthe invoice have been packaged up and shipped.',
  PRIMARY KEY (`InvoiceID`),
  KEY `fk_Invoice_User_idx` (`UserID`),
  CONSTRAINT `fk_Invoice_User1` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
INSERT INTO `invoice` VALUES (1,1,'2016-11-11 01:11:54',50.00,'y'),(2,1,'2016-11-12 15:22:02',20.00,'y'),(3,1,'2016-11-11 15:22:33',30.00,'y'),(4,2,'2016-11-11 01:01:50',12.00,'y'),(5,4,'2017-02-26 21:49:07',1.24,'y'),(6,4,'2017-02-27 13:10:17',6.48,'y'),(7,4,'2017-02-27 17:18:38',1.24,'y'),(8,4,'2017-03-01 20:46:04',1.24,'y'),(9,4,'2017-03-02 08:49:08',2.80,'y'),(10,4,'2017-03-02 08:55:40',1.24,'y'),(11,4,'2017-03-02 09:13:35',1.24,'y'),(12,17,'2017-03-02 10:36:02',1.24,'n'),(13,17,'2017-03-02 10:39:10',28.00,'n'),(14,17,'2017-03-06 08:37:54',37.80,'n'),(15,4,'2017-03-07 09:19:47',16.08,'y'),(16,23,'2017-03-15 15:42:43',2.44,'y'),(17,5,'2017-03-15 18:02:11',3.54,'y'),(18,23,'2017-03-16 10:42:50',2.48,'n'),(19,23,'2017-03-16 15:28:09',3.72,'n'),(20,17,'2017-03-16 15:48:46',5.00,'y'),(21,14,'2017-03-16 15:50:23',1.24,'y'),(22,28,'2017-03-16 21:48:38',1.00,'n'),(23,23,'2017-03-16 23:06:06',1.10,'n'),(24,23,'2017-03-16 23:20:16',2.00,'n'),(25,23,'2017-03-16 23:28:37',1.24,'n'),(26,23,'2017-03-16 23:37:41',1.24,'n'),(27,31,'2017-03-16 23:49:18',1.50,'n');
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lineitem`
--

DROP TABLE IF EXISTS `lineitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lineitem` (
  `InvoiceID` int(11) NOT NULL,
  `ProductID` int(11) NOT NULL,
  `Quantity` int(11) NOT NULL,
  PRIMARY KEY (`InvoiceID`,`ProductID`),
  KEY `fk_LineItem_Product1_idx` (`ProductID`),
  KEY `fk_LineItem_Invoice1_idx` (`InvoiceID`),
  CONSTRAINT `fk_LineItem_Invoice1` FOREIGN KEY (`InvoiceID`) REFERENCES `invoice` (`InvoiceID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_LineItem_Product` FOREIGN KEY (`ProductID`) REFERENCES `product` (`ProductID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lineitem`
--

LOCK TABLES `lineitem` WRITE;
/*!40000 ALTER TABLE `lineitem` DISABLE KEYS */;
INSERT INTO `lineitem` VALUES (1,1,1),(1,2,2),(2,3,1),(3,2,2),(3,4,2),(4,1,1),(4,2,2),(4,4,4),(5,1,1),(6,1,2),(6,4,6),(6,7,10),(7,1,1),(8,1,1),(9,8,8),(9,9,5),(9,12,2),(10,1,1),(11,1,1),(12,1,1),(13,2,10),(13,3,10),(13,4,10),(14,1,20),(14,2,10),(14,8,10),(15,1,12),(15,2,1),(16,1,1),(16,2,1),(17,1,1),(17,2,1),(17,3,1),(18,1,2),(19,1,3),(20,4,10),(21,1,1),(22,8,10),(23,3,1),(24,9,10),(25,1,1),(26,1,1),(27,10,5);
/*!40000 ALTER TABLE `lineitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `ProductID` int(11) NOT NULL AUTO_INCREMENT,
  `ProductTypeID` int(11) NOT NULL,
  `ProductCode` varchar(45) NOT NULL,
  `Name` varchar(45) DEFAULT NULL,
  `Description` varchar(1000) DEFAULT NULL,
  `ImageID` varchar(45) DEFAULT NULL,
  `Price` decimal(8,2) DEFAULT NULL,
  `InSeason` char(1) DEFAULT NULL,
  PRIMARY KEY (`ProductID`,`ProductTypeID`),
  UNIQUE KEY `ProductCode_UNIQUE` (`ProductCode`),
  KEY `fk_Product_ProductType1_idx` (`ProductTypeID`),
  CONSTRAINT `fk_Product_ProductType1` FOREIGN KEY (`ProductTypeID`) REFERENCES `producttype` (`ProductTypeID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,1,'AAA-001','Sweet Polly Watermelon (each)','\'Sweet Polly\' is a medium-large triploid with exceptional rind, appearance, and flesh qualities. Its flesh is dark red, firm, and the plants produce a strong vine. It has small pips. It has resistance to Fusarium Wilt and Anthracnose Race 1. \'Sweet Polly\' produces mostly 45 count','watermelon.jpg',1.24,'y'),(2,1,'AAA-002','Madison Peach (each)','\'Madison Peach\' is a red/yellow juicy sweet freestone fruit with yellow flesh.','peach.jpg',1.20,'n'),(3,1,'AAA-003','Blue Berries (1 pint)','Blueberries grow on low creeping shrubs or tall erect bushes, depending upon variety. The small round berries can range in size from 5-16 millimeters in diameter. They first appear green, but ripen into a deep shade of dusty blue. The soft, hazy white coating that develops on the skins\' surface, which is known as the bloom, is a natural waterproofing which helps protect the berries from the sun and other natural elements. Blueberries have a sweet and woodsy flavor with an acidity that can vary depending upon growing conditions. Long sunny days and warm temperatures develop a higher sugar content, while cooler temperatures and shorter days with limited sunlight increase acidity. After harvest some plants lose their leaves while other varieties retain their foliage year-round, becoming a colorful mix ','blueberry.jpg',1.10,'y'),(4,1,'AAA-004','Black Berries (1 pint)','Blackberries are characterized by their coloring, their unique composition and their flavor. Like raspberries, Blackberries are not technically a berry, but rather an aggregate fruit of individual drupes held together by very fine, nearly invisible hairs. Blackberries do not have a hollow center, instead they have a solid, edible core. When ripe, Blackberries have a deep inky sheen with purple highlights. They are succulent, soft, and juicy. Their flavor is sweet, slightly tart, with earthy undertones. ','blackberry.jpg',0.50,'y'),(5,1,'AAA-005','Strawberries (1 pint)','Strawberries are distinguished by their conical heart shape as well as the texture of their skin and their flavor. All varieties of strawberries have seeds on the skin rather than skin around the seed, which distinguishes them from a berry and a true fruit. The texture of any given strawberry is tender firm when ripe with varied levels of succulence. Leaner strawberries will have a semi cottony mouthfeel while sweeter varieties, with high sugar content, will create a more mouthwatering experience. Flavors also range anywhere from sweet-tart to overtly syrup-sweet. ','strawberry.jpg',0.45,'y'),(6,1,'AAA-006','Raspberries (1 pint)','Raspberries are distinguished by their flavor, size, shape and their hollow core. They are an aggregate fruit with individual drupelets that are held together by very fine, nearly invisible hairs. The fruits are petite, roughly oval, plump and vary in flavor from sweet-tart to low acid depending on growing region and coloring. The hollow core is created when the raspberry is separated from its growing receptacle. ','raspberry.jpg',0.55,'n'),(7,1,'AAA-007','Red Delishious  (each)','Red Delicious apples are bright to deep red in color, oftentimes speckled with faint white lenticels (spots). Its creamy white flesh is slightly crisp and dense offering a mildly sweet flavor and slightly flora aroma. ','redDelishApple.jpg',0.10,'y'),(8,2,'AAB-001','Brocolli (1 lb)','Broccoli grows like a tree with a thick, edible trunk that sprouts leaves, or branches and clusters of small, tight flower heads that turn bright green when cooked. Once fully mature or bolting, the flower buds will sprout golden yellow edible flowers. ','brocolli.jpg',0.10,'y'),(9,2,'AAB-002','Brussel Sprouts (1 lb)','Brussels sprouts are compact rounded leaves tightly bound into individual spherical-shaped heads ranging in diameter of one to two inches when mature. Their leaves range from sea green to fern green, some varieties featuring blushed violet red tips. They offer the flavors of the earth and the bitter sweetness of cabbage. The younger the Brussels sprouts carry a sweeter more palatable flavor. ','brusselSprouts.jpg',0.20,'y'),(10,2,'AAB-003','Carrots (1 lb)','Carrots can be shades of orange, yellow, purple and white. Their flesh is snappy and crisp. Their flavors, quintessentially earthy and sweet with notes of celery. Though carrots are most often found trimmed of their thin, dill-like foliage, their greens are equally edible, with herbaceous carrot and parsley undertones. ','carrots.jpg',0.30,'n'),(11,2,'AAB-004','Celery (bunch)','Most likely the Pascal or Utah varieties, celery can grow to optimal heights from 18\" to 24\". It has wide parsley-like green leaves and thick, juicy, ribbed stalks that join at a common base above the root. Celery, at its best, has a juicy and crunchy flesh with a mild salty flavor. Although celery is most often used for its stalks, its leaves are edible as well and have a concentrated celery-flavor. ','celery.jpg',0.40,'n'),(12,2,'AAB-005','Cucumbers (1 lb)','Cucumbers are cylindrical in shape, around 6-8 inches in size and have a dark green skin. The skin is somewhat ribbed in appearance with small bumps and nodes. The skin of a Cucumber can be thick, depending on the variety, but not thick enough to require more than a vegetable peeler to remove. A Cucumber’s flesh is light green with pale, edible seeds. The flesh has a 96% moisture content. The entire Cucumber is edible. ','cucumber.jpg',0.50,'y');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producttype`
--

DROP TABLE IF EXISTS `producttype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `producttype` (
  `ProductTypeID` int(11) NOT NULL AUTO_INCREMENT,
  `ProductTypeName` varchar(45) DEFAULT NULL,
  `OrderNumber` int(11) DEFAULT NULL,
  PRIMARY KEY (`ProductTypeID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producttype`
--

LOCK TABLES `producttype` WRITE;
/*!40000 ALTER TABLE `producttype` DISABLE KEYS */;
INSERT INTO `producttype` VALUES (1,'Fruits',1),(2,'Veggies',2),(3,'Baked Goods',4),(4,'Floral Items',3);
/*!40000 ALTER TABLE `producttype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `UserID` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(25) DEFAULT NULL,
  `LastName` varchar(25) DEFAULT NULL,
  `Email` varchar(100) NOT NULL,
  `CompanyName` varchar(100) DEFAULT NULL,
  `Address1` varchar(45) DEFAULT NULL,
  `Address2` varchar(45) DEFAULT NULL,
  `City` varchar(45) DEFAULT NULL,
  `State` varchar(25) DEFAULT NULL,
  `Zip` varchar(25) DEFAULT NULL,
  `Country` varchar(25) DEFAULT NULL,
  `CreditCardType` varchar(25) DEFAULT NULL,
  `CreditCardNumber` varchar(25) DEFAULT NULL,
  `CreditCardExpMonth` varchar(2) DEFAULT NULL,
  `CreditCardExpYear` varchar(4) DEFAULT NULL,
  `SubscribedToNewsletter` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`UserID`),
  UNIQUE KEY `Email_UNIQUE` (`Email`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Ann','Martin','ann@gmail.com','Company 1','1432 Street1','street2','leesburg','va','20176','usa','visa','111-111-1111','06','2018','\0'),(2,'Bob ','Allen','bob@gmail.com','Company 2','2nd street1','2nd street2','norfolk','va','20111','usa','Master Card','222-222-2222','07','2019','\0'),(3,'Sandy','Schmidt','sandy@gmail.com','NULL','NULL','NULL','NULL','NULL','NULL','NULL','NULL','NULL',NULL,NULL,'\0'),(4,'Amy','Radtke','radtke_family@verizon.net','ABC','12401 Eden Lane','','Woodbridge','VA','20176','United States','Visa','11','05','2018','\0'),(5,'Radtke','Purchases','radtke.purchases@gmail.com','','5422 Ellen Rd','','Manassas','VA','20111','USA','Mastercard','2321','05','2018','\0'),(8,'Test','Person','test@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'\0'),(10,NULL,NULL,'not-there@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'\0'),(11,NULL,NULL,'NewOne@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,''),(12,NULL,NULL,'amy@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,''),(13,NULL,NULL,'bobby@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'\0'),(14,'Super','User','amy.freelance.dev@gmail.com','adf','adasdf','','afd','adf','afsd','adf','Visa','asfe','01','2017','\0'),(15,NULL,NULL,'sallyann@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,''),(16,NULL,NULL,'abc@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,''),(17,'Exp','Date Test','ExpDateTest@gmail.com','Freelance Corp','123 Friday Ln','','Bobs','CA','20133','USA','Mastercard','31564849','05','2017','\0'),(23,'test2','test2','test2@gmail.com','','1555 test rd','Apt 1','Leesburg','VA','20176','United States','Visa','346346','12','2017','\0'),(24,'Sally','Jones','jones_family@verizon.net','','1321 Gaylord Blvd','Apt 312','Lorton','VA','30123','USA',NULL,NULL,NULL,NULL,'\0'),(25,'Amy','Radtke','radtke2_family@verizon.net','ABC','12401 Eden Lane','','Woodbridge','VA','20176','United States',NULL,NULL,NULL,NULL,'\0'),(26,'Amy','Radtke','radtke3_family@verizon.net','ABC','12401 Eden Lane','','Woodbridge','VA','20176','United States',NULL,NULL,NULL,NULL,'\0'),(27,'Amy','Radtke','radtke4_family@verizon.net','ABC','12401 Eden Lane','','Woodbridge','VA','20176','United States',NULL,NULL,NULL,NULL,'\0'),(28,'Amy','Radtke','radtke7_family@verizon.net','ABC','12401 Eden Lane','','Woodbridge','VA','20176','United States','Visa','111111','01','2019','\0'),(29,'New','Person','person2@gmail.com','Here','Address','','City','State','Zip','USA',NULL,NULL,NULL,NULL,'\0'),(30,'New','Person','person4@gmail.com','Here','Address','','City','State','Zip','USA',NULL,NULL,NULL,NULL,'\0'),(31,'Sally','Jones','sally_family@verizon.net','','1321 Gaylord Blvd','Apt 312','Lorton','VA','30123','United States','Visa','65','12','2017','\0'),(32,'Amy','Radtke','radtke_family2@verizon.net','ABC','12401 Eden Lane','','Woodbridge','VA','20176','United States',NULL,NULL,NULL,NULL,'\0'),(33,'Amy','Radtke','a_new_one@gmail.com','ABC','12401 Eden Lane','','Woodbridge','VA','20176','United States',NULL,NULL,NULL,NULL,'\0'),(34,'aaaaa','Radtke','radtke9_family@verizon.net','ABC','12401 Eden Lane','','Woodbridge','VA','20176','United States',NULL,NULL,NULL,NULL,'\0');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-20  8:42:51
