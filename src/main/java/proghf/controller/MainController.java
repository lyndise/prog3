package proghf.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
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
        tableCollectionView.createTable();
    }

    @FXML
    public void onQuitMenuItem(ActionEvent actionEvent){
        Platform.exit();
    }
}
