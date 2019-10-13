package com.models;

import kong.unirest.json.JSONObject;

import java.util.Date;

/**
 * An object that models a response from TDA API using the the instruments endpoint.
 */
public class FundamentalResponse {
    public FundamentalResponse(String symbol, JSONObject fundamental) {
        this.symbol = symbol;
        this.cusip = fundamental.getString("cusip");
        this.description = fundamental.getString("description");
        this.exchange = fundamental.getString("exchange");
        this.assetType = fundamental.getString("assetType");
        JSONObject f = fundamental.getJSONObject("fundamental");
        this.high52 = f.getDouble("high52");
        this.low52 = f.getDouble("low52");
        this.dividendAmount = f.getDouble("dividendAmount");
        this.dividendYield = f.getDouble("dividendYield");
        this.dividendDate = f.getString("dividendDate");
        this.peRatio = f.getDouble("peRatio");
        this.pegRatio = f.getDouble("pegRatio");
        this.pbRatio = f.getDouble("pbRatio");
        this.prRatio = f.getDouble("prRatio");
        this.pcfRatio = f.getDouble("pcfRatio");
        this.grossMarginTTM = f.getDouble("grossMarginTTM");
        this.grossMarginMRQ = f.getDouble("grossMarginMRQ");
        this.netProfitMarginTTM = f.getDouble("netProfitMarginTTM");
        this.netProfitMarginMRQ = f.getDouble("netProfitMarginMRQ");
        this.operatingMarginTTM = f.getDouble("operatingMarginTTM");
        this.operatingMarginMRQ = f.getDouble("operatingMarginMRQ");
        this.returnOnEquity = f.getDouble("returnOnEquity");
        this.returnOnInvestment = f.getDouble("returnOnInvestment");
        this.quickRatio = f.getDouble("quickRatio");
        this.interestCoverage = f.getDouble("interestCoverage");
        this.totalDebtToCapital = f.getDouble("totalDebtToCapital");
        this.ltDebtToCapital = f.getDouble("ltDebtToEquity");
        this.totalDebtToEquity = f.getDouble("totalDebtToEquity");
        this.epsTTM = f.getDouble("epsTTM");
        this.epsChangePercentTTM = f.getDouble("epsChangePercentTTM");
        this.epsChangeYear = f.getDouble("epsChangeYear");
        this.epsChange = f.getDouble("epsChange");
        this.revChangeYear = f.getDouble("revChangeYear");
        this.revChangeTTM = f.getDouble("revChangeTTM");
        this.revChangeIn = f.getDouble("revChangeIn");
        this.sharesOutstanding = f.getDouble("sharesOutstanding");
        this.marketCapFloat = f.getDouble("marketCapFloat");
        this.marketCap = f.getDouble("marketCap");
        this.bookValuePerShare = f.getDouble("bookValuePerShare");
        this.shortIntToFloat = f.getDouble("shortIntToFloat");
        this.shortIntDayToCover = f.getDouble("shortIntDayToCover");
        this.divGrowthRate3Year = f.getDouble("divGrowthRate3Year");
        this.dividendPayAmount = f.getDouble("dividendPayAmount");
        this.dividendPayDate = f.getString("dividendPayDate");
        this.beta = f.getDouble("beta");
        this.vol1DayAvg = f.getDouble("vol1DayAvg");
        this.vol10DayAvg = f.getDouble("vol10DayAvg");
        this.vol3MonthAvg = f.getDouble("vol3MonthAvg");
    }

