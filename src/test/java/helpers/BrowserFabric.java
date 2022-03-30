package helpers;

import enums.BrowserType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;

public class BrowserFabric {
    public static WebDriver getDriver(BrowserType browserType){
        switch (browserType){
            case FIREFOX:return getFirefoxDriver();
            case EDGE:return getEdgeDriver();
            case OPERA:return getOperaDriver();
            default:return getChromeDriver();
        }
    }
    private static WebDriver getChromeDriver(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("window-size=1400,1000");
        options.addArguments("--headless");
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(options);
    }
    private static WebDriver getFirefoxDriver(){
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--height=800");
        options.addArguments("--width=1400");
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver(options);
    }
    private static WebDriver getEdgeDriver(){
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver();
    }
    private static WebDriver getOperaDriver(){
        WebDriverManager.operadriver().setup();
        return new OperaDriver();
    }

}
