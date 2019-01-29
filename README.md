# LunchVote
Lunchvote REST Service API Guide

API provides RESTful web service that let users to make a vote for restaurant where to have a lunch and get the voted restaurant.

Each restaurant has list of dishes with prices.

Two user roles are supported: user and admin.

User can have a look on each restaurant menu, vote for restaurant, update the profile.

User can vote for a restaurant only before 11 a.m.

User cannot vote for the same restaurant twice.

Admin can create/update/delete restaurants, dishes and users profile.

General REST service:

Get all restaurants

curl request
$ curl 'http://localhost:8080/restaurants/' -i -u 'user@yandex.ru:password' -X GET

Response

[ {
  "id" : 100001,
  "name" : "BeefHouse",
  "votes" : 2
}, {
  "id" : 100000,
  "name" : "Kolizey",
  "votes" : 1
}, {
  "id" : 100002,
  "name" : "Ribai",
  "votes" : 0
} ]

Get all dishes by restaurant id

curl request
$ curl 'http://localhost:8080/dishes/?restaurantId=100001' -i -u 'admin@gmail.com:admin' -X GET

Response

[ {
  "id" : 100005,
  "name" : "BeefSteak",
  "price" : 800,
  "restaurant" : {
    "id" : 100001,
    "name" : "BeefHouse",
    "votes" : 2
  }
}, {
  "id" : 100009,
  "name" : "PorkSteak",
  "price" : 700,
  "restaurant" : {
    "id" : 100001,
    "name" : "BeefHouse",
    "votes" : 2
  }
} ]

Vote for restaurant

curl request
$ curl 'http://localhost:8080/restaurants/voteForRestaurant/100001' -i -u 'user@yandex.ru:password' -X GET

Response

{
  "Restaurant voted" : "BeefHouse"
}

Get restaurant selected by vote

curl request
$ curl 'http://localhost:8080/restaurants/getVoted' -i -u 'user@yandex.ru:password' -X GET

Response

{
  "id" : 100001,
  "name" : "BeefHouse",
  "votes" : 2
}

Full API guide can be found on index.html page that is automatically redirected to when the service starts
