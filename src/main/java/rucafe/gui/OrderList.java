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

    public void addOrder(Order order) {
        orderList.add(order);
    }

    public ObservableList<Order> getOrderList () {
        return orderList;
    }

    public boolean isEmpty(){
        return orderList.isEmpty();
    }
}
