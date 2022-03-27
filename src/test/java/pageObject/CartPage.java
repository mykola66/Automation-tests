package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class CartPage extends BasePage{

    public CartPage(WebDriver driver){
        super(driver);
    }
    public List<WebElement> getItems(){
        By itemLocator = By.xpath("//*[@class='cart item']");
        return driver.findElements(itemLocator);
    }
    private int getItemNumberForRemove() {
        List<WebElement> list = getItems();
        Random random = new Random();
        int randomItem = random.nextInt(list.size())+1;
        return randomItem;
    }

    private WebElement getItemForRemove(int x){
        By itemLocator = By.xpath("(//td/div/*[@class='product-item-name'])["+x+"]");
        return driver.findElement(itemLocator);
    }

    private WebElement getRemove(int x){
        By removeLocator = By.xpath("(//*[@title='remove'])["+x+"]");
        return driver.findElement(removeLocator);
    }
    public String removeItem(){
        int numberItem = getItemNumberForRemove();
        WebElement removeItem = getItemForRemove(numberItem);
        String item = removeItem.getText();
        System.out.println(removeItem.getText());
        getRemove(numberItem).click();
        return item;
    }

    public boolean itemIsRemoved(String removedItem) {
        By itemsLocator = By.xpath("//td/*[@title='"+removedItem+"']");
        try {
            driver.findElement(itemsLocator);
            return false;
        }catch (NoSuchElementException err){
            return true;
        }
    }
}
