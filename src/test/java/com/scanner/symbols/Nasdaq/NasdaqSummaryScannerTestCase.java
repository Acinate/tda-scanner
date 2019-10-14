package com.scanner.symbols.Nasdaq;

import com.models.responses.NasdaqSummary;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class NasdaqSummaryScannerTestCase {
    private NasdaqSummaryScanner summaryScanner = new NasdaqSummaryScanner();

    @Test
    public void getSummary_Test() {
        NasdaqSummary summary = summaryScanner.getSummary("AAPL", "Stock");
        Assertions.assertNotNull(summary);
        Assertions.assertEquals("Technology", summary.getSector());
    }

    @Test
    public void getSummary_Test_InvalidSymbol() {
        NasdaqSummary summary = summaryScanner.getSummary("InvalidSymbol", "Stock");
        Assertions.assertNull(summary);
    }

    @Test
    public void getSummary_Test_InvalidAsset() {
        NasdaqSummary summary = summaryScanner.getSummary("AAPL", "InvalidAsset");
        Assertions.assertNull(summary);
    }
}
