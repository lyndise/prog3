package proghf.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import proghf.TableManager;
import proghf.model.Task;
import proghf.view.TableView;
import proghf.view.TaskView;

public class TaskController {
    private Task task;
    private TaskView taskView;
    @FXML
    public AnchorPane taskBody;
    @FXML
    public ListView labelListView;

    @FXML
    private void initialize(){

    }

    public void bindView(TaskView taskView) {
        this.taskView=taskView;
    }

    public void onBackPressed(ActionEvent actionEvent) {
        TableManager.getInstance().navigateBack();
    }

    public void onDeletePressed(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Törlés megerősítése");
        alert.setHeaderText("Biztosan törli a feladatot?");
        alert.setContentText("A feladat törlésével elvesznek az adatok.");
        var result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            var task = taskView.getTask();
            task.getParent().deleteTask(task);
        }
        TableManager.getInstance().navigateBack();
    }
}
