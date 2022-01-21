package pageObject;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ShopPage extends BasePage{
    public ShopPage(WebDriver driver){
        super(driver);
    }
    private List<WebElement> getAllItems() {
        By allAvailableItemsLocator = By.xpath("//*[@class]/form/*[@title='Add to Cart']");
//        By allItemsLocator = By.className("item");
//        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(allAvailableItemsLocator));
        return driver.findElements(allAvailableItemsLocator);
    }
    private List<Integer> getItemNumbers(List<WebElement> list) {
        int numberOfItems = list.size();
        List<Integer> selectedNumbers = new ArrayList<>();
        Random random = new Random();
        while(selectedNumbers.size() < 2) {
            int randomItem = random.nextInt((numberOfItems-1) + 1);
            if (!selectedNumbers.contains(randomItem)) {
                selectedNumbers.add(randomItem);
            }
        }
        Collections.sort(selectedNumbers);
        System.out.println(selectedNumbers);
        return selectedNumbers;
    }
    private WebElement getClose() {
        By closeLocator = By.xpath("//*[@class='minicart-offscreen-title']/*[@id='btn-minicart-close']");
        wait.until(ExpectedConditions.elementToBeClickable(closeLocator));
        return driver.findElement(closeLocator);
    }

    private WebElement addToCart() {
        By addToCartLocator = By.xpath("//*[@id='product-addtocart-button']");
        wait.until(ExpectedConditions.elementToBeClickable(addToCartLocator));
        return driver.findElement(addToCartLocator);
    }

    private WebElement getCheckout() {
        By checkoutLocator = By.className("checkout");
        wait.until(ExpectedConditions.elementToBeClickable(checkoutLocator));
        return driver.findElement(checkoutLocator);
    }

    public void addItem(WebElement element) {
        Actions actions = new Actions(driver);
        for (int i = 0;i<10; i++) {
            try {
                actions.moveToElement(element).perform();
                actions.click().build().perform();
                actions.click(addToCart()).perform();
                return;
            } catch (StaleElementReferenceException | TimeoutException ignored) {
                try {
                    return;
                } catch (StaleElementReferenceException | TimeoutException ignored2) {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException ignored3) {
                    }
                }
            }
        }
    }

    public CheckoutPage checkoutItemWithoutDetails() {
        List<Integer> numbersOfItems = getItemNumbers(getAllItems());
        WebElement item1 = getAllItems().get(numbersOfItems.get(0));
        addItem(item1);
        getClose().click();
        driver.navigate().back();
        WebElement item2 = getAllItems().get(numbersOfItems.get(1));
        addItem(item2);
        getCheckout().click();
        return new CheckoutPage(driver);
    }
    private List<WebElement> getItemsWithDetails(){
        By itemsLocator = By.xpath("//*[@title='View Details']");
        return driver.findElements(itemsLocator);
    }
    private List<WebElement> getAllDetails(){
        By allDetails = By.xpath("//select[contains(@name,'super')]");
        return driver.findElements(allDetails);
    }
    private List<WebElement> getDetail1(){
        By detail = By.xpath("(//select)[1][contains(@name,'super')]/*[@value]");
        return driver.findElements(detail);
    }
    private List<WebElement> getDetail2(){
        By detail = By.xpath("(//select)[2][contains(@name,'super')]/*[@value]");
        return driver.findElements(detail);
    }
    private List<WebElement> getDetail3(){
        By detail = By.xpath("(//select)[3][contains(@name,'super')]/*[@value]");
        return driver.findElements(detail);
    }
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

    private void selectDetailsInItem1() {
        List<WebElement> details = getAllDetails();
        while (true){
            if(details.size()==0){
                getChoice(getQtyOptions(),0,0).click();
                return;
            }if(details.size()==1) {
                getChoice(getDetail1(),1,1).click();
                getChoice(getQtyOptions(),0,0).click();
                return;
            }if(details.size()==2){
                getChoice(getDetail1(),1,1).click();
                getChoice(getDetail2(),1,1).click();
                getChoice(getQtyOptions(),0,0).click();
                return;
            }
        }
    }
    private void selectDetailsInItem2() {
        List<WebElement> details = getAllDetails();
        while (true){
            if(details.size()==0){
                getChoice(getQtyOptions(),0,0).click();
                return;
            }if(details.size()==1) {
                getChoice(getDetail2(),1,1).click();
                getChoice(getQtyOptions(),0,0).click();
                return;
            }if(details.size()==2){
                getChoice(getDetail2(),1,1).click();
                getChoice(getDetail3(),1,1).click();
                getChoice(getQtyOptions(),0,0).click();
                return;
            }
        }
    }
    public void selectItemWithDetails(WebElement element) {
        Actions actions = new Actions(driver);
        for (int i = 0; i<3; i++) {
            try {
                actions.moveToElement(element);
                actions.click(element).build().perform();
                return;
            } catch (StaleElementReferenceException | TimeoutException ignored) {
                try {
                    Thread.sleep(300);
                    } catch (InterruptedException ignored3) {
                    }
                }
            }
        }

    public CheckoutPage checkoutItemWithDetails() throws InterruptedException {
        List<Integer> numbersOfItems = getItemNumbers(getItemsWithDetails());
        WebElement item1 = getItemsWithDetails().get(numbersOfItems.get(0));
        selectItemWithDetails(item1);
        selectDetailsInItem1();
        addToCart().click();
        getClose().click();
        driver.navigate().back();
        WebElement item2 = getItemsWithDetails().get(numbersOfItems.get(1));
        selectItemWithDetails(item2);
        selectDetailsInItem2();
        addToCart().click();
        getCheckout().click();
        Thread.sleep(3000);
        return new CheckoutPage(driver);
    }
}
