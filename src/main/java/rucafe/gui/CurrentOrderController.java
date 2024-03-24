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

    /**
     * Sets the reference to the current order.
     * Initializes the list view to show the menu items comprising the order.
     * @param order reference to the current order.
     */
    public void setCurrentOrder(Order order) { // come back here..
        currentOrder = order;
        lv_currentOrder.setItems(order.getMenuItems());
        tf_total.textProperty().bind(currentOrder.totalStringProperty());
    }

    /**
     * Changes the current scene back the primary scene (mainView).
     */
    @FXML
    protected void displayMain(){
        primaryStage.setScene(primaryScene);
    }


}
