package com.distill.stocks;

import com.distill.stocks.model.StockDaily;
import com.distill.stocks.service.StocksService;
import com.distill.stocks.repository.StockDailyRepository;
import com.distill.stocks.model.StockInfo;
import com.distill.stocks.utils.Logging;
import com.distill.stocks.dto.StockDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import java.time.LocalDate;

public class StocksServiceTest {

    @Mock
    private StockDailyRepository stockDailyRepository;

    @InjectMocks
    private StocksService stocksService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFetchStockData() {
        // Mocking data
        StockDto stockDto = new StockDto();
        stockDto.setSymbol("AAPL");
        Mockito.when(stockDailyRepository.existsByTickerAndDate(anyString(), any(org.joda.time.LocalDate.class)))
                .thenReturn(false);

        // Call the method and assert
        stocksService.fetchStockData("AAPL", LocalDate.now());
        Mockito.verify(stockDailyRepository, Mockito.times(1)).existsByTickerAndDate("AAPL", LocalDate.now());
    }

    @Test
    public void testDisplayStats() {
        // Mock data
        List<StockDaily> top10Stocks = new ArrayList<>();
        List<StockInfo> top5Companies = new ArrayList<>();
        // Add sample data to top10Stocks and top5Companies

        // Call the method
        stocksService.displayStats();
    }

}
