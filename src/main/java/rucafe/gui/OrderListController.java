package rucafe.gui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

/**
 * OrderListController handles all view logic for the orders placed in RUCafe.
 * Users can Remove an order from the system, or export the orders to a file.
 *
 * @author  Francisco Marquez
 */
public class OrderListController {
    private Stage primaryStage;
    private Scene primaryScene;
    private OrderList orderList;

    @FXML
    ListView<MenuItem> lv_orders;
    @FXML
    ChoiceBox<Order> cb_orderNumbers;
    @FXML
    TextField tf_subTotal;

    /**
     * Retrieves the reference to OrderList from the main view.
     * Sets the items of the choice box with the list of Orders and adds a listener to the choice box.
     * The listener sets the list view to display the contents of the order selected from the choice box.
     * @param orderList The orderList containing all orders placed in RUCafe.
     */
    public void setOrderList(OrderList orderList) {
        this.orderList = orderList;
        if (orderList.isEmpty()) return;

        ObservableList<MenuItem> menuItems = orderList.getOrderList().getLast().getMenuItems();
        cb_orderNumbers.valueProperty().addListener((observable) -> {
            if (cb_orderNumbers.getValue() != null) {
                lv_orders.setItems(cb_orderNumbers.getValue().getMenuItems());
                tf_subTotal.setText(cb_orderNumbers.getValue().subTotalStringProperty().get());
            }
        });
        cb_orderNumbers.setItems(orderList.getOrderList());
        cb_orderNumbers.setValue(orderList.getOrderList().getLast());
    }


    /**
     * Removes an order from the OrderList.
     * Confirms with the user before removing the order.
     */
    @FXML
    public void removeOrder() {
        if (orderList.isEmpty()) return;
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Remove item");
        confirmation.setContentText("Are you sure you want to remove order #: " + cb_orderNumbers.getValue());
        Optional<ButtonType> button = confirmation.showAndWait();
        if (button.get() == ButtonType.OK) {
            orderList.removeOrder(cb_orderNumbers.getValue());
            Alert confirmAlert = new Alert(Alert.AlertType.INFORMATION);
            confirmAlert.setTitle("Remove Item");
            confirmAlert.setHeaderText("");
            confirmAlert.setContentText("Order has been removed");
            confirmAlert.showAndWait();
            if (orderList.isEmpty()) {
                lv_orders.getItems().clear();
                tf_subTotal.setText("$0.00");
                Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);
                informationAlert.setTitle("Order");
                informationAlert.setHeaderText("");
                informationAlert.setContentText("No more Orders to be displayed");
                informationAlert.showAndWait();
                primaryStage.setScene(primaryScene);
                return;
            }
            cb_orderNumbers.setValue(orderList.getOrderList().getLast());
        }
    }

    /**
     * Changes the current scene back the primary scene (mainView).
     */
    @FXML
    protected void displayMain(){
        primaryStage.setScene(primaryScene);
    }

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
     * Exports all the orders in orderList to a .txt file displaying all the information shown on the gui.
     */
    @FXML
    public void exportOrder() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Order confirmation");
        alert.setContentText("Export these orders?");
        Optional<ButtonType> button = alert.showAndWait();
        if (button.get() == ButtonType.OK) {
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Open Target File for the Export");
            chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                    new FileChooser.ExtensionFilter("All Files", "*.*"));
            Stage stage = primaryStage;
            File targetFile = chooser.showSaveDialog(stage);
            if (targetFile == null) {
                return;
            }
            try (PrintWriter pw = new PrintWriter(targetFile)) {
                pw.write(orderList.printOrders());
            } catch (IOException e) {
                System.out.println("cannot open file");
            }
        }

    }
}
