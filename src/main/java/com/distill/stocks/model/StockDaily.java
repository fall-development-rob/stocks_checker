package com.distill.stocks.model;

import com.distill.stocks.utils.PersistentLocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;


@Entity
@Table(name = "stock_daily")
public class StockDaily {

    private Long id;
    private String ticker;
    private int volume;
    private double open;
    private double low;
    private double high;
    private double close;
    private LocalDate date;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "ticker", nullable = false)
    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    @Column(name = "volume")
    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Column(name = "open")
    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    @Column(name = "low")
    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    @Column(name = "high")
    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    @Column(name = "close")
    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    @Type(PersistentLocalDate.class)
    @Column(name = "date", nullable = false)
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "StockDaily{" +
               "id=" + id +
               ", ticker='" + ticker + '\'' +
               ", volume=" + volume +
               ", open=" + open +
               ", low=" + low +
               ", high=" + high +
               ", close=" + close +
               ", date=" + date +
               '}';
    }
}
