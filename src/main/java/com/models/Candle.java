package com.models;

import com.google.gson.JsonObject;
import kong.unirest.json.JSONObject;

public class Candle {

    public Candle(JSONObject candle) {
        open = candle.getDouble("open");
        high = candle.getDouble("high");
        low = candle.getDouble("low");
        close = candle.getDouble("close");
        volume = candle.getDouble("volume");
        datetime = candle.getLong("datetime");
    }

    public Candle(JsonObject candle) {
        open = candle.get("open").getAsDouble();
        high = candle.get("high").getAsDouble();
        low = candle.get("low").getAsDouble();
        close = candle.get("close").getAsDouble();
        volume = candle.get("volume").getAsDouble();
        datetime = candle.get("datetime").getAsLong();
    }

    public double getOpen() {
        return open;
    }

    public double getHigh() {
        return high;
    }

    public double getLow() {
        return low;
    }

    public double getClose() {
        return close;
    }

    public double getVolume() {
        return volume;
    }

    public long getDatetime() {
        return datetime;
    }

    private double open;
    private double high;
    private double low;
    private double close;
    private double volume;
    private long datetime;
}
