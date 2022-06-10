-- Authors: Maximilian Rublik, Daniel Mehlber
CREATE TABLE `user` (
    `username` varchar(30) PRIMARY KEY,
    `email` varchar(40) NOT NULL,
    `profile_picture` MediumBlob,
    `background_picture` MediumBlob,
    `passwordHash` varchar(64) NOT NULL,
    `nickname` varchar(20),
    `isAdmin` boolean NOT NULL DEFAULT 0
);

CREATE TABLE `team` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `name` varchar(50) NOT NULL,
    `slogan` varchar(255) DEFAULT NULL,
    `profile_picture` mediumblob,
    `background_picture` mediumblob,
    `tags` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `member` (
    `username` varchar(30) NOT NULL,
    `teamId` BIGINT NOT NULL,
    `since` datetime DEFAULT NULL,
    `role` varchar(30) DEFAULT NULL,
    PRIMARY KEY (`teamId`,`username`),
    CONSTRAINT FOREIGN KEY (`teamId`) REFERENCES `team` (`id`),
    CONSTRAINT FOREIGN KEY (`username`) REFERENCES `user` (`username`)
);

CREATE TABLE `newsfeed` (
    `id` SERIAL PRIMARY KEY,
    `date` TIMESTAMP(6) NOT NULL,
    `type` VARCHAR(20) NOT NULL,
    `player1` varchar(30),
    `player2` varchar(30),
    CONSTRAINT `fk_player1` FOREIGN KEY (player1) REFERENCES user(username) ON DELETE SET NULL,
    CONSTRAINT `fk_player2` FOREIGN KEY (player2) REFERENCES user(username) ON DELETE SET NULL
);