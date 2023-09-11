package com.example.ams;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;


public class RegistrationController {
    @FXML
    private Button createLoginButton;

    @FXML
    private TextField usernameRegistrationField;

    @FXML
    private PasswordField passwordRegistrationField;

    private Map<String, String> userLoginInfo = new HashMap<>();


    public void onCreateLoginButtonClick() {
        String username = usernameRegistrationField.getText();
        String password = passwordRegistrationField.getText();

        // Check if username or password is empty
        if (username.isEmpty() || password.isEmpty()) {
            // Display an error message or handle it as needed
            System.out.println("Username and password are required.");
            return;
        }
        // Store the username and password in the dictionary
        userLoginInfo.put(username, password);

        // Create and show a success alert
        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setTitle("Registration Successful");
        successAlert.setHeaderText(null);
        successAlert.setContentText("User registered successfully!");
        successAlert.showAndWait();

        Stage stage = (Stage) createLoginButton.getScene().getWindow();
        stage.close();
    }
}
