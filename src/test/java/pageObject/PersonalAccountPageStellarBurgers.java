package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PersonalAccountPageStellarBurgers {
    private WebDriver driver;
    //локаторы на кнопку "Выйти"
    private By buttonExit = By.xpath(".//nav//li/button[text() = 'Выход']");
    //локатор на кнопку "Конструктор"
    private By buttonConstructor = By.xpath(".//nav//a/p[text() = 'Конструктор']");
    //локатор на логотип stellar burgers
    private By logoStellarBurgers = By.xpath(".//nav/div[contains(@class,'AppHeader_header__logo')]");

    public PersonalAccountPageStellarBurgers(WebDriver driver) {
        this.driver = driver;
    }
    //метод ожидания загрузки страницы
    public void waitLoadPage() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(buttonExit));
    }
    //клик по кнопке выйти
    public void clickButtonExit() {
        waitLoadPage();
        driver.findElement(buttonExit).click();
    }
    //клик по конструктору
    public void clickButtonConstructor() {
        driver.findElement(buttonConstructor).click();
    }
    //клик по лого
    public void clickLogoStellarBurgers() {
        driver.findElement(logoStellarBurgers).click();
    }
}