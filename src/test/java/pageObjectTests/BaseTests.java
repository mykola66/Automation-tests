package pageObjectTests;

import enums.BrowserType;
import helpers.BrowserFabric;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

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
    public void tearDown() {
        driver.quit();
    }

}
