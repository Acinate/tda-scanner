package api;

import com.api.TimeSpanException;
import com.models.History;
import com.models.Quote;
import com.api.BadRequestException;
import com.api.TDA;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

class TestTDA {
    private TDA tda = new TDA();

    @Test
    void TestGetQuote() {
        Quote quote = tda.getQuote("DGAZ");
        Assertions.assertNotNull(quote);
        Assertions.assertEquals("DGAZ", quote.getSymbol());
        Assertions.assertTrue(quote.getLastPrice() > 0);
        Assertions.assertTrue(quote.getOpenPrice() > 0);
        Assertions.assertTrue(quote.getClosePrice() > 0);
        Assertions.assertTrue(quote.getTotalVolume() > 0);
        Assertions.assertTrue(quote.get52WkHigh() > 0);
        Assertions.assertTrue(quote.get52WkLow() > 0);
    }

    @Test
    void TestGetHistory() {
        History history = null;
        try {
            history = tda.getHistory("AAPL");
        } catch (BadRequestException | ParseException | TimeSpanException e) {
            e.printStackTrace();
        }
        Assertions.assertNotNull(history);
        Assertions.assertTrue(history.getCandles().size() > 0);
    }

    @Test
    void TestGetHistoryBetweenDates() {
        History history = null;
        try {
            history = tda.getHistory("AAPL", "24-09-2019", "24-09-2019");
        } catch (BadRequestException | ParseException | TimeSpanException e) {
            e.printStackTrace();
        }
        Assertions.assertNotNull(history);
        Assertions.assertTrue(history.getCandles().size() > 0);
        Assertions.assertTrue(history.getHigh().getClose() > 0);
        Assertions.assertTrue(history.getLow().getClose() > 0);
    }

    @Test
    void TestGetHistory1D1MCandles() {
        History history = null;
        try {
            history = tda.getHistory("AAPL", "25-09-2019", "26-09-2019", "1D", "1M");
        } catch (BadRequestException | ParseException | TimeSpanException e) {
            e.printStackTrace();
        }
        Assertions.assertNotNull(history);
        Assertions.assertEquals(780, history.getCandles().size());
    }

    @Test
    void TestGetHistory1D1MCandlesExtendedHours() {
        History history = null;
        try {
            history = tda.getHistory("AAPL", "25-09-2019", "26-09-2019", "1D", "1M", true);
        } catch (BadRequestException | ParseException | TimeSpanException e) {
            e.printStackTrace();
        }
        Assertions.assertNotNull(history);
        Assertions.assertEquals(1136, history.getCandles().size());
    }

    @Test
    void TestGetHistoryIntradayHigh() {
        History history = null;
        try {
            history = tda.getHistory("UWT", "27-09-2019", "27-09-2019", "1D", "1M");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assertions.assertNotNull(history);
        Assertions.assertEquals(11.88, history.getHigh().getHigh());
    }

    @Test
    void TestGetHistoryIntradayLow() {
        History history = null;
        try {
            history = tda.getHistory("UWT", "27-09-2019", "27-09-2019", "1D", "1M");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assertions.assertNotNull(history);
        Assertions.assertEquals(11.02, history.getLow().getLow());
    }

    @Test
    void TestGetHistoryBadRequest() {
        History history = null;
        try {
            history = tda.getHistory("AAPL", "24-09-2019", "20-09-2019");
        } catch (Exception ignored) { }
        Assertions.assertNull(history);
    }

    @Test
    void TestGetHistoryParseException() {
        History history = null;
        try {
            history = tda.getHistory("AAPL", "01-14-2019", "01-30-2019");
        } catch (Exception ignored) { }
        Assertions.assertNull(history);
    }

    @Test
    void TestGetHistoryBadTimeSpan() {
        History history = null;
        try {
            history = tda.getHistory("AAPL", "24-09-2019", "25-09-2019", "1d", "1w");
        } catch (Exception ignored) { }
        Assertions.assertNull(history);
    }
}
