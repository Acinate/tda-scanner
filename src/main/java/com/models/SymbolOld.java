package com.models;

public class SymbolOld {
    private String ticker;
    private String exchange;
    private String assetType;

    public SymbolOld(String ticker, String exchange, String assetType) {
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
