package com.scanner.symbols.Nasdaq;

import org.junit.Test;

public class NasdaqScannerTestCase {
    private NasdaqScanner scanner = new NasdaqScanner();

    @Test
    public void scanStocks_Test() {
        scanner.scanStocks();
    }
}
