package project.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import project.Const;
import java.util.List;

/**
 * GiftPage class extends BasePage, enters the final form of sending gifts in "BuyMe.co.il" website.
 */
public class GiftPage extends BasePage{
    private static final By FOR_SOMEONE_BUTTON_LOCATOR = By.className("button-forSomeone");
    private static final By FRIEND_NAME_LOCATOR = By.id("friendName");
    private static final By EVENT_TYPE_LOCATOR = By.className("selected-name");
    private static final By EVENT_TYPE_LIST_LOCATOR = By.className("show-options");
    private static final By TEXT_AREA_LOCATOR = By.className("bm-textarea");
    private static final By CLEAR_TEXT_AREA_LOCATOR = By.className("textarea-clear-all");
    private static final By UPLOAD_PIC_LOCATOR = By.className("bm-media-upload");
    private static final By PIC_LOCATOR = By.className("thumbnail");
    private static final By NOW_BUTTON_LOCATOR = By.className("button-now");
    private static final By CONTINUE_LOCATOR = By.cssSelector("button[type=submit]");
    private static final By HOW_TO_SEND_LOCATOR = By.className("bm-sending-methods");
    private static final By HOW_TO_SEND_OPTION_LOCATOR = By.className("toggle-icon");
    private static final By EMAIL_OPTION_LOCATOR = By.className("checked");
    private static final By BM_INPUT_LOCATOR = By.className("bm-input");
    private static final By SENDER_FORM_LOCATOR = By.className("md-12");


    /**
     * enter receiver name and returns it from the WebElement
     * @return receiver name that was entered
     * @throws Exception
     */
    public String chooseReceiver() throws Exception {
        explicitWaitForElement(FOR_SOMEONE_BUTTON_LOCATOR);
        clickElement(FOR_SOMEONE_BUTTON_LOCATOR);
        sendKeysToElement(FRIEND_NAME_LOCATOR,"אמא יקרה לי");
        return getWebElementFromWebElement(FRIEND_NAME_LOCATOR, Const.INPUT_LOCATOR).getAttribute("value");
    }

    /**
     * fill send gift form until sender name
     * returns it from the WebElement
     * @return sender name that was entered
     * @throws Exception
     */
    public String sendGift() throws Exception {
        chooseCelebration();
        blessing();
        uploadPic();
        explicitWaitForElement(PIC_LOCATOR);
        clickContinue();
        clickNow();
        enterEmail();
        senderName();
        return getWebElementFromWebElement(SENDER_FORM_LOCATOR, Const.INPUT_LOCATOR).getAttribute("value");
    }

    /**
     * choose celebration from list of celebration options
     * @throws Exception
     */
    private void chooseCelebration() throws Exception {
        explicitWaitForElement(EVENT_TYPE_LOCATOR);
        clickElement(EVENT_TYPE_LOCATOR);
        List<WebElement> list = getWebElementListFromWebElement(EVENT_TYPE_LIST_LOCATOR, Const.OPTIONS_LOCATOR);
        clickWebElementFromList(list,3);

    }

    /**
     * clears text area and enters a blessing instead
     * @throws Exception
     */
    private void blessing() throws Exception {
        clickElement(CLEAR_TEXT_AREA_LOCATOR);
        sendKeysToElement(TEXT_AREA_LOCATOR, "תתחדשי!");
    }

    /**
     * upload a picture to form
     * @throws Exception
     */
    private void uploadPic() throws Exception {

        getWebElementFromWebElement(UPLOAD_PIC_LOCATOR,Const.INPUT_LOCATOR).sendKeys("/Users/noamheller/Documents/NOAM/WORK/Automation Course/Project2/src/test/java/project/pages/gift.png");


    }

    /**
     * click continue button
     * @throws Exception
     */
    private void clickContinue() throws Exception {
       clickElement(CONTINUE_LOCATOR);
    }

    /**
     * click continue button
     * @throws Exception
     */
    private void clickNow() throws Exception {
        clickElement(NOW_BUTTON_LOCATOR);
    }

    /**
     * choose email option and enters an email
     * @throws Exception
     */
    private void enterEmail() throws Exception {
        List<WebElement> list = getWebElementListFromWebElement(HOW_TO_SEND_LOCATOR, HOW_TO_SEND_OPTION_LOCATOR);
        clickWebElementFromList(list,1);
        getWebElementFromWebElement(EMAIL_OPTION_LOCATOR,BM_INPUT_LOCATOR).sendKeys("noam.heller015@gmail.com");
    }

    /**
     * enter sender name
     * @throws Exception
     */
    private void senderName() throws Exception {
        explicitWaitForElement(SENDER_FORM_LOCATOR);
        WebElement senderName = getWebElementFromWebElement(SENDER_FORM_LOCATOR, BM_INPUT_LOCATOR);
        senderName.sendKeys("נועם");
    }

    /**
     * click continue to payment button
     * @throws Exception
     */
    public void clickContinueToPayment() throws Exception {
        clickElement(CONTINUE_LOCATOR);

    }
}
