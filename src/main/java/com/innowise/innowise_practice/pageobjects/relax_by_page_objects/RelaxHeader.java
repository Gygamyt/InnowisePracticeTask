package com.innowise.innowise_practice.pageobjects.relax_by_page_objects;

import com.innowise.innowise_practice.driver.Driver;
import com.innowise.innowise_practice.pageobjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RelaxHeader extends BasePage {
    public RelaxHeader(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "search_open")
    private WebElement searchBarField;

    @FindBy(xpath = "//a[contains(@href, 'lunabar')]")
    private WebElement lunabarResult;

    @FindBy(xpath = "//div[@title=\"Еда\" and text()='Еда' and contains(@class, 'body-2')]")
    private WebElement foodButton;

    @FindBy(xpath = "//a[text()='Рестораны']")
    private WebElement restaurantsButton;

    public LunabarPageObject openLunabarPage() {
        moveToElementAndClick(searchBarField);
        actions
                .sendKeys("Luna")
                .perform();
        clickElement(lunabarResult);
        return new LunabarPageObject(Driver.getDriver());
    }

    public RelaxHeader clickFoodButton() {
        clickElement(foodButton);
        return this;
    }

    public RestaurantsPage clickRestaurantButton() {
        clickElement(restaurantsButton);
        return new RestaurantsPage(Driver.getDriver());
    }
}
