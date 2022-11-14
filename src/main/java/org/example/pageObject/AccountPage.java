package pageInfo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage extends StellarBurgersPage {
    private final static By logoutLink = By.xpath(".//button[text()='Выход']");

    @Override
    public void open() {
        driver.get(baseUrl+"/account/profile");
        waitForLoad();
    }
    @Override
    public void waitForLoad() {
        waitForElement(logoutLink);
    }

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    public void waitForLoadWheUnauthorized() {
        new LoginPage(driver).waitForLoad();
    }
    public LoginPage clickLogoutLink() {
        driver.findElement(logoutLink).click();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoad();
        return loginPage;
    }
}
