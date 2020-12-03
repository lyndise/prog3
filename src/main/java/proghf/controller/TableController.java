package proghf.controller;

import javafx.collections.ListChangeListener;
import javafx.collections.SetChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import proghf.TableManager;
import proghf.view.TableColumnView;
import proghf.view.TableView;

import java.util.ArrayList;
import java.util.List;

public class TableController {
    @FXML
    public TextField tableNameTextField;
    @FXML
    public Button saveButton;
    @FXML
    public Button editButton;
    @FXML
    public Button deleteButton;
    @FXML
    private TextField newColumnName;
    private TableView tableView;
    private List<TableColumnView> tableColumnViews = new ArrayList<>();
    @FXML
    private HBox columns;
    @FXML
    private Label tableName;

    @FXML
    public void onNewColumnPressed(ActionEvent actionEvent) {
        if (newColumnName.getText().length() > 0) {
            var label = new proghf.model.Label(newColumnName.getText());
            tableView.getTable().getColumns().add(label);
        }
        newColumnName.clear();
    }

    @FXML
    public void onBackPressed(ActionEvent actionEvent) {
        TableManager.getInstance().navigateBack();
    }

    public void renderColumns() {
        columns.getChildren().clear();
        for (var view : tableColumnViews) {
            columns.getChildren().add(view.getView());
            if (view.getLabel() == null) {
                view.refresh();
            }
        }
    }

    public void bindView(TableView tableView) {
        this.tableView = tableView;
        tableName.setText(tableView.getTable().getName());
        tableView.getTable().getNameProperty().addListener((prop, oldName, newName) -> {
            tableName.setText(newName);
        });
        tableColumnViews.add(new TableColumnView(tableView.getTable(), null));
        for (var label : tableView.getTable().getColumns()) {
            tableColumnViews.add(new TableColumnView(tableView.getTable(), label));
        }
        renderColumns();
        tableView.getTable().getColumns().addListener((SetChangeListener<? super proghf.model.Label>) change -> {
            if (change.wasAdded()) {
                var newColumn = change.getElementAdded();
                var view = new TableColumnView(tableView.getTable(), newColumn);
                columns.getChildren().add(view.getView());
                tableColumnViews.add(view);
            }
            if (change.wasRemoved()) {
                var oldColumn = change.getElementRemoved();
                tableColumnViews.removeIf(view -> view.getLabel() != null && view.getLabel().equals(oldColumn));
            }
            renderColumns();
        });
        if(tableView.getTable()==tableView.getTable().getParent().getArchive()){
            editButton.setVisible(false);
            editButton.setPrefWidth(0.0);
            deleteButton.setVisible(false);
            deleteButton.setPrefWidth(0.0);
        }
    }

    public void onEditPressed(ActionEvent actionEvent) {
        tableNameTextField.setText(tableName.getText());
        tableName.setVisible(false);
        tableName.setPrefWidth(0.0);
        editButton.setVisible(false);
        editButton.setPrefWidth(0.0);
        deleteButton.setVisible(false);
        deleteButton.setPrefWidth(0.0);
        tableNameTextField.setVisible(true);
        tableNameTextField.setPrefWidth(-1);
        saveButton.setVisible(true);
        saveButton.setPrefWidth(-1);
    }

    public void onSavePressed(ActionEvent actionEvent) {
        if (tableNameTextField.getText().length() > 0) {
            tableView.getTable().setName(tableNameTextField.getText());
        }
        tableName.setVisible(true);
        tableName.setPrefWidth(-1);
        editButton.setVisible(true);
        editButton.setPrefWidth(-1);
        deleteButton.setVisible(true);
        deleteButton.setPrefWidth(-1);
        tableNameTextField.setVisible(false);
        tableNameTextField.setPrefWidth(0.0);
        saveButton.setVisible(false);
        saveButton.setPrefWidth(0.0);
    }

    public void onDeletePressed(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Törlés megerősítése");
        alert.setHeaderText("Biztosan törli a táblát?");
        alert.setContentText("A tábla törlésével elvesznek az adatok.");
        var result = alert.showAndWait();
        if (result.isPresent()&&result.get() == ButtonType.OK) {
            var table = tableView.getTable();
            table.getParent().deleteTable(table);
            TableManager.getInstance().navigateBack();
        }
    }
}
