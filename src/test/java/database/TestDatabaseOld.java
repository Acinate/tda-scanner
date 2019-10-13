package database;

import com.api.TDA;
import com.database.DatabaseOld;
import com.models.History;
import com.util.DateParser;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

class TestDatabaseOld {

    private TDA tda = new TDA();
    private Date date = null;
    private History history = null;
    private DatabaseOld database = new DatabaseOld(true);
    private String baseUrl = "/Users/Jason/Projects/Java/StockScanner/src/test/resources/json/data/";
    private String symbol = "AAPL";

    @BeforeEach
    void Initialize() {
        String dateString = "05-09-2019";
        date = null;
        history = null;
        try {
            date = new DateParser().parseDate(dateString);
            history = tda.getHistory(symbol, dateString, dateString, "1d", "1m");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assertions.assertNotNull(date);
        Assertions.assertNotNull(history);
    }

    @Test
    void TestWriteHistoryToFile() {
        try {
            database.SaveHistoryToFile(symbol, history, date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        /* Check if newly created test file exists */
        String filePath = baseUrl + "/" + database.getHistoryFileURI(symbol, date);
        Path path = Paths.get(filePath);
        boolean exists = Files.exists(path);

        Assertions.assertTrue(exists);

        History history = null;
        try {
            history = database.readHistoryFromFile(symbol, date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assertions.assertNotNull(history);
    }

    @Test
    void TestGetHistoryFilePath() {
        String filePath = database.getHistoryFilePath("AAPL", date);
        Assertions.assertEquals("2019/AAPL/09", filePath);
    }

    @Test
    void TestGetHistoryFileName() {
        String fileName = database.getHistoryFileName("AAPL", date);
        Assertions.assertEquals("05-09-2019.json", fileName);
    }

    @Test
    void TestGetFileURI() {
        String fileUri = database.getHistoryFileURI("AAPL", date);
        Assertions.assertEquals("2019/AAPL/09/05-09-2019.json", fileUri);
    }

    @AfterEach
    void RemoveTestData() {
        try {
            FileUtils.cleanDirectory(new File(baseUrl));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
