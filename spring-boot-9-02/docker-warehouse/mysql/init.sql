-- Utworzenie tabeli producentów (Producer.java)
CREATE TABLE producer (
                          id INT NOT NULL AUTO_INCREMENT,
                          name VARCHAR(255) NOT NULL,
                          country VARCHAR(255) NOT NULL,
                          PRIMARY KEY (id)
);

-- Utworzenie tabeli produktów (Product.java)
CREATE TABLE product (
                         id INT NOT NULL AUTO_INCREMENT,
                         name VARCHAR(20) NOT NULL,
                         sku_code VARCHAR(255) NOT NULL,
                         category VARCHAR(255),
                         producer_id INT, -- Klucz obcy do producenta (Producer brand)
                         PRIMARY KEY (id),
                         CONSTRAINT fk_producer FOREIGN KEY (producer_id) REFERENCES producer(id)
);

-- Utworzenie tabeli magazynów (Warehouse.java)
CREATE TABLE warehouse (
                           id INT NOT NULL AUTO_INCREMENT,
                           name VARCHAR(20) NOT NULL,
                           location VARCHAR(255),
                           PRIMARY KEY (id)
);

-- Tabela łącząca dla relacji Wiele-do-Wielu (Product <-> Warehouse)
-- Wynika z list produktów w Warehouse.java i magazynów w Product.java
CREATE TABLE product_warehouse (
                                   product_id INT NOT NULL,
                                   warehouse_id INT NOT NULL,
                                   PRIMARY KEY (product_id, warehouse_id),
                                   CONSTRAINT fk_pw_product FOREIGN KEY (product_id) REFERENCES product(id),
                                   CONSTRAINT fk_pw_warehouse FOREIGN KEY (warehouse_id) REFERENCES warehouse(id)
);