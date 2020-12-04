package proghf.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class TaskItemTest {

    @Test
    public void isChecked() {
        var taskItem = new TaskItem();
        assertFalse(taskItem.isChecked());
    }

    @Test
    public void setChecked() {
        var taskItem = new TaskItem();
        taskItem.setChecked(true);
        Assert.assertTrue(taskItem.isChecked());
    }

    @Test
    public void getText() {
        var taskItem = new TaskItem();
        assertNull(taskItem.getText());
    }

    @Test
    public void setText() {
        var taskItem = new TaskItem();
        taskItem.setText("test");
        assertEquals("test", taskItem.getText());
    }

    @Test
    public void checkedProperty() {
        var taskItem = new TaskItem();
        taskItem.setChecked(true);
        var property = taskItem.checkedProperty();
        assertTrue(property.get());
    }

    @Test
    public void textProperty() {
        var taskItem = new TaskItem();
        taskItem.setText("test");
        var property = taskItem.textProperty();
        assertEquals("test", property.get());
    }
}