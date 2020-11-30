package proghf.view;

import javafx.fxml.FXMLLoader;
import proghf.Main;
import proghf.controller.TableCollectionController;
import proghf.model.TableCollection;

import java.io.IOException;

public class TableCollectionView extends Navigable {
    private TableCollection tableCollection;

    public TableCollectionView() {
        this.tableCollection = new TableCollection();

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("tableCollection.fxml"));
        try {
            view = loader.load();
            TableCollectionController controller = loader.getController();
            controller.bindView(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TableCollection getTableCollection() {
        return tableCollection;
    }

    public void createTable() {
        tableCollection.addEmptyTable();
    }
}
