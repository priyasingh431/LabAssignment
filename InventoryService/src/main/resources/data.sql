DROP TABLE IF EXISTS inventory;
CREATE TABLE inventory ( ID INT AUTO_INCREMENT PRIMARY KEY, product_code VARCHAR(20) NOT NULL, quantity Integer);  

insert into inventory(id, product_code, quantity) VALUES
(1, 'P001', 250),
(2, 'P002', 132),
(3, 'P003', 0)
;