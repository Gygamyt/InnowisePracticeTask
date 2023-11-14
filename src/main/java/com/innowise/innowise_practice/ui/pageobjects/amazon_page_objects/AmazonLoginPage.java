package com.innowise.innowise_practice.ui.pageobjects.amazon_page_objects;

import com.innowise.innowise_practice.ui.driver.Driver;
import com.innowise.innowise_practice.ui.pageobjects.BasePage;
import com.innowise.innowise_practice.ui.utils.CredentialsEnum;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AmazonLoginPage extends BasePage {
    public AmazonLoginPage(WebDriver driver, WebDriverWait waiter, Actions actions) {
        super(driver, waiter, actions);
    }

    private final String INPUT_TYPE_PATTERN = "//input[@type='%s']";

    private WebElement emailInputField =
            Driver.getDriver().findElement(formatStringForXPath(INPUT_TYPE_PATTERN, "email"));
    private WebElement passwordInputField =
            Driver.getDriver().findElement(formatStringForXPath(INPUT_TYPE_PATTERN, "password"));

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(id = "auth-signin-button")
    private WebElement signInButton;

    public AmazonMainPage loginAmazon() {
        actions
                .sendKeys(CredentialsEnum.AMAZON_LOG.getInf())
                .moveToElement(continueButton)
                .click()
                .perform();
        actions
                .sendKeys(CredentialsEnum.AMAZON_PASS.getInf())
                .moveToElement(signInButton)
                .click()
                .perform();
        return new AmazonMainPage(this.driver, this.waiter, this.actions);
    }
}
