package LogoutTest;

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

public class LogoutTest {
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
    }

    @Test
    @Step("Выйти из аккаунта")
    public void testLogoutPersonalAccount() {
        HomePageStellarBurgers objHomePage = new HomePageStellarBurgers(driver);
        objHomePage.clickButtonPersonalAccount();

        PersonalAccountPageStellarBurgers objAccountPage = new PersonalAccountPageStellarBurgers(driver);
        objAccountPage.clickButtonExit();

        //ожидание загрузки страницы
        LoginPageStellarBurgers objLoginPage = new LoginPageStellarBurgers(driver);
        objLoginPage.waitLoadPage();

        String actual = driver.getCurrentUrl();
        String expected = "https://stellarburgers.nomoreparties.site/login";
        Assert.assertEquals("Ошибка, пользователь не вышел из системы", expected, actual);
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