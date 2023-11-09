package com.innowise.innowise_practice.ui.utils;

import com.innowise.innowise_practice.CustomLogger;
import com.innowise.innowise_practice.ui.driver.Driver;
import io.qameta.allure.Attachment;
import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.util.Optional;

public class CustomTestWatcher implements TestWatcher, CustomLogger, AfterAllCallback {
    private final WebDriver driver;

    public CustomTestWatcher(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        logger().info("test successful    " + context.getTestMethod());
        getScreenshot(driver);
        Driver.quitWebDriver();
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        logger().info("test pomer   " + context.getTestMethod());
        getScreenshot(driver);
        Driver.quitWebDriver();
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        logger().info("abort");
        Driver.quitWebDriver();
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        logger().info("test disabled. why.");
        Driver.quitWebDriver();
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] getScreenshot(WebDriver webDriver) {
        return ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public void afterAll(ExtensionContext context) {
        Driver.killAllDrivers();
        staticLogger.info("all drivers must be killed");
    }
}
