package rucafe.gui;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

abstract public class MenuItem {
    double price;

    public MenuItem () {
    }

    public abstract double price();

    public double getPrice () {
        return price;
    }

    public void setPrice (double newPrice) {
        price = newPrice;
    }

    @Override
    public String toString () {
        return String.format("%.2f", price);
    }
}
