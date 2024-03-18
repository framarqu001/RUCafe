package rucafe.gui;

public class Donut extends MenuItem {

    private enum DonutType {YEAST, CAKE, HOLE};

    private DonutType donutType;

    public Donut (DonutType donutType) {
        this.donutType = donutType;
        setPrice(price());
    }
    @Override
    public double price () {
        return 0.0;
//        switch (donutType) {
//            case YEAST -> 1.79;
//            case CAKE -> 1.
//        }
    }
}
