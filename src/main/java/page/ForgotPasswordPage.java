package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {

    private WebDriver driver;

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    private By loginLink = By.xpath(".//a[@href='/login']");

    @Step("Click on login link")
    public void clickOnLoginLink() {
        driver.findElement(loginLink).click();
    }
}
