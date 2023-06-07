import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.HomePage;
import page.LoginPage;
import page.RegistrationPage;
import user.User;
import user.UserGenerator;

public class RegistrationPageTest {

    private WebDriver driver;
    private User user;
    private User userWithIncorrectPassword;

    @Before
    public void setUp() {
        // YaBro
//        System.setProperty("webdriver.chrome.driver","/Users/novikaud/WebDriver/bin/yandexdriver");
//        driver = new ChromeDriver();

        // Chrome
        System.setProperty("webdriver.chrome.driver","/Users/novikaud/WebDriver/bin/chromedriver");
        driver = new ChromeDriver();

        driver.get("https://stellarburgers.nomoreparties.site/register");
        user = UserGenerator.getRandomUser();
        userWithIncorrectPassword = UserGenerator.getRandomUserWithIncorrectPassword();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    // можно зарегистрировать юзера с верным паролем и авторизоваться
    @DisplayName("Successful registration and login")
    @Test
    public void userCanBeRegisteredTest() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage =new HomePage(driver);

        registrationPage.inputName(user.getName());
        registrationPage.inputEmail(user.getEmail());
        registrationPage.inputPassword(user.getPassword());
        registrationPage.clickOnRegisterButton();

        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(loginPage.waitAndReturnLoginButton()));

        loginPage.inputEmail(user.getEmail());
        loginPage.inputPassword(user.getPassword());
        loginPage.clickOnLoginButton();

        Assert.assertTrue(homePage.isCreateOrderButtonDisplayed());

    }

    // нельзя зарегистрироваться с неверным паролем
    @DisplayName("Unsuccessful registration")
    @Test
    public void userCanNotBeRegisteredWithIncorrectPasswordTest() {
        RegistrationPage registrationPage = new RegistrationPage(driver);

        registrationPage.inputName(userWithIncorrectPassword.getName());
        registrationPage.inputEmail(userWithIncorrectPassword.getEmail());
        registrationPage.inputPassword(userWithIncorrectPassword.getPassword());
        registrationPage.clickOnRegisterButton();

        Assert.assertTrue(registrationPage.isDisplayedMessageIncorrectPassword());
    }

}
