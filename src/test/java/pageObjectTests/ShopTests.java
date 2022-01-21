package pageObjectTests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.CheckoutPage;
import pageObject.MainPage;
import pageObject.ShopPage;

public class ShopTests extends BaseTests{
    @Test
    public void shopTests_selectItemsWithoutDetails_itemsAreSelected(){
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage("https://www.wahoofitness.com/");
        ShopPage shopPage = mainPage.getShopPage();
        CheckoutPage checkoutPage = shopPage.checkoutItemWithoutDetails();
        Assert.assertTrue(checkoutPage.itemsAreInCheckoutPage());
    }
    @Test
    public void shopTests_placeOrder_orderIsPlaced() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage("https://www.wahoofitness.com/");
        ShopPage shopPage = mainPage.getShopPage();
        CheckoutPage checkoutPage = shopPage.checkoutItemWithoutDetails();
        checkoutPage.placeOrder();
        Assert.assertTrue(checkoutPage.errorMessageIsExist());
    }
    @Test
    public void shopTests_selectItemsWithDetails_itemsAreSelected() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage("https://www.wahoofitness.com/");
        ShopPage shopPage = mainPage.getShopPage();
        CheckoutPage checkoutPage = shopPage.checkoutItemWithDetails();
        Assert.assertTrue(checkoutPage.itemsAreInCheckoutPage());
    }
}
