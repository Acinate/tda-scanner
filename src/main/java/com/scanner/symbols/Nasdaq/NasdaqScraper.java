package com.scanner.symbols.Nasdaq;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

import java.util.HashMap;

public class NasdaqScraper {
    private int GetStockCount() {
        String url = "https://www.nasdaq.com/api/v1/screener?page=1&pageSize=20";
        HttpResponse<JsonNode>  response = Unirest.get(url).asJson();
        return response.getBody().getObject().getInt("count");
    }
    public HashMap<String, NasdaqStock> ScanStocks() {

        int page = 1;
        int pageSize = 20;
        int count = GetStockCount();

        HashMap<String, NasdaqStock> stocks = new HashMap<>();
        for (page=1; (page - 1) * pageSize <= count; page++) {
            String url = "https://www.nasdaq.com/api/v1/screener?page="+page+"&pageSize="+pageSize;
            HttpResponse<JsonNode> response = Unirest.get(url).asJson();
            JSONArray data = response.getBody().getObject().getJSONArray("data");
            if (data.length() <= 0) {
                System.out.println("Array length is 0!");
            }
            for (Object company : data) {
                NasdaqStock stock = new NasdaqStock((JSONObject)company);
                stocks.put(stock.getTicker(), stock);
            }
            System.out.println(page);
        }

        return stocks;
    }
    private int GetEtfCount() {
        String url = "https://api.nasdaq.com/api/screener/etf?limit=20&offset=0";
        HttpResponse<JsonNode> response =  Unirest.get(url).asJson();
        return response.getBody().getObject().getJSONObject("data")
                .getJSONObject("records")
                .getInt("totalrecords");
    }
    public HashMap<String, NasdaqEtf> ScanETFs() {
        int offset =  0;
        int limit = 20;
        int count = GetEtfCount();

        HashMap<String, NasdaqEtf> etfs = new HashMap<>();
        for (offset = 0; offset <= count - limit; offset += 20) {
            String url = "https://api.nasdaq.com/api/screener/etf?limit="+limit+"&offset="+offset;
            HttpResponse<JsonNode> response  = Unirest.get(url).asJson();

            JSONArray rows = response.getBody().getObject()
                    .getJSONObject("data")
                    .getJSONObject("records")
                    .getJSONObject("data")
                    .getJSONArray("rows");

            for (Object row : rows) {
                NasdaqEtf etf = new NasdaqEtf((JSONObject)row);
                etfs.put(etf.getTicker(), etf);
            }
            System.out.println(offset);
        }
        return etfs;
    }
    private int GetIndexCount() {
        String url = "https://api.nasdaq.com/api/screener/index?limit=20&offset=0";
        HttpResponse<JsonNode> response = Unirest.get(url).asJson();
        return response.getBody().getObject()
                .getJSONObject("data")
                .getJSONObject("records")
                .getInt("totalrecords");
    }
    public HashMap<String, NasdaqIndex> ScanIndexes() {

        int limit = 20;
        int offset = 0;
        int count = GetIndexCount();

        HashMap<String, NasdaqIndex> indexes = new HashMap<>();
        for (offset = 0; offset <= count; offset += 20) {
            String url = "https://api.nasdaq.com/api/screener/index?limit="+limit+"&offset="+offset;
            HttpResponse<JsonNode> response = Unirest.get(url).asJson();
            JSONArray rows = response.getBody().getObject()
                    .getJSONObject("data")
                    .getJSONObject("records")
                    .getJSONObject("data")
                    .getJSONArray("rows");

            for (Object row : rows) {
                NasdaqIndex index = new NasdaqIndex((JSONObject)row);
                indexes.put(index.getTicker(), index);
            }
            System.out.println(offset);
        }
        return indexes;
    }
    private int GetFutureCount() {
        String url = "https://api.nasdaq.com/api/screener/futures?limit=20&offset=0";
        HttpResponse<JsonNode> response = Unirest.get(url).asJson();
        return response.getBody().getObject()
                .getJSONObject("data")
                .getJSONObject("records")
                .getInt("totalrecords");
    }
    public HashMap<String, NasdaqIndex> ScanFutures() {

        int limit = 20;
        int offset = 0;
        int count = GetIndexCount();

        HashMap<String, NasdaqIndex> indexes = new HashMap<>();
        for (offset = 0; offset <= count; offset += 20) {
            String url = "https://api.nasdaq.com/api/screener/futures?limit="+limit+"&offset="+offset;
            HttpResponse<JsonNode> response = Unirest.get(url).asJson();
            JSONArray rows = response.getBody().getObject()
                    .getJSONObject("data")
                    .getJSONObject("records")
                    .getJSONObject("data")
                    .getJSONArray("rows");

            for (Object row : rows) {
                NasdaqIndex index = new NasdaqIndex((JSONObject)row);
                indexes.put(index.getTicker(), index);
            }
            System.out.println(offset);
        }
        return indexes;
    }
}
