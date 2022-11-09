import io.qameta.allure.Description;
import org.junit.*;
import org.openqa.selenium.chrome.ChromeDriver;
import pageInfo.MainPage;
import pageInfo.PasswordRestorePage;
import pageInfo.RegistrationPage;

public class LoginTest extends StellarBurgersTest {
    @BeforeClass
    public static void beforeClass() {
        user = getRandomUser();
        user.register();
    }

    @Before
    public void before() {
        driver = new ChromeDriver();
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
    @Description("Вход по кнопке «Войти в аккаунт» на главной")
    public void enterByButtonInMainPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickButtonLogin();
        loginAndConfirm(user);
    }

    @Test
    @Description("Вход через кнопку «Личный кабинет»")
    public void enterByButtonInAccountPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.openAccountPageWhenUnautorized();
        loginAndConfirm(user);
    }

    @Test
    @Description("Вход через кнопку в форме регистрации")
    public void enterByButtonInRegistrationPage() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.open();
        registrationPage.clickLoginLink();
        loginAndConfirm(user);
    }

    @Test
    @Description("Вход через кнопку в форме восстановления пароля")
    public void enterByButtonInPasswordRestorePage() {
        PasswordRestorePage passwordRestorePage = new PasswordRestorePage(driver);
        passwordRestorePage.open();
        passwordRestorePage.clickLoginLink();
        loginAndConfirm(user);
    }
}
