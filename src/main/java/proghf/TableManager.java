package proghf;

import javafx.scene.Node;
import proghf.controller.MainController;

import java.util.Stack;

public class TableManager {
    private static TableManager instance;

    private MainController mainController;

    private Stack<Node> viewHistory = new Stack<>();

    private TableManager() {}

    public static TableManager getInstance() {
        if (instance == null) {
            instance = new TableManager();
        }
        return instance;
    }

    public void registerMainController(MainController controller) {
        this.mainController = controller;
    }

    public void setCurrentView(Node n) {
        this.viewHistory.push(n);
        mainController.setContent(n);
    }

    public void navigateBack() {
        viewHistory.pop();
        setCurrentView(viewHistory.pop());
    }
}
