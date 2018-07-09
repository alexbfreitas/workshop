CREATE TABLE item_order(
	id int(20) PRIMARY KEY AUTO_INCREMENT,
	id_order int(20) NOT NULL,
	id_product int NOT NULL,
	price DECIMAL(12,2) NOT NULL,
	quantity int NOT NULL,
	total DECIMAL(12,2) NOT NULL,
	FOREIGN KEY (id_order) REFERENCES orders(id),
	FOREIGN KEY (id_product) REFERENCES product(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
