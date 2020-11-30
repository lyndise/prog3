package proghf.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

import java.util.HashSet;
import java.util.Set;

public abstract class Task {
    protected ObservableSet<Label> labels = FXCollections.observableSet(new HashSet<>());
    protected SimpleStringProperty name;

    public Task(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public ObservableSet<Label> getLabels() {
        return labels;
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty getNameProperty() {
        return name;
    }
}
