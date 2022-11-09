package pageInfo;

import org.example.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends StellarBurgersPage {
    @Override
    public void open() {
        driver.get(baseUrl+"/login");
        waitForLoad();
    }

    @Override
    public void waitForLoad() {
        waitForElement(loginButton);
    }

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private final static By loginField = By.name("name");
    private final static By passwordField = By.name("Пароль");
    private final static By loginButton = By.xpath(".//button[text()='Войти']");

    public MainPage login(User user) {
        MainPage mainPage = login(user.email, user.password);
        mainPage.waitForLoad();
        return mainPage;
    }
    public MainPage login(String login, String password) {
        driver.findElement(loginField).sendKeys(login);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
        return new MainPage(driver);
    }
    public void waitLoginButton() {
        waitForElement(loginButton);
    }
}
