import java.util.ArrayList;
import java.util.List;

public class ShoppingList {
    private List<Ingredient> ingredients;

    public ShoppingList() {
        this.ingredients = new ArrayList<>();
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    public void removeIngredient(Ingredient ingredient) {
        ingredients.remove(ingredient);
    }

    public void mergeWithOtherList(ShoppingList otherList) {
        for (Ingredient ingredient : otherList.getIngredients()) {
            addIngredient(ingredient);
        }
    }

    @Override
    public String toString() {
        StringBuilder listString = new StringBuilder("Shopping List:\n");
        for (Ingredient ingredient : ingredients) {
            listString.append(ingredient.toString()).append("\n");
        }
        return listString.toString();
    }
}
