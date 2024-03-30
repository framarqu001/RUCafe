package rucafe.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class OrderList {
    ObservableList<Order> orderList;

    public OrderList () {
        this.orderList = FXCollections.observableArrayList();
    }

    public void removeOrder (Order order) {
        orderList.remove(order);
    }

    public void addOrder (Order order) {
        orderList.add(order);
    }

    public ObservableList<Order> getOrderList () {
        return orderList;
    }

    public boolean isEmpty () {
        return orderList.isEmpty();
    }

    public int findByID (int id) {
        for (Order element : orderList) {
            if (element.getID() == id) {
                return element.getID();
            }
        }
        return -1;
    }

    public String printOrders() {
        String string = "";
        for (Order order : orderList) {
            string += "Order: #" + order + "\n";
                for (MenuItem menuItem : order.getMenuItems()) {
                    string += "\t" + menuItem + "\n";
                }
                string += "\t" + "Sub-Total: " + order.subTotalStringProperty().get() + "\n\n";
        }
        return string;
    }
}
