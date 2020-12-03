package proghf.model;

import com.fasterxml.jackson.annotation.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.paint.Color;

import java.util.Objects;

public class Label {
    private SimpleStringProperty name;

    public Label(@JsonProperty String name) {
        this.name = new SimpleStringProperty(name);
    }

    @JsonValue
    public String getName() {
        return name.get();
    }

    @JsonSetter
    public void setName(String name) {
        this.name.set(name);
    }

    public SimpleStringProperty getNameProperty() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Label label = (Label) o;
        return Objects.equals(name.get(), label.name.get());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name.get());
    }
}
