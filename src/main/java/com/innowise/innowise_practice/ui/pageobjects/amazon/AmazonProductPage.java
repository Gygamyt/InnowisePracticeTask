package com.innowise.innowise_practice.ui.pageobjects.amazon;

import com.innowise.innowise_practice.ui.pageobjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AmazonProductPage extends BasePage {
    public AmazonProductPage(WebDriver driver, WebDriverWait waiter, Actions actions) {
        super(driver, waiter, actions);
    }

    @FindBy(id = "add-to-cart-button")
    private WebElement addToCartButton;

    public AmazonHeader addToCart() {
        clickElement(addToCartButton, this);
        return new AmazonHeader(this.driver, this.waiter, this.actions);
    }

}
