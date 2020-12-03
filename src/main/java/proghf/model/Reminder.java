package proghf.model;

import javafx.beans.property.SimpleStringProperty;

public class Reminder extends Task{
    private SimpleStringProperty text = new SimpleStringProperty();

    public Reminder(String name) {
        super(name);
    }

    public String getText() {
        return text.get();
    }

    public SimpleStringProperty textProperty() {
        return text;
    }

    public void setText(String text) {
        this.text.set(text);
    }
}
