import com.google.gson.Gson;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.example.User;
import org.openqa.selenium.WebDriver;
import pageInfo.LoginPage;
import pageInfo.MainPage;
import pageInfo.StellarBurgersPage;

import java.util.Random;

public class StellarBurgersTest {
    protected WebDriver driver;
    protected static User user;
    protected StellarBurgersPage page;

    @Step
    @Description("Создаем случайного пользователя")
    static User getRandomUser() {
        int rnd = new Random().nextInt();
        User user = new User("test"+rnd+"@test.ru", "123456789", "Random User "+rnd);
        System.out.println("Create user: "+new Gson().toJson(user));
        return user;
    }

    @Step
    @Description("Логин и ожидание подтверждения: {user}")
    void loginAndConfirm(User user) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoad();
        MainPage mainPage = loginPage.login(user);
        mainPage.waitButtonPlaceOrder();
    }
}
