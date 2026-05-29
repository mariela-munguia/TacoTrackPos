package models;

class TacoTopping {
    private Toppings topping;
    private boolean extra;

    public TacoTopping(Toppings topping, boolean extra) {
        this.topping = topping;
        this.extra = extra;
    }

    public Toppings getTopping() {
        return topping;
    }

    public boolean isExtra() {
        return extra;
    }

    @Override
    public String toString() {
        return (extra ? "Extra " : "") + topping.getDisplayName();
    }
}

