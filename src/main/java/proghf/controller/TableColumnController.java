package proghf.controller;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import proghf.model.Reminder;
import proghf.model.Task;
import proghf.model.TaskList;
import proghf.view.TableColumnView;

public class TableColumnController {
    private TableColumnView tableColumnView;
    @FXML
    public Label columnName;
    @FXML
    public VBox taskItems;

    public void bindView(TableColumnView tableColumnView) {
        this.tableColumnView = tableColumnView;
        this.columnName.setText(tableColumnView.getLabel().getName());
        tableColumnView.getLabel().getNameProperty().addListener((prop, oldName, newName) -> {
                    this.columnName.setText(newName);
        });
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
    }

    private void renderTasks() {
        taskItems.getChildren().clear();
        tableColumnView.getTable().getTasks().forEach(task ->{
            taskItems.getChildren().add(createTaskView(task));
        });
    }

    private Node createTaskView(Task task) {
        var vbox = new VBox();
        var taskName = new Label(task.getName());
        taskName.setFont(Font.font("System", 16.0));
        vbox.getChildren().add(taskName);
        for (var label: task.getLabels()) {
            vbox.getChildren().add(new Label(label.getName()));
        }
        var separator = new Separator();
        separator.setPadding(new Insets(4, 0, 0, 4));
        vbox.getChildren().add(separator);
        return vbox;
    }

    @FXML
    public void onNewTaskPressed(ActionEvent actionEvent) {
        var task = new Reminder("Emlékeztető");
        tableColumnView.getTable().getTasks().add(task);
    }

    @FXML
    public void onDeletePressed(ActionEvent actionEvent) {
        tableColumnView.delete();
    }
}
