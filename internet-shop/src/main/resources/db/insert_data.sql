INSERT INTO users (name)
VALUES ('Bob');
INSERT INTO orders (user_id, sum)
VALUES (1, 2000);

CALL get_user_purchase_history(1);