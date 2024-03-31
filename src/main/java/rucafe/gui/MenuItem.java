package rucafe.gui;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.text.DecimalFormat;

/**
 * The Menu class is an abstract class for all items in the RU Cafe.
 * All menu items must implement the abstract method price().
 *
 * @author Francisco Marquez
 */
abstract public class MenuItem {
    double price;
    private StringProperty priceStringProperty;

    /**
     * Default constructor for Menu Item. Initializes a simple string property.
     */
    public MenuItem () {
        priceStringProperty = new SimpleStringProperty();
    }

    /**
     * Clone constructor for Menu Item.
     * @param menuItem Menu Item to be cloned
     */
    public MenuItem(MenuItem menuItem) {
        this.price = menuItem.price;
        this.priceStringProperty = new SimpleStringProperty();
        this.priceStringProperty.set(menuItem.getPriceString());
    }

    /**
     * Abstract method that determines the price of a menu item.
     * @return price of the item.
     */
    public abstract double price();

    @Override
    public MenuItem clone() {
        return null;}

    /**
     * @return The price of a menu Item.
     */
    public double getPrice () {
        return price;
    }

    /**
     * Sets the price of a menu item and updates string property.
     * @param newPrice Price to be set to.
     */
    public void setPrice (double newPrice) {
        price = newPrice;
        setPriceString();
    }

    /**
     * @return A String value of the menu items priceStringProperty.
     */
    public String getPriceString () {
        return priceStringProperty.get();
    }

    /**
     * @return A priceStringProperty of the menu item
     */
    public StringProperty priceStringProperty () {
        return priceStringProperty;
    }

    /**
     * Sets the value of a priceStringProperty. This method gets called everytime price is updated
     * in order to reflect changes in the gui.
     */
    public void setPriceString () {
        String string = "$" + new DecimalFormat("###.##").format(getPrice());
        this.priceStringProperty.set(string);
    }

    /**
     * @return A formatted String reflecting the menu item's price in format $xx.xx.
     */
    @Override
    public String toString () {
        return "$" + new DecimalFormat("###.##").format(getPrice());
    }

}
