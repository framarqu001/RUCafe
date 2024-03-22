package rucafe.gui;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.text.DecimalFormat;


abstract public class MenuItem {
    double price;
    private StringProperty priceString = new SimpleStringProperty(); // come back to this

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
        return "$" + new DecimalFormat("###.##").format(getPrice());
    }

    public static void main (String[] args) {

    }
}
