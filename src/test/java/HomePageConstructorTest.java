import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.HomePage;

public class HomePageConstructorTest {

    private WebDriver driver;
    private HomePage homePage;

    public HomePageConstructorTest() {
    }

    @Before
    public void setUp() {
        // YaBro
//        System.setProperty("webdriver.chrome.driver","/Users/novikaud/WebDriver/bin/yandexdriver");
//        driver = new ChromeDriver();

        // Chrome
        System.setProperty("webdriver.chrome.driver","/Users/novikaud/WebDriver/bin/chromedriver");
        driver = new ChromeDriver();

        driver.get("https://stellarburgers.nomoreparties.site/");

        homePage = new HomePage(driver);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    // можно переключаться на соусы внутри конструктора
    @DisplayName("Can switch to sauce in constructor")
    @Test
    public void canSwitchToSauceTabInConstructorTest() {
        final String name = "Соусы";
        homePage.waitAndClickOnSauceTab();
        Assert.assertTrue(homePage.isTabSelected(name));
    }

    // можно переключаться на начинки внутри конструктора
    @DisplayName("Can switch to ingredient in constructor")
    @Test
    public void canSwitchToIngredientTabInConstructorTest() {
        final String name = "Начинки";
        homePage.waitAndClickOnIngredientTab();
        Assert.assertTrue(homePage.isTabSelected(name));
    }

    // можно переключаться на булки внутри конструктора
    @DisplayName("Can switch to bun in constructor")
    @Test
    public void canSwitchToBunTabInConstructorTest() {
        final String name = "Булки";
        homePage.waitAndClickOnSauceTab();
        homePage.waitAndClickOnBunTab();
        new WebDriverWait(driver, 3);
        Assert.assertTrue(homePage.isTabSelected(name));
    }
}