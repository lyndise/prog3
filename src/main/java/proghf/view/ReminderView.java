package proghf.view;

import javafx.fxml.FXMLLoader;
import proghf.Main;
import proghf.controller.ReminderController;
import proghf.model.Reminder;

import java.io.IOException;

/**
 * Emlékeztető feladat nézete
 */
public class ReminderView extends View {

    /**
     * Az emlékeztető nézet modellje
     */
    private final Reminder reminder;

    /**
     * Új emlékeztető nézet létrehozása
     * <p>
     * Betölti a formot és köti a kontrollerhez a nézetet
     *
     * @param reminder az emlékeztető modellje
     */
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

    /**
     * Az emlékeztető modell lekérése
     *
     * @return a nézethez tartozó emlékeztető modell
     */
    public Reminder getReminder() {
        return reminder;
    }
}
