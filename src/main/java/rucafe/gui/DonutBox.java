package rucafe.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DonutBox extends MenuItem{
    private ObservableList<Donut> donuts;
    private Donut pendingDonut;

    public DonutBox () {
        this.donuts = FXCollections.observableArrayList();
        setPrice(price());
    }

    @Override
    public double price() {
        double price = 0;

        if (pendingDonut != null) {
            price += pendingDonut.getPrice();
        }

        for (Donut donut: donuts) {
            price += donut.price();
        }
        return price;
    }

    @Override
    public MenuItem clone() {
        return null;
    }

    public void removeDonut(Donut donut) {
        donuts.remove(donut);
        setPrice(price());
    }

    public void addDonut(Donut donut) {
        donuts.add(donut);
        setPrice(price());
    }

    public void setPendingDonut(Donut donut){
        this.pendingDonut = donut;
        setPrice(price());
    }

    public Donut getPendingDonut(){
        return this.pendingDonut;
    }

    public boolean addPendingDonut() {
        if(pendingDonut != null && !(pendingDonut.isIncomplete())) {
            Donut copy = (Donut)pendingDonut.clone();
            addDonut(copy);
            clearPendingDonut();
            setPrice(price());
            return true;
        }
        return false;
    }

    public void clearPendingDonut() {
        this.pendingDonut = null;
        setPrice(price());
    }

    public boolean hasPendingDonut() {
        return pendingDonut != null;
    }

    public ObservableList<Donut> getDonutList () {
        return donuts;
    }

    public boolean isEmpty(){
        return donuts.isEmpty();
    }
}