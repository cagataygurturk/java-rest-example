[![Build Status](https://travis-ci.org/cagataygurturk/java-rest-example.svg)](https://travis-ci.org/cagataygurturk/java-rest-example)

# REST Example

This repository includes a basic REST API built with Jersey and Spring framework and for demonstration purposes.

## Run and Test

To run the application type

```
mvn spring-boot:run
```

To execute unit and acceptance tests


```
mvn test
```

For unit tests and acceptance tests JUnit and REST Assured frameworks are used.

## Endpoints

**Important:** `Content-Type: application/json` header must be present to use API.

The most common HTTP status codes are returned when there is an error.

### Add a transaction

```
/transactionservice/transactions/{id} [POST]

Content-Type: application/json

{
    "amount": double,
    "type": string
    "parent_id": long|null
}
```

When succeeed 201 Status code and newly created transaction object are returned.

### Get a transaction

```
/transactionservice/transactions/{id} [GET]
```

Gets a transaction with given id.

### Get sum amount a transaction

```
/transactionservice/transactions/{id}/sum [GET]
```

Gets the total amount of transactions whose parent is the transaction with id {id}


### Get transactions with a given type

```
/transactionservice/types/{type} [GET]
```

Gets ID's of the transactions with given type.
