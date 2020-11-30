package proghf.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
        table.getNameProperty().addListener((property, oldName, newName) -> {
            tableNameLabel.setText(newName);
        });
    }

    @FXML
    public void onDeletePressed(ActionEvent actionEvent) {
        var table = tableCollectionElementView.getTable();
        table.getParent().deleteTable(table);
    }

    @FXML
    public void onOpenPressed(ActionEvent actionEvent) {
        var tableView = new TableView(tableCollectionElementView.getTable());
        TableManager.getInstance().setCurrentView(tableView.getView());
    }
}
