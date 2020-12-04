package proghf.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import proghf.ViewManager;
import proghf.view.SearchView;
import proghf.view.TaskView;

/**
 * A keresési nézet kontrollere
 */
public class SearchController {

    /**
     * A találatok listáját tartalmazó doboz
     */
    @FXML
    private VBox resultList;

    /**
     * A keresési nézet kötése
     * <p>
     * Végigiterál a találatokon, és létrehozza a képernyőn megjelenő elemeket
     *
     * @param searchView a keresési nézet
     */
    public void bindView(SearchView searchView) {
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
                ViewManager.getInstance().setCurrentView(taskView.getView());
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

    /**
     * A vissza navigáló gomb eseménykezelője
     *
     * @param actionEvent esemény paraméter
     */
    public void onBackPressed(ActionEvent actionEvent) {
        ViewManager.getInstance().navigateBack();
    }
}
