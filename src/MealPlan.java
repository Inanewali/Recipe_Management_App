import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MealPlan {
    private String name;
    private List<Recipe> recipes;
    private Date startDate;
    private Date endDate;

    public MealPlan(String name, Date startDate, Date endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.recipes = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    public void removeRecipe(Recipe recipe) {
        recipes.remove(recipe);
    }

    public ShoppingList generateShoppingList() {
        ShoppingList shoppingList = new ShoppingList();
        for (Recipe recipe : recipes) {
            for (Ingredient ingredient : recipe.getIngredients()) {
                shoppingList.addIngredient(ingredient);
            }
        }
        return shoppingList;
    }

    public double calculateTotalCalories() {
        double totalCalories = 0;
        for (Recipe recipe : recipes) {
            for (Ingredient ingredient : recipe.getIngredients()) {
                totalCalories += ingredient.calculateCalories();
            }
        }
        return totalCalories;
    }
}
