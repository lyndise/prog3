package proghf.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import proghf.TableManager;
import proghf.view.SearchView;
import proghf.view.TaskView;

public class SearchController {
    @FXML
    public VBox resultList;
    private SearchView searchView;

    public void bindView(SearchView searchView) {
        this.searchView = searchView;
        searchView.getResult().forEach(task -> {
            var hbox = new HBox();
            var title = new Label(task.getName());
            title.setFont(Font.font(14));
            var pane = new Pane();
            var tableName = new Label(task.getParent().getName());
            tableName.setFont(Font.font(14));
            var openButton = new Button("Megnyitás");
            openButton.setOnAction(actionEvent -> {
                var taskView = new TaskView(task);
                TableManager.getInstance().setCurrentView(taskView.getView());
            });
            hbox.getChildren().addAll(title, pane, tableName, openButton);
            hbox.setSpacing(8.0);
            hbox.setPadding(new Insets(8, 8, 8, 8));
            HBox.setHgrow(pane, Priority.ALWAYS);
            resultList.getChildren().add(hbox);
            var separator = new Separator();
            resultList.getChildren().add(separator);
        });
    }

    public void onBackPressed(ActionEvent actionEvent) {
        TableManager.getInstance().navigateBack();
    }
}
