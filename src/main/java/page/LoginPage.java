package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private WebDriver driver;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    private By registrationLink = By.xpath(".//a[@href='/register']");
    private By inputEmail = By.xpath(".//div[label[text()='Email']]//input");
    private By inputPassword = By.xpath(".//div[label[text()='Пароль']]//input");
    private By loginButton = By.xpath(".//button[text()='Войти']");
    private By forgotPasswordLink = By.xpath(".//a[@href='/forgot-password']");

    @Step("Click on registration link")
    public void clickOnRegistrationLink() {
        driver.findElement(registrationLink).click();
    }

    @Step("Input email")
    public void inputEmail(String email) {
        driver.findElement(inputEmail).sendKeys(email);
    }

    @Step("Input password")
    public void inputPassword(String password) {
        driver.findElement(inputPassword).sendKeys(password);
    }

    @Step("Click on login button")
    public void clickOnLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Click on forgot password link")
    public void clickOnForgotPasswordLink() {
        driver.findElement(forgotPasswordLink).click();
    }

    @Step("Wait and check displayed login button")
    public boolean isLoginButtonDisplayed() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        return driver.findElement(loginButton).isDisplayed();
    }

    @Step("Wait and return login button")
    public By waitAndReturnLoginButton() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        return loginButton;
    }
}
