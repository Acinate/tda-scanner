package scraper;

import com.scanner.symbols.Nasdaq.NasdaqScraper;
import org.junit.jupiter.api.Test;

class TestNasdaqScraper {

    private NasdaqScraper nasdaqScraper = new NasdaqScraper();

    @Test
    void TestNasdaqScanStocks() throws Exception {
        nasdaqScraper.ScanStocks();
    }

    @Test
    void TestNasdaqScanETFs() throws Exception {
        nasdaqScraper.ScanETFs();
    }

    @Test
    void TestNasdaqScanIndexes() throws Exception {
        nasdaqScraper.ScanIndexes();
    }

    @Test
    void TestNasdaqScanFutures() throws Exception {
        nasdaqScraper.ScanFutures();
    }
}
