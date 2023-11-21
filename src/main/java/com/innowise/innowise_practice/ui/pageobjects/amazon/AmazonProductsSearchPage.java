package com.innowise.innowise_practice.ui.pageobjects.amazon;

import com.innowise.innowise_practice.ui.pageobjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AmazonProductsSearchPage extends BasePage {
    public AmazonProductsSearchPage(WebDriver driver, WebDriverWait waiter, Actions actions) {
        super(driver, waiter, actions);
    }

    @FindBy(xpath = "//span[@class=\"a-color-state a-text-bold\"]")
    private WebElement nameOfSearchedProduct;

    @FindBy(xpath = "//div[contains(@class, 's-product-image-container')]")
    private WebElement everyProductXPath;


    public boolean isProductNamesMatch(String productName) {
        System.out.println(nameOfSearchedProduct.getText());
        return nameOfSearchedProduct.getText().contains(productName);
    }

    public AmazonProductPage openPageWithFirstProduct() {
        clickElement(everyProductXPath, this);

        return new AmazonProductPage(this.driver, this.waiter, this.actions);
    }
}
