package scanner.symbols.Nasdaq;

import com.models.NasdaqSymbol;
import com.models.Symbol;
import com.scanner.symbols.Nasdaq.NasdaqScanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.HashMap;

class TestNasdaqScanner {
    private NasdaqScanner nasdaqScraper = new NasdaqScanner();

    @Test
    void TestGetNasdaqSymbol() {
        HashMap<String, NasdaqSymbol> symbol = nasdaqScraper.ParsePageTest(1, 10);
        Assertions.assertNotNull(symbol);
    }

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
        Method method = NasdaqScanner.class.getDeclaredMethod("ParsePage", int.class, int.class);
        method.setAccessible(true);
        HashMap<String, Symbol> results = (HashMap<String, Symbol>) method.invoke(nasdaqScraper, 1, 10);
        Assertions.assertNotNull(results);
        Assertions.assertTrue(results.size() > 0);
    }

    @Test
    void TestParsePage_Stock_Empty() throws Exception {
        Method method = NasdaqScanner.class.getDeclaredMethod("ParsePage", int.class, int.class);
        method.setAccessible(true);
        HashMap<String, Symbol> results = (HashMap<String, Symbol>) method.invoke(nasdaqScraper, Integer.MAX_VALUE, 10);
        Assertions.assertNotNull(results);
        Assertions.assertEquals(0, results.size());
    }

    @Test
    void TestParseRows_Future() throws Exception {
        Method method = NasdaqScanner.class.getDeclaredMethod("ParseRows", String.class, int.class, int.class,
                int.class);
        method.setAccessible(true);
        HashMap<String, Symbol> results = (HashMap<String, Symbol>) method.invoke(nasdaqScraper, "futures", 20, 10, 0);
        Assertions.assertNotNull(results);
        Assertions.assertTrue(results.size() > 0);
    }

    @Test
    void TestParseRows_Future_Empty() throws Exception {
        Method method = NasdaqScanner.class.getDeclaredMethod("ParseRows", String.class, int.class, int.class,
                int.class);
        method.setAccessible(true);
        HashMap<String, Symbol> results = (HashMap<String, Symbol>) method.invoke(nasdaqScraper, "futures", 20, 10,
                Integer.MAX_VALUE);
        Assertions.assertNotNull(results);
        Assertions.assertEquals(0, results.size());
    }

    @Test
    void TestParseRows_Index() throws Exception {
        Method method = NasdaqScanner.class.getDeclaredMethod("ParseRows", String.class, int.class, int.class,
                int.class);
        method.setAccessible(true);
        HashMap<String, Symbol> results = (HashMap<String, Symbol>) method.invoke(nasdaqScraper, "index", 20, 10, 0);
        Assertions.assertNotNull(results);
        Assertions.assertTrue(results.size() > 0);
    }

    @Test
    void TestParseRows_Index_Empty() throws Exception {
        Method method = NasdaqScanner.class.getDeclaredMethod("ParseRows", String.class, int.class, int.class,
                int.class);
        method.setAccessible(true);
        HashMap<String, Symbol> results = (HashMap<String, Symbol>) method.invoke(nasdaqScraper, "index", 20, 10,
                Integer.MAX_VALUE);
        Assertions.assertNotNull(results);
        Assertions.assertEquals(0, results.size());
    }

    @Test
    void TestParseRows_Etf() throws Exception {
        Method method = NasdaqScanner.class.getDeclaredMethod("ParseRows", String.class, int.class, int.class,
                int.class);
        method.setAccessible(true);
        HashMap<String, Symbol> results = (HashMap<String, Symbol>) method.invoke(nasdaqScraper, "etf", 20, 10, 0);
        Assertions.assertNotNull(results);
        Assertions.assertTrue(results.size() > 0);
    }

    @Test
    void TestParseRows_Etf_Empty() throws Exception {
        Method method = NasdaqScanner.class.getDeclaredMethod("ParseRows", String.class, int.class, int.class,
                int.class);
        method.setAccessible(true);
        HashMap<String, Symbol> results = (HashMap<String, Symbol>) method.invoke(nasdaqScraper, "etf", 20, 10,
                Integer.MAX_VALUE);
        Assertions.assertNotNull(results);
        Assertions.assertEquals(0, results.size());
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
