package rucafe.gui;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CurrentOrderController {
    private Stage primaryStage;
    private Scene primaryScene;
    private Order currentOrder;

    @FXML
    ListView<MenuItem> lv_currentOrder;

    @FXML
    TextField tf_total;

    public void setPrimaryStage(Stage stage, Scene scene) {
        primaryStage = stage;
        primaryScene = scene;
    }

    public void setCurrentOrder(Order order) { // come back here..
        currentOrder = order;
        lv_currentOrder.setItems(order.getMenuItems());
        tf_total.textProperty().bind(currentOrder.totalStringProperty());
    }

    @FXML
    protected void displayMain(){
        primaryStage.setScene(primaryScene);
    }


}
