package com.api;

import com.api.TimeSpan;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestTimeSpan {

    @Test
    void TestDayCandles() {
        TimeSpan test = null;
        String[] days = new String[] {"1D","2D","3D","4D","5D","10D"};
        for (String day : days) {
            test = new TimeSpan(day,"1M");
            Assertions.assertNotNull(test.period);
            test = new TimeSpan(day,"5M");
            Assertions.assertNotNull(test.period);
            test = new TimeSpan(day,"10M");
            Assertions.assertNotNull(test.period);
            test = new TimeSpan(day,"15M");
            Assertions.assertNotNull(test.period);
            test = new TimeSpan(day,"30M");
            Assertions.assertNotNull(test.period);
        }
    }

    @Test
    void TestMonthCandles() {
        TimeSpan test = null;
        String[] months = new String[] {"1M","2M","3M","6M"};
        for (String month : months) {
            test = new TimeSpan(month, "1D");
            Assertions.assertNotNull(test.period);
            test = new TimeSpan(month, "1W");
            Assertions.assertNotNull(test.period);
        }
    }

    @Test
    void TestYearCandles() {
        TimeSpan test = null;
        String[] years = new String[] {"1Y","2Y","3Y","5Y","10Y","15Y","20Y"};
        for (String year : years) {
            test = new TimeSpan(year, "1D");
            Assertions.assertNotNull(test.period);
            test = new TimeSpan(year, "1W");
            Assertions.assertNotNull(test.period);
            test = new TimeSpan(year, "1M");
            Assertions.assertNotNull(test.period);
        }
    }

    @Test
    void TestInvalidCombinations() {
        TimeSpan test = null;
        test = new TimeSpan("1y","2d");
        Assertions.assertNull(test.period);
        test = new TimeSpan("1m","1m");
        Assertions.assertNull(test.period);
        test = new TimeSpan("1d","1y");
        Assertions.assertNull(test.period);
        test = new TimeSpan("1y","2w");
        Assertions.assertNull(test.period);
        test = new TimeSpan("1s","1b");
        Assertions.assertNull(test.period);
        test = new TimeSpan("3m","1y");
        Assertions.assertNull(test.period);
        test = new TimeSpan("5m","6m");
        Assertions.assertNull(test.period);
    }
}
