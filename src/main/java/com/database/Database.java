package com.database;

import java.sql.*;
import java.util.Set;

/**
 * This file contains the methods used to interact with the database.
 */
public class Database {
    private Connection connection = createConnection();

    /**
     * Inserts a symbol into the Symbol table.
     *
     * @param symbol The symbol to insert.
     * @return Returns 1 for successful, 0 for duplicate entry, -1 for unsuccessful
     */
    public int insertSymbol(String symbol) {
        try {
            Statement stmt = connection.createStatement();
            return stmt.executeUpdate("INSERT INTO symbol (symbol) VALUES ('" + symbol + "')");
        } catch (SQLException e) {
            // duplicate entry
            if (e.getErrorCode() == 1062) {
                return 0;
            }
            e.printStackTrace();
            return -1;
        }
    }

    public void insertSymbols(Set<String> symbols) {
        for (String symbol : symbols) {
            insertSymbol(symbol);
        }
    }

    /**
     * Create a new connection to market database.
     *
     * @return Returns a Connection object used to interact with database.
     */
    private Connection createConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/Market?serverTimezone=UTC", "root",
                    "linuxftw546");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Closes Connection object
     */
    private void closeConnection() {
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
