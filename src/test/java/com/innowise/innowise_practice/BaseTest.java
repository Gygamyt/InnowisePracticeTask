package com.innowise.innowise_practice;

import com.innowise.innowise_practice.utils.CustomTestWatcher;
import org.junit.jupiter.api.extension.RegisterExtension;

import static com.innowise.innowise_practice.driver.Driver.getDriver;

public abstract class BaseTest implements CustomLogger {

    @RegisterExtension
    public CustomTestWatcher customTestWatcher = new CustomTestWatcher(getDriver());
}
