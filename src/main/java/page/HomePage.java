package page;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class HomePage {
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    private By enterAccountButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private By createOrderButton = By.xpath(".//button[text()='Оформить заказ']");
    private By accountProfileButton = By.xpath(".//p[text()='Личный Кабинет']");
    private By currentTab = By.xpath(".//div[contains(@class, 'tab_tab_type_current__2BEPc')]");
    private By bunElement = By.xpath(".//span[text()='Булки']");
    private By sauceElement = By.xpath(".//span[text()='Соусы']");
    private By ingredientElement = By.xpath(".//span[text()='Начинки']");

    @Step("Wait and click on enter account button")
    public void waitAndClickOnEnterAccountButton() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(enterAccountButton));
        driver.findElement(enterAccountButton).click();
    }

    @Step("Wait and click on account profile button")
    public void waitAndClickOnAccountProfileButton() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(accountProfileButton));
        driver.findElement(accountProfileButton).click();
    }

    @Step("Wait and click on sauce tab")
    public void waitAndClickOnSauceTab() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(sauceElement));
        driver.findElement(sauceElement).click();
    }

    @Step("Wait and click on ingredient tab")
    public void waitAndClickOnIngredientTab() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(ingredientElement));
        driver.findElement(ingredientElement).click();
    }

    @Step("Wait and click on bun tab")
    public void waitAndClickOnBunTab() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(bunElement));
        driver.findElement(bunElement).click();
    }

    @Step("Check is tab selected")
    public boolean isTabSelected(String name) {
        return driver.findElement(currentTab).getText().contains(name);
    }

    @Step("Check create order button is displayed")
    public boolean isCreateOrderButtonDisplayed() {
        return driver.findElement(createOrderButton).isDisplayed();
    }

}
