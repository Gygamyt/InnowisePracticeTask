package com.innowise.innowise_practice.driver;

import com.innowise.innowise_practice.CustomLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class Driver implements CustomLogger {
    private static final ThreadLocal<WebDriver> DRIVER_THREAD_LOCAL_STORAGE = new ThreadLocal<>();

    private Driver() {
    }


    public static WebDriver getDriver() {
        if (DRIVER_THREAD_LOCAL_STORAGE.get() == null) {
            DRIVER_THREAD_LOCAL_STORAGE.set(setDriverConfigs());
            setUpImplicitWait();
            staticLogger.info("new driver has been created");
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
        ChromeOptions chromeOptions = new ChromeOptions();
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
}
