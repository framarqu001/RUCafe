package rucafe.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Class meant to represent a sandwich for a cafe software, extends MenuItem.
 * Sandwiches can be either Beef ($10.99), Chicken ($8.99) or Fish ($9.99) with a selection of three kinds of
 * bread: Sourdough, Bagel, Wheat. Customer can add-on Lettuce, Tomatoes, Onions and/Cheese for an extra charge.
 * ($0.30 for veggies and $1.00 for cheese)
 */
public class Sandwich extends MenuItem {

    /**
     * Enum meant to represent the types of sandwiches a person can order along with their prices.
     */
    enum SandwichType{
        BEEF(10.99), CHICKEN(8.99), FISH(9.99);

        final double price;

        /**
         * Constructor for Sandwich Type, takes in price as parameter
         * @param price the price of the sandwich.
         */
        SandwichType(double price) {
            this.price = price;
        }

        /**
         * Returns price of sandwich
         * @return price of sandwich.
         */
        public double getPrice() {
            return this.price;
        }
    }

    /**
     * Enum meant to represent add-ins for a sandwich along with their extra charge/price.
     */
    enum AddIn {
        CHEESE(1.0), LETTUCE(0.3), TOMATOES(0.3), ONIONS(0.3);

        final double price;

        /**
         * Constructor for AddIn, takes in price as parameter
         * @param price the price of the AddIn.
         */
        AddIn(double price) {
            this.price = price;
        }

        /**
         * Returns price of addIn
         * @return price of addIn.
         */
        public double getPrice() {
            return this.price;
        }
    }

    /**
     * Enum meant to represent the types of bread for a sandwich.
     */
    enum Bread {
        BAGEL, WHEAT, SOURDOUGH;
    }

    private SandwichType sandwichType;
    private Bread bread;
    private ObservableList<Sandwich.AddIn> addIns;

    /**
     * Constructor for a sandwich, takes in the sandwich type and its bread.
     * @param sandwichType the type of sandwich
     * @param bread the bread of the sandwich
     */
    public Sandwich(SandwichType sandwichType, Bread bread) {
        this.sandwichType = sandwichType;
        this.bread = bread;
        this.addIns = FXCollections.observableArrayList();
        setPrice(price());
    }

    /**
     * Helper method, returns the extra charge for all the add-ins in the sandwich
     * @return the price of all the add-ins in the sandwich
     */
    private double getAddInsPrice() {
        double price = 0;
        for (AddIn extraAddIns : addIns) {
            price += extraAddIns.getPrice();
        }
        return price;
    }

    /**
     * Helper method, returns a string containing all the adds-ins in the sandwich
     * @return returns a string containing all the adds-ins in the sandwich, if there is none returns "NONE"
     */
    private String addInsString() {
        String addInString = "";
        int removeCommaAndSpace = 2;

        for (AddIn extraAddIns : addIns) {
            addInString += extraAddIns.name() + ", ";
        }

        if(addInString.isEmpty())
            return "NONE";
        else
            return addInString.substring(0, addInString.length() - removeCommaAndSpace);
    }

    /**
     * Given a sandwich type, sets the type of the sandwich to that value.
     * @param sandwichType the type of sandwich the sandwich will be changed to.
     */
    public void setSandwichType(SandwichType sandwichType) {
        this.sandwichType = sandwichType;
        setPrice(price());
    }

    /**
     * Given a bread, sets the bread of the sandwich to that value.
     * @param bread the type of bread the sandwich will be changed to.
     */
    public void setBread(Bread bread) {
        this.bread = bread;
    }

    /**
     * Given an add-In adds that to the sandwich if it wasn't already on there.
     * @param addIn the add-in attempting to be added.
     * @return true if was successful in adding, false if otherwise.
     */
    public boolean addAddIn(AddIn addIn) {
        if(!(addIns.contains(addIn))) {
            addIns.add(addIn);
            setPrice(price());
            return true;
        }
        return false;
    }

    /**
     * Given an array of add-Ins adds them to the sandwich if they weren't already on there.
     * @param addIns the add-ins attempting to be added.
     */
    public void addAddIns(AddIn [] addIns) {
        for (AddIn extraAddIns : addIns)
            addAddIn(extraAddIns);
    }

    /**
     * Given an add-In removes that off the sandwich if it was there.
     * @param addIn the add-in attempting to be removed.
     * @return true if was successful in removing, false if otherwise.
     */
    public boolean removeAddIn(AddIn addIn) {
        boolean success = addIns.remove(addIn);
        setPrice(price());
        return success;
    }

    /**
     * Given an array of add-Ins removes them from the sandwich if they were already on there.
     * @param addIns the add-ins attempting to be removed.
     */
    public void removeAddIns(AddIn [] addIns) {
        for (AddIn extraAddIns : addIns)
            removeAddIn(extraAddIns);
    }

    /**
     * Returns the price of the sandwich based on its type and the extra add-ins
     * @return the price of the sandwich.
     */
    @Override
    public double price () {
        double price = 0;
        int rounding = 100;
        price += (sandwichType.getPrice() * rounding);
        price += (getAddInsPrice() * rounding);
        return price / rounding;
    }

    /**
     * Returns a string representing the sandwich, lists its type, its bread, its add-ins and its price.
     * @return a string of the format "Sandwich: [type] \tBread: [bread] \t Add-Ins: [add-Ins] \t Price: [price]"
     */
    @Override
    public String toString() {
        return "Sandwich: " + sandwichType + "\tBread: " + bread + "\tAdd-Ins: "
                + addInsString() + "\tPrice: " + super.toString();
    }

    /**
     * Returns an observable list containing all the add-in values.
     * @return an observable list containing all the add-in values.
     */
    public static ObservableList<AddIn> getSandwichAddInList(){
        return FXCollections.observableArrayList(AddIn.values());
    }

    /**
     * Returns an observable list containing all the bread values.
     * @return an observable list containing all the bread values.
     */
    public static ObservableList<Bread> getBreadList(){
        return FXCollections.observableArrayList(Bread.values());
    }

    /**
     * Returns an observable list containing all the sandwich type values.
     * @return an observable list containing all the sandwich type values.
     */
    public static ObservableList<SandwichType> getSandwichTypeList(){
        return FXCollections.observableArrayList(SandwichType.values());
    }
}
