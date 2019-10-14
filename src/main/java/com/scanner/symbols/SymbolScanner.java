package com.scanner.symbols;

import com.database.Database;
import com.models.database.Symbol;
import com.models.responses.NasdaqSymbol;
import com.scanner.symbols.Nasdaq.NasdaqScanner;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Responsible for Scanning Symbols of all different assets across all exchanges,
 * and inserting them into the symbol table in the market database.
 */
public class SymbolScanner {
    /**
     * Scans all Symbols and Inserts them into the Market Database
     */
    public void scanSymbols() {
        NasdaqScanner nasdaqScanner = new NasdaqScanner();
        HashMap<String, NasdaqSymbol> nasdaqSymbols = nasdaqScanner.scanStocks();
        Database database = new Database();
        System.out.println(nasdaqSymbols.size());
        database.insertSymbols(nasdaqSymbols.keySet());
    }

    /**
     * Retrieves Symbols from our Database and updates their metadata.
     */
    public void updateSymbols() {
        Set<String> symbols = new HashSet<>();
        Database database = new Database();
        symbols = database.getSymbols();
        SymbolBuilder builder = new SymbolBuilder();
        for (String symbol : symbols) {
            System.out.println("Scanning: " + symbol);
            Symbol s = builder.buildSymbol(symbol);
            // database.insertAsset(s.getSymbol(), s.getAsset());
            database.insertFundamental(s.getSymbol(), s.getFundamental());
        }
    }
}
