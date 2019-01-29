DELETE FROM restaurants;
DELETE FROM user_roles;
DELETE FROM dishes;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;


INSERT INTO restaurants (name, votes)
VALUES ('Kolizey', 1),
       ('BeefHouse', 2),
       ('Ribai', 0);

INSERT INTO users (name, email, password, restaurantVotedId)
VALUES ('User', 'user@yandex.ru', '{noop}password', 100000),
       ('Admin', 'admin@gmail.com', '{noop}admin', 100001);

INSERT INTO dishes (name, price, restaurantid)
VALUES ('BeefSteak', 800, 100001),
       ('Pasta', 400, 100000),
       ('Barbeque', 600, 100002),
       ('Lazaniya', 500, 100000),
       ('PorkSteak', 700, 100001),
       ('BeefChop', 500, 100002);

INSERT INTO user_roles (role, user_id)
VALUES ('ROLE_USER', 100003),
       ('ROLE_ADMIN', 100004);