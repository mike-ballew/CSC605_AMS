package com.notificationsetting;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/*
Notification Tester
This is a basic JavaFX application with a UI for monitoring appliance status and sending notifications.
It includes mocked methods for testing purposes.
 */

public class NotificationSettingTester extends Application {
    private static final String TITLE = "Appliance Notification";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(TITLE);

        // Create UI elements
        Label nameLabel = new Label("Appliance Name:");
        Label locationLabel = new Label("Appliance Location:");
        Label statusLabel = new Label("Appliance Status:");
        Label rEmailLabel = new Label("Enter email");
        Label userPNumLabel = new Label("Enter phone number");

        TextField nameField = new TextField();
        TextField locationField = new TextField();
        TextField userEmailField = new TextField();
        TextField userPNumField = new TextField();
        Label statusText = new Label();

        Button monitorButton = new Button("Monitor Appliance");
        Button sendNotificationButton = new Button("Send Notification");

        VBox layout = new VBox(10); // Initialize the VBox layout

        // Add UI elements to the layout
        layout.getChildren().addAll(
                nameLabel, nameField,
                locationLabel, locationField,
                rEmailLabel, userEmailField,
                userPNumLabel, userPNumField,
                monitorButton, statusLabel, statusText,
                sendNotificationButton
        );

        // Set actions for buttons
        monitorButton.setOnAction(e -> {
            String applianceName = nameField.getText();
            String applianceLocation = locationField.getText();
            String applianceStatus = ApplianceStatusMonitor.monitorApplianceStatus(applianceName, applianceLocation);
            statusText.setText("Status: " + applianceStatus);
        });

        sendNotificationButton.setOnAction(e -> {
            String userEmail = userEmailField.getText();
            String userPNum = userPNumField.getText();
            String encryptedMessage = encryptionMessage(statusText.getText(), 3);
            sendEncryptedEmail(userEmail, "Appliance Status Alert", encryptedMessage);
        });

        // Create the scene and set it on the primaryStage
        Scene scene = new Scene(layout, 400, 500);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    // Mocked method for getting user's email (replace with actual logic)
    //private String getUserEmail() {
    //    return "user@example.com";
    //}

    public static class ApplianceStatusMonitor {
        // Mocked method for monitoring appliance status (replace with actual logic)
        public static String monitorApplianceStatus(String appliances, String location) {
            // Return the name of Appliances, the location, and status as a string
            boolean status = SensorReader.readSensorForApplianceStatus();
            return appliances + " in " + location + (status ? " running" : " not working");
        }
    }

    public static class SensorReader {
        // Mocked method for reading appliance status from a sensor
        public static boolean readSensorForApplianceStatus() {
            // Pretend there is a method to read a sensor and return the status
            return true;
        }
    }

    // Mocked method for encrypting a message
    private String encryptionMessage(String message, int shift) {
        StringBuilder encrypted = new StringBuilder();

        for (char c : message.toCharArray()) {
            if (Character.isLetter(c)) {
                char shiftedChar = (char) (((c - 'A' + shift) % 26) + 'A');
                encrypted.append(shiftedChar);
            } else {
                encrypted.append(c);
            }
        }

        return encrypted.toString();
    }

    // Mocked method for sending an encrypted email
    private void sendEncryptedEmail(String userEmail, String subject, String encryptedMessage) {
        // Implement your email sending logic here
        System.out.println("Email sent successfully to: " + userEmail);
    }
}