package project.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import project.Const;
import java.util.List;

/**
 * HomePage class extends BasePage, controlling the Home Page of "BuyMe.co.il" website.
 */
public class HomePage extends BasePage{
    private static final By HOME_PAGE_FORM_LOCATORS = By.className("chosen-with-drop");
    private static final By PRICE_LOCATOR = By.linkText("סכום");
    private static final By REGION_LOCATOR = By.linkText("אזור");
    private static final By CATEGORY_LOCATOR = By.linkText("קטגוריה");

    /**
     * click "כניסה |ֿ הרשמה" button
     * @throws Exception
     */
    public void clickLogin() throws Exception {
            clickElement(Const.LOG_IN_HOME_LOCATOR);
    }

    /**
     * choose price, region and category in home page gift search
     * @throws Exception
     */
    public void searchGift() throws Exception {
        clickElement(PRICE_LOCATOR);
        clickWebElementFromList(optionsList(), 2);
        clickElement(REGION_LOCATOR);
        clickWebElementFromList(optionsList(), 2);
        clickElement(CATEGORY_LOCATOR);
        clickWebElementFromList(optionsList(), 2);

        }

    /**
     * gets url as string from search gift button WebElement
     * @return URL expected to be navigate to
     * @throws Exception
     */
    public String href() throws Exception {
        return getWebElement(Const.SEARCH_GIFT_LOCATOR).getAttribute("href");
    }

    /**
     * click search gift button WebElement
     * @throws Exception
     */
    public void clickSearchGift() throws Exception {
        clickElement(Const.SEARCH_GIFT_LOCATOR);
    }

    /**
     * get the options of the search form
     * @return list of WebElements
     * @throws Exception
     */
    private List<WebElement> optionsList() throws Exception {

       return getWebElementListFromWebElement(HOME_PAGE_FORM_LOCATORS,Const.OPTIONS_LOCATOR);
    }


}
