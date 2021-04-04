package project.extras;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import project.Const;
import project.Singelton;
import project.pages.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * extra challenges on the project
 */
public class Extra {
    private static WebDriver driver;
    private static final By HEADLINE_LOCATOR = By.className("bottom-xs");
    private static final By REGISTER_FORM_ERROR_LOCATOR = By.className("parsley-required");
    private static final By SPINNER_LOCATOR = By.className("spinner");
    private static final By DOT_LOCATOR = By.className("bounce1");
    private static final By BOTTOM_LOCATOR = By.className("footer-bottom");


    /**
     * before class test, gets url from data.xml, opens browser
     * @throws Exception
     */
    @BeforeClass
    private void beforeClass() throws Exception {

        driver = Singelton.getDriverInstance();
        String myURL = Singelton.getData("URL");
        driver.get(myURL);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    /**
     * print the size of one of the loading dots
     * @throws Exception
     */
    @Test(priority = 1)
    private void loadingDots() throws Exception {
//works when loading process is slow
        BasePage basePage = new BasePage();
        driver.navigate().refresh();
        Dimension dot = basePage.getWebElementFromWebElement(SPINNER_LOCATOR, DOT_LOCATOR).getSize();
        System.out.println("the dot height is: " + dot.height + " and the dot width is: " + dot.width);
    }

    /**
     * sending empty registration form, asserts error messages
     */
    @Test (priority = 2)
    private void assertException() throws Exception {
        HomePage homePage = new HomePage();
        RegisterPage registerPage = new RegisterPage();
        homePage.explicitWaitForElement(Const.LOG_IN_HOME_LOCATOR);
        homePage.clickLogin();
        registerPage.clickRegistration();
        registerPage.clickLogin();
        List<WebElement> errorList = registerPage.getWebElementListFromWebElement(Const.REGISTER_FORM_LOCATOR, REGISTER_FORM_ERROR_LOCATOR);
        for (int i = 0; i < errorList.size(); i++) {
            Assert.assertEquals(errorList.get(i).getText(), "ערך זה דרוש.");
        }
    }

    /**
     * scroll to the bottom of the gift page and takes ScreenShot
     */
    @Test (priority = 3)
    private void bottomOfGiftScreen() throws Exception {
        GiftPage giftPage= new GiftPage();
        giftPage.clickElement(Const.SEARCH_GIFT_LOCATOR);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",giftPage.getWebElement(BOTTOM_LOCATOR));
        takeScreenShot("bottomScreenShoot");

    }

    /**
     * prints color of the WebElement "who to send to?"
     */
    @Test (priority = 4)
    private void colorOfHeadline() throws Exception {
        driver.navigate().to("https://buyme.co.il/package/874764/1347040");
        BasePage basePage = new BasePage();
        System.out.println("color is: " +basePage.getWebElement(HEADLINE_LOCATOR).getCssValue("color"));

    }
    /**
     * final test, quit driver connection
     */
    @AfterClass
    private void afterClass() {
        driver.quit();
    }


    private static void takeScreenShot(String ImagesPath) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File screenShotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(ImagesPath + ".jpg");
        try {
            FileUtils.copyFile(screenShotFile, destinationFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }



}

