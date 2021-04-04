package project;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

/**
 * a Singelton class for creating one instance of extent reporter file
 */
public class ExtentSingelton {

        private static ExtentReports extent;

        public static ExtentReports getExtentInstance() throws Exception {
            if(extent == null){
                String report = System.getProperty("user.dir");
                ExtentSparkReporter htmlReporter = new ExtentSparkReporter(report + "\\extent.html");
                extent = new ExtentReports();
                extent.attachReporter(htmlReporter);
                extent.setSystemInfo("Environment", "Production");
                extent.setSystemInfo("Tester", "noam");

            }
            return extent;
        }
}
