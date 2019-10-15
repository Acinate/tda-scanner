package com.models.database;

import com.models.responses.TdaFundamental;

/**
 * Holds Dividend information for Symbol. Uses data from TdaFundamental.
 */
public class Dividend {
    public void applyDataSet(TdaFundamental dataset) {
        if (dataset != null) {
            amount = dataset.getDividendAmount();
            yield = dataset.getDividendYield();
            date = dataset.getDividendDate();
            growthRate3Year = dataset.getDivGrowthRate3Year();
            payAmount = dataset.getDividendPayAmount();
            payDate = dataset.getDividendPayDate();
        }
    }

    private double amount;
    private double yield;
    private String date;
    private double growthRate3Year;
    private double payAmount;
    private String payDate;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getYield() {
        return yield;
    }

    public void setYield(double yield) {
        this.yield = yield;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getGrowthRate3Year() {
        return growthRate3Year;
    }

    public void setGrowthRate3Year(double growthRate3Year) {
        this.growthRate3Year = growthRate3Year;
    }

    public double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(double payAmount) {
        this.payAmount = payAmount;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }
}
