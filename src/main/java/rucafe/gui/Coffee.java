package rucafe.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;


public class Coffee extends MenuItem {
    public enum Size {
        SHORT, TALL, GRANDE, VENTI;
        @Override
        public String toString () {
            String string = name().toLowerCase();
            return string.substring(0,1).toUpperCase() + string.substring(1);
        }
    }
    public enum AddIns {
        SWEET_CREAM, FRENCH_VANILLA, IRISH_CREAM, CARAMEL, MOCHA;

        @Override
        public String toString () {
            String string = name().toLowerCase();
            return string.substring(0,1).toUpperCase() + string.substring(1);
        }
    }

    private Size size;
    private ObservableList<AddIns> addIns;
    private int quantity;


    public Coffee() {
        this.addIns = FXCollections.observableArrayList();
        quantity = 1;
        size = Size.SHORT;
        price();
    }

    public Coffee(Coffee copy) {
        super(copy);
        this.size = copy.size;
        this.addIns = copy.addIns;
        this.quantity = copy.quantity;
    }

    @Override
    public double price () {
        double price = 1.99; // base price.
        price += addIns.size() * .30; //30 cents for every add in.
        switch (size) {
            case SHORT -> price += 0;
            case TALL -> price += .50;
            case GRANDE -> price += 1.00;
            case VENTI -> price += 1.50;
        }
        price *= quantity;
        setPrice(price);
        return price;
    }

    public void setSize (Size size) {
        this.size = size;
        price();
    }

    public boolean addAddIn(AddIns newAddIn) {
        if (!addIns.contains(newAddIn)) {
            addIns.add(newAddIn);
            price();
            return true;
        }
        return false;
    }

    public boolean removeAddIn(AddIns removeAddIn) {
        boolean success = addIns.remove(removeAddIn);
        price();
        return success;
    }

    public void setQuantity(int amount){
        quantity = amount;
        price();
    }

    public void reset(){
        this.quantity = 1;
        this.size = Size.SHORT;
        addIns.clear();
        price();
    }

    @Override
    public String toString () {
        String addinString = addIns.isEmpty() ? "None" : addIns.toString();
        return quantity + " " + size + " Coffee Add-Ins: " + addinString + " Total: " + getPriceString();
    }

    public static void main (String[] args) {

        Coffee coffeeTest2 = new Coffee();
        System.out.println(coffeeTest2.getPriceString());


    }

}
