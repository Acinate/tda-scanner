package scanner.symbols.Nasdaq;

import com.models.responses.NasdaqSymbol;
import com.models.SymbolOld;
import com.scanner.symbols.Nasdaq.NasdaqScanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.HashMap;

class TestNasdaqScanner {
    private NasdaqScanner nasdaqScraper = new NasdaqScanner();

    @Test
    void TestGetCount_Stock() throws Exception {
        Method method = NasdaqScanner.class.getDeclaredMethod("GetCount", String.class);
        method.setAccessible(true);
        int result = (int) method.invoke(nasdaqScraper, "stock");
        Assertions.assertTrue(result > 0);
    }

    @Test
    void TestGetCount_Future() throws Exception {
        Method method = NasdaqScanner.class.getDeclaredMethod("GetCount", String.class);
        method.setAccessible(true);
        int result = (int) method.invoke(nasdaqScraper, "futures");
        Assertions.assertTrue(result > 0);
    }

    @Test
    void TestGetCount_Index() throws Exception {
        Method method = NasdaqScanner.class.getDeclaredMethod("GetCount", String.class);
        method.setAccessible(true);
        int result = (int) method.invoke(nasdaqScraper, "index");
        Assertions.assertTrue(result > 0);
    }

    @Test
    void TestGetCount_Etf() throws Exception {
        Method method = NasdaqScanner.class.getDeclaredMethod("GetCount", String.class);
        method.setAccessible(true);
        int result = (int) method.invoke(nasdaqScraper, "etf");
        Assertions.assertTrue(result > 0);
    }

    @Test
    void TestParsePage_Stock() throws Exception {
        Method method = NasdaqScanner.class.getDeclaredMethod("ParsePage", int.class, int.class, HashMap.class);
        method.setAccessible(true);
        HashMap<String, NasdaqSymbol> symbols = new HashMap<>();
        method.invoke(nasdaqScraper, 1, 10, symbols);
        Assertions.assertNotNull(symbols);
        Assertions.assertEquals(10, symbols.size());
    }

    @Test
    void TestParsePage_Stock_Empty() throws Exception {
        Method method = NasdaqScanner.class.getDeclaredMethod("ParsePage", int.class, int.class, HashMap.class);
        method.setAccessible(true);
        HashMap<String, NasdaqSymbol> symbols = new HashMap<>();
        method.invoke(nasdaqScraper, Integer.MAX_VALUE, 10, symbols);
        Assertions.assertNotNull(symbols);
        Assertions.assertEquals(0, symbols.size());
    }

    @Test
    void TestParseRows_Future() throws Exception {
        Method method = NasdaqScanner.class.getDeclaredMethod("ParseRows", String.class, int.class,
                int.class, HashMap.class);
        method.setAccessible(true);
        HashMap<String, SymbolOld> symbols = new HashMap<>();
        method.invoke(nasdaqScraper, "futures", 10, 0, symbols);
        Assertions.assertNotNull(symbols);
        Assertions.assertTrue(symbols.size() > 0);
    }

    @Test
    void TestParseRows_Future_Empty() throws Exception {
        Method method = NasdaqScanner.class.getDeclaredMethod("ParseRows", String.class, int.class,
                int.class, HashMap.class);
        method.setAccessible(true);
        HashMap<String, SymbolOld> results = new HashMap<>();
        method.invoke(nasdaqScraper, "futures", 10, Integer.MAX_VALUE, results);
        Assertions.assertNotNull(results);
        Assertions.assertEquals(0, results.size());
    }

    @Test
    void TestParseRows_Index() throws Exception {
        Method method = NasdaqScanner.class.getDeclaredMethod("ParseRows", String.class, int.class,
                int.class, HashMap.class);
        method.setAccessible(true);
        HashMap<String, SymbolOld> symbols = new HashMap<>();
        method.invoke(nasdaqScraper, "index", 10, 0, symbols);
        Assertions.assertNotNull(symbols);
        Assertions.assertTrue(symbols.size() > 0);
    }

    @Test
    void TestParseRows_Index_Empty() throws Exception {
        Method method = NasdaqScanner.class.getDeclaredMethod("ParseRows", String.class, int.class,
                int.class, HashMap.class);
        method.setAccessible(true);
        HashMap<String, SymbolOld> symbols = new HashMap<>();
        method.invoke(nasdaqScraper, "index", 10, Integer.MAX_VALUE, symbols);
        Assertions.assertNotNull(symbols);
        Assertions.assertEquals(0, symbols.size());
    }

    @Test
    void TestParseRows_Etf() throws Exception {
        Method method = NasdaqScanner.class.getDeclaredMethod("ParseRows", String.class, int.class,
                int.class, HashMap.class);
        method.setAccessible(true);
        HashMap<String, SymbolOld> symbols = new HashMap<>();
        method.invoke(nasdaqScraper, "etf", 10, 0, symbols);
        Assertions.assertNotNull(symbols);
        Assertions.assertTrue(symbols.size() > 0);
    }

    @Test
    void TestParseRows_Etf_Empty() throws Exception {
        Method method = NasdaqScanner.class.getDeclaredMethod("ParseRows", String.class, int.class,
                int.class, HashMap.class);
        method.setAccessible(true);
        HashMap<String, SymbolOld> symbols = new HashMap<>();
        method.invoke(nasdaqScraper, "etf", 10, Integer.MAX_VALUE, symbols);
        Assertions.assertNotNull(symbols);
        Assertions.assertEquals(0, symbols.size());
    }

    @Test
    void TestGetAssetType() throws Exception {
        Method method = NasdaqScanner.class.getDeclaredMethod("GetAssetType", String.class);
        method.setAccessible(true);
        Assertions.assertEquals("Future", method.invoke(nasdaqScraper, "futures"));
        Assertions.assertEquals("Index", method.invoke(nasdaqScraper, "index"));
        Assertions.assertEquals("ETF", method.invoke(nasdaqScraper, "etf"));
        Assertions.assertEquals("Stock", method.invoke(nasdaqScraper, "stock"));
        Assertions.assertEquals("", method.invoke(nasdaqScraper, "randomString"));
        Assertions.assertEquals("", method.invoke(nasdaqScraper, ""));
    }
}
