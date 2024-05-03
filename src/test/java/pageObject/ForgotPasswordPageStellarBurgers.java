package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForgotPasswordPageStellarBurgers {
    private WebDriver driver;
    //поле email
    private By fieldEmail = By.xpath(".//label[text() = 'Email']");
    //кнопка "Восстановить"
    private By buttonRestore = By.xpath(".//button[text() = 'Восстановить']");
    //кнопка "Войти" (ссылка)
    private By buttonSignIn = By.xpath(".//div/p/a[text() = 'Войти']");

    public ForgotPasswordPageStellarBurgers(WebDriver driver) {
        this.driver = driver;
    }
    //Метод для ввода email
    public void setFieldEmail(String email) {
        driver.findElement(fieldEmail).sendKeys(email);
    }
    //Метод для клика по кнопке "Восстановить"
    public void clickButtonRestore() {
        driver.findElement(buttonRestore).click();
    }
    //Метод для клика по кнопке "Войти"
    public void clickButtonSignIn() {
        driver.findElement(buttonSignIn).click();
    }
    //Метод ожидания загрузки страницы
    public void waitLoadPage() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(buttonRestore));
    }
    //Метод для восстановления пароля
    public void restorePassword(String email) {
        waitLoadPage();
        setFieldEmail(email);
        clickButtonRestore();
    }
}