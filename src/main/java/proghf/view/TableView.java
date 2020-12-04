package proghf.view;

import javafx.fxml.FXMLLoader;
import proghf.Main;
import proghf.controller.TableController;
import proghf.model.Table;

import java.io.IOException;

/**
 * Tábla nézete
 */
public class TableView extends Navigable {

    /**
     * A tábla modellje
     */
    private final Table table;

    /**
     * Új táblanézet létrehozása
     *
     * @param table a tábla modellje
     */
    public TableView(Table table) {
        this.table = table;
        loadView();
    }

    /**
     * A tábla nézetet létrehozó segédfüggvény
     */
    private void loadView() {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("table.fxml"));
        try {
            view = loader.load();
            TableController controller = loader.getController();
            controller.bindView(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A tábla modelljének lekérése
     *
     * @return a tábla modellje
     */
    public Table getTable() {
        return table;
    }
}
