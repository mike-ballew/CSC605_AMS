package com.example.ams_scheduler;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;


public class DatabaseManager<T> {
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
    public T getCellValue(String primaryKeyColumnName, String columnName, T primaryKeyValue, String tableName) {
        T cellValue = null;
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             Statement statement = connection.createStatement()) {
            String selectSQL = "SELECT " + columnName + " FROM " + tableName + " WHERE " + primaryKeyColumnName + " = '" + primaryKeyValue + "'";
            ResultSet resultSet = statement.executeQuery(selectSQL);
            if (resultSet.next()) {
                cellValue = (T) resultSet.getObject(columnName);
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
            String selectSQL = "SELECT COALESCE(MAX(CAST(" + primaryKeyColumn + " AS INT)), 0) + 1 AS next_key FROM " + tableName;
            ResultSet resultSet = statement.executeQuery(selectSQL);
            if (resultSet.next()) {
                nextPrimaryKey = resultSet.getInt("next_key");
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return nextPrimaryKey;
    }

    public int getRowCount(String tableName) {
        int rowCount = 0;

        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             Statement statement = connection.createStatement()) {

            // SQL query to get the row count
            String query = "SELECT COUNT(*) as row_count FROM " + tableName;
            ResultSet resultSet = statement.executeQuery(query);

            // Retrieve the row count from the result set
            if (resultSet.next()) {
                rowCount = resultSet.getInt("row_count");
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }

        return rowCount;
    }

    public List<Integer> getAllPrimaryKeys(String tableName, String primaryKeyColumnName) {
        List<Integer> primaryKeys = new ArrayList<>();
        String query = "SELECT " + primaryKeyColumnName + " FROM " + tableName;

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                primaryKeys.add(rs.getInt(primaryKeyColumnName));
            }

        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }

        return primaryKeys;
    }

    public List<String> getAllAppointments(String tableName) {
        List<String> appointments = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             Statement statement = connection.createStatement()) {

            String query = "SELECT * FROM " + tableName;
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int applianceId = resultSet.getInt("ApplianceId");
                String technician = resultSet.getString("MaintenanceID");
                String type = resultSet.getString("Type");
                String status = resultSet.getString("Status");
                String date = resultSet.getString("ScheduleDate");

                String record = "Appliance#: " + applianceId + ", Type: " + type + ", Status: " + status + ", Technician: " + technician + ", Date: " + date;
                appointments.add(record);
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return appointments;
    }
}

