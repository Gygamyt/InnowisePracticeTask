package com.innowise.innowise_practice.ui.pageobjects.relax_by_page_objects;

import com.innowise.innowise_practice.ui.driver.Driver;
import com.innowise.innowise_practice.ui.pageobjects.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class RestaurantsPage extends BasePage {

    public RestaurantsPage(WebDriver driver, WebDriverWait waiter, Actions actions) {
        super(driver, waiter, actions);
    }

    @FindBy(xpath = "//span[text()='Фильтры']")
    private WebElement filtersButton;

    @FindBy(xpath = "//div[text()='Район']")
    private WebElement hoverDistrictButton;

    @FindBy(xpath = "//span[text()='Заводской']")
    private WebElement requiredDistrict;

    @FindBy(xpath = "//div[contains(@class, 'hasChecked')]")
    private WebElement selectedElements;

    private By selectedElementsBy = By.xpath("//div[contains(@class, 'hasChecked')]");

    @FindBy(xpath = "//div[@class=\"FilterSidebar__item\"]//span[text()='Еда навынос']")
    private WebElement takeAwayFoodSlider;

    @FindBy(xpath = "//div[@class=\"CompositeButtons__toggle\"]")
    private WebElement kitchenCompositeButton;

    @FindBy(xpath = "//span[text()='Белорусская']")
    private WebElement belKitchen;

    @FindBy(xpath = "//div[text()='Меню навынос']/following-sibling::*//span[text()='Да']")
    private WebElement chooseTakeAwayVariant;

    public RestaurantsPage openFilters() {
        clickElement(filtersButton, this);
        return this;
    }

    public RestaurantsPage applyDistrictFilter() {
        waitUntilElementVisible(hoverDistrictButton);
        actions
                .moveToElement(hoverDistrictButton)
                .click()
                .perform();
        waitUntilElementVisible(requiredDistrict);
        actions
                .moveToElement(requiredDistrict)
                .click()
                .perform();
        return this;
    }

    public RestaurantsPage applyTakeAwayFilter() {
        actions
                .moveToElement(takeAwayFoodSlider)
                .click()
                .perform();
        return this;
    }

    public RestaurantsPage applyKitchenVariant() {
        actions
                .moveToElement(kitchenCompositeButton)
                .click()
                .perform();
        waitUntilElementVisible(belKitchen);
        actions
                .moveToElement(belKitchen)
                .click()
                .perform();
        return this;
    }

    public RestaurantsPage applyTakeAwayVariant() {
        actions
                .scrollToElement(chooseTakeAwayVariant)
                .perform();
        waitUntilElementVisible(chooseTakeAwayVariant);
        actions
                .moveToElement(chooseTakeAwayVariant)
                .click()
                .perform();
        return this;
    }

    public List<WebElement> takeAllSelectedElements() {
        return getElementsWithBy(selectedElementsBy);
    }
}
