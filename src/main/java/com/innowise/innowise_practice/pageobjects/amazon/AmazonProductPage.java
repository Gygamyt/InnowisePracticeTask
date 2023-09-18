package com.innowise.innowise_practice.pageobjects.amazon;

import com.innowise.innowise_practice.driver.Driver;
import com.innowise.innowise_practice.pageobjects.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AmazonProductPage extends BasePage {
    public AmazonProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "add-to-cart-button")
    private WebElement addToCartButton;

    @FindBy(id = "NATC_SMART_WAGON_CONF_MSG_SUCCESS")
    private WebElement successAddingToCartInformationElement;

    @FindBy(xpath = "//div[@id=\"NATC_SMART_WAGON_CONF_MSG_SUCCESS\"]//i")
    private WebElement greenIconElement;

    public AmazonHeader addToCart() {
        clickElement(addToCartButton);
        return new AmazonHeader(Driver.getDriver());
    }

    public boolean isAddedToCartTextAppears() {
        return successAddingToCartInformationElement.getText().contains("Added to Cart");
    }

    public boolean isGreenIconAppears() {
        return greenIconElement.isDisplayed();
    }
}
