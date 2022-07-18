package api.client;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static api.client.RestAssuredClient.getBaseSpec;
import static api.endpoints.EndPoints.DELETE_USER;
import static io.restassured.RestAssured.given;

public class DeleteUser {
    @Step("DELETE user")
    public static ValidatableResponse deleteUser(String bearerToken){
        return given()
                .spec(getBaseSpec())
                .header("authorization",bearerToken)
                .when()
                .delete(DELETE_USER)
                .then();
    }
}
