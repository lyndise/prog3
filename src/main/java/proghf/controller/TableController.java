package proghf.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TableController {
    @FXML
    private HBox columns;

    @FXML
    private void onAddColumnButtonPressed() {
        var vbox = new VBox();
        vbox.getChildren().add(new Label("Column"));
        columns.getChildren().add(vbox);
    }
}
