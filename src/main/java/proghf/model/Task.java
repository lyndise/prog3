package proghf.model;

import com.fasterxml.jackson.annotation.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Reminder.class, name = "reminder"),
        @JsonSubTypes.Type(value = TaskList.class, name = "taskList")
})
public abstract class Task {
    @JsonIgnore
    protected ObservableSet<Label> labels = FXCollections.observableSet(new HashSet<>());
    @JsonIgnore
    protected SimpleStringProperty name = new SimpleStringProperty();
    @JsonIgnore
    protected Table parent;

    public Task(String name) {
        this.name.set(name);
    }

    @JsonGetter("labels")
    public ObservableSet<Label> getLabels() {
        return labels;
    }

    @JsonSetter("labels")
    public void setLabels(List<Label> labels) {
        this.labels.clear();
        this.labels.addAll(labels);
    }

    @JsonGetter("name")
    public String getName() {
        return name.get();
    }

    @JsonSetter("name")
    public void setName(String name) {
        this.name.set(name);
    }

    public SimpleStringProperty nameProperty() {
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
