package pe.edu.nova;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;

/**
 * Tests de integracion que validan que el envelope {@code ApiResponse} y el
 * {@code ApiExceptionMapper} (provistos por
 * {@code nova-api-standard-quarkus-extension}) funcionan end-to-end.
 */
@QuarkusTest
class GreetingResourceTest {

    @Test
    void testHelloEndpointReturnsApiResponseEnvelope() {
        given()
            .when().get("/hello")
            .then()
                .statusCode(200)
                .body("success", is(true))
                .body("status", is(200))
                .body("data", is("Hello from Quarkus REST"))
                .body("errors", is(notNullValue()));
    }

    @Test
    void testIllegalArgumentExceptionIsMappedToBadRequest() {
        given()
            .when().get("/hello/boom")
            .then()
                .statusCode(400)
                .body("success", is(false))
                .body("status", is(400))
                .body("data", is(nullValue()))
                .body("errors[0].code", is("BAD_REQUEST"))
                .body("errors[0].message", is("simulated validation error"));
    }
}
