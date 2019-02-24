DELETE FROM restaurants;
DELETE FROM user_roles;
DELETE FROM dishes;
DELETE FROM users;
DELETE FROM voteHistory;
ALTER SEQUENCE global_seq RESTART WITH 100000;


INSERT INTO restaurants (name, votes)
VALUES ('No Restaurant', 0),
       ('BeefHouse', 0),
       ('Shinok', 0),
       ('Kolizey', 0);

INSERT INTO users (name, email, password, restaurantVotedId, voteMade)
VALUES ('User', 'user@yandex.ru', '{noop}password', 100000, false ),
       ('Admin', 'admin@gmail.com', '{noop}admin', 100000, false );

INSERT INTO dishes (name, price, restaurantid)
VALUES ('BeefSteak', 1200, 100001),
       ('PorkSteak', 1000, 100001),
       ('GrilledChicken', 800, 100001),
       ('Borsh', 400, 100002),
       ('RoastWithPotatoes', 800, 100002),
       ('FishPanCakes', 600, 100002),
       ('Pasta', 500, 100003),
       ('Rizotto', 800, 100003),
       ('Lazaniya', 900, 100003);

INSERT INTO user_roles (role, user_id)
VALUES ('ROLE_USER', 100004),
       ('ROLE_ADMIN', 100005);