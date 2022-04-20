package pageObjectTests;

import enums.BrowserType;
import helpers.BrowserFabric;
import helpers.Screenshot;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseTests {
    protected WebDriver driver;
    protected String url;

    @Parameters({"url","browser"})

    @BeforeMethod
    public void startUp(String url,String browser) {
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
        BrowserType browserType = browser.equals("chrome")?BrowserType.CHROME:BrowserType.EDGE;
        driver = BrowserFabric.getDriver(browserType);
        this.url = url;
    }

    @AfterMethod
    public void finishTest(ITestResult testResult) {
        if (testResult.getStatus() == testResult.FAILURE) {
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("=MM-dd-yyyy--HH-mm-ss");
            String filename = testResult.getName() + formatter.format(date);
            Screenshot.get(driver, filename);
        }
        driver.quit();
    }

}
