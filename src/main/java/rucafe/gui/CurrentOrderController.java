package rucafe.gui;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CurrentOrderController {
    private Stage primaryStage;
    private Scene primaryScene;
    private Order currentOrder;

    public void setPrimaryStage(Stage stage, Scene scene) {
        primaryStage = stage;
        primaryScene = scene;
    }

    public void setCurrentOrder(Order order) { // come back here..
        currentOrder = order;
    }

    @FXML
    protected void displayMain(){
        primaryStage.setScene(primaryScene);
    }


}
