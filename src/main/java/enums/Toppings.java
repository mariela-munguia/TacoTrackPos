package enums;

public enum Toppings {
    CARNE_ASADA("Carne Asada", ToppingsCategory.MEAT),
    AL_PASTOR("Al Pastor", ToppingsCategory.MEAT),
    CARNITAS("Carnitas", ToppingsCategory.MEAT),
    POLLO("Pollo", ToppingsCategory.MEAT),
    CHORIZO("Chorizo", ToppingsCategory.MEAT),
    PESCADO("Pescado", ToppingsCategory.MEAT),

    QUESO_FRESCO("Queso Fresco", ToppingsCategory.CHEESE),
    OAXACA("Oaxaca", ToppingsCategory.CHEESE),
    COTIJA("Cotija", ToppingsCategory.CHEESE),
    CHEDDAR("Cheddar", ToppingsCategory.CHEESE),

    LETTUCE("Lettuce", ToppingsCategory.REGULAR),
    CILANTRO("Cilantro", ToppingsCategory.REGULAR),
    ONIONS("Onions", ToppingsCategory.REGULAR),
    TOMATOES("Tomatoes", ToppingsCategory.REGULAR),
    JALAPENOS("Jalapenos", ToppingsCategory.REGULAR),
    RADISHES("Radishes", ToppingsCategory.REGULAR),
    PICO_DE_GALLO("Pico de Gallo", ToppingsCategory.REGULAR),
    GUACAMOLE("Guacamole", ToppingsCategory.REGULAR),
    CORN("Corn", ToppingsCategory.REGULAR),

    SALSA_VERDE("Salsa Verde", ToppingsCategory.SAUCE),
    SALSA_ROJA("Salsa Roja", ToppingsCategory.SAUCE),
    CHIPOTLE("Chipotle", ToppingsCategory.SAUCE),
    HABANERO("Habanero", ToppingsCategory.SAUCE),
    MILD("Mild", ToppingsCategory.SAUCE),
    EXTRA_HOT("Extra Hot", ToppingsCategory.SAUCE);

    private String displayName;
    private ToppingsCategory category;

    Toppings(String displayName, ToppingsCategory category) {
        this.displayName = displayName;
        this.category = category;
    }

    public String getDisplayName() { return displayName; }
    public ToppingsCategory getCategory() { return category; }
}
