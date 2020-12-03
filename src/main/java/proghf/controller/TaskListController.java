package proghf.controller;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import proghf.model.TaskItem;
import proghf.view.TaskListView;

public class TaskListController {
    @FXML
    public VBox container;
    @FXML
    public Button newButton;
    private TaskListView taskListView;
    @FXML
    private VBox taskItemList;

    public void bindView(TaskListView taskListView) {
        this.taskListView = taskListView;
        renderTaskItemList();
        taskListView.getTaskList().getTaskItems().addListener((ListChangeListener<? super TaskItem>) change -> {
            renderTaskItemList();
        });
    }

    private void renderTaskItemList() {
        taskItemList.getChildren().clear();
        for (var taskItem : taskListView.getTaskList().getTaskItems()) {
            var hbox = new HBox();
            var checkBox = new CheckBox();
            checkBox.selectedProperty().bindBidirectional(taskItem.checkedProperty());
            var taskItemField = new TextField(taskItem.getText());
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

    public void onNewPressed(ActionEvent actionEvent) {
        taskListView.getTaskList().getTaskItems().add(new TaskItem());
    }
}
