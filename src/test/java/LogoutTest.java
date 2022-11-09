import io.qameta.allure.Description;
import org.junit.*;
import org.openqa.selenium.chrome.ChromeDriver;
import pageInfo.AccountPage;
import pageInfo.LoginPage;
import pageInfo.MainPage;

public class LogoutTest extends StellarBurgersTest {
    protected MainPage page;

    @BeforeClass
    public static void beforeClass() {
        user = getRandomUser();
        user.register();
    }

    @Before
    public void before() {
        driver = new ChromeDriver();
        page = new MainPage(driver);
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickButtonLogin();
        loginAndConfirm(user);
    }

    @After
    public void after() {
        if (driver != null)
            driver.quit();
    }

    @AfterClass
    public static void afterClass() {
        if (user != null)
            user.delete(null);
    }

    @Test
    @Description("Выход из аккаунта по кнопке «Выйти» в личном кабинете")
    public void logout() {
        AccountPage accPage = page.openAccountPage();
        LoginPage loginPage = accPage.clickLogoutLink();
        loginPage.waitForLoad();
    }
}
