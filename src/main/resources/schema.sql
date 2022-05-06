--------------------------------------------------
-- Authors:
--------------------------------------------------

CREATE SCHEMA `eSportDS`;
USE `eSportDS`;

CREATE TABLE `user` (
  `Username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Email` varchar(100) NOT NULL,
  `Logo` blob,
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `Password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;