    private String symbol;
    private String cusip;
    private String description;
    private String exchange;
    private String assetType;
    private double high52;
    private double low52;
    private double dividendAmount;
    private double dividendYield;
    private String dividendDate;
    private double peRatio;
    private double pegRatio;
    private double pbRatio;
    private double prRatio;
    private double pcfRatio;
    private double grossMarginTTM;
    private double grossMarginMRQ;
    private double netProfitMarginTTM;
    private double netProfitMarginMRQ;
    private double operatingMarginTTM;
    private double operatingMarginMRQ;
    private double returnOnEquity;
    private double returnOnAssets;
    private double returnOnInvestment;
    private double quickRatio;
    private double currentRatio;
    private double interestCoverage;
    private double totalDebtToCapital;
    private double ltDebtToCapital;
    private double totalDebtToEquity;
    private double epsTTM;
    private double epsChangePercentTTM;
    private double epsChangeYear;
    private double epsChange;
    private double revChangeYear;
    private double revChangeTTM;
    private double revChangeIn;
    private double sharesOutstanding;
    private double marketCapFloat;
    private double marketCap;
    private double bookValuePerShare;
    private double shortIntToFloat;
    private double shortIntDayToCover;
    private double divGrowthRate3Year;
    private double dividendPayAmount;
    private String dividendPayDate;
    private double beta;
    private double vol1DayAvg;
    private double vol10DayAvg;
    private double vol3MonthAvg;
    private Date lastUpdated;

    public String getSymbol() {
        return symbol;
    }

    public String getCusip() {
        return cusip;
    }

    public String getDescription() {
        return description;
    }

    public String getExchange() {
        return exchange;
    }

    public String getAssetType() {
        return assetType;
    }

    public double getHigh52() {
        return high52;
    }

    public double getLow52() {
        return low52;
    }

    public double getDividendAmount() {
        return dividendAmount;
    }

    public double getDividendYield() {
        return dividendYield;
    }

    public String getDividendDate() {
        return dividendDate;
    }

    public double getPeRatio() {
        return peRatio;
    }

    public double getPegRatio() {
        return pegRatio;
    }

    public double getPbRatio() {
        return pbRatio;
    }

    public double getPrRatio() {
        return prRatio;
    }

    public double getPcfRatio() {
        return pcfRatio;
    }

    public double getGrossMarginTTM() {
        return grossMarginTTM;
    }

    public double getGrossMarginMRQ() {
        return grossMarginMRQ;
    }

    public double getNetProfitMarginTTM() {
        return netProfitMarginTTM;
    }

    public double getNetProfitMarginMRQ() {
        return netProfitMarginMRQ;
    }

    public double getOperatingMarginTTM() {
        return operatingMarginTTM;
    }

    public double getOperatingMarginMRQ() {
        return operatingMarginMRQ;
    }

    public double getReturnOnEquity() {
        return returnOnEquity;
    }

    public double getReturnOnAssets() {
        return returnOnAssets;
    }

    public double getReturnOnInvestment() {
        return returnOnInvestment;
    }

    public double getQuickRatio() {
        return quickRatio;
    }

    public double getCurrentRatio() {
        return currentRatio;
    }

    public double getInterestCoverage() {
        return interestCoverage;
    }

    public double getTotalDebtToCapital() {
        return totalDebtToCapital;
    }

    public double getLtDebtToCapital() {
        return ltDebtToCapital;
    }

    public double getTotalDebtToEquity() {
        return totalDebtToEquity;
    }

    public double getEpsTTM() {
        return epsTTM;
    }

    public double getEpsChangePercentTTM() {
        return epsChangePercentTTM;
    }

    public double getEpsChangeYear() {
        return epsChangeYear;
    }

    public double getEpsChange() {
        return epsChange;
    }

    public double getRevChangeYear() {
        return revChangeYear;
    }

    public double getRevChangeTTM() {
        return revChangeTTM;
    }

    public double getRevChangeIn() {
        return revChangeIn;
    }

    public double getSharesOutstanding() {
        return sharesOutstanding;
    }

    public double getMarketCap() {
        return marketCap;
    }

    public double getBookValuePerShare() {
        return bookValuePerShare;
    }

    public double getShortIntToFloat() {
        return shortIntToFloat;
    }

    public double getShortIntDayToCover() {
        return shortIntDayToCover;
    }

    public double getDivGrowthRate3Year() {
        return divGrowthRate3Year;
    }

    public double getDividendPayAmount() {
        return dividendPayAmount;
    }

    public String getDividendPayDate() {
        return dividendPayDate;
    }

    public double getBeta() {
        return beta;
    }

    public double getVol1DayAvg() {
        return vol1DayAvg;
    }

    public double getVol10DayAvg() {
        return vol10DayAvg;
    }

    public double getVol3MonthAvg() {
        return vol3MonthAvg;
    }
}
