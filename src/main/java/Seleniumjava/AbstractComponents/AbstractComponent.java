package Seleniumjava.AbstractComponents;

import Seleniumjava.pageobjects.CartPage;
import Seleniumjava.pageobjects.OrderPage;
import org.openqa.selenium.*;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

public class AbstractComponent {

    WebDriver driver;

    public AbstractComponent(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[routerlink*='cart']")
    WebElement cartHeader;

    @FindBy(css = "[routerlink*='myorders']")
    WebElement orderHeader;


    public void waitForElementToAppear(By findBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

    }

        public void waitForWebElementToAppear (WebElement findBy)  {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOf(findBy));
        }

    public CartPage goToCartPage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", cartHeader);
        //cartHeader.click();
        return new CartPage(driver);
    }
    public OrderPage goToOrderPage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", orderHeader);
       //orderHeader.click();
        return new OrderPage(driver);
    }




        // public void waitForElementToDisappear(WebElement ele) throws InterruptedException{
        //Thread.sleep(1000);
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        // wait.until(ExpectedConditions.invisibilityOf(ele));
    }




