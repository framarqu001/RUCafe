package rucafe.gui;


import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.text.DecimalFormat;

/**
 * Order class represents a unique order comprised of menu Items in RUCafe.
 * User can add and remove menu items from an order.
 */
public class Order {
    private static int TOTAL_ORDERS = 0;

    private int orderNumber;
    private ObservableList<MenuItem> menuItems;
    private double total;
    private SimpleStringProperty totalStringProperty;
    private SimpleStringProperty subTotalStringProperty;
    private SimpleStringProperty salesTaxStringProperty;

    /**
     * Constructs an order object.
     */
    public Order() {
        this.menuItems = FXCollections.observableArrayList();
        subTotalStringProperty = new SimpleStringProperty();
        totalStringProperty = new SimpleStringProperty();
        salesTaxStringProperty = new SimpleStringProperty();

    }

    public Order(Order copyOrder) {
       this.orderNumber = ++TOTAL_ORDERS;
       this.total = copyOrder.total;
       this.totalStringProperty = new SimpleStringProperty();
       this.totalStringProperty.set(copyOrder.totalStringProperty.get());
       this.menuItems = FXCollections.observableArrayList(copyOrder.menuItems);

    }

    /**
     * String property that contains a string showing a formatted total of an order.
     * @return
     */
    public SimpleStringProperty totalStringProperty () {
        return totalStringProperty;
    }

    /**
     * @return String property that contains a string showing a formatted sub-total of an order.
     */
    public SimpleStringProperty subTotalStringProperty () {
        return subTotalStringProperty;
    }

    /**
     * @return String property that contains a string showing the formatted sales-tax of an order.
     */
    public SimpleStringProperty getSalesTaxStringProperty() {
        return salesTaxStringProperty;
    }

    /**
     * Adds a menu Item to the order and increments the menu items price to the total.
     * @param menuItem Menu item to be added to the order.
     */
    public void addToOrder(MenuItem menuItem) {
        menuItems.add(menuItem);
        total += menuItem.getPrice();
        setPriceStrings();
    }

    /**
     * removes a menu Item from order and decrements the menu items price from the order total.
     * @param menuItem Menu item to be removed from the order.
     */
    public void removeFromOrder(MenuItem menuItem) {
        total -= menuItem.getPrice();
        menuItems.remove(menuItem);
        setPriceStrings();
    }

    /**
     * Sets up synchronization between the order total and the reflected total on the gui.
     * This method is called everytime a menu item is added or removed from an order.
     */
    private void setPriceStrings() {
        final double SALES_TAX =.0625;
        DecimalFormat dcFormat = new DecimalFormat("###.##");

        String string = "$" + dcFormat.format(total);
        this.subTotalStringProperty.set(string);

        string = "$" + dcFormat.format(total * (1 + SALES_TAX));
        this.totalStringProperty.set(string);

        string = "$" + dcFormat.format(total * SALES_TAX);
        this.salesTaxStringProperty.set(string);

    }

    public boolean isEmpty() {
        return menuItems.isEmpty();
    }

    public void reset() {
        menuItems.clear();
        total = 0;
        DecimalFormat dcFormat = new DecimalFormat("###.##");
        String string = "$" + dcFormat.format(total);
        this.totalStringProperty.set(string);

        string = "$" + dcFormat.format(total);
        this.subTotalStringProperty.set(string);

        string = "$" + dcFormat.format(total);
        this.salesTaxStringProperty.set(string);
    }

    /**
     * This method allows binding between JavaFX elements and Order's menu items.
     * @return The observable array list of Menu Items.
     */
    public ObservableList<MenuItem> getMenuItems () {
        return menuItems;
    }

    @Override
    public String toString () {
        return  orderNumber + "";
    }
}
