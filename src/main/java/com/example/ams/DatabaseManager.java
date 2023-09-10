package com.example.ams;

import java.sql.*;

public class DatabaseManager {
    public final String DATABASE_URL;

    public DatabaseManager(String DATABASE_URL) {
        this.DATABASE_URL = DATABASE_URL;
    }
    public Boolean removeDataRow(int primaryKeyValue, String primaryKeyColumnName, String tableName) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             Statement statement = connection.createStatement()) {
            String deleteRowSQL = "DELETE FROM " + tableName + " WHERE " + primaryKeyColumnName + " = '" + primaryKeyValue + "'";
            boolean rowDeleted = statement.executeUpdate(deleteRowSQL) > 0;
            if (rowDeleted) {
                System.out.println("Row with " + primaryKeyColumnName + " = " + primaryKeyValue + " deleted successfully.");
                return true;
            } else {
                System.out.println("No rows were deleted. Row with " + primaryKeyColumnName + " = " + primaryKeyValue + " not found.");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            return false;
        }
    }

    public String getCellValue(String primaryKeyColumnName, String columnName, int primaryKeyValue, String tableName) {
        String cellValue = null;
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             Statement statement = connection.createStatement()) {
            String selectSQL = "SELECT " + columnName + " FROM " + tableName + " WHERE " + primaryKeyColumnName + " = '" + primaryKeyValue + "'";
            ResultSet resultSet = statement.executeQuery(selectSQL);
            if (resultSet.next()) {
                cellValue = resultSet.getString(columnName);
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return cellValue;
    }
    public Boolean updateCellValue(String columnName, String primaryKeyColumn, int primaryKeyValue, String newValue, String tableName) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             Statement statement = connection.createStatement()) {
            String updateSQL = "UPDATE " + tableName + " SET " + columnName + " = '" + newValue + "'" + " WHERE " + primaryKeyColumn + " = '" + primaryKeyValue + "'";
            int rowsUpdated = statement.executeUpdate(updateSQL);
            if (rowsUpdated > 0) {
                System.out.println("Cell value updated successfully.");
                return true;
            } else {
                System.out.println("No rows were updated. Row with " + primaryKeyColumn + " = " + primaryKeyValue + " not found.");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            return false;
        }
    }
    public int getNextPrimaryKey(String tableName, String primaryKeyColumn) {
        int nextPrimaryKey = 1; // Default value if no rows exist
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             Statement statement = connection.createStatement()) {
            String selectSQL = "SELECT COALESCE(MAX(" + primaryKeyColumn + "), 0) + 1 AS next_key FROM " + tableName;
            ResultSet resultSet = statement.executeQuery(selectSQL);
            if (resultSet.next()) {
                nextPrimaryKey = resultSet.getInt("next_key");
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return nextPrimaryKey;
    }
}

