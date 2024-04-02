package rucafe.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The OrderList class contains a list of all the orders placed in RUCafe.
 *
 * @author Ashley Berlinski, Francisco Marquez
 */
public class OrderList {
    ObservableList<Order> orderList;

    /**
     * Constructs an OrderList with a new Observable Array List.
     */
    public OrderList () {
        this.orderList = FXCollections.observableArrayList();
    }

    /**
     * Removes an order from the list.
     * @param order order to be removed.
     */
    public void removeOrder (Order order) {
        orderList.remove(order);
    }

    /**
     * Adds an order to the list.
     * @param order order to be added.
     */
    public void addOrder (Order order) {
        orderList.add(order);
    }

    /**
     * Returns The list of orders held by OrderList
     * @return The list of orders held by OrderList
     */
    public ObservableList<Order> getOrderList () {
        return orderList;
    }

    /**
     * Determines if the order list has any orders.
     * @return True if order list is empty, false otehrwise.
     */
    public boolean isEmpty () {
        return orderList.isEmpty();
    }

    /**
     * Formats each order in the list to display the order #, details of each menu item, and subtotal.
     * @return A formatted String to be written to a file.
     */
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
