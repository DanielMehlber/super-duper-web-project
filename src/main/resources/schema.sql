-- Authors: Maximilian Rublik
CREATE TABLE `user` (
   `id` bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `username` varchar(30) NOT NULL,
  `email` varchar(40) NOT NULL,
  `logo` blob,
  `passwordHash` varchar(64) NOT NULL
);
