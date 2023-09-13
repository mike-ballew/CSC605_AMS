module com.example.ams_scheduler {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.ams_scheduler to javafx.fxml;
    exports com.example.ams_scheduler;
    exports com.example.ams;
    opens com.example.ams to javafx.fxml;
}