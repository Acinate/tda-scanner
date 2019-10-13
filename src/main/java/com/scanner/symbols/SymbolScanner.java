package com.scanner.symbols;

import com.database.Database;
import com.models.NasdaqSymbol;
import com.scanner.symbols.Nasdaq.NasdaqScanner;

import java.util.HashMap;

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
}
