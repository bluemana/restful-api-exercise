# RESTful API Exercise

Implementation of a RESTful API for money transfers between users.

## API

The API adheres to the REST constraints, is based on HTTP and uses a custom JSON media type.

### Examples

#### Base resource

**Request**

```
GET /
```

**Response**

```
HTTP 200 OK

{
  "links": [
    {
      "uri": "/users",
      "rel": "users",
      "method": "GET"
    },
    {
      "uri": "/transactions",
      "rel": "transactions",
      "method": "GET"
    }
  ]
}
```

#### List users

**Request**

```
GET /users
```

**Response**

```
HTTP 200 OK

{
  "users": [
    {
      "id": 1,
      "name": "Mark",
      "balance": 10,
      "links": [
        {
          "uri": "/user/1",
          "rel": "self",
          "method": "GET"
        }
      ]
    },
    {
      "id": 2,
      "name": "Sophie",
      "balance": 0,
      "links": [
        {
          "uri": "/user/2",
          "rel": "self",
          "method": "GET"
        }
      ]
    }
  ],
  "links": [
    {
      "uri": "/users",
      "rel": "create",
      "method": "POST"
    }
  ]
}
```

#### Create user

**Request**

```
POST /users

{
  "name": "Andrew",
  "balance": 5
}
```

**Response**

```
HTTP 200 OK

{
  "user": {
    "id": 3,
    "name": "Andrew",
    "balance": 5,
    "links": [
      {
        "uri": "/user/3",
        "rel": "self",
        "method": "GET"
      }
    ]
  }
}
```

#### List transactions

**Request**

```
GET /transactions
```

**Response**

```
HTTP 200 OK

{
  "transactions": [
    {
      "id": 1,
      "sourceUserId": 1,
      "destinationUserId": 2,
      "amount": 2,
      "links": [
        {
          "uri": "/transaction/1",
          "rel": "self",
          "method": "GET"
        }
      ]
    }
  ],
  "links": [
    {
      "uri": "/transactions",
      "rel": "execute",
      "method": "POST"
    }
  ]
}
```

#### Transfer funds

**Request**

```
POST /transactions

{
  "sourceUserId": 1,
  "destinationUserId": 2,
  "amount": 2
}
```

**Response**

```
HTTP 200 OK

{
  "transaction": {
    "id": 1,
    "sourceUserId": 1,
    "destinationUserId": 2,
    "amount": 2,
    "links": [
      {
        "uri": "/transaction/1",
        "rel": "self",
        "method": "GET"
      }
    ]
  }
}
```

## Implementation notes

The project depends on the following libraries:

* [log4j 1.2](https://logging.apache.org/log4j/1.2/)
* [Netty 5.0](http://netty.io/)
* [Gson 2.6.2](https://sites.google.com/site/gson)
* [JUnit 4](http://junit.org/)
