package proghf.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@JsonPropertyOrder({"name", "columns", "tasks"})
public class Table {
    @JsonIgnore
    private ObservableSet<Label> columns = FXCollections.observableSet(new HashSet<>());
    @JsonIgnore
    private ObservableList<Task> tasks = FXCollections.observableList(new ArrayList<>());
    @JsonIgnore
    private SimpleStringProperty name = new SimpleStringProperty();
    @JsonIgnore
    private TableCollection parent;

    public Table(String name, TableCollection parent) {
        this.name.set(name);
        this.parent = parent;
    }

    public Table() {

    }

    @JsonGetter("columns")
    public ObservableSet<Label> getColumns() {
        return columns;
    }

    @JsonSetter("columns")
    public void setColumns(List<Label> columns) {
        this.columns.clear();
        this.columns.addAll(columns);
    }

    @JsonGetter("tasks")
    public ObservableList<Task> getTasks() {
        return tasks;
    }

    @JsonSetter("tasks")
    public void setTasks(List<Task> tasks) {
        this.tasks.clear();
        this.tasks.addAll(tasks);
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    @JsonGetter("name")
    public String getName() {
        return name.getValue();
    }

    @JsonSetter("name")
    public void setName(String name) {
        this.name.set(name);
    }

    public TableCollection getParent() {
        return parent;
    }

    public void setParent(TableCollection parent) {
        this.parent = parent;
    }

    public void removeColumn(Label column) {
        this.columns.remove(column);
    }

    public void deleteTask(Task task) {
        tasks.remove(task);
    }

    public void archiveTask(Task task) {
        if (tasks.contains(task)) {
            deleteTask(task);
            getParent().getArchive().getTasks().add(task);
            task.setParent(getParent().getArchive());
        }
    }
}
