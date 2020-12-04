package proghf.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import proghf.ViewManager;
import proghf.model.TableCollection;
import proghf.view.TableCollectionView;

import java.io.File;
import java.io.IOException;

/**
 * A fő ablak kontrollere
 */
public class MainController {

    /**
     * A táblagyűjteményhez tartozó nézet
     */
    private TableCollectionView tableCollectionView;

    /**
     * A fő ablak nézete
     */
    private Stage stage;

    /**
     * A fő tartalmat befoglaló elem
     */
    @FXML
    private AnchorPane mainContent;

    /**
     * A kontroller inicializálása, a táblagyűjtemény megjelenítése
     */
    @FXML
    protected void initialize() {
        ViewManager.getInstance().registerMainController(this);
        tableCollectionView = new TableCollectionView(new TableCollection());
        tableCollectionView.activate();
    }

    /**
     * A fő ablak nézetének kötése a kontrollerhez
     *
     * @param stage a fő ablak nézete
     */
    public void bindStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * A fő nézetet lecserélő függvény
     *
     * @param view az új nézet
     */
    public void setContent(Node view) {
        var children = mainContent.getChildren();
        children.clear();
        children.add(view);
        AnchorPane.setTopAnchor(view, 0.0);
        AnchorPane.setBottomAnchor(view, 0.0);
        AnchorPane.setLeftAnchor(view, 0.0);
        AnchorPane.setRightAnchor(view, 0.0);
    }

    /**
     * Új tábla létrehozása menüelem kezelése
     * <p>
     * Az új tábla létrehozásánál a tábla nevét felugró ablakban adhatja meg a felhasználó
     *
     * @param actionEvent esemény paraméter
     */
    public void onNewTableMenuItem(ActionEvent actionEvent) {
        var tableNameDialog = new TextInputDialog("Tábla");
        tableNameDialog.setTitle("Új tábla létrehozása");
        tableNameDialog.setHeaderText("Kérem adja meg az új tábla nevét");
        tableNameDialog.setContentText(null);
        var result = tableNameDialog.showAndWait();
        if (result.isPresent() && result.get().length() > 0) {
            tableCollectionView.createTable(result.get());
        }
    }

    /**
     * Kilépés menüelem kezelése
     * <p>
     * A kilépésről a fő ablakot értesíti
     *
     * @param actionEvent esemény paraméter
     */
    public void onQuitMenuItem(ActionEvent actionEvent) {
        stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    /**
     * A mentés menüelem kezelése
     * <p>
     * Figyelmezteti a felhasználót, majd fájlba menti a programban tárolt adatokat
     * <p>
     * Siker vagy probléma esetén felugró ablakkal értesíti a felhasználót
     *
     * @param actionEvent esemény paraméter
     */
    public void onSavePressed(ActionEvent actionEvent) {
        var dialog = new Alert(Alert.AlertType.CONFIRMATION, "Biztosan folytatja?");
        dialog.setTitle("Figyelmeztetés");
        dialog.setHeaderText("A lemezen tárolt adatok felülíródnak.");
        var result = dialog.showAndWait();
        if (result.isEmpty() || result.get() != ButtonType.OK) {
            return;
        }
        try {
            tableCollectionView.getTableCollection().saveTo(new File("tasks.json"));
            var alert = new Alert(Alert.AlertType.INFORMATION, "Az adatok sikeresen mentésre kerültek.", ButtonType.OK);
            alert.setTitle("Információ");
            alert.setHeaderText(null);
            alert.show();
        } catch (IOException e) {
            e.printStackTrace();
            var alert = new Alert(Alert.AlertType.ERROR, "Hiba történt mentéskor!", ButtonType.OK);
            alert.setTitle("Hiba");
            alert.setHeaderText(null);
            alert.show();
        }
    }

    /**
     * A betöltés menüelem kezelése
     * <p>
     * Figyelmezteti a felhasználót, majd fájlból betölti a tárolt adatokat
     * <p>
     * Siker vagy probléma esetén felugró ablakkal értesíti a felhasználót
     *
     * @param actionEvent esemény paraméter
     */
    public void onLoadPressed(ActionEvent actionEvent) {
        var dialog = new Alert(Alert.AlertType.CONFIRMATION, "Biztosan folytatja?");
        dialog.setTitle("Figyelmeztetés");
        dialog.setHeaderText("A memóriában tárolt adatok elvesznek.");
        var result = dialog.showAndWait();
        if (result.isEmpty() || result.get() != ButtonType.OK) {
            return;
        }
        try {
            var tableCollection = TableCollection.loadFrom(new File("tasks.json"));
            tableCollection.getTables().forEach(table -> {
                table.setParent(tableCollection);
                table.getTasks().forEach(task -> {
                    task.setParent(table);
                });
            });
            var archive = tableCollection.getArchive();
            archive.setParent(tableCollection);
            archive.getTasks().forEach(task -> {
                task.setParent(archive);
            });
            tableCollectionView = new TableCollectionView(tableCollection);
            ViewManager.getInstance().navigateBack();
            tableCollectionView.activate();
            var alert = new Alert(Alert.AlertType.INFORMATION, "Az adatok sikeresen be lettek töltve.", ButtonType.OK);
            alert.setTitle("Információ");
            alert.setHeaderText(null);
            alert.show();
        } catch (IOException e) {
            e.printStackTrace();
            var alert = new Alert(Alert.AlertType.ERROR, "Hiba történt betöltés közben!", ButtonType.OK);
            alert.setTitle("Hiba");
            alert.setHeaderText(null);
            alert.show();
        }
    }
}
