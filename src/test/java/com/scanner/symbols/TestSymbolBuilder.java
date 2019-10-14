package com.scanner.symbols;

import org.junit.Test;

public class TestSymbolBuilder {
    private SymbolBuilder symbolBuilder = new SymbolBuilder();

    @Test
    public void TestSymbolBuilder() {
        symbolBuilder.buildSymbol("AAPL");
    }

}
