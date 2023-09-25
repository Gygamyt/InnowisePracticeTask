package com.innowise.innowise_practice.ui;

import com.innowise.innowise_practice.CustomLogger;
import com.innowise.innowise_practice.ui.driver.Driver;
import com.innowise.innowise_practice.ui.utils.CustomTestWatcher;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.RegisterExtension;

import static com.innowise.innowise_practice.ui.driver.Driver.getDriver;

public abstract class BaseTest implements CustomLogger {

    @RegisterExtension
    public CustomTestWatcher customTestWatcher = new CustomTestWatcher(getDriver());

    @BeforeAll
    public static void closeAllDriverInstances() {
        Driver.killAllDrivers();
    }
}
