-- Authors: Maximilian Rublik, Daniel Mehlber, Philipp Phan

-- Allow 10MB for images per packet
SET GLOBAL max_allowed_packet=10 * 1024 * 1024;

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
    `username` varchar(30) NOT NULL ,
    `teamId` BIGINT NOT NULL,
    `since` datetime DEFAULT NULL,
    `role` varchar(30) DEFAULT NULL,
    `isTeamLeader` tinyint(1) NOT NULL,
    PRIMARY KEY (`teamId`,`username`),
    CONSTRAINT FOREIGN KEY (`teamId`) REFERENCES `team` (`id`) ON DELETE CASCADE,
    CONSTRAINT FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE
);

CREATE TABLE `newsfeed` (
    `id` SERIAL PRIMARY KEY,
    `date` TIMESTAMP(6) NOT NULL,
    `type` VARCHAR(20) NOT NULL,
    `player1` varchar(30),
    `player2` varchar(30),
    `team1` BIGINT,
    `team2` BIGINT,
    CONSTRAINT `fk_player1` FOREIGN KEY (player1) REFERENCES user(username) ON DELETE CASCADE ,
    CONSTRAINT `fk_player2` FOREIGN KEY (player2) REFERENCES user(username) ON DELETE CASCADE,
    CONSTRAINT `fk_team1` FOREIGN KEY (team1) REFERENCES team(id) ON DELETE CASCADE,
    CONSTRAINT `fk_team2` FOREIGN KEY (team2) REFERENCES team(id) ON DELETE CASCADE
);

CREATE TABLE `game` (
    `id` SERIAL PRIMARY KEY,
    `name` VARCHAR(64) NOT NULL,
    `description` TEXT NOT NULL,
    `profile_picture` mediumblob,
    `background_picture` mediumblob
);

CREATE TABLE `game_team` (
    game_id BIGINT UNSIGNED NOT NULL,
    team_id BIGINT NOT NULL,
    PRIMARY KEY (game_id, team_id),
    CONSTRAINT FOREIGN KEY (game_id) REFERENCES game(id) ON DELETE CASCADE,
    CONSTRAINT FOREIGN KEY (team_id) REFERENCES team(id) ON DELETE CASCADE
);