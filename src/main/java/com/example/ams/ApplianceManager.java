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

    public Boolean removeAppliance(int primaryKeyValue) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             Statement statement = connection.createStatement()) {
            String deleteRowSQL = "DELETE FROM " + TABLE_NAME + " WHERE ApplianceID = '" + primaryKeyValue + "'";
            boolean rowDeleted = statement.executeUpdate(deleteRowSQL) > 0;
            if (rowDeleted) {
                System.out.println("Row with ApplianceID = " + primaryKeyValue + " deleted successfully.");
                return true;
            } else {
                System.out.println("No rows were deleted. Row with ApplianceID = " + primaryKeyValue + " not found.");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            return false;
        }
    }

    public String getCellValue(String columnName, int primaryKeyValue) {
        String cellValue = null;
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             Statement statement = connection.createStatement()) {
            String selectSQL = "SELECT " + columnName + " FROM " + TABLE_NAME + " WHERE ApplianceID = '" + primaryKeyValue + "'";
            ResultSet resultSet = statement.executeQuery(selectSQL);
            if (resultSet.next()) {
                cellValue = resultSet.getString(columnName);
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return cellValue;
    }


    public static void main(String[] args) {
        ApplianceManager manager = new ApplianceManager();

        String searchCol = "Alias";
        for (int i = 0; i < 100; i++) {
            //manager.addAppliance(i, "Washer", "Unit 10" + String.valueOf(i), "HOLD" , "Good", "09/05/2023", "Chubby Tom");
            String result = manager.getCellValue(searchCol, i);
            System.out.println(result);
            //manager.removeAppliance(i);
        }
    }
}
