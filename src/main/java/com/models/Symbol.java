package com.models;

public class Symbol {
    private String ticker;
    private String exchange;
    private String assetType;

    public Symbol(String ticker, String exchange, String assetType) {
        this.ticker = ticker;
        this.exchange = exchange;
        this.assetType = assetType;
    }

    public String getTicker() {
        return ticker;
    }

    public String getExchange() {
        return exchange;
    }

    public String getAssetType() {
        return assetType;
    }
}
