package proghf.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import proghf.Main;
import proghf.controller.TableCollectionElementController;
import proghf.model.Table;

import java.io.IOException;

public class TableCollectionElementView {
    private Table table;
    private Node view;

    public TableCollectionElementView(Table table) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("tableCollectionElement.fxml"));
        try {
            view = loader.load();
            this.table = table;
            TableCollectionElementController controller = loader.getController();
            controller.bindView(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Table getTable() {
        return table;
    }

    public Node getView() {
        return view;
    }
}
