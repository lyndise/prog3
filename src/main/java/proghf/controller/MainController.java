package proghf.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import proghf.TableManager;
import proghf.view.TableCollectionView;

public class MainController {
    @FXML
    private AnchorPane mainContent;

    private TableCollectionView tableCollectionView;

    @FXML
    protected void initialize() {
        TableManager.getInstance().registerMainController(this);
        tableCollectionView = new TableCollectionView();
        tableCollectionView.activate();
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
    public void onNewTableMenuItem(ActionEvent actionEvent){
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
    public void onQuitMenuItem(ActionEvent actionEvent){
        Platform.exit();
    }
}
