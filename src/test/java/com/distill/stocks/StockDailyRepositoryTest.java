package com.distill.stocks;

import com.distill.stocks.model.StockDaily;
import com.distill.stocks.repository.StockDailyRepository;
import org.joda.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class StockDailyRepositoryTest {

    @Autowired
    private StockDailyRepository stockDailyRepository;

    @Test
    public void testExistsByTickerAndDate_Exists() {
        // Create and save a sample StockDaily entity
        StockDaily stockDaily = new StockDaily();
        stockDaily.setTicker("AAPL");
        stockDaily.setDate(LocalDate.now());
        stockDailyRepository.save(stockDaily);

        // Test the existsByTickerAndDate method
        boolean exists = stockDailyRepository.existsByTickerAndDate("AAPL", LocalDate.now());
        assertThat(exists).isTrue();
    }

    @Test
    public void testExistsByTickerAndDate_NotExists() {
        // Test the existsByTickerAndDate method when the record doesn't exist
        boolean exists = stockDailyRepository.existsByTickerAndDate("AAPL", LocalDate.now());
        assertThat(exists).isFalse();
    }

}
