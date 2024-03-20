package rucafe.gui;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SandwichTest {

    private Sandwich beef, tuna, chicken, undecided;
    private final Sandwich.AddIn [] ADD_INS = Sandwich.AddIn.values();
    private static final double DELTA = 0.00000001;

    @Before
    public void setup(){
        beef = new Sandwich(Sandwich.SandwichType.BEEF, Sandwich.Bread.BAGEL);
        tuna = new Sandwich(Sandwich.SandwichType.FISH, Sandwich.Bread.SOURDOUGH);
        chicken = new Sandwich(Sandwich.SandwichType.CHICKEN, Sandwich.Bread.WHEAT);
    }

    @Test
    public void testChickenAddIns() {
        Assert.assertEquals(8.99, chicken.price(), DELTA);
    }

    @Test
    public void testBeefWithCheese() {
        beef.addAddIn(Sandwich.AddIn.CHEESE);
        Assert.assertEquals(11.99, beef.price(), DELTA);
    }

    @Test
    public void testTunaWithOnionsLettuceTomatoes() {
        tuna.addAddIns(new Sandwich.AddIn[] {Sandwich.AddIn.ONIONS, Sandwich.AddIn.LETTUCE,
                Sandwich.AddIn.TOMATOES});
        Assert.assertEquals(10.89, tuna.price(), DELTA);
    }

    @Test
    public void testChickenWithAllAddIns() {
        for (Sandwich.AddIn extraAddIns : ADD_INS) {
            chicken.addAddIn(extraAddIns);
        }
        Assert.assertEquals(10.89, chicken.price(), DELTA);
    }

    @Test
    public void testRemoveAddIns() {
        undecided = new Sandwich(Sandwich.SandwichType.BEEF, Sandwich.Bread.WHEAT);
        undecided.addAddIns(ADD_INS);
        undecided.removeAddIns(ADD_INS);
        Assert.assertEquals(10.99, undecided.price(), DELTA);
    }

    @Test
    public void testRemoveAndReaddAddIns() {
        undecided = new Sandwich(Sandwich.SandwichType.FISH, Sandwich.Bread.BAGEL);
        undecided.addAddIn(Sandwich.AddIn.ONIONS);
        undecided.removeAddIn(Sandwich.AddIn.ONIONS);
        undecided.addAddIn(Sandwich.AddIn.ONIONS);
        Assert.assertEquals(10.29, undecided.price(), DELTA);
    }

    @Test
    public void testChangeSandwichType() {
        undecided = new Sandwich(Sandwich.SandwichType.CHICKEN, Sandwich.Bread.BAGEL);
        undecided.setSandwichType(Sandwich.SandwichType.FISH);
        Assert.assertEquals(9.99, undecided.price(), DELTA);
    }
}
