public class Ingredient {
    private String name;
    private double quantity;
    private String unit; // e.g., grams, cups
    private int calories;

    public Ingredient(String name, double quantity, String unit, int calories) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public double calculateCalories() {
        return quantity * calories;
    }

    @Override
    public String toString() {
        return quantity + " " + unit + " of " + name + " (" + calculateCalories() + " calories)";
    }
}
