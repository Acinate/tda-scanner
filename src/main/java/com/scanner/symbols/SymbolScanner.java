package com.scanner.symbols;

import com.database.Database;
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
    public void ScanSymbols() {
        NasdaqScanner nasdaqScanner = new NasdaqScanner();
        HashMap<String, NasdaqSymbol> nasdaqSymbols = nasdaqScanner.GetStock();
        Database database = new Database();
        System.out.println(nasdaqSymbols.size());
        database.insertSymbols(nasdaqSymbols.keySet());
    }

    /**
     * Retrieves Symbols from our Database and updates their metadata.
     */
    public void UpdateSymbols() {
        Set<String> symbols = new HashSet<>();
        Database database = new Database();
        symbols = database.getSymbols();
        for (String symbol : symbols) {

        }
    }
}
