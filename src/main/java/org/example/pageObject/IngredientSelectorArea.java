package pageInfo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class IngredientSelectorArea extends StellarBurgersPage {
    public IngredientSelectorArea(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        new MainPage(driver).open();
        waitForLoad();
    }

    @Override
    public void waitForLoad() {
        waitForElement(headerArea);
    }

    By headerArea = By.className("BurgerIngredients_ingredients__1N8v2");
    private By getSelector(String type) {
        return By.xpath(".//section[@class='BurgerIngredients_ingredients__1N8v2']//div[./span[text()='"+type+"']]");
    }
    private By getIngredientTypeHeaderArea(String type) {
        return By.xpath(".//div[@class='BurgerIngredients_ingredients__menuContainer__Xu3Mo']/h2[text()='"+type+"']");
    }
    public void select(String type) {
        driver.findElement(getSelector(type)).click();
        int newDiff = Integer.MAX_VALUE;
        int oldDiff = Integer.MAX_VALUE;
        do { // ждем окончания прокрутки
            oldDiff = newDiff;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            newDiff = getIngredientTypeHeaderScrollPosition(type);
        } while (newDiff != oldDiff);
    }
    public boolean isSelected(String type) {
        return driver.findElement(getSelector(type)).getAttribute("class").contains("tab_tab_type_current");
    }
    public int getIngredientTypeHeaderScrollPosition(String type) {
        WebElement weSelector = driver.findElement(getSelector(type));
        WebElement weHeader = driver.findElement(getIngredientTypeHeaderArea(type));
        return weHeader.getLocation().y - (weSelector.getLocation().y + weSelector.getSize().height);
    }
}
