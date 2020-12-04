package proghf.model;

import com.fasterxml.jackson.annotation.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Feladat (absztrakt osztály)
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Reminder.class, name = "reminder"),
        @JsonSubTypes.Type(value = TaskList.class, name = "taskList")
})
public abstract class Task {

    /**
     * A feladathoz tartozó címkék halmaza
     */
    @JsonIgnore
    protected ObservableSet<Label> labels = FXCollections.observableSet(new HashSet<>());

    /**
     * A feladat nevét tartalmazó property
     */
    @JsonIgnore
    protected SimpleStringProperty name = new SimpleStringProperty();

    /**
     * A feladat szülője (tábla)
     */
    @JsonIgnore
    protected Table parent;

    /**
     * Új feladat létrehozása
     *
     * @param name a feladat neve
     */
    public Task(String name) {
        this.name.set(name);
    }

    /**
     * A feladathoz tartozó címkék lekérése
     *
     * @return a feladathoz tartozó címkék halmaza
     */
    @JsonGetter("labels")
    public ObservableSet<Label> getLabels() {
        return labels;
    }

    /**
     * A feladathoz tartozó címkék beállítása
     *
     * @param labels a feladathoz tartozó címkék halmaza
     */
    @JsonSetter("labels")
    public void setLabels(Set<Label> labels) {
        this.labels.clear();
        this.labels.addAll(labels);
    }

    /**
     * A feladat nevének lekérése
     *
     * @return a feladat neve
     */
    @JsonGetter("name")
    public String getName() {
        return name.get();
    }

    /**
     * A feladat nevének beállítása
     *
     * @param name a feladat neve
     */
    @JsonSetter("name")
    public void setName(String name) {
        this.name.set(name);
    }

    /**
     * A feladatot tartalmazó property lekérése
     *
     * @return a feladatot tartalmazó property
     */
    public SimpleStringProperty nameProperty() {
        return name;
    }

    /**
     * A feladathoz tartozó szülő lekérése (tábla)
     *
     * @return a feladathoz tartozó szülő
     */
    public Table getParent() {
        return parent;
    }

    /**
     * A feladathoz tartozó szülő beállítása
     *
     * @param table a beállítandó tábla
     */
    public void setParent(Table table) {
        parent = table;
    }

    /**
     * Megadja, hogy a paraméterként adott címkék megtalálhatóak-e a feladat címkéiben
     *
     * @param compare összehasonlítandó címkék
     * @return igaz, ha van egyezés
     */
    public boolean hasAnyLabel(Set<Label> compare) {
        var intersection = new HashSet<>(labels);
        intersection.retainAll(compare);
        return !intersection.isEmpty();
    }
}
