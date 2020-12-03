package proghf.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import proghf.TableManager;
import proghf.model.Table;
import proghf.view.*;
import proghf.view.TableView;

import java.util.ArrayList;
import java.util.List;

public class TableCollectionController {
    @FXML
    public CheckBox isArchiveChecked;
    @FXML
    public TextField searchField;
    @FXML
    private ProgressIndicator searchSpinner;
    private TableCollectionView tableCollectionView;
    private List<TableCollectionElementView> tableCollectionElementViews = new ArrayList<>();

    @FXML
    private VBox tableRows;

    public void bindView(TableCollectionView tableCollectionView) {
        this.tableCollectionView = tableCollectionView;
        tableCollectionView.getTableCollection().getTables().addListener((ListChangeListener<? super Table>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    for (var added : change.getAddedSubList()) {
                        tableCollectionElementViews.add(new TableCollectionElementView(added));
                    }
                }
                if (change.wasRemoved()) {
                    for (var removed : change.getRemoved()) {
                        tableCollectionElementViews.removeIf(el -> el.getTable().equals(removed));
                    }
                }
            }
            tableRows.getChildren().clear();
            for (var el : tableCollectionElementViews) {
                tableRows.getChildren().add(el.getView());
            }
        });
    }

    @FXML
    public void onAddTableButtonPressed(ActionEvent actionEvent) {
        var tableNameDialog = new TextInputDialog("Tábla");
        tableNameDialog.setTitle("Új tábla létrehozása");
        tableNameDialog.setHeaderText("Kérem adja meg az új tábla nevét");
        tableNameDialog.setContentText(null);
        var result = tableNameDialog.showAndWait();
        if (result.isPresent() && result.get().length() > 0) {
            tableCollectionView.getTableCollection().newTable(result.get());
        }
    }

    public void onArchivedTasksPressed(ActionEvent actionEvent) {
        var tableView = new TableView(tableCollectionView.getTableCollection().getArchive());
        TableManager.getInstance().setCurrentView(tableView.getView());
    }

    public void onSearchPressed(ActionEvent actionEvent) {
        searchSpinner.setPrefWidth(20);
        searchField.setDisable(true);
        new Timeline(new KeyFrame(Duration.millis(1000), evt -> {
            searchSpinner.setPrefWidth(0);
            searchField.setDisable(false);
            List<Table> tables = new ArrayList<>(tableCollectionView.getTableCollection().getTables());
            if (isArchiveChecked.isSelected()) {
                tables.add(tableCollectionView.getTableCollection().getArchive());
            }
            var searchView = new SearchView(searchField.getText(), tables);
            TableManager.getInstance().setCurrentView(searchView.getView());
            searchField.clear();
        })).play();
    }
}
