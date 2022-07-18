package api.client;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RestAssuredClient {

    public static String BASE_URL = "https://stellarburgers.nomoreparties.site/";

    public static RequestSpecification getBaseSpec(){
        return new RequestSpecBuilder()
                .setBaseUri("https://stellarburgers.nomoreparties.site/")
                .setContentType("application/json")
                .build();
    }
}
