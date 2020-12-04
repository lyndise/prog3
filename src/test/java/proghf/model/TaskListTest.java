package proghf.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class TaskListTest {

    @Test
    public void getTaskItems() {
        var taskList = new TaskList("");
        assertEquals(0, taskList.getTaskItems().size());
    }

    @Test
    public void setTaskItems() {
        var taskList = new TaskList("");
        var tasks = Stream.of(
                new TaskItem(),
                new TaskItem()
        ).collect(Collectors.toCollection(ArrayList::new));
        taskList.setTaskItems(tasks);
        var length = taskList.getTaskItems().size();
        assertEquals(2, length);
    }
}