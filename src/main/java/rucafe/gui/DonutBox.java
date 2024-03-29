package rucafe.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DonutBox extends MenuItem{
    ObservableList<Donut> donuts;

    public DonutBox () {
        this.donuts = FXCollections.observableArrayList();
        setPrice(price());
    }

    @Override
    public double price() {
        double price = 0;
        for (Donut donut: donuts) {
            price += donut.price();
        }
        return price;
    }

    public void removeDonut(Donut donut) {
        donuts.remove(donut);
        setPrice(price());
    }

    public void addDonut(Donut donut) {
        donuts.add(donut);
        setPrice(price());
    }

    public ObservableList<Donut> getDonutList () {
        return donuts;
    }

    public boolean isEmpty(){
        return donuts.isEmpty();
    }
}