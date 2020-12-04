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
import java.util.Set;

/**
 * Feladatokat tartalmazó tábla
 */
@JsonPropertyOrder({"name", "columns", "tasks"})
public class Table {

    /**
     * A táblán rögzített címkék (oszlopok)
     */
    @JsonIgnore
    private final ObservableSet<Label> columns = FXCollections.observableSet(new HashSet<>());

    /**
     * A táblához tartozó feladatok
     */
    @JsonIgnore
    private final ObservableList<Task> tasks = FXCollections.observableList(new ArrayList<>());

    /**
     * A tábla nevét tartalmazó property
     */
    @JsonIgnore
    private final SimpleStringProperty name = new SimpleStringProperty();

    /**
     * A tábla szülője (táblagyűjtemény)
     */
    @JsonIgnore
    private TableCollection parent;

    private Table() {}

    /**
     * Új tábla létrehozása
     *
     * @param name   a tábla neve
     * @param parent a tábla szülője (táblagyűjtemény)
     */
    public Table(String name, TableCollection parent) {
        this.name.set(name);
        this.parent = parent;
    }

    /**
     * Oszlopok lekérése
     *
     * @return a táblához tartozó címkék (oszlopok)
     */
    @JsonGetter("columns")
    public ObservableSet<Label> getColumns() {
        return columns;
    }

    /**
     * Oszlopok beállítása
     *
     * @param columns a címkéket tartalmazó halmaz
     */
    @JsonSetter("columns")
    public void setColumns(Set<Label> columns) {
        this.columns.clear();
        this.columns.addAll(columns);
    }

    /**
     * Feladatok lekérése
     *
     * @return a feladatok listája
     */
    @JsonGetter("tasks")
    public ObservableList<Task> getTasks() {
        return tasks;
    }

    /**
     * Feladatok beállítása
     *
     * @param tasks a feladatok listája
     */
    @JsonSetter("tasks")
    public void setTasks(List<Task> tasks) {
        this.tasks.clear();
        this.tasks.addAll(tasks);
    }

    /**
     * A tábla nevét tároló property lekérése
     *
     * @return a tábla nevét tároló property
     */
    public SimpleStringProperty nameProperty() {
        return name;
    }

    /**
     * A tábla nevének lekérése
     *
     * @return a tábla neve
     */
    @JsonGetter("name")
    public String getName() {
        return name.getValue();
    }

    /**
     * A tábla nevének beállítása
     *
     * @param name a tábla neve
     */
    @JsonSetter("name")
    public void setName(String name) {
        this.name.set(name);
    }

    /**
     * A tábla szülőjének lekérése (táblagyűjtemény)
     *
     * @return a tábla szülője
     */
    public TableCollection getParent() {
        return parent;
    }

    /**
     * A tábla szülőjének beállítása (táblagyűjtemény)
     *
     * @param parent a tábla szülője
     */
    public void setParent(TableCollection parent) {
        this.parent = parent;
    }

    /**
     * Egy címke törlése az oszlopok közül
     *
     * @param column a törölni kívánt címke
     */
    public void removeColumn(Label column) {
        this.columns.remove(column);
    }

    /**
     * Egy feladat eltávolítása a feladatlistából
     *
     * @param task az eltávolítandó feladat
     */
    public void deleteTask(Task task) {
        tasks.remove(task);
    }

    /**
     * Egy feladat archiválása
     *
     * @param task az archiválandó feladat
     */
    public void archiveTask(Task task) {
        if (tasks.contains(task)) {
            deleteTask(task);
            getParent().getArchive().getTasks().add(task);
            task.setParent(getParent().getArchive());
        }
    }
}
