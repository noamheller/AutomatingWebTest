package project;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * a Singelton class for creating one instance of driver.
 * driver initiation depends on XML file
 */
public class Singelton {
    private static WebDriver driver;

    public static WebDriver getDriverInstance() throws Exception {
        if(driver == null){
            String type = getData("browserType");
            if(type.equals("Chrome")){
                System.setProperty("webdriver.chrome.driver", "/Users/noamheller/" +
                        "Documents/NOAM/WORK/Automation Course/chromedriver");
                driver = new ChromeDriver();
            }else if(type.equals("FF")){
                System.setProperty("webdriver.firefox.driver", "C:\\.exe");
                driver = new FirefoxDriver();
            }
        }
        return driver;
    }

    /**
     * get data from xml file
     * @param keyName tag name in xml file of browser type
     * @return driver kind
     * @throws Exception
     */
    public static String getData(String keyName) throws Exception {
        File fXmlFile = new File("/Users/noamheller/Documents/NOAM/WORK/Automation" +
                " Course/Project2/src/main/resources/data.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
        doc.getDocumentElement().normalize();
        assert doc != null;
        return doc.getElementsByTagName(keyName).item(0).getTextContent();
    }
}
