package com.innowise.innowise_practice.pageobjects.onliner_page_objects;

import com.innowise.innowise_practice.pageobjects.BasePage;
import com.innowise.innowise_practice.driver.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPageOnlinerObject extends BasePage {
    public MainPageOnlinerObject(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@class=\"fast-search__input\"]")
    private WebElement searchFieldInput;

    @FindBy(xpath = "//iframe[@class=\"modal-iframe\"]")
    private WebElement iFrame;

    @FindBy(xpath = "//div[contains(@class, result__item_product)]//div[@class=\"product__title\"]")
    private WebElement resultOfSearch;


    public MainPageOnlinerObject clickOnSearchFieldAndEnterText(String searchText) {
        clickElement(searchFieldInput);
        actions
                .sendKeys(searchText)
                .perform();
        return this;
    }

    public ResultProductPage openSearchResult() {
        Driver.getDriver().switchTo().frame(iFrame);
        clickElement(resultOfSearch);
        return new ResultProductPage(Driver.getDriver());
    }
}