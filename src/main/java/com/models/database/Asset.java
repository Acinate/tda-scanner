package com.models.database;

import com.models.responses.NasdaqSummary;
import com.models.responses.TdaFundamental;

/**
 * Holds asset information for Symbols, uses information from TdaFundamental and NasdaqSymbol
 */
public class Asset {
    public void applyDataSet(TdaFundamental dataset) {
        if (dataset != null) {
            cusip = dataset.getCusip();
            description = dataset.getDescription();
            exchange = dataset.getExchange();
            assetType = dataset.getAssetType();
        }
    }

    public void applyDataSet(NasdaqSummary dataset) {
        if (dataset != null) {
            sector = dataset.getSector();
        }
    }

    private String cusip;
    private String description;
    private String exchange;
    private String assetType;
    private String sector;

    public String getCusip() {
        return cusip;
    }

    public void setCusip(String cusip) {
        this.cusip = cusip;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }
}
