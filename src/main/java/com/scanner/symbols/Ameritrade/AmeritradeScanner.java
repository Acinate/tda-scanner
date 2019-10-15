package com.scanner.symbols.Ameritrade;

import com.auth.Auth;
import com.models.responses.TdaFundamental;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;

/**
 * AmeritradeScanner is responsible for scanning TDAmeritrade endpoints to retrieve stock data
 */
public class AmeritradeScanner {
    /**
     * @param symbol The symbol used to search an asset's fundamentals.
     * @return Returns a fundamental response if the symbol is valid, otherwise will return null.
     */
    public TdaFundamental getFundamental(String symbol) {
        String url = "https://api.tdameritrade.com/v1/instruments";
        HttpResponse<JsonNode> response = Unirest.get(url)
                .queryString("apiKey", auth.getClientId())
                .queryString("symbol", symbol)
                .queryString("projection", "fundamental")
                .header("Authorization", "Bearer " + auth.getAccessToken())
                .asJson();
        if (response.getBody().getObject().has(symbol)) {
            JSONObject object = response.getBody().getObject().getJSONObject(symbol);
            return new TdaFundamental(symbol, object);
        }
        return null;
    }

    private Auth auth = new Auth();
}
