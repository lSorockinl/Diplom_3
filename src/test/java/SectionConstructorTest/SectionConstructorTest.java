package SectionConstructorTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import pageObject.HomePageStellarBurgers;

public class SectionConstructorTest {
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

        //переход на главную страницу
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    @Test
    @Step("Перейти в раздел 'Булки' (конструктор)")
    public void testTransitionInSectionBuns() {

        HomePageStellarBurgers objHomePage = new HomePageStellarBurgers(driver);

        objHomePage.clickSectionFilling();
        //проверка перехода к разделу "Булки"
        objHomePage.clickSectionBuns();
        By expected = By.xpath(".//div[contains(@class, 'tab_tab_type_current')]//span[text() = 'Булки']");
        Boolean actual = driver.findElements(expected).size() > 0;
        Assert.assertTrue("Ошибка, переход к разделу 'Булки' не осуществлен", actual);

    }

    @Test
    @Step("Перейти в раздел 'Соусы' (конструктор)")
    public void testTransitionInSectionSauce() {

        HomePageStellarBurgers objHomePage = new HomePageStellarBurgers(driver);

        //проверка перехода к разделу "Соусы"
        objHomePage.clickSectionSauce();
        By expected = By.xpath(".//div[contains(@class, 'tab_tab_type_current')]//span[text() = 'Соусы']");
        Boolean actual = driver.findElements(expected).size() > 0;
        Assert.assertTrue("Ошибка, переход к разделу 'Соусы' не осуществлен", actual);
    }

    @Test
    @Step("Перейти в раздел 'Начинки' (конструктор)")
    public void testTransitionInSectionFilling() {

        HomePageStellarBurgers objHomePage = new HomePageStellarBurgers(driver);
        objHomePage.clickSectionSauce();
        //проверка перехода к разделу "Начинки"
        objHomePage.clickSectionFilling();
        By expected = By.xpath(".//div[contains(@class, 'tab_tab_type_current')]//span[text() = 'Начинки']");
        Boolean actual = driver.findElements(expected).size() > 0;
        Assert.assertTrue("Ошибка, переход к разделу 'Начинки' не осуществлен", actual);
    }

    @After
    public void teardown() {
        driver.quit();
    }
}