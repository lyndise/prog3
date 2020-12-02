package proghf.controller;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
import proghf.view.TaskView;

public class TableColumnController {
    @FXML
    public Button deleteButton;
    private TableColumnView tableColumnView;
    @FXML
    public Label columnName;
    @FXML
    public VBox taskItems;

    public void bindView(TableColumnView tableColumnView) {
        this.tableColumnView = tableColumnView;
        if (tableColumnView.getLabel() != null) {
            this.columnName.setText(tableColumnView.getLabel().getName());
            tableColumnView.getLabel().getNameProperty().addListener((prop, oldName, newName) -> {
                this.columnName.setText(newName);
            });
        } else {
            this.columnName.setText("Alapértelmezett");
            deleteButton.setVisible(false);
            deleteButton.setPrefWidth(0.0);
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
    }

    public void renderTasks() {
        taskItems.getChildren().clear();
        tableColumnView.getTable().getTasks().forEach(task -> {
            if ((tableColumnView.getLabel() == null && !task.hasAnyLabel(tableColumnView.getTable().getColumns()))
                    || (task.getLabels().contains(tableColumnView.getLabel()))) {
                taskItems.getChildren().add(createTaskView(task));
            }
        });
    }

    private Node createTaskView(Task task) {
        var vbox = new VBox();
        var taskName = new Label(task.getName());
        taskName.setFont(Font.font("System", 16.0));
        vbox.getChildren().add(taskName);
        for (var label : task.getLabels()) {
            vbox.getChildren().add(new Label(label.getName()));
        }
        var button = new Button("Szerkesztés");
        button.onActionProperty().setValue(actionEvent -> {
            var taskView = new TaskView(task);
            taskView.activate();
        });
        vbox.getChildren().add(button);
        var separator = new Separator();
        separator.setPadding(new Insets(4, 0, 0, 4));
        vbox.getChildren().add(separator);
        return vbox;
    }

    @FXML
    public void onNewTaskPressed(ActionEvent actionEvent) {
        var task = new Reminder("Emlékeztető");
        task.setParent(tableColumnView.getTable());
        if (tableColumnView.getLabel() != null) {
            task.getLabels().add(tableColumnView.getLabel());
        }
        tableColumnView.getTable().getTasks().add(task);
    }

    @FXML
    public void onDeletePressed(ActionEvent actionEvent) {
        tableColumnView.delete();
    }
}
