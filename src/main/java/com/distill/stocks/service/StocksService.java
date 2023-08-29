package com.distill.stocks.service;

import com.distill.stocks.configuration.StocksProperties;
import com.distill.stocks.dto.StockDto;
import com.distill.stocks.model.StockDaily;
import com.distill.stocks.model.StockInfo;
import com.distill.stocks.repository.StockDailyRepository;
import jakarta.annotation.PostConstruct;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.distill.stocks.utils.Logging;

@Service
public class StocksService {
    private static final Logger logger = LoggerFactory.getLogger(StocksService.class);
    private final Logging loggingUtils = new Logging(logger);

    private static final LinkedList<StockInfo> tickers = new LinkedList<>();

    private static final Object tickersLock = new Object(); // Lock for synchronization

    @Autowired
    private StockDailyRepository stockDailyRepository;

    @Autowired
    private StocksProperties config;

    private final ExecutorService executorService = Executors.newFixedThreadPool(1);

    private final Thread printer = new Thread(() -> {
        while (true) {
            print();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {

            }
        }
    });

    @PostConstruct
    public void start() {
        synchronized (tickersLock) {
            tickers.add(  new StockInfo("AAPL", "Apple Inc"  ));
            tickers.add(  new StockInfo("MSFT", "Microsoft Corp"  ));
            tickers.add(  new StockInfo("GOOG", "Alphabet Inc Class C"  ));
            tickers.add(  new StockInfo("AMZN", "Amazon.Com Inc."  ));
            tickers.add(  new StockInfo("NVDA", "Nvidia Corp"  ));
            tickers.add(  new StockInfo("TSLA", "Tesla Inc"  ));
            tickers.add(  new StockInfo("META", "Meta Platforms, Inc."  ));
            tickers.add(  new StockInfo("NFLX", "Netflix Inc"  ));
            tickers.add(  new StockInfo("INTC", "Intel Corporation Corp"  ));
            tickers.add(  new StockInfo("SBUX", "Starbucks Corp"  ));
            tickers.add(  new StockInfo("ABNB", "Airbnb, Inc."  ));
            tickers.add(  new StockInfo("PYPL", "Paypal Holdings Inc"  ));
            tickers.add(  new StockInfo("ATVI", "Activision Blizzard Inc"  ));
            tickers.add(  new StockInfo("MDLZ", "Mondelez International Inc"  ));
            tickers.add(  new StockInfo("ADP", "Automatic Data Processing Inc"  ));
            tickers.add(  new StockInfo("REGN", "Regeneron Pharmaceuticals Inc"  ));
            tickers.add(  new StockInfo("ISRG", "Intuitive Surgical Inc"  ));
            tickers.add(  new StockInfo("VRTX", "Vertex Pharmaceuticals Inc"  ));
            tickers.add(  new StockInfo("MU", "Micron Technology Inc"  ));
            tickers.add(  new StockInfo("MELI", "Mercadolibre Inc"  ));
            tickers.add(  new StockInfo("PYPL", "Paypal Holdings Inc"  ));
        }

        // Today's date requires upgraded api plan.
        LocalDate yesterday = LocalDate.now().minusDays(1);
        LocalDate dateToRequest = yesterday.minusDays(config.getFetchDataForDays());

        while (!dateToRequest.isAfter(yesterday)) {
            for (StockInfo ticker : tickers) {
                if (!stockDailyRepository.existsByTickerAndDate(ticker.getSymbol(), dateToRequest)) {
                    executorService.submit(getTickerRunnable(ticker.getSymbol(), dateToRequest));
                }
            }
            dateToRequest = dateToRequest.plusDays(1);
        }

        printer.start();

    }

    private Runnable getTickerRunnable(String ticker, LocalDate date) {
        return new Runnable() {
            @Override
            public void run() {
                fetchStockData(ticker, date)
                        .map(stockDto -> {
                            StockDaily stockDaily = new StockDaily();
                            stockDaily.setTicker(stockDto.getSymbol());
                            stockDaily.setOpen(stockDto.getOpen());
                            stockDaily.setLow(stockDto.getLow());
                            stockDaily.setHigh(stockDto.getHigh());
                            stockDaily.setClose(stockDto.getClose());
                            stockDaily.setVolume(stockDto.getVolume());
                            stockDaily.setDate(LocalDate.parse(stockDto.getFrom(), DateTimeFormat.forPattern("yyyy-MM-dd"  )));
                            return stockDaily;
                        })
                        .ifPresent(stockDailyRepository::save);
            }
        };
    }

    private Optional<StockDto> fetchStockData(String ticker, LocalDate date) {
        String formattedDate = date.toString(DateTimeFormat.forPattern("yyyy-MM-dd"));
        String url = "https://api.polygon.io/v1/open-close/" + ticker + "/" + formattedDate + "?apiKey=U8qL6DQmwOLZMp5w5YHw3GJMJhdZjOvp";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<StockDto> response = null;
        try {
            response = restTemplate.exchange(url, HttpMethod.GET, null, StockDto.class);
            StockDto body = response.getBody();
            System.out.println(body);
            return Optional.of(body);
        } catch (RestClientException e) {
            System.out.println("API request failed");
            e.printStackTrace();
            return Optional.of(null);
        }
    }

    private void print() {
        System.out.println(stockDailyRepository.findAll());
    }

    public List<StockDaily> getTop10HighestCloseValueStocks() {
        return stockDailyRepository.findTop10ByOrderByCloseDesc();
    }

    public List<String> getTop5CompaniesWithGreatestPositiveChange() {
        synchronized (tickersLock) {
            List<String> top5Companies = new ArrayList<>();
            List<StockDaily> stocks = stockDailyRepository.findAll();

            // Calculate % change for each company over a 3-day period
            Map<String, Double> percentChangeMap = new HashMap<>();
            for (StockInfo ticker : tickers) {
                List<StockDaily> companyStocks = stocks.stream()
                        .filter(stock -> stock.getTicker().equals(ticker.getSymbol()))
                        .collect(Collectors.toList());

                if (companyStocks.size() >= 3) {
                    double initialOpen = companyStocks.get(2).getOpen();
                    double finalClose = companyStocks.get(0).getClose();
                    double percentChange = ((finalClose - initialOpen) / initialOpen) * 100;
                    percentChangeMap.put(ticker.getCompanyName(), percentChange);
                }
            }

            // Sort companies by % change and select top 5
            percentChangeMap.entrySet().stream()
                    .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                    .limit(5)
                    .forEach(entry -> top5Companies.add(entry.getKey(  )));

            return top5Companies;
        }
    }

    @Scheduled(fixedDelay = 10000) // 10 seconds
    public void displayStats() {
        List<StockDaily> top10Stocks = getTop10HighestCloseValueStocks();
        List<String> top5Companies = getTop5CompaniesWithGreatestPositiveChange();

        // Log messages for top 10 and top 5 using LoggingUtils
        loggingUtils.logListOfItems(top10Stocks, "Top 10 Highest Close Value Stocks:", 10);
        loggingUtils.logListOfItems(top5Companies, "Top 5 Companies with Greatest Positive Change:", 5);

    }
}
