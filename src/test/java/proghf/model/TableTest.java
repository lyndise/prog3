package proghf.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class TableTest {

    @Test
    public void getColumns() {
        var table = new Table("test", null);
        var columns = table.getColumns();
        var length = columns.size();
        assertEquals(0, length);
    }

    @Test
    public void setColumns() {
        var table = new Table("test", null);
        var columns = Stream.of(
                new Label("test"),
                new Label("test2")
        ).collect(Collectors.toCollection(HashSet::new));
        table.setColumns(columns);
        var length = table.getColumns().size();
        assertEquals(2, length);
    }

    @Test
    public void getTasks() {
        var table = new Table("test", null);
        var tasks = table.getTasks();
        var length = tasks.size();
        assertEquals(0, length);
    }

    @Test
    public void setTasks() {
        var table = new Table("test", null);
        var tasks = Stream.of(
                new Reminder("test"),
                new TaskList("test2")
        ).collect(Collectors.toCollection(ArrayList::new));
        table.setTasks(tasks);
        var length = table.getTasks().size();
        assertEquals(2, length);
    }

    @Test
    public void nameProperty() {
        var table = new Table("test", null);
        table.setName("test");
        var property = table.nameProperty();
        assertEquals("test", property.get());
    }

    @Test
    public void getName() {
        var table = new Table("test", null);
        assertEquals("test", table.getName());
    }

    @Test
    public void setName() {
        var table = new Table("invalid", null);
        table.setName("test");
        assertEquals("test", table.getName());
    }

    @Test
    public void getParent() {
        var parent = new TableCollection();
        var table = new Table("test", parent);
        assertEquals(parent, table.getParent());
    }

    @Test
    public void setParent() {
        var table = new Table("test", null);
        var parent = new TableCollection();
        table.setParent(parent);
        assertEquals(parent, table.getParent());
    }

    @Test
    public void removeColumn() {
        var table = new Table("test", null);
        var columns = Stream.of(
                new Label("test"),
                new Label("test2")
        ).collect(Collectors.toCollection(HashSet::new));
        table.setColumns(columns);
        table.removeColumn(new Label("test"));
        var length = table.getColumns().size();
        assertEquals(1, length);
    }

    @Test
    public void deleteTask() {
        var table = new Table("test", null);
        var task = new Reminder("test");
        table.getTasks().add(task);
        assertEquals(1, table.getTasks().size());
        table.deleteTask(task);
        assertEquals(0, table.getTasks().size());
    }

    @Test
    public void archiveTask() {
        var parent = new TableCollection();
        var table = new Table("test", parent);
        var task = new Reminder("test");
        table.getTasks().add(task);
        table.archiveTask(task);
        assertEquals(0, table.getTasks().size());
        assertEquals(1, parent.getArchive().getTasks().size());
    }
}