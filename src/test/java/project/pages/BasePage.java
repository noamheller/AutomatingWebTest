package project.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import project.Singelton;
import java.time.Duration;
import java.util.List;

/**
 * the base page includes methods to use in "BuyMe.co.il" website sanity testing.
 */
public class BasePage {

    /**
     * get and click WebElement by its locator.
     * @param locator locator of WebElement
     * @throws Exception
     */
       public void clickElement(By locator) throws Exception {

           getWebElement(locator).click();
       }
    /**
     * click on a WebElement
     * @param webElement the WebElement to be clicked
     * @throws Exception
     */
        public void clickElement(WebElement webElement) throws Exception {

            webElement.click();
        }
    /**
     * send keys to WebElement by its locator
     * @param locator locator of WebElement
     * @param text keys to send to the WebElement
     * @throws Exception
     */
        public void sendKeysToElement(By locator, String text) throws Exception {

            getWebElement(locator).sendKeys(text);
        }

    /**
     * find and returns a WebElement by its locator, get driver instance from singelton.
     * @param locator locator of WebElement
     * @return WebElement of the locator
     * @throws Exception
     */
        public WebElement getWebElement(By locator) throws Exception {

            return Singelton.getDriverInstance().findElement(locator);
        }

    /**
     * gets and returns WebElements List From a WebElement by its locators
     * @param elementLocator father element locator
     * @param listLocator children elements locator
     * @return List of WebElements
     * @throws Exception
     */
        public List<WebElement> getWebElementListFromWebElement(By elementLocator, By listLocator) throws Exception {

            WebElement webElement = Singelton.getDriverInstance().findElement(elementLocator);
            return webElement.findElements(listLocator);
        }

    /**
     * click WebElement From a List of WebElements.
     * @param list a List of WebElements
     * @param index index of the WebElement to be clicked from the list
     */
        public void clickWebElementFromList (List<WebElement> list, int index ){
           list.get(index).click();
        }

    /**
     * send Keys To a WebElement From a List of WebElements
     * @param list a List of WebElements
     * @param index index of the WebElement to be clicked from the list
     * @param keys keys to send to the WebElement
     */
        public void sendKeysToWebElementFromList (List<WebElement> list, int index , String keys){
            list.get(index).sendKeys(keys);
        }

    /**
     * get a WebElement From its father WebElement
     * @param main father WebElement
     * @param son son WebElement
     * @return the son WebElement
     * @throws Exception
     */
        public WebElement getWebElementFromWebElement(By main, By son) throws Exception {
            return getWebElement(main).findElement(son);
        }

        /**
         * creates explicit Wait For Element to be clickable by its locator.
         * @param locator locator of WebElement to wait for
         * @throws Exception
         */
        public void explicitWaitForElement (By locator) throws Exception {
                WebDriverWait wait = new WebDriverWait(Singelton.getDriverInstance(), Duration.ofSeconds(10));
                wait.until(ExpectedConditions.elementToBeClickable(locator));
            }

}
