package com.innowise.innowise_practice.ui.pageobjects.google;

import com.innowise.innowise_practice.ui.annotations.NameForLogger;
import com.innowise.innowise_practice.ui.pageobjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

@SuppressWarnings("")
public class GooglePageObject extends BasePage {

    public GooglePageObject(WebDriver driver, WebDriverWait waiter, Actions actions) {
        super(driver, waiter, actions);
    }

    @FindBy(xpath = "//button[@aria-label=\"More target languages\"]")
    @NameForLogger(name = "expand all right side")
    private WebElement expandAllLanguagesButtonRight;

    @FindBy(xpath = "(//div[@data-language-code=\"ru\"])[2]")
    @NameForLogger(name = "right side russian button")
    private WebElement russianLanguageRightButtonWhenExpanded;

    @FindBy(xpath = "//span[@lang=\"en\"]")
    @NameForLogger(name = "field for text for translate")
    private WebElement fieldForEnglishText;

    @FindBy(xpath = "//span[@lang=\"ru\"]")
    @NameForLogger(name = "result of translate")
    private WebElement resultOfTranslateField;

    @FindBy(id = "i12")
//    @NameForLogger(name = "left English Button") //for example
    private WebElement leftEnglishButton;


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
        clickElement(expandAllLanguagesButtonRight, this);
        return this;
    }

    public GooglePageObject clickLeftEnglishButton() {
        clickElement(leftEnglishButton, this);
        return this;
    }

    public String getResultOfTranslate() {
        return getElementText(resultOfTranslateField);
    }
}
