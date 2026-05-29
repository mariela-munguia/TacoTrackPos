package models;

public class ChipsAndSalsa {
    private String salsaType;

    public ChipsAndSalsa(String salsaType) {
        this.salsaType = salsaType;
    }

    public double getPrice() {
        return 1.50;
    }

    public String toString() {
        return "Chips & Salsa (" + salsaType + ") - $"
                + String.format("%.2f", getPrice());
    }
}
