package proghf.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import javafx.beans.property.SimpleStringProperty;

public class Reminder extends Task{
    @JsonIgnore
    private SimpleStringProperty text = new SimpleStringProperty();

    public Reminder(@JsonProperty("name") String name) {
        super(name);
    }

    @JsonGetter("text")
    public String getText() {
        return text.get();
    }

    public SimpleStringProperty textProperty() {
        return text;
    }

    @JsonSetter("text")
    public void setText(String text) {
        this.text.set(text);
    }
}
