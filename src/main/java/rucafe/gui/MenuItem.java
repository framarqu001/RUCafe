package rucafe.gui;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.text.DecimalFormat;


abstract public class MenuItem {
    double price;
    private StringProperty priceStringProperty; // come back to this

    public MenuItem () {
        priceStringProperty = new SimpleStringProperty();
    }

    public MenuItem(MenuItem menuItem) {
        this.price = menuItem.price;
        this.priceStringProperty = new SimpleStringProperty();
        this.priceStringProperty.set(menuItem.getPriceString());
    }

    public abstract double price();

    @Override
    public MenuItem clone() {
        return null;}

    public double getPrice () {
        return price;
    }

    public void setPrice (double newPrice) {
        price = newPrice;
        setPriceString();
    }

    public String getPriceString () {
        return priceStringProperty.get();
    }

    public StringProperty priceStringProperty () {
        return priceStringProperty;
    }

    public void setPriceString () {
        String string = "$" + new DecimalFormat("###.##").format(getPrice());
        this.priceStringProperty.set(string);
    }

    @Override
    public String toString () {
        return "$" + new DecimalFormat("###.##").format(getPrice());
    }

    public static void main (String[] args) {

    }
}
