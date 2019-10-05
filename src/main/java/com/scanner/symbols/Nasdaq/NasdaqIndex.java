package com.scanner.symbols.Nasdaq;

import kong.unirest.json.JSONObject;

public class NasdaqIndex {
    private String ticker;
    private String exchange = "Nasdaq";
    private String assetType = "Index";

    public NasdaqIndex(JSONObject index) {
        ticker = index.getString("symbol");
    }

    public String getTicker() {
        return ticker;
    }

    public String getExchange() {
        return exchange;
    }
}
