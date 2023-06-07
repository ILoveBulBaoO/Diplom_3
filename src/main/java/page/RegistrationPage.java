package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {

    private WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    private By inputName = By.xpath(".//div[label[text()='Имя']]//input");
    private By inputEmail = By.xpath(".//div[label[text()='Email']]//input");
    private By inputPassword = By.xpath(".//div[label[text()='Пароль']]//input");
    private By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private By messageIncorrectPassword = By.xpath(".//p[text()='Некорректный пароль']");
    private By loginLink = By.xpath(".//a[@href='/login']");

    @Step("Input name")
    public void inputName(String name) {
        driver.findElement(inputName).sendKeys(name);
    }

    @Step("Input email")
    public void inputEmail(String email) {
        driver.findElement(inputEmail).sendKeys(email);
    }

    @Step("Input password")
    public void inputPassword(String password) {
        driver.findElement(inputPassword).sendKeys(password);
    }

    @Step("Click on register button")
    public void clickOnRegisterButton() {
        driver.findElement(registerButton).click();
    }

    @Step("Check displayed message")
    public boolean isDisplayedMessageIncorrectPassword() {
        driver.findElement(inputEmail).click();
        return driver.findElement(messageIncorrectPassword).isDisplayed();
    }

    @Step("Click on login link")
    public void clickOnLoginLink() {
        driver.findElement(loginLink).click();
    }

}
