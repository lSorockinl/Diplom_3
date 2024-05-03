package RegistrationTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import pageObject.RegisterPageStellarBurgers;

public class ErrorPasswordTest {
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
    @Step("Проверить наличие ошибки о некорректном пароле при вводе пароля менее 6 символов")
    public void checkErrorWhenEnteringPasswordLessThanSixCharacters() {
        //переход на страницу регистрации
        driver.get("https://stellarburgers.nomoreparties.site/register");

        //объект класса страницы регистрации
        RegisterPageStellarBurgers objRegisterPage = new RegisterPageStellarBurgers(driver);
        //попытка регистрации пользователя с паролем менее 6 символов
        objRegisterPage.register("test", "test@yandex.ru", "12345");
        String actual = objRegisterPage.findTextIncorrectPassword();
        Assert.assertEquals("Текст ошибки отсутствует или не совпадает с ожидаемым значением", "Некорректный пароль", actual);
    }

    @After
    public void teardown() {
        driver.quit();
    }
}