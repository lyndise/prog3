package proghf;

import javafx.scene.Node;
import proghf.controller.MainController;

import java.util.Stack;

/**
 * Nézet kezelő
 * <p>
 * Megvalósítja a navigálható nézetek cseréjét
 */
public class ViewManager {
    /**
     * Egyke (singleton) példány
     */
    private static ViewManager instance;
    /**
     * A nézeteket tartalmazó verem (előzmények)
     */
    private final Stack<Node> viewHistory = new Stack<>();
    /**
     * Az alkalmazás főképernyőjének kontrollere
     */
    private MainController mainController;

    private ViewManager() {
    }

    /**
     * Az osztálypéldány lekérése
     *
     * @return az osztálypéldány
     */
    public static ViewManager getInstance() {
        if (instance == null) {
            instance = new ViewManager();
        }
        return instance;
    }

    /**
     * A főképernyő kontrollerének bekötése
     *
     * @param controller a főképernyő kontrollere
     */
    public void registerMainController(MainController controller) {
        this.mainController = controller;
    }

    /**
     * Az aktuális nézet cseréje a megadott nézetre
     *
     * @param n az új nézet
     */
    public void setCurrentView(Node n) {
        this.viewHistory.push(n);
        mainController.setContent(n);
    }

    /**
     * Az aktuális nézet cseréje a korábbira
     */
    public void navigateBack() {
        viewHistory.pop();
        if (!viewHistory.empty()) {
            setCurrentView(viewHistory.pop());
        }
    }
}
