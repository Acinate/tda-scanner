package com.scanner.symbols;

import com.api.TDA;
import com.models.database.*;
import com.models.responses.NasdaqSummary;
import com.models.responses.TdaFundamental;
import com.scanner.symbols.Nasdaq.NasdaqScanner;

/**
 * This object focusing on building the generic Symbol Object so that we
 * can easily add/update our database records. It uses all APIs including
 * TDA and Nasdaq APIs.
 */
public class SymbolBuilder {
    public Symbol buildSymbol(String s) {
        Symbol symbol = new Symbol(s);

        TDA tda = new TDA();
        TdaFundamental tdaFundamental = tda.getFundamental(s);
        NasdaqScanner nasdaqScanner = new NasdaqScanner();
        NasdaqSummary nasdaqSummary = nasdaqScanner.getSummary(s, "Stock");

        /* Populate Fundamental Table */
        Fundamental fundamental = new Fundamental();
        fundamental.applyDataSet(tdaFundamental);

        /* Populate Asset Table */
        Asset asset = new Asset();
        asset.applyDataSet(tdaFundamental);
        asset.applyDataSet(nasdaqSummary);

        /* Populate Earnings Table */
        Earnings earnings = new Earnings();
        earnings.applyDataSet(tdaFundamental);

        /* Populate Dividend Table */
        Dividend dividend = new Dividend();
        dividend.applyDataSet(tdaFundamental);

        symbol.setFundamental(fundamental);
        symbol.setAsset(asset);
        symbol.setEarnings(earnings);
        symbol.setDividend(dividend);

        if (symbol.getAsset().getCusip() == null) {
            System.out.println("Error retrieving asset information for " + symbol.getSymbol() +
                    ", retrying again in 10 seconds.");
            try {
                Thread.sleep(15000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return buildSymbol(s);
        }
        return symbol;
    }
}
