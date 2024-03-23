package rucafe.gui;


import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.text.DecimalFormat;
import java.util.ArrayList;


/**
 * Order class represents the current order;
 */
public class Order {
    private static int totalOrders = 0;
    private int orderNumber;
    private ObservableList<MenuItem> menuItems;
    private double total; // maybe make into property?
    private SimpleStringProperty totalStringProperty;

    public Order() {
        orderNumber = ++totalOrders;
        this.menuItems = FXCollections.observableArrayList();
        totalStringProperty = new SimpleStringProperty();
    }

    public String getTotalString () {
        return totalStringProperty.get();
    }

    public SimpleStringProperty totalStringProperty () {
        return totalStringProperty;
    }

    public void setTotalString () {
        String string = "$" + new DecimalFormat("###.##").format(total);
        this.totalStringProperty.set(string);
    }

    public void addToOrder(MenuItem menuItem) {
        menuItems.add(menuItem);
        total += menuItem.getPrice();
        setTotalString();
    }

    public void removeToOrder(MenuItem menuItem) {
        total -= menuItem.getPrice();
        menuItems.remove(menuItem);
        setTotalString();
    }

    public ObservableList<MenuItem> getMenuItems () {
        return menuItems;
    }
}
