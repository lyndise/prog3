package proghf.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import proghf.view.TaskView;

public class TaskController {
    @FXML
    public AnchorPane taskBody;
    @FXML
    public ListView labelListView;

    @FXML
    private void initialize(){

    }

    public void bindView(TaskView taskView) {
    }

    public void onBackPressed(ActionEvent actionEvent) {
    }
}
