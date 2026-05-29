package models;

import enums.TacoSize;
import enums.Toppings;
import enums.ToppingsCategory;
import enums.Tortilla;

import java.util.ArrayList;

public class Tacos {
    private TacoSize size;
    private Tortilla tortilla;
    private ArrayList<Toppings> toppings;
    private ArrayList<Boolean> extras;
    private boolean specialized;

    public Tacos(TacoSize size, Tortilla tortilla) {
        this.size = size;
        this.tortilla = tortilla;
        this.toppings = new ArrayList<>();
        this.extras = new ArrayList<>();
    }

    public void addTopping(Toppings topping, boolean extra) {
        toppings.add(topping);
        extras.add(extra);
    }

    public void setSpecialized(boolean specialized) {
        this.specialized = specialized;
    }

    public double getPrice() {
        double total = size.getBasePrice();

        for (int i = 0; i < toppings.size(); i++) {
            Toppings topping = toppings.get(i);
            boolean extra = extras.get(i);

            if (topping.getCategory() == ToppingsCategory.MEAT) {
                total += size.getMeatPrice();
                if (extra) {
                    total += size.getExtraMeatPrice();
                }
            } else if (topping.getCategory() == ToppingsCategory.CHEESE) {
                total += size.getCheesePrice();
                if (extra) {
                    total += size.getExtraCheesePrice();
                }
            }
        }

        return total;
    }

    public String toString() {
        String result = size.getDisplayName() + " on " + tortilla.getDisplayName()
                + " - $" + String.format("%.2f", getPrice());

        if (specialized) {
            result += "\n  Specialized: Covered in salsa and queso";
        }

        if (!toppings.isEmpty()) {
            result += "\n  Toppings: ";

            for (int i = 0; i < toppings.size(); i++) {
                if (extras.get(i)) {
                    result += "Extra ";
                }

                result += toppings.get(i).getDisplayName();

                if (i < toppings.size() - 1) {
                    result += ", ";
                }
            }
        }

        return result;
    }
}
