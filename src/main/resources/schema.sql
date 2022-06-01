-- Authors: Maximilian Rublik
CREATE TABLE `user` (
  `username` varchar(30) PRIMARY KEY,
  `email` varchar(40) NOT NULL,
  `profile_picture` blob,
  `background_picture` blob,
  `passwordHash` varchar(64) NOT NULL,
  `nickname` varchar(20)
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