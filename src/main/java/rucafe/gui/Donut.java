package rucafe.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.text.DecimalFormat;

public class Donut extends MenuItem {

    /**
     * Enum meant to represent the flavor of donut offered.
     */
    enum Flavor {
        JELLY("JELLY"), APPLE_CIDER("APPLE CIDER"), BOSTON_CREAM("BOSTON CREAM"), //Yeast
        CHOCOLATE("CHOCOLATE"), POWDERED("POWDERED"), GLAZED("GLAZED"), //Yeast & Holes
        VANILLA("VANILLA"), PINK_FROSTED("PINK FROSTED"), FUNFETTI("FUNFETTI"); //Cake

        final String name;

        /**
         * Constructor for flavor, takes in name of flavor as parameter
         * @param name the name of the flavor.
         */
        Flavor(String name){
            this.name = name;
        }

        /**
         * Returns a string representing the flavor of donut.
         * @return a string with the flavor of donut.
         */
        @Override
        public String toString() {
            return this.name;
        }

    }

    /**
     * Enum meant to represent the type of donut a person can order along with their prices.
     */
    enum Type {
        YEAST(1.79), CAKE(1.89), HOLE(0.39);
        final double price;

        /**
         * Constructor for donut type, takes in price as parameter
         * @param price the price of the type of donut.
         */
        Type(double price) {
            this.price = price;
        }

        /**
         * Returns price of the type of donut
         * @return price of type of donut.
         */
        public double getPrice() {
            return this.price;
        }

        /**
         * Returns an observable array of flavors that the corresponding donut type can come in.
         * @return an observable array of flavors that the corresponding donut type can come in.
         */
        public ObservableList<Flavor> getFlavors() {
            return switch (this) {
                case YEAST -> FXCollections.observableArrayList(Flavor.CHOCOLATE, Flavor.POWDERED, Flavor.GLAZED,
                        Flavor.JELLY, Flavor.APPLE_CIDER, Flavor.BOSTON_CREAM);
                case HOLE -> FXCollections.observableArrayList(Flavor.CHOCOLATE, Flavor.POWDERED, Flavor.GLAZED);
                case CAKE -> FXCollections.observableArrayList(Flavor.VANILLA, Flavor.PINK_FROSTED, Flavor.FUNFETTI);
            };
        }

        /**
         * Returns a string representing the type of donut and its price.
         * @return a string with all the information about a donut.
         */
        @Override
        public String toString() {
            return this.name() + " - $" + price;
        }
    };

    private Type type;
    private Flavor flavor;
    private int quantity;


    public Donut (Type type, int quantity) {
        this.type = type;
        this.quantity = quantity;
        setPrice(price());
    }

    public Donut(Donut donut){
        this.flavor = donut.flavor;
        this.type = donut.type;
        this.quantity = donut.quantity;
        setPrice(price());
    }

    public void setType(Type type) {
        this.type = type;
        setPrice(price());
    }

    public void setFlavor(Flavor flavor) {
        this.flavor = flavor;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        setPrice(price());
    }

    public int getQuantity() {
        return this.quantity;
    }

    public String getDonutText() {
        if(!isIncomplete()) {
            String donut = (type == Type.HOLE) ? "donut " + type.name().toLowerCase() : type.name().toLowerCase()
                    + " donut";
            return flavor.name.toLowerCase() + " " + donut;
        }
        return "incomplete donut";
    }

    /**
     * Determines if a donut does not have its flavor set.
     * @return true if flavor is null, false otherwise.
     */
    public boolean isIncomplete() {
        return flavor == null;
    }

    @Override
    public double price() {
        int rounding = 100;
        double price = type.getPrice() * rounding;
        return (price * quantity) / rounding;
    }

    @Override
    public MenuItem clone() {
        return new Donut(this);
    }

    @Override
    public String toString() {
        if(!isIncomplete()) {
            return getDonutText() + " quantity (" + quantity + ") - $" + price;
        }
        return "incomplete donut";
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof Donut) {
            Donut donut = (Donut) o;
            return this.flavor == donut.flavor && this.type == donut.type && this.quantity == donut.quantity;
        }
        return false;
    }

}