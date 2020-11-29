package proghf.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class TableCollection {
    private ObservableList<Table> tables = FXCollections.observableList(new ArrayList<>());
    private Table archive = new Table();

    public ObservableList<Table> getTables() {
        return tables;
    }

    public Table getArchive() {
        return archive;
    }

    public void AddEmptyTable() {
        this.tables.add(new Table("Quuux"));
    }
}
