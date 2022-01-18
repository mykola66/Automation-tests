package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends BasePage{
    public MainPage(WebDriver driver){
        super(driver);
    }
    public void openMainPage(String url){
        driver.get(url);
    }
    private WebElement getShopElement(){
        By shopLocator = By.xpath("//a/span[text()='SHOP']");
        return driver.findElement(shopLocator);
    }
    private void closeSignup(){
        By closeSignupLocator = By.xpath("(//*[@class='dy-lb-close'])[2]");
        try {
            wait.until(ExpectedConditions.elementToBeClickable(closeSignupLocator));
            driver.findElement(closeSignupLocator).click();
        }catch (TimeoutException ignored) {
        }
    }

    public ShopPage getShopPage(){
        getShopElement().click();
        closeSignup();
        return new ShopPage(driver);
    }
}
