package com.example.ams;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class ApplianceManagementController implements Initializable {
    DatabaseManager databaseManager;
    @FXML private Button button_AddAppliance;
    @FXML private Button button_Home;
    @FXML private Button button_RemoveAppliance;
    @FXML private ImageView image_App_1;
    @FXML private ImageView image_App_10;
    @FXML private ImageView image_App_11;
    @FXML private ImageView image_App_12;
    @FXML private ImageView image_App_13;
    @FXML private ImageView image_App_14;
    @FXML private ImageView image_App_15;
    @FXML private ImageView image_App_16;
    @FXML private ImageView image_App_17;
    @FXML private ImageView image_App_18;
    @FXML private ImageView image_App_19;
    @FXML private ImageView image_App_2;
    @FXML private ImageView image_App_20;
    @FXML private ImageView image_App_21;
    @FXML private ImageView image_App_22;
    @FXML private ImageView image_App_23;
    @FXML private ImageView image_App_24;
    @FXML private ImageView image_App_25;
    @FXML private ImageView image_App_26;
    @FXML private ImageView image_App_27;
    @FXML private ImageView image_App_28;
    @FXML private ImageView image_App_29;
    @FXML private ImageView image_App_3;
    @FXML private ImageView image_App_30;
    @FXML private ImageView image_App_4;
    @FXML private ImageView image_App_5;
    @FXML private ImageView image_App_6;
    @FXML private ImageView image_App_7;
    @FXML private ImageView image_App_8;
    @FXML private ImageView image_App_9;
    @FXML private Label labelApplianceCount;
    @FXML private Label label_Alias_1;
    @FXML private Label label_Alias_10;
    @FXML private Label label_Alias_11;
    @FXML private Label label_Alias_12;
    @FXML private Label label_Alias_13;
    @FXML private Label label_Alias_14;
    @FXML private Label label_Alias_15;
    @FXML private Label label_Alias_16;
    @FXML private Label label_Alias_17;
    @FXML private Label label_Alias_18;
    @FXML private Label label_Alias_19;
    @FXML private Label label_Alias_2;
    @FXML private Label label_Alias_20;
    @FXML private Label label_Alias_21;
    @FXML private Label label_Alias_22;
    @FXML private Label label_Alias_23;
    @FXML private Label label_Alias_24;
    @FXML private Label label_Alias_25;
    @FXML private Label label_Alias_26;
    @FXML private Label label_Alias_27;
    @FXML private Label label_Alias_28;
    @FXML private Label label_Alias_29;
    @FXML private Label label_Alias_3;
    @FXML private Label label_Alias_30;
    @FXML private Label label_Alias_4;
    @FXML private Label label_Alias_5;
    @FXML private Label label_Alias_6;
    @FXML private Label label_Alias_7;
    @FXML private Label label_Alias_8;
    @FXML private Label label_Alias_9;
    @FXML private Label label_Status_1;
    @FXML private Label label_Status_10;
    @FXML private Label label_Status_11;
    @FXML private Label label_Status_12;
    @FXML private Label label_Status_13;
    @FXML private Label label_Status_14;
    @FXML private Label label_Status_15;
    @FXML private Label label_Status_16;
    @FXML private Label label_Status_17;
    @FXML private Label label_Status_18;
    @FXML private Label label_Status_19;
    @FXML private Label label_Status_2;
    @FXML private Label label_Status_20;
    @FXML private Label label_Status_21;
    @FXML private Label label_Status_22;
    @FXML private Label label_Status_23;
    @FXML private Label label_Status_24;
    @FXML private Label label_Status_25;
    @FXML private Label label_Status_26;
    @FXML private Label label_Status_27;
    @FXML private Label label_Status_28;
    @FXML private Label label_Status_29;
    @FXML private Label label_Status_3;
    @FXML private Label label_Status_30;
    @FXML private Label label_Status_4;
    @FXML private Label label_Status_5;
    @FXML private Label label_Status_6;
    @FXML private Label label_Status_7;
    @FXML private Label label_Status_8;
    @FXML private Label label_Status_9;

    @FXML
    void handelAddAppliance(ActionEvent event) {

    }
    @FXML
    void handelHomeButton(ActionEvent event) {

    }
    @FXML
    void handelRemoveAppliance(ActionEvent event) {

    }

    @Override // Method called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        databaseManager = new DatabaseManager("jdbc:sqlite:/Users/matthewrivera/Projects/AMS/AMS_Database");
        final String TABLE_NAME_APPLIANCE_DATA = "ApplianceData";
        final String P_KEY_COL_NAME_APPLIANCE = "ApplianceID";

        // Get appliance count and set label
        int applianceCount = databaseManager.getRowCount(TABLE_NAME_APPLIANCE_DATA);
        labelApplianceCount.setText("Appliance Count: " + applianceCount);

        // List of containers needed for updates
        Label[] aliasContainers = {label_Alias_1, label_Alias_2, label_Alias_3, label_Alias_4, label_Alias_5, label_Alias_6};
        Label[] statusContainers = {label_Status_1, label_Status_2, label_Status_3, label_Status_4, label_Status_5, label_Status_6};
        ImageView[] imageLinkContainers = {image_App_1, image_App_2, image_App_3, image_App_4, image_App_5, image_App_6};





        for (int i = 1; i < applianceCount; i++) {
            // this will cause a bug if an appliance is removed, and it's not the last appliance on the list, ill figure this out eventually
            updateDashboard(aliasContainers[i-1], statusContainers[i-1],imageLinkContainers[i-1], i);
        }
    }

    public void updateDashboard(Label alias, Label status, ImageView imageLink, int index) {
        final String TABLE_NAME_APPLIANCE_DATA = "ApplianceData";
        final String P_KEY_COL_NAME_APPLIANCE = "ApplianceID";
        alias.setText("Alias: " + (String) databaseManager.getCellValue(P_KEY_COL_NAME_APPLIANCE, "Alias", index, TABLE_NAME_APPLIANCE_DATA));
        status.setText("Status: " + (String) databaseManager.getCellValue(P_KEY_COL_NAME_APPLIANCE, "Status", index, TABLE_NAME_APPLIANCE_DATA));

        // creating the image object
        String imageFilePath = (String) databaseManager.getCellValue(P_KEY_COL_NAME_APPLIANCE, "ImageLink", index, TABLE_NAME_APPLIANCE_DATA);
        InputStream stream = null;
        try {
            stream = new FileInputStream(imageFilePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Image image = new Image(stream);
        imageLink.setImage(image);
        imageLink.setX(5);
        imageLink.setY(5);
        imageLink.setFitWidth(65);
        imageLink.setPreserveRatio(true);
    }
}

