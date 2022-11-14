package pageInfo;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class StellarBurgersPage {
    public static String baseUrl = "https://stellarburgers.nomoreparties.site";
    protected final WebDriver driver;

    public abstract void open();
    public abstract void waitForLoad();
    public StellarBurgersPage(WebDriver driver) {
        this.driver = driver;
    }

    private final static By constructorButton = By.partialLinkText("Конструктор");
    public MainPage clickConstructorButton() {
        driver.findElement(constructorButton).click();
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForLoad();
        return mainPage;
    }

    private final static By logoButton = By.className("AppHeader_header__logo__2D0X2");
    public MainPage clickLogoButton() {
        driver.findElement(logoButton).click();
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForLoad();
        return mainPage;
    }

    @Step
    @Description("Ждем появления элемента {element}")
    void waitForElement(By element) {
        new WebDriverWait(driver,10)
                .until(ExpectedConditions.visibilityOfElementLocated(element));
    }
}
