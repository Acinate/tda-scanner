package com.models.database;

import com.models.responses.TdaFundamental;

/**
 * Holds Fundamental information for Symbol. Uses data from TdaFundamental.
 */
public class Fundamental {

    public void applyDataSet(TdaFundamental dataset) {
        if (dataset != null) {
            high52 = dataset.getHigh52();
            low52 = dataset.getLow52();
            sharesOutstanding = dataset.getSharesOutstanding();
            marketCapFloat = dataset.getMarketCapFloat();
            marketCap = dataset.getMarketCap();
            bookValuePerShare = dataset.getBookValuePerShare();
            beta = dataset.getBeta();
            vol1DayAvg = dataset.getVol1DayAvg();
            vol10DayAvg = dataset.getVol10DayAvg();
            vol3MonthAvg = dataset.getVol3MonthAvg();
        }
    }

    private double high52;
    private double low52;
    private double sharesOutstanding;
    private double marketCapFloat;
    private double marketCap;
    private double bookValuePerShare;
    private double shortIntToFloat; // Useless Data Column (Always 0)
    private double shortIntDayToCover; // Useless Data Column (Always 0)
    private double beta;
    private double vol1DayAvg;
    private double vol10DayAvg;
    private double vol3MonthAvg;

    public double getHigh52() {
        return high52;
    }

    public double getLow52() {
        return low52;
    }

    public double getSharesOutstanding() {
        return sharesOutstanding;
    }

    public double getMarketCapFloat() {
        return marketCapFloat;
    }

    public double getMarketCap() {
        return marketCap;
    }

    public double getBookValuePerShare() {
        return bookValuePerShare;
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
