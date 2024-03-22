package rucafe.gui;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CurrentOrderController {
    private Stage primaryStage;
    private Scene primaryScene;

    public void setPrimaryStage(Stage stage, Scene scene) {
        primaryStage = stage;
        primaryScene = scene;
    }

    @FXML
    protected void displayMain(){
        primaryStage.setScene(primaryScene);
    }
}
