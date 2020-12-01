package proghf.controller;

import javafx.collections.SetChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    }

    @FXML
    public void onBackPressed(ActionEvent actionEvent) {
        TableManager.getInstance().navigateBack();
    }

    public void renderColumns() {
        columns.getChildren().clear();
        for (var view : tableColumnViews) {
            columns.getChildren().add(view.getView());
        }
    }

    public void bindView(TableView tableView) {
        this.tableView = tableView;
        tableName.setText(tableView.getTable().getName());
        tableView.getTable().getNameProperty().addListener((prop, oldName, newName) -> {
            tableName.setText(newName);
        });
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
                tableColumnViews.removeIf(view -> view.getLabel().equals(oldColumn));
            }
            renderColumns();
        });
    }

    public void onEditPressed(ActionEvent actionEvent) {
        tableNameTextField.setText(tableName.getText());
        tableName.setVisible(false);
        tableName.setPrefWidth(0.0);
        editButton.setVisible(false);
        editButton.setPrefWidth(0.0);
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
        tableNameTextField.setVisible(false);
        tableNameTextField.setPrefWidth(0.0);
        saveButton.setVisible(false);
        saveButton.setPrefWidth(0.0);
    }
}
