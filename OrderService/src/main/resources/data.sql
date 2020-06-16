CREATE TABLE orders ( ID INT AUTO_INCREMENT PRIMARY KEY, customerEmail VARCHAR(20) NOT NULL, customerAddress VARCHAR(20) NOT NULL, items Integer);  

CREATE TABLE order_items ( ID INT AUTO_INCREMENT PRIMARY KEY, productId Integer NOT NULL, quantity Integer, productPrice decimal(30,2));  






