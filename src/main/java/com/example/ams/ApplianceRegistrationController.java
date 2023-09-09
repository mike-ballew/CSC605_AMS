package com.example.ams;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ApplianceRegistrationController implements Initializable {

    @FXML private Button button_CancelWindow;
    @FXML private Button button_Registrator;
    @FXML private ChoiceBox<String> choiceBox_ApplianceType;
    @FXML private ChoiceBox<String> choiceBox_Priority;
    @FXML private TextField textFeild_Alias;
    @FXML private TextField textFeild_DateOfInstall;
    @FXML private TextField textFeild_Location;

    @FXML
    void handelButtonCancel(ActionEvent event) {
    
    }

    @FXML
    void handelButtonRegistrator(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
