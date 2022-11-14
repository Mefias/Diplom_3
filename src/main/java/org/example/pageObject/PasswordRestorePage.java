package pageInfo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PasswordRestorePage extends StellarBurgersPage {
    @Override
    public void open() {
        driver.get(baseUrl+"/forgot-password");
        waitForLoad();
    }

    @Override
    public void waitForLoad() {
        waitForElement(loginLink);
    }

    public PasswordRestorePage(WebDriver driver) {
        super(driver);
    }

    private final static By loginLink = By.linkText("Войти");
    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }
}
