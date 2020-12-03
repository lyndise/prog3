package proghf.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import proghf.TableManager;
import proghf.view.TableCollectionElementView;
import proghf.view.TableView;

public class TableCollectionElementController {
    private TableCollectionElementView tableCollectionElementView;

    @FXML
    private Label tableNameLabel;

    public void bindView(TableCollectionElementView tableCollectionElementView) {
        this.tableCollectionElementView = tableCollectionElementView;
        var table = tableCollectionElementView.getTable();
        tableNameLabel.setText(table.getName());
        table.nameProperty().addListener((property, oldName, newName) -> {
            tableNameLabel.setText(newName);
        });
    }

    @FXML
    public void onDeletePressed(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Törlés megerősítése");
        alert.setHeaderText("Biztosan törli a táblát?");
        alert.setContentText("A tábla törlésével elvesznek az adatok.");
        var result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            var table = tableCollectionElementView.getTable();
            table.getParent().deleteTable(table);
        }
    }

    @FXML
    public void onOpenPressed(ActionEvent actionEvent) {
        var tableView = new TableView(tableCollectionElementView.getTable());
        TableManager.getInstance().setCurrentView(tableView.getView());
    }
}
