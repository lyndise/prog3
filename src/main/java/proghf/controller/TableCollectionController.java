package proghf.controller;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import proghf.model.Table;
import proghf.model.TableCollection;
import proghf.view.TableCollectionElementView;
import proghf.view.TableCollectionView;

import java.util.ArrayList;
import java.util.List;

public class TableCollectionController {
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
                    for (var removed: change.getRemoved()) {
                        tableCollectionElementViews.removeIf(el -> el.getTable().equals(removed));
                    }
                }
            }
            tableRows.getChildren().clear();
            for (var el: tableCollectionElementViews) {
                tableRows.getChildren().add(el.getView());
            }
        });
    }

    @FXML
    public void onAddTableButtonPressed(ActionEvent actionEvent) {
        tableCollectionView.getTableCollection().addEmptyTable();
    }
}
