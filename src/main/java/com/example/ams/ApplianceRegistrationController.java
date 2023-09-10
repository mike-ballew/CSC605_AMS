package com.example.ams;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ApplianceRegistrationController implements Initializable {
    ApplianceManager manager = new ApplianceManager();
    @FXML private Button button_CancelWindow;
    @FXML private Button button_Registrator;
    @FXML private ChoiceBox<String> choiceBox_ApplianceType;
    @FXML private TextField textFeild_Alias;
    @FXML private TextField textFeild_DateOfInstall;
    @FXML private TextField textFeild_Location;
    @FXML private TextField textFeild_Model;
    @FXML private TextField textFeild_Brand;

    @FXML
    void handelButtonCancel(ActionEvent event) {
        Stage stage = (Stage) button_CancelWindow.getScene().getWindow();
        stage.close();
    }

    @FXML
    void handelButtonRegistrator(ActionEvent event) {
        DatabaseManager databaseManager = new DatabaseManager("jdbc:sqlite:/Users/matthewrivera/Projects/AMS/AMS_Database");
        final String DATABASE_URL = databaseManager.DATABASE_URL;
        final String TABLE_NAME = "ApplianceData";
        final String P_KEY_COL_NAME = "ApplianceID";
        ApplianceManager applianceManager = new ApplianceManager();

        // pKey - ApplianceID
        int applianceID = databaseManager.getNextPrimaryKey(TABLE_NAME, P_KEY_COL_NAME);
        String type = choiceBox_ApplianceType.getValue();
        String location = textFeild_Location.getText();
        String status = "pending";
        String dateOfInstall = textFeild_DateOfInstall.getText();
        String alias = textFeild_Alias.getText();
        String brand = textFeild_Model.getText();
        String model = textFeild_Brand.getText();

        // fun that inputs applianceType and outputs appropriate imageLink
        //String imageLink = ;

        // func that capture if string is empty.
        //Boolean bRegistratorAddDate = manager.addAppliance(applianceID, type, brand, model, location, imageLink, status,  dateOfInstall,  alias);
        // function that return UI output of the status of data addition
        // FMXL unit to show out put
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
