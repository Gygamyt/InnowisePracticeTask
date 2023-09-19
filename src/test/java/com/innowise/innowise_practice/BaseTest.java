package com.innowise.innowise_practice;

import com.innowise.innowise_practice.driver.Driver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseTest implements CustomLogger {
    @BeforeEach
    public void setUpDriver() {
        Driver.getDriver();
    }

    @AfterEach
    public void killDriver() {
        if (Driver.getDriver() != null) {
            Driver.quitWebDriver();
        }
    }
}
