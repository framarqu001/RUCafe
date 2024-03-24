package rucafe.gui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.stage.Stage;

public class OrderListController {
    private Stage primaryStage;
    private Scene primaryScene;
    private OrderList orderList;


    @FXML
    ListView<MenuItem> lv_orders;

    @FXML
    ChoiceBox<Order> cb_orderNumbers;


    public void setPrimaryStage(Stage stage, Scene scene) {
        primaryStage = stage;
        primaryScene = scene;
    }

    public void setOrderList(OrderList orderList) {
        this.orderList = orderList;
        if (orderList.isEmpty()) return;
        ObservableList<MenuItem> menuItems = orderList.getOrderList().getLast().getMenuItems();
        lv_orders.setItems(menuItems);
        cb_orderNumbers.setItems(orderList.getOrderList());
        cb_orderNumbers.setValue(orderList.getOrderList().getLast());
        //make it so that when cb changes value it displays the correct list of menu items
    }

    @FXML
    protected void displayMain(){
        primaryStage.setScene(primaryScene);
    }
}
