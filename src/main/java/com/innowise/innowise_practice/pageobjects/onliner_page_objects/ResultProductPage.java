package com.innowise.innowise_practice.pageobjects.onliner_page_objects;

import com.innowise.innowise_practice.pageobjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ResultProductPage extends BasePage {
    public ResultProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h1[contains(@class, \"catalog-masthead__title\")]")
    private WebElement productResultTittle;

    public String getProductResultTittleText() {
        return getElementText(productResultTittle);
    }
}
