package proghf.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Table {
    private ObservableSet<Label> columns = FXCollections.observableSet(new HashSet<>());
    private ObservableList<Task> tasks = FXCollections.observableList(new ArrayList<>());
    private SimpleStringProperty name;
    private TableCollection parent;

    public Table(String name, TableCollection parent) {
        this.name = new SimpleStringProperty(name);
        this.parent = parent;
    }

    public ObservableSet<Label> getColumns() {
        return columns;
    }

    public ObservableList<Task> getTasks() {
        return tasks;
    }

    public SimpleStringProperty getNameProperty() {
        return name;
    }

    public String getName() {
        return name.getValue();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public TableCollection getParent() {
        return parent;
    }

    public void removeColumn(Label column) {
        this.columns.remove(column);
    }
}
