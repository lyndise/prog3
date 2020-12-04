package proghf.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonValue;
import javafx.beans.property.SimpleStringProperty;

import java.util.Objects;

/**
 * Feladat címke
 */
public class Label {

    /**
     * A címke nevét tartalmazó property,
     * amivel lehetőség van az érték változását figyelni
     */
    private final SimpleStringProperty name;

    /**
     * Új címke létrehozása
     *
     * @param name a címke neve
     */
    public Label(@JsonProperty String name) {
        this.name = new SimpleStringProperty(name);
    }

    /**
     * A címke nevének lekérése
     *
     * @return a címke neve
     */
    @JsonValue
    public String getName() {
        return name.get();
    }

    /**
     * A címke nevének beállítása
     *
     * @param name a címke neve
     */
    @JsonSetter
    public void setName(String name) {
        this.name.set(name);
    }

    /**
     * A címke nevét tartalmazó property lekérése
     *
     * @return a címke nevét tartalmazó property
     */
    public SimpleStringProperty nameProperty() {
        return name;
    }

    /**
     * Összehasonlító függvény címkékhez
     *
     * @param o az összehasonlítandó címke
     * @return igaz, ha a két címke megegyezik
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Label label = (Label) o;
        return Objects.equals(name.get(), label.name.get());
    }

    /**
     * Hash érték számító függvény (kollekciókhoz)
     *
     * @return a címke hash értéke
     */
    @Override
    public int hashCode() {
        return Objects.hash(name.get());
    }
}
