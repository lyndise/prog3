package proghf.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class TaskList extends Task{
    private ObservableList<TaskItem> taskItems= FXCollections.observableList(new ArrayList<>());

    public TaskList(String name) {
        super(name);
    }

    public ObservableList<TaskItem> getTaskItems() {
        return taskItems;
    }
}
