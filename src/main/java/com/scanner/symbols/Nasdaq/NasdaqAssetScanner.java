package com.scanner.symbols.Nasdaq;

import com.models.SymbolOld;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

import java.util.HashMap;

/**
 * This object contains all the methods responsible for retrieving all asset symbols
 * listed on the Nasdaq API.
 */
class NasdaqAssetScanner {
    /**
     * Automatically scans all etfs in the Nasdaq API.
     *
     * @return a hash map that maps symbol names to their symbol metadata
     */
    HashMap<String, SymbolOld> getEtf() {
        return getRows("etf");
    }

    /**
     * Automatically scans all indexes in the Nasdaq API.
     *
     * @return a hash map that maps symbol names to their symbol metadata
     */
    HashMap<String, SymbolOld> getIndex() {
        return getRows("index");
    }

    /**
     * Automatically scans all futures in the Nasdaq API.
     *
     * @return a hash map that maps symbol names to their symbol metadata
     */
    HashMap<String, SymbolOld> getFutures() {
        return getRows("futures");
    }

    /**
     * Gets the number of stocks in the Stocks Endpoint
     *
     * @param asset the asset to retrieve count for
     * @return the number of symbols for this asset
     */
    private int getCount(String asset) {
        String url = "https://api.nasdaq.com/api/screener/" + asset + "?offset=0";
        HttpResponse<JsonNode> response = Unirest.get(url).asJson();
        return response.getBody().getObject()
                .getJSONObject("data")
                .getJSONObject("records")
                .getInt("totalrecords");
    }

    /**
     * Retrieves
     *
     * @param asset the assetType to retrieve symbols for
     * @return a hash map that maps symbol names to their symbol metadata
     */
    private HashMap<String, SymbolOld> getRows(String asset) {
        int count = getCount(asset);
        int limit = 10;
        int offset = 0;
        HashMap<String, SymbolOld> symbols = new HashMap<>();
        for (offset = 0; offset < count; offset += limit) {
            parseRows(asset, limit, offset, symbols);
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
    private void parseRows(String asset, int limit, int offset, HashMap<String, SymbolOld> symbols) {
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
                    String assetType = getAssetType(asset);
                    SymbolOld symbol = new SymbolOld(ticker, exchange, assetType);
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
    private String getAssetType(String asset) {
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
