package proghf.view;

import javafx.fxml.FXMLLoader;
import proghf.Main;
import proghf.controller.SearchController;
import proghf.model.Table;
import proghf.model.Task;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Kereső nézet
 * <p>
 * A keresési szöveg alapján a táblák közti feladatok nevében keres,
 * majd megjeleníti a találatokat
 */
public class SearchView extends Navigable {

    /**
     * A keresési találatokat tartalmazó lista
     */
    private final List<Task> result;

    /**
     * Keresési nézet létrehozása
     * <p>
     * A keresési szöveg alapján (kisbetűsítve) a megadott táblák
     * közötti feladatok nevében keres. A feltételnek megfelelő találatokat
     * megjeleníti egy listán
     *
     * @param query  a keresési szöveg (a feladat nevének részlete)
     * @param tables a kereséshez használt táblák listája
     */
    public SearchView(String query, List<Table> tables) {
        result = tables.stream().flatMap(table -> {
            return table.getTasks().stream();
        }).filter(task -> {
            return task.getName().toLowerCase().contains(query.toLowerCase());
        }).collect(Collectors.toList());
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("search.fxml"));
        try {
            view = loader.load();
            SearchController controller = loader.getController();
            controller.bindView(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A keresés eredményének lekérése
     *
     * @return a keresési szövegnek megfelelő feladatok
     */
    public List<Task> getResult() {
        return result;
    }
}
