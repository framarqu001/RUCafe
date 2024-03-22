package rucafe.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class OrderList {
    ObservableList<Order> orderList;

    public OrderList () {
        this.orderList = FXCollections.observableArrayList();
    }

    public void removeOrder(Order order) {
        orderList.remove(order);
    }

}
