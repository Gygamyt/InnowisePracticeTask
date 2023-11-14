package com.innowise.innowise_practice.ui.pageobjects.google_page_objects;

import com.innowise.innowise_practice.ui.logger.NameForLogger;
import com.innowise.innowise_practice.ui.pageobjects.BasePage;
import org.openqa.selenium.By;
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

//    private static final By TEXT_FIELD_PATTERN = By.xpath("//span[contains(@lang, '%s')]"); //надо доделать

    @FindBy(id = "i12")
    @NameForLogger(name = "leftEnglishButton")
    private WebElement leftEnglishButton;
//           = findElementById(By.id("i12"));

    @FindBy(xpath = "//button[@aria-label=\"More target languages\"]")
    @NameForLogger(name = "expand all right side")
    private WebElement expandAllLanguagesButtonRight;
//            = findElementByXpath("//button[@aria-label=\"More target languages\"]");

    @FindBy(xpath = "(//div[@data-language-code=\"ru\"])[2]")
    @NameForLogger(name = "right side russian button")
    private WebElement russianLanguageRightButtonWhenExpanded;
//            =
//            findElementByXpath("(//div[@data-language-code=\"ru\"])[2]");

    @FindBy(xpath = "//span[@lang=\"en\"]")
    @NameForLogger(name = "field for text for translate")
    private WebElement fieldForEnglishText;
//            =
//            findElementByXpath("//span[@lang=\"en\"]");

    @FindBy(xpath = "//span[@lang=\"ru\"]")
    @NameForLogger(name = "result of translate")
    private WebElement resultOfTranslateField;
//            =
//            findElementByXpath("//span[@lang=\"ru\"]");


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
