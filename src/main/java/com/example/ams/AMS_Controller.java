package com.example.ams;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import java.io.IOException;
public class AMS_Controller {
    @FXML
    private Button loginButton;

    @FXML
    private Label wrongPass;

    @FXML
    private Button createAccountButton;

    private RegistrationController registrationController;
    public void onLoginButtonClick(ActionEvent actionEvent) {
        wrongPass.setText("Wrong Login Information");
    }

    public void onCreateAccountButtonClick(ActionEvent actionEvent) {
        try {
            // Load the RegistrationDialog.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RegistrationDialog.fxml"));
            Parent root = loader.load();

            // Create a new stage for the registration dialog
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Registration");
            stage.setScene(new Scene(root));

            // Show the registration dialog
            stage.showAndWait();

            // Optionally, you can handle the registration logic here once the dialog is closed
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}