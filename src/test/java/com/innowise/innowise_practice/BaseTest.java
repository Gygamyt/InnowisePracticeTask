package com.innowise.innowise_practice;

import com.innowise.innowise_practice.driver.Driver;
import org.junit.jupiter.api.AfterEach;

public abstract class BaseTest implements CustomLogger {
    @AfterEach
    public void killDriver() {
        Driver.quitWebDriver();
    }
}
