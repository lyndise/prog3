package proghf.model;

import java.util.List;

public class TaskList extends Task{
    private List<TaskItem> taskItems;

    public TaskList(String name) {
        super(name);
    }

    public List<TaskItem> getTaskItems() {
        return taskItems;
    }
}
