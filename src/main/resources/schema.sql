
DROP TABLE IF EXISTS users;

CREATE TABLE `users` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `username` varchar(100) NOT NULL UNIQUE,
  `password` varchar(255) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `surname` varchar(100) DEFAULT NULL,
  gender TINYINT DEFAULT NULL,
  creation_dt timestamp NOT NULL DEFAULT current_timestamp,
  updated_dt timestamp DEFAULT current_timestamp,
  login_dt timestamp NULL,
  note varchar(255) DEFAULT NULL
);

