package proghf.controller;

import javafx.collections.SetChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import proghf.TableManager;
import proghf.model.Reminder;
import proghf.model.TaskList;
import proghf.view.*;

import java.util.Comparator;
import java.util.stream.Collectors;

public class TaskController {
    @FXML
    public TextField titleTextField;
    @FXML
    public Button saveButton;
    @FXML
    public VBox body;
    @FXML
    public TextField labelTextField;
    @FXML
    private Button editButton;
    @FXML
    private Label taskName;
    @FXML
    private VBox labelList;
    @FXML
    private Button archiveButton;
    private TaskView taskView;
    @FXML
    private AnchorPane taskBody;
    private View taskBodyView;

    public void bindView(TaskView taskView) {
        this.taskView = taskView;
        if (taskView.getTask().getParent() == taskView.getTask().getParent().getParent().getArchive()) {
            archiveButton.setVisible(false);
            archiveButton.setPrefWidth(0.0);
            archiveButton.setMinWidth(0.0);
        }
        taskName.setText(taskView.getTask().getName());
        taskView.getTask().nameProperty().addListener((property, oldName, newName) -> {
            taskName.setText(newName);
        });
        if (taskView.getTask() instanceof Reminder) {
            var reminder = (Reminder) taskView.getTask();
            taskBodyView = new ReminderView(reminder);
        }else if(taskView.getTask() instanceof TaskList){
            var taskList=(TaskList) taskView.getTask();
            taskBodyView=new TaskListView(taskList);
        }
        taskBody.getChildren().add(taskBodyView.getView());
        renderLabelList();
        taskView.getTask().getLabels().addListener((SetChangeListener<? super proghf.model.Label>) change -> {
            renderLabelList();
        });
    }

    private void renderLabelList() {
        labelList.getChildren().clear();
        for (var label : taskView.getTask().getLabels().stream().sorted(Comparator.comparing(proghf.model.Label::getName)).collect(Collectors.toList())) {
            var hbox = new HBox();
            var labelField = new TextField(label.getName());
            labelField.textProperty().bindBidirectional(label.getNameProperty());
            var deleteButton = new Button("Törlés");
            deleteButton.setOnAction(actionEvent -> {
                taskView.getTask().getLabels().remove(label);
            });
            deleteButton.setMinWidth(60.0);
            hbox.getChildren().addAll(labelField, deleteButton);
            hbox.setSpacing(8.0);
            HBox.setHgrow(labelField, Priority.SOMETIMES);
            labelList.getChildren().add(hbox);
            var separator = new Separator();
            separator.setPadding(new Insets(8, 0, 8, 0));
            labelList.getChildren().add(separator);
        }
    }

    public void onBackPressed(ActionEvent actionEvent) {
        // Remove and re-add the task to refresh the column
        var task = taskView.getTask();
        task.getParent().getTasks().remove(task);
        task.getParent().getTasks().add(task);
        TableManager.getInstance().navigateBack();
    }

    public void onDeletePressed(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Törlés megerősítése");
        alert.setHeaderText("Biztosan törli a feladatot?");
        alert.setContentText("A feladat törlésével elvesznek az adatok.");
        var result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            var task = taskView.getTask();
            task.getParent().deleteTask(task);
            TableManager.getInstance().navigateBack();
        }
    }

    public void onArchivePressed(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Archiválás megerősítése");
        alert.setHeaderText("Biztosan archiválja a feladatot?");
        alert.setContentText("A feladat archiválásával az archív elemek közé kerül a feladat.");
        var result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            var task = taskView.getTask();
            task.getParent().archiveTask(task);
        }
        TableManager.getInstance().navigateBack();
    }

    public void onEditPressed(ActionEvent actionEvent) {
        titleTextField.setText(taskView.getTask().getName());
        taskName.setVisible(false);
        taskName.setPrefWidth(0.0);
        taskName.setMinWidth(0.0);
        editButton.setVisible(false);
        editButton.setPrefWidth(0.0);
        editButton.setMinWidth(0.0);
        titleTextField.setVisible(true);
        titleTextField.setPrefWidth(-1);
        saveButton.setVisible(true);
        saveButton.setPrefWidth(-1);
    }

    public void onSavePressed(ActionEvent actionEvent) {
        if (titleTextField.getText().length() > 0) {
            taskView.getTask().setName(titleTextField.getText());
        }
        taskName.setVisible(true);
        taskName.setPrefWidth(-1);
        editButton.setVisible(true);
        editButton.setPrefWidth(-1);
        titleTextField.setVisible(false);
        titleTextField.setPrefWidth(0.0);
        saveButton.setVisible(false);
        saveButton.setPrefWidth(0.0);
    }

    public void onNewLabelPressed(ActionEvent actionEvent) {
        if(labelTextField.getText().length()>0){
            taskView.getTask().getLabels().add(new proghf.model.Label(labelTextField.getText()));
        }
        labelTextField.clear();
    }
}
