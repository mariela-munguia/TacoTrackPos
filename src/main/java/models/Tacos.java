package models;

import enums.TacoSize;
import enums.ToppingsCategory;
import enums.Tortilla;

import java.util.ArrayList;
import java.util.List;

public class Tacos implements OrderItem {
    private TacoSize size;
    private Tortilla tortilla;
    public List<TacoToppings> toppings;
    private boolean specialized;

    public Tacos(TacoSize size, Tortilla tortilla) {
        this.size = size;
        this.tortilla = tortilla;
        this.toppings = new ArrayList<>();
    }

    public void addTopping(Toppings topping, boolean extra) {
        toppings.add(new TacoToppings(topping, extra));
    }

    public void setSpecialized(boolean specialized) {
        this.specialized = specialized;
    }

    @Override
    public String getName() {
        return size.getDisplayName();
    }

    @Override
    public double getPrice() {
        double total = size.getBasePrice();

        for (TacoToppings tacoTopping : toppings) {
            ToppingsCategory category = tacoTopping.getTopping().getCategory();

            if (category == ToppingsCategory.MEAT) {
                total += size.getMeatPrice();

                if (tacoTopping.isExtra()) {
                    total += size.getExtraMeatPrice();
                }
            } else if (category == ToppingsCategory.CHEESE) {
                total += size.getCheesePrice();

                if (tacoTopping.isExtra()) {
                    total += size.getExtraCheesePrice();
                }
            }
        }

        return total;
    }

    @Override
    public String toString() {
        String result = size.getDisplayName() + " on " + tortilla.getDisplayName()
                + " - $" + String.format("%.2f", getPrice());

        if (specialized) {
            result += "\n  Specialized: Covered in salsa and queso";
        }

        if (!toppings.isEmpty()) {
            result += "\n  Toppings: ";

            for (int i = 0; i < toppings.size(); i++) {
                result += toppings.get(i).toString();

                if (i < toppings.size() - 1) {
                    result += ", ";
                }
            }
        }

        return result;
    }

    private class TacoToppings {
    }
}
