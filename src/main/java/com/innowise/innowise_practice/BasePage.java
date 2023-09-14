package com.innowise.innowise_practice;

import com.innowise.innowise_practice.driver.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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
        waiter.until(ExpectedConditions.elementToBeClickable(element)).click();
        staticLogger.info("'{}' is clicked", element); //получается некрасиво :с
    }
}
