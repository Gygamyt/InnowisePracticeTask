package com.innowise.innowise_practice.ui.pageobjects.amazon_page_objects;

import com.innowise.innowise_practice.ui.pageobjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AmazonCartPage extends BasePage {
    public AmazonCartPage(WebDriver driver, WebDriverWait waiter, Actions actions) {
        super(driver, waiter, actions);
    }

    @FindBy(xpath = "//div[@class=\"a-row\"]//h1")
    private WebElement elementForCartCheck;

    @FindBy(xpath = "//div[@data-name=\"Active Items\"]")
    private WebElement addedProductElement;

    public boolean isCartOpened() {
        return elementForCartCheck.getText().contains("Shopping Cart");
    }

    public boolean isProductVisible() {
        return addedProductElement.isDisplayed();
    }
}
