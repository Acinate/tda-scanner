package com.scanner.symbols.NYSE;

import com.google.gson.Gson;
import com.models.Symbol;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class NYSEScanner {

    public HashMap<String, Symbol> GetAllSymbols() {
        return GetPages("");
    }

    public HashMap<String, Symbol> GetStockSymbols() {
        return GetPages("stock");
    }

    public HashMap<String, Symbol> GetIndexSymbols() {
        return GetPages("index");
    }

    public HashMap<String, Symbol> GetEtfSymbols() {
        return GetPages("etf");
    }

    private HashMap<String, Symbol> GetPages(String asset) {
        HashMap<String, Symbol> symbols = new HashMap<>();
        int count = GetCount(asset);
        int page = 1;
        int pageSize = 10;
        for (page = 1; page < (count / pageSize); page++) {
            symbols.putAll(GetPage(asset, page, pageSize));
        }
        return symbols;
    }

    private HashMap<String, Symbol> GetPage(String asset, int page, int pageSize) {
        String url = "https://www.nyse.com/api/quotes/filter";
        HashMap<String, Symbol> symbols = new HashMap<>();
        HttpResponse<JsonNode> response = Unirest.post(url)
                .body(GetBody(GetAssetType(asset), page, pageSize))
                .headers(GetHeaders())
                .asJson();

        if (response.isSuccess()) {
            JSONArray rows = response.getBody().getArray();
            for (Object row : rows) {
                String ticker = ((JSONObject) row).getString("symbolTicker");
                String instrumentType = ((JSONObject) row).getString("instrumentType");
                String assetType = ParseInstrumentType(instrumentType);
                String exchange = "NYSE";
                Symbol symbol = new Symbol(ticker, exchange, ParseInstrumentType(assetType));
                symbols.put(symbol.getTicker(), symbol);
            }
        }
        return symbols;
    }

    private int GetCount(String asset) {
        String url = "https://www.nyse.com/api/quotes/filter";
        String assetType = GetAssetType(asset);
        HttpResponse<JsonNode> response = Unirest.post(url)
                .body(GetBody(assetType, 1, 1))
                .headers(GetHeaders())
                .asJson();
        if (response.isSuccess()) {
            if (response.getBody().getArray().length() > 0) {
                return ((JSONObject) response.getBody().getArray().get(0)).getInt("total");
            }
        }
        return -1;
    }

    private String GetBody(String assetType, int page, int pageSize) {
        Gson gson = new Gson();
        NYSEBody body = new NYSEBody();

        body.instrumentType = assetType;
        body.pageNumber =  page;
        body.sortColumn = "NORMALIZED_TICKER";
        body.sortOrder = "ASC";
        body.maxResultsPerPage = 10;
        body.filterToken = "";

        return gson.toJson(body);
    }

    private String GetAssetType(String asset) {
        switch (asset) {
            case "stock":
                return "EQUITY";
            case "etf":
                return "EXCHANGE_TRADED_FUND";
            case "index":
                return "INDEX";
            default:
                return "INVALID_ASSET_TYPE";
        }
    }

    private String ParseInstrumentType(String instrument) {
        switch (instrument) {
            case "COMMON_STOCK":
                return "Stock";
            case "INDEX":
                return "Index";
            case "CONVERTIBLE_BOND":
                return "Bond";
            case "PREFERRED_STOCK":
                return "Preferred Stock";
            case "EXCHANGE_TRADED_FUND":
                return "ETF";
            default:
                return instrument;
        }
    }

    private Map<String, String> GetHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Cookie", GetCookie());
        headers.put("Content-Type", "application/json");
        return headers;
    }

    private String GetCookie() {
        return "__cfduid=db19a0ddf9582bcdef1f6be3737b485261567429706; _ga=GA1.2.194448053.1567429708; " +
                "ICE_notification=462751242.56425.0000; _gid=GA1.2.1259522087.1570200589; cookieDisclaimer=true; " +
                "JSESSIONID=3F718C6C5585F83454EA0F34A82B14CE; _gat_UA-97108014-2=1; ICE=!T2B1ePBlCW/iprA+xkwKbNFUCBVLalFX+MMyuO6Wf+QIZPe0r7jrhOC5g0vMsLPmNVPfmHOmtOcyRg==; TS01ebd031=0100e6d49590e0af3beef5f49cb7629d623ed0c762f64010911cc51daf0d33dfa9ee93f618db82ac70333c16a79fe444b5cfea96b519cb3787ec1b335070360a3c515d5471e7723e493b07408d3aec21021ef20687316ee069fe56eb8bd41bc77a98f692d2";
    }
}

class NYSEBody {
    String instrumentType;
    int pageNumber;
    String sortColumn;
    String sortOrder;
    int maxResultsPerPage;
    String filterToken;
}
