package enums;

public enum Tortilla {
    CORN("Corn"),
    FLOUR("Flour"),
    HARD_SHELL("Hard Shell"),
    BOWL("Bowl");

    private String displayName;

    Tortilla(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
