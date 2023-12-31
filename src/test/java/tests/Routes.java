package tests;

import java.net.URI;

public class Routes {


    public static String BASE_URL = "http://localhost:8080/api";
    public static String HEALTH = "/health";
    public static String ALL_USERS = BASE_URL + "/users";
    public static String SINGLE_USER = BASE_URL + "/users/{id}";
    public static String ADD_USER = BASE_URL + "/users/add";
    public static String DELETE_USER = BASE_URL + "/users/{id}";
    public static String UPDATE_USER = BASE_URL + "/users/{id}";
}
