package proghf.view;

import javafx.fxml.FXMLLoader;
import proghf.Main;
import proghf.controller.TableController;
import proghf.model.Table;

import java.io.IOException;

public class TableView extends Navigable {
    private Table table;


    public TableView(Table table) {
        this.table = table;
        loadView();
    }

    private void loadView() {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("table.fxml"));
        try {
            view = loader.load();
            TableController controller = loader.getController();
            controller.bindView(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Table getTable() {
        return table;
    }
}
