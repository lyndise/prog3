package proghf.controller;

import javafx.collections.SetChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import proghf.ViewManager;
import proghf.model.Reminder;
import proghf.model.TaskList;
import proghf.view.ReminderView;
import proghf.view.TaskListView;
import proghf.view.TaskView;
import proghf.view.View;

import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * A feladat nézet kontrollere
 */
public class TaskController {

    /**
     * A feladat nézete
     */
    private TaskView taskView;

    /**
     * A feladat specifikus nézet
     */
    private View taskBodyView;

    /**
     * A feladat nevét tartalmazó szövegdoboz
     */
    @FXML
    private TextField titleTextField;

    /**
     * A feladat mentése gomb
     */
    @FXML
    private Button saveButton;

    /**
     * Új címke felvétele szövegmező
     */
    @FXML
    private TextField labelTextField;

    /**
     * A feladat nevét szerkesztő gomb
     */
    @FXML
    private Button editButton;

    /**
     * A feladat nevét tartalmazó szövegelem
     */
    @FXML
    private Label taskName;

    /**
     * A címkék listáját tartalmazó doboz
     */
    @FXML
    private VBox labelList;

    /**
     * Az archiváló gomb
     */
    @FXML
    private Button archiveButton;

    /**
     * A feladat specifikus részt tartalmazó doboz
     */
    @FXML
    private AnchorPane taskBody;

    /**
     * Feladat nézet kötése
     * <p>
     * Megjeleníti a feladat specifikus nézetet, valamint a feladathoz tartozó címkéket
     *
     * @param taskView a feladat nézet
     */
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
        } else if (taskView.getTask() instanceof TaskList) {
            var taskList = (TaskList) taskView.getTask();
            taskBodyView = new TaskListView(taskList);
        }
        taskBody.getChildren().add(taskBodyView.getView());
        renderLabelList();
        taskView.getTask().getLabels().addListener((SetChangeListener<? super proghf.model.Label>) change -> {
            renderLabelList();
        });
    }

    /**
     * A feladat címkéit megjelenítő segédfüggvény
     * <p>
     * Köti a címkét a modellhez, valamint beállítja a címke eseménykezelőket
     */
    private void renderLabelList() {
        labelList.getChildren().clear();
        for (var label : taskView.getTask().getLabels().stream().sorted(Comparator.comparing(proghf.model.Label::getName)).collect(Collectors.toList())) {
            var hbox = new HBox();
            var labelField = new Label(label.getName());
            var pane = new Pane();
            HBox.setHgrow(pane, Priority.ALWAYS);
            var deleteButton = new Button("Törlés");
            deleteButton.setOnAction(actionEvent -> {
                taskView.getTask().getLabels().remove(label);
            });
            deleteButton.setMinWidth(60.0);
            hbox.getChildren().addAll(labelField, pane, deleteButton);
            hbox.setSpacing(8.0);
            HBox.setHgrow(labelField, Priority.SOMETIMES);
            labelList.getChildren().add(hbox);
            var separator = new Separator();
            separator.setPadding(new Insets(8, 0, 8, 0));
            labelList.getChildren().add(separator);
        }
    }

    /**
     * A vissza navigáló gomb eseménykezelője
     *
     * @param actionEvent esemény paraméter
     */
    public void onBackPressed(ActionEvent actionEvent) {
        // Remove and re-add the task to refresh the column
        var task = taskView.getTask();
        task.getParent().getTasks().remove(task);
        task.getParent().getTasks().add(task);
        ViewManager.getInstance().navigateBack();
    }

    /**
     * A feladat törlése gomb eseménykezelője
     * <p>
     * Megerősítést kér a felhasználótól törlés előtt
     *
     * @param actionEvent esemény paraméter
     */
    public void onDeletePressed(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Törlés megerősítése");
        alert.setHeaderText("Biztosan törli a feladatot?");
        alert.setContentText("A feladat törlésével elvesznek az adatok.");
        var result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            var task = taskView.getTask();
            task.getParent().deleteTask(task);
            ViewManager.getInstance().navigateBack();
        }
    }

    /**
     * Az archiváló gomb eseménykezelője
     * <p>
     * Megerősítést kér a felhasználótól az archiválás előtt
     *
     * @param actionEvent esemény paraméter
     */
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
        ViewManager.getInstance().navigateBack();
    }

    /**
     * A feladat nevét szerkesztő gomb eseménykezelője
     * <p>
     * Megjeleníti a szerkesztési mezőt és a mentés gombot
     *
     * @param actionEvent esemény paraméter
     */
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

    /**
     * A feladat nevét elmentő gomb eseménykezelője
     * <p>
     * Üres szövegmező esetén nem történik változtatás
     *
     * @param actionEvent esemény paraméter
     */
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

    /**
     * Az új címke hozzáadása gomb eseménykezelője
     *
     * @param actionEvent esemény paraméter
     */
    public void onNewLabelPressed(ActionEvent actionEvent) {
        if (labelTextField.getText().length() > 0) {
            taskView.getTask().getLabels().add(new proghf.model.Label(labelTextField.getText()));
        }
        labelTextField.clear();
    }
}
