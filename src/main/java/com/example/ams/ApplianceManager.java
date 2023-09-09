package com.example.ams;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class ApplianceManager {

    public final String DATABASE_URL = "jdbc:sqlite:/Users/matthewrivera/Projects/AMS/AMS_Database";
    public final String TABLE_NAME = "ApplianceData";
    public Boolean addAppliance(int applianceID, String applianceType, String applianceLocation, String applianceImageLink, String applianceStatus, String dateOfInstall, String alias) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             Statement statement = connection.createStatement()) {
                String createTableSQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(ApplianceID TEXT PRIMARY KEY, ApplianceType TEXT, ApplianceLocation TEXT, ApplianceImageLink TEXT, ApplianceStatus TEXT, DateOfInstall TEXT, Alias TEXT);";
                statement.execute(createTableSQL);
                String insertDataSQL = "INSERT INTO ApplianceData (ApplianceID, ApplianceType, ApplianceLocation, " +
                    "ApplianceImageLink, ApplianceStatus, DateOfInstall, Alias) VALUES " +
                    "( '" + applianceID + "', '" + applianceType+ "', '" + applianceLocation+ "', '" + applianceImageLink + "', '" + applianceStatus + "', '" + dateOfInstall + "', '" + alias + "');";
                statement.execute(insertDataSQL);
                System.out.println("Data inserted successfully.");
                return true;
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
                return false;
        }
    }

    public Boolean removeAppliance(int primaryKeyValue, String primaryKeyColumnName) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             Statement statement = connection.createStatement()) {
            String deleteRowSQL = "DELETE FROM " + TABLE_NAME + " WHERE " + primaryKeyColumnName + " = '" + primaryKeyValue + "'";
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

    public String getCellValue(String primaryKeyColumnName, String columnName, int primaryKeyValue) {
        String cellValue = null;
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             Statement statement = connection.createStatement()) {
            String selectSQL = "SELECT " + columnName + " FROM " + TABLE_NAME + " WHERE " + primaryKeyColumnName + " = '" + primaryKeyValue + "'";
            ResultSet resultSet = statement.executeQuery(selectSQL);
            if (resultSet.next()) {
                cellValue = resultSet.getString(columnName);
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return cellValue;
    }
    public Boolean updateCellValue(String columnName, String primaryKeyColumn, int primaryKeyValue, String newValue) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             Statement statement = connection.createStatement()) {

            // SQL command to update the cell value
            String updateSQL = "UPDATE " + TABLE_NAME + " SET " + columnName + " = '" + newValue + "'" + " WHERE " + primaryKeyColumn + " = '" + primaryKeyValue + "'";

            // Execute the update command
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

    public static void main(String[] args) {
        ApplianceManager manager = new ApplianceManager();

        String searchCol = "Alias";
        String pKeyColName = "ApplianceID";
        String newValue = "Chubbier Tom";
        for (int i = 0; i < 100; i++) {
            //manager.addAppliance(i, "Washer", "Unit 10" + String.valueOf(i), "HOLD" , "Good", "09/05/2023", "Chubby Tom");
            //String result = manager.getCellValue(pKeyColName, searchCol, i);
            //System.out.println(result);
            //manager.removeAppliance(i, pKeyColName);
            manager.updateCellValue(searchCol, pKeyColName, i, newValue);
        }
    }
}
