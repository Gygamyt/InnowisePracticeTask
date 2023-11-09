package com.innowise.innowise_practice.ui.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface CustomLogger {
    default Logger logger() {
        return LogManager.getLogger(this.getClass());
    }
    Logger staticLogger = LogManager.getLogger();
}
