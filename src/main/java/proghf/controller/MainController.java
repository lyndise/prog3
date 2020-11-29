package proghf.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import proghf.Main;

import java.io.IOException;

public class MainController {
    @FXML
    private Tab tableCollectionTab;

    @FXML
    private TabPane tabPane;
    private TableCollectionController tableCollectionController;

    @FXML
    protected void initialize() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("tableCollection.fxml"));
            Parent root = loader.load();
            tableCollectionController = loader.getController();
            tableCollectionTab.setContent(root);
            tableCollectionController.setMainController(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addNewTab() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("table.fxml"));
            Parent root = loader.load();
            TableController controller = loader.getController();
            var tab = new Tab("Feladat");
            tab.setContent(root);
            tabPane.getTabs().add(tab);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void onNewTableMenuItem(){
        tableCollectionController.onAddTableButtonPressed();
    }

    @FXML
    private void onQuitMenuItem(){
        Platform.exit();
    }
}
