package com.innowise.innowise_practice.pageobjects;

import com.innowise.innowise_practice.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GooglePageObject extends BasePage {

    public GooglePageObject(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "i12")
    private WebElement leftEnglishButton;

    @FindBy(xpath = "//button[@aria-label=\"More target languages\"]")
    private WebElement expandAllLanguagesButtonRight;

    @FindBy(xpath = "//div[@data-irrelevant-id=\"ucj-240\"]")
    private WebElement russianLanguageRightButtonWhenExpanded;

    @FindBy(xpath = "//span[@lang=\"en\"]")
    private WebElement fieldForEnglishText;
                                                                    //можно как-то унифицировать эти два xpath'a
    @FindBy(xpath = "//span[@lang=\"ru\"]")
    private WebElement resultOfTranslateField;

    public boolean isLeftEnglishButtonVisibleCheck() {
        return isElementVisible(leftEnglishButton);
    }

    public boolean isExpandAllLanguagesButtonRightVisible() {
        return isElementVisible(expandAllLanguagesButtonRight);
    }

    public boolean isRussianLanguageRightButtonWhenExpandedVisible() {
        return isElementVisible(russianLanguageRightButtonWhenExpanded);
    }

    public GooglePageObject clickOnFieldAndEnterText(String textForTranslate) {
        waiter.until(ExpectedConditions.elementToBeClickable(fieldForEnglishText));
        actions
                .moveToElement(fieldForEnglishText)
                .click()
                .sendKeys(textForTranslate)
                .perform();
        return this;
    }

    public GooglePageObject clickRussianLanguageRightButtonWhenExpanded() {
        clickElement(russianLanguageRightButtonWhenExpanded);
        return this;
    }

    public GooglePageObject clickExpandAllLanguagesButtonRight() {
        clickElement(expandAllLanguagesButtonRight);
        return this;
    }

    public GooglePageObject clickLeftEnglishButton() {
        clickElement(leftEnglishButton);
        return this;
    }

    public String getResultOfTranslate() {
        return getElementText(resultOfTranslateField);
    }
}
