CREATE TABLE orders (
   id INT(20) PRIMARY KEY AUTO_INCREMENT,
   id_customer INT(20) NOT NULL,
   order_date DATETIME NOT NULL,
   deliveryaddress VARCHAR(150) NOT NULL,
   contact VARCHAR(80) NOT NULL,
   total DECIMAL(10,2) NOT NULL,
   status CHAR(25) NOT NULL,
   last_update DATETIME NULL,
   FOREIGN KEY (id_customer) REFERENCES customer(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;