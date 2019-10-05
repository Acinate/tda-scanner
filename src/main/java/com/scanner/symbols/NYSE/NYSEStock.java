package com.scanner.symbols.NYSE;

import kong.unirest.json.JSONObject;

class NYSEStock {
    private String ticker;
    private String exchange = "NYSE";
    private String assetType = "Stock";

    NYSEStock(JSONObject stock) {
        ticker = stock.getString("symbolTicker");
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
