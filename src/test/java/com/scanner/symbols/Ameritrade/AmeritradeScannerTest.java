package com.scanner.symbols.Ameritrade;

import com.models.responses.TdaFundamental;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AmeritradeScannerTest {

    private AmeritradeScanner scanner = new AmeritradeScanner();

    @Test
    void getFundamental_Test_AAPL() {
        TdaFundamental fundamental = scanner.getFundamental("AAPL");
        Assertions.assertNotNull(fundamental);
        Assertions.assertEquals("AAPL", fundamental.getSymbol());
    }

    @Test
    void getFundamental_Test_InvalidSymbol() {
        TdaFundamental fundamental = scanner.getFundamental("InvalidSymbol");
        Assertions.assertNull(fundamental);
    }
}