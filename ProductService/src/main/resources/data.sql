DROP TABLE IF EXISTS products;
CREATE TABLE products ( ID INT AUTO_INCREMENT PRIMARY KEY, code VARCHAR(20) NOT NULL,name VARCHAR(40) NOT NULL, description VARCHAR(400) NOT NULL,price decimal(30,2));  

insert into products(ID, code, name, description, price) VALUES
(1, 'P001', 'Apple', 'Apple description', 25.10),
(2, 'P002', 'Banana', 'Banan description', 32.75),
(3, 'P003', 'Orange', 'Orange description', 49.99);