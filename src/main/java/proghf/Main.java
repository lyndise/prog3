package proghf;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import proghf.controller.MainController;

import java.io.IOException;

/**
 * Feladatlista alkalmazás belépési pontja
 * <p>
 * Létrehozza az ablakot és beállítja a bezárást megerősítő eseménykezelőt
 */
public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main.fxml"));
        Parent parent = fxmlLoader.load();
        var controller = (MainController) fxmlLoader.getController();
        controller.bindStage(stage);
        Scene scene = new Scene(parent, 640, 480);
        stage.setScene(scene);
        stage.setTitle("Feladatlista");
        stage.setMinHeight(400.0);
        stage.setMinWidth(600.0);
        stage.setOnCloseRequest(windowEvent -> {
            var alert = new Alert(Alert.AlertType.CONFIRMATION, "Biztosan kilép?");
            alert.setTitle("Megerősítés");
            alert.setHeaderText(null);
            var result = alert.showAndWait();
            if (result.isEmpty() || result.get() != ButtonType.OK) {
                windowEvent.consume();
            }
        });
        stage.show();
    }

}