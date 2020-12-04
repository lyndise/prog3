package proghf.view;

import proghf.ViewManager;

/**
 * Navigálható nézet osztály
 * <p>
 * Segítségével a vissza gomb kezelése egységesen történik
 */
public abstract class Navigable extends View {

    /**
     * A navigálható nézet aktiválása
     * <p>
     * Értesíti a tábla kezelőt, hogy aktiválja a nézetet
     */
    public void activate() {
        ViewManager.getInstance().setCurrentView(view);
    }
}
