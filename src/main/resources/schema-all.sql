DROP TABLE customerInfo IF EXISTS;

CREATE TABLE customerInfo (
    customer_id BIGINT IDENTITY NOT NULL PRIMARY KEY,
    
    cardNumber VARCHAR(50),
    mobileNumber VARCHAR(50),
    email VARCHAR(50),
    expDate VARCHAR(50),
    status VARCHAR(50)
);