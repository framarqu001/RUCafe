package rucafe.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Sandwich extends MenuItem {

    private enum SandwichType{
        BEEF(10.99), CHICKEN(8.99), FISH(9.99);

        final double price;
        SandwichType(double price) {
            this.price = price;
        }

        public double getPrice() {
            return this.price;
        }
    }

    private enum AddIns {
        CHEESE(1.0), LETTUCE(0.3), TOMATOES(0.3), ONIONS(0.3);

        final double price;

        AddIns(double price) {
            this.price = price;
        }

        public double getPrice() {
            return this.price;
        }
    }

    private enum Bread {
        BAGEL, WHEAT, SOURDOUGH;
    }

    private SandwichType sandwichType;
    private Bread bread;
    private ObservableList<Sandwich.AddIns> addIns;

    public Sandwich(SandwichType sandwichType, Bread bread) {
        this.sandwichType = sandwichType;
        this.bread = bread;
        this.addIns = FXCollections.observableArrayList();
        setPrice(price());
    }

    private double getAddInsPrice() {
        double price = 0;
        for (AddIns extraAddIns : addIns) {
            price += extraAddIns.getPrice();
        }
        return price;
    }

    public void setSandwichType(SandwichType sandwichType) {
        this.sandwichType = sandwichType;
        setPrice(price());
    }

    public void setBread(Bread bread) {
        this.bread = bread;
    }

    public boolean addAddIn(AddIns addIn) {
        if(!(addIns.contains(addIn))) {
            addIns.add(addIn);
            setPrice(price());
            return true;
        }
        return false;
    }

    public boolean removeAddIn(AddIns addIn) {
        boolean success = addIns.remove(addIn);
        setPrice(price());
        return success;
    }

    @Override
    public double price () {
        double price = 0;
        price += sandwichType.getPrice();
        price += getAddInsPrice();
        return price;
    }

    public static ObservableList<AddIns> getSandwichAddInList(){
        return FXCollections.observableArrayList(AddIns.values());
    }

    public static ObservableList<Bread> getBreadList(){
        return FXCollections.observableArrayList(Bread.values());
    }

    public static ObservableList<SandwichType> getSandwichTypeList(){
        return FXCollections.observableArrayList(SandwichType.values());
    }

}
