package proghf.view;

import javafx.fxml.FXMLLoader;
import proghf.Main;
import proghf.controller.TableCollectionController;
import proghf.model.TableCollection;

import java.io.IOException;

/**
 * Táblagyűjtemény nézet
 */
public class TableCollectionView extends Navigable {

    /**
     * A nézethez tartozó modell
     */
    private TableCollection tableCollection;

    /**
     * Új táblagyűjtemény nézet létrehozása
     * @param tableCollection a táblagyűjtemény
     */
    public TableCollectionView(TableCollection tableCollection) {
        setTableCollection(tableCollection);
    }

    /**
     * A nézethez tartozó modell lekérése
     * @return a nézethez tartozó táblagyűjtemény
     */
    public TableCollection getTableCollection() {
        return tableCollection;
    }

    /**
     * A táblagyűjtemény beállítása
     *
     * Létrehozza a táblagyűjtemény nézetet és hozzáköti a kontrollert
     * @param tableCollection a táblagyűjtemény modellje
     */
    public void setTableCollection(TableCollection tableCollection) {
        this.tableCollection = tableCollection;
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("tableCollection.fxml"));
        try {
            view = loader.load();
            TableCollectionController controller = loader.getController();
            controller.bindView(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Új tábla létrehozása a gyűjteményben
     * @param name az új tábla neve
     */
    public void createTable(String name) {
        tableCollection.newTable(name);
    }
}
