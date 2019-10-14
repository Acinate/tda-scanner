package scanner.symbols;

import com.scanner.symbols.SymbolScanner;
import org.junit.Test;

public class TestScanSymbols {
    private SymbolScanner scanner = new SymbolScanner();

    @Test
    public void TestScanSymbols() {
        scanner.ScanSymbols();
    }

    @Test
    public void TestUpdateSymbols() {
        scanner.UpdateSymbols();
    }
}
