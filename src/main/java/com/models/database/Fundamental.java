package com.models.database;

/**
 * Holds Fundamental information for Symbol. Uses data from TdaFundamental.
 */
public class Fundamental {
    private double high52;
    private double low52;
    private double sharesOutstanding;
    private double marketCapFloat;
    private double marketCap;
    private double bookValuePerShare;
    private double shortIntToFloat;
    private double shortIntDayToCover;
    private double beta;
    private double vol1DayAvg;
    private double vol10DayAvg;
    private double vol3MonthAvg;

    public double getHigh52() {
        return high52;
    }

    public void setHigh52(double high52) {
        this.high52 = high52;
    }

    public double getLow52() {
        return low52;
    }

    public void setLow52(double low52) {
        this.low52 = low52;
    }

    public double getSharesOutstanding() {
        return sharesOutstanding;
    }

    public void setSharesOutstanding(double sharesOutstanding) {
        this.sharesOutstanding = sharesOutstanding;
    }

    public double getMarketCapFloat() {
        return marketCapFloat;
    }

    public void setMarketCapFloat(double marketCapFloat) {
        this.marketCapFloat = marketCapFloat;
    }

    public double getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(double marketCap) {
        this.marketCap = marketCap;
    }

    public double getBookValuePerShare() {
        return bookValuePerShare;
    }

    public void setBookValuePerShare(double bookValuePerShare) {
        this.bookValuePerShare = bookValuePerShare;
    }

    public double getShortIntToFloat() {
        return shortIntToFloat;
    }

    public void setShortIntToFloat(double shortIntToFloat) {
        this.shortIntToFloat = shortIntToFloat;
    }

    public double getShortIntDayToCover() {
        return shortIntDayToCover;
    }

    public void setShortIntDayToCover(double shortIntDayToCover) {
        this.shortIntDayToCover = shortIntDayToCover;
    }

    public double getBeta() {
        return beta;
    }

    public void setBeta(double beta) {
        this.beta = beta;
    }

    public double getVol1DayAvg() {
        return vol1DayAvg;
    }

    public void setVol1DayAvg(double vol1DayAvg) {
        this.vol1DayAvg = vol1DayAvg;
    }

    public double getVol10DayAvg() {
        return vol10DayAvg;
    }

    public void setVol10DayAvg(double vol10DayAvg) {
        this.vol10DayAvg = vol10DayAvg;
    }

    public double getVol3MonthAvg() {
        return vol3MonthAvg;
    }

    public void setVol3MonthAvg(double vol3MonthAvg) {
        this.vol3MonthAvg = vol3MonthAvg;
    }
}
