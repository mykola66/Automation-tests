package pageObject;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class AllProductsPage extends BasePage {
    public AllProductsPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getItemsWithoutDetails() {
        By allAvailableItemsLocator = By.xpath("//*[@data-role='tocart-form']/parent::*/parent::*/preceding-sibling::*");
//        By allItemsLocator = By.xpath("//*[@class='product-name']");
//        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(allAvailableItemsLocator));
        return driver.findElements(allAvailableItemsLocator);
    }

    public List<Integer> getItemNumbers(List<WebElement> list) {
        int numberOfItems = list.size();
        List<Integer> selectedNumbers = new ArrayList<>();
        Random random = new Random();
        while (selectedNumbers.size() < 2) {
            int randomItem = random.nextInt((numberOfItems - 1) + 1);
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

    private void selectItem(WebElement element) {
        Actions actions = new Actions(driver);

        for (int i = 0; i < 10; i++) {
            try {
//                JavascriptExecutor js = (JavascriptExecutor) driver;
//                js.executeScript("arguments[0].scrollIntoView();", element );
                actions.moveToElement(element).perform();
                actions.click(element).perform();
//                actions.click();
//                actions.perform();
//                actions.click(addToCart()).perform();
                return;
            } catch (StaleElementReferenceException | TimeoutException ignored) {
                try {
//                    element.click();
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

    public List<Integer> getItemNumbersWithoutDetails() {
        List<Integer> numbersOfItems = getItemNumbers(getItemsWithoutDetails());
        return numbersOfItems;
    }

    public ItemPage selectItemWithoutDetails(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        selectItem(element);
        return new ItemPage(driver);
    }

    public List<WebElement> getItemsWithDetails() {
        By itemsLocator = By.xpath("//*[@title='View Details']");
        return driver.findElements(itemsLocator);
    }

    public List<Integer> getItemNumbersWithDetails() {
        List<Integer> numbersOfItems = getItemNumbers(getItemsWithDetails());
        return numbersOfItems;
    }

    public ItemPage selectItemWithDetails(WebElement element) {
        selectItem(element);
        return new ItemPage(driver);
    }


}
