package com.scanner.symbols.Nasdaq;

import com.models.Symbol;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

import java.util.HashMap;

public class NasdaqScanner {
    public HashMap<String, Symbol> GetStock() {
        return GetPages();
    }
    public HashMap<String, Symbol> GetEtf() {
        return GetRows("etf");
    }
    public HashMap<String, Symbol> GetIndex() {
        return GetRows("index");
    }
    public HashMap<String, Symbol> GetFutures() {
        return GetRows("futures");
    }
    private int GetCount(String asset) {
        if (asset.equals("stock")) {
            String url = "https://www.nasdaq.com/api/v1/screener?page=1&pageSize=1";
            HttpResponse<JsonNode>  response = Unirest.get(url).asJson();
            return response.getBody().getObject().getInt("count");
        } else {
            String url = "https://api.nasdaq.com/api/screener/"+asset+"?offset=0";
            HttpResponse<JsonNode> response = Unirest.get(url).asJson();
            return response.getBody().getObject()
                    .getJSONObject("data")
                    .getJSONObject("records")
                    .getInt("totalrecords");
        }
    }
    private HashMap<String, Symbol> GetPages() {
        HashMap<String, Symbol> symbols = new HashMap<>();
        int page = 1;
        int pageSize = 10;
        int count = GetCount("stock");
        for (page = 1; page < (count / pageSize); page += 1) {
            symbols.putAll(ParsePage(page,pageSize));
        }
        return symbols;
    }
    private HashMap<String, Symbol> ParsePage(int page, int pageSize) {
        HashMap<String, Symbol> symbols = new HashMap<>();
        String url = "https://www.nasdaq.com/api/v1/screener?page="+page+"&pageSize="+pageSize;
        HttpResponse<JsonNode> response = Unirest.get(url).asJson();
        if (response.isSuccess()) {
            JSONArray rows = response.getBody().getObject()
                    .getJSONArray("data");

            for (Object row : rows) {
                String ticker = ((JSONObject)row).getString("ticker");
                String exchange = "Nasdaq";
                String assetType = "Stock";
                Symbol symbol = new Symbol(ticker, exchange, assetType);
                symbols.put(symbol.getTicker(), symbol);
            }
        }
        return symbols;
    }
    private HashMap<String, Symbol> GetRows(String asset) {
        int count = GetCount(asset);
        int limit = 10;
        int offset = 0;
        HashMap<String, Symbol> symbols = new HashMap<>();
        for (offset = 0; offset < count; offset += limit) {
            symbols.putAll(ParseRows(asset, count, limit, offset));
        }
        return symbols;
    }
    private HashMap<String, Symbol> ParseRows(String asset, int count, int limit, int offset) {
        HashMap<String, Symbol> symbols = new HashMap<>();
        String url = "https://api.nasdaq.com/api/screener/"+asset+"?limit="+limit+"&offset="+offset;
        HttpResponse<JsonNode> response = Unirest.get(url).asJson();
        if (response.isSuccess() && response.getBody().getObject().get("data") != null) {
            JSONObject data = response.getBody().getObject()
                    .getJSONObject("data")
                    .getJSONObject("records")
                    .getJSONObject("data");
            if (data.get("rows") != null) {
                JSONArray rows = data.getJSONArray("rows");
                for (Object row : rows) {
                    String ticker = ((JSONObject)row).getString("symbol");
                    String exchange = "Nasdaq";
                    String assetType = GetAssetType(asset);
                    Symbol symbol = new Symbol(ticker, exchange, assetType);
                    symbols.put(symbol.getTicker(), symbol);
                }
            }
        }
        return symbols;
    }
    private String GetAssetType(String asset) {
        switch (asset) {
            case "futures":
                return "Future";
            case "index":
                return "Index";
            case "etf":
                return "ETF";
            case "stock":
                return "Stock";
            default:
                return "";
        }
    }
}
