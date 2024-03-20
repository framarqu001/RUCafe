package rucafe.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class CoffeeController {
    private Stage primaryStage;
    private Scene primaryScene;
    private RUCafeMainController mainController;

    public void setPrimaryStage(Stage stage, Scene scene) {
        primaryStage = stage;
        primaryScene = scene;
    }

    @FXML
    protected void displayMain(){
        primaryStage.setScene(primaryScene);
    }

}