import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String email;
    private String password;
    private List<Recipe> favoriteRecipes;
    private ShoppingList shoppingList;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.favoriteRecipes = new ArrayList<>();
        this.shoppingList = new ShoppingList();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Recipe> getFavoriteRecipes() {
        return favoriteRecipes;
    }

    public void addFavoriteRecipe(Recipe recipe) {
        favoriteRecipes.add(recipe);
    }

    public void removeFavoriteRecipe(Recipe recipe) {
        favoriteRecipes.remove(recipe);
    }

    public ShoppingList getShoppingList() {
        return shoppingList;
    }

    public void generateShoppingList(Recipe recipe) {
        for (Ingredient ingredient : recipe.getIngredients()) {
            shoppingList.addIngredient(ingredient);
        }
    }
}
