package com.innowise.innowise_practice.ui.driver;

import com.innowise.innowise_practice.ui.logger.CustomLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Driver implements CustomLogger {
    private static final ThreadLocal<WebDriver> DRIVER_THREAD_LOCAL_STORAGE = new ThreadLocal<>();

    private static final ThreadLocal<WebDriverWait> WAITER_THREAD_LOCAL_STORAGE = new ThreadLocal<>();

    public static final ThreadLocal<Actions> ACTIONS_THREAD_LOCAL_STORAGE = new ThreadLocal<>();

    private Driver() {
    }

    public static WebDriver getDriver() {
        if (DRIVER_THREAD_LOCAL_STORAGE.get() == null) {
            DRIVER_THREAD_LOCAL_STORAGE.set(setDriverConfigs());
            setUpImplicitWait();
            staticLogger.info("new driver has been created");
            setUpWaiter();
            staticLogger.info("waiter has been created");
            setUpActions();
            staticLogger.info("actions has been created");
        }
        staticLogger.info("driver has been called");
        return DRIVER_THREAD_LOCAL_STORAGE.get();
    }

    public static void quitWebDriver() {
        DRIVER_THREAD_LOCAL_STORAGE.get().close();
        DRIVER_THREAD_LOCAL_STORAGE.get().quit();
        staticLogger.info("quit from browser");
        DRIVER_THREAD_LOCAL_STORAGE.remove();
        staticLogger.info("local storage has been cleaned");
    }

    private static WebDriver setDriverConfigs() {
        var chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("enable-automation");
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--window-size=1920x1080");
        return new ChromeDriver(chromeOptions);
    }

    private static void setUpImplicitWait() {
        DRIVER_THREAD_LOCAL_STORAGE.get()
                .manage()
                .timeouts()
                .implicitlyWait(Duration.ofSeconds(5));
    }

    public static void openLink(String link) {
        Driver.getDriver().get(link);
    }

    public static void killAllDrivers() {
        while (DRIVER_THREAD_LOCAL_STORAGE.get() != null) {
            DRIVER_THREAD_LOCAL_STORAGE.get().close();
            DRIVER_THREAD_LOCAL_STORAGE.get().quit();
        }
    }

    private static void setUpWaiter() {
        WAITER_THREAD_LOCAL_STORAGE.set(new WebDriverWait(getDriver(), Duration.ofSeconds(10)));
    }

    public static WebDriverWait getWaiter() {
        return WAITER_THREAD_LOCAL_STORAGE.get();
    }

    private static void setUpActions() {
        ACTIONS_THREAD_LOCAL_STORAGE.set(new Actions(getDriver()));
    }

    public static Actions getActions() {
        return ACTIONS_THREAD_LOCAL_STORAGE.get();
    }
}
