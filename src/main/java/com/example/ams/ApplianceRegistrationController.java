package com.example.ams;
import javafx.collections.FXCollections;
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
    ApplianceManager applianceManager;
    DatabaseManager databaseManager;
    @FXML private Button button_CancelWindow;
    @FXML private Button button_Registration;

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
    void handelButtonRegistration(ActionEvent event) {
        final String DATABASE_URL = databaseManager.DATABASE_URL;
        final String TABLE_NAME_APPLIANCE_DATA = "ApplianceData";
        final String TABLE_NAME_IMAGE_DATA = "ApplianceImageTypeData";
        final String P_KEY_COL_NAME_APPLIANCE = "ApplianceID";
        final String P_KEY_COL_NAME_IMAGE = "Type";



        // pKey - ApplianceID
        int applianceID = databaseManager.getNextPrimaryKey(TABLE_NAME_APPLIANCE_DATA, P_KEY_COL_NAME_APPLIANCE);
        System.out.println(applianceID);
        String type = choiceBox_ApplianceType.getValue();
        String location = textFeild_Location.getText();
        String status = "pending";
        String dateOfInstall = textFeild_DateOfInstall.getText();
        String alias = textFeild_Alias.getText();
        String brand = textFeild_Model.getText();
        String model = textFeild_Brand.getText();
        String imageLink = (String) databaseManager.getCellValue(P_KEY_COL_NAME_IMAGE, "ImageLink", type, TABLE_NAME_IMAGE_DATA);


        if (type != null && !location.isEmpty() && !dateOfInstall.isEmpty() && !alias.isEmpty() && !brand.isEmpty() && !model.isEmpty()) {
            Boolean bRegistratorAddDate = applianceManager.addAppliance(DATABASE_URL, TABLE_NAME_APPLIANCE_DATA, applianceID, type, brand, model, location, imageLink, status,  dateOfInstall,  alias);
            // function that return UI output of the status of data addition
            // FMXL unit to show out put
            Stage stage = (Stage) button_CancelWindow.getScene().getWindow();
            stage.close();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        databaseManager = new DatabaseManager("jdbc:sqlite:/Users/matthewrivera/Projects/AMS/AMS_Database");
        applianceManager = new ApplianceManager();
        String[] arrApplianceTypes = { "Refrigerator", "Washing Machine", "Oven", "Microwave", "Dishwasher", "Air Conditioner", "Furnace"};
        choiceBox_ApplianceType.setItems(FXCollections.observableArrayList(arrApplianceTypes));
    }
}
