package clients;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import request.CreateUserRequest;
import request.LoginUserRequest;
import static io.restassured.RestAssured.given;

public class UserClient extends BaseClient {
    private final String userBaseUri = "/api/auth";
    @Step("Создать пользователя")
    public ValidatableResponse create(CreateUserRequest createUserRequest) {
        return given()
                .spec(getSpec())
                .body(createUserRequest)
                .when()
                .post(userBaseUri + "/register")
                .then();
    }

    @Step("Авторизоваться под пользователем")
    public ValidatableResponse login(LoginUserRequest loginUserRequest) {
        return given()
                .spec(getSpec())
                .body(loginUserRequest)
                .when()
                .post(userBaseUri + "/login")
                .then();
    }

    @Step("Удалить пользователя")
    public ValidatableResponse delete(String accessToken) {
        return given()
                .spec(getSpec())
                .header("Authorization", accessToken)
                .when()
                .delete(userBaseUri + "/user")
                .then();
    }
}