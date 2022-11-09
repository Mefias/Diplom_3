import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import pageInfo.LoginPage;
import pageInfo.RegistrationPage;

public class RegistrationTest extends StellarBurgersTest {
    private RegistrationPage registrationPage;

    @Before
    public void before() {
        driver = new ChromeDriver();
        registrationPage = new RegistrationPage(driver);
        registrationPage.open();
        user = getRandomUser();
    }

    @After
    public void after() {
        if (user != null)
            user.delete(null);
        if (driver != null)
            driver.quit();
    }

    @Test
    @Description("Успешная регистрация")
    public void successfullyRegistrationLeadsAbilityToLogin() {
        LoginPage loginPage = registrationPage.register(user);
        loginPage.waitLoginButton();
        loginAndConfirm(user);
    }

    @Test
    @Description("Ошибка для некорректного пароля. Минимальный пароль — шесть символов")
    public void registrationFailIfPasswordLengthLessThan6() {
        user = getRandomUser();
        user.password = "12345";
        registrationPage.register(user);
        registrationPage.waitForWrongPasswordLabel();
    }
}
