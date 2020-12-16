DROP TABLE IF EXISTS basket;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS products_in_basket;
DROP TABLE IF EXISTS order_info;
DROP PROCEDURE IF EXISTS get_user_purchase_history;

CREATE TABLE users
(
    id       INTEGER AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(10) NOT NULL,
    password VARCHAR(8)  NOT NULL
);

CREATE TABLE products
(
    id    INTEGER AUTO_INCREMENT PRIMARY KEY,
    name  VARCHAR(15) NOT NULL,
    price DOUBLE      NOT NULL
);

CREATE TABLE basket
(
    id      INTEGER AUTO_INCREMENT PRIMARY KEY,
    user_id INTEGER NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE products_in_basket
(
    product_id INTEGER NOT NULL,
    basket_id  INTEGER NOT NULL,
    quantity   INTEGER NOT NULL,
    PRIMARY KEY (product_id, basket_id)
);

CREATE TABLE orders
(
    id      INTEGER AUTO_INCREMENT PRIMARY KEY,
    user_id INTEGER NOT NULL,
    sum     DOUBLE  NOT NULL
);

CREATE TABLE order_info
(
    id         INTEGER AUTO_INCREMENT PRIMARY KEY,
    order_id   INTEGER   NOT NULL,
    user_id    INTEGER   NOT NULL,
    created_at TIMESTAMP NOT NULL,
    sum        INTEGER   NOT NULL,
    proceeded  BOOLEAN   NOT NULL DEFAULT false
);

CREATE TRIGGER order_trigger
    AFTER INSERT
    ON orders
    FOR EACH ROW
BEGIN
    INSERT INTO order_info (order_id, user_id, created_at, sum)
    VALUES (NEW.id, NEW.user_id, CURRENT_TIMESTAMP(), NEW.sum);
END;

CREATE PROCEDURE get_user_purchase_history(IN user_id INT)
BEGIN
    SELECT order_id, sum, created_at FROM order_info WHERE order_info.user_id = user_id;
END


