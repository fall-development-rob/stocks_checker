package com.distill.stocks.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StockDto {
    @JsonProperty("afterHours")
    private double afterHours;
    @JsonProperty("close")
    private double close;
    @JsonProperty("from")
    private String from;
    @JsonProperty("high")
    private double high;
    @JsonProperty("low")
    private double low;
    @JsonProperty("open")
    private double open;
    @JsonProperty("preMarket")
    private double preMarket;
    @JsonProperty("status")
    private String status;
    @JsonProperty("symbol")
    private String symbol;
    @JsonProperty("volume")
    private int volume;

    public double getAfterHours() {
        return afterHours;
    }

    public void setAfterHours(double afterHours) {
        this.afterHours = afterHours;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getPreMarket() {
        return preMarket;
    }

    public void setPreMarket(double preMarket) {
        this.preMarket = preMarket;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "StockDto{" +
               "afterHours=" + afterHours +
               ", close=" + close +
               ", from='" + from + '\'' +
               ", high=" + high +
               ", low=" + low +
               ", open=" + open +
               ", preMarket=" + preMarket +
               ", status='" + status + '\'' +
               ", symbol='" + symbol + '\'' +
               ", volume=" + volume +
               '}';
    }
}
