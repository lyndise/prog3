package proghf.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

/**
 * Feladatlista
 */
public class TaskList extends Task {

    /**
     * A feladatlistához tartozó részfeladatok listája
     */
    @JsonIgnore
    private final ObservableList<TaskItem> taskItems = FXCollections.observableList(new ArrayList<>());

    /**
     * Új feladatlista létrehozása
     *
     * @param name az új feladatlista neve
     */
    public TaskList(@JsonProperty("name") String name) {
        super(name);
    }

    /**
     * A feladatlistához tartozó részfeladatok lekérése
     *
     * @return a feladatlista részfeladatai
     */
    @JsonGetter
    public ObservableList<TaskItem> getTaskItems() {
        return taskItems;
    }

    /**
     * A feladatlistához tartozó részfeladatok beállítása
     *
     * @param taskItems a beállítandó részfeladatok listája
     */
    @JsonSetter
    public void setTaskItems(List<TaskItem> taskItems) {
        this.taskItems.clear();
        this.taskItems.addAll(taskItems);
    }
}
