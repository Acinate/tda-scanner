package com.scanner.symbols.Nasdaq;

import com.models.SymbolOld;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.lang.reflect.Method;
import java.util.HashMap;

public class NasdaqAssetScannerTestCase {

    private NasdaqAssetScanner assetScanner = new NasdaqAssetScanner();

    @Test
    public void getCount_Test_Future() throws Exception {
        Method method = NasdaqAssetScanner.class.getDeclaredMethod("getCount", String.class);
        method.setAccessible(true);
        int count = (int) method.invoke(assetScanner, "futures");
        Assertions.assertTrue(count > 0);
    }

    @Test
    public void getCount_Test_Index() throws Exception {
        Method method = NasdaqAssetScanner.class.getDeclaredMethod("getCount", String.class);
        method.setAccessible(true);
        int count = (int) method.invoke(assetScanner, "index");
        Assertions.assertTrue(count > 0);
    }

    @Test
    public void getCount_Test_Etf() throws Exception {
        Method method = NasdaqAssetScanner.class.getDeclaredMethod("getCount", String.class);
        method.setAccessible(true);
        int count = (int) method.invoke(assetScanner, "etf");
        Assertions.assertTrue(count > 0);
    }

    @Test
    public void parseRows_Test_Futures() throws Exception {
        Method method = NasdaqAssetScanner.class.getDeclaredMethod("parseRows", String.class, int.class,
                int.class, HashMap.class);
        method.setAccessible(true);
        HashMap<String, SymbolOld> symbols = new HashMap<>();
        method.invoke(assetScanner, "futures", 10, 0, symbols);
        Assertions.assertNotNull(symbols);
        Assertions.assertTrue(symbols.size() > 0);
    }

    @Test
    public void parseRows_Test_Futures_Empty() throws Exception {
        Method method = NasdaqAssetScanner.class.getDeclaredMethod("parseRows", String.class, int.class, int.class, HashMap.class);
        method.setAccessible(true);
        HashMap<String, SymbolOld> results = new HashMap<>();
        method.invoke(assetScanner, "futures", 10, Integer.MAX_VALUE, results);
        Assertions.assertNotNull(results);
        Assertions.assertEquals(0, results.size());
    }

    @Test
    public void parseRows_Test_Index() throws Exception {
        Method method = NasdaqAssetScanner.class.getDeclaredMethod("parseRows", String.class, int.class,
                int.class, HashMap.class);
        method.setAccessible(true);
        HashMap<String, SymbolOld> symbols = new HashMap<>();
        method.invoke(assetScanner, "index", 10, 0, symbols);
        Assertions.assertNotNull(symbols);
        Assertions.assertTrue(symbols.size() > 0);
    }

    @Test
    public void parseRows_Test_Index_Empty() throws Exception {
        Method method = NasdaqAssetScanner.class.getDeclaredMethod("parseRows", String.class, int.class, int.class, HashMap.class);
        method.setAccessible(true);
        HashMap<String, SymbolOld> results = new HashMap<>();
        method.invoke(assetScanner, "index", 10, Integer.MAX_VALUE, results);
        Assertions.assertNotNull(results);
        Assertions.assertEquals(0, results.size());
    }

    @Test
    public void parseRows_Test_Etf() throws Exception {
        Method method = NasdaqAssetScanner.class.getDeclaredMethod("parseRows", String.class, int.class,
                int.class, HashMap.class);
        method.setAccessible(true);
        HashMap<String, SymbolOld> symbols = new HashMap<>();
        method.invoke(assetScanner, "etf", 10, 0, symbols);
        Assertions.assertNotNull(symbols);
        Assertions.assertTrue(symbols.size() > 0);
    }

    @Test
    public void parseRows_Test_Etf_Empty() throws Exception {
        Method method = NasdaqAssetScanner.class.getDeclaredMethod("parseRows", String.class, int.class, int.class, HashMap.class);
        method.setAccessible(true);
        HashMap<String, SymbolOld> results = new HashMap<>();
        method.invoke(assetScanner, "etf", 10, Integer.MAX_VALUE, results);
        Assertions.assertNotNull(results);
        Assertions.assertEquals(0, results.size());
    }

    @Test
    public void getAssetType_Test() throws Exception {
        Method method = NasdaqAssetScanner.class.getDeclaredMethod("getAssetType", String.class);
        method.setAccessible(true);
        Assertions.assertEquals("Future", method.invoke(assetScanner, "futures"));
        Assertions.assertEquals("Index", method.invoke(assetScanner, "index"));
        Assertions.assertEquals("ETF", method.invoke(assetScanner, "etf"));
        Assertions.assertEquals("Stock", method.invoke(assetScanner, "stock"));
        Assertions.assertEquals("", method.invoke(assetScanner, "randomString"));
        Assertions.assertEquals("", method.invoke(assetScanner, ""));
    }
}
