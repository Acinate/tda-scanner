package scanner.symbols.NYSE;

import com.models.SymbolOld;
import com.scanner.symbols.NYSE.NYSEScanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.HashMap;

class TestNYSEScanner {
    private NYSEScanner nyseScanner = new NYSEScanner();

    @Test
    void TestGetCount_Stock() throws Exception {
        Method method = NYSEScanner.class.getDeclaredMethod("GetCount", String.class);
        method.setAccessible(true);
        int count = (int) method.invoke(nyseScanner, "stock");
        Assertions.assertTrue(count >= 0);
    }

    @Test
    void TestGetCount_Index() throws Exception {
        Method method = NYSEScanner.class.getDeclaredMethod("GetCount", String.class);
        method.setAccessible(true);
        int count = (int) method.invoke(nyseScanner, "index");
        Assertions.assertTrue(count >= 0);
    }

    @Test
    void TestGetCount_Etf() throws Exception {
        Method method = NYSEScanner.class.getDeclaredMethod("GetCount", String.class);
        method.setAccessible(true);
        int count = (int) method.invoke(nyseScanner, "etf");
        Assertions.assertTrue(count >= 0);
    }

    @Test
    void TestGetCount_Invalid() throws Exception {
        Method method = NYSEScanner.class.getDeclaredMethod("GetCount", String.class);
        method.setAccessible(true);
        int count = (int) method.invoke(nyseScanner, "invalidAssetType");
        Assertions.assertEquals(-1, count);
    }

    @Test
    void TestGetPage_Stock() throws Exception {
        Method method = NYSEScanner.class.getDeclaredMethod("GetPage", String.class, int.class, int.class);
        method.setAccessible(true);
        HashMap<String, SymbolOld> symbols = (HashMap<String, SymbolOld>) method.invoke(nyseScanner, "stock", 1, 10);
        Assertions.assertNotNull(symbols);
        Assertions.assertEquals(10, symbols.size());
    }

    @Test
    void TestGetPage_Index() throws Exception {
        Method method = NYSEScanner.class.getDeclaredMethod("GetPage", String.class, int.class, int.class);
        method.setAccessible(true);
        HashMap<String, SymbolOld> symbols = (HashMap<String, SymbolOld>) method.invoke(nyseScanner, "index", 1, 10);
        Assertions.assertNotNull(symbols);
        Assertions.assertEquals(10, symbols.size());
    }

    @Test
    void TestGetPage_Etf() throws Exception {
        Method method = NYSEScanner.class.getDeclaredMethod("GetPage", String.class, int.class, int.class);
        method.setAccessible(true);
        HashMap<String, SymbolOld> symbols = (HashMap<String, SymbolOld>) method.invoke(nyseScanner, "etf", 1, 10);
        Assertions.assertNotNull(symbols);
        Assertions.assertEquals(10, symbols.size());
    }

    @Test
    void TestGetPage_Invalid() throws Exception {
        Method method = NYSEScanner.class.getDeclaredMethod("GetPage", String.class, int.class, int.class);
        method.setAccessible(true);
        HashMap<String, SymbolOld> symbols = (HashMap<String, SymbolOld>) method.invoke(nyseScanner, "invalidAsset", 1, 10);
        Assertions.assertNotNull(symbols);
        Assertions.assertEquals(0, symbols.size());
    }
}
