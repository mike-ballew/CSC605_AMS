package com.example.ams;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AMS_Application extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(AMS_Application.class.getResource("/ApplianceManagement.fxml")); // ApplianceManagement ApplianceRegistration

        Scene scene = new Scene(fxmlLoader.load(), 994, 638); // ApplianceManagement
        //Scene scene = new Scene(fxmlLoader.load(), 400, 550); // ApplianceRegistration
        stage.setTitle("ApplianceManagemantSystem");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}