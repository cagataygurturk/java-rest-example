package com.cagataygurturk.controllers;


import com.cagataygurturk.DemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port=12347")
public class TransactionsControllerTest {

    protected static String BASE_STRING = "http://localhost:12347/transactionservice";

    @Test
    public void getNotFoundTransaction() throws Exception {
        get(BASE_STRING + "/transactions/112313")
                .then()
                .statusCode(404);
    }

    @Test
    public void createTransactionWithNotExistingParent() throws Exception {

        given()
                .contentType("application/json")
                .body("{\"amount\": 20,\"type\": \"cars\", \"parent_id\": 20 }")
                .expect()
                .statusCode(422)
                .when()
                .post(BASE_STRING + "/transactions");

    }

    @Test
    public void createTransactionWithMalformedRequest() throws Exception {

        given()
                .contentType("application/json")
                .body("{\"name\":\"Jimi Hendrix\"}")
                .expect()
                .statusCode(400)
                .when()
                .post(BASE_STRING + "/transactions");

    }

    @Test
    public void createTransaction() throws Exception {
        given()
                .contentType("application/json")
                .body("{\"amount\": 20.8,\"type\": \"cars\" }")
                .expect()
                .statusCode(201)
                .when()
                .post(BASE_STRING + "/transactions");

    }

    @Test
    public void createTransactionWithParents() throws Exception {

        String location = given()
                .contentType("application/json")
                .body("{\"amount\": 20.8,\"type\": \"cars\" }")
                .expect()
                .statusCode(201)
                .when()
                .post(BASE_STRING + "/transactions")
                .then()
                .extract()
                .header("Location");


        Integer parentId = given()
                .contentType("application/json")
                .expect()
                .statusCode(200)
                .when()
                .get(location)
                .then()
                .extract()
                .path("transaction_id");

        given()
                .contentType("application/json")
                .body("{\"amount\": 20,\"type\": \"cars\", \"parent_id\": " + parentId + "}")
                .expect()
                .statusCode(201)

                .when()
                .post(BASE_STRING + "/transactions");

    }

    @Test
    public void calculateSumOfNotExistingTransaction() throws Exception {
        get(BASE_STRING + "/transactions/112313/sum")
                .then()
                .statusCode(404);
    }

    @Test
    public void calculateSum() throws Exception {
        String location = given()
                .contentType("application/json")
                .body("{\"amount\": 20.8,\"type\": \"cars\" }")
                .expect()
                .statusCode(201)
                .when()
                .post(BASE_STRING + "/transactions")
                .then()
                .extract()
                .header("Location");


        Integer parentId = given()
                .contentType("application/json")
                .expect()
                .statusCode(200)
                .when()
                .get(location)
                .then()
                .extract()
                .path("transaction_id");

        for (int i = 1; i <= 5; i++) {
            given()
                    .contentType("application/json")
                    .body("{\"amount\": 20,\"type\": \"cars\", \"parent_id\": " + parentId + "}")
                    .expect()
                    .statusCode(201)
                    .when()
                    .post(BASE_STRING + "/transactions");
        }


        given()
                .contentType("application/json")
                .expect()
                .statusCode(200)
                .body("sum", is(100f))
                .when()
                .get(BASE_STRING + "/transactions/" + parentId + "/sum")
                .then()
                .extract()
                .path("sum");
    }

    @Test
    public void getTransactionsByType() throws Exception {

        for (int i = 1; i <= 3; i++) {
            given()
                    .contentType("application/json")
                    .body("{\"amount\": 20,\"type\": \"clothes\"}")
                    .expect()
                    .statusCode(201)
                    .when()
                    .post(BASE_STRING + "/transactions");
        }


        given()
                .contentType("application/json")
                .body("{\"amount\": 20,\"type\": \"bus\"}")
                .expect()
                .statusCode(201)
                .when()
                .post(BASE_STRING + "/transactions");


        given()
                .contentType("application/json")
                .expect()
                .body("size()", is(3))
                .statusCode(200)
                .when()
                .get(BASE_STRING + "/types/clothes");

    }
}