package proghf.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class TaskItem {
    @JsonIgnore
    private SimpleBooleanProperty checked=new SimpleBooleanProperty();
    @JsonIgnore
    private SimpleStringProperty text=new SimpleStringProperty();

    @JsonGetter("checked")
    public boolean isChecked() {
        return checked.get();
    }

    @JsonSetter("checked")
    public void setChecked(boolean checked) {
        this.checked.set(checked);
    }

    @JsonGetter("text")
    public String getText() {
        return text.get();
    }

    @JsonSetter("text")
    public void setText(String text) {
        this.text.set(text);
    }

    public SimpleBooleanProperty checkedProperty() {
        return checked;
    }

    public SimpleStringProperty textProperty() {
        return text;
    }
}
