package tests;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

public class HealthCheckTest {

    @Test
    public void healthCheck(){
        when().
                get("http://localhost:8080/api/health").
        then().
                statusCode(200)
                .body("status", is("ok"));
    }

}
