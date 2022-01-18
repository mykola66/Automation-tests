package pageObjectTests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.CheckoutPage;
import pageObject.MainPage;
import pageObject.ShopPage;

public class ShopTests extends BaseTests{
    @Test
    public void shopTests_selectItems_itemsAreSelected(){
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage("https://www.wahoofitness.com/");
        ShopPage shopPage = mainPage.getShopPage();
        CheckoutPage checkoutPage = shopPage.checkoutItem();
        Assert.assertTrue(checkoutPage.itemsAreInCheckoutPage());
    }
    @Test
    public void shopTests_placeOrder_orderIsPlaced() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage("https://www.wahoofitness.com/");
        ShopPage shopPage = mainPage.getShopPage();
        CheckoutPage checkoutPage = shopPage.checkoutItem();
        checkoutPage.placeOrder();
        Assert.assertTrue(checkoutPage.errorMessageIsExist());
    }
}
