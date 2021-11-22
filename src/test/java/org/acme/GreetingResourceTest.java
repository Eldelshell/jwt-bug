package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import io.restassured.http.ContentType;
import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class GreetingResourceTest {

    @Test
    public void testBug_POST() {
        given()
          .contentType(ContentType.JSON)
          .when()
          .post("/hello/secured/bugged")
          .then()
          .statusCode(401);
    }
    
    @Test
    public void testBug_PUT() {
        given()
          .contentType(ContentType.JSON)
          .when()
          .put("/hello/secured/bugged")
          .then()
          .statusCode(401);
    }
    
    @Test
    public void testBug_PATCH() {
        given()
          .contentType(MediaType.APPLICATION_JSON_PATCH_JSON)
          .when()
          .patch("/hello/secured/bugged")
          .then()
          .statusCode(401);
    }

}
