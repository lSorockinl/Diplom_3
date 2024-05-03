package dataprovider;

import org.apache.commons.lang3.RandomStringUtils;
import request.CreateUserRequest;

public class UserProvider {
    //сгенерировать Email
    private static final String DOMAIN = "@yandex.ru";

    public static String generateRandomEmail() {
        String email_beginning = RandomStringUtils.randomAlphabetic(7);
        return email_beginning + DOMAIN;
    }

    //Получение данных для создания пользователя с рандомными данными
    public static CreateUserRequest getRandomCreateUserRequest() {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setEmail(generateRandomEmail());
        createUserRequest.setPassword(RandomStringUtils.randomAlphabetic(7));
        createUserRequest.setName(RandomStringUtils.randomAlphabetic(7));

        return createUserRequest;
    }

    //Получение данных зарегистрированного пользователя
    public static CreateUserRequest getDataCreatedUser(String email, String password, String name) {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setEmail(email);
        createUserRequest.setPassword(password);
        createUserRequest.setName(name);

        return createUserRequest;
    }
}