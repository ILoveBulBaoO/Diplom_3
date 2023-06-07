import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page.ForgotPasswordPage;
import page.HomePage;
import page.LoginPage;
import page.RegistrationPage;
import user.User;
import user.UserClient;
import user.UserGenerator;

public class LoginPageTest {

    private WebDriver driver;
    private User user;
    private UserClient userClient;
    private String accessToken;

    @Before
    public void setUp() {
        // YaBro
//        System.setProperty("webdriver.chrome.driver","/Users/novikaud/WebDriver/bin/yandexdriver");
//        driver = new ChromeDriver();

        // Chrome
        System.setProperty("webdriver.chrome.driver","/Users/novikaud/WebDriver/bin/chromedriver");
        driver = new ChromeDriver();

        driver.get("https://stellarburgers.nomoreparties.site/");

        userClient = new UserClient();
        user = UserGenerator.getRandomUser();

        ValidatableResponse registerResponse = userClient.register(user);
        accessToken = registerResponse.extract().path("accessToken");
    }

    @After
    public void tearDown() {
        userClient.delete(accessToken);
        driver.quit();
    }

    // можно залогиниться по кнопке войти в аккаунт
    @DisplayName("Can login by enter account button")
    @Test
    public void userCanBeLoginByEnterAccountButtonTest(){
        HomePage homePage =new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);

        homePage.waitAndClickOnEnterAccountButton();
        loginPage.inputEmail(user.getEmail());
        loginPage.inputPassword(user.getPassword());
        loginPage.clickOnLoginButton();

        Assert.assertTrue(homePage.isCreateOrderButtonDisplayed());
    }

    // можно залогиниться по кнопке личный кабинет
    @DisplayName("Can login by account button")
    @Test
    public void userCanBeLoginByAccountButtonTest(){
        HomePage homePage =new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);

        homePage.waitAndClickOnAccountProfileButton();
        loginPage.inputEmail(user.getEmail());
        loginPage.inputPassword(user.getPassword());
        loginPage.clickOnLoginButton();

        Assert.assertTrue(homePage.isCreateOrderButtonDisplayed());
    }

    // можно залогиниться по ссылке войти со страницы регистрации
    @DisplayName("Can login by login link from registration page")
    @Test
    public void userCanBeLoginByLoginLinkFromRegistrationPageTest(){
        HomePage homePage =new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);

        homePage.waitAndClickOnEnterAccountButton();
        loginPage.clickOnRegistrationLink();
        registrationPage.clickOnLoginLink();

        loginPage.inputEmail(user.getEmail());
        loginPage.inputPassword(user.getPassword());
        loginPage.clickOnLoginButton();

        Assert.assertTrue(homePage.isCreateOrderButtonDisplayed());
    }

    // можно залогиниться по ссылке войти со страницы восстановления пароля
    @DisplayName("Can login by login link from forgot password page")
    @Test
    public void userCanBeLoginByLoginLinkFromForgotPasswordPageTest(){
        HomePage homePage =new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);

        homePage.waitAndClickOnEnterAccountButton();
        loginPage.clickOnForgotPasswordLink();
        forgotPasswordPage.clickOnLoginLink();

        loginPage.inputEmail(user.getEmail());
        loginPage.inputPassword(user.getPassword());
        loginPage.clickOnLoginButton();

        Assert.assertTrue(homePage.isCreateOrderButtonDisplayed());
    }
}
