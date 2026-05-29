package enums;

public enum DrinkSize {
    SMALL("Small", 2.00),
    MEDIUM("Medium", 2.50),
    LARGE("Large", 3.00);

    private String displayName;
    private double price;

    DrinkSize(String displayName, double price) {
        this.displayName = displayName;
        this.price = price;
    }

    public String getDisplayName() { return displayName; }
    public double getPrice() { return price; }
}
