import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page.AccountProfilePage;
import page.HomePage;
import page.LoginPage;
import user.User;
import user.UserClient;
import user.UserGenerator;

public class AccountProfilePageTest {

    private WebDriver driver;
    private User user;
    private UserClient userClient;
    private String accessToken;
    private HomePage homePage;
    private LoginPage loginPage;
    private AccountProfilePage accountProfilePage;

    @Before
    public void setUp() {

        // YaBro
//         System.setProperty("webdriver.chrome.driver","/Users/novikaud/WebDriver/bin/yandexdriver");
//         driver = new ChromeDriver();

        // Chrome
        System.setProperty("webdriver.chrome.driver","/Users/novikaud/WebDriver/bin/chromedriver");
        driver = new ChromeDriver();

        driver.get("https://stellarburgers.nomoreparties.site/");

        userClient = new UserClient();
        user = UserGenerator.getRandomUser();

        ValidatableResponse registerResponse = userClient.register(user);
        accessToken = registerResponse.extract().path("accessToken");

        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        accountProfilePage = new AccountProfilePage(driver);
    }

    @After
    public void tearDown() {
        userClient.delete(accessToken);
        driver.quit();
    }

    // можно перейти на страницу профиля после клика на личный кабинет авторизованным пользователем
    @DisplayName("Login user can opened profile page")
    @Test
    public void loginUserCanOpenProfilePageTest() {

        homePage.waitAndClickOnAccountProfileButton();
        loginPage.inputEmail(user.getEmail());
        loginPage.inputPassword(user.getPassword());
        loginPage.clickOnLoginButton();

        homePage.waitAndClickOnAccountProfileButton();

        Assert.assertTrue(accountProfilePage.isLogoutButtonDisplayed());
    }

    // из личного кабинета при клике на конструктор переходим на главную страницу
    @DisplayName("Opened home page after login user click on constructor from profile page")
    @Test
    public void openedHomePageAfterLoginUserClickOnConstructorFromAccountProfilePageTest() {

        homePage.waitAndClickOnAccountProfileButton();
        loginPage.inputEmail(user.getEmail());
        loginPage.inputPassword(user.getPassword());
        loginPage.clickOnLoginButton();

        homePage.waitAndClickOnAccountProfileButton();
        accountProfilePage.clickOnConstructorButton();

        Assert.assertTrue(homePage.isCreateOrderButtonDisplayed());
    }

    // из личного кабинета при клике на лого переходим на главную страницу
    @DisplayName("Opened home page after login user click on logo from profile page")
    @Test
    public void openedHomePageAfterLoginUserClickOnLogoFromAccountProfilePageTest() {

        homePage.waitAndClickOnAccountProfileButton();
        loginPage.inputEmail(user.getEmail());
        loginPage.inputPassword(user.getPassword());
        loginPage.clickOnLoginButton();

        homePage.waitAndClickOnAccountProfileButton();
        accountProfilePage.clickOnLogoButton();

        Assert.assertTrue(homePage.isCreateOrderButtonDisplayed());
    }

    // открывается страница логина после логаута
    @DisplayName("Opened login page after logout")
    @Test
    public void openedLoginPageAfterLogoutTest() {

        homePage.waitAndClickOnAccountProfileButton();
        loginPage.inputEmail(user.getEmail());
        loginPage.inputPassword(user.getPassword());
        loginPage.clickOnLoginButton();

        homePage.waitAndClickOnAccountProfileButton();
        accountProfilePage.waitAndClickOnLogoutButton();

        Assert.assertTrue(loginPage.isLoginButtonDisplayed());

    }
}
