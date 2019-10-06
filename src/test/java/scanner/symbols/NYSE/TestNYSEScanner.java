package scanner.symbols.NYSE;

import com.scanner.symbols.NYSE.NYSEScanner;
import org.junit.jupiter.api.Test;

class TestNYSEScanner {

    private NYSEScanner nyseScanner = new NYSEScanner();

    @Test
    void TestNyseStockScanner() {
        nyseScanner.GetStocks();
    }

    @Test
    void TestNyseEtfScanner() {
        nyseScanner.GetETFs();
    }

    @Test
    void TestNyseIndexScanner() {
        nyseScanner.GetIndexes();
    }
}
