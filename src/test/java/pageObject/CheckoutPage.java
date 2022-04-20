package pageObject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

public class CheckoutPage extends BasePage{
    public CheckoutPage(WebDriver driver){
        super(driver);
    }
    public boolean itemsAreInCheckoutPage(){
        By itemsLocator = By.xpath("//*[@class='product-item']");
        try {
            List<WebElement> items = driver.findElements(itemsLocator);
//            wait.until(ExpectedConditions.visibilityOfAllElements(items));
            for (WebElement item : items) {
                System.out.println(item.getText());
                item.isEnabled();
            }
            return true;
        }catch (NoSuchElementException err){
            return false;
        }
    }

    private WebElement getEmailField(){
        By emailFieldLocator = By.xpath("//*[@class='control _with-tooltip']/*[@name='username']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailFieldLocator));
        return driver.findElement(emailFieldLocator);
    }
    private WebElement getFirstNameField(){
        By firstNameField = By.xpath("//*[@name='firstname']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField));
        return driver.findElement(firstNameField);
    }
    private WebElement getLastNameField(){
        By lastNameFieldLocator = By.xpath("//*[@name='lastname']");
        return driver.findElement(lastNameFieldLocator);
    }
    private WebElement getStreetAddressField(){
        By streetAddressFieldLocator = By.xpath("//*[@name='street[0]']");
        return driver.findElement(streetAddressFieldLocator);
    }
    private WebElement getCityField(){
        By cityFieldLocator = By.xpath("//*[@name='city']");
        return driver.findElement(cityFieldLocator);
    }
    private WebElement getZipCodeField(){
        By zipCodeFieldLocator = By.xpath("//*[@name='postcode']");
        return driver.findElement(zipCodeFieldLocator);
    }
    private WebElement getPhoneNumberField(){
        By phoneNumberLocator = By.xpath("//*[@name='telephone']");
        return driver.findElement(phoneNumberLocator);
    }

    private WebElement getShippingMethod(){
        By shippingMethodLocator = By.xpath("(//*[@type='radio'])[2]");
        return driver.findElement(shippingMethodLocator);
    }
    private WebElement getCardNumberField(){
        By cardFieldLocator = By.xpath("//*[@class='InputElement is-empty Input Input--empty']");
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.tagName("iframe")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(cardFieldLocator));
        return driver.findElement(cardFieldLocator);
    }

    private WebElement getDateField(){
        By dateLocator = By.xpath("//*[@autocomplete='cc-exp']");
        return driver.findElement(dateLocator);
    }
    private WebElement getCVCField(){
        By cvcLocator = By.xpath("//*[@autocomplete='cc-csc']");
        return driver.findElement(cvcLocator);
    }
    private WebElement getPlaceOrderButton(){
        By placeOrderButtonLocator = By.xpath("//div[@class='actions-toolbar']/*[@class='action primary checkout amasty']");

        wait.until(ExpectedConditions.elementToBeClickable(placeOrderButtonLocator));
        return driver.findElement(placeOrderButtonLocator);
    }
//    private void clickPlaceOrderButton(){
//        try{
//            driver.switchTo().defaultContent();
//            Thread.sleep(2000);
//            getPlaceOrderButton().click();
//            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='OK']")));
//            driver.findElement(By.xpath("//*[text()='OK']")).click();
//            getPlaceOrderButton().click();
//        }catch (ElementNotInteractableException | NoSuchElementException | TimeoutException | InterruptedException ignored){
//        }
//    }

    public void placeOrder() throws InterruptedException {
        getEmailField().sendKeys("smorris@gmail.com");
        getFirstNameField().sendKeys("Smith");
        getLastNameField().sendKeys("Morris");
        getStreetAddressField().sendKeys("15 Broad St Apt 1402");
        getCityField().sendKeys("New York");
        Select drpRegion = new Select(driver.findElement(By.name("region_id")));
        drpRegion.selectByVisibleText("New York");
        getZipCodeField().sendKeys("10005-1973");
        getPhoneNumberField().sendKeys("8571258975");
        getShippingMethod().click();
        getCardNumberField().sendKeys("4242424242424242");
        getDateField().sendKeys("1125");
        getCVCField().sendKeys("676");
        driver.switchTo().defaultContent();
        Thread.sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", getPlaceOrderButton());
    }

    public boolean isOpen() {
        try{
            getPlaceOrderButton().isDisplayed();
            return true;
        }catch (TimeoutException err){
            return false;
        }
    }

    public boolean errorMessageIsExist() {
        By errorMessageLocator = By.xpath("//*[@data-ui-id='checkout-cart-validationmessages-message-error']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageLocator));
        try{
            driver.findElement(errorMessageLocator).isDisplayed();
            return true;
        }catch (TimeoutException err){
            return false;
        }
    }
    public List<WebElement> getItems(){
        By itemLocator = By.xpath("//*[@class='product-item']");
        return driver.findElements(itemLocator);
    }
    private int getItemNumberForRemove() {
        List<WebElement> list = getItems();
        Random random = new Random();
        int randomItemNumber = random.nextInt(list.size())+1;
        return randomItemNumber;
    }
    private WebElement getDelete(int x){
        By removeLocator = By.xpath("(//*[@class='delete'])["+x+"]");
        wait.until(ExpectedConditions.elementToBeClickable(removeLocator));
        return driver.findElement(removeLocator);
    }
    private WebElement getItemForDelete(int x){
        By itemLocator = By.xpath("(//*[@class='product-item-name-block'])["+x+"]");
        return driver.findElement(itemLocator);
    }
    private WebElement getAccept(){
        By acceptLocator = By.xpath("//*[@class='action-primary action-accept']");
        wait.until(ExpectedConditions.elementToBeClickable(acceptLocator));
        return driver.findElement(acceptLocator);
    }
    public String deleteItem(){
        int numberItem = getItemNumberForRemove();
        WebElement removeItem = getItemForDelete(numberItem);
        String item = removeItem.getText();
        System.out.println(removeItem.getText());
        getDelete(numberItem).click();
        getAccept().click();
        return item;
    }
    public boolean itemIsDeleted(String deletedItem) {
        By itemsLocator = By.xpath("//*[text()='"+deletedItem+"']");
        try {
            driver.findElement(itemsLocator);
            return false;
        }catch (NoSuchElementException err){
            return true;
        }
    }
}
