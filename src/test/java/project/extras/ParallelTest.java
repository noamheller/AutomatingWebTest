package project.extras;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParallelTest {

        WebDriver driver;

        /**
         * This function will execute before each Test tag in testng.xml
         * @param browser
         * @throws Exception
         */
        @BeforeTest
        @Parameters("browser")
        public void setup(String browser) throws Exception{
            if(browser.equalsIgnoreCase("firefox")){
                System.setProperty("webdriver.gecko.driver", ".\\geckodriver.exe");
                driver = new FirefoxDriver();
            }
            else if(browser.equalsIgnoreCase("chrome")){
                System.setProperty("webdriver.chrome.driver","/Users/noamheller/Documents/NOAM/WORK/Automation Course/chromedriver");
                driver = new ChromeDriver();
            }
            else if(browser.equalsIgnoreCase("Edge")){
                System.setProperty("webdriver.edge.driver",".\\MicrosoftWebDriver.exe");
                driver = new EdgeDriver();
            }
            else{
                throw new Exception("Browser is not correct");
            }
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }

        @Test
        public void testParameterWithXML() throws InterruptedException{
            driver.get("https://buyme.co.il/");
            //
        }
}

