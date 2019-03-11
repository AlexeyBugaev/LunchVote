<div id="header">

# Lunchvote REST Service API Guide

<div id="toc" class="toc2">

<div id="toctitle">Table of Contents</div>

*   [Description](#description)
*   [Resources](#resources)
    *   [General REST service](#general-rest-service)
        *   [Get all restaurants](#get-all-restaurants)
        *   [Get all dishes by restaurant id](#get-all-dishes-by-restaurant-id)
        *   [Vote for restaurant](#vote-for-restaurant)
        *   [Vote for same restaurant twice](#vote-for-same-restaurant-twice)
        *   [Change vote after 11 a.m.](#change-vote-after-11-a.m.)
        *   [Get restaurant selected by vote](#get-restaurant-selected-by-vote)
        *   [Get vote history](#get-vote-history)
        *   [Clear vote history](#clear-vote-history)
        *   [Clear voting data](#clear-voting-data)
        
    *   [User REST service](#user-rest-service)
        *   [Get all users](#get-all-users)
        *   [Get user](#get-user)
        *   [Get user by email](#get-user-by-email)
        *   [Delete user](#delete-user)
        *   [Create user](#create-user)
        *   [Update user](#update-user)

    *   [Restaurant REST service](#restaurant-rest-service)
        *   [Get restaurant](#get-restaurant)
        *   [Delete restaurant](#delete-restaurant)
        *   [Create restaurant](#create-restaurant)
        *   [Update restaurant](#update-restaurant)
        
    *   [Dish REST service](#dish-rest-service)
        *   [Get dish](#get-dish)
        *   [Delete dish](#delete-dish)
        *   [Create dish](#create-dish)
        *   [Update dish](#update-dish)

</div>

</div>

<div id="content">

<div class="sect1">

##  [Description](#Lunchvote-REST-Service-API-Guide)

<div class="sectionbody">

<div class="ulist">

*   API provides RESTful web service that let users to make a vote for restaurant where to have a lunch and get the voted restaurant.

*   Each restaurant has list of dishes with prices.

*   Two user roles are supported: user and admin.

*   User can have a look on each restaurant menu, vote for restaurant, update the profile.

*   User cannot change his vote after 11 a.m.

*   User cannot vote for the same restaurant twice.

*   There is a history for votes that were made by a users.

*   Each history contains information on date of the vote, restaurant and list of dishes, user ID who made this vote.

*   Users can look through the history of his votes.

*   Admin can create/update/delete restaurants, dishes and users profile. Admin can clear vote data and history of votes.

</div>

</div>

</div>

# [Resources](#Lunchvote-REST-Service-API-Guide)
<div class="sect1">

## [General REST service](#Lunchvote-REST-Service-API-Guide)

<div class="sectionbody">

<div class="sect2">

### [Get all restaurants](#Lunchvote-REST-Service-API-Guide)

<div class="sect3">

#### curl request

<div class="listingblock">

<div class="content">

    $ curl 'http://localhost:8080/rest/restaurants/' -i -u 'user@yandex.ru:password' -X GET

</div>

</div>

</div>

<div class="sect3">

#### Request structure

<div class="listingblock">

<div class="content">

    GET /rest/restaurants/ HTTP/1.1
    Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ=
    Host: localhost:8080

</div>

</div>

</div>

<div class="sect3">

#### Response structure

<div class="listingblock">

<div class="content">

    HTTP/1.1 200 OK
    Pragma: no-cache
    X-XSS-Protection: 1; mode=block
    Expires: 0
    X-Frame-Options: DENY
    X-Content-Type-Options: nosniff
    Content-Type: application/json;charset=UTF-8
    Cache-Control: no-cache, no-store, max-age=0, must-revalidate
    Content-Length: 189

    [ {
      "id" : 100001,
      "name" : "BeefHouse",
      "votes" : 0
    }, {
      "id" : 100003,
      "name" : "Kolizey",
      "votes" : 0
    }, {
      "id" : 100002,
      "name" : "Shinok",
      "votes" : 0
    } ]

</div>

</div>

</div>

</div>

<div class="sect2">

### [Get all dishes by restaurant id](#Lunchvote-REST-Service-API-Guide)

<div class="sect3">

#### curl request

<div class="listingblock">

<div class="content">

    $ curl 'http://localhost:8080/rest/dishes/100001/getAll' -i -u 'admin@gmail.com:admin' -X GET

</div>

</div>

</div>

<div class="sect3">

#### Request structure

<div class="listingblock">

<div class="content">

    GET /rest/dishes/100001/getAll HTTP/1.1
    Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
    Host: localhost:8080

</div>

</div>

</div>

<div class="sect3">

#### Response structure

<div class="listingblock">

<div class="content">

    HTTP/1.1 200 OK
    Pragma: no-cache
    X-XSS-Protection: 1; mode=block
    Expires: 0
    X-Frame-Options: DENY
    X-Content-Type-Options: nosniff
    Content-Type: application/json;charset=UTF-8
    Cache-Control: no-cache, no-store, max-age=0, must-revalidate
    Content-Length: 279

    [ {
      "id" : 100006,
      "name" : "BeefSteak",
      "price" : 1200,
      "restaurant" : null
    }, {
      "id" : 100008,
      "name" : "GrilledChicken",
      "price" : 800,
      "restaurant" : null
    }, {
      "id" : 100007,
      "name" : "PorkSteak",
      "price" : 1000,
      "restaurant" : null
    } ]

</div>

</div>

</div>

</div>

<div class="sect2">

### [Vote for restaurant](#Lunchvote-REST-Service-API-Guide)

<div class="sect3">

#### curl request

<div class="listingblock">

<div class="content">

    $ curl 'http://localhost:8080/rest/restaurants/100002/vote' -i -u 'user@yandex.ru:password' -X GET

</div>

</div>

</div>

<div class="sect3">

#### Request structure

<div class="listingblock">

<div class="content">

    GET /rest/restaurants/100002/vote HTTP/1.1
    Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ=
    Host: localhost:8080

</div>

</div>

</div>

<div class="sect3">

#### Response structure

<div class="listingblock">

<div class="content">

    HTTP/1.1 200 OK
    Pragma: no-cache
    X-XSS-Protection: 1; mode=block
    Expires: 0
    X-Frame-Options: DENY
    X-Content-Type-Options: nosniff
    Content-Type: application/json;charset=UTF-8
    Cache-Control: no-cache, no-store, max-age=0, must-revalidate
    Content-Length: 37

    {
      "Restaurant voted" : "Shinok"
    }

</div>

</div>

</div>

</div>

<div class="sect2">

### [Vote for same restaurant twice](#Lunchvote-REST-Service-API-Guide)

<div class="sect3">

#### curl request

<div class="listingblock">

<div class="content">

    $ curl 'http://localhost:8080/rest/restaurants/100001/vote' -i -u 'voted@yandex.ru:voted' -X GET

</div>

</div>

</div>

<div class="sect3">

#### Request structure

<div class="listingblock">

<div class="content">

    GET /rest/restaurants/100001/vote HTTP/1.1
    Authorization: Basic dm90ZWRAeWFuZGV4LnJ1OnZvdGVk
    Host: localhost:8080

</div>

</div>

</div>

<div class="sect3">

#### Response structure

<div class="listingblock">

<div class="content">

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

</div>

</div>

</div>

</div>

<div class="sect2">

### [Change vote after 11 a.m.](#Lunchvote-REST-Service-API-Guide)

<div class="sect3">

#### curl request

<div class="listingblock">

<div class="content">

    $ curl 'http://localhost:8080/rest/restaurants/100002/vote' -i -u 'voted@yandex.ru:voted' -X GET

</div>

</div>

</div>

<div class="sect3">

#### Request structure

<div class="listingblock">

<div class="content">

    GET /rest/restaurants/100002/vote HTTP/1.1
    Authorization: Basic dm90ZWRAeWFuZGV4LnJ1OnZvdGVk
    Host: localhost:8080

</div>

</div>

</div>

<div class="sect3">

#### Response structure

<div class="listingblock">

<div class="content">

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
      "errorMessage" : "Change vote is unavailable after 11 a.m."
    }

</div>

</div>

</div>

</div>

<div class="sect2">

### [Get restaurant selected by vote](#Lunchvote-REST-Service-API-Guide)

<div class="sect3">

#### curl request

<div class="listingblock">

<div class="content">

    $ curl 'http://localhost:8080/rest/restaurants/getVoted' -i -u 'user@yandex.ru:password' -X GET

</div>

</div>

</div>

<div class="sect3">

#### Request structure

<div class="listingblock">

<div class="content">

    GET /rest/restaurants/getVoted HTTP/1.1
    Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ=
    Host: localhost:8080

</div>

</div>

</div>

<div class="sect3">

#### Response structure

<div class="listingblock">

<div class="content">

    HTTP/1.1 200 OK
    Pragma: no-cache
    X-XSS-Protection: 1; mode=block
    Expires: 0
    X-Frame-Options: DENY
    X-Content-Type-Options: nosniff
    Content-Type: application/json;charset=UTF-8
    Cache-Control: no-cache, no-store, max-age=0, must-revalidate
    Content-Length: 59

    {
      "id" : 100002,
      "name" : "Shinok",
      "votes" : 1
    }

</div>

</div>

</div>

</div>

<div class="sect2">

### [Get vote history](#Lunchvote-REST-Service-API-Guide)

<div class="sect3">

#### curl request

<div class="listingblock">

<div class="content">

    $ curl 'http://localhost:8080/rest/users/100004/voteHistory' -i -u 'admin@gmail.com:admin' -X GET

</div>

</div>

</div>

<div class="sect3">

#### Request structure

<div class="listingblock">

<div class="content">

    GET /rest/users/100004/voteHistory HTTP/1.1
    Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
    Host: localhost:8080

</div>

</div>

</div>

<div class="sect3">

#### Response structure

<div class="listingblock">

<div class="content">

    HTTP/1.1 200 OK
    Pragma: no-cache
    X-XSS-Protection: 1; mode=block
    Expires: 0
    X-Frame-Options: DENY
    X-Content-Type-Options: nosniff
    Content-Length: 468
    Content-Type: application/json;charset=UTF-8
    Cache-Control: no-cache, no-store, max-age=0, must-revalidate
    
    [ {
      "id" : 100016,
      "userId" : 100004,
      "date" : "2019-03-11",
      "dishName" : "BeefSteak",
      "price" : 1200,
      "restaurantName" : "BeefHouse"
    }, {
      "id" : 100017,
      "userId" : 100004,
      "date" : "2019-03-11",
      "dishName" : "GrilledChicken",
      "price" : 800,
      "restaurantName" : "BeefHouse"
    }, {
      "id" : 100018,
      "userId" : 100004,
      "date" : "2019-03-11",
      "dishName" : "PorkSteak",
      "price" : 1000,
      "restaurantName" : "BeefHouse"
    } ]

</div>

</div>

</div>

</div>

<div class="sect2">

### [Clear vote history](#Lunchvote-REST-Service-API-Guide)

<div class="sect3">

#### curl request

<div class="listingblock">

<div class="content">

    $ curl 'http://localhost:8080/rest/users/clearVoteHistory' -i -u 'admin@gmail.com:admin' -X GET

</div>

</div>

</div>

<div class="sect3">

#### Request structure

<div class="listingblock">

<div class="content">

    GET /rest/users/clearVoteHistory HTTP/1.1
    Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
    Host: localhost:8080

</div>

</div>

</div>

<div class="sect3">

#### Response structure

<div class="listingblock">

<div class="content">

    HTTP/1.1 200 OK
    Pragma: no-cache
    X-XSS-Protection: 1; mode=block
    Expires: 0
    X-Frame-Options: DENY
    X-Content-Type-Options: nosniff
    Content-Length: 28
    Content-Type: application/json;charset=UTF-8
    Cache-Control: no-cache, no-store, max-age=0, must-revalidate
    
    "Voting history was cleared"

</div>

</div>

</div>

</div>

</div>

</div>

### [Clear voting data](#Lunchvote-REST-Service-API-Guide)

<div class="sect3">

#### curl request

<div class="listingblock">

<div class="content">

    $ curl 'http://localhost:8080/rest/restaurants/clearVotingData' -i -u 'admin@gmail.com:admin' -X GET

</div>

</div>

</div>

<div class="sect3">

#### Request structure

<div class="listingblock">

<div class="content">

    GET /rest/restaurants/clearVotingData HTTP/1.1
    Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
    Host: localhost:8080

</div>

</div>

</div>

<div class="sect3">

#### Response structure

<div class="listingblock">

<div class="content">

    HTTP/1.1 200 OK
    Pragma: no-cache
    X-XSS-Protection: 1; mode=block
    Expires: 0
    X-Frame-Options: DENY
    X-Content-Type-Options: nosniff
    Content-Type: application/json;charset=UTF-8
    Cache-Control: no-cache, no-store, max-age=0, must-revalidate
    Content-Length: 25
    
    "Voting data was cleared"

</div>

</div>

</div>

</div>

</div>

</div>

<div class="sect1">

## [User REST service](#Lunchvote-REST-Service-API-Guide)

<div class="sectionbody">

<div class="sect2">

### [Get all users](#Lunchvote-REST-Service-API-Guide)

<div class="sect3">

#### curl request

<div class="listingblock">

<div class="content">

    $ curl 'http://localhost:8080/rest/users/' -i -u 'admin@gmail.com:admin' -X GET

</div>

</div>

</div>

<div class="sect3">

#### Request structure

<div class="listingblock">

<div class="content">

    GET /rest/users/ HTTP/1.1
    Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
    Host: localhost:8080

</div>

</div>

</div>

<div class="sect3">

#### Response structure

<div class="listingblock">

<div class="content">

    HTTP/1.1 200 OK
    Pragma: no-cache
    X-XSS-Protection: 1; mode=block
    Expires: 0
    X-Frame-Options: DENY
    X-Content-Type-Options: nosniff
    Content-Length: 490
    Content-Type: application/json;charset=UTF-8
    Cache-Control: no-cache, no-store, max-age=0, must-revalidate

    [ {
      "id" : 100005,
      "name" : "Admin",
      "email" : "admin@gmail.com",
      "roles" : [ "ROLE_ADMIN" ],
      "restaurantVotedId" : 100000,
      "voteMade" : false
    }, {
      "id" : 100004,
      "name" : "User",
      "email" : "user@yandex.ru",
      "roles" : [ "ROLE_USER" ],
      "restaurantVotedId" : 100000,
      "voteMade" : false
    }, {
      "id" : 100015,
      "name" : "VotedUser",
      "email" : "voted@yandex.ru",
      "roles" : [ "ROLE_USER" ],
      "restaurantVotedId" : 100001,
      "voteMade" : true
    } ]

</div>

</div>

</div>

</div>

<div class="sect2">

### [Get user](#Lunchvote-REST-Service-API-Guide)

<div class="sect3">

#### curl request

<div class="listingblock">

<div class="content">

    $ curl 'http://localhost:8080/rest/users/100005' -i -u 'admin@gmail.com:admin' -X GET

</div>

</div>

</div>

<div class="sect3">

#### Request structure

<div class="listingblock">

<div class="content">

    GET /rest/users/100005 HTTP/1.1
    Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
    Host: localhost:8080

</div>

</div>

</div>

<div class="sect3">

#### Response structure

<div class="listingblock">

<div class="content">

    HTTP/1.1 200 OK
    Pragma: no-cache
    X-XSS-Protection: 1; mode=block
    Expires: 0
    X-Frame-Options: DENY
    X-Content-Type-Options: nosniff
    Content-Length: 161
    Content-Type: application/json;charset=UTF-8
    Cache-Control: no-cache, no-store, max-age=0, must-revalidate

    {
      "id" : 100005,
      "name" : "Admin",
      "email" : "admin@gmail.com",
      "roles" : [ "ROLE_ADMIN" ],
      "restaurantVotedId" : 100000,
      "voteMade" : false
    }

</div>

</div>

</div>

</div>

<div class="sect2">

### [Get user by email](#Lunchvote-REST-Service-API-Guide)

<div class="sect3">

#### curl request

<div class="listingblock">

<div class="content">

    $ curl 'http://localhost:8080/rest/users/by?email=user@yandex.ru' -i -u 'admin@gmail.com:admin' -X GET

</div>

</div>

</div>

<div class="sect3">

#### Request structure

<div class="listingblock">

<div class="content">

    GET /rest/users/by?email=user@yandex.ru HTTP/1.1
    Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
    Host: localhost:8080

</div>

</div>

</div>

<div class="sect3">

#### Response structure

<div class="listingblock">

<div class="content">

    HTTP/1.1 200 OK
    Pragma: no-cache
    X-XSS-Protection: 1; mode=block
    Expires: 0
    X-Frame-Options: DENY
    X-Content-Type-Options: nosniff
    Content-Type: application/json;charset=UTF-8
    Cache-Control: no-cache, no-store, max-age=0, must-revalidate
    Content-Length: 158

    {
      "id" : 100004,
      "name" : "User",
      "email" : "user@yandex.ru",
      "roles" : [ "ROLE_USER" ],
      "restaurantVotedId" : 100000,
      "voteMade" : false
    }

</div>

</div>

</div>

</div>

<div class="sect2">

### [Delete user](#Lunchvote-REST-Service-API-Guide)

<div class="sect3">

#### curl request

<div class="listingblock">

<div class="content">

    $ curl 'http://localhost:8080/rest/users/100004' -i -u 'admin@gmail.com:admin' -X DELETE

</div>

</div>

</div>

<div class="sect3">

#### Request structure

<div class="listingblock">

<div class="content">

    DELETE /rest/users/100004 HTTP/1.1
    Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
    Host: localhost:8080

</div>

</div>

</div>

<div class="sect3">

#### Response structure

<div class="listingblock">

<div class="content">

    HTTP/1.1 204 No Content
    Pragma: no-cache
    X-XSS-Protection: 1; mode=block
    Expires: 0
    X-Frame-Options: DENY
    X-Content-Type-Options: nosniff
    Cache-Control: no-cache, no-store, max-age=0, must-revalidate

</div>

</div>

</div>

</div>

<div class="sect2">

### [Create user](#Lunchvote-REST-Service-API-Guide)

<div class="sect3">

#### curl request

<div class="listingblock">

<div class="content">

    $ curl 'http://localhost:8080/rest/users/' -i -u 'admin@gmail.com:admin' -X POST \
        -H 'Content-Type: application/json;charset=UTF-8' \
        -d '{
      "name" : "NewUser",
      "email" : "user@gmail.ru",
      "roles" : [ "ROLE_USER" ],
      "restaurantVotedId" : 100000,
      "voteMade" : false,
      "password" : "newPassword"
    }'

</div>

</div>

</div>

<div class="sect3">

#### Request structure

<div class="listingblock">

<div class="content">

    POST /rest/users/ HTTP/1.1
    Content-Length: 173
    Content-Type: application/json;charset=UTF-8
    Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
    Host: localhost:8080

    {
      "name" : "NewUser",
      "email" : "user@gmail.ru",
      "roles" : [ "ROLE_USER" ],
      "restaurantVotedId" : 100000,
      "voteMade" : false,
      "password" : "newPassword"
    }

</div>

</div>

</div>

<div class="sect3">

#### Response structure

<div class="listingblock">

<div class="content">

    HTTP/1.1 201 Created
    Pragma: no-cache
    X-XSS-Protection: 1; mode=block
    Expires: 0
    X-Frame-Options: DENY
    X-Content-Type-Options: nosniff
    Content-Length: 160
    Location: http://localhost:8080/rest/users/100016
    Content-Type: application/json;charset=UTF-8
    Cache-Control: no-cache, no-store, max-age=0, must-revalidate

    {
      "id" : 100016,
      "name" : "NewUser",
      "email" : "user@gmail.ru",
      "roles" : [ "ROLE_USER" ],
      "restaurantVotedId" : 100000,
      "voteMade" : false
    }

</div>

</div>

</div>

</div>

<div class="sect2">

### [Update user](#Lunchvote-REST-Service-API-Guide)

<div class="sect3">

#### curl request

<div class="listingblock">

<div class="content">

    $ curl 'http://localhost:8080/rest/users/100004' -i -u 'user@yandex.ru:password' -X PUT \
        -H 'Content-Type: application/json;charset=UTF-8' \
        -d '{
      "id" : 100004,
      "name" : "UpdatedName",
      "email" : "user@yandex.ru",
      "roles" : [ "ROLE_USER" ],
      "restaurantVotedId" : 100000,
      "voteMade" : false
    }'

</div>

</div>

</div>

<div class="sect3">

#### Request structure

<div class="listingblock">

<div class="content">

    PUT /rest/users/100004 HTTP/1.1
    Content-Length: 165
    Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ=
    Content-Type: application/json;charset=UTF-8
    Host: localhost:8080

    {
      "id" : 100004,
      "name" : "UpdatedName",
      "email" : "user@yandex.ru",
      "roles" : [ "ROLE_USER" ],
      "restaurantVotedId" : 100000,
      "voteMade" : false
    }

</div>

</div>

</div>

<div class="sect3">

#### Response structure

<div class="listingblock">

<div class="content">

    HTTP/1.1 204 No Content
    Pragma: no-cache
    X-XSS-Protection: 1; mode=block
    Expires: 0
    X-Frame-Options: DENY
    X-Content-Type-Options: nosniff
    Cache-Control: no-cache, no-store, max-age=0, must-revalidate

</div>

</div>

</div>

</div>

</div>

</div>

<div class="sect1">

## [Restaurant REST service](#Lunchvote-REST-Service-API-Guide)

<div class="sectionbody">

<div class="sect2">

### [Get restaurant](#Lunchvote-REST-Service-API-Guide)

<div class="sect3">

#### curl request

<div class="listingblock">

<div class="content">

    $ curl 'http://localhost:8080/rest/restaurants/100001' -i -u 'admin@gmail.com:admin' -X GET

</div>

</div>

</div>

<div class="sect3">

#### Request structure

<div class="listingblock">

<div class="content">

    GET /rest/restaurants/100001 HTTP/1.1
    Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
    Host: localhost:8080

</div>

</div>

</div>

<div class="sect3">

#### Response structure

<div class="listingblock">

<div class="content">

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
      "votes" : 0
    }

</div>

</div>

</div>

</div>

<div class="sect2">

### [Delete restaurant](#Lunchvote-REST-Service-API-Guide)

<div class="sect3">

#### curl request

<div class="listingblock">

<div class="content">

    $ curl 'http://localhost:8080/rest/restaurants/100001' -i -u 'admin@gmail.com:admin' -X DELETE

</div>

</div>

</div>

<div class="sect3">

#### Request structure

<div class="listingblock">

<div class="content">

    DELETE /rest/restaurants/100001 HTTP/1.1
    Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
    Host: localhost:8080

</div>

</div>

</div>

<div class="sect3">

#### Response structure

<div class="listingblock">

<div class="content">

    HTTP/1.1 204 No Content
    Pragma: no-cache
    X-XSS-Protection: 1; mode=block
    Expires: 0
    X-Frame-Options: DENY
    X-Content-Type-Options: nosniff
    Cache-Control: no-cache, no-store, max-age=0, must-revalidate

</div>

</div>

</div>

</div>

<div class="sect2">

### [Create restaurant](#Lunchvote-REST-Service-API-Guide)

<div class="sect3">

#### curl request

<div class="listingblock">

<div class="content">

    $ curl 'http://localhost:8080/rest/restaurants/' -i -u 'admin@gmail.com:admin' -X POST \
        -H 'Content-Type: application/json;charset=UTF-8' \
        -d '{
      "name" : "Shashlykoff",
      "votes" : 3
    }'

</div>

</div>

</div>

<div class="sect3">

#### Request structure

<div class="listingblock">

<div class="content">

    POST /rest/restaurants/ HTTP/1.1
    Content-Type: application/json;charset=UTF-8
    Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
    Content-Length: 46
    Host: localhost:8080

    {
      "name" : "Shashlykoff",
      "votes" : 3
    }

</div>

</div>

</div>

<div class="sect3">

#### Response structure

<div class="listingblock">

<div class="content">

    HTTP/1.1 201 Created
    Pragma: no-cache
    Content-Length: 64
    X-XSS-Protection: 1; mode=block
    Expires: 0
    X-Frame-Options: DENY
    X-Content-Type-Options: nosniff
    Content-Type: application/json;charset=UTF-8
    Location: http://localhost:8080/rest/restaurants/100016
    Cache-Control: no-cache, no-store, max-age=0, must-revalidate

    {
      "id" : 100016,
      "name" : "Shashlykoff",
      "votes" : 3
    }

</div>

</div>

</div>

</div>

<div class="sect2">

### [Update restaurant](#Lunchvote-REST-Service-API-Guide)

<div class="sect3">

#### curl request

<div class="listingblock">

<div class="content">

    $ curl 'http://localhost:8080/rest/restaurants/100001' -i -u 'admin@gmail.com:admin' -X PUT \
        -H 'Content-Type: application/json;charset=UTF-8' \
        -d '{
      "id" : 100001,
      "name" : "UpdatedName",
      "votes" : 0
    }'

</div>

</div>

</div>

<div class="sect3">

#### Request structure

<div class="listingblock">

<div class="content">

    PUT /rest/restaurants/100001 HTTP/1.1
    Content-Length: 64
    Content-Type: application/json;charset=UTF-8
    Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
    Host: localhost:8080

    {
      "id" : 100001,
      "name" : "UpdatedName",
      "votes" : 0
    }

</div>

</div>

</div>

<div class="sect3">

#### Response structure

<div class="listingblock">

<div class="content">

    HTTP/1.1 204 No Content
    Pragma: no-cache
    X-XSS-Protection: 1; mode=block
    Expires: 0
    X-Frame-Options: DENY
    X-Content-Type-Options: nosniff
    Cache-Control: no-cache, no-store, max-age=0, must-revalidate

</div>

</div>

</div>

</div>

</div>

</div>

<div class="sect1">

## [Dish REST service](#Lunchvote-REST-Service-API-Guide)

<div class="sectionbody">

<div class="sect2">

### [Get dish](#Lunchvote-REST-Service-API-Guide)

<div class="sect3">

#### curl request

<div class="listingblock">

<div class="content">

    $ curl 'http://localhost:8080/rest/dishes/100006' -i -u 'admin@gmail.com:admin' -X GET

</div>

</div>

</div>

<div class="sect3">

#### Request structure

<div class="listingblock">

<div class="content">

    GET /rest/dishes/100006 HTTP/1.1
    Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
    Host: localhost:8080

</div>

</div>

</div>

<div class="sect3">

#### Response structure

<div class="listingblock">

<div class="content">

    HTTP/1.1 200 OK
    Pragma: no-cache
    X-XSS-Protection: 1; mode=block
    Expires: 0
    X-Frame-Options: DENY
    X-Content-Type-Options: nosniff
    Content-Type: application/json;charset=UTF-8
    Content-Length: 89
    Cache-Control: no-cache, no-store, max-age=0, must-revalidate

    {
      "id" : 100006,
      "name" : "BeefSteak",
      "price" : 1200,
      "restaurant" : null
    }

</div>

</div>

</div>

</div>

<div class="sect2">

### [Delete dish](#Lunchvote-REST-Service-API-Guide)

<div class="sect3">

#### curl request

<div class="listingblock">

<div class="content">

    $ curl 'http://localhost:8080/rest/dishes/100006' -i -u 'admin@gmail.com:admin' -X DELETE

</div>

</div>

</div>

<div class="sect3">

#### Request structure

<div class="listingblock">

<div class="content">

    DELETE /rest/dishes/100006 HTTP/1.1
    Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
    Host: localhost:8080

</div>

</div>

</div>

<div class="sect3">

#### Response structure

<div class="listingblock">

<div class="content">

    HTTP/1.1 204 No Content
    Pragma: no-cache
    X-XSS-Protection: 1; mode=block
    Expires: 0
    X-Frame-Options: DENY
    X-Content-Type-Options: nosniff
    Cache-Control: no-cache, no-store, max-age=0, must-revalidate

</div>

</div>

</div>

</div>

<div class="sect2">

### [Create dish](#Lunchvote-REST-Service-API-Guide)

<div class="sect3">

#### curl request

<div class="listingblock">

<div class="content">

    $ curl 'http://localhost:8080/rest/dishes/100001' -i -u 'admin@gmail.com:admin' -X POST \
        -H 'Content-Type: application/json;charset=UTF-8' \
        -d '{
      "name" : "newDish",
      "price" : 1000,
      "restaurant" : {
        "id" : 100001,
        "name" : "BeefHouse",
        "votes" : 0
      }
    }'

</div>

</div>

</div>

<div class="sect3">

#### Request structure

<div class="listingblock">

<div class="content">

    POST /rest/dishes/100001 HTTP/1.1
    Content-Type: application/json;charset=UTF-8
    Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
    Host: localhost:8080
    Content-Length: 135

    {
      "name" : "newDish",
      "price" : 1000,
      "restaurant" : {
        "id" : 100001,
        "name" : "BeefHouse",
        "votes" : 0
      }
    }

</div>

</div>

</div>

<div class="sect3">

#### Response structure

<div class="listingblock">

<div class="content">

    HTTP/1.1 201 Created
    Pragma: no-cache
    Content-Length: 153
    X-XSS-Protection: 1; mode=block
    Expires: 0
    X-Frame-Options: DENY
    X-Content-Type-Options: nosniff
    Content-Type: application/json;charset=UTF-8
    Cache-Control: no-cache, no-store, max-age=0, must-revalidate
    Location: http://localhost:8080/rest/dishes/100016

    {
      "id" : 100016,
      "name" : "newDish",
      "price" : 1000,
      "restaurant" : {
        "id" : 100001,
        "name" : "BeefHouse",
        "votes" : 0
      }
    }

</div>

</div>

</div>

</div>

<div class="sect2">

### [Update dish](#Lunchvote-REST-Service-API-Guide)

<div class="sect3">

#### curl request

<div class="listingblock">

<div class="content">

    $ curl 'http://localhost:8080/rest/dishes/100006' -i -u 'admin@gmail.com:admin' -X PUT \
        -H 'Content-Type: application/json;charset=UTF-8' \
        -d '{
      "id" : 100006,
      "name" : "updatedDish",
      "price" : 1000
    }'

</div>

</div>

</div>

<div class="sect3">

#### Request structure

<div class="listingblock">

<div class="content">

    PUT /rest/dishes/100006 HTTP/1.1
    Content-Type: application/json;charset=UTF-8
    Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
    Content-Length: 67
    Host: localhost:8080

    {
      "id" : 100006,
      "name" : "updatedDish",
      "price" : 1000
    }

</div>

</div>

</div>

<div class="sect3">

#### Response structure

<div class="listingblock">

<div class="content">

    HTTP/1.1 204 No Content
    Pragma: no-cache
    X-XSS-Protection: 1; mode=block
    Expires: 0
    X-Frame-Options: DENY
    X-Content-Type-Options: nosniff
    Cache-Control: no-cache, no-store, max-age=0, must-revalidate

</div>

</div>

</div>

</div>

</div>

</div>

</div>

<div id="footer">

<div id="footer-text">Last updated 2019-03-10 19:59:07 NOVT</div>

</div>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.12.0/styles/github.min.css">  <script>hljs.initHighlighting()</script>