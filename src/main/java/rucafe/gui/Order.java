package rucafe.gui;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;


/**
 * Order class represents the current order;
 */
public class Order {
    private static int totalOrders = 0;
    private int orderNumber;
    private ObservableList<MenuItem> menuItems;
    private double total; // maybe make into property?

    public Order() {
        orderNumber = ++totalOrders;
        this.menuItems = FXCollections.observableArrayList();
    }

    public void addToOrder(MenuItem menuItem) {
        menuItems.add(menuItem);
        total += menuItem.getPrice();
    }

    public void removeToOrder(MenuItem menuItem) {
        total -= menuItem.getPrice();
        menuItems.remove(menuItem);
    }


}
