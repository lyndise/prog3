package proghf.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class TaskList extends Task {
    @JsonIgnore
    private ObservableList<TaskItem> taskItems = FXCollections.observableList(new ArrayList<>());

    public TaskList(@JsonProperty("name") String name) {
        super(name);
    }

    @JsonGetter
    public ObservableList<TaskItem> getTaskItems() {
        return taskItems;
    }

    @JsonSetter
    public void setTaskItems(List<TaskItem> taskItems) {
        this.taskItems.clear();
        this.taskItems.addAll(taskItems);
    }
}
