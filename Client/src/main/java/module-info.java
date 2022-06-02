module com.example.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires jackson.databind;
    requires jackson.core;


    opens com.example.client to javafx.fxml;
    exports com.example.client;
}