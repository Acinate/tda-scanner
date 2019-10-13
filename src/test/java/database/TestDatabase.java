package database;

import com.database.Database;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.lang.reflect.Method;
import java.sql.Connection;

public class TestDatabase {
    private Database database = new Database();

    @Test
    public void TestInsertSymbol() {
        int result = database.insertSymbol("AAPL");
        System.out.println(result);
    }

    @Test
    public void TestCreateConnection() throws Exception {
        Method method = Database.class.getDeclaredMethod("createConnection");
        method.setAccessible(true);
        Connection result = (Connection) method.invoke(database);
        Assertions.assertNotNull(result);
    }
}
