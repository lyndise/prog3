package proghf.model;

import org.junit.Test;

import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class TaskTest {
    private static class DummyTask extends Task {
        public DummyTask() {
            super("");
        }
    }

    @Test
    public void getLabels() {
        Task task = new DummyTask();
        assertEquals(0, task.getLabels().size());
    }

    @Test
    public void setLabels() {
        Task task = new DummyTask();
        var labels = Stream.of(
                new Label("test"),
                new Label("test2")
        ).collect(Collectors.toCollection(HashSet::new));
        task.setLabels(labels);
        assertEquals(2, task.getLabels().size());
    }

    @Test
    public void getName() {
        Task task = new DummyTask();
        assertEquals("", task.getName());
    }

    @Test
    public void setName() {
        Task task = new DummyTask();
        task.setName("test");
        assertEquals("test", task.getName());
    }

    @Test
    public void nameProperty() {
        Task task = new DummyTask();
        var property = task.nameProperty();
        assertEquals("", property.get());
    }

    @Test
    public void getParent() {
        Task task = new DummyTask();
        assertNull(task.getParent());
    }

    @Test
    public void setParent() {
        Task task = new DummyTask();
        var parent = new Table("", null);
        task.setParent(parent);
        assertEquals(parent, task.getParent());
    }

    @Test
    public void hasAnyLabel() {
        Task task = new DummyTask();
        var labels = Stream.of(
                new Label("test"),
                new Label("test2")
        ).collect(Collectors.toCollection(HashSet::new));
        task.setLabels(labels);
        assertTrue(task.hasAnyLabel(labels));
        assertFalse(task.hasAnyLabel(new HashSet<>()));
    }
}