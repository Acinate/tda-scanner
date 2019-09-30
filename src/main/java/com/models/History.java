package com.models;

import kong.unirest.json.JSONObject;

import java.util.ArrayList;

public class History {

    public History(JSONObject candles) {
        this.candles = new ArrayList<>();
        for (Object obj : candles.getJSONArray("candles")) {
            this.candles.add(new Candle((JSONObject)obj));
        }
    }

    public History(ArrayList candles) {
        this.candles = candles;
    }

    public Candle getHigh() {
        Candle high = null;
        for (Candle candle : candles) {
            if (high == null) {
                high = candle;
            } else {
                if (high.getClose() < candle.getClose()) {
                    high = candle;
                }
            }
        }
        return high;
    }

    public Candle getLow() {
        Candle low = null;
        for (Candle candle : candles) {
            if (low == null) {
                low = candle;
            } else {
                if (low.getClose() > candle.getClose()) {
                    low = candle;
                }
            }
        }
        return low;
    }

    public History() {}

    public ArrayList<Candle> getCandles() {
        return candles;
    }

    private ArrayList<Candle> candles;
}
