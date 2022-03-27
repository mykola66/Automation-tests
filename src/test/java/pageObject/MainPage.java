package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

    public AllProductsPage getAllProductsPage(){
        closeSignup();
        getShopElement().click();
        return new AllProductsPage(driver);
    }

    private List<WebElement> getProductsList(){
        By products = By.xpath("//*[@data-pb-style='RXRL1LA']/ul/li/a[@tabindex='0']");
        return driver.findElements(products);
    }

    private WebElement getRandomWebElement(List<WebElement> list,int x) {
        int numberOfAllProduct = list.size();
         Random random = new Random();
          int randomProduct = random.nextInt(numberOfAllProduct-x);
         System.out.println(list.get(randomProduct).getText());
        return list.get(randomProduct);
    }

    private List<WebElement> getButtonBuy(){
        By buyButton = By.xpath("//*[@class='button learn_more blue']/span[contains(text(),'B')]");
        return driver.findElements(buyButton);
    }
    public ItemPage getShopPageProduct(){
        closeSignup();
        Actions actions = new Actions(driver);
        actions.moveToElement(getShopElement()).perform();
        WebElement product = getRandomWebElement(getProductsList(),3);
        wait.until(ExpectedConditions.elementToBeClickable(product));
        actions.moveToElement(product).click().perform();
        WebElement buttonBuy = getRandomWebElement(getButtonBuy(),0);
        actions.click(buttonBuy).perform();
        return new ItemPage(driver);
    }





}
