CREATE DATABASE renu;

\c renu;

CREATE TABLE IF NOT EXISTS categories(
    id SERIAL PRIMARY KEY,
    name VARCHAR,
    description VARCHAR
);

INSERT INTO categories (name, description) VALUES ('Drinks', 'To supplement the meal');
INSERT INTO categories (name, description) VALUES ('Dessert', 'For those still with space after the meal');
INSERT INTO categories (name, description) VALUES ('Breakfast', 'On your way to work');
INSERT INTO categories (name, description) VALUES ('Brunch', 'Those wishing to have something before lunch');
INSERT INTO categories (name, description) VALUES ('Lunch', 'You mid-day meal');
INSERT INTO categories (name, description) VALUES ('Snacks', 'for those who cannot wait for dinner');
INSERT INTO categories (name, description) VALUES ('Dinner', 'Close the day with either of our meals');

CREATE TABLE IF NOT EXISTS meals(
    id SERIAL PRIMARY KEY,
    name VARCHAR,
    type VARCHAR,
    size VARCHAR,
    imageurl VARCHAR,
    description VARCHAR,
    price VARCHAR,
    categoryid INT
);

INSERT INTO meals (name, type, size, imageurl, description, price, categoryid) VALUES ('Water', 'Normal', 'Small', 'http://img.recipepuppy.com/5.jpg', 'Plain Water', 'Ksh: 70', 1);
INSERT INTO meals (name, type, size, imageurl, description, price, categoryid) VALUES ('Water', 'Normal', 'Medium', 'http://img.recipepuppy.com/5.jpg', 'Plain Water', 'Ksh: 90', 1);
INSERT INTO meals (name, type, size, imageurl, description, price, categoryid) VALUES ('Water', 'Normal', 'Large', 'http://img.recipepuppy.com/5.jpg', 'Plain Water', 'Ksh: 120', 1);



CREATE DATABASE renu_test WITH TEMPLATE renu;