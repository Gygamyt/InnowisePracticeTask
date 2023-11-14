package com.innowise.innowise_practice.ui.pageobjects.amazon_page_objects;

import com.innowise.innowise_practice.ui.driver.Driver;
import com.innowise.innowise_practice.ui.pageobjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AmazonMainPage extends BasePage {

    public AmazonMainPage(WebDriver driver, WebDriverWait waiter, Actions actions) {
        super(driver, waiter, actions);
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
        return new AmazonProductsSearchPage(this.driver, this.waiter, this.actions);
    }
}
