package com.api;

import com.models.responses.TdaFundamental;
import com.models.History;
import com.models.Quote;
import com.util.DateParser;
import com.auth.Auth;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONException;
import kong.unirest.json.JSONObject;

import java.text.ParseException;
import java.util.Date;

public class TDA {
    private Auth auth = new Auth();
    private DateParser dateParser = new DateParser();

    public Quote getQuote(String symbol) {
        String url = "https://api.tdameritrade.com/v1/marketdata/"+symbol+"/quotes";
        HttpResponse<JsonNode> response = Unirest.get(url)
                .queryString("apiKey", auth.getClientId())
                .header("Authorization", "Bearer " + auth.getAccessToken())
                .asJson();

        return new Quote(response.getBody().getObject().getJSONObject(symbol));
    }

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

    public History getHistory(String symbol) throws BadRequestException, ParseException, TimeSpanException {
        return getHistory(symbol, null, null, null, null, false);
    }

    public History getHistory(String symbol, String startDate, String endDate)
            throws BadRequestException, ParseException, TimeSpanException {
        return getHistory(symbol, startDate, endDate, null, null, false);
    }

    public History getHistory(String symbol, String startDate, String endDate, String period, String frequency)
            throws BadRequestException, ParseException, TimeSpanException {
        return getHistory(symbol, startDate, endDate, period, frequency, false);
    }

    public History getHistory(String symbol, String startDate, String endDate, String period, String frequency,
                               boolean extended) throws BadRequestException, ParseException, TimeSpanException {
        /* Parse Period Strings into Periods */
        TimeSpan timeSpan = null;
        if (period == null || frequency == null) {
            timeSpan = new TimeSpan("1Y", "1D");
        } else {
            timeSpan = parseTimeSpan(period, frequency);
        }
        /* Parse Date Strings into Dates */
        Date start = null;
        Date end = null;
        if (startDate == null || endDate == null) {
            /* Set start date to exactly one year ago */
            long yearInMillis = Long.parseLong("31556952000");
            start = new Date(System.currentTimeMillis()-yearInMillis);
            /* Set end date to today*/
            end = new Date(System.currentTimeMillis());
        } else {
            start = dateParser.parseDate(startDate);
            end = dateParser.parseDate(endDate);
        }
        String url = "https://api.tdameritrade.com/v1/marketdata/"+symbol+"/pricehistory";
        HttpResponse<JsonNode> response = Unirest.get(url)
                .queryString("apiKey", auth.getClientId())
                .queryString("period",timeSpan.period)
                .queryString("periodType", timeSpan.periodType)
                .queryString("frequency",timeSpan.frequency)
                .queryString("frequencyType",timeSpan.frequencyType)
                .queryString("startDate",start.getTime())
                .queryString("endDate",end.getTime())
                .queryString("needExtendedHoursData",extended)
                .header("Authorization", "Bearer " + auth.getAccessToken())
                .asJson();

        checkBadRequest(response);

        JSONObject candles = response.getBody().getObject();

        return new History(candles);
    }

    private TimeSpan parseTimeSpan(String period, String frequency) throws TimeSpanException {
        TimeSpan timespan = new TimeSpan(period, frequency);
        if (timespan.period == null) {
            throw new TimeSpanException("Invalid Time Span Combination");
        } else {
            return timespan;
        }
    }

    private void checkBadRequest(HttpResponse<JsonNode> response) throws BadRequestException {
        Object error = null;
        try {
            error = response.getBody().getObject().get("error");
        } catch (JSONException ignored) { }
        if (error != null && error.equals("Bad request.")) {
            throw new BadRequestException("Invalid API Call");
        }
    }
}
