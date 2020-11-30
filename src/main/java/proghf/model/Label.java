package proghf.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.paint.Color;

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
}
