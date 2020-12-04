package proghf.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import javafx.beans.property.SimpleStringProperty;

/**
 * Emlékeztető feladat (szabad szöveges)
 */
public class Reminder extends Task {

    /**
     * Az emlékezető szövegét tartalmazó property,
     * amivel lehetőség van az érték változását figyelni
     */
    @JsonIgnore
    private final SimpleStringProperty text = new SimpleStringProperty();

    /**
     * Új emlékeztető létrehozása
     *
     * @param name az emlékeztető neve
     */
    public Reminder(@JsonProperty("name") String name) {
        super(name);
    }

    /**
     * Az emlékeztető szövegének lekérése
     *
     * @return az emlékeztető szövege
     */
    @JsonGetter("text")
    public String getText() {
        return text.get();
    }

    /**
     * Az emlékeztető szövegének beállítása
     *
     * @param text az emlékeztető szövege
     */
    @JsonSetter("text")
    public void setText(String text) {
        this.text.set(text);
    }

    /**
     * Az emlékeztető szövegét tartalmazó property lekérése
     *
     * @return az emlékeztető szövegét tartalmazó property
     */
    public SimpleStringProperty textProperty() {
        return text;
    }
}
