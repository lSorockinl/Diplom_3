package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPageStellarBurgers {
    private WebDriver driver;
    //поле email
    private By fieldEmail = By.xpath(".//div/input[@name = 'name']");
    //поле пароль
    private By fieldPassword = By.xpath(".//div/input[@name = 'Пароль']");
    //кнопка войти
    private By buttonSignIn = By.xpath(".//button[text() = 'Войти']");

    public LoginPageStellarBurgers(WebDriver driver) {
        this.driver = driver;
    }
    //Метод для ввода Email
    public void setFieldEmail(String email) {
        driver.findElement(fieldEmail).click();
        driver.findElement(fieldEmail).sendKeys(email);
    }
    //Метод для ввода пароля
    public void setFieldPassword(String password) {
        driver.findElement(fieldPassword).click();
        driver.findElement(fieldPassword).sendKeys(password);
    }
    //Метод для клика по кнопке "Войти"
    public void clickButtonSignIn() {
        driver.findElement(buttonSignIn).click();
    }
    //Метод ожидания загрузки страницы
    public void waitLoadPage() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(buttonSignIn));
    }
    //Метод для входа в систему
    public void login(String email, String password) {
        waitLoadPage();
        setFieldEmail(email);
        setFieldPassword(password);
        clickButtonSignIn();
    }
}