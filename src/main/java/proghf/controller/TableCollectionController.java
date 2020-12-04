package proghf.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import proghf.ViewManager;
import proghf.model.Table;
import proghf.view.SearchView;
import proghf.view.TableCollectionElementView;
import proghf.view.TableCollectionView;
import proghf.view.TableView;

import java.util.ArrayList;
import java.util.List;

/**
 * A tábla gyűjtemény nézet kontrollere
 */
public class TableCollectionController {

    /**
     * A táblagyűjtemény nézet elemeit tartalmazó lista
     */
    private final List<TableCollectionElementView> tableCollectionElementViews = new ArrayList<>();

    /**
     * A táblagyűjtemény nézet
     */
    private TableCollectionView tableCollectionView;

    /**
     * Az archivált elemek közti keresés jelölője
     */
    @FXML
    private CheckBox isArchiveChecked;

    /**
     * A keresési kifejezés szövegmezője
     */
    @FXML
    private TextField searchField;

    /**
     * A keresés folyamatjelzője
     */
    @FXML
    private ProgressIndicator searchSpinner;

    /**
     * A táblagyűjtemény nézet elemeit tartalmazó doboz
     */
    @FXML
    private VBox tableRows;

    /**
     * A táblagyűjtemény nézet kötése
     * <p>
     * Végigiterál a táblákon, megjeleníti a sorokat és beállítja az eseménykezelőket
     *
     * @param tableCollectionView a táblagyűjtemény nézet
     */
    public void bindView(TableCollectionView tableCollectionView) {
        this.tableCollectionView = tableCollectionView;
        tableCollectionView.getTableCollection().getTables().forEach(table -> {
            var tableRow = new TableCollectionElementView(table);
            tableCollectionElementViews.add(tableRow);
            tableRows.getChildren().add(tableRow.getView());
        });
        tableCollectionView.getTableCollection().getTables().addListener((ListChangeListener<? super Table>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    for (var added : change.getAddedSubList()) {
                        tableCollectionElementViews.add(new TableCollectionElementView(added));
                    }
                }
                if (change.wasRemoved()) {
                    for (var removed : change.getRemoved()) {
                        tableCollectionElementViews.removeIf(el -> el.getTable().equals(removed));
                    }
                }
            }
            tableRows.getChildren().clear();
            for (var el : tableCollectionElementViews) {
                tableRows.getChildren().add(el.getView());
            }
        });
    }

    /**
     * Az új tábla gomb eseménykezelője
     * <p>
     * Felugró ablakban kéri be az új tábla nevét a felhasználótól
     *
     * @param actionEvent esemény paraméter
     */
    public void onAddTableButtonPressed(ActionEvent actionEvent) {
        var tableNameDialog = new TextInputDialog("Tábla");
        tableNameDialog.setTitle("Új tábla létrehozása");
        tableNameDialog.setHeaderText("Kérem adja meg az új tábla nevét");
        tableNameDialog.setContentText(null);
        var result = tableNameDialog.showAndWait();
        if (result.isPresent() && result.get().length() > 0) {
            tableCollectionView.getTableCollection().newTable(result.get());
        }
    }

    /**
     * Az archivált feladatok nézetére váltó gomb eseménykezelője
     *
     * @param actionEvent esemény paraméter
     */
    public void onArchivedTasksPressed(ActionEvent actionEvent) {
        var tableView = new TableView(tableCollectionView.getTableCollection().getArchive());
        ViewManager.getInstance().setCurrentView(tableView.getView());
    }

    /**
     * A keresést indító gomb eseménykezelője
     * <p>
     * Létrehozza, majd megjeleníti a keresési nézetet
     *
     * @param actionEvent esemény paraméter
     */
    public void onSearchPressed(ActionEvent actionEvent) {
        searchSpinner.setPrefWidth(20);
        searchField.setDisable(true);
        new Timeline(new KeyFrame(Duration.millis(1000), evt -> {
            searchSpinner.setPrefWidth(0);
            searchField.setDisable(false);
            List<Table> tables = new ArrayList<>(tableCollectionView.getTableCollection().getTables());
            if (isArchiveChecked.isSelected()) {
                tables.add(tableCollectionView.getTableCollection().getArchive());
            }
            var searchView = new SearchView(searchField.getText(), tables);
            ViewManager.getInstance().setCurrentView(searchView.getView());
            searchField.clear();
        })).play();
    }
}
