package com.distill.stocks.repository;

import com.distill.stocks.model.StockDaily;
import org.joda.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StockDailyRepository extends JpaRepository<StockDaily, Long> {

    boolean existsByTickerAndDate(String ticker, LocalDate date);

    List<StockDaily> findTop10ByOrderByCloseDesc();
}
