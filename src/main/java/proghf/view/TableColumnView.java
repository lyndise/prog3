package proghf.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import proghf.Main;
import proghf.controller.TableColumnController;
import proghf.model.Label;
import proghf.model.Table;

import java.io.IOException;

public class TableColumnView {
    private Table table;
    private Label label;
    private Node view;

    public TableColumnView(Table table, Label label) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("tableColumn.fxml"));
        this.table = table;
        this.label = label;
        try {
            view = loader.load();
            TableColumnController controller = loader.getController();
            controller.bindView(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Table getTable() {
        return table;
    }

    public Label getLabel() {
        return label;
    }

    public Node getView() {
        return view;
    }

    public void delete() {
        table.removeColumn(label);
    }
}
