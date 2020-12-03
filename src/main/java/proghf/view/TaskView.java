package proghf.view;

import javafx.fxml.FXMLLoader;
import proghf.Main;
import proghf.controller.TaskController;
import proghf.model.Task;

import java.io.IOException;

public class TaskView extends Navigable {
    private Task task;

    public TaskView(Task task) {
        this.task = task;
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("task.fxml"));
        try {
            view = loader.load();
            TaskController controller = loader.getController();
            controller.bindView(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Task getTask() {
        return task;
    }
}
