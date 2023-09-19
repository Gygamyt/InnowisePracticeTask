package com.innowise.innowise_practice.pageobjects;

import com.innowise.innowise_practice.CustomLogger;
import com.innowise.innowise_practice.driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public abstract class BasePage implements CustomLogger {

    private static WebDriver driver = Driver.getDriver();

    public static final WebDriverWait waiter = new WebDriverWait(driver, Duration.ofSeconds(10));

    public static final Actions actions = new Actions(driver);

    public BasePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        BasePage.driver = driver;
    }

    public static boolean isElementVisible(WebElement webElement) {
        return webElement.isDisplayed();
    }

    public static String getElementText(WebElement webElement) {
        return waiter.until(ExpectedConditions.visibilityOf(webElement)).getText();
    }

    public static void clickElement(WebElement element) {
        waitUntilClickable(element);
        element.click();
        staticLogger.info("'{}' is clicked", element); //получается некрасиво :с
    }

    public static void moveToElementAndClick(WebElement element) {
        waitUntilClickable(element);
        actions
                .moveToElement(element)
                .click(element)
                .perform();
        staticLogger.info("moved to '{}' and clicked", element);
    }

    public static void enterText(WebElement element, String text) {
//        waitUntilClickable(element);
//        element.click();
        element.sendKeys(text);
    }

    private static void waitUntilClickable(WebElement element) {
        waiter.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static List<WebElement> findElementsByStringXPath(String xpath) {
        List<WebElement> webElementArrayList;
        return webElementArrayList = Driver.getDriver().findElements(By.xpath(xpath));
    }

    public static By formatStringForXPath(String pattern, String text) {
        return By.xpath(String.format(pattern, text));
    }

    public static void waitUntilElementVisible(By by) {
        waiter.until(ExpectedConditions.visibilityOf(Driver.getDriver().findElement(by)));
    }

    public static void waitUntilElementVisible(WebElement element) {
        waiter.until(ExpectedConditions.visibilityOf(element));
    }
}
