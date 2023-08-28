package com.distill.stocks.model;

public class StockInfo {
    private String symbol;
    private String companyName;

    public StockInfo(String symbol, String companyName) {
        this.symbol = symbol;
        this.companyName = companyName;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getCompanyName() {
        return companyName;
    }
}
