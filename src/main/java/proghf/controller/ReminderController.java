package proghf.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import proghf.view.ReminderView;

/**
 * Az emlékeztető nézetéhez tartozó kontroller
 */
public class ReminderController {

    /**
     * Az emlékeztető szövegét tartalmazó szövegdoboz
     */
    @FXML
    private TextArea textArea;

    /**
     * Az emlékeztetőhöz tartozó nézet kötése
     *
     * @param reminderView az emlékeztetőhöz tartozó nézet
     */
    public void bindView(ReminderView reminderView) {
        textArea.textProperty().bindBidirectional(reminderView.getReminder().textProperty());
    }
}
