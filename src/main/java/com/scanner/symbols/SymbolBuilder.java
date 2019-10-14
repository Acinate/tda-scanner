package com.scanner.symbols;

import com.api.TDA;
import com.models.database.*;
import com.models.responses.TdaFundamental;

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
        Fundamental fundamental = new Fundamental();
        fundamental.setHigh52(tdaFundamental.getHigh52());
        fundamental.setLow52(tdaFundamental.getLow52());
        fundamental.setSharesOutstanding(tdaFundamental.getSharesOutstanding());
        fundamental.setMarketCapFloat(tdaFundamental.getMarketCapFloat());
        fundamental.setMarketCap(tdaFundamental.getMarketCap());
        fundamental.setBookValuePerShare(tdaFundamental.getBookValuePerShare());
        fundamental.setShortIntToFloat(tdaFundamental.getShortIntToFloat());
        fundamental.setShortIntDayToCover(tdaFundamental.getShortIntDayToCover());
        fundamental.setBeta(tdaFundamental.getBeta());
        fundamental.setVol1DayAvg(tdaFundamental.getVol1DayAvg());
        fundamental.setVol10DayAvg(tdaFundamental.getVol10DayAvg());
        fundamental.setVol3MonthAvg(tdaFundamental.getVol3MonthAvg());
        Asset asset = new Asset();
        asset.setCusip(tdaFundamental.getCusip());
        asset.setDescription(tdaFundamental.getDescription());
        asset.setExchange(tdaFundamental.getExchange());
        asset.setAssetType(tdaFundamental.getAssetType());
        Earnings earnings = new Earnings();
        earnings.setPeRatio(tdaFundamental.getPeRatio());
        earnings.setPegRatio(tdaFundamental.getPegRatio());
        earnings.setPbRatio(tdaFundamental.getPbRatio());
        earnings.setPrRatio(tdaFundamental.getPrRatio());
        earnings.setPcfRatio(tdaFundamental.getPcfRatio());
        earnings.setGrossMarginTtm(tdaFundamental.getGrossMarginTTM());
        earnings.setGrossMarginMrq(tdaFundamental.getGrossMarginMRQ());
        earnings.setNetProfitMarginTtm(tdaFundamental.getNetProfitMarginTTM());
        earnings.setNetProfitMarginMrq(tdaFundamental.getNetProfitMarginMRQ());
        earnings.setReturnOnEquity(tdaFundamental.getReturnOnEquity());
        earnings.setReturnOnAssets(tdaFundamental.getReturnOnAssets());
        earnings.setReturnOnInvestment(tdaFundamental.getReturnOnInvestment());
        earnings.setQuickRatio(tdaFundamental.getQuickRatio());
        earnings.setInterestCoverage(tdaFundamental.getInterestCoverage());
        earnings.setTotalDebtToCapital(tdaFundamental.getTotalDebtToCapital());
        earnings.setLtDebtToEquity(tdaFundamental.getLtDebtToCapital());
        earnings.setEpsTtm(tdaFundamental.getEpsTTM());
        earnings.setEpsChangePercentTtm(tdaFundamental.getEpsChangePercentTTM());
        earnings.setEpsChangeYear(tdaFundamental.getEpsChangeYear());
        earnings.setEpsChange(tdaFundamental.getEpsChange());
        earnings.setRevChangeYear(tdaFundamental.getRevChangeYear());
        earnings.setRevChangeTtm(tdaFundamental.getRevChangeTTM());
        earnings.setRevChangeIn(tdaFundamental.getRevChangeIn());
        Dividend dividend = new Dividend();
        dividend.setAmount(tdaFundamental.getDividendAmount());
        dividend.setYield(tdaFundamental.getDividendYield());
        dividend.setDate(tdaFundamental.getDividendDate());
        dividend.setGrowthRate3Year(tdaFundamental.getDivGrowthRate3Year());
        dividend.setPayAmount(tdaFundamental.getDividendPayAmount());
        dividend.setPayDate(tdaFundamental.getDividendPayDate());

        symbol.setFundamental(fundamental);
        symbol.setAsset(asset);
        symbol.setEarnings(earnings);
        symbol.setDividend(dividend);

        return symbol;
    }
}
