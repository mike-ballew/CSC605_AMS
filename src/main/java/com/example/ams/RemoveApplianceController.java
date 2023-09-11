package com.example.ams;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionModel;

import java.net.URL;
import java.util.ResourceBundle;

public class RemoveApplianceController implements Initializable {
    DatabaseManager databaseManager;
    final String TABLE_NAME_APPLIANCE_DATA = "ApplianceData";
    final String P_KEY_COL_NAME_APPLIANCE = "ApplianceID";
    @FXML private Button button_RemoveAppliance;
    @FXML private ListView<String> listView_Appliance;
    @FXML
    void handel_ButtonRemoveAppliance(ActionEvent event) {
        SelectionModel<String> selectionModel = listView_Appliance.getSelectionModel();
        String selectedText = selectionModel.getSelectedItem();
        int applianceID = Integer.parseInt(selectedText.substring(selectedText.indexOf(':')));
        databaseManager.removeDataRow(applianceID, P_KEY_COL_NAME_APPLIANCE, TABLE_NAME_APPLIANCE_DATA);

        // list view popular function
        ObservableList<String> items = FXCollections.observableArrayList ();
        listView_Appliance.setItems(items);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        databaseManager  = new DatabaseManager("jdbc:sqlite:/Users/matthewrivera/Projects/AMS/AMS_Database");

        //list view popular function
        ObservableList<String> items = FXCollections.observableArrayList ();
        listView_Appliance.setItems(items);
    }
}
