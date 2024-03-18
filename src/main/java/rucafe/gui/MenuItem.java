package rucafe.gui;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.ArrayList;

abstract public class MenuItem {
    private DoubleProperty price = new SimpleDoubleProperty();

    public MenuItem () {
    }

    public abstract double price();

    public double getPrice () {
        return price.get();
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public void setPrice (double newPrice) {
        price.set(newPrice);
    }

    @Override
    public String toString () {
        return String.format("%.2f", getPrice());
    }

    public static void main (String[] args) {

    }
}
