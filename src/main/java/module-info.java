module proghf {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;

    opens proghf.model to javafx.fxml, com.fasterxml.jackson.databind;
    opens proghf.controller to javafx.fxml;
    opens proghf.view to javafx.fxml;

    exports proghf;
    exports proghf.controller;
    exports proghf.model;
    exports proghf.view;
}