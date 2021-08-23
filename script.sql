CREATE TABLE client (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    country VARCHAR(5) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    phoneNumber VARCHAR(15) DEFAULT NULL,
    registrationDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    lastPurchaseDate TIMESTAMP NULL DEFAULT NULL,
    notificationChannel VARCHAR(32) NOT NULL DEFAULT 'email'
);

CREATE TABLE rider (
    id VARCHAR(255) PRIMARY KEY,
    firstName VARCHAR(255) NOT NULL,
    lastName VARCHAR(255) NOT NULL,
    country VARCHAR(5) NOT NULL,
    idType VARCHAR(5) NOT NULL,
    idNumber VARCHAR(15) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    registrationDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE compoundItem (
    id VARCHAR(255) PRIMARY KEY,
    fatherId VARCHAR(255) NULL DEFAULT NULL,
    FOREIGN KEY(fatherId) REFERENCES compoundItem(id)
);

CREATE TABLE item (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DECIMAL NOT NULL,
    compoundId VARCHAR(255) NULL DEFAULT NULL,
    FOREIGN KEY(compoundId) REFERENCES compoundItem(id)
);

CREATE TABLE itemComposition (
    id VARCHAR(255) PRIMARY KEY,
    baseItemId VARCHAR(255) NULL DEFAULT NULL,
    compoundId VARCHAR(255) NULL DEFAULT NULL,
    FOREIGN KEY(compoundId) REFERENCES compoundItem(id),
    FOREIGN KEY(baseItemId) REFERENCES item(id)
);

CREATE TABLE purchase (
    id VARCHAR(255) PRIMARY KEY,
    client VARCHAR(255) NOT NULL,
    rider VARCHAR(255) NOT NULL,
    deliveryPrice DECIMAL NOT NULL,
    creationDate TIMESTAMP NULL DEFAULT NULL,
    confirmationDate TIMESTAMP NULL DEFAULT NULL,
    dispatchDate TIMESTAMP NULL DEFAULT NULL,
    deliveryDate TIMESTAMP NULL DEFAULT NULL,
    status DECIMAL NOT NULL,
    FOREIGN KEY(client) REFERENCES client(id),
    FOREIGN KEY(rider) REFERENCES rider(id)
);

CREATE TABLE itemXPurchase (
    id VARCHAR(255) PRIMARY KEY,
    itemId VARCHAR(255) NOT NULL,
    purchaseId VARCHAR(255) NOT NULL,
    FOREIGN KEY(itemId) REFERENCES item(id),
    FOREIGN KEY(purchaseId) REFERENCES purchase(id)
);