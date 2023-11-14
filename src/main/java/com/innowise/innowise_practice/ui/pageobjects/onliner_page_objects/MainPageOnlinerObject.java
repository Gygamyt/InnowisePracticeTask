package com.innowise.innowise_practice.ui.pageobjects.onliner_page_objects;

import com.innowise.innowise_practice.ui.pageobjects.BasePage;
import com.innowise.innowise_practice.ui.driver.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPageOnlinerObject extends BasePage {

    public MainPageOnlinerObject(WebDriver driver, WebDriverWait waiter, Actions actions) {
        super(driver, waiter, actions);
    }

    @FindBy(xpath = "//input[@class=\"fast-search__input\"]")
    private WebElement searchFieldInput;

    @FindBy(xpath = "//iframe[@class=\"modal-iframe\"]")
    private WebElement iFrame;

    @FindBy(xpath = "//div[contains(@class, result__item_product)]//div[@class=\"product__title\"]")
    private WebElement resultOfSearch;



    public MainPageOnlinerObject clickOnSearchFieldAndEnterText(String searchText) {
        actions
                .moveToElement(searchFieldInput)
                .click()
                .perform();
        searchFieldInput.sendKeys(searchText);
        return this;
    }

    public ResultProductPage openSearchResult() {
        Driver.getDriver().switchTo().frame(iFrame);
        clickElement(resultOfSearch, this);
        return new ResultProductPage(this.driver, this.waiter, this.actions);
    }
}
