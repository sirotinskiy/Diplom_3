package api.client;

import api.model.User;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static api.endpoints.EndPoints.LOGIN_USER;
import static io.restassured.RestAssured.given;

public class LoginUser {
    @Step("POST login user ")
    public static ValidatableResponse loginUser(User user){
        return given()
                .spec(RestAssuredClient.getBaseSpec())
                .body(user)
                .log().all()
                .when()
                .post(LOGIN_USER)
                .then();
    }
}
