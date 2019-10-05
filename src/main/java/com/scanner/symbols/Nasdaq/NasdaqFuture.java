package com.scanner.symbols.Nasdaq;

import kong.unirest.json.JSONObject;

public class NasdaqFuture {
    private String ticker;
    private String exchange = "Nasdaq";
    private String assetType = "Future";

    public NasdaqFuture(JSONObject future) {
        ticker = future.getString("symbol");
    }

    public String getTicker() {
        return ticker;
    }

    public String getExchange() {
        return exchange;
    }
}
