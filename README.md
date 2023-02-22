# SAE S4.A02.1 : Web Backend 
Thomas Dujardin & Simon Barbeau
## Création d'une API REST pour une pizzeria 

# A : Ingredients

1) On crée une table ingredients

    ```sql
    CREATE TABLE ingredients(
        idI int PRIMARY KEY, 
        namei varchar(50)
    );

    INSERT INTO ingredients VALUES (1,'pomme de terre');
    INSERT INTO ingredients VALUES (2,'poivrons');
    INSERT INTO ingredients VALUES (3,'poulet');
    INSERT INTO ingredients VALUES (4,'lardons');
    INSERT INTO ingredients VALUES (5,'oignon');
    INSERT INTO ingredients VALUES (6,'champignons');
    INSERT INTO ingredients VALUES (7,'mozzarella');
    INSERT INTO ingredients VALUES (8,'compté');
    INSERT INTO ingredients VALUES (9,'cheddar');
    INSERT INTO ingredients VALUES (10,'gorgonzola');
    INSERT INTO ingredients VALUES (11,'reblochon');

    ```

2) DTO = un pojo de l'ingredient (réel -> objet java)

3) DAO = manager des pojos depuis la base de donnée (CRUD : creer, read, update and delete)

4) CONTROLEUR = 
    - get 
        - ingredients = retourne liste de toute la collection
        - ingredients/id = retourne ingrédient identifié
        - ingredients/id/name 

    - post 
        - ingredients = ajouter un ingredient

    - delete
        - ingredients/id = supprime ingredient



# B : Pizzas

1) On crée une table pizza 

    ```sql
    CREATE TABLE pizzas(
        idP int PRIMARY KEY , 
        nomP varchar(50), 
        pate varchar(100),
        prixP decimal(10,2)
    );
    
    INSERT INTO pizzas VALUES (1,'4 fromages', 'new-yorkaise', 8.80);
    INSERT INTO pizzas VALUES (2,'tartiflette', 'new-yorkaise', 8.80);
    INSERT INTO pizzas VALUES (3,'barbecue', 'new-yorkaise', 8.80);
    ```


2) on crée une table pizza-ingredints qui contient des enregistrements qui lient les pizzas aux ingrédients qui les composent (relation many-to-many) (c'est le contenu d'une pizza)

    ```sql
    CREATE TABLE pizza_ingredients (
        pizza_id INT,
        ingredient_id INT,
        -- complexifie la chose quantite varchar(10),
        PRIMARY KEY (pizza_id, ingredient_id),
        FOREIGN KEY (pizza_id) REFERENCES pizzas(idP),
        FOREIGN KEY (ingredient_id) REFERENCES ingredients(idI)
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

    ```

    Dans cette table, chaque enregistrement représente la présence d'un ingrédient dans une pizza particulière. 
    La clé primaire est composée des deux colonnes "pizza_id" et "ingredient_id", qui empêchent les doublons et garantissent que chaque association est unique. Les deux colonnes sont également des clés étrangères qui font référence aux tables "pizzas" et "ingrédients" respectivement, assurant ainsi que seuls les ingrédients et les pizzas existantes peuvent être liés dans la table de jonction.



3) DTO = un pojo de la pizza (modèle)

4) DAO = manager des pojos depuis la base de donnée (CRUD : creer, read, update and delete)

5) CONTROLEUR = 
    - get 
        - pizzas = retourne liste de toute la collection
        - pizzas/id = retourne pizza identifié

            ```sql
            SELECT pizza.id, pizza.name, pizza.description, pizza.price, ingredient.id, ingredient.name, ingredient.description, ingredient.price, pizza_ingredient.quantity
            FROM pizza
            JOIN pizza_ingredient ON pizza.id = pizza_ingredient.pizza_id
            JOIN ingredient ON pizza_ingredient.ingredient_id = ingredient.id
            WHERE pizza.id = {id};
            ```

    - post 
        - pizzas = ajouter une pizza
        - pizzas/id/addIngredient = ajouter un ingredient à la pizza identifié


        On ajoute en 1er l'ingredient dans sa table (on pourrait appeler la méthode doPost de ingredient)
        on l'ajoute ensuite a la pizza



        - pizzas/id/removeIngredient = supprime un ingredient à la pizza identifié

    - delete
        - pizzas/id = supprime pizza

    - patch
        - pizzas/id = modifie attribut de la pizza identifié



# C : Commandes

1) on crée table des clients

    ```sql
    create table clients (
        id int PRIMARY KEY,
        nom varchar(50),
        adresse varchar(50)
    );

    INSERT INTO clients values (1, 'Thomas', 'Nieppe');
    INSERT INTO clients values (2, 'Simon', 'Lille');
    ```

2) on crée table des commandes

    ```sql
    create table commandes (
        id int PRIMARY KEY,
        id_client int,
        date date,
        FOREIGN KEY (id_client) REFERENCES clients(id)
    );

    INSERT INTO commandes values (1, 1, 2023-02-18);
    INSERT INTO commandes values (2, 2, 2023-02-18);
    ```

3) on crée table pizza_commande qui affecte une pizza à une commande (c'est le contenu d'une commande)

    ```sql
    create table commande_pizza (
        commandes INT,
        pizza_id INT,
        PRIMARY KEY (commandes, pizza_id),
        FOREIGN KEY (commandes) REFERENCES commandes(id),
        FOREIGN KEY (pizza_id) REFERENCES pizzas(id)
    )

     -- POUR LA COMMANDE DE THOMAS
    INSERT INTO commande_pizza VALUES (1, 1);

     -- POUR LA COMMANDE DE SIMON
    INSERT INTO commande_pizza VALUES (2, 2);
    ```


- DTO = un pojo d'une commande (modèle)
- DAO = manager des pojos depuis la base de donnée (CRUD : creer, read, update and delete)
- CONTROLEUR = 
    - get 
        - commandes = retourne liste de toute les commandes en cours
        - commandes/id = retourne le détail de la commande identifié

    - post 
        - commandes = ajouter une commande


# D : Accès

- DTO = un pojo d'un accès (modèle)
- DAO = manager des pojos depuis la base de donnée (CRUD : creer, read, update and delete)
- CONTROLEUR = 
    - get 
        - /users/token = récupérer un token d'identification
