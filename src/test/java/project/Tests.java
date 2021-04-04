package project;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import project.pages.BusinessPage;
import project.pages.GiftPage;
import project.pages.HomePage;
import project.pages.RegisterPage;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.testng.FileAssert.fail;

/**
 * This is a "BuyMe" website sanity test in java.
 * the test will assert different screens and progresses in the website.
 * all tests steps will be documented inside an extent report file.
 * @link https://buyme.co.il/
 * @author Noam Heller
 */
public class Tests {
    private static WebDriver driver;
    private static ExtentReports extent;
    private static ExtentTest test;
    private static final By X_LOCATOR = By.id("times");

    /**
     * before class test, gets url from data.xml, opens browser, builds an extent report file.
     *
     * @throws Exception
     */
    @BeforeClass
    private void beforeClass() throws Exception {
        extent = ExtentSingelton.getExtentInstance();
        test = extent.createTest("beforeClassTest", "gets url from data.xml, opens browser");
        test.log(Status.INFO, "@BeforeClass");
        boolean driverEstablish = false;
        try {
            driver = Singelton.getDriverInstance();
            String myURL = Singelton.getData("URL");
            driver.get(myURL);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            driverEstablish = true;
        } catch (Exception e) {
            e.printStackTrace();
            fail("Cant connect Driver");
            test.log(Status.FAIL, "Driver Connection Failed! " + e.getMessage());
            driverEstablish = false;
        } finally {
            if (driverEstablish) {
                test.log(Status.PASS, "Driver established successfully");
            }
        }

    }

    /**
     * registration screen test
     * sends keys to registration form and asserts them
     *
     * @throws Exception
     */
    @Test(priority = 1)
    private void registration() throws Exception {
        test = extent.createTest("registrationFormTest", "sends keys to registration form and asserts them");
        test.log(Status.INFO, "@registration");
        HomePage homePage = new HomePage();
        RegisterPage register = new RegisterPage();
        boolean driverEstablish = false;
        try {
            homePage.clickLogin();
            register.registration();
            List<WebElement> list = register.getWebElementListFromWebElement(Const.REGISTER_FORM_LOCATOR, Const.INPUT_LOCATOR);
            Assert.assertEquals(list.get(0).getAttribute("value"), "noam");
            Assert.assertEquals(list.get(1).getAttribute("value"), "noam.heller015@gmail.com");
            Assert.assertEquals(list.get(2).getAttribute("value"), "12345678aA");
            Assert.assertEquals(list.get(3).getAttribute("value"), "12345678aA");
//      register.clickLogin();
// for flow:
            driver.findElement(X_LOCATOR).click();
            driverEstablish = true;
        } catch (Exception e) {
            e.printStackTrace();
            fail("Cant assert registration form");
            test.log(Status.FAIL, "Registration form test Failed! " + e.getMessage());
            driverEstablish = false;
        } finally {
            if (driverEstablish) {
                test.log(Status.PASS, "Registration form test passed successfully");
            }
        }

    }

    /**
     * searching gift, asserts Url expected after search is correct
     * @throws Exception
     */
    @Test(priority = 2)
    private void HomeScreen() throws Exception {
        HomePage homePage = new HomePage();
        test = extent.createTest("Home Screen Test", "searching gift, asserts Url expected after search is correct");
        test.log(Status.INFO, "@Home Screen Test");
        boolean driverEstablish = false;
        try {
            homePage.searchGift();
            String expectedUrl = homePage.href();
            homePage.clickSearchGift();
            String currentUrl = driver.getCurrentUrl();
            Assert.assertEquals(currentUrl, expectedUrl);
            driverEstablish = true;
        }catch (Exception e){
            e.printStackTrace();
            fail("Can't complete test");
            test.log(Status.FAIL, "Can't complete test" + e.getMessage());
            driverEstablish = false;
        }finally {
            if (driverEstablish) {
                test.log(Status.PASS, "Home Screen test passed successfully");
            }
          }
    }

    /**
     * pick business, price and confirms
     *
     * @throws Exception
     */
    @Test(priority = 3)
    private void Business() throws Exception {
        BusinessPage businessPage = new BusinessPage();
        test = extent.createTest("Business Page test", "pick business, price and confirms");
        test.log(Status.INFO, "@business Page");
        boolean driverEstablish = false;
        try {
            businessPage.pickBusiness();
            businessPage.pickPrice();
            driverEstablish = true;
        }catch (Exception e){
            e.printStackTrace();
            fail("Can't complete test");
            test.log(Status.FAIL, "Can't complete test" + e.getMessage());
            driverEstablish = false;
        }finally {
            if (driverEstablish) {
                test.log(Status.PASS, "Business Page test passed successfully");
            }
          }
    }

    /**
     * fill Sender and Receiver information screen, asserts name fields
     * @throws Exception
     */
    @Test(priority = 4)
    private void giftSender() throws Exception {
        GiftPage giftPage = new GiftPage();
        test = extent.createTest("Send Gift Test", "fill Sender and Receiver information screen, asserts name fields");
        test.log(Status.INFO, "@send gift");
        boolean driverEstablish = false;
        try {
            String receiver = giftPage.chooseReceiver();
            Assert.assertEquals(receiver,"אמא יקרה לי");
            String senderName = giftPage.sendGift();
            Assert.assertEquals(senderName,"נועם");
            giftPage.clickContinueToPayment();
            driverEstablish = true;
        }catch (Exception e){
            e.printStackTrace();
            fail("Can't complete test");
            test.log(Status.FAIL, "Can't complete test" + e.getMessage());
            driverEstablish = false;
        }finally {
            if (driverEstablish) {
                test.log(Status.PASS, "Send Gift test successfully");
            }
          }


    }

    /**
     * final test, quit driver and extent report connections
     */
    @AfterClass
    private void afterClass() {
        driver.quit();
        extent.flush();
    }

}
