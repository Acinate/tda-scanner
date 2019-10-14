package com.scanner.symbols.Nasdaq;

import com.models.responses.NasdaqSymbol;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.lang.reflect.Method;
import java.util.HashMap;

public class NasdaqStockScannerTestCase {
    private NasdaqStockScanner stockScanner = new NasdaqStockScanner();

    @Test
    public void getCount_Test() throws Exception {
        Method method = NasdaqStockScanner.class.getDeclaredMethod("getCount");
        method.setAccessible(true);
        int count = (int) method.invoke(stockScanner);
        Assertions.assertTrue(count > 0);
    }

    @Test
    public void parsePage_Test() throws Exception {
        Method method = NasdaqStockScanner.class.getDeclaredMethod("parsePage", int.class, int.class, HashMap.class);
        method.setAccessible(true);
        HashMap<String, NasdaqSymbol> symbols = new HashMap<>();
        method.invoke(stockScanner, 1, 20, symbols);
        Assertions.assertEquals(20, symbols.size());
    }

    @Test
    public void parsePage_Test_EmptyPage() throws Exception {
        Method method = NasdaqStockScanner.class.getDeclaredMethod("parsePage", int.class, int.class, HashMap.class);
        method.setAccessible(true);
        HashMap<String, NasdaqSymbol> symbols = new HashMap<>();
        method.invoke(stockScanner, Integer.MAX_VALUE, 20, symbols);
        Assertions.assertEquals(0, symbols.size());
    }
}
