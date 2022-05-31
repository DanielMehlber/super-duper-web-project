-- Authors: Maximilian Rublik
CREATE TABLE `user` (
  `username` varchar(30) NOT NULL,
  `email` varchar(40) NOT NULL,
  `profile_picture` blob,
  `background_picture` blob,
  `passwordHash` varchar(64) NOT NULL,
  `nickname` varchar(20)
);