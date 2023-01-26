CREATE TABLE `Users` (
    `Username` varchar(20)  NOT NULL ,
    `Password` varchar(20)  NOT NULL ,
    `Admin` boolean  NOT NULL ,
    PRIMARY KEY (
        `Username`
    )
);

CREATE TABLE `Items` (
    `ItemID` int  NOT NULL ,
    `Name` varchar(30)  NOT NULL ,
    `Price` decimal(5,2)  NOT NULL ,
    PRIMARY KEY (
        `ItemID`
    )
);

CREATE TABLE `Orders` (
    `OrderID` int  NOT NULL ,
    `Subtotal` decimal(5,2)  NOT NULL ,
    `Total` decimal(5,2)  NOT NULL ,
    `Time` varchar(5)  NOT NULL ,
    `Server` varchar(20)  NOT NULL ,
    `Paid` boolean  NOT NULL ,
    `PayType` varchar(4)  NULL ,
    `Change` decimal(5,2)  NULL ,
    `NumItems` int  NOT NULL ,
    `Items` varbinary(65000) NOT NULL ,
    PRIMARY KEY (
        `OrderID`
    )
);

ALTER TABLE `Orders` ADD CONSTRAINT `fk_Orders_Server` FOREIGN KEY(`Server`)
REFERENCES `Users` (`Username`);

CREATE TABLE `VoidOrders` (
	`orderIdVoid` int NOT NULL,
	`timeVoid` varchar(5) NOT NULL,
    `serverVoid` varchar(20) NOT NULL,
    `description` tinytext NULL,
    PRIMARY KEY(
		`orderIdVoid`
    )
);

ALTER TABLE `VoidOrders` ADD CONSTRAINT `fk_VoidOrders_Orders` FOREIGN KEY(`orderIdVoid`)
REFERENCES `Orders` (`orderId`);


