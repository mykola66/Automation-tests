package pageObjectTests;

import listeners.RetryAnalyzer;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.*;

import java.util.List;

public class ShopTests extends BaseTests{
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void shopTests_selectItemsWithoutDetails_itemsAreSelected(){
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage(url);
        AllProductsPage allProductsPage = mainPage.getAllProductsPage();
        List<Integer> items = allProductsPage.getItemNumbersWithoutDetails();
        WebElement item1 = allProductsPage.getItemsWithoutDetails().get(items.get(0));
        ItemPage itemPage1 = allProductsPage.selectItemWithoutDetails(item1);
        itemPage1.addItemToCart();
        itemPage1.backToAllProducts();
        WebElement item2 = allProductsPage.getItemsWithoutDetails().get(items.get(1));
        ItemPage itemPage2 = allProductsPage.selectItemWithoutDetails(item2);
        itemPage2.addItemToCart();
        CheckoutPage checkoutPage = itemPage2.moveToCheckout();
        Assert.assertTrue(checkoutPage.itemsAreInCheckoutPage());
    }
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void shopTests_placeOrder_orderIsPlaced() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage(url);
        AllProductsPage allProductsPage = mainPage.getAllProductsPage();
        List<Integer> items = allProductsPage.getItemNumbersWithoutDetails();
        WebElement item1 = allProductsPage.getItemsWithoutDetails().get(items.get(0));
        ItemPage itemPage1 = allProductsPage.selectItemWithoutDetails(item1);
        itemPage1.addItemToCart();
        itemPage1.backToAllProducts();
        WebElement item2 = allProductsPage.getItemsWithoutDetails().get(items.get(1));
        ItemPage itemPage2 = allProductsPage.selectItemWithoutDetails(item2);
        itemPage2.addItemToCart();
        CheckoutPage checkoutPage = itemPage2.moveToCheckout();
        checkoutPage.placeOrder();
        Assert.assertTrue(checkoutPage.errorMessageIsExist());
    }
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void shopTests_selectItemsWithDetails_itemsAreSelected(){
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage(url);
        AllProductsPage allProductsPage = mainPage.getAllProductsPage();
        List<Integer> items = allProductsPage.getItemNumbersWithDetails();
        WebElement item1 = allProductsPage.getItemsWithDetails().get(items.get(0));
        ItemPage itemPage1 = allProductsPage.selectItemWithDetails(item1);
        itemPage1.addItemToCart();
        itemPage1.backToAllProducts();
        WebElement item2 = allProductsPage.getItemsWithDetails().get(items.get(1));
        ItemPage itemPage2 = allProductsPage.selectItemWithDetails(item2);
        itemPage2.addItemToCart2();
        CheckoutPage checkoutPage = itemPage2.moveToCheckout();
        Assert.assertTrue(checkoutPage.itemsAreInCheckoutPage());
    }
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void shopTest_selectItemFromMainPage_itemIsSelected(){
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage(url);
        ItemPage itemPage = mainPage.getShopPageProduct();
        itemPage.addItemToCart();
        CheckoutPage checkoutPage = itemPage.moveToCheckout();
        Assert.assertTrue(checkoutPage.itemsAreInCheckoutPage());

    }
    @Test
    public void shopTest_removeItemFromCart_itemIsRemoved(){
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage(url);
        AllProductsPage allProductsPage = mainPage.getAllProductsPage();
        List<Integer> items = allProductsPage.getItemNumbersWithoutDetails();
        WebElement item1 = allProductsPage.getItemsWithoutDetails().get(items.get(0));
        ItemPage itemPage1 = allProductsPage.selectItemWithoutDetails(item1);
        itemPage1.addItemToCart();
        itemPage1.backToAllProducts();
        WebElement item2 = allProductsPage.getItemsWithoutDetails().get(items.get(1));
        ItemPage itemPage2 = allProductsPage.selectItemWithoutDetails(item2);
        itemPage2.addItemToCart();
        CartPage cartPage = itemPage2.moveToCart();
        String itemForRemove = cartPage.removeItem();
        Assert.assertTrue((cartPage.itemIsRemoved(itemForRemove)));
    }
}
