INSERT INTO users (username, password)
VALUES ('Bob', '111');
INSERT INTO users (username, password)
VALUES ('Tom', '111');
INSERT INTO users (username, password)
VALUES ('Baba', '111');
INSERT INTO orders (user_id, sum)
VALUES (1, 2000.00);
INSERT INTO products (name, price)
VALUES ('Milk', 2.00);
INSERT INTO products (name, price)
VALUES ('Bread', 4.50);
INSERT INTO products (name, price)
VALUES ('Knife', 15.00);
INSERT INTO products (name, price)
VALUES ('Mirror', 25.50);
INSERT INTO basket (user_id)
VALUES (1);
INSERT INTO basket (user_id)
VALUES (2);
INSERT INTO basket (user_id)
VALUES (3);

CALL get_user_purchase_history(1);