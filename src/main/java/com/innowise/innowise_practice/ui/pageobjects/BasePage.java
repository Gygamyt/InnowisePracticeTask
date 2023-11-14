package com.innowise.innowise_practice.ui.pageobjects;

import com.innowise.innowise_practice.ui.logger.CustomLogger;
import com.innowise.innowise_practice.ui.logger.NameForLogger;
import com.innowise.innowise_practice.ui.utils.Reflector;
import com.vk.api.sdk.objects.users.Fields;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
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

    public void clickElement(WebElement element, BasePage page) {
        try {
//            Field field = page.getClass().getDeclaredField(element.);

            Field[] fields = page.getClass().getDeclaredFields();

            logger().info(Arrays.toString(fields));
            for (Field f : fields) {
                try {
                    System.out.println(f.getType().getName());
                    System.out.println(f.getAnnotation(NameForLogger.class).name());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        waitUntilClickable(element);
        element.click();
        staticLogger.info("'{}' is clicked", element);
    }

    public void moveToElementAndClick(WebElement element) {
        waitUntilClickable(element);
        actions
                .moveToElement(element)
                .click(element)
                .perform();
        staticLogger.info("moved to '{}' and clicked", element);
    }

    public static void enterText(WebElement element, String text) {
        element.sendKeys(text);
    }

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

    public void waitUntilElementVisible(By by) {
        this.waiter.until(ExpectedConditions.visibilityOf(getDriver().findElement(by)));
    }

    public void waitUntilElementVisible(WebElement element) {
        this.waiter.until(ExpectedConditions.visibilityOf(element));
    }

//    public void waitUntilElementClickable(WebElement element) {
//        this.waiter.until(ExpectedConditions.elementToBeClickable(element));
//    }

    public List<WebElement> getElementsWithBy(By by) {
        return getDriver().findElements(by);
    }

    public String getPageUrl() {
        return getDriver().getCurrentUrl();
    }


    public WebElement findElementById(By by) {
        return this.driver.findElement(by);
    }

    public WebElement findElementByXpath(String xpath) {
        return getDriver().findElement(By.xpath(xpath));
    }
}
