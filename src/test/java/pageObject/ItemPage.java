package pageObject;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Random;

public class ItemPage extends BasePage{
    public ItemPage(WebDriver driver){
        super(driver);
    }
    private List<WebElement> getAllDetails(){
        By allDetails = By.xpath("//select[contains(@name,'super')]");
        return driver.findElements(allDetails);
    }
//    private WebElement getDetailButton1(){
//        By detailButton1 = By.xpath("(//select)[1][contains(@name,'super')]");
//        return driver.findElement(detailButton1);
//    }
    private List<WebElement> getDetail1(){
        By detail = By.xpath("(//select)[1][contains(@name,'super')]/*[@value]");
        return driver.findElements(detail);
    }
//    private WebElement getDetailButton2(){
//        By detailButton1 = By.xpath("(//select)[2][contains(@name,'super')]");
//        return driver.findElement(detailButton1);
//    }
    private List<WebElement> getDetail2(){
        By detail = By.xpath("(//select)[2][contains(@name,'super')]/*[@value]");
        return driver.findElements(detail);
    }
//    private WebElement getDetailButton3(){
//        By detailButton1 = By.xpath("(//select)[3][contains(@name,'super')]");
//        return driver.findElement(detailButton1);
//    }

    private List<WebElement> getQtyOptions(){
        By qtyOptionsLocator = By.xpath("//select[@name='qty']/option[@value<5]");
        return driver.findElements(qtyOptionsLocator);
    }
    private WebElement getChoice(List<WebElement> options,int x,int y){
        Random random = new Random();
        int maxOptions = options.size();
        int randomOption = random.nextInt(maxOptions-y)+x;
        return options.get(randomOption);
    }
    private WebElement addToCart() {
        By addToCartLocator = By.xpath("//*[@id='product-addtocart-button']");
        wait.until(ExpectedConditions.elementToBeClickable(addToCartLocator));
        return driver.findElement(addToCartLocator);
    }

    private void selectDetailsInItem(List<WebElement> list1,int y,int x ) {
//        Actions actions = new Actions(driver);
        List<WebElement> details = getAllDetails();
//        if(addToCart().isEnabled()) {
//        while (true){
            if (details.isEmpty()) {
                getChoice(getQtyOptions(), 0, 0).click();
                return;
            }
            if (details.size() == 1) {
                driver.findElement(By.xpath("(//select)["+y+"][contains(@name,'super')]")).click();
                getChoice(list1, 1, 1).click();
                getChoice(getQtyOptions(), 0, 0).click();
                return;
            }
            if (details.size() == 2) {
                driver.findElement(By.xpath("(//select)["+y+"][contains(@name,'super')]")).click();
                getChoice(list1, 1, 1).click();
                List<WebElement> list2 = driver.findElements(By.xpath("(//select)["+x+"][contains(@name,'super')]/*[@value]"));
                driver.findElement(By.xpath("(//select)["+x+"][contains(@name,'super')]")).click();
                getChoice(list2, 1, 1).click();
                getChoice(getQtyOptions(), 0, 0).click();

            }
//        }
    }

    private WebElement getCheckout() {
        By checkoutLocator = By.className("checkout");
        wait.until(ExpectedConditions.elementToBeClickable(checkoutLocator));
        return driver.findElement(checkoutLocator);
    }
    public void addItemToCart() {
        selectDetailsInItem(getDetail1(),1,2);
        addToCart().click();
    }
    public void addItemToCart2() {
        selectDetailsInItem(getDetail2(),2,3);
        addToCart().click();
    }

    private WebElement getClose() {
        By closeLocator = By.xpath("//*[@class='minicart-offscreen-title']/*[@id='btn-minicart-close']");
        wait.until(ExpectedConditions.elementToBeClickable(closeLocator));
        return driver.findElement(closeLocator);
    }

    public void backToAllProducts(){
        getClose().click();
        driver.navigate().back();
    }

    public CheckoutPage moveToCheckout(){
        getCheckout().click();
        return new CheckoutPage(driver);
    }
    private WebElement getViewCart(){
        By viewCartLocator = By.xpath("//*[@class='action viewcart']");
        wait.until(ExpectedConditions.elementToBeClickable(viewCartLocator));
        return driver.findElement(viewCartLocator);
    }
    public CartPage moveToCart(){
        getViewCart().click();
        return new CartPage(driver);
    }
}
