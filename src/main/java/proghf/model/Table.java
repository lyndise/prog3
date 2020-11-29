package proghf.model;

import javafx.beans.property.SimpleStringProperty;

import java.util.List;
import java.util.Set;

public class Table {
    private Set<Label> columns;
    private List<Task> tasks;
    private SimpleStringProperty name;

    public Table() {
        name = new SimpleStringProperty("");
    }

    public Table(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public Set<Label> getColumns() {
        return columns;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }
}
