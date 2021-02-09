
DROP TABLE IF EXISTS expenses;
DROP TABLE IF EXISTS categories;

DROP TABLE IF EXISTS users;

CREATE TABLE users (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username varchar(100) NOT NULL UNIQUE,
  password varchar(255) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  surname varchar(100) DEFAULT NULL,
  gender TINYINT DEFAULT NULL,
  creation_dt timestamp NOT NULL DEFAULT current_timestamp,
  updated_dt timestamp DEFAULT current_timestamp,
  login_dt timestamp NULL,
  note varchar(255) DEFAULT NULL
);

CREATE TABLE categories (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  category varchar(100) DEFAULT NULL
);

CREATE TABLE expenses (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  user_id BIGINT(20) NOT NULL,
  creation_dt timestamp NOT NULL DEFAULT current_timestamp,
  voice varchar(100) DEFAULT NULL COMMENT 'The cost or income voice',
  price decimal(22,2) NOT NULL DEFAULT 0 COMMENT 'The final price',
  category_id BIGINT(20) NOT NULL COMMENT 'The category of the expense',
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (category_id) REFERENCES categories(id)
);