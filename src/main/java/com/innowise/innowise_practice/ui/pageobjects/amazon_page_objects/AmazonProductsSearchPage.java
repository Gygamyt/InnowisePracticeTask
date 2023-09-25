package com.innowise.innowise_practice.ui.pageobjects.amazon_page_objects;

import com.innowise.innowise_practice.ui.driver.Driver;
import com.innowise.innowise_practice.ui.pageobjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AmazonProductsSearchPage extends BasePage {
    public AmazonProductsSearchPage(WebDriver driver) {
        super(driver);
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
        clickElement(everyProductXPath);
        return new AmazonProductPage(Driver.getDriver());
    }
}
