package com.innowise.innowise_practice.driver;

import com.innowise.innowise_practice.CustomLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class Driver implements CustomLogger {
    private static final ThreadLocal<WebDriver> localStorage = new ThreadLocal<>();

    private Driver() {
    }


    public static WebDriver getDriver() {
        if (localStorage.get() == null) {
            localStorage.set(setDriverConfigs());
            setUpImplicitWait();

            staticLogger.info("new driver has been created");
        }
        staticLogger.info("driver has been called");
        return localStorage.get();
    }

    public static void quitWebDriver() {
        localStorage.get().close();
        localStorage.get().quit();
        staticLogger.info("quit from browser");
        localStorage.remove();
        staticLogger.info("local storage has been cleaned");
    }

    private static WebDriver setDriverConfigs() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("enable-automation");
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("--remote-allow-origins=*");
//        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--window-size=1920x1080");
        return new ChromeDriver(chromeOptions);
    }

    private static void setUpImplicitWait() {
        localStorage.get()
                .manage()
                .timeouts()
                .implicitlyWait(Duration.ofSeconds(5));
    }

    public static void openLink(String link) {
        Driver.getDriver().get(link);
    }
}
