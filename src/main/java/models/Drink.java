package models;

import enums.DrinkSize;

public class Drink implements OrderItem {
    private DrinkSize size;
    private String flavor;

    public Drink(DrinkSize size, String flavor) {
        this.size = size;
        this.flavor = flavor;
    }

    @Override
    public String getName() {
        return size.getDisplayName() + " Drink";
    }

    @Override
    public double getPrice() {
        return size.getPrice();
    }

    @Override
    public String toString() {
        return size.getDisplayName() + " " + flavor + " Drink - $"
                + String.format("%.2f", getPrice());
    }
}

