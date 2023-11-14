package com.innowise.innowise_practice.ui.pageobjects.onliner_page_objects;

import com.innowise.innowise_practice.ui.pageobjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver, WebDriverWait waiter, Actions actions) {
        super(driver, waiter, actions);
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
        clickElement(closeButton, this);
        return this;
    }

    public String getResultText() {
        return getElementText(placeWithResultText);
    }
}
