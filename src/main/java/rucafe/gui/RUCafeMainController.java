package rucafe.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class RUCafeMainController {
    private Stage primaryStage;
    private Scene primaryScene;

    public void setPrimaryStage(Stage stage, Scene scene) {
        primaryStage = stage;
        primaryScene = scene;
    }


    @FXML
    protected void displayCoffeeView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("coffeeView.fxml"));
            Scene scene = new Scene(loader.load());
            primaryStage.setScene(scene);
            CoffeeController coffeeController = loader.getController();
            coffeeController.setPrimaryStage(primaryStage, primaryScene);

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading View1.fxml.");
            alert.setContentText("Couldn't load View1.fxml.");
            alert.showAndWait();
        }
    }

    @FXML
    protected void displayCurrentOrderView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("currentOrderView.fxml"));
            Scene scene = new Scene(loader.load());
            primaryStage.setScene(scene);
            CurrentOrderController coffeeController = loader.getController();
            coffeeController.setPrimaryStage(primaryStage, primaryScene);

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading View1.fxml.");
            alert.setContentText("Couldn't load View1.fxml.");
            alert.showAndWait();
        }
    }
}