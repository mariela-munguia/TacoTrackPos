package models;

public class ChipsAndSalsa implements OrderItem {
    private String salsaType;

    public ChipsAndSalsa(String salsaType) {
        this.salsaType = salsaType;
    }

    public String getName() {
        return "Chips & Salsa";
    }

    public double getPrice() {
        return 1.50;
    }

    public String toString() {
        return "Chips & Salsa (" + salsaType + ") - $" + String.format("%.2f", getPrice());
    }
}
