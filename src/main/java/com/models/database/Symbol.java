package com.models.database;

public class Symbol {
    private String symbol;
    private Asset asset;
    private Dividend dividend;
    private Earnings earnings;
    private Fundamental fundamental;

    public Symbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public Dividend getDividend() {
        return dividend;
    }

    public void setDividend(Dividend dividend) {
        this.dividend = dividend;
    }

    public Earnings getEarnings() {
        return earnings;
    }

    public void setEarnings(Earnings earnings) {
        this.earnings = earnings;
    }

    public Fundamental getFundamental() {
        return fundamental;
    }

    public void setFundamental(Fundamental fundamental) {
        this.fundamental = fundamental;
    }
}
