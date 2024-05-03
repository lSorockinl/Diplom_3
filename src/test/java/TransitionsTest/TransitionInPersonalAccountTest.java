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

public class TransitionInPersonalAccountTest {
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

    @Test
    @Step("Перейти в личный кабинет через кнопку 'Личный кабинет', авторизовавшись под пользователем")
    public void testClickPersonalAccountWithAuth() {
        //создание пользователя
        CreateUserRequest createUserRequest = UserProvider.getRandomCreateUserRequest();
        accessToken = userClient.create(createUserRequest)
                .extract().jsonPath().get("accessToken");
        //логин
        driver.get("https://stellarburgers.nomoreparties.site/login");
        LoginPageStellarBurgers objLoginPage = new LoginPageStellarBurgers(driver);
        objLoginPage.login(createUserRequest.getEmail(), createUserRequest.getPassword());

        //проверка клика по кнопке личный кабинет
        HomePageStellarBurgers objHomePage = new HomePageStellarBurgers(driver);
        objHomePage.clickButtonPersonalAccount();

        //ожидание загрузки страницы
        PersonalAccountPageStellarBurgers objPersonalAcc = new PersonalAccountPageStellarBurgers(driver);
        objPersonalAcc.waitLoadPage();

        String actual = driver.getCurrentUrl();
        String expected = "https://stellarburgers.nomoreparties.site/account/profile";
        Assert.assertEquals("Ошибка: пользователь НЕ находится в личном кабинете", expected, actual);
    }

    @Test
    @Step("Перейти в личный кабинет через кнопку 'Личный кабинет' под неавторизованным пользователем")
    public void testClickPersonalAccountWithoutAuth() {
        //переход на cтартовую страницу
        driver.get("https://stellarburgers.nomoreparties.site/");

        //проверка клика по кнопке личный кабинет без авторизации
        HomePageStellarBurgers objHomePage = new HomePageStellarBurgers(driver);
        objHomePage.clickButtonPersonalAccount();
        String actual = driver.getCurrentUrl();
        String expected = "https://stellarburgers.nomoreparties.site/login";
        Assert.assertEquals("Ошибка: пользователь НЕ находится на странице входа", expected, actual);
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