package com.database;

import com.models.database.Asset;
import com.models.database.Symbol;

import java.sql.*;
import java.util.HashSet;
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

    /**
     * Inserts a list of symbols into the Symbol table
     *
     * @param symbols the list of symbols to insert
     */
    public void insertSymbols(Set<String> symbols) {
        for (String symbol : symbols) {
            insertSymbol(symbol);
        }
    }

    public void insertAsset(String symbol, Asset asset) {
        if (asset == null) {
            System.out.println("asset null");
        } else if (asset.getCusip() == null) {
            System.out.println("found one");
        } else if (asset.getCusip().equals("null")) {
            System.out.println("found one");
        }
        String sql = "INSERT INTO asset " +
                "(symbol, cusip, description, " +
                "exchange, asset_type, sector) " +
                "VALUES ('" + symbol + "', " +
                "'" + asset.getCusip() + "', " +
                "'" + asset.getDescription() + "', " +
                "'" + asset.getExchange() + "', " +
                "'" + asset.getAssetType() + "', " +
                "'" + asset.getSector() + "')";
        try {
            Statement stmt = connection.createStatement();
            int result = stmt.executeUpdate(sql);
            System.out.println("Added: " + symbol);
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                System.out.println(symbol + " already exists");
                // Run an update query
                updateAsset(symbol, asset);
            } else {
                System.out.println(sql);
                e.printStackTrace();
            }
        }
    }

    public void updateAsset(String symbol, Asset asset) {
        String sql = "UPDATE asset " +
                "SET symbol = '" + symbol + "', " +
                "cusip = '" + asset.getCusip() + "', " +
                "description = '" + asset.getDescription() + "', " +
                "exchange = '" + asset.getExchange() + "', " +
                "asset_type = '" + asset.getAssetType() + "', " +
                "sector = '" + asset.getSector() + "' " +
                "WHERE symbol = '" + symbol + "';";
        try {
            Statement stmt = connection.createStatement();
            int result = stmt.executeUpdate(sql);
            System.out.println("Updated: " + symbol);
        } catch (SQLException e) {
            System.out.println(sql);
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all symbols from the Symbol table
     */
    public HashSet<String> getSymbols() {
        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT symbol FROM asset WHERE cusip = 'null'");
            HashSet<String> symbols = new HashSet<>();
            while (resultSet.next()) {
                String symbol = resultSet.getString("symbol");
                symbols.add(symbol);
            }
            return symbols;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
