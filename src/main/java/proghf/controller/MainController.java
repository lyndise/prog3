package proghf.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import proghf.TableManager;
import proghf.model.TableCollection;
import proghf.view.TableCollectionView;

import java.io.File;
import java.io.IOException;

public class MainController {
    @FXML
    private AnchorPane mainContent;

    private TableCollectionView tableCollectionView;
    private Stage stage;

    @FXML
    protected void initialize() {
        TableManager.getInstance().registerMainController(this);
        tableCollectionView = new TableCollectionView();
        tableCollectionView.activate();
    }

    public void bindStage(Stage stage) {
        this.stage = stage;
    }

    public void setContent(Node n) {
        var children = mainContent.getChildren();
        children.clear();
        children.add(n);
        AnchorPane.setTopAnchor(n, 0.0);
        AnchorPane.setBottomAnchor(n, 0.0);
        AnchorPane.setLeftAnchor(n, 0.0);
        AnchorPane.setRightAnchor(n, 0.0);
    }

    @FXML
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

    @FXML
    public void onQuitMenuItem(ActionEvent actionEvent) {
        stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    public void onSavePressed(ActionEvent actionEvent) {
        var dialog = new Alert(Alert.AlertType.CONFIRMATION, "Biztosan folytatja?");
        dialog.setTitle("Figyelmeztetés");
        dialog.setHeaderText("A lemezen tárolt adatok felülíródnak.");
        var result = dialog.showAndWait();
        if (result.isEmpty() || result.get() != ButtonType.OK) {
            return;
        }

        try {
            var objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File("tasks.json"), tableCollectionView.getTableCollection());
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

    public void onLoadPressed(ActionEvent actionEvent) {
        var dialog = new Alert(Alert.AlertType.CONFIRMATION, "Biztosan folytatja?");
        dialog.setTitle("Figyelmeztetés");
        dialog.setHeaderText("A memóriában tárolt adatok elvesznek.");
        var result = dialog.showAndWait();
        if (result.isEmpty() || result.get() != ButtonType.OK) {
            return;
        }

        try {
            var tableCollection = new ObjectMapper().readValue(new File("tasks.json"), TableCollection.class);
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
            TableManager.getInstance().navigateBack();
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
