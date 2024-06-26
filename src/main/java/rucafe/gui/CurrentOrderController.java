package rucafe.gui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * This controller handles all the view logic for currentOrderView.
 * Users can remove an item from the current order or place the current order.
 * The scene displays all the menu items in the current order, their details,
 * and the total price of the order.
 *
 * @author Francisco Marquez
 */
public class CurrentOrderController {
    private Stage primaryStage;
    private Scene primaryScene;
    private Order currentOrder;
    private OrderList orderList;

    @FXML
    ListView<MenuItem> lv_currentOrder;

    @FXML
    TextField tf_total, tf_tax, tf_subtotal;

    /**
     * Sets the references to the primary stage/scene.
     * @param stage reference to the primary stage.
     * @param scene reference to the primary scene.
     */
    public void setPrimaryStage(Stage stage, Scene scene) {
        primaryStage = stage;
        primaryScene = scene;
    }

    /**
     * Sets the reference to the current order.
     * Initializes the list view to show the menu items comprising the order.
     * @param order reference to the current order.
     * @param orderList the list of all orders
     */
    public void setCurrentOrder(Order order, OrderList orderList) {
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

    /**
     * Removes a Menu Item from the current order if the item is selected from the list view.
     * Ensures that item an item can be removed from a list before removing it.
     */
    @FXML
    public void removeItem() {
        ObservableList<MenuItem> menuItems = currentOrder.getMenuItems();
        int index = lv_currentOrder.getSelectionModel().getSelectedIndex();
        if (index == -1) {
            Alert warning = new Alert(Alert.AlertType.WARNING);
            warning.setTitle("Remove item");
            warning.setContentText("No items to be removed");
            warning.showAndWait();
            return;
        }
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Remove item");
        confirmation.setContentText("Are you sure you want to remove:\n " + menuItems.get(index));
        Optional<ButtonType> button = confirmation.showAndWait();
        if (button.get() == ButtonType.OK) {
            currentOrder.removeFromOrder(menuItems.get(index));

        }
    }

    /**
     * Places an order by adding the order to the OrderList.
     * Ask user for confirmation before placing the order.
     */
    @FXML
    public void placeOrder() {
        if (currentOrder.isEmpty()) {
            Alert warning = new Alert(Alert.AlertType.WARNING);
            warning.setTitle("Place order");
            warning.setContentText("No items in current order");
            warning.showAndWait();
            return; // no item to be removed
        }
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
