DROP TABLE IF EXISTS conversion_factor;
CREATE TABLE conversion_factor ( id integer AUTO_INCREMENT PRIMARY KEY, country VARCHAR(20) NOT NULL,country_code VARCHAR(4) NOT NULL, tax decimal(30,2));  

--INSERT INTO conversion_factor (country, country_code, tax) VALUES ('India','INR', 5.2);
--INSERT INTO conversion_factor (country, country_code, tax) VALUES ('USA','USD', 0.001);
