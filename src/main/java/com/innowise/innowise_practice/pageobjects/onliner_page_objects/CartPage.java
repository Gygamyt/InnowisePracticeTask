package com.innowise.innowise_practice.pageobjects.onliner_page_objects;

import com.innowise.innowise_practice.pageobjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[contains(@class, 'cart-form__offers-part')]//a[contains(@class, 'cart-form__link')]")
    private WebElement productInCartElement;

    @FindBy(xpath = "//a[contains(@class, 'cart-form__button_remove')]")
    private WebElement deleteButton;

    @FindBy(xpath = "//a[text()='Закрыть']")
    private WebElement closeButton;

    @FindBy(xpath = "//div[contains(@class, 'cart-message__title')]")
    private WebElement placeWithResultText;

    public String getProductName() {
        return getElementText(productInCartElement);
    }

    public CartPage clickOnDeleteButton() {
        actions
                .moveToElement(deleteButton)
                .click()
                .perform();
        return this;
    }

    public CartPage clickOnCloseButton() {
        clickElement(closeButton);
        return this;
    }

    public String getResultText() {
        return getElementText(placeWithResultText);
    }
}
