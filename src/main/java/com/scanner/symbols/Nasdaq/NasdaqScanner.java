package com.scanner.symbols.Nasdaq;

import com.models.NasdaqSymbol;
import com.models.Symbol;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

import java.util.HashMap;

/**
 * Scans Nasdaq API endpoints to retrieve stocks, etf, futures and indexes.
 * Makes use of Database to insert data into the market database.
 */
public class NasdaqScanner {

    /**
     * Automatically scans all stocks in the Nasdaq API.
     *
     * @return a hash map that maps symbol names to their symbol metadata
     */
    public HashMap<String, NasdaqSymbol> GetStock() {
        return GetPages();
    }

    /**
     * Gets the number of stocks in the Stocks Endpoint
     *
     * @param asset the asset to retrieve count for
     * @return the number of symbols for this asset
     */
    private int GetCount(String asset) {
        if (asset.equals("stock")) {
            String url = "https://www.nasdaq.com/api/v1/screener?page=1&pageSize=1";
            HttpResponse<JsonNode> response = Unirest.get(url).asJson();
            return response.getBody().getObject().getInt("count");
        } else {
            String url = "https://api.nasdaq.com/api/screener/" + asset + "?offset=0";
            HttpResponse<JsonNode> response = Unirest.get(url).asJson();
            return response.getBody().getObject()
                    .getJSONObject("data")
                    .getJSONObject("records")
                    .getInt("totalrecords");
        }
    }

    /**
     * @return a hash map that maps symbol names to their symbol metadata
     */
    private HashMap<String, NasdaqSymbol> GetPages() {
        HashMap<String, NasdaqSymbol> symbols = new HashMap<>();
        int page = 1;
        int pageSize = 20;
        int count = GetCount("stock");
        for (page = 1; page < (count / pageSize); page += 1) {
            ParsePage(page, pageSize, symbols);
        }
        return symbols;
    }

    /**
     * Parses a page given the page and pageSize. Creates NasdaqSymbol for each row
     * that is scanned.
     *
     * @param page     the page to scan
     * @param pageSize the pageSize of the scanned page
     * @param symbols  the hash map that holds all the scanned symbols
     */
    private void ParsePage(int page, int pageSize, HashMap<String, NasdaqSymbol> symbols) {
        String url = "https://www.nasdaq.com/api/v1/screener?page=" + page + "&pageSize=" + pageSize;
        HttpResponse<JsonNode> response = Unirest.get(url).asJson();
        if (response.isSuccess()) {
            JSONArray rows = response.getBody().getObject()
                    .getJSONArray("data");
            for (Object row : rows) {
                NasdaqSymbol symbol = new NasdaqSymbol((JSONObject) row);
                symbols.put(symbol.getTicker(), symbol);
            }
        }
    }

    /**
     * Automatically scans all etfs in the Nasdaq API.
     *
     * @return a hash map that maps symbol names to their symbol metadata
     */
    public HashMap<String, Symbol> GetEtf() {
        return GetRows("etf");
    }

    /**
     * Automatically scans all indexes in the Nasdaq API.
     *
     * @return a hash map that maps symbol names to their symbol metadata
     */
    public HashMap<String, Symbol> GetIndex() {
        return GetRows("index");
    }

    /**
     * Automatically scans all futures in the Nasdaq API.
     *
     * @return a hash map that maps symbol names to their symbol metadata
     */
    public HashMap<String, Symbol> GetFutures() {
        return GetRows("futures");
    }

    /**
     * Retrieves
     *
     * @param asset the assetType to retrieve symbols for
     * @return a hash map that maps symbol names to their symbol metadata
     */
    private HashMap<String, Symbol> GetRows(String asset) {
        int count = GetCount(asset);
        int limit = 10;
        int offset = 0;
        HashMap<String, Symbol> symbols = new HashMap<>();
        for (offset = 0; offset < count; offset += limit) {
            ParseRows(asset, limit, offset, symbols);
        }
        return symbols;
    }

    /**
     * Parses a page given the asset, current count, limit, and offset.
     *
     * @param asset   the assetType to retrieve symbols for
     * @param limit   the pageSize of each request
     * @param offset  the current pageOffset
     * @param symbols the hash map that holds the symbols retrieved from scanning
     */
    private void ParseRows(String asset, int limit, int offset, HashMap<String, Symbol> symbols) {
        String url = "https://api.nasdaq.com/api/screener/" + asset + "?limit=" + limit + "&offset=" + offset;
        HttpResponse<JsonNode> response = Unirest.get(url).asJson();
        if (response.isSuccess() && response.getBody().getObject().get("data") != null) {
            JSONObject data = response.getBody().getObject()
                    .getJSONObject("data")
                    .getJSONObject("records")
                    .getJSONObject("data");
            if (data.get("rows") != null) {
                JSONArray rows = data.getJSONArray("rows");
                for (Object row : rows) {
                    String ticker = ((JSONObject) row).getString("symbol");
                    String exchange = "Nasdaq";
                    String assetType = GetAssetType(asset);
                    Symbol symbol = new Symbol(ticker, exchange, assetType);
                    symbols.put(symbol.getTicker(), symbol);
                }
            }
        }
    }

    /**
     * Formats a string to be visually appealing
     *
     * @param asset the asset to reformat
     * @return the reformatted asset or empty string if invalid asset is passed in
     */
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
