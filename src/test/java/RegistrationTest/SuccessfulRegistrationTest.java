package RegistrationTest;

import clients.UserClient;
import dataprovider.UserProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import pageObject.RegisterPageStellarBurgers;
import request.CreateUserRequest;
import request.LoginUserRequest;
import java.util.Objects;
import java.util.Random;

@RunWith(Parameterized.class)
public class SuccessfulRegistrationTest {
    private WebDriver driver;
    private UserClient userClient = new UserClient();
    private String accessToken;

    @Before
    public void setUp() {
        //драйвер для браузера Chrome
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();

        /**драйвер для яндекс браузера
         System.setProperty("webdriver.chrome.driver","укажи_путь_к_драйверу");
         ChromeDriverService service = new ChromeDriverService.Builder().build();
         this.driver = new ChromeDriver(service);
         */

    }

    private final String name;
    private final String email;
    private final String password;

    public SuccessfulRegistrationTest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Parameterized.Parameters(name = "Имя, email и пароль для регистрации пользователя. Тестовые данные: {0} , {1} , {2}")
    public static Object[][] sendFieldsForSuccessfulRegistration() {
        Random random = new Random();
        return new Object[][]{
                {"Sorokin123", "sorokin" + random.nextInt(100000000) +"@yandex.ru", "123456"},
                {"Сорокин", "сорокин" + random.nextInt(100000000) + "@gmail.com", "test123"},
                {"С", random.nextInt(100000000) + "@mail.ru", "_test_"},
                {"ДлинноеИмяДлинноеИмяДлинноеИмя", "d-sorokin" + random.nextInt(100000000) + "@ya.ru", "ТестовыйПарольТестовыйПарольТестовыйПароль"},
                {"Имя с пробелами", random.nextInt(100000000) + "@ya.ru", "Пароль с пробелами"}
        };
    }

    @Test
    @Step("Успешно зарегистрировать пользователя")
    public void checkSuccessfulRegistration() {
        //переход на страницу регистрации
        driver.get("https://stellarburgers.nomoreparties.site/register");

        //объект класса страницы регистрации
        RegisterPageStellarBurgers objRegisterPage = new RegisterPageStellarBurgers(driver);

        //зарегистрировать пользователя
        objRegisterPage.register(name, email, password);

        //убедиться, что пользователь успешно зарегистрирован
        driver.get("https://stellarburgers.nomoreparties.site/register");
        objRegisterPage.register(name, email, password);

        String actual = objRegisterPage.findTextUserAlreadyRegistered();

        Assert.assertEquals("Ошибка: пользователь НЕ зарегистрирован", "Такой пользователь уже существует",actual);
    }

    @After
    public void teardown() {

        //получить токен для удаления пользователя
        CreateUserRequest createUserRequest = UserProvider.getDataCreatedUser(email, password, name);
        LoginUserRequest loginUserRequest = LoginUserRequest.from(createUserRequest);
        accessToken = userClient.login(loginUserRequest)
                .statusCode(200)
                .extract().jsonPath().get("accessToken");

        //удалить пользователя
        if( !(Objects.equals(accessToken, null)) && !(Objects.equals(accessToken, "")) ) {
            userClient.delete(accessToken)
                    .statusCode(202);
        }

        //закрыть страницу
        driver.quit();
    }
}