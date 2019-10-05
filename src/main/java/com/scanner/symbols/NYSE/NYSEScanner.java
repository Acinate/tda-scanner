package com.scanner.symbols.NYSE;

import com.google.gson.Gson;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

import java.util.HashMap;

public class NYSEScanner {

    Gson gson = new Gson();
    String url = "https://www.nyse.com/api/quotes/filter";
    String cookie = "__cfduid=db19a0ddf9582bcdef1f6be3737b485261567429706; _ga=GA1.2.194448053.1567429708; ICE_notification=462751242.56425.0000; _gid=GA1.2.1259522087.1570200589; cookieDisclaimer=true; JSESSIONID=3F718C6C5585F83454EA0F34A82B14CE; _gat_UA-97108014-2=1; ICE=!T2B1ePBlCW/iprA+xkwKbNFUCBVLalFX+MMyuO6Wf+QIZPe0r7jrhOC5g0vMsLPmNVPfmHOmtOcyRg==; TS01ebd031=0100e6d49590e0af3beef5f49cb7629d623ed0c762f64010911cc51daf0d33dfa9ee93f618db82ac70333c16a79fe444b5cfea96b519cb3787ec1b335070360a3c515d5471e7723e493b07408d3aec21021ef20687316ee069fe56eb8bd41bc77a98f692d2";

    enum ASSET_TYPE { EQUITY, EXCHANGE_TRADED_FUND, INDEX };

    /* assetType = {"EQUITY", "EXCHANGE_TRADED_FUND", "INDEX"} */
    private String GetBody(String assetType, int page, int pageSize) {
        NYSEBody body = new NYSEBody();

        body.instrumentType = assetType;
        body.pageNumber =  page;
        body.sortColumn = "NORMALIZED_TICKER";
        body.sortOrder = "ASC";
        body.maxResultsPerPage = 10;
        body.filterToken = "";

        return gson.toJson(body);
    }

    private int GetStockCount() {
        HttpResponse<JsonNode> response = Unirest.post(url)
                .body(GetBody("EQUITY",1, 10))
                .header("Cookie", cookie)
                .header("Content-Type", "application/json")
                .asJson();

        JSONObject stock = (JSONObject) response.getBody().getArray().get(0);

        return stock.getInt("total");
    }

    public HashMap<String, NYSEStock> GetStocks() {
        int count = GetStockCount();
        int pageSize = 10;
        int page = 1;

        HashMap<String, NYSEStock> stocks = new HashMap<>();
        for (page = 1; page <= (count / pageSize + 1); page++) {
            HttpResponse<JsonNode> response = Unirest.post(url)
                    .body(GetBody("EQUITY",page,pageSize))
                    .header("Cookie", cookie)
                    .header("Content-Type", "application/json")
                    .asJson();

            JSONArray results = response.getBody().getArray();

            for (Object result : results) {
                NYSEStock s = new NYSEStock((JSONObject) result);
                stocks.put(s.getTicker(), s);
            }
        System.out.println(page);
        }
        return stocks;
    }

    private int GetETFCount() {
        HttpResponse<JsonNode> response = Unirest.post(url)
                .body(GetBody("EXCHANGE_TRADED_FUND",1, 10))
                .header("Cookie", cookie)
                .header("Content-Type", "application/json")
                .asJson();

        JSONObject stock = (JSONObject) response.getBody().getArray().get(0);

        return stock.getInt("total");
    }
    public HashMap<String, NYSEStock> GetETFs() {
        int count = GetETFCount();
        int pageSize = 10;
        int page = 1;

        HashMap<String, NYSEStock> stocks = new HashMap<>();
        for (page = 1; page <= (count / pageSize + 1); page++) {
            HttpResponse<JsonNode> response = Unirest.post(url)
                    .body(GetBody("EXCHANGE_TRADED_FUND",page,pageSize))
                    .header("Cookie", cookie)
                    .header("Content-Type", "application/json")
                    .asJson();

            JSONArray results = response.getBody().getArray();

            for (Object result : results) {
                NYSEStock s = new NYSEStock((JSONObject) result);
                stocks.put(s.getTicker(), s);
            }
            System.out.println(page);
        }
        return stocks;
    }

    private int GetIndexCount() {
        HttpResponse<JsonNode> response = Unirest.post(url)
                .body(GetBody("INDEX",1, 10))
                .header("Cookie", cookie)
                .header("Content-Type", "application/json")
                .asJson();

        JSONObject stock = (JSONObject) response.getBody().getArray().get(0);

        return stock.getInt("total");
    }
    public HashMap<String, NYSEStock> GetIndexes() {
        int count = GetIndexCount();
        int pageSize = 10;
        int page = 1;

        HashMap<String, NYSEStock> stocks = new HashMap<>();
        for (page = 1; page <= (count / pageSize + 1); page++) {
            HttpResponse<JsonNode> response = Unirest.post(url)
                    .body(GetBody("INDEX",page,pageSize))
                    .header("Cookie", cookie)
                    .header("Content-Type", "application/json")
                    .asJson();

            JSONArray results = response.getBody().getArray();

            for (Object result : results) {
                NYSEStock s = new NYSEStock((JSONObject) result);
                stocks.put(s.getTicker(), s);
            }
            System.out.println(page);
        }
        return stocks;
    }
}
