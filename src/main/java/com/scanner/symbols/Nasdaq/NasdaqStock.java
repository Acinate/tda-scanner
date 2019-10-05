package com.scanner.symbols.Nasdaq;

import kong.unirest.json.JSONObject;

public class NasdaqStock {

    private String ticker;
    private String exchange = "Nasdaq";
    private String assetType = "Stock";

    NasdaqStock(JSONObject stock) {
        this.ticker = stock.getString("ticker");
    }

    public String getTicker() {
        return ticker;
    }

    public String getExchange() {
        return exchange;
    }
}
