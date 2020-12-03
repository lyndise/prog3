package proghf.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class TableCollection {
    @JsonIgnore
    private ObservableList<Table> tables = FXCollections.observableList(new ArrayList<>());
    private Table archive = new Table("Archivált elemek", this);

    @JsonGetter
    public ObservableList<Table> getTables() {
        return tables;
    }

    @JsonSetter
    public void setTables(List<Table> tables) {
        this.tables.clear();
        this.tables.addAll(tables);
    }

    public Table getArchive() {
        return archive;
    }

    public void newTable(String name) {
        this.tables.add(new Table(name,this));
    }

    public void deleteTable(Table table) {
        tables.removeAll(table);
    }
}
