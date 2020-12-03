package proghf.view;

import javafx.fxml.FXMLLoader;
import proghf.Main;
import proghf.controller.ReminderController;
import proghf.controller.TaskListController;
import proghf.model.Reminder;
import proghf.model.TaskList;

import java.io.IOException;

public class TaskListView extends View {
    private TaskList taskList;

    public TaskListView(TaskList taskList) {
        this.taskList = taskList;
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("taskList.fxml"));
        try {
            view = loader.load();
            TaskListController controller = loader.getController();
            controller.bindView(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TaskList getTaskList() {
        return taskList;
    }
}
