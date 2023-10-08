package tests.user;

import domain.User;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import tests.Routes;

import static factory.UserFactory.generateUser;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class UserFunctionalTest {

    @Test
    public void getAllUsers(){
        when().
                get(Routes.ALL_USERS).
        then().
                statusCode(HttpStatus.SC_OK)
                .body("[0].id", is(1))
                .body("[0].firstname", is("fulano"))
                .body("[0].age", equalTo(18))
                .body("[0].email", containsString("@"));
    }

    @Test
    public void getSingleUser(){
        given().
                pathParam("id", 15).
        when().
                get(Routes.SINGLE_USER).
        then().
                statusCode(HttpStatus.SC_OK)
                .body("user.id", is(15))
                .body("user.firstname", is("new"))
                .body("user.lastname", is("novo"))
                .body("user.age", equalTo(25))
                .body("user.email", containsString("@novo.com"));
    }

    @Test
    public void getNonExistentUser(){
        given().
                pathParam("id", 99999).
        when().
                get(Routes.SINGLE_USER).
        then().
                statusCode(HttpStatus.SC_NOT_FOUND).
                body("message", is("Can't find user with id= 99999"));
    }

    @Test
    public void addNewUser(){
        User user = generateUser();

        given().
                header("Content-Type", "application/json").
                body(user).
        when().
                post(Routes.ADD_USER).
        then().
                statusCode(HttpStatus.SC_CREATED);
    }

}
