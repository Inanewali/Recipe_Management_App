import java.util.List;

public class Recipe {
    private String name;
    private String source;
    private String url;
    private int calories;
    private int servings;
    private String imageUrl;
    private List<Ingredient> ingredients;

    public Recipe(String name, String source, String url, int calories, int servings, String imageUrl, List<Ingredient> ingredients) {
        this.name = name;
        this.source = source;
        this.url = url;
        this.servings = this.servings;
        this.imageUrl = imageUrl;
        this.ingredients = ingredients;
    }

    // Constructor without ingredients
    public Recipe(String name, String source, String url, int calories, String imageUrl) {
        this.name = name;
        this.source = source;
        this.url = url;
        this.calories = calories;
        this.imageUrl = imageUrl;
    }

    // Getters and setters

    public String getName() {
        return name;
    }

    public Object getSource() {
        return source;
    }

    public Object getUrl() {
        return url;
    }

    public Object getCalories() {
        return calories;
    }

    public int getServings() {
        return servings;
    }

    public String getImageUrl() {
        return imageUrl;
    }


    public List<Ingredient> getIngredients() {
        return ingredients;
    }


    @Override
    public String toString() {
        return String.format("Recipe Name: %s\nSource: %s\nURL: %s\nCalories: %d\n\n",
                this.name, this.source, this.url, this.calories);
    }
}



