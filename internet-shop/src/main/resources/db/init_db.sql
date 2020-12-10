DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS order_info;
DROP PROCEDURE IF EXISTS get_user_purchase_history;

CREATE TABLE users
(
    user_id INTEGER AUTO_INCREMENT PRIMARY KEY,
    name    VARCHAR(10) NOT NULL
);

CREATE TABLE orders
(
    order_id INTEGER AUTO_INCREMENT PRIMARY KEY,
    user_id  INTEGER NOT NULL,
    sum      INTEGER NOT NULL
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
    VALUES (NEW.order_id, NEW.user_id, CURRENT_TIMESTAMP(), NEW.sum);
END;

CREATE PROCEDURE get_user_purchase_history(IN user_id INT)
BEGIN
    SELECT order_id, sum, created_at FROM order_info WHERE order_info.user_id = user_id;
END


