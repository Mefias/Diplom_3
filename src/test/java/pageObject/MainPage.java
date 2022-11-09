package pageInfo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends StellarBurgersPage {
    @Override
    public void open() {
        driver.get(baseUrl);
        waitForLoad();
    }

    @Override
    public void waitForLoad() {
        new IngredientSelectorArea(driver).waitForLoad();
    }

    public MainPage(WebDriver driver) {
        super(driver);
    }

    private final static By accountButton = By.partialLinkText("Личный Кабинет");
    public AccountPage openAccountPage() {
        driver.findElement(accountButton).click();

        AccountPage accountPage = new AccountPage(driver);
        accountPage.waitForLoad();
        return accountPage;
    }

    public LoginPage openAccountPageWhenUnautorized() {
        driver.findElement(accountButton).click();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoad();
        return loginPage;
    }
    private final static By buttonPlaceOrder = By.xpath(".//button[text()='Оформить заказ']");
    public void waitButtonPlaceOrder() {
        waitForElement(buttonPlaceOrder);
    }
    private final static By buttonLogin = By.xpath(".//button[text()='Войти в аккаунт']");
    public LoginPage clickButtonLogin() {
        driver.findElement(buttonLogin).click();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoad();
        return loginPage;
    }

}
