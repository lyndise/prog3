package proghf.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import proghf.ViewManager;
import proghf.view.TableCollectionElementView;
import proghf.view.TableView;

/**
 * A táblagyűjtemény lista egy elemének kontrollere
 */
public class TableCollectionElementController {

    /**
     * A táblagyűjtemény listaelem nézete
     */
    private TableCollectionElementView tableCollectionElementView;

    /**
     * A tábla nevét megjelenítő szövegelem
     */
    @FXML
    private Label tableNameLabel;

    /**
     * A táblagyűjtemény listaelem nézet kötése
     * <p>
     * Beállítja a nézethez tartozó elemeket, és a modellhez köti
     *
     * @param tableCollectionElementView a táblagyűjtemény listaelem nézete
     */
    public void bindView(TableCollectionElementView tableCollectionElementView) {
        this.tableCollectionElementView = tableCollectionElementView;
        var table = tableCollectionElementView.getTable();
        tableNameLabel.setText(table.getName());
        table.nameProperty().addListener((property, oldName, newName) -> {
            tableNameLabel.setText(newName);
        });
    }

    /**
     * A tábla törlése gomb eseménykezelője
     * <p>
     * Felugró ablakban megerősítést kér a felhasználótól
     *
     * @param actionEvent esemény paraméter
     */
    public void onDeletePressed(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Törlés megerősítése");
        alert.setHeaderText("Biztosan törli a táblát?");
        alert.setContentText("A tábla törlésével elvesznek az adatok.");
        var result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            var table = tableCollectionElementView.getTable();
            table.getParent().deleteTable(table);
        }
    }

    /**
     * A tábla megnyitása gomb eseménykezelője
     * <p>
     * Megjeleníti a táblához tartozó nézetet
     *
     * @param actionEvent esemény paraméter
     */
    public void onOpenPressed(ActionEvent actionEvent) {
        var tableView = new TableView(tableCollectionElementView.getTable());
        ViewManager.getInstance().setCurrentView(tableView.getView());
    }
}
