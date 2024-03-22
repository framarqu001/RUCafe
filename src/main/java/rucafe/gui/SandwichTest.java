package rucafe.gui;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SandwichTest {

    private Sandwich beef, tuna, chicken, undecided;
    private final Sandwich.AddOn [] ADD_ONS = Sandwich.AddOn.values();
    private static final double DELTA = 0.00000001;

    @Before
    public void setup(){
        beef = new Sandwich(Sandwich.Protein.BEEF, Sandwich.Bread.BAGEL);
        tuna = new Sandwich(Sandwich.Protein.FISH, Sandwich.Bread.SOURDOUGH);
        chicken = new Sandwich(Sandwich.Protein.CHICKEN, Sandwich.Bread.WHEAT);
    }

    @Test
    public void testChickenNoAddOns() {
        Assert.assertEquals(8.99, chicken.price(), DELTA);
    }

    @Test
    public void testBeefWithCheese() {
        beef.addAddOn(Sandwich.AddOn.CHEESE);
        Assert.assertEquals(11.99, beef.price(), DELTA);
    }

    @Test
    public void testTunaWithOnionsLettuceTomatoes() {
        tuna.addAddOns(new Sandwich.AddOn[] {Sandwich.AddOn.ONIONS, Sandwich.AddOn.LETTUCE,
                Sandwich.AddOn.TOMATOES});
        Assert.assertEquals(10.89, tuna.price(), DELTA);
    }

    @Test
    public void testChickenWithAllAddOns() {
        for (Sandwich.AddOn extraAddOns : ADD_ONS) {
            chicken.addAddOn(extraAddOns);
        }
        Assert.assertEquals(10.89, chicken.price(), DELTA);
    }

    @Test
    public void testRemoveAddOns() {
        undecided = new Sandwich(Sandwich.Protein.BEEF, Sandwich.Bread.WHEAT);
        undecided.addAddOns(ADD_ONS);
        undecided.removeAddOns(ADD_ONS);
        Assert.assertEquals(10.99, undecided.price(), DELTA);
    }

    @Test
    public void testRemoveAndReaddAddOns() {
        undecided = new Sandwich(Sandwich.Protein.FISH, Sandwich.Bread.BAGEL);
        undecided.addAddOn(Sandwich.AddOn.ONIONS);
        undecided.removeAddOn(Sandwich.AddOn.ONIONS);
        undecided.addAddOn(Sandwich.AddOn.ONIONS);
        Assert.assertEquals(10.29, undecided.price(), DELTA);
    }

    @Test
    public void testChangeSandwichType() {
        undecided = new Sandwich(Sandwich.Protein.CHICKEN, Sandwich.Bread.BAGEL);
        undecided.setProtein(Sandwich.Protein.FISH);
        Assert.assertEquals(9.99, undecided.price(), DELTA);
    }
}
