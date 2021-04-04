package project.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import project.Const;
import java.util.List;

/**
 * RegisterPage class extends BasePage, fill the Registration form of "BuyMe.co.il" website.
 */
public class RegisterPage extends BasePage{
    static final By TO_REGISTER_LOCATOR = By.className("text-link");

    /**
     * fill registration form
     * @throws Exception
     */
    public void registration() throws Exception {
        clickRegistration();
        enterCredentials();
    }

    /**
     * click Registration link
     * @throws Exception
     */
    public void clickRegistration() throws Exception {
        clickElement(TO_REGISTER_LOCATOR);
    }

    /**
     * enter Credentials in registration form
     * @throws Exception
     */
    private void enterCredentials() throws Exception {
        List<WebElement> list = getWebElementListFromWebElement(Const.REGISTER_FORM_LOCATOR,Const.INPUT_LOCATOR);
        sendKeysToWebElementFromList(list,0,"noam");
        sendKeysToWebElementFromList(list,1,"noam.heller015@gmail.com");
        sendKeysToWebElementFromList(list,2,"12345678aA");
        sendKeysToWebElementFromList(list,3,"12345678aA");

    }

    /**
     * click login button on registration form
     * @throws Exception
     */
    public void clickLogin() throws Exception {
        clickElement(getWebElementFromWebElement(Const.REGISTER_FORM_LOCATOR,Const.BUTTON_LOCATOR));
    }

}

