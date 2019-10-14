package com.scanner.symbols.Nasdaq;

import com.models.responses.NasdaqSummary;
import com.models.responses.NasdaqSymbol;
import com.models.SymbolOld;

import java.util.HashMap;

/**
 * Scans Nasdaq API endpoints to retrieve stocks, etf, futures and indexes.
 *
 */
public class NasdaqScanner {
    /**
     * Automatically scans all stocks in the Nasdaq API.
     *
     * @return a hash map that maps symbol names to their symbol metadata
     */
    public HashMap<String, NasdaqSymbol> scanStocks() {
        return stockScanner.getStocks();
    }

    /**
     * Automatically scans all etfs in the Nasdaq API.
     *
     * @return a hash map that maps symbol names to their symbol metadata
     */
    public HashMap<String, SymbolOld> scanEtfs() {
        return assetScanner.getEtf();
    }

    /**
     * Automatically scans all indexes in the Nasdaq API.
     *
     * @return a hash map that maps symbol names to their symbol metadata
     */
    public HashMap<String, SymbolOld> scanIndexes() {
        return assetScanner.getIndex();
    }

    /**
     * Automatically scans all futures in the Nasdaq API.
     *
     * @return a hash map that maps symbol names to their symbol metadata
     */
    public HashMap<String, SymbolOld> scanFutures() {
        return assetScanner.getFutures();
    }

    /**
     * Returns a nasdaq summary for a given symbol and assetType
     * @param symbol the symbol to retrieve summary for
     * @param assetType the assetType of the symbol
     * @return a NasdaqSummary Object
     */
    public NasdaqSummary getSummary(String symbol, String assetType) {
        return summaryScanner.getSummary(symbol, assetType);
    }

    private NasdaqSummaryScanner summaryScanner = new NasdaqSummaryScanner();
    private NasdaqStockScanner stockScanner = new NasdaqStockScanner();
    private NasdaqAssetScanner assetScanner = new NasdaqAssetScanner();
}
