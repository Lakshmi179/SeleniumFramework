package tests;

import Seleniumjava.pageobjects.CartPage;
import Seleniumjava.pageobjects.ProductCatalogue;
import TestComponents.BaseTest;
import TestComponents.Retry;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.util.List;

public class ErrorValidations extends BaseTest {
    @Test(groups = {"ErrorHandling"},retryAnalyzer= Retry.class)
    public void LoginErrorValidations() {
        String productName = "ZARA COAT 3";
        landingPage.loginapplication("lakshmikh105@gmail.com", "Laxmi995*");
        Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

    }

    @Test
    public void ProductErrorValidations()  {
        String productName = "ZARA COAT 3";
        ProductCatalogue productCatalogue = landingPage.loginapplication("lakshmikh105@gmail.com", "Laxmi1995*");
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
        CartPage cartPage = productCatalogue.goToCartPage();
        Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 3");
        Assert.assertTrue(match);
    }
}

