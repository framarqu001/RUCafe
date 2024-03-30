package rucafe.gui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Optional;

public class CurrentOrderController {
    private Stage primaryStage;
    private Scene primaryScene;
    private Order currentOrder;
    private OrderList orderList;

    @FXML
    ListView<MenuItem> lv_currentOrder;

    @FXML
    TextField tf_total, tf_tax, tf_subtotal;

    public void setPrimaryStage(Stage stage, Scene scene) {
        primaryStage = stage;
        primaryScene = scene;
    }

    /**
     * Sets the reference to the current order.
     * Initializes the list view to show the menu items comprising the order.
     * @param order reference to the current order.
     */
    public void setCurrentOrder(Order order, OrderList orderList) { // come back here..
        currentOrder = order;
        this.orderList = orderList;
        lv_currentOrder.setItems(order.getMenuItems());
        lv_currentOrder.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tf_total.textProperty().bind(currentOrder.totalStringProperty());
        tf_tax.textProperty().bind(currentOrder.getSalesTaxStringProperty());
        tf_subtotal.textProperty().bind(currentOrder.subTotalStringProperty());

    }

    /**
     * Changes the current scene back the primary scene (mainView).
     */
    @FXML
    protected void displayMain(){
        primaryStage.setScene(primaryScene);
    }

    @FXML
    public void removeItem() {
        if (currentOrder.isEmpty()) return; // no item to be removed

        ObservableList<MenuItem> menuItems = currentOrder.getMenuItems();
        int index = lv_currentOrder.getSelectionModel().getSelectedIndex();
        if (index == -1) {
            return; //no item selected
        }
        Alert warning = new Alert(Alert.AlertType.WARNING);
        warning.setTitle("Remove item");
        warning.setContentText("Are you sure you want to remove:\n " + menuItems.get(index));
        Optional<ButtonType> button = warning.showAndWait();
        if (button.get() == ButtonType.OK) {
            currentOrder.removeFromOrder(menuItems.get(index));

        }
    }

    @FXML
    public void placeOrder() {
        if (currentOrder.isEmpty()) return;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Order confirmation");
        alert.setContentText("Place this order?");
        Optional<ButtonType> button = alert.showAndWait();
        if (button.get() == ButtonType.OK) {
            Order copyOrder = new Order(currentOrder);
            orderList.addOrder(copyOrder);
            currentOrder.reset();
            Alert confirmAlert = new Alert(Alert.AlertType.INFORMATION);
            confirmAlert.setTitle("Order");
            confirmAlert.setHeaderText("");
            confirmAlert.setContentText("Order has been placed");
            confirmAlert.showAndWait();
            primaryStage.setScene(primaryScene);
        }

    }


}
