CREATE DATABASE super_market_ijse;
USE super_market_ijse;

CREATE TABLE Customer(
    id INT NOT NULL,
    name VARCHAR(50) NOT NULL,
    address VARCHAR(200) NOT NULL,
    salary decimal(10,2),
    CONSTRAINT PRIMARY KEY(id)
);


CREATE TABLE Item(
    id VARCHAR(50) NOT NULL,
    description VARCHAR(14) NOT NULL,
    unitPrice decimal(10,2) NOT NULL,
    qoh decimal(10,2),
    CONSTRAINT PRIMARY KEY(id)
);

CREATE TABLE Orders(
    orderId VARCHAR(50) NOT NULL,
    orderDate DATE NOT NULL,
    customerID int NOT NULL,
    CONSTRAINT PRIMARY KEY (orderId),
    CONSTRAINT FOREIGN KEY (customerID) REFERENCES Customer(id)
);

CREATE TABLE OrderDetail(
    orderId VARCHAR(50) NOT NULL,
    id VARCHAR(50) NOT NULL,
    qty decimal(10,2),
    unitPrice decimal(10,2),
    CONSTRAINT PRIMARY KEY (orderId,id),
    CONSTRAINT FOREIGN KEY (orderId) REFERENCES Orders(orderId),
    CONSTRAINT FOREIGN KEY (id) REFERENCES Item(id)
);