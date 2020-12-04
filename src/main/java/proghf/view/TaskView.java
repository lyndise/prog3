package proghf.view;

import javafx.fxml.FXMLLoader;
import proghf.Main;
import proghf.controller.TaskController;
import proghf.model.Task;

import java.io.IOException;

/**
 * Feladat nézet
 */
public class TaskView extends Navigable {

    /**
     * A feladat modellje
     */
    private final Task task;

    /**
     * Új feladat nézet létrehozása
     *
     * @param task a feladat modellje
     */
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

    /**
     * A nézethez tartozó feladat lekérése
     *
     * @return a feladat modellje
     */
    public Task getTask() {
        return task;
    }
}
