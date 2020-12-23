INSERT INTO basket () VALUES ();
INSERT INTO basket () VALUES ();
INSERT INTO basket () VALUES ();

INSERT INTO currency VALUES ();
INSERT INTO currency VALUES ();

INSERT INTO users (username, password, role, basket_id)
VALUES ('Bob', '$2y$12$bp26LmUVsTl6vYGGm9iAK.7JNGgvlNJ4iZNQGA2IVqJuhXc/yqFrW', 'USER', 1);
INSERT INTO users (username, password, role, basket_id)
VALUES ('Tom', '$2y$12$3PFYCb6EJLqj6BWphapxJu9QcUgTvtrwBhw2KMlByv.n3hfx8Hxje', 'USER', 2);
INSERT INTO users (username, password, role, basket_id)
VALUES ('Baba', '$2y$12$beV.Hg5eVrtztXUqpM2n3./KzEYB9uKRrCTohA/5Yq3Aqe8YKbUIi', 'USER', 3);

INSERT INTO products (name, price, product_group, currency_id, country)
VALUES ('bread', 2.5, 'Food', 1, 'BY');
INSERT INTO products (name, price, product_group, currency_id, country)
VALUES ('milk', 5.0, 'Food', 1, 'BY');
INSERT INTO products (name, price, product_group, currency_id, country)
VALUES ('knife', 25.0, 'NotFood', 2, 'USA');
INSERT INTO products (name, price, product_group, currency_id, country)
VALUES ('mirror', 15.0, 'NotFood', 2, 'RU');