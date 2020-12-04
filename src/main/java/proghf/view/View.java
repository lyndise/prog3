package proghf.view;

import javafx.scene.Node;

/**
 * Általános nézetet tároló osztály
 */
public abstract class View {

    /**
     * A nézetet tároló mező
     */
    protected Node view;

    /**
     * A nézet lekérése
     *
     * @return a nézet
     */
    public Node getView() {
        return view;
    }
}
