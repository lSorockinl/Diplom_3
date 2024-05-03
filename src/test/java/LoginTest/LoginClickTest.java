package LoginTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import pageObject.ForgotPasswordPageStellarBurgers;
import pageObject.HomePageStellarBurgers;
import pageObject.RegisterPageStellarBurgers;

public class LoginClickTest {
    private WebDriver driver;

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
    @Step("Кликнуть по кнопке 'Войти' на странице восстановления пароля")
    public void clickSignInFromForgotPassPage() {
        driver.get("https://stellarburgers.nomoreparties.site/forgot-password");
        ForgotPasswordPageStellarBurgers objForgotPassPage = new ForgotPasswordPageStellarBurgers(driver);
        objForgotPassPage.clickButtonSignIn();
        String actual = driver.getCurrentUrl();
        String expected = "https://stellarburgers.nomoreparties.site/login";
        Assert.assertEquals("Ошибка, пользователь НЕ перешел из страницы восстановления пароля на страницу входа в систему", expected, actual);
    }


    @Test
    @Step("Кликнуть по кнопке 'Войти в аккаунт' на главной странице")
    public void clickSignInFromHomePage() {
        driver.get("https://stellarburgers.nomoreparties.site/");
        HomePageStellarBurgers objHomePage = new HomePageStellarBurgers(driver);
        objHomePage.clickButtonSignInAccount();
        String actual = driver.getCurrentUrl();
        String expected = "https://stellarburgers.nomoreparties.site/login";
        Assert.assertEquals("Ошибка, пользователь НЕ перешел из стартовой страницы на страницу входа в систему", expected, actual);
    }


    @Test
    @Step("Кликнуть на кнопку 'Личный кабинет' из главной страницы")
    public void clickPersonalAccountFromHomePage() {
        driver.get("https://stellarburgers.nomoreparties.site/");
        HomePageStellarBurgers objHomePage = new HomePageStellarBurgers(driver);
        objHomePage.clickButtonPersonalAccount();
        String actual = driver.getCurrentUrl();
        String expected = "https://stellarburgers.nomoreparties.site/login";
        Assert.assertEquals("Ошибка, пользователь НЕ перешел из стартовой страницы на страницу входа в систему", expected, actual);
    }


    @Test
    @Step("Кликнуть на кнопку 'Войти' на странице регистрации пользователя")
    public void clickSignInFromRegisterPage() {
        driver.get("https://stellarburgers.nomoreparties.site/register");
        RegisterPageStellarBurgers objRegisterPage = new RegisterPageStellarBurgers(driver);
        objRegisterPage.clickButtonSignIn();
        String actual = driver.getCurrentUrl();
        String expected = "https://stellarburgers.nomoreparties.site/login";
        Assert.assertEquals("Ошибка, пользователь НЕ перешел из страницы регистрации на страницу входа в систему", expected, actual);
    }

    @After
    public void teardown() {
        driver.quit();
    }
}