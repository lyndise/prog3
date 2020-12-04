package proghf.view;

import javafx.fxml.FXMLLoader;
import proghf.Main;
import proghf.controller.TaskListController;
import proghf.model.TaskList;

import java.io.IOException;

/**
 * Feladatlista nézet
 */
public class TaskListView extends View {

    /**
     * A feladatlista modellje
     */
    private final TaskList taskList;

    /**
     * Új feladatlista nézet létrehozása
     *
     * @param taskList a feladatlista modellje
     */
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

    /**
     * A feladatlista nézethez tartozó modell
     *
     * @return a feladatlista modellje
     */
    public TaskList getTaskList() {
        return taskList;
    }
}
