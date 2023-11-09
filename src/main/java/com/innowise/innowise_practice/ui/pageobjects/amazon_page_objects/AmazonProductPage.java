package com.innowise.innowise_practice.ui.pageobjects.amazon_page_objects;

import com.innowise.innowise_practice.ui.driver.Driver;
import com.innowise.innowise_practice.ui.pageobjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AmazonProductPage extends BasePage {
    public AmazonProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "add-to-cart-button")
    private WebElement addToCartButton;

    public AmazonHeader addToCart() {
        clickElement(addToCartButton);
        return new AmazonHeader(this.driver);
    }

}
