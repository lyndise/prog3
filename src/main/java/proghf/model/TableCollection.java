package proghf.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Táblagyűjtemény
 */
public class TableCollection {

    /**
     * A táblák listája
     */
    @JsonIgnore
    private final ObservableList<Table> tables = FXCollections.observableList(new ArrayList<>());

    /**
     * Az archivált elemek táblája
     */
    private final Table archive = new Table("Archivált elemek", this);

    /**
     * A gyűjteményhez tartozó táblák lekérése
     *
     * @return a gyűjteményhez tartozó táblák listája
     */
    @JsonGetter
    public ObservableList<Table> getTables() {
        return tables;
    }

    /**
     * A gyűjteményhez tartozó táblák beállítása
     *
     * @param tables a gyűjteményhez tartozó táblák listája
     */
    @JsonSetter
    public void setTables(List<Table> tables) {
        this.tables.clear();
        this.tables.addAll(tables);
    }

    /**
     * Az archivált elemeket tartalmazó tábla lekérése
     *
     * @return az archivált elemeket tartalmazó tábla
     */
    public Table getArchive() {
        return archive;
    }

    /**
     * Új tábla létrehozása
     *
     * @param name az új tábla neve
     */
    public void newTable(String name) {
        this.tables.add(new Table(name, this));
    }

    /**
     * Tábla törlése
     *
     * @param table a törlendő tábla
     */
    public void deleteTable(Table table) {
        tables.remove(table);
    }

    /**
     * Táblagyűjtemény betöltése fájlból
     * <p>
     * A betöltés során a fájl automatikusan lezárásra kerül
     *
     * @param file a használni kívánt fájl
     * @return a betöltött táblagyűjtemény
     * @throws IOException olvasási vagy deszerializálási hiba
     */
    public static TableCollection loadFrom(File file) throws IOException {
        return new ObjectMapper().readValue(file, TableCollection.class);
    }

    /**
     * Táblagyűjtemény mentése fájlba
     * <p>
     * A mentés során a fájl automatikusan lezárásra kerül
     *
     * @param file a használni kívánt fájl
     * @throws IOException írási vagy szerializálási hiba
     */
    public void saveTo(File file) throws IOException {
        new ObjectMapper().writeValue(file, this);
    }
}
