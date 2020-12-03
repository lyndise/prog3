package proghf.view;

import javafx.fxml.FXMLLoader;
import proghf.Main;
import proghf.controller.SearchController;
import proghf.controller.TableCollectionController;
import proghf.model.Table;
import proghf.model.TableCollection;
import proghf.model.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class SearchView extends Navigable {
    private List<Task> result;

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

    public List<Task> getResult() {
        return result;
    }
}
