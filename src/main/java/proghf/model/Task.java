package proghf.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

import java.util.HashSet;
import java.util.Set;

public abstract class Task {
    protected ObservableSet<Label> labels = FXCollections.observableSet(new HashSet<>());
    protected SimpleStringProperty name;
    protected Table parent;

    public Task(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public ObservableSet<Label> getLabels() {
        return labels;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }
    public SimpleStringProperty getNameProperty() {
        return name;
    }

    public Table getParent() {
        return parent;
    }

    public void setParent(Table table) {
        parent = table;
    }

    public boolean hasAnyLabel(Set<Label> compare) {
        var intersection = new HashSet<>(labels);
        intersection.retainAll(compare);
        return !intersection.isEmpty();
    }
}
