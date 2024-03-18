package rucafe.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



public class Coffee extends MenuItem {
    private enum Size {
        SHORT, TALL, GRANDE, VENTI;
    }
    private enum AddIns {
        SWEET_CREAM, FRENCH_VANILLA, IRISH_CREAM, CARAMEL, MOCHA;
    }

    private Size size;
    private ObservableList<AddIns> addIns;

    public Coffee (Size size, ObservableList<AddIns> addIns) {
        this.size = size;
        this.addIns = addIns;
        price();
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
        setPrice(price);
        return price;
    }

    public static ObservableList<Size> getSizeList(){
        return FXCollections.observableArrayList(Size.values());
    }

    public static ObservableList<AddIns> getCoffeeAddInList(){
        return FXCollections.observableArrayList(AddIns.values());
    }

    public static void main (String[] args) {
        //testing
        ObservableList<AddIns> addIns1 = Coffee.getCoffeeAddInList();
        ObservableList<Size> size = Coffee.getSizeList();
        ObservableList<AddIns> test = FXCollections.observableArrayList();
        test.add(addIns1.get(2));

        Coffee coffeeTest = new Coffee(size.get(1), test);

        System.out.println(coffeeTest.getPrice());
    }

}
