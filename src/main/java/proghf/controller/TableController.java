package proghf.controller;

import javafx.collections.SetChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import proghf.ViewManager;
import proghf.view.TableColumnView;
import proghf.view.TableView;

import java.util.ArrayList;
import java.util.List;

/**
 * Tábla nézethez tartozó kontroller
 */
public class TableController {

    /**
     * A táblához tartozó oszlopok nézetei
     */
    private final List<TableColumnView> tableColumnViews = new ArrayList<>();

    /**
     * A táblához tartozó nézet
     */
    private TableView tableView;

    /**
     * A tábla nevének szerkesztéséhez tartozó szövegmező
     */
    @FXML
    private TextField tableNameTextField;

    /**
     * A tábla nevének mentése gomb
     */
    @FXML
    private Button saveButton;

    /**
     * A tábla nevének szerkesztése gomb
     */
    @FXML
    private Button editButton;

    /**
     * A tábla törlése gomb
     */
    @FXML
    private Button deleteButton;

    /**
     * Az új oszlop nevét tartalmazó szövegdoboz
     */
    @FXML
    private TextField newColumnName;

    /**
     * Az oszlopokat tartalmazó doboz
     */
    @FXML
    private HBox columns;

    /**
     * A tábla nevét tartalmazó szövegelem
     */
    @FXML
    private Label tableName;

    /**
     * Az új oszlop gomb eseménykezelője
     *
     * @param actionEvent esemény paraméter
     */
    public void onNewColumnPressed(ActionEvent actionEvent) {
        if (newColumnName.getText().length() > 0) {
            var label = new proghf.model.Label(newColumnName.getText());
            tableView.getTable().getColumns().add(label);
        }
        newColumnName.clear();
    }

    /**
     * A vissza navigáló gomb eseménykezelője
     *
     * @param actionEvent esemény paraméter
     */
    public void onBackPressed(ActionEvent actionEvent) {
        ViewManager.getInstance().navigateBack();
    }

    /**
     * Az oszlopok kirajzolásához használt segédfüggvény
     */
    private void renderColumns() {
        columns.getChildren().clear();
        for (var view : tableColumnViews) {
            columns.getChildren().add(view.getView());
            if (view.getLabel() == null) {
                view.refresh();
            }
        }
    }

    /**
     * A tábla nézet kötése
     * <p>
     * Létrehozza a táblához tartozó oszlopok nézeteit és
     * köti az eseménykezelőket a modellhez
     *
     * @param tableView a tábla nézet
     */
    public void bindView(TableView tableView) {
        this.tableView = tableView;
        tableName.setText(tableView.getTable().getName());
        tableView.getTable().nameProperty().addListener((prop, oldName, newName) -> {
            tableName.setText(newName);
        });
        tableColumnViews.add(new TableColumnView(tableView.getTable(), null));
        for (var label : tableView.getTable().getColumns()) {
            tableColumnViews.add(new TableColumnView(tableView.getTable(), label));
        }
        renderColumns();
        tableView.getTable().getColumns().addListener((SetChangeListener<? super proghf.model.Label>) change -> {
            if (change.wasAdded()) {
                var newColumn = change.getElementAdded();
                var view = new TableColumnView(tableView.getTable(), newColumn);
                columns.getChildren().add(view.getView());
                tableColumnViews.add(view);
            }
            if (change.wasRemoved()) {
                var oldColumn = change.getElementRemoved();
                tableColumnViews.removeIf(view -> view.getLabel() != null && view.getLabel().equals(oldColumn));
            }
            renderColumns();
        });
        if (tableView.getTable() == tableView.getTable().getParent().getArchive()) {
            editButton.setVisible(false);
            editButton.setPrefWidth(0.0);
            deleteButton.setVisible(false);
            deleteButton.setPrefWidth(0.0);
        }
    }

    /**
     * A szerkesztési mezőket aktiváló gomb eseménykezelője
     * <p>
     * Elrejti a tábla nevét és a szerkesztés gombot, majd
     * megjeleníti a szerkesztési mezőt és a mentés gombot
     *
     * @param actionEvent esemény paraméter
     */
    public void onEditPressed(ActionEvent actionEvent) {
        tableNameTextField.setText(tableName.getText());
        tableName.setVisible(false);
        tableName.setPrefWidth(0.0);
        editButton.setVisible(false);
        editButton.setPrefWidth(0.0);
        deleteButton.setVisible(false);
        deleteButton.setPrefWidth(0.0);
        tableNameTextField.setVisible(true);
        tableNameTextField.setPrefWidth(-1);
        saveButton.setVisible(true);
        saveButton.setPrefWidth(-1);
    }

    /**
     * A tábla nevét mentő gomb eseménykezelője
     * <p>
     * Elrejti a szerkesztési mezőt és a mentés gombot
     * ha üres a szövegmező, akkor nem módosítja a tábla nevét
     *
     * @param actionEvent esemény paraméter
     */
    public void onSavePressed(ActionEvent actionEvent) {
        if (tableNameTextField.getText().length() > 0) {
            tableView.getTable().setName(tableNameTextField.getText());
        }
        tableName.setVisible(true);
        tableName.setPrefWidth(-1);
        editButton.setVisible(true);
        editButton.setPrefWidth(-1);
        deleteButton.setVisible(true);
        deleteButton.setPrefWidth(-1);
        tableNameTextField.setVisible(false);
        tableNameTextField.setPrefWidth(0.0);
        saveButton.setVisible(false);
        saveButton.setPrefWidth(0.0);
    }

    /**
     * A tábla törlése gomb eseménykezelője
     * <p>
     * Megerősítést kér a felhasználótól törlés előtt
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
            var table = tableView.getTable();
            table.getParent().deleteTable(table);
            ViewManager.getInstance().navigateBack();
        }
    }
}
