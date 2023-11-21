package com.innowise.innowise_practice.ui.pageobjects;

import com.innowise.innowise_practice.ui.logger.CustomLogger;
import com.innowise.innowise_practice.ui.logger.LoggerAnnotation;
import com.innowise.innowise_practice.ui.utils.Reflector;
import org.aspectj.lang.reflect.MethodSignature;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static com.innowise.innowise_practice.ui.driver.Driver.getDriver;

public abstract class BasePage implements CustomLogger, Reflector {

    public WebDriver driver;

    public WebDriverWait waiter;

    public Actions actions;

    public BasePage(WebDriver driver, WebDriverWait waiter, Actions actions) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.waiter = waiter;
        this.actions = actions;
    }

    public static boolean isElementVisible(WebElement webElement) {
        return webElement.isDisplayed();
    }

    public String getElementText(WebElement webElement) {
        return this.waiter.until(ExpectedConditions.visibilityOf(webElement)).getText();
    }

    @LoggerAnnotation(action = "clicked")
    public void clickElement(WebElement element, BasePage page) {
        waitUntilClickable(element);
        element.click();
    }

    @LoggerAnnotation(action = "moved and clicked")
    public void moveToElementAndClick(WebElement element) {
        waitUntilClickable(element);
        actions
                .moveToElement(element)
                .click(element)
                .perform();
    }

    public static void enterText(WebElement element, String text) {
        element.sendKeys(text);
    }

    @LoggerAnnotation(action = "waited until element clickable")
    private void waitUntilClickable(WebElement element) {
        this.waiter.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static List<WebElement> findElementsByStringXPath(String xpath) {
        List<WebElement> webElementArrayList;
        return webElementArrayList = getDriver().findElements(By.xpath(xpath));
    }

    public static By formatStringForXPath(String pattern, String text) {
        return By.xpath(String.format(pattern, text));
    }

    @LoggerAnnotation(action = "waited until element visible, using 'By'")
    public void waitUntilElementVisible(By by) {
        this.waiter.until(ExpectedConditions.visibilityOf(getDriver().findElement(by)));
    }

    @LoggerAnnotation(action = "waited until element visible")
    public void waitUntilElementVisible(WebElement element) {
        this.waiter.until(ExpectedConditions.visibilityOf(element));
    }

    @LoggerAnnotation(action = "got elements, using 'By'")
    public List<WebElement> getElementsWithBy(By by) {
        return getDriver().findElements(by);
    }

    @LoggerAnnotation(action = "page is opened")
    public String getPageUrl() {
        return getDriver().getCurrentUrl();
    }

}
