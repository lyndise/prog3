package proghf.view;

import javafx.fxml.FXMLLoader;
import proghf.Main;
import proghf.controller.TableColumnController;
import proghf.model.Label;
import proghf.model.Table;

import java.io.IOException;

/**
 * Táblaoszlop nézete
 */
public class TableColumnView extends View {

    /**
     * Az oszlophoz tartozó tábla modellje
     */
    private final Table table;

    /**
     * Az oszlophoz tartozó címke
     */
    private final Label label;

    /**
     * A nézethez tartozó kontroller
     */
    private TableColumnController controller;

    /**
     * Új táblaoszlop nézet létrehozása
     * @param table az oszlophoz tartozó tábla
     * @param label az oszlophoz tartozó címke
     */
    public TableColumnView(Table table, Label label) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("tableColumn.fxml"));
        this.table = table;
        this.label = label;
        try {
            view = loader.load();
            controller = loader.getController();
            controller.bindView(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A tábla modelljének lekérése
     * @return a tábla modellje
     */
    public Table getTable() {
        return table;
    }

    /**
     * Az oszlophoz tartozó címke lekérése
     * @return az oszlophoz tartozó címke
     */
    public Label getLabel() {
        return label;
    }

    /**
     * Az oszlop törlése
     */
    public void delete() {
        if (label != null) {
            table.removeColumn(label);
        }
    }

    /**
     * Az oszlop újrarajzolása
     */
    public void refresh() {
        controller.renderTasks();
    }
}
