package user;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
import data.GeneralData;

public class UserClient extends GeneralData {
    private static final String USER_REGISTER = "api/auth/register";
    private static final String USER_LOGIN = "api/auth/login";
    private static final String USER_UPDATE_USER_DELETE = "api/auth/user";

    public UserClient() {

    }

    @Step("Create user")
    // зарегистрировать
    public ValidatableResponse register(User user) {
        return given()
                .spec(getBaseSpec())
                .body(user)
                .when()
                .post(USER_REGISTER)
                .then();
    }

    @Step("Delete")
    // удалить
    public ValidatableResponse delete(String accessToken) {
        return given()
                .spec(getBaseSpec())
                .header("Authorization", accessToken)
                .when()
                .delete(USER_UPDATE_USER_DELETE)
                .then();
    }

}