package proghf.view;

import javafx.fxml.FXML;
import javafx.scene.Node;
import proghf.TableManager;

public abstract class Navigable {
    protected Node view;

    public void activate() {
        TableManager.getInstance().setCurrentView(view);
    }

    @FXML
    private void onBackPressed() {
        TableManager.getInstance().navigateBack();
    };

    public Node getView() {
        return view;
    }
}
