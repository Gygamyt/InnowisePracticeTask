package com.innowise.innowise_practice.ui.pageobjects.relax_by_page_objects;

import com.innowise.innowise_practice.ui.driver.Driver;
import com.innowise.innowise_practice.ui.pageobjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RelaxHeader extends BasePage {

    public RelaxHeader(WebDriver driver, WebDriverWait waiter, Actions actions) {
        super(driver, waiter, actions);
    }

    @FindBy(id = "search_open")
    private WebElement searchBarField;

    @FindBy(xpath = "//a[contains(@href, 'lunabar')]")
    private WebElement lunabarResult;

    @FindBy(xpath = "//div[@title=\"Еда\" and text()='Еда' and contains(@class, 'body-2')]")
    private WebElement foodButton;

    @FindBy(xpath = "//a[text()='Рестораны']")
    private WebElement restaurantsButton;

    @FindBy(xpath = "//div[contains(@class, \"Gallery__list\")]//div[@title=\"Афиша, кино\"]")
    private WebElement afishaButton;


    public LunabarPageObject openLunabarPage() {
        moveToElementAndClick(searchBarField);
        actions
                .sendKeys("Luna")
                .perform();
        clickElement(lunabarResult, this);
        return new LunabarPageObject(this.driver, this.waiter, this.actions);
    }

    public RelaxHeader clickFoodButton() {
        clickElement(foodButton, this);
        return this;
    }

    public RestaurantsPage clickRestaurantButton() {
        clickElement(restaurantsButton, this);
        return new RestaurantsPage(this.driver, this.waiter, this.actions);
    }
}
