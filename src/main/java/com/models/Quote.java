package com.models;

import kong.unirest.json.JSONObject;

public class Quote {

    public String getSymbol() { return symbol; }
    public double getLastPrice() { return lastPrice; }
    public int getTotalVolume() { return totalVolume; }
    public double getOpenPrice() { return openPrice; }
    public double getClosePrice() { return closePrice; }
    public double get52WkHigh() { return high52Wk; }
    public double get52WkLow() { return low52Wk; }

    public Quote(JSONObject object) {
        assetType = object.get("assetType").toString();
        symbol = object.get("symbol").toString();
        description = object.get("description").toString();
        bidPrice = Double.parseDouble(object.get("bidPrice").toString());
        bidSize = Double.parseDouble(object.get("bidSize").toString());
        bidId = object.get("bidId").toString();
        askPrice = Double.parseDouble(object.get("askPrice").toString());
        askSize = Integer.parseInt(object.get("askSize").toString());
        askId = object.get("askId").toString();
        lastPrice = Double.parseDouble(object.get("lastPrice").toString());
        lastSize = Integer.parseInt(object.get("lastSize").toString());
        lastId = object.get("lastId").toString();
        openPrice = Double.parseDouble(object.get("openPrice").toString());
        highPrice = Double.parseDouble(object.get("highPrice").toString());
        lowPrice = Double.parseDouble(object.get("lowPrice").toString());
        bidTick = object.get("bidTick").toString();
        closePrice = Double.parseDouble(object.get("closePrice").toString());
        netChange = Double.parseDouble(object.get("netChange").toString());
        totalVolume = Integer.parseInt(object.get("totalVolume").toString());
        quoteTimeInLong = Long.parseLong(object.get("quoteTimeInLong").toString());
        tradeTimeInLong = Long.parseLong(object.get("tradeTimeInLong").toString());
        mark = Double.parseDouble(object.get("mark").toString());
        exchange = object.get("exchange").toString();
        exchangeName = object.get("exchangeName").toString();
        marginable = Boolean.parseBoolean(object.get("marginable").toString());
        shortable = Boolean.parseBoolean(object.get("shortable").toString());
        volatility = Double.parseDouble(object.get("volatility").toString());
        digits = Integer.parseInt(object.get("digits").toString());
        high52Wk = Double.parseDouble(object.get("52WkHigh").toString());
        low52Wk = Double.parseDouble(object.get("52WkLow").toString());
        nAV = Double.parseDouble(object.get("nAV").toString());
        peRatio = Double.parseDouble(object.get("peRatio").toString());
        divAmount = Double.parseDouble(object.get("divAmount").toString());
        divYield = Double.parseDouble(object.get("divYield").toString());
        divDate = object.get("divDate").toString();
        securityStatus = object.get("securityStatus").toString();
        regularMarketLastPrice = Double.parseDouble(object.get("regularMarketLastPrice").toString());
        regularMarketLastSize = Integer.parseInt(object.get("regularMarketLastSize").toString());
        regularMarketNetChange = Double.parseDouble(object.get("regularMarketNetChange").toString());
        regularMarketTradeTimeInLong = Long.parseLong(object.get("regularMarketTradeTimeInLong").toString());
        netPercentChangeInDouble = Double.parseDouble(object.get("netPercentChangeInDouble").toString());
        markChangeInDouble = Double.parseDouble(object.get("markChangeInDouble").toString());
        markPercentChangeInDouble = Double.parseDouble(object.get("markPercentChangeInDouble").toString());
        regularMarketPercentChangeInDouble =
                Double.parseDouble(object.get("regularMarketPercentChangeInDouble").toString());
        delayed = Boolean.parseBoolean(object.get("delayed").toString());
    }

    private String assetType;
    private String symbol;
    private String description;
    private double bidPrice;
    private double bidSize;
    private String bidId;
    private double askPrice;
    private double askSize;
    private String askId;
    private double lastPrice;
    private int lastSize;
    private String lastId;
    private double openPrice;
    private double highPrice;
    private double lowPrice;
    private String bidTick;
    private double closePrice;
    private double netChange;
    private int totalVolume;
    private long quoteTimeInLong;
    private long tradeTimeInLong;
    private double mark;
    private String exchange;
    private String exchangeName;
    private boolean marginable;
    private boolean shortable;
    private double volatility;
    private int digits;
    private double high52Wk;
    private double low52Wk;
    private double nAV;
    private double peRatio;
    private double divAmount;
    private double divYield;
    private String divDate;
    private String securityStatus;
    private double regularMarketLastPrice;
    private int regularMarketLastSize;
    private double regularMarketNetChange;
    private long regularMarketTradeTimeInLong;
    private double netPercentChangeInDouble;
    private double markChangeInDouble;
    private double markPercentChangeInDouble;
    private double regularMarketPercentChangeInDouble;
    private boolean delayed;
}
