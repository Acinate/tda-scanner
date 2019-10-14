package com.scanner.symbols.Nasdaq;

import com.models.responses.NasdaqSummary;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

/**
 * This object was built to scan the Nasdaq Summary API Endpoint and return
 * a NasdaqSummary object.
 * NOTE: This object is not complete, there are many fields the original endpoint
 * returns that are not supported by this object.
 */
class NasdaqSummaryScanner {
    /**
     * Returns a nasdaq summary for a given symbol and assetType
     *
     * @param symbol    the symbol to retrieve summary for
     * @param assetType the assetType of the symbol
     * @return a NasdaqSummary Object
     */
    NasdaqSummary getSummary(String symbol, String assetType) {
        if (assetType.equals("Stock")) {
            return getSummaryForStock(symbol);
        }
        return null;
    }

    /**
     * Returns a nasdaq summary for a given symbol
     *
     * @param symbol the symbol to retrieve summary for
     * @return a NasdaqSummary Object
     */
    private NasdaqSummary getSummaryForStock(String symbol) {
        String url = "https://api.nasdaq.com/api/quote/" + symbol + "/summary?assetclass=stocks";
        HttpResponse<JsonNode> response = Unirest.get(url).asJson();

        if (response.getBody().getObject().get("data") != null) {
            return new NasdaqSummary(response.getBody().getObject().getJSONObject("data"));
        }
        return null;
    }
}
