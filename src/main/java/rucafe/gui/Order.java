package rucafe.gui;


import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.text.DecimalFormat;

/**
 * Order class represents a unique order comprised of menu Items in RUCafe.
 * User can add and remove menu items from an order.
 *
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

    /**
     * Copy constructor for an Order.
     * This constructor is used to pass an order from OrderController Class to an OrderList object.
     * @param copyOrder the copyOrder to be cloned.
     */
    public Order(Order copyOrder) {
       this.orderNumber = ++TOTAL_ORDERS;
       this.total = copyOrder.total;
       this.subTotalStringProperty = new SimpleStringProperty();
       this.subTotalStringProperty.set(copyOrder.subTotalStringProperty.get());
       this.menuItems = FXCollections.observableArrayList(copyMenuItems(copyOrder.menuItems));
    }

    /**
     * String property that contains a string showing a formatted total of an order.
     * @return String property that contains a string showing a formatted total of an order.
     */
    public SimpleStringProperty totalStringProperty () {
        return totalStringProperty;
    }

    /**
     * Returns String property that contains a string showing a formatted sub-total of an order.
     * @return String property that contains a string showing a formatted sub-total of an order.
     */
    public SimpleStringProperty subTotalStringProperty () {
        return subTotalStringProperty;
    }

    /**
     * Returns String property that contains a string showing the formatted sales-tax of an order.
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

    /**
     * Helper method to clone an order. Creates cloned Menu Items to be added to a cloned Order.
     * @param menuItems Menu Items to be cloned.
     * @return a new observable list of menu items with cloned menu items.
     */
    private static ObservableList<MenuItem> copyMenuItems(ObservableList<MenuItem> menuItems) {
        ObservableList<MenuItem> copy = FXCollections.observableArrayList();
        for (MenuItem item : menuItems) {
            copy.add(item.clone());
        }
        return copy;
    }

    /**
     * Determines if order has any menu items or not.
     * @return true if the order contains no menu items, false otherwise.
     */
    public boolean isEmpty() {
        return menuItems.isEmpty();
    }

    /**
     * Resets an Order to its default values when constructed.
     */
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

    /**
     * Returns the ID number of the Order.
     * @return the ID number of the Order.
     */
    public int getID () {
        return TOTAL_ORDERS;
    }


    /**
     * Returns a formatted String of an order only showing its order number.
     * @return Formatted String of an order only showing its order number.
     */
    @Override
    public String toString () {
        return  orderNumber + "";
    }
}
