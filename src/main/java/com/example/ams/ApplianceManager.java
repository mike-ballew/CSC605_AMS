package com.example.ams;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class ApplianceManager {

    public Boolean addAppliance(String DATABASE_URL, String TABLE_NAME, int applianceID, String applianceType, String applianceBrand, String applianceModel, String applianceLocation, String applianceImageLink, String applianceStatus, String dateOfInstall, String alias) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             Statement statement = connection.createStatement()) {
                String createTableSQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(ApplianceID TEXT PRIMARY KEY, Type TEXT, Brand TEXT, Model TEXT, Location TEXT, ImageLink TEXT, Status TEXT, DateOfInstall TEXT, Alias TEXT);";
                statement.execute(createTableSQL);
                String insertDataSQL = "INSERT INTO ApplianceData (ApplianceID, Type, Brand, Model, Location, ImageLink, Status, DateOfInstall, Alias) VALUES " +
                    "( '" + applianceID + "', '" + applianceType + "', '" + applianceBrand + "', '" + applianceModel + "', '" + applianceLocation+ "', '" + applianceImageLink + "', '" + applianceStatus + "', '" + dateOfInstall + "', '" + alias + "');";
                statement.execute(insertDataSQL);
                System.out.println("Data inserted successfully.");
                return true;
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
                return false;
        }
    }
    public Boolean addApplianceTypeImageLink(String DATABASE_URL, String TABLE_NAME, String applianceType, String applianceImageLink) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             Statement statement = connection.createStatement()) {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(ApplianceID TEXT PRIMARY KEY, Type TEXT, ImageLink TEXT);";
            statement.execute(createTableSQL);
            String insertDataSQL = "INSERT INTO ApplianceData (ApplianceID, Type, ImageLink) VALUES " +
                    "( '" + applianceType + "', '" + applianceImageLink  + "');";
            statement.execute(insertDataSQL);
            System.out.println("Data inserted successfully.");
            return true;
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        DatabaseManager databaseManager = new DatabaseManager("jdbc:sqlite:/Users/matthewrivera/Projects/AMS/AMS_Database");
        final String DATABASE_URL = databaseManager.DATABASE_URL;
        final String TABLE_NAME = "ApplianceData";

        ApplianceManager applianceManager = new ApplianceManager();

        String searchCol = "Alias";
        String pKeyColName = "ApplianceID";
        String newValue = "Chubbier Tom";
        /*
        for (int i = 0; i < 100; i++) {
            //manager.addAppliance(i, "Washer", "GE", "a3000HD", "Unit 10" + String.valueOf(i), "HOLD" , "Good", "09/05/2023", "Chubby Tom");
            //String result = manager.getCellValue(pKeyColName, searchCol, i);
            //System.out.println(result);
            //manager.removeAppliance(i, pKeyColName);
            //manager.updateCellValue(searchCol, pKeyColName, i, newValue);
        }
        */
        //int nextKey = databaseManager.getNextPrimaryKey("ApplianceData", pKeyColName);
        //System.out.println(nextKey);

        // ADD IMAGE LINKS
        applianceManager.addAppliance();
        /*
            Refrigerator
            Washing Machine
            Oven
            Microwave
            Dishwasher
            Air Conditioner
            Heater or Furnace
        */

    }
}
