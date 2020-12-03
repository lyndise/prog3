package proghf.view;

import javafx.fxml.FXMLLoader;
import proghf.Main;
import proghf.controller.TableCollectionController;
import proghf.model.TableCollection;

import java.io.IOException;

public class TableCollectionView extends Navigable {
    private TableCollection tableCollection;

    public TableCollectionView() {
        setTableCollection(new TableCollection());
    }

    public TableCollectionView(TableCollection tableCollection) {
        setTableCollection(tableCollection);
    }

    public TableCollection getTableCollection() {
        return tableCollection;
    }

    public void setTableCollection(TableCollection tableCollection) {
        this.tableCollection = tableCollection;
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("tableCollection.fxml"));
        try {
            view = loader.load();
            TableCollectionController controller = loader.getController();
            controller.bindView(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createTable(String name) {
        tableCollection.newTable(name);
    }
}
