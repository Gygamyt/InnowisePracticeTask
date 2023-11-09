package com.innowise.innowise_practice.ui.pageobjects.amazon_page_objects;

import com.innowise.innowise_practice.ui.driver.Driver;
import com.innowise.innowise_practice.ui.pageobjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AmazonHeader extends BasePage {
    public AmazonHeader(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "nav-link-accountList")
    private WebElement AccountAndListButton;

    @FindBy(xpath = "//div[@id=\"nav-al-signin\"]//span[contains(text(), \"Sign in\")]")
    private WebElement signInButton;

    @FindBy(id = "nav-cart-count")
    private WebElement quantityOfItemsInCart;

    @FindBy(id = "nav-cart")
    private WebElement cartButton;

    @FindBy(id = "NATC_SMART_WAGON_CONF_MSG_SUCCESS")
    private WebElement successAddingToCartInformationElement;

    @FindBy(xpath = "//div[@id=\"NATC_SMART_WAGON_CONF_MSG_SUCCESS\"]//i")
    private WebElement greenIconElement;

    public AmazonLoginPage hoverAccountsAndListsAndClickLogin() {
        actions
                .moveToElement(AccountAndListButton)
                .perform();
        waiter.until(ExpectedConditions.visibilityOf(signInButton));
        clickElement(signInButton);
        return new AmazonLoginPage(this.driver);
    }

    public boolean isQuantityOfItemsIsOne() {
        return quantityOfItemsInCart.getText().equals("1");
    }

    public AmazonCartPage openCart() {
        actions
                .moveToElement(cartButton)
                .click()
                .perform();
        return new AmazonCartPage(this.driver);
    }

    public boolean isAddedToCartTextAppears() {
        return successAddingToCartInformationElement.getText().contains("Added to Cart");
    }

    public boolean isGreenIconAppears() {
        return greenIconElement.isDisplayed();
    }
}
