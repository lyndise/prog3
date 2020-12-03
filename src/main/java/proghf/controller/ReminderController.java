package proghf.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import proghf.view.ReminderView;

public class ReminderController {
    @FXML
    public Button editButton;
    @FXML
    public Button saveButton;
    @FXML
    public TextArea textArea;
    private ReminderView reminderView;

    public void bindView(ReminderView reminderView) {
        this.reminderView = reminderView;
        textArea.textProperty().bindBidirectional(reminderView.getReminder().textProperty());
    }
}
