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
    private static int TOTAL_ORDERS = 0;

    private int orderNumber;
    private ObservableList<MenuItem> menuItems;
    private double total; // maybe make into property?
    private SimpleStringProperty totalStringProperty;
    private SimpleStringProperty subTotalStringProperty;
    private SimpleStringProperty salesTaxStringProperty;

    /**
     * Constructs an order object.
     */
    public Order() {
        orderNumber = ++TOTAL_ORDERS;
        this.menuItems = FXCollections.observableArrayList();
        totalStringProperty = new SimpleStringProperty();
        subTotalStringProperty = new SimpleStringProperty();
        salesTaxStringProperty = new SimpleStringProperty();

    }


    public SimpleStringProperty totalStringProperty () {
        return totalStringProperty;
    }

    public SimpleStringProperty subTotalStringProperty () {
        return subTotalStringProperty;
    }
    public void addToOrder(MenuItem menuItem) {
        menuItems.add(menuItem);
        total += menuItem.getPrice();
        setPriceStrings();
    }

    private void setPriceStrings() {
        final double SALES_TAX =.0625;
        DecimalFormat dcFormat = new DecimalFormat("###.##");

        String string = "$" + dcFormat.format(total);
        this.totalStringProperty.set(string);

        string = "$" + dcFormat.format(total * (1 + SALES_TAX));
        this.subTotalStringProperty.set(string);

        string = "$" + dcFormat.format(total * SALES_TAX);
        this.salesTaxStringProperty.set(string);

    }

    public void removeToOrder(MenuItem menuItem) {
        total -= menuItem.getPrice();
        menuItems.remove(menuItem);
        setPriceStrings();
    }

    /**
     * This method allows binding between JavaFX elements and Order's menu items.
     * @return The observable array list of Menu Items.
     */
    public ObservableList<MenuItem> getMenuItems () {
        return menuItems;
    }
}
