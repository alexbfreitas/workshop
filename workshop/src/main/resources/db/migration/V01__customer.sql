CREATE TABLE customer(
	id int(20) PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(150) NOT NULL,
	email VARCHAR(80) NOT NULL,
	address VARCHAR(150) NOT NULL,
	birthdate datetime null,
	last_update datetime null
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
