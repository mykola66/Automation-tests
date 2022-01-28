package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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

    private List<WebElement> getProductsList(){
        By products = By.xpath("//*[@data-pb-style='PFKIO78']/ul/li/a[@tabindex='0']");
        return driver.findElements(products);
    }

    private WebElement getRandomProduct(List<WebElement> list) {
        int numberOfAllProduct = list.size();
         Random random = new Random();
          int randomProduct = random.nextInt(numberOfAllProduct-7);
         System.out.println(list.get(randomProduct).getText());
        return list.get(randomProduct);
    }
    private WebElement buttonBuy(){
        By buyNowButton = By.xpath("//*[text()='BUY NOW']");
        wait.until(ExpectedConditions.elementToBeClickable(buyNowButton));
        return driver.findElement(buyNowButton);
    }
    public ShopPage getShopPageProduct() throws InterruptedException {
        closeSignup();
        Actions actions = new Actions(driver);
        actions.moveToElement(getShopElement()).perform();
        WebElement product = getRandomProduct(getProductsList());
        wait.until(ExpectedConditions.elementToBeClickable(product));
        actions.moveToElement(product).click().perform();
//        Thread.sleep(2000);
        buttonBuy().click();
        return new ShopPage(driver);
    }

}
