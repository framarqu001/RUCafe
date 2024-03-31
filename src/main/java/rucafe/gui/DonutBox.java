package rucafe.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Class meant to represent a donut box, contains a collection of donuts as well as a pending donut object that
 * may or may not be added to the donut box.
 */
public class DonutBox extends MenuItem{
    private ObservableList<Donut> donuts;
    private Donut pendingDonut;

    /**
     * Default constructor for a donut box, initializes the list of donuts as well as the current price.
     */
    public DonutBox () {
        this.donuts = FXCollections.observableArrayList();
        setPrice(price());
    }

    /**
     * Calculates the price of the donut box depending on the donuts currently in the box as well as any donuts
     * pending to be added to the box.
     * @return the current price of the donut box.
     */
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

    /**
     * Given a donut object, removes it from the box if it was in there.
     * @param donut the donut to be removed.
     */
    public void removeDonut(Donut donut) {
        donuts.remove(donut);
        setPrice(price());
    }

    /**
     * Given a donut, adds it to the donut box.
     * @param donut the donut to be added.
     */
    public void addDonut(Donut donut) {
        donuts.add(donut);
        setPrice(price());
    }

    /**
     * Given a donut, sets the box's pending donut to that donut object
     * @param donut the new value for the box's pending donut
     */
    public void setPendingDonut(Donut donut){
        this.pendingDonut = donut;
        setPrice(price());
    }

    /**
     * Returns the current pending donut in the box
     * @return the current pending donut
     */
    public Donut getPendingDonut(){
        return this.pendingDonut;
    }

    /**
     * If the box has a pending donut, adds that donut to the box and then clears reference from pending donut variable
     * @return true if the operation was successful, false if otherwise.
     */
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

    /**
     * Clears the reference from pending donut variable.
     */
    public void clearPendingDonut() {
        this.pendingDonut = null;
        setPrice(price());
    }

    /**
     * Determines if donut box has a pending donut.
     * @return true if there is a pending donut in the box, false if otherwise.
     */
    public boolean hasPendingDonut() {
        return pendingDonut != null;
    }

    /**
     * Returns an observable list containing all the donuts currently in the box.
     * @return an observable list containing all the donuts currently in the box.
     */
    public ObservableList<Donut> getDonutList () {
        return donuts;
    }

    /**
     * Determines if the donut box is empty.
     * @return true if the donut box is empty, false if otherwise.
     */
    public boolean isEmpty(){
        return donuts.isEmpty();
    }
}