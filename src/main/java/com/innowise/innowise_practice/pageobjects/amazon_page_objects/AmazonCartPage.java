package com.innowise.innowise_practice.pageobjects.amazon_page_objects;

import com.innowise.innowise_practice.pageobjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AmazonCartPage extends BasePage {
    public AmazonCartPage(WebDriver driver) {
        super(driver);
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
