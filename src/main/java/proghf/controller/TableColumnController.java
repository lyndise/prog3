package proghf.controller;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import proghf.model.Reminder;
import proghf.model.Task;
import proghf.model.TaskList;
import proghf.view.TableColumnView;
import proghf.view.TaskView;

import java.util.ArrayList;
import java.util.List;

/**
 * A tábla egy oszlopához tartozó kontroller
 */
public class TableColumnController {

    /**
     * A táblaoszlop nézete
     */
    private TableColumnView tableColumnView;

    /**
     * Az oszlop törlése gomb
     */
    @FXML
    private Button deleteButton;

    /**
     * Az új feladat gomb
     */
    @FXML
    private Button newButton;

    /**
     * Az oszlop nevét tartalmazó szövegelem
     */
    @FXML
    private Label columnName;

    /**
     * A feladatokat tartalmazó doboz
     */
    @FXML
    private VBox taskItems;

    /**
     * A táblaoszlop nézet kötése
     * <p>
     * Beállítja az oszlop nevét, valamint kirajzolja az oszlophoz tartozó feladatokat
     *
     * @param tableColumnView a táblaoszlop nézet
     */
    public void bindView(TableColumnView tableColumnView) {
        this.tableColumnView = tableColumnView;
        if (tableColumnView.getLabel() != null) {
            this.columnName.setText(tableColumnView.getLabel().getName());
            tableColumnView.getLabel().nameProperty().addListener((prop, oldName, newName) -> {
                this.columnName.setText(newName);
            });
        } else {
            this.columnName.setText("Alapértelmezett");
            deleteButton.setVisible(false);
            deleteButton.setPrefWidth(0.0);
            deleteButton.setMinWidth(0.0);
        }
        renderTasks();
        tableColumnView.getTable().getTasks().addListener((ListChangeListener<? super Task>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    renderTasks();
                }
                if (change.wasRemoved()) {
                    renderTasks();
                }
            }
        });
        if (tableColumnView.getTable() == tableColumnView.getTable().getParent().getArchive()) {
            newButton.setVisible(false);
            newButton.setPrefWidth(0.0);
            newButton.setMinWidth(0.0);
        }
    }

    /**
     * A feladatok kirajzolásához használt segédfüggvény
     */
    public void renderTasks() {
        taskItems.getChildren().clear();
        tableColumnView.getTable().getTasks().forEach(task -> {
            if ((tableColumnView.getLabel() == null && !task.hasAnyLabel(tableColumnView.getTable().getColumns()))
                    || (task.getLabels().contains(tableColumnView.getLabel()))) {
                taskItems.getChildren().add(createTaskView(task));
            }
        });
    }

    /**
     * Oszlopon belüli feladat nézet létrehozása
     * <p>
     * Megjeleníti a feladat nevét, valamint a feladathoz tartozó címkéket, és egy
     * gombot amivel a feladat megnyithatóvá válik
     *
     * @param task a nézethez tartozó feladat
     * @return a létrehozott nézet
     */
    private Node createTaskView(Task task) {
        var vbox = new VBox();
        vbox.setPadding(new Insets(4, 0, 4, 0));
        var taskName = new Label(task.getName());
        taskName.setFont(Font.font(16.0));
        taskName.setPadding(new Insets(0, 0, 8, 0));
        var headerBox = new HBox();
        headerBox.getChildren().add(taskName);
        if (task instanceof Reminder) {
            var image = new Image("reminder.png", 24.0, 24.0, true, true);
            var imageView = new ImageView(image);
            var pane = new Pane();
            HBox.setHgrow(pane, Priority.ALWAYS);
            headerBox.getChildren().addAll(pane, imageView);
        } else if (task instanceof TaskList) {
            var image = new Image("task.png", 24.0, 24.0, true, true);
            var imageView = new ImageView(image);
            var pane = new Pane();
            HBox.setHgrow(pane, Priority.ALWAYS);
            headerBox.getChildren().addAll(pane, imageView);
        }
        vbox.getChildren().add(headerBox);

        for (var label : task.getLabels()) {
            var columnLabel = tableColumnView.getLabel();
            if (columnLabel == null || !columnLabel.equals(label)) {
                vbox.getChildren().add(new Label(label.getName()));
            }
        }
        var button = new Button("Megnyitás");
        button.onActionProperty().setValue(actionEvent -> {
            var taskView = new TaskView(task);
            taskView.activate();
        });
        var hbox = new HBox();
        hbox.setAlignment(Pos.CENTER_RIGHT);
        hbox.getChildren().add(button);
        vbox.getChildren().add(hbox);
        var separator = new Separator();
        separator.setPadding(new Insets(8, 0, 0, 0));
        vbox.getChildren().add(separator);
        return vbox;
    }

    /**
     * Az új feladat gomb eseménykezelője
     * <p>
     * Felugró ablakban kérdezi meg a feladat típusát, valamint a feladat nevét
     *
     * @param actionEvent esemény paraméter
     */
    public void onNewTaskPressed(ActionEvent actionEvent) {
        List<String> choices = new ArrayList<>();
        choices.add("Emlékeztető");
        choices.add("Feladatlista");
        ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
        dialog.setTitle("Feladat típus választás");
        dialog.setHeaderText("Milyen típusú feladatot szeretne létrehozni?");
        dialog.setContentText("Válasszon típust:");
        var result = dialog.showAndWait();
        Task task;
        if (result.isEmpty()) {
            return;
        }
        var taskNameDialog = new TextInputDialog("Feladat");
        taskNameDialog.setTitle("Új feladat");
        taskNameDialog.setHeaderText("Adja meg a feladat nevét");
        taskNameDialog.setContentText(null);
        var taskNameResult = taskNameDialog.showAndWait();
        if (taskNameResult.isPresent() && taskNameResult.get().length() > 0) {
            if (result.get().equals(choices.get(0))) {
                task = new Reminder(taskNameResult.get());
            } else if (result.get().equals(choices.get(1))) {
                task = new TaskList(taskNameResult.get());
            } else {
                return;
            }
            task.setParent(tableColumnView.getTable());
            if (tableColumnView.getLabel() != null) {
                task.getLabels().add(tableColumnView.getLabel());
            }
            tableColumnView.getTable().getTasks().add(task);
        }
    }

    /**
     * Az oszlop törlése gomb eseménykezelője
     *
     * @param actionEvent esemény paraméter
     */
    public void onDeletePressed(ActionEvent actionEvent) {
        tableColumnView.delete();
    }
}
