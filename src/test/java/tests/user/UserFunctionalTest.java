package tests.user;

import domain.User;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import tests.Routes;

import static factory.UserFactory.generateUser;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class UserFunctionalTest extends BaseTest{

    @Test(groups = "functional")
    public void getAllUsers(){
        when().
                get(Routes.ALL_USERS).
        then().
                statusCode(HttpStatus.SC_OK).
                body("[0].id", is(4)).
                body("[0].firstname", is("teste")).
                body("[0].age", equalTo(271)).
                body("[0].email", containsString("@"));
    }

    @Test(groups = "functional")
    public void getSingleUser(){
        given().
                pathParam("id", 4).
        when().
                get(Routes.SINGLE_USER).
        then().
                statusCode(HttpStatus.SC_OK).
                body("user.id", is(4)).
                body("user.firstname", is("teste")).
                body("user.lastname", is("oliveira")).
                body("user.age", equalTo(271)).
                body("user.email", containsString("mail@email.com"));
    }

    @Test(groups = "functional")
    public void getNonExistentUser(){
        given().
                pathParam("id", 99999).
        when().
                get(Routes.SINGLE_USER).
        then().
                statusCode(HttpStatus.SC_NOT_FOUND).
                body("message", is("Can't find user with id= 99999"));
    }

    @Test(groups = "functional")
    public void addNewUser(){
        User user = generateUser();

        given().
                body(user).
        when().
                post(Routes.ADD_USER).
        then().
                statusCode(HttpStatus.SC_CREATED);
    }

    @Test(groups = "functional")
    public void deleteUser(){
        given().
                pathParam("id", getExistentUserID()).
        when().
                delete(Routes.DELETE_USER).
        then().
                statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test(groups = "functional")
    public void deleteNonExistentUser(){
        given().
                pathParam("id",99999).
        when().
                delete(Routes.DELETE_USER).
        then().
                statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test(groups = "functional")
    public void updateUser(){
        User user = generateUser();

        given().
                pathParam("id", getExistentUserID()).
                body(user).
        when().
                put(Routes.UPDATE_USER).
        then().
                statusCode(HttpStatus.SC_OK);
    }


}
