package tests;


import Seleniumjava.pageobjects.*;
import TestComponents.BaseTest;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;


public class SubmitOrderTest extends BaseTest {
    String productName = "ZARA COAT 3";

    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void submitOrder(HashMap<String, String> input) {

        ProductCatalogue productCatalogue = landingPage.loginapplication(input.get("email"), input.get("password"));
        productCatalogue.getProductList();
        productCatalogue.addProductToCart(input.get("product"));
        CartPage cartPage = productCatalogue.goToCartPage();

        boolean match = cartPage.VerifyProductDisplay(productName);
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckOut();
        checkoutPage.selectCountry("india");
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();
        String confirmMessage = confirmationPage.getConfirmMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

    }

    @Test(dependsOnMethods = {"submitOrder"})
    public void OrderHistoryTest() {
        //"ZARA COAT 3"
        ProductCatalogue productCatalogue = landingPage.loginapplication("lakshmikh105@gmail.com", "Laxmi1995*");
        OrderPage orderPage = productCatalogue.goToOrderPage();
        Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
    }



    @DataProvider
    public Object[][] getData() throws IOException {

       List<HashMap<String,String>> data=getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//Data//PurchaseOrder.json");
        return new Object[][] {{data.get(0)},{data.get(1)}};
    }
    }

/*   HashMap<String,String> map=new HashMap<String,String>();
        map.put("email","lakshmikh105@gmail.com");
        map.put("password","Laxmi1995*");
        map.put("product","ZARA COAT 3");

        HashMap<String,String> map1=new HashMap<String,String>();
        map1.put("email","lakshmihalagatti2@gmail.com");
        map1.put("password","Laxmi1995*");
        map1.put("product","ADIDAS ORIGINAL");*/



