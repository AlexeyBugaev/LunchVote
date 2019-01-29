# LunchVote
Lunchvote REST Service API Guide

API provides RESTful web service that let users to make a vote for restaurant where to have a lunch and get the voted restaurant.

Each restaurant has list of dishes with prices.

Two user roles are supported: user and admin.

User can have a look on each restaurant menu, vote for restaurant, update the profile.

User can vote for a restaurant only before 11 a.m.

User cannot vote for the same restaurant twice.

Admin can create/update/delete restaurants, dishes and users profile.

General REST service

Get all restaurants

curl request
$ curl 'http://localhost:8080/restaurants/' -i -u 'user@yandex.ru:password' -X GET

Request structure
GET /restaurants/ HTTP/1.1
Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ=
Host: localhost:8080

Response structure
HTTP/1.1 200 OK
Pragma: no-cache
X-XSS-Protection: 1; mode=block
Expires: 0
X-Frame-Options: DENY
X-Content-Type-Options: nosniff
Content-Type: application/json;charset=UTF-8
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Content-Length: 188

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

Request structure
GET /dishes/?restaurantId=100001 HTTP/1.1
Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
Host: localhost:8080

Response structure
HTTP/1.1 200 OK
Pragma: no-cache
X-XSS-Protection: 1; mode=block
Expires: 0
X-Frame-Options: DENY
X-Content-Type-Options: nosniff
Content-Length: 314
Content-Type: application/json;charset=UTF-8
Cache-Control: no-cache, no-store, max-age=0, must-revalidate

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

Request structure
GET /restaurants/voteForRestaurant/100001 HTTP/1.1
Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ=
Host: localhost:8080

Response structure
HTTP/1.1 200 OK
Pragma: no-cache
X-XSS-Protection: 1; mode=block
Expires: 0
X-Frame-Options: DENY
X-Content-Type-Options: nosniff
Content-Length: 40
Content-Type: application/json;charset=UTF-8
Cache-Control: no-cache, no-store, max-age=0, must-revalidate

{
  "Restaurant voted" : "BeefHouse"
}

Vote for same restaurant twice

curl request
$ curl 'http://localhost:8080/restaurants/voteForRestaurant/100000' -i -u 'user@yandex.ru:password' -X GET

Request structure
GET /restaurants/voteForRestaurant/100000 HTTP/1.1
Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ=
Host: localhost:8080

Response structure
HTTP/1.1 403 Forbidden
Pragma: no-cache
X-XSS-Protection: 1; mode=block
Expires: 0
X-Frame-Options: DENY
X-Content-Type-Options: nosniff
Content-Type: application/json;charset=UTF-8
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Content-Length: 67

{
  "errorMessage" : "You cannot vote for one restaurant twice"
}

Vote for restaurant after 11 a.m.

curl request
$ curl 'http://localhost:8080/restaurants/voteForRestaurant/100001' -i -u 'user@yandex.ru:password' -X GET

Request structure
GET /restaurants/voteForRestaurant/100001 HTTP/1.1
Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ=
Host: localhost:8080

Response structure
HTTP/1.1 403 Forbidden
Pragma: no-cache
X-XSS-Protection: 1; mode=block
Expires: 0
X-Frame-Options: DENY
X-Content-Type-Options: nosniff
Content-Length: 61
Content-Type: application/json;charset=UTF-8
Cache-Control: no-cache, no-store, max-age=0, must-revalidate

{
  "errorMessage" : "Voting is available before 11 a.m."
}

Get restaurant selected by vote

curl request
$ curl 'http://localhost:8080/restaurants/getVoted' -i -u 'user@yandex.ru:password' -X GET

Request structure
GET /restaurants/getVoted HTTP/1.1
Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ=
Host: localhost:8080

Response structure
HTTP/1.1 200 OK
Pragma: no-cache
X-XSS-Protection: 1; mode=block
Expires: 0
X-Frame-Options: DENY
X-Content-Type-Options: nosniff
Content-Length: 62
Content-Type: application/json;charset=UTF-8
Cache-Control: no-cache, no-store, max-age=0, must-revalidate

{
  "id" : 100001,
  "name" : "BeefHouse",
  "votes" : 2
}

Full API guide can be found on index.html page that is automatically redirected to when the service starts
