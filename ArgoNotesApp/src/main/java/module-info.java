module com.example.UserApp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires eu.hansolo.tilesfx;
    requires com.fasterxml.jackson.databind;
    requires junit;

    opens com.example.UserApp to javafx.fxml;
    exports com.example.UserApp;
    exports com.service.JSON;
    exports com.example.UserApp.Objects;
}