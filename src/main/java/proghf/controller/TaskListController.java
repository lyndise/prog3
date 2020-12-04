package proghf.controller;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import proghf.model.TaskItem;
import proghf.view.TaskListView;

/**
 * A feladatlista nézethez tartozó kontroller
 */
public class TaskListController {

    /**
     * A feladatlista nézet
     */
    private TaskListView taskListView;

    /**
     * A feladatlista nézet elemeit tartalmazó doboz
     */
    @FXML
    private VBox taskItemList;

    /**
     * A feladatlista nézet kötése
     * <p>
     * Megjeleníti a feladatlista elemeit
     *
     * @param taskListView a feladatlista nézete
     */
    public void bindView(TaskListView taskListView) {
        this.taskListView = taskListView;
        renderTaskItemList();
        taskListView.getTaskList().getTaskItems().addListener((ListChangeListener<? super TaskItem>) change -> {
            renderTaskItemList();
        });
    }

    /**
     * A feladatlista elem megjelenítő segédfüggvény
     * <p>
     * Létrehozza az elemeket és beállítja az elemekhez tartozó eseménykezelőket
     */
    private void renderTaskItemList() {
        taskItemList.getChildren().clear();
        for (var taskItem : taskListView.getTaskList().getTaskItems()) {
            var hbox = new HBox();
            var taskItemField = new TextField(taskItem.getText());
            var checkBox = new CheckBox();
            checkBox.selectedProperty().bindBidirectional(taskItem.checkedProperty());
            taskItemField.setDisable(taskItem.isChecked());
            checkBox.selectedProperty().addListener((prop, oldValue, newValue) -> {
                taskItemField.setDisable(newValue);
            });
            taskItemField.textProperty().bindBidirectional(taskItem.textProperty());
            var deleteButton = new Button("Törlés");
            deleteButton.setOnAction(actionEvent -> {
                taskListView.getTaskList().getTaskItems().remove(taskItem);
            });
            hbox.getChildren().addAll(checkBox, taskItemField, deleteButton);
            HBox.setHgrow(taskItemField, Priority.ALWAYS);
            hbox.setSpacing(8.0);
            taskItemList.getChildren().add(hbox);
            var separator = new Separator();
            separator.setPadding(new Insets(8, 0, 8, 0));
            taskItemList.getChildren().add(separator);
        }
    }

    /**
     * Új feladatlista elem felvétele gomb eseménykezelője
     *
     * @param actionEvent esemény paraméter
     */
    public void onNewPressed(ActionEvent actionEvent) {
        taskListView.getTaskList().getTaskItems().add(new TaskItem());
    }
}
