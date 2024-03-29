package tests.user;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import tests.Routes;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class UserContractTest {

    @Test(groups = "contract")
    public void contractAllUsersTest(){
        when().
                get(Routes.ALL_USERS).
        then().
                statusCode(HttpStatus.SC_OK).
                body(matchesJsonSchemaInClasspath("schemas/AllUsersSchema.json"));
    }

    @Test(groups = "contract")
    public void contractSingleUserTest(){
        given().
                pathParam("id",getExistentUserID()).
        when().
                get(Routes.SINGLE_USER).
        then().
                statusCode(HttpStatus.SC_OK).
                body(matchesJsonSchemaInClasspath("schemas/SingleUserSchema.json"));
    }

    public int getExistentUserID(){
        return when().
                    get(Routes.ALL_USERS).
                then().
                    statusCode(HttpStatus.SC_OK).
                    extract().
                    path("[1].id");
    }

}
