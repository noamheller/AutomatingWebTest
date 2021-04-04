package project.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import project.Const;
import java.util.List;
/**
 * BusinessPage class extends BasePage, controlling the Business Page of "BuyMe.co.il" website.
 */

public class BusinessPage extends BasePage{
    private static final By BUSINESS_GRID_LOCATOR = By.className("bm-product-cards");
    private static final By PRICE_FORM_LOCATOR = By.cssSelector("form[action=submitMoney]");

    /**
     *pick a business
     * @throws Exception
     */
    public void pickBusiness() throws Exception {
        List<WebElement> list = getWebElementListFromWebElement(BUSINESS_GRID_LOCATOR,Const.OPTIONS_LOCATOR );
        clickWebElementFromList(list,5);
    }

    /**
     * enter a price and click next
     * @throws Exception
     */
    public void pickPrice() throws Exception {
        getWebElement(PRICE_FORM_LOCATOR).findElement(Const.INPUT_LOCATOR).sendKeys("150");
        clickElement(getWebElementFromWebElement(PRICE_FORM_LOCATOR,Const.BUTTON_LOCATOR));


    }

}
