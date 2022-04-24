CREATE SCHEMA pizzeria;

-- creating tables
CREATE TABLE pizzeria.t_ingredient(
       ingredient_name VARCHAR(30) NOT NULL,
       value_en VARCHAR(60) NOT NULL,
       value_pl VARCHAR(60) NOT NULL,
       PRIMARY KEY (ingredient_name)
);

CREATE TABLE pizzeria.t_pizza_type (
       pizza_name VARCHAR(30) NOT NULL,
       PRIMARY KEY (pizza_name)
);

CREATE TABLE pizzeria.t_pizza_type_ingredient(
       pizza_type_id VARCHAR(30),
       ingredient_id VARCHAR(30),
       PRIMARY KEY (pizza_type_id, ingredient_id)
);

ALTER TABLE pizzeria.t_pizza_type_ingredient
    ADD CONSTRAINT fk_pizza_type FOREIGN KEY (pizza_type_id) REFERENCES pizzeria.t_pizza_type(pizza_name),
    ADD CONSTRAINT fk_ingredient FOREIGN KEY (ingredient_id) REFERENCES pizzeria.t_ingredient(ingredient_name);

CREATE TABLE pizzeria.t_pizza_order (
        id BIGINT GENERATED ALWAYS AS IDENTITY,
        order_status VARCHAR(30),
        user_id BIGINT,
        PRIMARY KEY (id)
);

ALTER TABLE pizzeria.t_pizza_order
    ADD CONSTRAINT ck_order_status CHECK (order_status IN ('ORDERED', 'STARTED_COOKING', 'STARTED_DELIVERING', 'DELIVERED'));

CREATE TABLE pizzeria.t_pizza (
        id BIGINT GENERATED ALWAYS AS IDENTITY,
        pizza_size VARCHAR(6),
        pizza_order_id BIGINT,
        pizza_type_id VARCHAR(30),
        PRIMARY KEY (id)
);

ALTER TABLE pizzeria.t_pizza
    ADD CONSTRAINT ck_pizza_size CHECK (pizza_size IN ('SMALL', 'MEDIUM', 'BIG')),
    ADD CONSTRAINT fk_pizza_type FOREIGN KEY (pizza_type_id) REFERENCES pizzeria.t_pizza_type(pizza_name);

-- creating dummy data

INSERT INTO pizzeria.t_ingredient(ingredient_name, value_en, value_pl) VALUES
('SALAMI', 'Salami', 'Salami'),
('SAUCE', 'Sauce', 'Sos'),
('BASIL', 'Basil', 'Bazylia'),
('HAM', 'Ham', 'Szynka'),
('CHAMPIGNONS', 'Champignons', 'Pieczarki'),
('PINEAPPLE', 'Pineapple', 'Ananas'),
('OREGANO', 'Oregano', 'Oregano'),
('ONION', 'Onion', 'Cebula'),
('CHEESE', 'Cheese', 'Ser'),
('CORN', 'Corn', 'Kukurydza'),
('BELL_PEPPER', 'Bell pepper', 'Papryka');

INSERT INTO pizzeria.t_pizza_type(pizza_name) VALUES
('MARGHERITTA'),
('NEAPOLETANA'),
('VESUVIO'),
('FUNGHI'),
('SALAME'),
('HAWAINA'),
('TOSCANA');

INSERT INTO pizzeria.t_pizza_type_ingredient(pizza_type_id, ingredient_id) VALUES
-- MARGHERITTA
('MARGHERITTA','SAUCE'),
('MARGHERITTA','CHEESE'),
('MARGHERITTA','OREGANO'),
-- NEAPOLETANA
('NEAPOLETANA','SAUCE'),
('NEAPOLETANA','CHEESE'),
('NEAPOLETANA','BASIL'),
-- VESUVIO
('VESUVIO','SAUCE'),
('VESUVIO','CHEESE'),
('VESUVIO','HAM'),
('VESUVIO','BASIL'),
-- FUNGHI
('FUNGHI','SAUCE'),
('FUNGHI','CHEESE'),
('FUNGHI','CHAMPIGNONS'),
('FUNGHI','BASIL'),
-- SALAME
('SALAME','SAUCE'),
('SALAME','CHEESE'),
('SALAME','SALAMI'),
('SALAME','BASIL'),
-- HAWAINA
('HAWAINA','SAUCE'),
('HAWAINA','CHEESE'),
('HAWAINA','HAM'),
('HAWAINA','PINEAPPLE'),
('HAWAINA','OREGANO'),
-- TOSCANA
('TOSCANA','SAUCE'),
('TOSCANA','CHEESE'),
('TOSCANA','HAM'),
('TOSCANA','SALAMI'),
('TOSCANA','BELL_PEPPER'),
('TOSCANA','CHAMPIGNONS'),
('TOSCANA','CORN'),
('TOSCANA','BASIL');

INSERT INTO pizzeria.t_pizza_order(order_status, user_id) VALUES
('ORDERED', 1337);

INSERT INTO pizzeria.t_pizza (pizza_size, pizza_order_id, pizza_type_id) VALUES
('SMALL', (SELECT id FROM pizzeria.t_pizza_order WHERE order_status LIKE 'ORDERED' AND user_id = 1337), 'SALAME');
