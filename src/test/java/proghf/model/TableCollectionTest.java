package proghf.model;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class TableCollectionTest {

    @Test
    public void getTables() {
        var tableCollection = new TableCollection();
        var tables = tableCollection.getTables();
        assertEquals(0, tables.size());
    }

    @Test
    public void setTables() {
        var tableCollection = new TableCollection();
        var tables = Stream.of(
                new Table("test", tableCollection),
                new Table("test2", tableCollection)
        ).collect(Collectors.toCollection(ArrayList::new));
        tableCollection.setTables(tables);
        var length = tableCollection.getTables().size();
        assertEquals(2, length);
    }

    @Test
    public void getArchive() {
        var tableCollection = new TableCollection();
        var archivedTasks = tableCollection.getArchive().getTasks();
        assertEquals(0, archivedTasks.size());
    }

    @Test
    public void newTable() {
        var tableCollection = new TableCollection();
        tableCollection.newTable("test");
        assertEquals(1, tableCollection.getTables().size());
    }

    @Test
    public void deleteTable() {
        var tableCollection = new TableCollection();
        var table = new Table("test", tableCollection);
        tableCollection.getTables().add(table);
        tableCollection.deleteTable(table);
        assertEquals(0, tableCollection.getTables().size());
    }

    @Test()
    public void loadFrom() throws IOException {
        var url = getClass().getClassLoader().getResource("tasks.json");
        var file = new File(url.getFile());
        var tableCollection = TableCollection.loadFrom(file);
        assertEquals(1, tableCollection.getTables().size());

        var table = tableCollection.getTables().get(0);
        assertEquals("Tábla", table.getName());
        assertEquals(2, table.getTasks().size());
        assertEquals(2, table.getColumns().size());
        assertTrue(table.getColumns().contains(new Label("címke1")));
        assertTrue(table.getColumns().contains(new Label("címke2")));

        TaskList taskList = (TaskList)table.getTasks().get(0);
        assertEquals(TaskList.class, taskList.getClass());
        assertEquals("Feladat", taskList.getName());
        assertEquals(1, taskList.getLabels().size());
        assertTrue(taskList.getLabels().contains(new Label("címke1")));

        Reminder reminder = (Reminder)table.getTasks().get(1);
        assertEquals(Reminder.class, reminder.getClass());
        assertEquals(1, reminder.getLabels().size());
        assertTrue(reminder.getLabels().contains(new Label("címke2")));

        var archive = tableCollection.getArchive();
        assertEquals("Archivált elemek", archive.getName());
        assertEquals(0, archive.getTasks().size());
        assertEquals(0, archive.getColumns().size());
    }
}