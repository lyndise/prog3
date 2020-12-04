package proghf.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ReminderTest {

    @Test
    public void getText() {
        var reminder = new Reminder("");
        reminder.setText("test");
        assertEquals("test", reminder.getText());
    }

    @Test
    public void setText() {
        var reminder = new Reminder("invalid");
        reminder.setText("test");
        assertEquals("test", reminder.getText());
    }

    @Test
    public void textProperty() {
        var reminder = new Reminder("");
        reminder.setText("test");
        var property = reminder.textProperty();
        assertEquals("test", property.get());
    }
}