package com.database;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.models.Candle;
import com.models.History;
import kong.unirest.json.JSONObject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DatabaseOld {

    public DatabaseOld() {
        basePath = "/Users/Jason/Projects/Java/StockScanner/src/main/resources/json/data/";
    }

    public DatabaseOld(boolean testing) {
        if (testing) {
            basePath = "/Users/Jason/Projects/Java/StockScanner/src/test/resources/json/data/";
        } else {
            basePath = "/Users/Jason/Projects/Java/StockScanner/src/main/resources/json/data/";
        }
    }

    /* Base path to data directory */
    private Gson gson = new Gson();
    private String basePath = null;

    public void SaveHistoryToFile(String symbol, History history, Date date) throws IOException {
        String fileURI = basePath + "/" + getHistoryFileURI(symbol, date);
        String historyJSON = gson.toJson(history.getCandles());
        Path filePath = Paths.get(basePath + "/" + getHistoryFilePath(symbol, date));
        if (!Files.exists(filePath)) {
            Files.createDirectories(filePath);
        }
        FileWriter writer = new FileWriter(fileURI);
        writer.write(historyJSON);
        writer.flush();
        writer.close();
    }

    public History readHistoryFromFile(String symbol, Date date) throws IOException {
        String fileURI = basePath + getHistoryFileURI(symbol, date);
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonHistory = jsonParser.parse(new FileReader(fileURI)).getAsJsonArray();
        ArrayList<Candle> candles = new ArrayList<>();
        for (JsonElement element : jsonHistory) {
            Candle candle = new Candle(element.getAsJsonObject());
            candles.add(candle);
        }
        return new History(candles);
    }

    public String getHistoryFileURI(String symbol, Date date) {
        return getHistoryFilePath(symbol, date) + "/" + getHistoryFileName(symbol, date);
    }

    public String getHistoryFilePath(String symbol, Date date) {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/New_York"));
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1; // months start at 0, not 1
        String formattedMonth = "";
        String formattedDay = "";
        if (month < 10) {
            formattedMonth = String.format("%02d", month);
        } else {
            formattedMonth = String.format("%d", month);
        }

        return year + "/" + symbol + "/" + formattedMonth;
    }

    public String getHistoryFileName(String symbol, Date date) {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/New_York"));
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1; // months start at 0, not 1
        int day = cal.get(Calendar.DAY_OF_MONTH);
        String formattedMonth = "";
        String formattedDay = "";
        if (month < 10) {
            formattedMonth = String.format("%02d", month);
        } else {
            formattedMonth = String.format("%d", month);
        }
        if (day < 10) {
            formattedDay = String.format("%02d", day);
        } else {
            formattedDay = String.format("%d", day);
        }
        return formattedDay + "-" + formattedMonth + "-" + year + ".json";
    }
}
