Lunchvote REST Service API Guide
================================

Table of Contents

-   [Description](#_description)
-   [Resources](#_resources)
    -   [General REST service](#_general_rest_service)
        -   [Get all restaurants](#_get_all_restaurants)            
        -   [Get all dishes by restaurant
            id](#_get_all_dishes_by_restaurant_id)            
        -   [Vote for restaurant](#_vote_for_restaurant)            
        -   [Vote for same restaurant
            twice](#_vote_for_same_restaurant_twice)            
        -   [Vote for restaurant after 11
            a.m.](#_vote_for_restaurant_after_11_a_m)          
        -   [Get restaurant selected by
            vote](#_get_restaurant_selected_by_vote)           
    -   [User REST service](#_user_rest_service)
        -   [Get all users](#_get_all_users)         
        -   [Get user](#_get_user)            
        -   [Get user by email](#_get_user_by_email)           
        -   [Delete user](#_delete_user)           
        -   [Create user](#_create_user)           
        -   [Update user](#_update_user)           
    -   [Restaurant REST service](#_restaurant_rest_service)
        -   [Get restaurant](#_get_restaurant)           
        -   [Delete restaurant](#_delete_restaurant)           
        -   [Create restaurant](#_create_restaurant)            
        -   [Update restaurant](#_update_restaurant)            
    -   [Dish REST service](#_dish_rest_service)
        -   [Get dish](#_get_dish)           
        -   [Delete dish](#_delete_dish)           
        -   [Create dish](#_create_dish)          
        -   [Update dish](#_update_dish)           

[Description](#_description) <a name="description"></a>
----------------------------

-   API provides RESTful web service that let users to make a vote for
    restaurant where to have a lunch and get the voted restaurant.

-   Each restaurant has list of dishes with prices.

-   Two user roles are supported: user and admin.

-   User can have a look on each restaurant menu, vote for restaurant,
    update the profile.

-   User can vote for a restaurant only before 11 a.m.

-   User cannot vote for the same restaurant twice.

-   Admin can create/update/delete restaurants, dishes and users
    profile.

[Resources](#_resources) {#_resources .sect0}
========================

[General REST service](#_general_rest_service) {#_general_rest_service}
----------------------------------------------

### [Get all restaurants](#_get_all_restaurants) {#_get_all_restaurants}

#### [curl request](#_curl_request) {#_curl_request}

``` {.highlightjs .highlight}
$ curl 'http://localhost:8080/restaurants/' -i -u 'user@yandex.ru:password' -X GET
```

#### [Request structure](#_request_structure) {#_request_structure}

``` {.highlightjs .highlight .nowrap}
GET /restaurants/ HTTP/1.1
Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ=
Host: localhost:8080
```

#### [Response structure](#_response_structure) {#_response_structure}

``` {.highlightjs .highlight .nowrap}
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
```

### [Get all dishes by restaurant id](#_get_all_dishes_by_restaurant_id) {#_get_all_dishes_by_restaurant_id}

#### [curl request](#_curl_request_2) {#_curl_request_2}

``` {.highlightjs .highlight}
$ curl 'http://localhost:8080/dishes/?restaurantId=100001' -i -u 'admin@gmail.com:admin' -X GET
```

#### [Request structure](#_request_structure_2) {#_request_structure_2}

``` {.highlightjs .highlight .nowrap}
GET /dishes/?restaurantId=100001 HTTP/1.1
Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
Host: localhost:8080
```

#### [Response structure](#_response_structure_2) {#_response_structure_2}

``` {.highlightjs .highlight .nowrap}
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
```

### [Vote for restaurant](#_vote_for_restaurant) {#_vote_for_restaurant}

#### [curl request](#_curl_request_3) {#_curl_request_3}

``` {.highlightjs .highlight}
$ curl 'http://localhost:8080/restaurants/voteForRestaurant/100001' -i -u 'user@yandex.ru:password' -X GET
```

#### [Request structure](#_request_structure_3) {#_request_structure_3}

``` {.highlightjs .highlight .nowrap}
GET /restaurants/voteForRestaurant/100001 HTTP/1.1
Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ=
Host: localhost:8080
```

#### [Response structure](#_response_structure_3) {#_response_structure_3}

``` {.highlightjs .highlight .nowrap}
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
```

### [Vote for same restaurant twice](#_vote_for_same_restaurant_twice) {#_vote_for_same_restaurant_twice}

#### [curl request](#_curl_request_4) {#_curl_request_4}

``` {.highlightjs .highlight}
$ curl 'http://localhost:8080/restaurants/voteForRestaurant/100000' -i -u 'user@yandex.ru:password' -X GET
```

#### [Request structure](#_request_structure_4) {#_request_structure_4}

``` {.highlightjs .highlight .nowrap}
GET /restaurants/voteForRestaurant/100000 HTTP/1.1
Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ=
Host: localhost:8080
```

#### [Response structure](#_response_structure_4) {#_response_structure_4}

``` {.highlightjs .highlight .nowrap}
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
```

### [Vote for restaurant after 11 a.m.](#_vote_for_restaurant_after_11_a_m) {#_vote_for_restaurant_after_11_a_m}

#### [curl request](#_curl_request_5) {#_curl_request_5}

``` {.highlightjs .highlight}
$ curl 'http://localhost:8080/restaurants/voteForRestaurant/100001' -i -u 'user@yandex.ru:password' -X GET
```

#### [Request structure](#_request_structure_5) {#_request_structure_5}

``` {.highlightjs .highlight .nowrap}
GET /restaurants/voteForRestaurant/100001 HTTP/1.1
Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ=
Host: localhost:8080
```

#### [Response structure](#_response_structure_5) {#_response_structure_5}

``` {.highlightjs .highlight .nowrap}
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
```

### [Get restaurant selected by vote](#_get_restaurant_selected_by_vote) {#_get_restaurant_selected_by_vote}

#### [curl request](#_curl_request_6) {#_curl_request_6}

``` {.highlightjs .highlight}
$ curl 'http://localhost:8080/restaurants/getVoted' -i -u 'user@yandex.ru:password' -X GET
```

#### [Request structure](#_request_structure_6) {#_request_structure_6}

``` {.highlightjs .highlight .nowrap}
GET /restaurants/getVoted HTTP/1.1
Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ=
Host: localhost:8080
```

#### [Response structure](#_response_structure_6) {#_response_structure_6}

``` {.highlightjs .highlight .nowrap}
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
```

[User REST service](#_user_rest_service) {#_user_rest_service}
----------------------------------------

### [Get all users](#_get_all_users) {#_get_all_users}

#### [curl request](#_curl_request_7) {#_curl_request_7}

``` {.highlightjs .highlight}
$ curl 'http://localhost:8080/users/' -i -u 'admin@gmail.com:admin' -X GET
```

#### [Request structure](#_request_structure_7) {#_request_structure_7}

``` {.highlightjs .highlight .nowrap}
GET /users/ HTTP/1.1
Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
Host: localhost:8080
```

#### [Response structure](#_response_structure_7) {#_response_structure_7}

``` {.highlightjs .highlight .nowrap}
HTTP/1.1 200 OK
Pragma: no-cache
X-XSS-Protection: 1; mode=block
Expires: 0
X-Frame-Options: DENY
X-Content-Type-Options: nosniff
Content-Type: application/json;charset=UTF-8
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Content-Length: 344

[ {
  "id" : 100004,
  "name" : "Admin",
  "email" : "admin@gmail.com",
  "password" : "{noop}admin",
  "roles" : [ "ROLE_ADMIN" ],
  "restaurantVotedId" : 100001
}, {
  "id" : 100003,
  "name" : "User",
  "email" : "user@yandex.ru",
  "password" : "{noop}password",
  "roles" : [ "ROLE_USER" ],
  "restaurantVotedId" : 100000
} ]
```

### [Get user](#_get_user) {#_get_user}

#### [curl request](#_curl_request_8) {#_curl_request_8}

``` {.highlightjs .highlight}
$ curl 'http://localhost:8080/users/100004' -i -u 'admin@gmail.com:admin' -X GET
```

#### [Request structure](#_request_structure_8) {#_request_structure_8}

``` {.highlightjs .highlight .nowrap}
GET /users/100004 HTTP/1.1
Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
Host: localhost:8080
```

#### [Response structure](#_response_structure_8) {#_response_structure_8}

``` {.highlightjs .highlight .nowrap}
HTTP/1.1 200 OK
Pragma: no-cache
X-XSS-Protection: 1; mode=block
Expires: 0
X-Frame-Options: DENY
X-Content-Type-Options: nosniff
Content-Type: application/json;charset=UTF-8
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Content-Length: 169

{
  "id" : 100004,
  "name" : "Admin",
  "email" : "admin@gmail.com",
  "password" : "{noop}admin",
  "roles" : [ "ROLE_ADMIN" ],
  "restaurantVotedId" : 100001
}
```

### [Get user by email](#_get_user_by_email) {#_get_user_by_email}

#### [curl request](#_curl_request_9) {#_curl_request_9}

``` {.highlightjs .highlight}
$ curl 'http://localhost:8080/users/by?email=user@yandex.ru' -i -u 'admin@gmail.com:admin' -X GET
```

#### [Request structure](#_request_structure_9) {#_request_structure_9}

``` {.highlightjs .highlight .nowrap}
GET /users/by?email=user@yandex.ru HTTP/1.1
Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
Host: localhost:8080
```

#### [Response structure](#_response_structure_9) {#_response_structure_9}

``` {.highlightjs .highlight .nowrap}
HTTP/1.1 200 OK
Pragma: no-cache
X-XSS-Protection: 1; mode=block
Expires: 0
X-Frame-Options: DENY
X-Content-Type-Options: nosniff
Content-Type: application/json;charset=UTF-8
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Content-Length: 169

{
  "id" : 100003,
  "name" : "User",
  "email" : "user@yandex.ru",
  "password" : "{noop}password",
  "roles" : [ "ROLE_USER" ],
  "restaurantVotedId" : 100000
}
```

### [Delete user](#_delete_user) {#_delete_user}

#### [curl request](#_curl_request_10) {#_curl_request_10}

``` {.highlightjs .highlight}
$ curl 'http://localhost:8080/users/100003' -i -u 'admin@gmail.com:admin' -X DELETE
```

#### [Request structure](#_request_structure_10) {#_request_structure_10}

``` {.highlightjs .highlight .nowrap}
DELETE /users/100003 HTTP/1.1
Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
Host: localhost:8080
```

#### [Response structure](#_response_structure_10) {#_response_structure_10}

``` {.highlightjs .highlight .nowrap}
HTTP/1.1 204 No Content
Pragma: no-cache
X-XSS-Protection: 1; mode=block
Expires: 0
X-Frame-Options: DENY
X-Content-Type-Options: nosniff
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
```

### [Create user](#_create_user) {#_create_user}

#### [curl request](#_curl_request_11) {#_curl_request_11}

``` {.highlightjs .highlight}
$ curl 'http://localhost:8080/users/' -i -u 'admin@gmail.com:admin' -X POST \
    -H 'Content-Type: application/json;charset=UTF-8' \
    -d '{
  "name" : "NewUser",
  "email" : "user@gmail.ru",
  "password" : "newPassword",
  "roles" : [ "ROLE_USER" ],
  "restaurantVotedId" : 100000
}'
```

#### [Request structure](#_request_structure_11) {#_request_structure_11}

``` {.highlightjs .highlight .nowrap}
POST /users/ HTTP/1.1
Content-Length: 150
Content-Type: application/json;charset=UTF-8
Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
Host: localhost:8080

{
  "name" : "NewUser",
  "email" : "user@gmail.ru",
  "password" : "newPassword",
  "roles" : [ "ROLE_USER" ],
  "restaurantVotedId" : 100000
}
```

#### [Response structure](#_response_structure_11) {#_response_structure_11}

``` {.highlightjs .highlight .nowrap}
HTTP/1.1 201 Created
Pragma: no-cache
X-XSS-Protection: 1; mode=block
Expires: 0
X-Frame-Options: DENY
X-Content-Type-Options: nosniff
Location: http://localhost:8080/users/100011
Content-Type: application/json;charset=UTF-8
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Content-Length: 168

{
  "id" : 100011,
  "name" : "NewUser",
  "email" : "user@gmail.ru",
  "password" : "newPassword",
  "roles" : [ "ROLE_USER" ],
  "restaurantVotedId" : 100000
}
```

### [Update user](#_update_user) {#_update_user}

#### [curl request](#_curl_request_12) {#_curl_request_12}

``` {.highlightjs .highlight}
$ curl 'http://localhost:8080/users/' -i -u 'user@yandex.ru:password' -X PUT \
    -H 'Content-Type: application/json;charset=UTF-8' \
    -d '{
  "id" : 100003,
  "name" : "UpdatedName",
  "email" : "user@yandex.ru",
  "password" : "password",
  "roles" : [ "ROLE_USER" ],
  "restaurantVotedId" : 100000
}'
```

#### [Request structure](#_request_structure_12) {#_request_structure_12}

``` {.highlightjs .highlight .nowrap}
PUT /users/ HTTP/1.1
Content-Length: 170
Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ=
Content-Type: application/json;charset=UTF-8
Host: localhost:8080

{
  "id" : 100003,
  "name" : "UpdatedName",
  "email" : "user@yandex.ru",
  "password" : "password",
  "roles" : [ "ROLE_USER" ],
  "restaurantVotedId" : 100000
}
```

#### [Response structure](#_response_structure_12) {#_response_structure_12}

``` {.highlightjs .highlight .nowrap}
HTTP/1.1 204 No Content
Pragma: no-cache
X-XSS-Protection: 1; mode=block
Expires: 0
X-Frame-Options: DENY
X-Content-Type-Options: nosniff
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
```

[Restaurant REST service](#_restaurant_rest_service) {#_restaurant_rest_service}
----------------------------------------------------

### [Get restaurant](#_get_restaurant) {#_get_restaurant}

#### [curl request](#_curl_request_13) {#_curl_request_13}

``` {.highlightjs .highlight}
$ curl 'http://localhost:8080/restaurants/100000' -i -u 'admin@gmail.com:admin' -X GET
```

#### [Request structure](#_request_structure_13) {#_request_structure_13}

``` {.highlightjs .highlight .nowrap}
GET /restaurants/100000 HTTP/1.1
Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
Host: localhost:8080
```

#### [Response structure](#_response_structure_13) {#_response_structure_13}

``` {.highlightjs .highlight .nowrap}
HTTP/1.1 200 OK
Pragma: no-cache
X-XSS-Protection: 1; mode=block
Expires: 0
X-Frame-Options: DENY
X-Content-Type-Options: nosniff
Content-Length: 60
Content-Type: application/json;charset=UTF-8
Cache-Control: no-cache, no-store, max-age=0, must-revalidate

{
  "id" : 100000,
  "name" : "Kolizey",
  "votes" : 1
}
```

### [Delete restaurant](#_delete_restaurant) {#_delete_restaurant}

#### [curl request](#_curl_request_14) {#_curl_request_14}

``` {.highlightjs .highlight}
$ curl 'http://localhost:8080/restaurants/100000' -i -u 'admin@gmail.com:admin' -X DELETE
```

#### [Request structure](#_request_structure_14) {#_request_structure_14}

``` {.highlightjs .highlight .nowrap}
DELETE /restaurants/100000 HTTP/1.1
Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
Host: localhost:8080
```

#### [Response structure](#_response_structure_14) {#_response_structure_14}

``` {.highlightjs .highlight .nowrap}
HTTP/1.1 204 No Content
Pragma: no-cache
X-XSS-Protection: 1; mode=block
Expires: 0
X-Frame-Options: DENY
X-Content-Type-Options: nosniff
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
```

### [Create restaurant](#_create_restaurant) {#_create_restaurant}

#### [curl request](#_curl_request_15) {#_curl_request_15}

``` {.highlightjs .highlight}
$ curl 'http://localhost:8080/restaurants/' -i -u 'admin@gmail.com:admin' -X POST \
    -H 'Content-Type: application/json;charset=UTF-8' \
    -d '{
  "name" : "Shashlykoff",
  "votes" : 3
}'
```

#### [Request structure](#_request_structure_15) {#_request_structure_15}

``` {.highlightjs .highlight .nowrap}
POST /restaurants/ HTTP/1.1
Content-Type: application/json;charset=UTF-8
Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
Content-Length: 46
Host: localhost:8080

{
  "name" : "Shashlykoff",
  "votes" : 3
}
```

#### [Response structure](#_response_structure_15) {#_response_structure_15}

``` {.highlightjs .highlight .nowrap}
HTTP/1.1 201 Created
Pragma: no-cache
Content-Length: 64
Location: http://localhost:8080/restaurants/100011
X-XSS-Protection: 1; mode=block
Expires: 0
X-Frame-Options: DENY
X-Content-Type-Options: nosniff
Content-Type: application/json;charset=UTF-8
Cache-Control: no-cache, no-store, max-age=0, must-revalidate

{
  "id" : 100011,
  "name" : "Shashlykoff",
  "votes" : 3
}
```

### [Update restaurant](#_update_restaurant) {#_update_restaurant}

#### [curl request](#_curl_request_16) {#_curl_request_16}

``` {.highlightjs .highlight}
$ curl 'http://localhost:8080/restaurants/' -i -u 'admin@gmail.com:admin' -X PUT \
    -H 'Content-Type: application/json;charset=UTF-8' \
    -d '{
  "id" : 100000,
  "name" : "UpdatedName",
  "votes" : 1
}'
```

#### [Request structure](#_request_structure_16) {#_request_structure_16}

``` {.highlightjs .highlight .nowrap}
PUT /restaurants/ HTTP/1.1
Content-Length: 64
Content-Type: application/json;charset=UTF-8
Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
Host: localhost:8080

{
  "id" : 100000,
  "name" : "UpdatedName",
  "votes" : 1
}
```

#### [Response structure](#_response_structure_16) {#_response_structure_16}

``` {.highlightjs .highlight .nowrap}
HTTP/1.1 204 No Content
Pragma: no-cache
X-XSS-Protection: 1; mode=block
Expires: 0
X-Frame-Options: DENY
X-Content-Type-Options: nosniff
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
```

[Dish REST service](#_dish_rest_service) {#_dish_rest_service}
----------------------------------------

### [Get dish](#_get_dish) {#_get_dish}

#### [curl request](#_curl_request_17) {#_curl_request_17}

``` {.highlightjs .highlight}
$ curl 'http://localhost:8080/dishes/100005' -i -u 'admin@gmail.com:admin' -X GET
```

#### [Request structure](#_request_structure_17) {#_request_structure_17}

``` {.highlightjs .highlight .nowrap}
GET /dishes/100005 HTTP/1.1
Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
Host: localhost:8080
```

#### [Response structure](#_response_structure_17) {#_response_structure_17}

``` {.highlightjs .highlight .nowrap}
HTTP/1.1 200 OK
Pragma: no-cache
X-XSS-Protection: 1; mode=block
Content-Length: 88
Expires: 0
X-Frame-Options: DENY
X-Content-Type-Options: nosniff
Content-Type: application/json;charset=UTF-8
Cache-Control: no-cache, no-store, max-age=0, must-revalidate

{
  "id" : 100005,
  "name" : "BeefSteak",
  "price" : 800,
  "restaurant" : null
}
```

### [Delete dish](#_delete_dish) {#_delete_dish}

#### [curl request](#_curl_request_18) {#_curl_request_18}

``` {.highlightjs .highlight}
$ curl 'http://localhost:8080/dishes/100005' -i -u 'admin@gmail.com:admin' -X DELETE
```

#### [Request structure](#_request_structure_18) {#_request_structure_18}

``` {.highlightjs .highlight .nowrap}
DELETE /dishes/100005 HTTP/1.1
Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
Host: localhost:8080
```

#### [Response structure](#_response_structure_18) {#_response_structure_18}

``` {.highlightjs .highlight .nowrap}
HTTP/1.1 204 No Content
Pragma: no-cache
X-XSS-Protection: 1; mode=block
Expires: 0
X-Frame-Options: DENY
X-Content-Type-Options: nosniff
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
```

### [Create dish](#_create_dish) {#_create_dish}

#### [curl request](#_curl_request_19) {#_curl_request_19}

``` {.highlightjs .highlight}
$ curl 'http://localhost:8080/dishes/100000' -i -u 'admin@gmail.com:admin' -X POST \
    -H 'Content-Type: application/json;charset=UTF-8' \
    -d '{
  "name" : "newDish",
  "price" : 1000,
  "restaurant" : {
    "id" : 100000,
    "name" : "Kolizey",
    "votes" : 1
  }
}'
```

#### [Request structure](#_request_structure_19) {#_request_structure_19}

``` {.highlightjs .highlight .nowrap}
POST /dishes/100000 HTTP/1.1
Content-Type: application/json;charset=UTF-8
Content-Length: 133
Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
Host: localhost:8080

{
  "name" : "newDish",
  "price" : 1000,
  "restaurant" : {
    "id" : 100000,
    "name" : "Kolizey",
    "votes" : 1
  }
}
```

#### [Response structure](#_response_structure_19) {#_response_structure_19}

``` {.highlightjs .highlight .nowrap}
HTTP/1.1 201 Created
Content-Length: 151
Pragma: no-cache
Location: http://localhost:8080/dishes/100011
X-XSS-Protection: 1; mode=block
Expires: 0
X-Frame-Options: DENY
X-Content-Type-Options: nosniff
Content-Type: application/json;charset=UTF-8
Cache-Control: no-cache, no-store, max-age=0, must-revalidate

{
  "id" : 100011,
  "name" : "newDish",
  "price" : 1000,
  "restaurant" : {
    "id" : 100000,
    "name" : "Kolizey",
    "votes" : 1
  }
}
```

### [Update dish](#_update_dish) {#_update_dish}

#### [curl request](#_curl_request_20) {#_curl_request_20}

``` {.highlightjs .highlight}
$ curl 'http://localhost:8080/dishes/100005' -i -u 'admin@gmail.com:admin' -X PUT \
    -H 'Content-Type: application/json;charset=UTF-8' \
    -d '{
  "id" : 100005,
  "name" : "updatedDish",
  "price" : 1000
}'
```

#### [Request structure](#_request_structure_20) {#_request_structure_20}

``` {.highlightjs .highlight .nowrap}
PUT /dishes/100005 HTTP/1.1
Content-Type: application/json;charset=UTF-8
Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
Content-Length: 67
Host: localhost:8080

{
  "id" : 100005,
  "name" : "updatedDish",
  "price" : 1000
}
```

#### [Response structure](#_response_structure_20) {#_response_structure_20}

``` {.highlightjs .highlight .nowrap}
HTTP/1.1 204 No Content
Pragma: no-cache
X-XSS-Protection: 1; mode=block
Expires: 0
X-Frame-Options: DENY
X-Content-Type-Options: nosniff
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
```

Last updated 2019-01-29 15:34:16 NOVT
