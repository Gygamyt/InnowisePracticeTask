package com.innowise.innowise_practice.pageobjects.amazon;

import com.innowise.innowise_practice.driver.Driver;
import com.innowise.innowise_practice.pageobjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AmazonMainPage extends BasePage {
    public AmazonMainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "nav-link-accountList-nav-line-1")
    private WebElement helloText;

    @FindBy(xpath = "//div[@class=\"nav-search-field \"]")
    private WebElement searchBar;

    @FindBy(id = "nav-search-submit-button")
    private WebElement submitSearchButton;

    public boolean isHelloTextRight(String name) {
        return helloText.getText().equals("Hello, " + name);
    }

    public AmazonProductsSearchPage searchProduct(String productName) {
        actions
                .moveToElement(searchBar)
                .click()
                .sendKeys(productName)
                .perform();
        submitSearchButton.click();
        return new AmazonProductsSearchPage(Driver.getDriver()); //cheknyt' kak ono rabotaet
    }
}
