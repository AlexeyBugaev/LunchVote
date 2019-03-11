<div id="header">

# Lunchvote REST Service API Guide

<div id="toc" class="toc2">

<div id="toctitle">Table of Contents</div>

*   [Description](#description)
*   [Resources](#_resources)
    *   [General REST service](#_general_rest_service)
        *   [Get all restaurants](#_get_all_restaurants)
            *   [curl request](#_curl_request)
            *   [Request structure](#_request_structure)
            *   [Response structure](#_response_structure)
        *   [Get all dishes by restaurant id](#_get_all_dishes_by_restaurant_id)
            *   [curl request](#_curl_request_2)
            *   [Request structure](#_request_structure_2)
            *   [Response structure](#_response_structure_2)
        *   [Vote for restaurant](#_vote_for_restaurant)
            *   [curl request](#_curl_request_3)
            *   [Request structure](#_request_structure_3)
            *   [Response structure](#_response_structure_3)
        *   [Vote for same restaurant twice](#_vote_for_same_restaurant_twice)
            *   [curl request](#_curl_request_4)
            *   [Request structure](#_request_structure_4)
            *   [Response structure](#_response_structure_4)
        *   [Change vote after 11 a.m.](#_change_vote_after_11_a_m)
            *   [curl request](#_curl_request_5)
            *   [Request structure](#_request_structure_5)
            *   [Response structure](#_response_structure_5)
        *   [Get restaurant selected by vote](#_get_restaurant_selected_by_vote)
            *   [curl request](#_curl_request_6)
            *   [Request structure](#_request_structure_6)
            *   [Response structure](#_response_structure_6)
        *   [Get vote history](#_get_vote_history)
            *   [curl request](#_curl_request_7)
            *   [Request structure](#_request_structure_7)
            *   [Response structure](#_response_structure_7)
        *   [Clear vote history](#_clear_vote_history)
            *   [curl request](#_curl_request_8)
            *   [Request structure](#_request_structure_8)
            *   [Response structure](#_response_structure_8)
    *   [User REST service](#_user_rest_service)
        *   [Get all users](#_get_all_users)
            *   [curl request](#_curl_request_9)
            *   [Request structure](#_request_structure_9)
            *   [Response structure](#_response_structure_9)
        *   [Get user](#_get_user)
            *   [curl request](#_curl_request_10)
            *   [Request structure](#_request_structure_10)
            *   [Response structure](#_response_structure_10)
        *   [Get user by email](#_get_user_by_email)
            *   [curl request](#_curl_request_11)
            *   [Request structure](#_request_structure_11)
            *   [Response structure](#_response_structure_11)
        *   [Delete user](#_delete_user)
            *   [curl request](#_curl_request_12)
            *   [Request structure](#_request_structure_12)
            *   [Response structure](#_response_structure_12)
        *   [Create user](#_create_user)
            *   [curl request](#_curl_request_13)
            *   [Request structure](#_request_structure_13)
            *   [Response structure](#_response_structure_13)
        *   [Update user](#_update_user)
            *   [curl request](#_curl_request_14)
            *   [Request structure](#_request_structure_14)
            *   [Response structure](#_response_structure_14)
    *   [Restaurant REST service](#_restaurant_rest_service)
        *   [Get restaurant](#_get_restaurant)
            *   [curl request](#_curl_request_15)
            *   [Request structure](#_request_structure_15)
            *   [Response structure](#_response_structure_15)
        *   [Delete restaurant](#_delete_restaurant)
            *   [curl request](#_curl_request_16)
            *   [Request structure](#_request_structure_16)
            *   [Response structure](#_response_structure_16)
        *   [Create restaurant](#_create_restaurant)
            *   [curl request](#_curl_request_17)
            *   [Request structure](#_request_structure_17)
            *   [Response structure](#_response_structure_17)
        *   [Update restaurant](#_update_restaurant)
            *   [curl request](#_curl_request_18)
            *   [Request structure](#_request_structure_18)
            *   [Response structure](#_response_structure_18)
    *   [Dish REST service](#_dish_rest_service)
        *   [Get dish](#_get_dish)
            *   [curl request](#_curl_request_19)
            *   [Request structure](#_request_structure_19)
            *   [Response structure](#_response_structure_19)
        *   [Delete dish](#_delete_dish)
            *   [curl request](#_curl_request_20)
            *   [Request structure](#_request_structure_20)
            *   [Response structure](#_response_structure_20)
        *   [Create dish](#_create_dish)
            *   [curl request](#_curl_request_21)
            *   [Request structure](#_request_structure_21)
            *   [Response structure](#_response_structure_21)
        *   [Update dish](#_update_dish)
            *   [curl request](#_curl_request_22)
            *   [Request structure](#_request_structure_22)
            *   [Response structure](#_response_structure_22)

</div>

</div>

<div id="content">

<div class="sect1">

## Description

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

# [Resources](#_resources)

<div class="sect1">

## [General REST service](#_general_rest_service)

<div class="sectionbody">

<div class="sect2">

### [Get all restaurants](#_get_all_restaurants)

<div class="sect3">

#### [curl request](#_curl_request)

<div class="listingblock">

<div class="content">

    $ curl 'http://localhost:8080/rest/restaurants/' -i -u 'user@yandex.ru:password' -X GET

</div>

</div>

</div>

<div class="sect3">

#### [Request structure](#_request_structure)

<div class="listingblock">

<div class="content">

    GET /rest/restaurants/ HTTP/1.1
    Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ=
    Host: localhost:8080

</div>

</div>

</div>

<div class="sect3">

#### [Response structure](#_response_structure)

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

### [Get all dishes by restaurant id](#_get_all_dishes_by_restaurant_id)

<div class="sect3">

#### [curl request](#_curl_request_2)

<div class="listingblock">

<div class="content">

    $ curl 'http://localhost:8080/rest/dishes/100001/getAll' -i -u 'admin@gmail.com:admin' -X GET

</div>

</div>

</div>

<div class="sect3">

#### [Request structure](#_request_structure_2)

<div class="listingblock">

<div class="content">

    GET /rest/dishes/100001/getAll HTTP/1.1
    Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
    Host: localhost:8080

</div>

</div>

</div>

<div class="sect3">

#### [Response structure](#_response_structure_2)

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

### [Vote for restaurant](#_vote_for_restaurant)

<div class="sect3">

#### [curl request](#_curl_request_3)

<div class="listingblock">

<div class="content">

    $ curl 'http://localhost:8080/rest/restaurants/100002/vote' -i -u 'user@yandex.ru:password' -X GET

</div>

</div>

</div>

<div class="sect3">

#### [Request structure](#_request_structure_3)

<div class="listingblock">

<div class="content">

    GET /rest/restaurants/100002/vote HTTP/1.1
    Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ=
    Host: localhost:8080

</div>

</div>

</div>

<div class="sect3">

#### [Response structure](#_response_structure_3)

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

### [Vote for same restaurant twice](#_vote_for_same_restaurant_twice)

<div class="sect3">

#### [curl request](#_curl_request_4)

<div class="listingblock">

<div class="content">

    $ curl 'http://localhost:8080/rest/restaurants/100001/vote' -i -u 'voted@yandex.ru:voted' -X GET

</div>

</div>

</div>

<div class="sect3">

#### [Request structure](#_request_structure_4)

<div class="listingblock">

<div class="content">

    GET /rest/restaurants/100001/vote HTTP/1.1
    Authorization: Basic dm90ZWRAeWFuZGV4LnJ1OnZvdGVk
    Host: localhost:8080

</div>

</div>

</div>

<div class="sect3">

#### [Response structure](#_response_structure_4)

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

### [Change vote after 11 a.m.](#_change_vote_after_11_a_m)

<div class="sect3">

#### [curl request](#_curl_request_5)

<div class="listingblock">

<div class="content">

    $ curl 'http://localhost:8080/rest/restaurants/100002/vote' -i -u 'voted@yandex.ru:voted' -X GET

</div>

</div>

</div>

<div class="sect3">

#### [Request structure](#_request_structure_5)

<div class="listingblock">

<div class="content">

    GET /rest/restaurants/100002/vote HTTP/1.1
    Authorization: Basic dm90ZWRAeWFuZGV4LnJ1OnZvdGVk
    Host: localhost:8080

</div>

</div>

</div>

<div class="sect3">

#### [Response structure](#_response_structure_5)

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

### [Get restaurant selected by vote](#_get_restaurant_selected_by_vote)

<div class="sect3">

#### [curl request](#_curl_request_6)

<div class="listingblock">

<div class="content">

    $ curl 'http://localhost:8080/rest/restaurants/getVoted' -i -u 'user@yandex.ru:password' -X GET

</div>

</div>

</div>

<div class="sect3">

#### [Request structure](#_request_structure_6)

<div class="listingblock">

<div class="content">

    GET /rest/restaurants/getVoted HTTP/1.1
    Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ=
    Host: localhost:8080

</div>

</div>

</div>

<div class="sect3">

#### [Response structure](#_response_structure_6)

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

### [Get vote history](#_get_vote_history)

<div class="sect3">

#### [curl request](#_curl_request_7)

<div class="listingblock">

<div class="content">

    $ curl 'http://localhost:8080/rest/users/clearVoteHistory' -i -u 'admin@gmail.com:admin' -X GET

</div>

</div>

</div>

<div class="sect3">

#### [Request structure](#_request_structure_7)

<div class="listingblock">

<div class="content">

    GET /rest/users/clearVoteHistory HTTP/1.1
    Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
    Host: localhost:8080

</div>

</div>

</div>

<div class="sect3">

#### [Response structure](#_response_structure_7)

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

<div class="sect2">

### [Clear vote history](#_clear_vote_history)

<div class="sect3">

#### [curl request](#_curl_request_8)

<div class="listingblock">

<div class="content">

    $ curl 'http://localhost:8080/rest/restaurants/clearVotingData' -i -u 'admin@gmail.com:admin' -X GET

</div>

</div>

</div>

<div class="sect3">

#### [Request structure](#_request_structure_8)

<div class="listingblock">

<div class="content">

    GET /rest/restaurants/clearVotingData HTTP/1.1
    Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
    Host: localhost:8080

</div>

</div>

</div>

<div class="sect3">

#### [Response structure](#_response_structure_8)

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

## [User REST service](#_user_rest_service)

<div class="sectionbody">

<div class="sect2">

### [Get all users](#_get_all_users)

<div class="sect3">

#### [curl request](#_curl_request_9)

<div class="listingblock">

<div class="content">

    $ curl 'http://localhost:8080/rest/users/' -i -u 'admin@gmail.com:admin' -X GET

</div>

</div>

</div>

<div class="sect3">

#### [Request structure](#_request_structure_9)

<div class="listingblock">

<div class="content">

    GET /rest/users/ HTTP/1.1
    Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
    Host: localhost:8080

</div>

</div>

</div>

<div class="sect3">

#### [Response structure](#_response_structure_9)

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

### [Get user](#_get_user)

<div class="sect3">

#### [curl request](#_curl_request_10)

<div class="listingblock">

<div class="content">

    $ curl 'http://localhost:8080/rest/users/100005' -i -u 'admin@gmail.com:admin' -X GET

</div>

</div>

</div>

<div class="sect3">

#### [Request structure](#_request_structure_10)

<div class="listingblock">

<div class="content">

    GET /rest/users/100005 HTTP/1.1
    Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
    Host: localhost:8080

</div>

</div>

</div>

<div class="sect3">

#### [Response structure](#_response_structure_10)

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

### [Get user by email](#_get_user_by_email)

<div class="sect3">

#### [curl request](#_curl_request_11)

<div class="listingblock">

<div class="content">

    $ curl 'http://localhost:8080/rest/users/by?email=user@yandex.ru' -i -u 'admin@gmail.com:admin' -X GET

</div>

</div>

</div>

<div class="sect3">

#### [Request structure](#_request_structure_11)

<div class="listingblock">

<div class="content">

    GET /rest/users/by?email=user@yandex.ru HTTP/1.1
    Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
    Host: localhost:8080

</div>

</div>

</div>

<div class="sect3">

#### [Response structure](#_response_structure_11)

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

### [Delete user](#_delete_user)

<div class="sect3">

#### [curl request](#_curl_request_12)

<div class="listingblock">

<div class="content">

    $ curl 'http://localhost:8080/rest/users/100004' -i -u 'admin@gmail.com:admin' -X DELETE

</div>

</div>

</div>

<div class="sect3">

#### [Request structure](#_request_structure_12)

<div class="listingblock">

<div class="content">

    DELETE /rest/users/100004 HTTP/1.1
    Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
    Host: localhost:8080

</div>

</div>

</div>

<div class="sect3">

#### [Response structure](#_response_structure_12)

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

### [Create user](#_create_user)

<div class="sect3">

#### [curl request](#_curl_request_13)

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

#### [Request structure](#_request_structure_13)

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

#### [Response structure](#_response_structure_13)

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

### [Update user](#_update_user)

<div class="sect3">

#### [curl request](#_curl_request_14)

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

#### [Request structure](#_request_structure_14)

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

#### [Response structure](#_response_structure_14)

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

## [Restaurant REST service](#_restaurant_rest_service)

<div class="sectionbody">

<div class="sect2">

### [Get restaurant](#_get_restaurant)

<div class="sect3">

#### [curl request](#_curl_request_15)

<div class="listingblock">

<div class="content">

    $ curl 'http://localhost:8080/rest/restaurants/100001' -i -u 'admin@gmail.com:admin' -X GET

</div>

</div>

</div>

<div class="sect3">

#### [Request structure](#_request_structure_15)

<div class="listingblock">

<div class="content">

    GET /rest/restaurants/100001 HTTP/1.1
    Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
    Host: localhost:8080

</div>

</div>

</div>

<div class="sect3">

#### [Response structure](#_response_structure_15)

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

### [Delete restaurant](#_delete_restaurant)

<div class="sect3">

#### [curl request](#_curl_request_16)

<div class="listingblock">

<div class="content">

    $ curl 'http://localhost:8080/rest/restaurants/100001' -i -u 'admin@gmail.com:admin' -X DELETE

</div>

</div>

</div>

<div class="sect3">

#### [Request structure](#_request_structure_16)

<div class="listingblock">

<div class="content">

    DELETE /rest/restaurants/100001 HTTP/1.1
    Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
    Host: localhost:8080

</div>

</div>

</div>

<div class="sect3">

#### [Response structure](#_response_structure_16)

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

### [Create restaurant](#_create_restaurant)

<div class="sect3">

#### [curl request](#_curl_request_17)

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

#### [Request structure](#_request_structure_17)

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

#### [Response structure](#_response_structure_17)

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

### [Update restaurant](#_update_restaurant)

<div class="sect3">

#### [curl request](#_curl_request_18)

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

#### [Request structure](#_request_structure_18)

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

#### [Response structure](#_response_structure_18)

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

## [Dish REST service](#_dish_rest_service)

<div class="sectionbody">

<div class="sect2">

### [Get dish](#_get_dish)

<div class="sect3">

#### [curl request](#_curl_request_19)

<div class="listingblock">

<div class="content">

    $ curl 'http://localhost:8080/rest/dishes/100006' -i -u 'admin@gmail.com:admin' -X GET

</div>

</div>

</div>

<div class="sect3">

#### [Request structure](#_request_structure_19)

<div class="listingblock">

<div class="content">

    GET /rest/dishes/100006 HTTP/1.1
    Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
    Host: localhost:8080

</div>

</div>

</div>

<div class="sect3">

#### [Response structure](#_response_structure_19)

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

### [Delete dish](#_delete_dish)

<div class="sect3">

#### [curl request](#_curl_request_20)

<div class="listingblock">

<div class="content">

    $ curl 'http://localhost:8080/rest/dishes/100006' -i -u 'admin@gmail.com:admin' -X DELETE

</div>

</div>

</div>

<div class="sect3">

#### [Request structure](#_request_structure_20)

<div class="listingblock">

<div class="content">

    DELETE /rest/dishes/100006 HTTP/1.1
    Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu
    Host: localhost:8080

</div>

</div>

</div>

<div class="sect3">

#### [Response structure](#_response_structure_20)

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

### [Create dish](#_create_dish)

<div class="sect3">

#### [curl request](#_curl_request_21)

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

#### [Request structure](#_request_structure_21)

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

#### [Response structure](#_response_structure_21)

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

### [Update dish](#_update_dish)

<div class="sect3">

#### [curl request](#_curl_request_22)

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

#### [Request structure](#_request_structure_22)

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

#### [Response structure](#_response_structure_22)

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
