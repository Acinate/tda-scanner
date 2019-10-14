package com.scanner.symbols.Nasdaq;

import com.models.responses.NasdaqSymbol;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

import java.util.HashMap;

/**
 * This object contains all the methods responsible for retrieving all stock symbols
 * listed on the Nasdaq API.
 */
class NasdaqStockScanner {
    /**
     * Automatically scans all stocks in the Nasdaq API.
     *
     * @return a hash map that maps symbol names to their symbol metadata
     */
    HashMap<String, NasdaqSymbol> getStocks() {
        return getPages();
    }

    /**
     * Gets the number of stocks in the Stocks Endpoint
     *
     * @return the number of symbols for all listed stocks
     */
    private int getCount() {
        String url = "https://www.nasdaq.com/api/v1/screener?page=1&pageSize=1";
        HttpResponse<JsonNode> response = Unirest.get(url).asJson();
        return response.getBody().getObject().getInt("count");
    }

    /**
     * @return a hash map that maps symbol names to their symbol metadata
     */
    private HashMap<String, NasdaqSymbol> getPages() {
        HashMap<String, NasdaqSymbol> symbols = new HashMap<>();
        int page = 1;
        int pageSize = 20;
        int count = getCount();
        for (page = 1; page <= (count / pageSize); page += 1) {
            parsePage(page, pageSize, symbols);
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
    private void parsePage(int page, int pageSize, HashMap<String, NasdaqSymbol> symbols) {
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
}
