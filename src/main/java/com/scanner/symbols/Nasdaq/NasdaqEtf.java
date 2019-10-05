package com.scanner.symbols.Nasdaq;

import com.models.Symbol;
import kong.unirest.json.JSONObject;

public class NasdaqEtf extends Symbol {
    private String ticker;
    private String exchange = "Nasdaq";
    private String assetType = "ETF";

    public NasdaqEtf(JSONObject etf) {
        ticker = etf.getString("symbol");
    }

    public String getTicker() {
        return ticker;
    }

    public String getExchange() {
        return exchange;
    }
}
