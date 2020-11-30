package proghf.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class TableCollection {
    private ObservableList<Table> tables = FXCollections.observableList(new ArrayList<>());
    private Table archive = new Table("Archivált elemek", this);
    private int nextId = 1;

    public ObservableList<Table> getTables() {
        return tables;
    }

    public Table getArchive() {
        return archive;
    }

    public void addEmptyTable() {
        this.tables.add(new Table(String.format("Tábla %d", nextId++), this));
    }

    public void deleteTable(Table table) {
        tables.removeAll(table);
    }
}
