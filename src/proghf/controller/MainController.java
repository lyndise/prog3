package proghf.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import proghf.model.TableCollection;

import java.io.IOException;

public class MainController {
    @FXML
    private Tab tableCollectionTab;

    @FXML
    private TabPane tabPane;

    @FXML
    protected void initialize() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/tableCollection.fxml"));
            Parent root = loader.load();
            TableCollectionController controller = loader.getController();
            tableCollectionTab.setContent(root);
            controller.setMainController(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addNewTab() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/table.fxml"));
            Parent root = loader.load();
            TableController controller = loader.getController();
            var tab = new Tab("Feladat");
            tab.setContent(root);
            tabPane.getTabs().add(tab);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
