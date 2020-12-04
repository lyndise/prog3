package proghf.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class LabelTest {
    @Test
    public void getName() {
        var label = new Label("test");
        assertEquals("test", label.getName());
    }

    @Test
    public void setName() {
        var label = new Label("invalid");
        label.setName("test");
        assertEquals("test", label.getName());
    }

    @Test
    public void nameProperty() {
        var label = new Label("test");
        var property = label.nameProperty();
        assertEquals("test", property.get());
    }

    @Test
    public void labelEquals() {
        var label1 = new Label("test");
        var label2 = new Label("test");
        var label3 = new Label("other");
        assertEquals(label1, label2);
        assertEquals(label2, label1);
        assertNotEquals(label1, label3);
        assertNotEquals(null, label1);
        assertNotEquals(new Object(), label1);
    }

    @Test
    public void labelHashCode() {
        var label1 = new Label("test");
        var label2 = new Label("test");
        var label3 = new Label("other");
        assertEquals(label1.hashCode(), label2.hashCode());
        assertEquals(label2.hashCode(), label1.hashCode());
        assertNotEquals(label1.hashCode(), label3.hashCode());
    }
}
