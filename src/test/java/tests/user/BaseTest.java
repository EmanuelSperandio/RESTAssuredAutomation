package tests.user;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeSuite;
import tests.Routes;

import static io.restassured.RestAssured.when;

public class BaseTest {

    public int getExistentUserID(){
        return when().
                get(Routes.ALL_USERS).
                then().
                statusCode(HttpStatus.SC_OK).
                extract().
                path("[1].id");
    }

    @BeforeSuite(alwaysRun = true)
    public void setupForTest(){

        //setar baseUI, path e porta da minha API
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "/api";
        RestAssured.port = 8080;

        //setar log verboso = log().all().
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        //setar request / response body sempre sendo tratado como um JSON
        RestAssured.requestSpecification = new RequestSpecBuilder().setContentType(ContentType.JSON).build();
        RestAssured.responseSpecification = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();

    }


}
