package org.disx;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class GreetingResourceTest {

    // @Test
    // void testHelloEndpoint() {
    //     given()
    //       .when().get("/posts/hello")
    //       .then()
    //          .statusCode(200)
    //          .body(is("Hello"));
    // }
}