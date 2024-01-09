package tests.general;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import tests.Routes;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

public class HealthCheckTest {

    @Test(groups = "health")
    public void healthCheck(){
        when().
                get(Routes.HEALTH).
        then().
                statusCode(HttpStatus.SC_OK).
                body("status", is("ok"));
    }


}
