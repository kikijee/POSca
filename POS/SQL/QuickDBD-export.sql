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
    `Name` String  NOT NULL ,
    `Price` decimal(,2)  NOT NULL ,
    PRIMARY KEY (
        `ItemID`
    )
);

CREATE TABLE `Orders` (
    `OrderID` int  NOT NULL ,
    `Subtotal` decimal(,2)  NOT NULL ,
    `Total` decimal(,2)  NOT NULL ,
    `Time` String  NOT NULL ,
    `Server` String  NOT NULL ,
    `Paid` boolean  NOT NULL ,
    `PayType` String  NULL ,
    `Change` decimal(,2)  NULL ,
    `NumItems` int  NOT NULL ,
    PRIMARY KEY (
        `OrderID`
    )
);

ALTER TABLE `Orders` ADD CONSTRAINT `fk_Orders_Server` FOREIGN KEY(`Server`)
REFERENCES `Users` (`Username`);

