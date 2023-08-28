package com.distill.stocks.utils;

import org.slf4j.Logger;

import java.util.*;

public class Logging {

    private final Logger logger;
    private final Object logLock = new Object();

    public Logging(Logger logger) {
        this.logger = logger;
    }

    public <T> void logListOfItems(List<T> items, String title, int minItems) {
        synchronized (logLock) {
            if (items.size() >= minItems) {
                StringBuilder logMessage = new StringBuilder(title);
                items.forEach(item -> {

                    logMessage.append("\n- ").append(" (").append(item).append(")");
                });
                logger.info(logMessage.toString());
            } else {
                logger.info("Not enough data points to display: {}", title);
            }
        }
    }
}
