DROP TABLE commande_pizza;
DROP TABLE commandes;
DROP TABLE clients;

DROP TABLE pizza_ingredients;
DROP TABLE pizzas;
DROP TABLE ingredients;

DROP TABLE users;

CREATE TABLE ingredients(
    idI int PRIMARY KEY, 
    namei varchar(50),
    prixI decimal(10,2)
);

INSERT INTO ingredients VALUES (1,'pomme de terre', 1.50);
INSERT INTO ingredients VALUES (2,'poivrons', 1);
INSERT INTO ingredients VALUES (3,'poulet', 1);
INSERT INTO ingredients VALUES (4,'lardons', 1);
INSERT INTO ingredients VALUES (5,'oignon', 1);
INSERT INTO ingredients VALUES (6,'champignons', 1);
INSERT INTO ingredients VALUES (7,'mozzarella', 1);
INSERT INTO ingredients VALUES (8,'compté', 1);
INSERT INTO ingredients VALUES (9,'cheddar', 1);
INSERT INTO ingredients VALUES (10,'gorgonzola', 1);
INSERT INTO ingredients VALUES (11,'reblochon', 1);



CREATE TABLE pizzas(
    idP int PRIMARY KEY , 
    nomP varchar(50), 
    pate varchar(100),
    prixBaseP decimal(10,2)
);

INSERT INTO pizzas VALUES (1,'4 fromages', 'new-yorkaise', 8.80);
INSERT INTO pizzas VALUES (2,'tartiflette', 'new-yorkaise', 8.80);
INSERT INTO pizzas VALUES (3,'barbecue', 'new-yorkaise', 8.80);


CREATE TABLE pizza_ingredients (
    pizza_id INT,
    ingredient_id INT,
    -- complexifie la chose quantite varchar(10),
    PRIMARY KEY (pizza_id, ingredient_id),
    FOREIGN KEY (pizza_id) REFERENCES pizzas(idP) on delete cascade,
    FOREIGN KEY (ingredient_id) REFERENCES ingredients(idI) on delete cascade
);

-- POUR LA PIZZA 4 FROMAGES ON AOUTE TOUT LES INGRE UN PAR UN
INSERT INTO pizza_ingredients VALUES (1, 7);
INSERT INTO pizza_ingredients VALUES (1, 8);
INSERT INTO pizza_ingredients VALUES (1, 9);
INSERT INTO pizza_ingredients VALUES (1, 10);

-- POUR LA PIZZA TARTIFLETTE ON AOUTE TOUT LES INGRE UN PAR UN
INSERT INTO pizza_ingredients VALUES (2, 1);
INSERT INTO pizza_ingredients VALUES (2, 4);
INSERT INTO pizza_ingredients VALUES (2, 6);
INSERT INTO pizza_ingredients VALUES (2, 11);


-- POUR LA PIZZA BBQ ON AOUTE TOUT LES INGRE UN PAR UN
INSERT INTO pizza_ingredients VALUES (3, 3);
INSERT INTO pizza_ingredients VALUES (3, 5);
INSERT INTO pizza_ingredients VALUES (3, 8);






create table clients (
    idC int PRIMARY KEY,
    nomC varchar(50),
    adresseC varchar(50)
);

INSERT INTO clients values (1, 'Thomas', 'Nieppe');
INSERT INTO clients values (2, 'Simon', 'Lille');


create table commandes (
    idCo int PRIMARY KEY,
    client_id int,
    dateCo date,
    FOREIGN KEY (client_id) REFERENCES clients(idC) on delete cascade
);

INSERT INTO commandes values (1, 1, '2023-02-18');
INSERT INTO commandes values (2, 2, '2023-02-18');


create table commande_pizza (
    commande_id INT,
    pizza_id INT,
    PRIMARY KEY (commande_id, pizza_id),
    FOREIGN KEY (commande_id) REFERENCES commandes(idCo) on delete cascade,
    FOREIGN KEY (pizza_id) REFERENCES pizzas(idP) on delete cascade
);

 -- POUR LA COMMANDE DE THOMAS
INSERT INTO commande_pizza VALUES (1, 1);
INSERT INTO commande_pizza VALUES (1, 3);

 -- POUR LA COMMANDE DE SIMON
INSERT INTO commande_pizza VALUES (2, 2);

create table users (
	user_id INT,
	login varchar(20),
	pwd varchar(20)
);

INSERT INTO users values (1,'simon','simon');
INSERT INTO users values (2,'thomas','thomas');