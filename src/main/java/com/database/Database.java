package com.database;

import com.models.database.Asset;
import com.models.database.Earnings;
import com.models.database.Fundamental;

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

    /**
     * Inserts an Asset record into the Assets table
     *
     * @param symbol the asset's corresponding symbol
     * @param asset  the asset object to insert
     */
    public void insertAsset(String symbol, Asset asset) {
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

    /**
     * Updates an existing Asset record in the Assets table
     *
     * @param symbol the asset's corresponding symbol
     * @param asset  the asset object to update
     */
    private void updateAsset(String symbol, Asset asset) {
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

    public void insertFundamental(String symbol, Fundamental fundamental) {
        String sql = "INSERT INTO fundamental " +
                "(symbol, high52, low52, shares_outstanding, market_cap_float," +
                "market_cap, book_value_per_share, beta, vol_1_day_avg, vol_10_day_avg," +
                "vol_3_month_avg) VALUES ('" + symbol + "', " + fundamental.getHigh52() + "," +
                fundamental.getLow52() + ", " + fundamental.getSharesOutstanding() + "," +
                fundamental.getMarketCapFloat() + "," + fundamental.getMarketCap() + "," +
                fundamental.getBookValuePerShare() + ", " + fundamental.getBeta() + ", " +
                fundamental.getVol1DayAvg() + ", " + fundamental.getVol10DayAvg() + ", " +
                fundamental.getVol3MonthAvg() + ")";
        try {
            Statement stmt = connection.createStatement();
            int result = stmt.executeUpdate(sql);
            System.out.println("Added: " + symbol);
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                System.out.println(symbol + " already exists");
                // Run an update query
                updateFundamental(symbol, fundamental);
            } else {
                System.out.println(sql);
                e.printStackTrace();
            }
        }
    }

    private void updateFundamental(String symbol, Fundamental fundamental) {
        String sql = "UPDATE fundamental " +
                "SET symbol = '" + symbol + "', " +
                "high52 = " + fundamental.getHigh52() + ", " +
                "low52 = " + fundamental.getLow52() + ", " +
                "shares_outstanding = " + fundamental.getSharesOutstanding() + ", " +
                "market_cap_float = " + fundamental.getMarketCapFloat() + ", " +
                "market_cap = " + fundamental.getMarketCap() + ", " +
                "book_value_per_share = " + fundamental.getBookValuePerShare() + ", " +
                "beta = " + fundamental.getBeta() + ", " +
                "vol_1_day_avg = " + fundamental.getVol1DayAvg() + ", " +
                "vol_10_day_avg = " + fundamental.getVol10DayAvg() + ", " +
                "vol_3_month_avg = " + fundamental.getVol3MonthAvg() + " " +
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

    public void insertEarnings(String symbol, Earnings earnings) {
        String sql = "INSERT INTO earnings " +
                "(symbol, pe_ratio, peg_ratio, pb_ratio, pr_ratio, pcf_ratio, " +
                "gross_margin_ttm, gross_margin_mrq, net_profit_margin_ttm, " +
                "net_profit_margin_mrq, operating_margin_ttm, operating_margin_mrq, " +
                "return_on_equity, return_on_assets, return_on_investment, quick_ratio, " +
                "current_ratio, interest_coverage, total_debt_to_capital, lt_debt_to_equity, " +
                "eps_ttm, eps_change_percent_ttm, eps_change_year, eps_change, rev_change_year, " +
                "rev_change_ttm, rev_change_in) VALUES ('" + symbol + "', " +
                earnings.getPeRatio() + ", " + earnings.getPegRatio() + ", " + earnings.getPbRatio() + ", " +
                earnings.getPrRatio() + ", " + earnings.getPcfRatio() + ", " + earnings.getGrossMarginTtm() + ", " +
                earnings.getGrossMarginMrq() + ", " + earnings.getNetProfitMarginTtm() + ", " +
                earnings.getNetProfitMarginMrq() + ", " + earnings.getOperatingMarginTtm() + ", " +
                earnings.getOperatingMarginMrq() + ", " + earnings.getReturnOnEquity() + ", " + earnings.getReturnOnAssets() +
                ", " + earnings.getReturnOnInvestment() + ", " + earnings.getQuickRatio() + ", " + earnings.getCurrentRatio() +
                ", " + earnings.getInterestCoverage() + ", " + earnings.getTotalDebtToCapital() + ", " +
                earnings.getLtDebtToEquity() + ", " + earnings.getEpsTtm() + ", " + earnings.getEpsChangePercentTtm() + ", " +
                earnings.getEpsChangeYear() + ", " + earnings.getEpsChange() + ", " + earnings.getRevChangeYear() + ", " +
                earnings.getRevChangeTtm() + ", " + earnings.getRevChangeIn() + ")";
        try {
            Statement stmt = connection.createStatement();
            int result = stmt.executeUpdate(sql);
            System.out.println("Added: " + symbol);
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                System.out.println(symbol + " already exists");
                // Run an update query
                // updateFundamental(symbol, fundamental);
            } else {
                System.out.println(sql);
                e.printStackTrace();
            }
        }
    }

    /**
     * Retrieves all symbols from the Symbol table
     */
    public HashSet<String> getSymbols() {
        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT symbol FROM asset WHERE cusip != 'null'");
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
