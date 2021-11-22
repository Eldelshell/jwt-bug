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

    /*
    * Tests with no body
    */

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
    
    /*
    * Tests with valid body
    */
    
    @Test
    public void testBug_POST_withValidBody() {
        given()
          .contentType(ContentType.JSON)
          .body("{\"name\":\"foo\"}")
          .when()
          .post("/hello/secured/bugged")
          .then()
          .statusCode(401);
    }
    
    @Test
    public void testBug_PUT_withValidBody() {
        given()
          .contentType(ContentType.JSON)
          .body("{\"name\":\"foo\"}")
          .when()
          .put("/hello/secured/bugged")
          .then()
          .statusCode(401);
    }
    
    @Test
    public void testBug_PATCH_withValidBody() {
        given()
          .contentType(MediaType.APPLICATION_JSON_PATCH_JSON)
          .body("{\"op\":\"replace\", \"path\":\"/name\", \"value\":\"foo\"}")
          .when()
          .patch("/hello/secured/bugged")
          .then()
          .statusCode(401);
    }
    
    /*
    * Tests with invalid body
    */
    
    @Test
    public void testBug_POST_withInvalidBody() {
        given()
          .contentType(ContentType.JSON)
          .body("{\"invalid\":\"foo\"}")
          .when()
          .post("/hello/secured/bugged")
          .then()
          .statusCode(401);
    }
    
    @Test
    public void testBug_PUT_withInvalidBody() {
        given()
          .contentType(ContentType.JSON)
          .body("{\"invalid\":\"foo\"}")
          .when()
          .put("/hello/secured/bugged")
          .then()
          .statusCode(401);
    }
    
    @Test
    public void testBug_PATCH_withInvalidBody() {
        given()
          .contentType(MediaType.APPLICATION_JSON_PATCH_JSON)
          .body("{\"op\":\"invalid\", \"path\":\"/name\", \"value\":\"foo\"}")
          .when()
          .patch("/hello/secured/bugged")
          .then()
          .statusCode(401);
    }
    
    /*
    * Tests with invalid content type.
    * Not sure if the JWT should be first validated before the content type validation 
    */
    
    @Test
    public void testBug_POST_withInvalidContent() {
        given()
          .contentType(ContentType.TEXT)
          .body("{\"invalid\":\"foo\"}")
          .when()
          .post("/hello/secured/bugged")
          .then()
          .statusCode(401);
    }
    
    @Test
    public void testBug_PUT_withInvalidContent() {
        given()
          .contentType(ContentType.TEXT)
          .body("{\"invalid\":\"foo\"}")
          .when()
          .put("/hello/secured/bugged")
          .then()
          .statusCode(401);
    }
    
    @Test
    public void testBug_PATCH_withInvalidContent() {
        given()
          .contentType(ContentType.JSON)
          .body("{\"op\":\"invalid\", \"path\":\"/name\", \"value\":\"foo\"}")
          .when()
          .patch("/hello/secured/bugged")
          .then()
          .statusCode(401);
    }

}
