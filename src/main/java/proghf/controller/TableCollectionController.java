package proghf.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import proghf.model.Table;
import proghf.model.TableCollection;

public class TableCollectionController {
    private MainController mainController;
    private TableCollection tableCollection = new TableCollection();

    @FXML
    private Button addTableButton;

    @FXML
    private TableView<Table> tableList;

    @FXML
    protected void initialize() {
        var tableNameCol = new TableColumn<Table, String>("Table name");
        tableNameCol.setMinWidth(100);
        tableNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableList.setItems(tableCollection.getTables());
        tableList.getColumns().add(tableNameCol);
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private void onAddTableButtonPressed() {
        tableCollection.AddEmptyTable();
        mainController.addNewTab();
    }
}
