package rucafe.gui;


import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.text.DecimalFormat;



/**
 * Order class represents a unique order in RUCafe that is made up of Menu Items.
 * Order keeps track of a unique order number, and the total of an order.
 * String properties are used to help the gui reflect dynamic changes to the backend.
 */
public class Order {
    private static int TOTAL_ORDERS = 0;

    private int orderNumber;
    private ObservableList<MenuItem> menuItems;
    private double total; // maybe make into property?
    private SimpleStringProperty totalStringProperty;

    /**
     * Constructs an order object.
     */
    public Order() {
        orderNumber = ++TOTAL_ORDERS;
        this.menuItems = FXCollections.observableArrayList();
        totalStringProperty = new SimpleStringProperty();
    }


    public SimpleStringProperty totalStringProperty () {
        return totalStringProperty;
    }

    /**
     *
     */
    public void setTotalString () {
        String string = "$" + new DecimalFormat("###.##").format(total);
        this.totalStringProperty.set(string);
    }

    /**
     * Adds a menu item to the order.
     * @param menuItem menu item to be added to the order.
     */
    public void addToOrder(MenuItem menuItem) {
        menuItems.add(menuItem);
        total += menuItem.getPrice();
        setTotalString();
    }

    /**
     * Removes a menu item from the order.
     * @param menuItem menu item to be removed from the order.
     */
    public void removeToOrder(MenuItem menuItem) {
        total -= menuItem.getPrice();
        menuItems.remove(menuItem);
        setTotalString();
    }

    /**
     * This method allows binding between JavaFX elements and Order's menu items.
     * @return The observable array list of Menu Items.
     */
    public ObservableList<MenuItem> getMenuItems () {
        return menuItems;
    }
}
