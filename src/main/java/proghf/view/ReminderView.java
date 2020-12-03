package proghf.view;

import javafx.fxml.FXMLLoader;
import proghf.Main;
import proghf.controller.ReminderController;
import proghf.model.Reminder;
import java.io.IOException;

public class ReminderView extends View {
    private Reminder reminder;


    public ReminderView(Reminder reminder) {
        this.reminder = reminder;
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("reminder.fxml"));
        try {
            view = loader.load();
            ReminderController controller = loader.getController();
            controller.bindView(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Reminder getReminder() {
        return reminder;
    }
}
