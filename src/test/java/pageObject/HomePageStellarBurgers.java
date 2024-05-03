package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageStellarBurgers {
    private WebDriver driver;
    // кнопка "Войти в аккаунт" на главной странице
    private By buttonSignInAccount = By.xpath(".//button[text() = 'Войти в аккаунт']");
    // кнопка "Личный кабинет"
    private By buttonPersonalAccount = By.xpath(".//a/p[text() = 'Личный Кабинет']");
    // раздел "булки"
    private By sectionBuns = By.xpath(".//div/span[text() = 'Булки']");
    // раздел "соусы"
    private By sectionSauce = By.xpath(".//div/span[text() = 'Соусы']");
    // раздел "начинки"
    private By sectionFilling = By.xpath(".//div/span[text() = 'Начинки']");
    // выбран раздел "булки"
    private By selectedSectionBuns = By.xpath(".//div[contains(@class, 'tab_tab_type_current')]//span[text() = 'Булки']");
    // выбран раздел "соусы"
    private By selectedSectionSauce = By.xpath(".//div[contains(@class, 'tab_tab_type_current')]//span[text() = 'Соусы']");
    // выбран раздел "начинки"
    private By selectedSectionFilling = By.xpath(".//div[contains(@class, 'tab_tab_type_current')]//span[text() = 'Начинки']");

    public HomePageStellarBurgers(WebDriver driver) {
        this.driver = driver;
    }

    //Метод для клика по кнопке "Войти в аккаунт"
    public void clickButtonSignInAccount() {
        driver.findElement(buttonSignInAccount).click();
    }
    //Метод для клика по кнопке "Личный кабинет"
    public void clickButtonPersonalAccount() {
        driver.findElement(buttonPersonalAccount).click();
    }
    //Метод ожидания подсветки раздела булки
    public void waitBuns() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement section = wait.until(ExpectedConditions.visibilityOfElementLocated(selectedSectionBuns));
    }
    //Метод ожидания перехода к элементу "Соусы"
    public void waitSauce() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement section = wait.until(ExpectedConditions.visibilityOfElementLocated(selectedSectionSauce));
    }
    //Метод ожидания перехода к элементу "Начинки"
    public void waitFilling() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement section = wait.until(ExpectedConditions.visibilityOfElementLocated(selectedSectionFilling));
    }
    //Метод для клика по разделу "Булки"
    public void clickSectionBuns() {
        driver.findElement(sectionBuns).click();
        waitBuns();
    }
    //Метод для клика по разделу "Соусы"
    public void clickSectionSauce() {
        driver.findElement(sectionSauce).click();
        waitSauce();
    }
    //Метод для клика по разделу "Начинки"
    public void clickSectionFilling() {
        driver.findElement(sectionFilling).click();
        waitFilling();
    }
}