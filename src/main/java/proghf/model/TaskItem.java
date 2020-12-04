package proghf.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Feladatlista eleme
 */
public class TaskItem {

    /**
     * A feladatlista elemének jelölési állapotát tároló property
     */
    @JsonIgnore
    private final SimpleBooleanProperty checked = new SimpleBooleanProperty();

    /**
     * A feladatlista elem szövegét tároló property
     */
    @JsonIgnore
    private final SimpleStringProperty text = new SimpleStringProperty();

    /**
     * A feladatlista elemének jelölésre vonatkozó állapotának lekérése
     *
     * @return igaz, ha a feladatlista elem megjelölt állapotú
     */
    @JsonGetter("checked")
    public boolean isChecked() {
        return checked.get();
    }

    /**
     * A feladatlista elemének jelölésére vonatkozó beállítás
     *
     * @param checked jelölési állapot
     */
    @JsonSetter("checked")
    public void setChecked(boolean checked) {
        this.checked.set(checked);
    }

    /**
     * A feladatlista elem szövegének lekérése
     *
     * @return a feladatlista elem szövege
     */
    @JsonGetter("text")
    public String getText() {
        return text.get();
    }

    /**
     * A feladatlista elem szövegének beállítása
     *
     * @param text a beállítandó szöveg
     */
    @JsonSetter("text")
    public void setText(String text) {
        this.text.set(text);
    }

    /**
     * A feladatlista elemének jelölésre vonatkozó állapotát tároló property lekérése
     *
     * @return a jelölésre vonatkozó property
     */
    public SimpleBooleanProperty checkedProperty() {
        return checked;
    }

    /**
     * A feladatlista szövegét tároló property lekérése
     *
     * @return a feladatlista szövegét tartalmazó property
     */
    public SimpleStringProperty textProperty() {
        return text;
    }
}
