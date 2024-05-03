package TransitionsTest;

import clients.UserClient;
import dataprovider.UserProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import pageObject.HomePageStellarBurgers;
import pageObject.LoginPageStellarBurgers;
import pageObject.PersonalAccountPageStellarBurgers;
import request.CreateUserRequest;

import java.util.Objects;

public class TransitionFromPersonalAccountToConstructorTest {
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

        //создание пользователя
        CreateUserRequest createUserRequest = UserProvider.getRandomCreateUserRequest();
        accessToken = userClient.create(createUserRequest)
                .extract().jsonPath().get("accessToken");

        //логин
        driver.get("https://stellarburgers.nomoreparties.site/login");
        LoginPageStellarBurgers objLoginPage = new LoginPageStellarBurgers(driver);
        objLoginPage.login(createUserRequest.getEmail(), createUserRequest.getPassword());

        //переход в личный кабинет
        HomePageStellarBurgers objHomePage = new HomePageStellarBurgers(driver);
        objHomePage.clickButtonPersonalAccount();
    }

    @Test
    @Step("Перейти из личного кабинета в конструктор через кнопку 'Конструктор'")
    public void testClickConstructorFromPersonalAccount() {
        PersonalAccountPageStellarBurgers objAccountPage = new PersonalAccountPageStellarBurgers(driver);
        //клик по разделу конструктор
        objAccountPage.clickButtonConstructor();
        String actual = driver.getCurrentUrl();
        String expected = "https://stellarburgers.nomoreparties.site/";
        Assert.assertEquals("Ошибка: пользователь находится не в конструкторе", expected, actual);
    }

    @Test
    @Step("Перейти из личного кабинета в конструктор через клик по логотипу в хедере")
    public void testClickLogoFromPersonalAccount() {
        PersonalAccountPageStellarBurgers objAccountPage = new PersonalAccountPageStellarBurgers(driver);
        //клик по логотипу
        objAccountPage.clickLogoStellarBurgers();
        String actual = driver.getCurrentUrl();
        String expected = "https://stellarburgers.nomoreparties.site/";
        Assert.assertEquals("Ошибка: пользователь находится не в конструкторе", expected, actual);
    }

    @After
    public void teardown() {
        //удалить пользователя
        if( !(Objects.equals(accessToken, null)) && !(Objects.equals(accessToken, "")) ) {
            userClient.delete(accessToken)
                    .statusCode(202);
        }
        //закрыть страницу
        driver.quit();
    }
}