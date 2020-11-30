package proghf.controller;

import javafx.collections.SetChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import proghf.TableManager;
import proghf.model.Table;
import proghf.view.TableColumnView;
import proghf.view.TableView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TableController {
    private TableView tableView;
    private List<TableColumnView> tableColumnViews = new ArrayList<>();
    @FXML
    private HBox columns;
    @FXML
    private Label tableName;

    @FXML
    public void onNewColumnPressed(ActionEvent actionEvent) {
        var label = new proghf.model.Label(Integer.toString(new Random().nextInt()));
        tableView.getTable().getColumns().add(label);
    }

    @FXML
    public void onBackPressed(ActionEvent actionEvent) {
        TableManager.getInstance().navigateBack();
    }

    public void renderColumns() {
        columns.getChildren().clear();
        for (var view: tableColumnViews) {
            columns.getChildren().add(view.getView());
        }
    }

    public void bindView(TableView tableView) {
        this.tableView = tableView;
        tableName.setText(tableView.getTable().getName());
        tableView.getTable().getNameProperty().addListener((prop, oldName, newName) -> {
            tableName.setText(newName);
        });
        for (var label: tableView.getTable().getColumns()) {
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

}
