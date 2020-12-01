package proghf.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.paint.Color;

import java.util.Objects;

public class Label {
    private SimpleStringProperty name;

    public Label(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public String getName() {
        return name.get();
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
