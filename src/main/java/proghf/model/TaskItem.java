package proghf.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class TaskItem {
    private SimpleBooleanProperty checked=new SimpleBooleanProperty();
    private SimpleStringProperty text=new SimpleStringProperty();

    public boolean isChecked() {
        return checked.get();
    }

    public String getText() {
        return text.get();
    }

    public SimpleBooleanProperty checkedProperty() {
        return checked;
    }

    public SimpleStringProperty textProperty() {
        return text;
    }
}
