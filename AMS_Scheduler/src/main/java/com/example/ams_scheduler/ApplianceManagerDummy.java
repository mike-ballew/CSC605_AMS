package com.example.ams_scheduler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ApplianceManagerDummy {

    public static Boolean addAppliance(String DATABASE_URL, String TABLE_NAME, int applianceID, String applianceType, String applianceBrand, String applianceModel,
                                String applianceLocation, String applianceImageLink, String applianceStatus, String dateOfInstall, String alias) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             Statement statement = connection.createStatement()) {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                    "(ApplianceID TEXT PRIMARY KEY, Type TEXT, Brand TEXT, Model TEXT, Location TEXT, ImageLink TEXT, Status TEXT, DateOfInstall TEXT, Alias TEXT);";
            statement.execute(createTableSQL);
            String insertDataSQL = "INSERT INTO " + TABLE_NAME + "(ApplianceID, Type, Brand, Model, Location, ImageLink, Status, DateOfInstall, Alias) VALUES " +
                    "( '" + applianceID + "', '" + applianceType + "', '" + applianceBrand + "', '" + applianceModel + "', '" + applianceLocation + "', '" + applianceImageLink + "', '" + applianceStatus + "', '" + dateOfInstall + "', '" + alias + "');";
            statement.execute(insertDataSQL);
            System.out.println("Data inserted successfully.");
            return true;
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            return false;
        }
    }


}
