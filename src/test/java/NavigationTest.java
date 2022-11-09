import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.hamcrest.Matchers;
import org.junit.*;
import org.openqa.selenium.chrome.ChromeDriver;
import pageInfo.AccountPage;
import pageInfo.IngredientSelectorArea;
import pageInfo.LoginPage;
import pageInfo.MainPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class NavigationTest extends StellarBurgersTest {
    protected MainPage page;

    @BeforeClass
    public static void beforeClass() {
        user = getRandomUser();
        user.register();
    }

    @Before
    public void before() {
        driver = new ChromeDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        page = loginPage.login(user);
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
    @Description("Переход в личный кабинет по клику на «Личный кабинет»")
    public void openAccountPage() {
        openAccountPageAndWaitLoad();
    }

    @Step
    @Description("Переход в личный кабинет по клику на «Личный кабинет» и ожидание загрузки")
    public AccountPage openAccountPageAndWaitLoad() {
        AccountPage accPage = page.openAccountPage();
        return accPage;
    }

    @Test
    @Description("Переход из личного кабинета в конструктор по клику на «Конструктор»")
    public void openMainPageFromAccountWithConstructorButton() {
        AccountPage accPage = openAccountPageAndWaitLoad();
        MainPage mainPage = accPage.clickConstructorButton();
        mainPage.waitForLoad();
    }

    @Test
    @Description("Переход из личного кабинета в конструктор по клику на логотип Stellar Burgers")
    public void openMainPageFromAccountWithLogoButton() {
        AccountPage accPage = openAccountPageAndWaitLoad();
        MainPage mainPage = accPage.clickLogoButton();
        mainPage.waitForLoad();
    }

    @Test
    @Description("Раздел «Конструктор»: работает переходы к разделу «Булки»")
    public void openBunsFromMainPage() {
        openIngredientFromMainPage("Булки");
    }

    @Test
    @Description("Раздел «Конструктор»: работает переходы к разделу «Соусы»")
    public void openSaucesFromMainPage() {
        openIngredientFromMainPage("Соусы");
    }

    @Test
    @Description("Раздел «Конструктор»: работает переходы к разделу «Начинки»")
    public void openMainsFromMainPage() {
        openIngredientFromMainPage("Начинки");
    }

    private void openIngredientFromMainPage(String type) {
        IngredientSelectorArea selectorArea = new IngredientSelectorArea(driver);
        clickOtherIngredientTypeIfMineSelected(selectorArea, type);
        selectorArea.select(type);
        assertTrue(selectorArea.isSelected(type));
        assertThat(Math.abs(selectorArea.getIngredientTypeHeaderScrollPosition(type)), Matchers.lessThan(10)) ;
    }
    private void clickOtherIngredientTypeIfMineSelected(IngredientSelectorArea selectorArea, String type) {
        if (selectorArea.isSelected(type))
            selectorArea.select(type.equals("Булки") ? "Начинки" : "Булки");
    }
}
