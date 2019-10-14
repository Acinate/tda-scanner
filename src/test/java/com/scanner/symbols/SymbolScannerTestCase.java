package com.scanner.symbols;

import org.junit.Test;

public class SymbolScannerTestCase {
    private SymbolScanner scanner = new SymbolScanner();

    @Test
    public void scanSymbols_Test() {
        scanner.scanSymbols();
    }

    @Test
    public void updateSymbols_Test() {
        scanner.updateSymbols();
    }
}
