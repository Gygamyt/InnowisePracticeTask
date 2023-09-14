package com.innowise.innowise_practice.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class Driver {
    private static final ThreadLocal<WebDriver> localStorage = new ThreadLocal<>();

    private Driver() {
    }


    public static WebDriver getDriver() {
        if (localStorage.get() == null) {
            localStorage.set(setDriverConfigs());
            setUpImplicitWait();
        }
        return localStorage.get();
    }

    public static void quitWebDriver() {
        localStorage.get().quit();
        localStorage.remove();
    }

    private static WebDriver setDriverConfigs() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("enable-automation");
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-extensions");
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

    public static void main(String[] args) throws InterruptedException {
        getDriver().get("https://google.com");
        Thread.sleep(1000);
        quitWebDriver();
    }
}
