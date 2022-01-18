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
        By allAvailableItemsLocator = By.xpath("//*[@title='Add to Cart']");
//        By allItemsLocator = By.className("item");
//        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(allAvailableItemsLocator));
        return driver.findElements(allAvailableItemsLocator);
    }
    private List<Integer> getItemNumbers() {
        int numberOfItems = getAllItems().size();
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
        for (int i = 0; true; i++) {
            try {
                actions.moveToElement(element);
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

    public CheckoutPage checkoutItem() {
        List<Integer> numbersOfItems = getItemNumbers();
        WebElement item1 = getAllItems().get(numbersOfItems.get(0));
        addItem(item1);
        getClose().click();
        driver.navigate().back();
        WebElement item2 = getAllItems().get(numbersOfItems.get(1));
        addItem(item2);
        getCheckout().click();
        return new CheckoutPage(driver);
    }
}
