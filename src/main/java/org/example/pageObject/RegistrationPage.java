package pageInfo;

import org.example.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage extends StellarBurgersPage {
    @Override
    public void open() {
        driver.get(baseUrl+"/register");
        waitForLoad();
    }

    @Override
    public void waitForLoad() {
        waitForElement(registerButton);
    }

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    private final static By nameField = By.xpath(".//div[@class='input__container' and .//label[text()='Имя']]//input");
    private final static By loginField = By.xpath(".//div[@class='input__container' and .//label[text()='Email']]//input");
    private final static By passwordField = By.xpath(".//div[@class='input__container' and .//label[text()='Пароль']]//input");
    private final static By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");

    private final static By loginLink = By.linkText("Войти");
    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }

    private final static By wrongPasswordLabel = By.xpath(".//div[@class='input__container' and .//label[text()='Пароль']]//p[text()='Некорректный пароль']");
    private final static By registrationFailLabel = By.className("input__error");

    public LoginPage register(User user) {
        driver.findElement(nameField).sendKeys(user.name);
        driver.findElement(loginField).sendKeys(user.email);
        driver.findElement(passwordField).sendKeys(user.password);
        driver.findElement(registerButton).click();
        return new LoginPage(driver);
    }

    public void waitForWrongPasswordLabel() {
        waitForElement(wrongPasswordLabel);
    }
}
