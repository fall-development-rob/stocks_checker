package com.distill.stocks;

import org.junit.jupiter.api.Test;
import com.distill.stocks.model.StockInfo;
import com.distill.stocks.utils.Logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;

public class LoggingUtilsTest {

    @Test
    public void testLogListOfItemsWithCompanyName() {
        // Mock logger
        Logger logger = LoggerFactory.getLogger(Logging.class);

        // Initialize LoggingUtils
        Logging loggingUtils = new Logging(logger);

        // Mock data
        List<StockInfo> items = new ArrayList<>();
        items.add(new StockInfo("AAPL", "Apple Inc"));
        items.add(new StockInfo("MSFT", "Microsoft Corp"));

        // Call the method
        loggingUtils.logListOfItems(items, "Test message:", 2);

        // Verify log statements
        // You can use appropriate verification methods based on your logger implementation
    }

    @Test
    public void testLogListOfItemsWithCompanyNameEmptyList() {
        // Mock logger
        Logger logger = LoggerFactory.getLogger(Logging.class);

        // Initialize LoggingUtils
        Logging loggingUtils = new Logging(logger);

        // Mock data
        List<StockInfo> items = new ArrayList<>();

        // Call the method
        loggingUtils.logListOfItems(items, "Test message:", 2);

        // Verify log statements
        // You can use appropriate verification methods based on your logger implementation
    }

}
