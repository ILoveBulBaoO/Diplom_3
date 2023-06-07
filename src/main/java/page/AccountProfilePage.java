package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountProfilePage {
    private WebDriver driver;

    public AccountProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    private By logoutButton = By.xpath(".//button[text()='Выход']");
    private By constructorButton = By.xpath(".//p[text()='Конструктор']");
    private By logoButton = By.xpath(".//nav/div/a[@href='/']");



    @Step("Wait and click on logout button")
    public void waitAndClickOnLogoutButton() {
        new WebDriverWait(driver, 2).until(ExpectedConditions.visibilityOfElementLocated(logoButton));
        driver.findElement(logoutButton).click();
    }

    @Step("Click on constructor button")
    public void clickOnConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    @Step("Click on logo button")
    public void clickOnLogoButton() {
        driver.findElement(logoButton).click();
    }

    @Step("Check displayed logout button")
    public boolean isLogoutButtonDisplayed() {
        return driver.findElement(logoutButton).isDisplayed();
    }
}
