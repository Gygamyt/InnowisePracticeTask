package com.innowise.innowise_practice.ui.pageobjects.google_page_objects;

import com.innowise.innowise_practice.ui.pageobjects.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GooglePageObject extends BasePage {

    public GooglePageObject(WebDriver driver) {
        super(driver);
    }

    private static final By TEXT_FIELD_PATTERN = By.xpath("//span[contains(@lang, '%s')]"); //надо доделать

    @FindBy(id = "i12")
    private WebElement leftEnglishButton;

    @FindBy(xpath = "//button[@aria-label=\"More target languages\"]")
    private WebElement expandAllLanguagesButtonRight;

    @FindBy(xpath = "(//div[@data-language-code=\"ru\"])[2]")
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
        moveToElementAndClick(fieldForEnglishText);
        actions
                .sendKeys(textForTranslate)
                .perform();
        return this;
    }

    public GooglePageObject clickRussianLanguageRightButtonWhenExpanded() {
        moveToElementAndClick(russianLanguageRightButtonWhenExpanded);
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
