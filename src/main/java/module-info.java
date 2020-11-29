module proghf {
    requires javafx.controls;
    requires javafx.fxml;

    opens proghf to javafx.fxml;
    opens proghf.model to javafx.fxml;
    opens proghf.controller to javafx.fxml;

    exports proghf;
    exports proghf.controller;
    exports proghf.model;
}