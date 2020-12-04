package proghf.view;

import javafx.fxml.FXMLLoader;
import proghf.Main;
import proghf.controller.TableCollectionElementController;
import proghf.model.Table;

import java.io.IOException;

/**
 * Táblagyűjtemény elemének nézete
 */
public class TableCollectionElementView extends View {

    /**
     * A nézethez tartozó tábla modellje
     */
    private Table table;

    /**
     * Új táblagyűjtemény elem létrehozása
     *
     * @param table az elemhez tartozó tábla
     */
    public TableCollectionElementView(Table table) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("tableCollectionElement.fxml"));
        try {
            view = loader.load();
            this.table = table;
            TableCollectionElementController controller = loader.getController();
            controller.bindView(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Az elemhez tartozó tábla lekérése
     *
     * @return az elemhez tartozó tábla
     */
    public Table getTable() {
        return table;
    }
}
